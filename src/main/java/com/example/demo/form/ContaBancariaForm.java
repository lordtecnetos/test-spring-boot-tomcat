package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaBancariaForm implements Serializable {

    private Long codigo;

    @NotBlank
    private String nome;

    private boolean ativo;

    @NotNull
    private Long codigoBanco;

    private String nomeBanco;

}
