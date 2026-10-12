package br.com.rm566844.cp2.sistemacursos.service;

import br.com.rm566844.cp2.sistemacursos.dto.ModuloRequest;
import br.com.rm566844.cp2.sistemacursos.dto.ModuloResponse;
import br.com.rm566844.cp2.sistemacursos.dto.PageResponse;
import br.com.rm566844.cp2.sistemacursos.exceptions.ResourceNotFoundException;
import br.com.rm566844.cp2.sistemacursos.mapper.ModuloMapper;
import br.com.rm566844.cp2.sistemacursos.model.Curso;
import br.com.rm566844.cp2.sistemacursos.model.Modulo;
import br.com.rm566844.cp2.sistemacursos.repository.CursoRepository;
import br.com.rm566844.cp2.sistemacursos.repository.ModuloRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository moduloRepository;
    private final CursoRepository cursoRepository;
    private final CursoService cursoService;

    public ModuloServiceImpl(ModuloRepository moduloRepository, CursoRepository cursoRepository,
                             CursoService cursoService) {
        this.moduloRepository = moduloRepository;
        this.cursoRepository = cursoRepository;
        this.cursoService = cursoService;
    }

    @Override
    @Transactional
    public ModuloResponse create(Long cursoId, ModuloRequest request) {
        Curso curso = cursoService.findEntityById(cursoId);
        Modulo modulo = new Modulo();
        modulo.setTitulo(request.titulo());
        modulo.setOrdem(request.ordem());
        modulo.setCargaHoraria(request.cargaHoraria());
        curso.adicionarModulo(modulo);
        return ModuloMapper.toResponse(moduloRepository.save(modulo));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ModuloResponse> findByCurso(Long cursoId, Pageable pageable) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new ResourceNotFoundException("Curso não encontrado com id " + cursoId);
        }
        return PageResponse.from(moduloRepository.findByCursoId(cursoId, pageable).map(ModuloMapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public ModuloResponse findById(Long id) {
        return ModuloMapper.toResponse(findEntityById(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Modulo modulo = findEntityById(id);
        // remove da lista do curso: orphanRemoval apaga do banco
        modulo.getCurso().removerModulo(modulo);
    }

    private Modulo findEntityById(Long id) {
        return moduloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Módulo não encontrado com id " + id));
    }
}
