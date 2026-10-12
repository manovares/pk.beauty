package br.com.matheustavares.cp2.sistemacursos.service;

import br.com.matheustavares.cp2.sistemacursos.dto.CursoRequest;
import br.com.matheustavares.cp2.sistemacursos.dto.CursoResponse;
import br.com.matheustavares.cp2.sistemacursos.dto.PageResponse;
import br.com.matheustavares.cp2.sistemacursos.exceptions.ResourceNotFoundException;
import br.com.matheustavares.cp2.sistemacursos.mapper.CursoMapper;
import br.com.matheustavares.cp2.sistemacursos.model.Curso;
import br.com.matheustavares.cp2.sistemacursos.repository.CursoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    @Transactional
    public CursoResponse create(CursoRequest request) {
        Curso curso = new Curso();
        curso.setTitulo(request.titulo());
        curso.setDescricao(request.descricao());
        return CursoMapper.toResponse(cursoRepository.save(curso));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<CursoResponse> findAll(Pageable pageable) {
        return PageResponse.from(cursoRepository.findAll(pageable).map(CursoMapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse findById(Long id) {
        return CursoMapper.toResponse(findEntityById(id));
    }

    @Override
    @Transactional
    public CursoResponse update(Long id, CursoRequest request) {
        Curso curso = findEntityById(id);
        curso.setTitulo(request.titulo());
        curso.setDescricao(request.descricao());
        return CursoMapper.toResponse(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // cascade = ALL + orphanRemoval em Curso.modulos apaga os módulos junto
        cursoRepository.delete(findEntityById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Curso findEntityById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id " + id));
    }
}
