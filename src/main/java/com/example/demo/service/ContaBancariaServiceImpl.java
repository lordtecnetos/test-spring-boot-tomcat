package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.CrudServiceImpl;
import com.example.demo.ServiceException;
import com.example.demo.dto.ContaBancariaDTO;
import com.example.demo.model.Banco;
import com.example.demo.model.ContaBancaria;

@Service
class ContaBancariaServiceImpl extends CrudServiceImpl<ContaBancaria, ContaBancariaRepository>
        implements ContaBancariaService {

    private final BancoService bancoService;

    public ContaBancariaServiceImpl(ContaBancariaRepository repository, BancoService bancoService) {
        super(repository);
        this.bancoService = bancoService;
    }

    @Override
    protected void antesInserir(ContaBancaria entidade) {
        var banco = bancoService.buscar(entidade.getBanco().getCodigo())
                .orElseThrow(ServiceException.notFound(Banco.class));
        if (!banco.isAtivo()) {
            throw new ServiceException("erro.banco.inativo");
        }
    }

    public List<ContaBancariaDTO> listarDTO() {
        return repository.findAllDTO();
    }

}
