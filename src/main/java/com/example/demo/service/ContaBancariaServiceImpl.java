package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.CrudServiceImpl;
import com.example.demo.dto.ContaBancariaDTO;
import com.example.demo.model.ContaBancaria;

@Service
class ContaBancariaServiceImpl extends CrudServiceImpl<ContaBancaria, ContaBancariaRepository>
        implements ContaBancariaService {

    public ContaBancariaServiceImpl(ContaBancariaRepository repository) {
        super(repository);
    }

    public List<ContaBancariaDTO> listarDTO() {
        return repository.findAllDTO();
    }

}
