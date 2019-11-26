package com.VA2.projetoescola.repository;

import com.VA2.projetoescola.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
