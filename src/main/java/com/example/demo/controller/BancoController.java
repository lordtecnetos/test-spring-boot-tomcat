package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.ServiceException;
import com.example.demo.component.BancoComponent;
import com.example.demo.dto.LabelValue;
import com.example.demo.form.BancoForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/banco")
public class BancoController {

    private final BancoComponent component;

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("form", component.novo());
        return "banco/novo";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("dtos", component.listar());
        return "banco/lista";
    }

    @GetMapping("/visualizar/{codigo}")
    public String visualizar(@PathVariable Long codigo, Model model) {
        model.addAttribute("form", component.visualizar(codigo).orElseThrow());
        return "banco/visualiza";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("form") @Valid BancoForm form, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "banco/novo";
        }
        try {
            component.salvar(form);
            redirect.addFlashAttribute("success", "Salvo com sucesso!");
            return "redirect:/banco/listar";

        } catch (ServiceException e) {
            result.reject("error", e.getMessage());
            return "banco/novo";
        }
    }

    @PostMapping("/alterar/{codigo}")
    public String alterar(@ModelAttribute("form") BancoForm form, Model model, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "banco/visualiza";
        }
        try {
            component.alterar(form);
            redirect.addFlashAttribute("success", "Alterado com sucesso!");
            return "redirect:/banco/listar";

        } catch (ServiceException e) {
            result.reject("error", e.getMessage());
            return "banco/visualiza";
        }
    }

    @PostMapping("/excluir/{codigo}")
    public String excluir(@PathVariable Long codigo, Model model, RedirectAttributes redirect) {
        try {
            component.excluir(codigo);
            redirect.addFlashAttribute("success", "Excluído com sucesso!");
            return "redirect:/banco/listar";

        } catch (ServiceException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/banco/visualizar/{codigo}";
        }
    }

    @GetMapping("/autocomplete")
    public @ResponseBody List<LabelValue> autocomplete(
            @RequestParam(required = false, defaultValue = Strings.EMPTY, value = "search") String nome,
            @RequestParam(required = false) Boolean ativos) {
        return component.autocomplete(nome, ativos);
    }

}
