# Uso de IA generativa

Ferramenta usada: Claude (Claude Code, Anthropic).

## Prompt 1
[anexo: enunciado da CP2 - Java Advanced (CP22.pdf)]

faça isso

### Resposta
A IA leu o enunciado e gerou o projeto inteiro (não atuou como tutor), sem nenhum outro prompt:

- Criou o projeto Maven com Spring Boot 4.1.0, Java 21, group `br.com.matheustavares.cp2`,
  artifact `sistema-cursos` e as dependências pedidas (webmvc, data-jpa, validation, h2 +
  h2console, devtools, lombok). Ela montou o `pom.xml` e o Maven Wrapper à mão porque não
  conseguia acessar o start.spring.io. Também criou o `application.yml` do enunciado.
- `model/Curso` (`@OneToMany(mappedBy = "curso", fetch = LAZY, cascade = ALL, orphanRemoval = true)`,
  com os métodos auxiliares `adicionarModulo`/`removerModulo`) e `model/Modulo`
  (`@ManyToOne(fetch = LAZY, optional = false)` + `@JoinColumn(name = "curso_id")`).
- `repository/CursoRepository` e `repository/ModuloRepository` com
  `Page<Modulo> findByCursoId(Long cursoId, Pageable pageable)`.
- DTOs em record: `CursoRequest`, `CursoResponse`, `ModuloRequest`, `ModuloResponse` (com `cursoId`),
  `PageResponse<T>` (com `from(Page<T>)`), `ErrorResponse` e `ValidationErrorResponse`.
- `validation/@MultipleOf(value = 5)` + `MultipleOfValidator` (retorna válido para null e deixa
  esse caso para o `@NotNull`).
- `mapper/CursoMapper` e `mapper/ModuloMapper`: classes utilitárias com construtor privado e
  `toResponse` estático.
- `service/` com interfaces e implementações, injeção por construtor, `ResourceNotFoundException`
  quando não encontra. Para apagar um módulo, ele é removido da lista do curso e o
  `orphanRemoval` apaga do banco.
- `controller/CursoController` (CRUD de curso + `/api/cursos/{cursoId}/modulos` GET/POST, 201 + Location)
  e `controller/ModuloController` (GET/DELETE `/api/modulos/{id}`).
- `configurations/GlobalExceptionHandler` com os handlers de 404, 400 (lista de erros por campo)
  e o catch-all 500 com mensagem genérica.

Ela compilou o projeto (`./mvnw package`), subiu a aplicação e testou todos os endpoints com curl
(201/200/204/400/404/500, cascade ao apagar o curso e paginação), e todos responderam como
o enunciado pede.
