package br.com.brasilprev.desafio.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private String nome;
    private BigDecimal valorUnidade;
    private LocalDateTime dataCadastro;

}