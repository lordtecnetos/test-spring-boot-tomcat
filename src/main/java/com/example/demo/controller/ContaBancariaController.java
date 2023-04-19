package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ServiceException;
import com.example.demo.component.ContaBancariaComponent;
import com.example.demo.form.ContaBancariaForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/conta-bancaria")
public class ContaBancariaController {

    private final ContaBancariaComponent component;

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("form", component.novo());
        return "contaBancaria/novo";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("dtos", component.listar());
        return "contaBancaria/lista";
    }

    @GetMapping("/visualizar/{codigo}")
    public String visualizar(@PathVariable Long codigo, Model model) {
        model.addAttribute("form", component.visualizar(codigo).orElseThrow());
        return "contaBancaria/visualizar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("form") @Valid ContaBancariaForm form, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "contaBancaria/novo";
        }
        try {
            component.salvar(form);
            redirect.addFlashAttribute("success", "Salvo com sucesso!");
            return "redirect:listar";

        } catch (ServiceException e) {
            result.reject("error", e.getMessage());
            return "contaBancaria/novo";
        }
    }

    @PostMapping("/alterar/{codigo}")
    public String alterar(@ModelAttribute("form") @Valid ContaBancariaForm form, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "contaBancaria/visualizar";
        }
        try {
            component.alterar(form);
            redirect.addFlashAttribute("success", "Alterado com sucesso!");
            return "redirect:../listar";

        } catch (ServiceException e) {
            result.reject("error", e.getMessage());
            return "contaBancaria/visualizar";
        }
    }

    @PostMapping("/excluir/{codigo}")
    public String excluir(@PathVariable Long codigo, RedirectAttributes redirect) {
        try {
            component.excluir(codigo);
            redirect.addFlashAttribute("success", "Excluído com sucesso!");
            return "redirect:../listar";

        } catch (ServiceException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:../visualizar/{codigo}";
        }
    }

}
