package br.com.brasilprev.desafio.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.desafio.model.entity.ItemPedido;
import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.model.vo.ItemPedidoVO;
import br.com.brasilprev.desafio.model.vo.PedidoVO;

@Component
public class PedidoConverter {

    @Autowired
    private ModelMapper mm;

    public PedidoVO toVO(Pedido pedido) {
        return mm.map(pedido, PedidoVO.class);
    } 

    public Pedido toEntity(PedidoVO vo) {
        return mm.map(vo, Pedido.class);
    } 

    public List<PedidoVO> toListVO(List<Pedido> pedidos) {
        return pedidos.stream().map(this::toVO).collect(Collectors.toList());
    } 

    public List<Pedido> toListEntity(List<PedidoVO> vos) {
        return vos.stream().map(this::toEntity).collect(Collectors.toList());
    } 

    /**
     * Conversion method from ItemPedido to ItemPedidoVO
     * 
     * @param item
     * @return itemVo
     */
    public ItemPedidoVO toVO(ItemPedido item) {
        return mm.map(item, ItemPedidoVO.class);
    } 

    /**
     * Conversion method from ItemPedidoVO to ItemPedido
     * 
     * @param itemVo
     * @return item
     */
    public ItemPedido toEntity(ItemPedidoVO itemVo) {
        return mm.map(itemVo, ItemPedido.class);
    } 

    /**
     * Conversion method from List<ItemPedido> to List<ItemPedidoVO>
     * 
     * @param itens
     * @return itensVo
     */
    public List<ItemPedidoVO> toListItemPedidoVO(List<ItemPedido> itens) {
        return itens.stream().map(this::toVO).collect(Collectors.toList());
    } 

    /**
     * Conversion method from List<ItemPedidoVO> to List<ItemPedido>
     * 
     * @param itensVo
     * @return itens
     */
    public List<ItemPedido> toListItemPedidoEntity(List<ItemPedidoVO> itensVo) {
        return itensVo.stream().map(this::toEntity).collect(Collectors.toList());
    } 
    
}