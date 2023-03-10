package com.example.carpark.common.validate.validator;

import com.example.carpark.common.validate.annotations.IsEntityField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityFieldValidator implements ConstraintValidator<IsEntityField, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(IsEntityField annotation) {
        acceptedValues = Stream.of(annotation.entityClass().getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
