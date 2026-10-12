package br.com.rm566844.cp2.sistemacursos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultipleOfValidator implements ConstraintValidator<MultipleOf, Integer> {

    private int divisor;

    @Override
    public void initialize(MultipleOf annotation) {
        this.divisor = annotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null fica a cargo do @NotNull
        if (value == null) {
            return true;
        }
        return value % divisor == 0;
    }
}
