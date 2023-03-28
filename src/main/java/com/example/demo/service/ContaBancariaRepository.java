package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.ContaBancariaDTO;
import com.example.demo.model.ContaBancaria;

interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

    @Query("""
            select new com.example.demo.dto.ContaBancariaDTO(
                cb.codigo,
                cb.nome,
                cb.ativo,
                cb.banco.nome)
            from 
                ContaBancaria cb
            """)
    List<ContaBancariaDTO> findAllDTO();

}
