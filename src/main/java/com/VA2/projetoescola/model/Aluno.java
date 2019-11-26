package com.VA2.projetoescola.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id;

    @NotEmpty(message = "Informe o nome do Aluno.")
    @NotNull(message = "Informe o nome do Aluno.")
    private String nome;

    @NotNull(message = "Informe a data de nascimento do aluno")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    private String sexo;

    @ManyToMany(mappedBy = "listaAluno", cascade = CascadeType.ALL)
    private Set<Turma> listaTurma;

}