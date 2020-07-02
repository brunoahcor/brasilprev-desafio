package br.com.brasilprev.desafio.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_pedido_sequence")
    @SequenceGenerator(name="item_pedido_sequence", sequenceName="seq_item_pedido")
    private Long id;

    private Integer qtd;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @ManyToOne
    @NotNull
    private Produto produto;

    @ManyToOne
    @NotNull
    private Pedido pedido;

    public ItemPedido(Integer qtd, Produto produto, Pedido pedido) {
        this.qtd = qtd;
        this.valorTotal = produto.getValorUnidade().multiply( new BigDecimal(qtd) );
        this.produto = produto;
        this.pedido = pedido;
    }
    
}