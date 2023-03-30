package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.BancoDTO;
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
                b.nome like concat('%', ?1, '%')
                and (b.ativo = ?2 or ?2 is null)
            """)
    List<LabelValue> findByNomeLikeAndAtivo(String nome, Boolean ativos);

    @Query("""
            select new com.example.demo.dto.BancoDTO(
                b.codigo,
                b.nome,
                b.ativo)
            from 
                Banco b
            """)
    List<BancoDTO> findAllDTO();

}
