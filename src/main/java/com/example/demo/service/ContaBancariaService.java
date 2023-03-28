package com.example.demo.service;

import java.util.List;

import com.example.demo.CrudService;
import com.example.demo.dto.ContaBancariaDTO;
import com.example.demo.model.ContaBancaria;

public interface ContaBancariaService extends CrudService<ContaBancaria, ContaBancariaRepository> {

    List<ContaBancariaDTO> listarDTO();

}
