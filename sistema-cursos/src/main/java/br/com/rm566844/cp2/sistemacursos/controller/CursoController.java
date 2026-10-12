package br.com.rm566844.cp2.sistemacursos.controller;

import br.com.rm566844.cp2.sistemacursos.dto.*;
import br.com.rm566844.cp2.sistemacursos.service.CursoService;
import br.com.rm566844.cp2.sistemacursos.service.ModuloService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final ModuloService moduloService;

    public CursoController(CursoService cursoService, ModuloService moduloService) {
        this.cursoService = cursoService;
        this.moduloService = moduloService;
    }

    @PostMapping
    public ResponseEntity<CursoResponse> create(@Valid @RequestBody CursoRequest request) {
        CursoResponse response = cursoService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<CursoResponse>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(cursoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> update(@PathVariable Long id, @Valid @RequestBody CursoRequest request) {
        return ResponseEntity.ok(cursoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cursoId}/modulos")
    public ResponseEntity<PageResponse<ModuloResponse>> findModulos(@PathVariable Long cursoId,
                                                                     @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(moduloService.findByCurso(cursoId, pageable));
    }

    @PostMapping("/{cursoId}/modulos")
    public ResponseEntity<ModuloResponse> createModulo(@PathVariable Long cursoId,
                                                       @Valid @RequestBody ModuloRequest request) {
        ModuloResponse response = moduloService.create(cursoId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/modulos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }
}
