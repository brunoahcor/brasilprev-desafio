package br.com.brasilprev.desafio.model.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class ClienteVO {

    @ApiModelProperty(hidden = true)
    private Long id;
    @NotBlank(message = "{nome.not.blank}")
    @ApiModelProperty(required = true)
    private String nome;
    @Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$",
        message = "{cpf.not.valid}"
    )
    @ApiModelProperty(required = true)
    private String cpf;
    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    @ApiModelProperty(required = true)
    private String email;
    @ApiModelProperty(hidden = true)
    private LocalDateTime dataCadastro;
    
}