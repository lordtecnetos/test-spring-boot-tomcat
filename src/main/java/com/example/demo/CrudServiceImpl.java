package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudServiceImpl<T extends EntidadePersistente, R extends JpaRepository<T, Long>>
        implements CrudService<T, R> {

    protected final R repository;

    @Override
    public Optional<T> buscar(Long codigo) {
        return repository.findById(codigo);
    }

    @Override
    public List<T> listar() {
        return repository.findAll();
    }

    protected void antesInserir(T entidade) {

    }

    @Override
    public T inserir(T entidade) {
        this.antesInserir(entidade);
        repository.saveAndFlush(entidade);
        this.depoisInserir(entidade);
        return entidade;
    }

    protected void depoisInserir(T entidade) {

    }

    @Override
    public T salvar(T entidade) {
        if (entidade.getCodigo() != null) {
            return this.alterar(entidade);
        } else {
            return this.inserir(entidade);
        }
    }

    protected void antesAlterar(T entidade) {

    }

    @Override
    public T alterar(T entidade) {
        this.antesAlterar(entidade);
        repository.saveAndFlush(entidade);
        this.depoisAlterar(entidade);
        return entidade;
    }

    protected void depoisAlterar(T entidade) {

    }

    protected void antesExcluir(T entidade) {

    }

    @Override
    public void excluir(T entidade) {
        this.antesExcluir(entidade);
        repository.delete(entidade);
        repository.flush();
        this.depoisExcluir(entidade);
    }

    @Override
    public void excluir(Long codigo) {
        this.buscar(codigo).ifPresent(this::excluir);
    }

    protected void depoisExcluir(T entidade) {

    }

}
