package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.component.BancoComponent;
import com.example.demo.dto.LabelValue;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/banco")
public class BancoController {

    private final BancoComponent component;

    @GetMapping("/autocomplete")
    public @ResponseBody List<LabelValue> autocomplete(@RequestParam(required = false) Boolean ativos, Model model) {
        return component.autocomplete(ativos);
    }

}
