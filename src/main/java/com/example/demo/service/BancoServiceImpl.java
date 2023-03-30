package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.CrudServiceImpl;
import com.example.demo.dto.BancoDTO;
import com.example.demo.dto.LabelValue;
import com.example.demo.model.Banco;

@Service
class BancoServiceImpl extends CrudServiceImpl<Banco, BancoRepository> implements BancoService {

    public BancoServiceImpl(BancoRepository repository) {
        super(repository);
    }

    @Override
    public List<BancoDTO> listarDTO() {
        return repository.findAllDTO();
    }

    @Override
    public List<LabelValue> listarLabelValue(String nome, Boolean ativos) {
        return repository.findByNomeLikeAndAtivo(nome, ativos);
    }

}
