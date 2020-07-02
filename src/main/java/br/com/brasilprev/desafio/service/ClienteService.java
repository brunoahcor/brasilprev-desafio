package br.com.brasilprev.desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.brasilprev.desafio.model.vo.ClienteVO;

public interface ClienteService {

    ClienteVO salvar(ClienteVO vo);

    Page<ClienteVO> listar(Pageable pageable);

    ClienteVO buscarPorId(Long id);

    ClienteVO buscarPorCpf(String cpf);

    void deletar(Long id);
    
}