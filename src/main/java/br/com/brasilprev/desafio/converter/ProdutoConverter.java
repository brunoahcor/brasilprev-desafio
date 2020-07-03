package br.com.brasilprev.desafio.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.model.vo.ProdutoVO;

@Component
public class ProdutoConverter {

    @Autowired
    private ModelMapper mm;

    public ProdutoVO toVO(Produto produto) {
        return mm.map(produto, ProdutoVO.class);
    } 

    public Produto toEntity(ProdutoVO vo) {
        return mm.map(vo, Produto.class);
    } 

    public List<ProdutoVO> convertToListVO(List<Produto> produtos) {
        return produtos.stream().map(this::toVO).collect(Collectors.toList());
    } 

    public List<Produto> convertToListEntity(List<ProdutoVO> vos) {
        return vos.stream().map(this::toEntity).collect(Collectors.toList());
    } 
    
}