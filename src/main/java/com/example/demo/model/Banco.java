package com.example.demo.model;

import com.example.demo.EntidadePersistente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BANCO")
@EqualsAndHashCode(of = "codigo")
public class Banco implements EntidadePersistente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean ativo;

    public Banco(Long codigo) {
        this.codigo = codigo;
    }

}
