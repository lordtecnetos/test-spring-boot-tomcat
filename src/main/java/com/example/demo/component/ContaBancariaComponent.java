package com.example.demo.component;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.ServiceException;
import com.example.demo.converter.ContaBancariaConverter;
import com.example.demo.dto.ContaBancariaDTO;
import com.example.demo.form.ContaBancariaForm;
import com.example.demo.model.ContaBancaria;
import com.example.demo.service.ContaBancariaService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ContaBancariaComponent {

    private final ContaBancariaService service;
    private final ContaBancariaConverter converter;

    public ContaBancariaForm novo() {
        var form = new ContaBancariaForm();
        form.setAtivo(true);
        return form;
    }

    public List<ContaBancariaDTO> listar() {
        return service.listarDTO();
    }

    public Optional<ContaBancariaForm> visualizar(Long codigo) {
        return service.buscar(codigo).map(converter::toForm);
    }

    public void salvar(ContaBancariaForm form) {
        service.inserir(converter.toEntidade(form));
    }

    public void alterar(ContaBancariaForm form) {
        var entidade = service.buscar(form.getCodigo()).orElseThrow(ServiceException.notFound(ContaBancaria.class));
        service.alterar(converter.toEntidade(form, entidade));
    }

    public void excluir(Long codigo) {
        service.excluir(codigo);
    }

}
