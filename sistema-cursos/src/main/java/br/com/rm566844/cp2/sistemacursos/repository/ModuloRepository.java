package br.com.rm566844.cp2.sistemacursos.repository;

import br.com.rm566844.cp2.sistemacursos.model.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    Page<Modulo> findByCursoId(Long cursoId, Pageable pageable);
}
