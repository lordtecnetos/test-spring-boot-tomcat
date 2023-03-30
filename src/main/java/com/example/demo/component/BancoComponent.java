package com.example.demo.component;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.converter.BancoConverter;
import com.example.demo.dto.BancoDTO;
import com.example.demo.dto.LabelValue;
import com.example.demo.form.BancoForm;
import com.example.demo.service.BancoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BancoComponent {
    
    private final BancoService service;
    private final BancoConverter converter;

    public BancoForm novo() {
        var form = new BancoForm();
        form.setAtivo(true);
        return form;
    }

    public List<BancoDTO> listar() {
        return service.listarDTO();
    }

    public Optional<BancoForm> visualizar(Long codigo) {
        return service.buscar(codigo).map(converter::toForm);
    }

    public void salvar(BancoForm form) {
        service.inserir(converter.toEntidade(form));
    }

    public void alterar(BancoForm form) {
        service.alterar(converter.toEntidade(form));
    }

    public void excluir(Long codigo) {
        service.excluir(codigo);
    }

    public List<LabelValue> autocomplete(String nome, Boolean ativos) {
        return service.listarLabelValue(nome, ativos);
    }

}
