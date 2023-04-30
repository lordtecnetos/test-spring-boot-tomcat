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

import com.example.demo.ServiceException;
import com.example.demo.component.BancoComponent;
import com.example.demo.dto.LabelValue;
import com.example.demo.form.BancoForm;
import com.example.demo.view.component.MessageComponent;
import com.example.demo.view.component.ValidationsComponent;
import com.example.demo.view.model.Message;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/banco")
public class BancoController {

    private final ValidationsComponent validations;
    private final MessageComponent message;
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
            Model model, RedirectAttributes redirect) {

        if (result.hasErrors()) {
            message.error(model, result.getAllErrors());
            return "banco/novo";
        }
        try {
            component.salvar(form);
            message.success(redirect, "banco.salvo.com.sucesso");
            return "redirect:listar";

        } catch (ServiceException e) {
            message.error(model, e);
            return "banco/novo";
        }
    }

    @PostMapping("/salvar-json")
    public @ResponseBody Message salvarJson(@ModelAttribute("form") @Valid BancoForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            throw message.error(result.getAllErrors());
        }
        try {
            component.salvar(form);
            return message.success("salvo.com.sucesso");

        } catch (ServiceException e) {
            throw message.error(e);
        }
    }

    @PostMapping("/alterar/{codigo}")
    public String alterar(@ModelAttribute("form") BancoForm form, BindingResult result,
            Model model, RedirectAttributes redirect) {

        if (result.hasErrors()) {
            message.error(model, result.getAllErrors());
            return "banco/visualizar";
        }
        try {
            component.alterar(form);
            message.success(redirect, "banco.alterado.com.sucesso");
            return "redirect:../listar";

        } catch (ServiceException e) {
            message.error(model, e);
            return "banco/visualizar";
        }
    }

    @PostMapping("/excluir/{codigo}")
    public String excluir(@PathVariable Long codigo, RedirectAttributes redirect) {
        try {
            component.excluir(codigo);
            message.success(redirect, "banco.excluido.com.sucesso");
            return "redirect:../listar";

        } catch (ServiceException e) {
            message.error(redirect, e);
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
