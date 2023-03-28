package com.example.demo.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.LabelValue;
import com.example.demo.service.BancoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BancoComponent {
    
    private final BancoService service;

    public List<LabelValue> autocomplete(Boolean ativos) {
        return service.listarAtivosParaAutocomplete(ativos);
    }

}
