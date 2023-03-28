package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudService<T, R extends JpaRepository<T, Long>> {
    
    Optional<T> buscar(Long codigo);

    List<T> listar();

    T inserir(T entidade);

    T salvar(T entidade);

    T alterar(T entidade);

    void excluir(T entidade);

    void excluir(Long codigo);

}
