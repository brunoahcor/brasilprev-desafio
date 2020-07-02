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

    public ProdutoVO convertToVO(Produto produto) {
        return mm.map(produto, ProdutoVO.class);
    } 

    public Produto convertToEntity(ProdutoVO vo) {
        return mm.map(vo, Produto.class);
    } 

    public List<ProdutoVO> convertToListVO(List<Produto> produtos) {
        return produtos.stream().map(produto -> mm.map(produto, ProdutoVO.class)).collect(Collectors.toList());
    } 

    public List<Produto> convertToListEntity(List<ProdutoVO> vos) {
        return vos.stream().map(vo -> mm.map(vo, Produto.class)).collect(Collectors.toList());
    } 
    
}