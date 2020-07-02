package br.com.brasilprev.desafio.model.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClienteVO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDateTime dataCadastro;
    
}