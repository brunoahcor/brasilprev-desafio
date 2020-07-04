package br.com.brasilprev.desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.brasilprev.desafio.model.vo.PedidoVO;

public interface PedidoService {

    PedidoVO salvar(PedidoVO vo) throws Exception;

    Page<PedidoVO> listar(Pageable pageable);

    PedidoVO buscarPorId(Long id) throws Exception;

    void deletar(Long id) throws Exception;
    
}