package br.com.appdahora.lanchonete.Model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Pessoa {
    @NotNull
    @Size(min = 3)
    protected String nome;
    @EqualsAndHashCode.Include
    @CPF
    @NotNull
    @NotBlank
    protected String cpf;
    @Column(length = 13)
    @NotBlank
    protected String telefone;
    @Email
    @NotNull
    protected String email;
    protected LocalDate dataNascimento;
}