package br.com.brasilprev.desafio.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.model.vo.PedidoVO;

@Component
public class PedidoConverter {

    @Autowired
    private ModelMapper mm;

    public PedidoVO convertToVO(Pedido pedido) {
        return mm.map(pedido, PedidoVO.class);
    } 

    public Pedido convertToEntity(PedidoVO vo) {
        return mm.map(vo, Pedido.class);
    } 

    public List<PedidoVO> convertToListVO(List<Pedido> pedidos) {
        return pedidos.stream().map(pedido -> mm.map(pedido, PedidoVO.class)).collect(Collectors.toList());
    } 

    public List<Pedido> convertToListEntity(List<PedidoVO> vos) {
        return vos.stream().map(vo -> mm.map(vo, Pedido.class)).collect(Collectors.toList());
    } 
    
}