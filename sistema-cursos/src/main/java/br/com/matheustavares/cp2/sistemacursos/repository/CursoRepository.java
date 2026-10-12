package br.com.matheustavares.cp2.sistemacursos.repository;

import br.com.matheustavares.cp2.sistemacursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
