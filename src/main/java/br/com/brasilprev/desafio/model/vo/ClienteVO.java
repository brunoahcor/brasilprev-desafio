package br.com.brasilprev.desafio.model.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class ClienteVO {

    private Long id;
    @NotBlank(message = "{nome.not.blank}")
    private String nome;
    @Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$",
        message = "{cpf.not.valid}"
    )
    private String cpf;
    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    private String email;
    @JsonIgnore
    private LocalDateTime dataCadastro;
    
}