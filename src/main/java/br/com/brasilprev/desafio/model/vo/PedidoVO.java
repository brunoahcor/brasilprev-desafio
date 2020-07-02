package br.com.brasilprev.desafio.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PedidoVO {

    private Long id;
    private ClienteVO cliente;
    private List<ItemPedidoVO> itensPedido;
    private Integer qtd;
    private BigDecimal valorTotal;
    private LocalDateTime dataCadastro;
    
}