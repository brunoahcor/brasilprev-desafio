package br.com.brasilprev.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.converter.ProdutoConverter;
import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.model.vo.ProdutoVO;
import br.com.brasilprev.desafio.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoConverter converter;

    @Override
    public ProdutoVO salvar(ProdutoVO vo){
        Produto produto = produtoRepository.save( converter.toEntity(vo) );
        return converter.toVO(produto);
    }

    @Override
    public Page<ProdutoVO> listar(Pageable pageable){
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        List<ProdutoVO> vos = converter.convertToListVO(produtos.getContent());
        return new PageImpl<ProdutoVO>(vos, pageable, produtos.getTotalElements());
    }

    @Override
    public ProdutoVO buscarPorId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return converter.toVO(produto.get());
    }

    @Override
    public void deletar(Long id){
        produtoRepository.deleteById(id);
    }
    
}