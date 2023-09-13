package br.com.appdahora.lanchonete.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    @NotNull
    @Size(min = 2)
    private String nome;
    @EqualsAndHashCode.Include
    @NotNull
    @Size(max = 3)
    @NotBlank
    private String sigla;

    public String toString(){
        return this.nome;
    }
    }

