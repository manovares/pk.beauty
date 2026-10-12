package br.com.rm566844.cp2.sistemacursos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "modulos")
@Getter
@Setter
@NoArgsConstructor
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lado dono da relação (tem a FK curso_id). LAZY para não trazer o curso
    // inteiro a cada módulo; o response só precisa do id, que o proxy já tem.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false)
    private Integer ordem;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;
}
