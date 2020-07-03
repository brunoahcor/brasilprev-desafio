package br.com.brasilprev.desafio.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

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
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_sequence")
    @SequenceGenerator(name="cliente_sequence", sequenceName="seq_cliente")
    private Long id;

    @NotNull
    private String nome;
    
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotNull
    private String email;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataCadastro = LocalDateTime.now();
    }
    
}