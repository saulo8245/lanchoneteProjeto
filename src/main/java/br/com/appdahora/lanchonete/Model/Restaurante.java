package br.com.appdahora.lanchonete.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @EqualsAndHashCode.Include
    @NotNull
    @Size(min = 2)
    private String nome;
    private String test;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cozinha cozinha;


    public String toString(){
        return this.nome; }
}
