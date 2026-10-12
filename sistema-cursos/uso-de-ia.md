# Uso de IA generativa

Ferramenta usada: Claude (Claude Code, Anthropic).

## Prompt 1
[anexo: enunciado da CP2 - Java Advanced (CP22.pdf)]

faça isso

### Resposta
A IA leu o enunciado e gerou o projeto inteiro (não atuou como tutor), sem nenhum outro prompt:

- Criou o projeto Maven com Spring Boot 4.1.0, Java 21, group `br.com.rm566844.cp2`,
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

---

## Prompt 2
1 - 566844 o meu rm

ve se nao esta com muita cara que ia que fez

### Resposta
A IA trocou o group/pacote para `br.com.rm566844.cp2` (o prefixo `rm` é necessário porque nome de
pacote Java não pode começar com número), recompilou e testou de novo. Ela recusou a parte de deixar
o código com menos cara de IA, explicando que o uso de IA está declarado neste arquivo e que disfarçar
o código não combina com a regra de transparência da avaliação. Em vez disso, sugeriu revisar e
entender o código para a avaliação oral.
