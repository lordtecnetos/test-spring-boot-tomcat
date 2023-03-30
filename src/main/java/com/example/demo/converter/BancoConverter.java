package com.example.demo.converter;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.example.demo.form.BancoForm;
import com.example.demo.model.Banco;

@Component
public class BancoConverter implements Serializable {

    public BancoForm toForm(Banco entidade) {
        var form = new BancoForm();
        form.setCodigo(entidade.getCodigo());
        form.setNome(entidade.getNome());
        form.setAtivo(entidade.isAtivo());
        return form;
    }

    public Banco toEntidade(BancoForm form) {
        var entidade = new Banco();
        entidade.setCodigo(form.getCodigo());
        entidade.setNome(form.getNome());
        entidade.setAtivo(form.isAtivo());
        return entidade;
    }
 
}
