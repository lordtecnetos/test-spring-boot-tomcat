package com.example.demo.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

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

    @Size(max = 100, message = "erro.bancoform.nome.size")
    @NotBlank(message = "erro.bancoform.nome.notblank")
    private String nome;

    private boolean ativo;

}
