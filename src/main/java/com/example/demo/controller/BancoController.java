package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.MessagesComponent;
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

    private final MessagesComponent messages;
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
        model.addAttribute("form",
                component.visualizar(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "banco/visualizar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("form") @Valid BancoForm form, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "banco/novo";
        }
        try {
            component.salvar(form);
            messages.success(redirect, "salvo.com.sucesso");
            return "redirect:listar";

        } catch (ServiceException e) {
            messages.error(result, e);
            return "banco/novo";
        }
    }

    @PostMapping("/salvar")
    public @ResponseBody MessagesComponent.Message salvarJson(@ModelAttribute("form") @Valid BancoForm form,
            BindingResult result) {
        messages.validate(result);

        try {
            component.salvar(form);
            return messages.success("salvo.com.sucesso");

        } catch (ServiceException e) {
            throw messages.error(e);
        }
    }

    @PostMapping("/alterar/{codigo}")
    public String alterar(@ModelAttribute("form") BancoForm form, BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "banco/visualizar";
        }
        try {
            component.alterar(form);
            redirect.addFlashAttribute("success", "Alterado com sucesso!");
            return "redirect:../listar";

        } catch (ServiceException e) {
            result.reject("error", e.getMessage());
            return "banco/visualizar";
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

    @GetMapping("/autocomplete")
    public @ResponseBody List<LabelValue> autocomplete(
            @RequestParam(required = false, defaultValue = Strings.EMPTY, value = "search") String nome,
            @RequestParam(required = false) Boolean ativos) {
        return component.autocomplete(nome, ativos);
    }

}
