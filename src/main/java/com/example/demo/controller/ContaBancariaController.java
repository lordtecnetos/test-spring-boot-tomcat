package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @ModelAttribute
    public void addAttributes(Model model) {
        // model.addAttribute("bancos", bancoComponent.autocomplete(true));
    }

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
        return "contaBancaria/visualiza";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("form") @Valid ContaBancariaForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "contaBancaria/novo";
        }
        try {
            component.salvar(form);
            return "redirect:/conta-bancaria/listar";

        } catch (ServiceException e) {
            result.reject("error", e.getMessage());
            return "contaBancaria/novo";
        }
    }

    @PostMapping("/alterar/{codigo}")
    public String alterar(@ModelAttribute("form") @Valid ContaBancariaForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "contaBancaria/visualizar";
        }
        component.alterar(form);
        return "redirect:/conta-bancaria/listar";
    }

    @PostMapping("/excluir/{codigo}")
    public String excluir(@PathVariable Long codigo) {
        component.excluir(codigo);
        return "redirect:/conta-bancaria/listar";
    }

}
