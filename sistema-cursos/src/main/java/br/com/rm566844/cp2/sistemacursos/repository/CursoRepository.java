package br.com.rm566844.cp2.sistemacursos.repository;

import br.com.rm566844.cp2.sistemacursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
