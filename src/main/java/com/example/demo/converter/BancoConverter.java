package com.example.demo.converter;

import org.springframework.stereotype.Component;

import com.example.demo.form.BancoForm;
import com.example.demo.model.Banco;

@Component
public class BancoConverter {

    public Banco toEntidade(BancoForm form) {
        return this.toEntidade(form, new Banco());
    }

    public Banco toEntidade(BancoForm form, Banco entidade) {
        entidade.setCodigo(form.getCodigo());
        entidade.setNome(form.getNome());
        entidade.setAtivo(form.isAtivo());
        return entidade;
    }

    public BancoForm toForm(Banco entidade) {
        var form = new BancoForm();
        form.setCodigo(entidade.getCodigo());
        form.setNome(entidade.getNome());
        form.setAtivo(entidade.isAtivo());
        return form;
    }

}
