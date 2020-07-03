package br.com.brasilprev.desafio.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class ProdutoVO {

    private Long id;
    @NotBlank
    private String nome;
    private BigDecimal valorUnidade;
    @JsonIgnore
    private LocalDateTime dataCadastro;

    public ProdutoVO(String nome, BigDecimal valorUnidade) {
        this.nome = nome;
        this.valorUnidade = valorUnidade;
    }

}