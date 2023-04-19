package com.example.demo.converter;

import org.springframework.stereotype.Component;

import com.example.demo.form.ContaBancariaForm;
import com.example.demo.model.Banco;
import com.example.demo.model.ContaBancaria;

@Component
public class ContaBancariaConverter {

    public ContaBancaria toEntidade(ContaBancariaForm form) {
        return this.toEntidade(form, new ContaBancaria());
    }

    public ContaBancaria toEntidade(ContaBancariaForm form, ContaBancaria entidade) {
        entidade.setCodigo(form.getCodigo());
        entidade.setNome(form.getNome());
        entidade.setAtivo(form.isAtivo());
        entidade.setBanco(new Banco(form.getCodigoBanco()));
        return entidade;
    }

    public ContaBancariaForm toForm(ContaBancaria entidade) {
        var form = new ContaBancariaForm();
        form.setCodigo(entidade.getCodigo());
        form.setNome(entidade.getNome());
        form.setAtivo(entidade.isAtivo());
        form.setCodigoBanco(entidade.getBanco().getCodigo());
        form.setNomeBanco(entidade.getBanco().getNome());
        return form;
    }

}
