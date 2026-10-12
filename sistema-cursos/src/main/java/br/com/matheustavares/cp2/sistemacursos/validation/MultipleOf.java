package br.com.matheustavares.cp2.sistemacursos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MultipleOfValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleOf {

    int value();

    String message() default "deve ser múltiplo de {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
