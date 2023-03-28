package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudServiceImpl<T, R extends JpaRepository<T, Long>> implements CrudService<T, R> {

    protected final R repository;

    @Override
    public Optional<T> buscar(Long codigo) {
        return repository.findById(codigo);
    }

    @Override
    public List<T> listar() {
        return repository.findAll();
    }

    @Override
    public T inserir(T entidade) {
        return repository.save(entidade);
    }

    @Override
    public T salvar(T entidade) {
        return repository.save(entidade);
    }

    @Override
    public T alterar(T entidade) {
        return repository.save(entidade);
    }

    @Override
    public void excluir(T entidade) {
        repository.delete(entidade);
    }

    @Override
    public void excluir(Long codigo) {
        repository.deleteById(codigo);
    }

}
