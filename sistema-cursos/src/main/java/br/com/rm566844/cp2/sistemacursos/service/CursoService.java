package br.com.rm566844.cp2.sistemacursos.service;

import br.com.rm566844.cp2.sistemacursos.dto.CursoRequest;
import br.com.rm566844.cp2.sistemacursos.dto.CursoResponse;
import br.com.rm566844.cp2.sistemacursos.dto.PageResponse;
import br.com.rm566844.cp2.sistemacursos.model.Curso;
import org.springframework.data.domain.Pageable;

public interface CursoService {

    CursoResponse create(CursoRequest request);

    PageResponse<CursoResponse> findAll(Pageable pageable);

    CursoResponse findById(Long id);

    CursoResponse update(Long id, CursoRequest request);

    void delete(Long id);

    Curso findEntityById(Long id);
}
