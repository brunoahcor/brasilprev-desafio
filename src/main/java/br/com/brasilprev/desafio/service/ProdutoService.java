package br.com.brasilprev.desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.brasilprev.desafio.model.vo.ProdutoVO;

public interface ProdutoService {

    ProdutoVO salvar(ProdutoVO vo);

    Page<ProdutoVO> listar(Pageable pageable);

    ProdutoVO buscarPorId(Long id);

    void deletar(Long id);
    
}