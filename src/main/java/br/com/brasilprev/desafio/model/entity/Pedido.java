package br.com.brasilprev.desafio.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pedido_sequence")
    @SequenceGenerator(name="pedido_sequence", sequenceName="seq_pedido")
    private Long id;

    @ManyToOne
    @NotNull
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;

    private Integer qtd;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    public Pedido(Cliente cliente){
        this.cliente = cliente;
        this.dataCadastro = LocalDateTime.now();
    }
    
}