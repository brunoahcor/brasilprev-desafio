package br.com.brasilprev.desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.brasilprev.desafio.model.vo.PedidoVO;

public interface PedidoService {

    PedidoVO salvar(PedidoVO vo);

    Page<PedidoVO> listar(Pageable pageable);

    PedidoVO buscarPorId(Long id);

    void deletar(Long id);
    
}