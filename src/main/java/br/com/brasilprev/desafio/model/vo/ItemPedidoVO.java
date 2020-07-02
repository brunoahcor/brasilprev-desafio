package br.com.brasilprev.desafio.model.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemPedidoVO {
    
    private Long id;
    private Integer qtd;
    private BigDecimal valorTotal;
    private ProdutoVO produto;

}