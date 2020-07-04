package br.com.brasilprev.desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.brasilprev.desafio.model.vo.ProdutoVO;

public interface ProdutoService {

    ProdutoVO salvar(ProdutoVO vo) throws Exception;

    Page<ProdutoVO> listar(Pageable pageable);

    ProdutoVO buscarPorId(Long id) throws Exception;

    ProdutoVO buscarPorNome(String nome) throws Exception;

    void deletar(Long id) throws Exception;
    
}