package br.com.matheustavares.cp2.sistemacursos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    // Lado inverso. LAZY: listar cursos não precisa carregar os módulos.
    // cascade ALL: apagar o curso apaga os módulos junto.
    // orphanRemoval: tirar um módulo da lista apaga ele do banco.
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Modulo> modulos = new ArrayList<>();

    public void adicionarModulo(Modulo modulo) {
        modulos.add(modulo);
        modulo.setCurso(this);
    }

    public void removerModulo(Modulo modulo) {
        modulos.remove(modulo);
        modulo.setCurso(null);
    }
}
