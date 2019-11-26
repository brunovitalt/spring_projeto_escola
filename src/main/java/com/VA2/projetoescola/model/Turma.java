package com.VA2.projetoescola.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Long id;

    private String nome;

    private String local;

    private String horario;

    private String professor;

    @ManyToMany
    @JoinTable(
            name = "turma_aluno",
            joinColumns = @JoinColumn(name = "id_turma"),
            inverseJoinColumns = @JoinColumn(name = "id_aluno")
    )
    private Set<Aluno> listaAluno;


}
