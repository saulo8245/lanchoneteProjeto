package br.com.appdahora.lanchonete.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @EqualsAndHashCode.Include
    @NotNull
    @Size(min = 2)
    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;


    public String toString(){
        return this.nome;
    }
}
