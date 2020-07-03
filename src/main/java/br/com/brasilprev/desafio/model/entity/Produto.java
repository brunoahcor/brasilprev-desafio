package br.com.brasilprev.desafio.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="produto_sequence")
    @SequenceGenerator(name="produto_sequence", sequenceName="seq_produto")
    private Long id;

    private String nome;

    @Column(name = "valor_unidade")
    private BigDecimal valorUnidade;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    public Produto(String nome, BigDecimal valorUnidade) {
        this.nome = nome;
        this.valorUnidade = valorUnidade;
        this.dataCadastro = LocalDateTime.now();
    }

}