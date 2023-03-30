package com.example.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BancoDTO implements Serializable {
    
    private Long codigo;
    private String nome;
    private boolean ativo;

}
