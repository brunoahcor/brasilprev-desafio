package br.com.brasilprev.desafio.model.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ItemPedidoVO {
    
    private Long id;
    private Integer qtd;
    private BigDecimal valorTotal;
    private ProdutoVO produto;

    public ItemPedidoVO(Integer qtd, ProdutoVO produto){
        this.qtd = qtd;
        this.valorTotal = produto.getValorUnidade().multiply( new BigDecimal(qtd) );
        this.produto = produto;
    }

}