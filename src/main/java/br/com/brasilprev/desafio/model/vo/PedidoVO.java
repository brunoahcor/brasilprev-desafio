package br.com.brasilprev.desafio.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
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
public class PedidoVO {

    @ApiModelProperty(hidden = true)
    private Long id;
    private ClienteVO cliente;
    private List<ItemPedidoVO> itensPedido;
    private Integer qtd;
    private BigDecimal valorTotal;
    @ApiModelProperty(hidden = true)
    private LocalDateTime dataCadastro;

    public PedidoVO(ClienteVO cliente) {
        this.cliente = cliente;
        this.dataCadastro = LocalDateTime.now();
    }

}