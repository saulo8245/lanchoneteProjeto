package br.com.appdahora.lanchonete.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    @Column(length = 50, nullable = false)
    private String nome;
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String sigla;

    public String toString(){
        return this.nome;
    }
    }

