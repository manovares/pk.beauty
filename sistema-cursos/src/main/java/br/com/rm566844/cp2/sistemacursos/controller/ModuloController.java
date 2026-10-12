package br.com.rm566844.cp2.sistemacursos.controller;

import br.com.rm566844.cp2.sistemacursos.dto.ModuloResponse;
import br.com.rm566844.cp2.sistemacursos.service.ModuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moduloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
