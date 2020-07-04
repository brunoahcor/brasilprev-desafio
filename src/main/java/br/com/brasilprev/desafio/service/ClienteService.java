package br.com.brasilprev.desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.brasilprev.desafio.exception.BusinessException;
import br.com.brasilprev.desafio.model.vo.ClienteVO;

public interface ClienteService {

    Page<ClienteVO> listar(Pageable pageable);

    ClienteVO salvar(ClienteVO vo) throws Exception;

    ClienteVO buscarPorId(Long id) throws Exception ;

    ClienteVO buscarPorCpf(String cpf) throws BusinessException;

    void deletar(Long id) throws Exception;
    
}