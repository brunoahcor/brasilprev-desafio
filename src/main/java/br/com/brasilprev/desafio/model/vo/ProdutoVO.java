package br.com.brasilprev.desafio.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

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
public class ProdutoVO {

    @ApiModelProperty(hidden = true)
    private Long id;
    @NotBlank
    @ApiModelProperty(required = true)
    private String nome;
    private BigDecimal valorUnidade;
    @ApiModelProperty(hidden = true)
    private LocalDateTime dataCadastro;

    public ProdutoVO(String nome, BigDecimal valorUnidade) {
        this.nome = nome;
        this.valorUnidade = valorUnidade;
    }

}