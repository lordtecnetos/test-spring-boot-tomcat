package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BancoForm implements Serializable {

    private Long codigo;

    @Size(min = 1, max = 100, message = "erro.banco.nome.size")
    @NotBlank(message = "erro.banco.nome.notblank")
    private String nome;

    private boolean ativo;

}
