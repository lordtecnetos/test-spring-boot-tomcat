package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.LabelValue;
import com.example.demo.model.Banco;

interface BancoRepository extends JpaRepository<Banco, Long> {

    @Query("""
            select new com.example.demo.dto.LabelValue(
                b.codigo, 
                b.nome)
            from 
                Banco b
            where 
                (b.ativo = ?1 or ?1 is null)
            """)
    List<LabelValue> findAutocompleteByAtivos(Boolean ativos);

}
