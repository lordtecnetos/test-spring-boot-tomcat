package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BancoForm implements Serializable {
    
    private Long codigo;
    
    @NotBlank
    private String nome;
    
    private boolean ativo;

}
