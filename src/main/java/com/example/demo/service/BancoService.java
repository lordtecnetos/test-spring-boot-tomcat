package com.example.demo.service;

import java.util.List;

import com.example.demo.CrudService;
import com.example.demo.dto.LabelValue;
import com.example.demo.model.Banco;

public interface BancoService extends CrudService<Banco, BancoRepository> {

    List<LabelValue> listarAtivosParaAutocomplete(Boolean ativos);

}
