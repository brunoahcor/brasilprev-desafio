package br.com.brasilprev.desafio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.converter.ProdutoConverter;
import br.com.brasilprev.desafio.exception.BusinessException;
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
    public ProdutoVO salvar(ProdutoVO vo) throws Exception {

        if( !Optional.ofNullable( vo.getNome() ).isPresent() ){
            throw new BusinessException("NOME nao informado.",HttpStatus.BAD_REQUEST);
        }
        if( !Optional.ofNullable( vo.getValorUnidade() ).isPresent() ){
            throw new BusinessException("VALOR nao informado ou invalido.",HttpStatus.BAD_REQUEST);
        }
        vo.setDataCadastro( Optional.ofNullable(vo.getDataCadastro()).orElse(LocalDateTime.now()) );
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
    public ProdutoVO buscarPorId(Long id) throws Exception {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            return converter.toVO(produto.get());
        } else {
            throw new BusinessException("Produto nao encontrado com ID informado.",HttpStatus.NO_CONTENT);
        } 
    }

    @Override
    public ProdutoVO buscarPorNome(String nome) throws Exception {
        if( !Optional.ofNullable( nome ).isPresent() ){
            throw new BusinessException("NOME nao informado.",HttpStatus.BAD_REQUEST);
        }
        Optional<Produto> produto = produtoRepository.findByNome(nome.toUpperCase());
        if(produto.isPresent()){
            return converter.toVO(produto.get());
        } else {
            throw new BusinessException("Produto nao encontrado com NOME informado.",HttpStatus.NO_CONTENT);
        } 
    }

    @Override
    public void deletar(Long id) throws Exception {
        buscarPorId(id);
        produtoRepository.deleteById(id);
    }
    
}