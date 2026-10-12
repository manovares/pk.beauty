package br.com.rm566844.cp2.sistemacursos.service;

import br.com.rm566844.cp2.sistemacursos.dto.ModuloRequest;
import br.com.rm566844.cp2.sistemacursos.dto.ModuloResponse;
import br.com.rm566844.cp2.sistemacursos.dto.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ModuloService {

    ModuloResponse create(Long cursoId, ModuloRequest request);

    PageResponse<ModuloResponse> findByCurso(Long cursoId, Pageable pageable);

    ModuloResponse findById(Long id);

    void delete(Long id);
}
