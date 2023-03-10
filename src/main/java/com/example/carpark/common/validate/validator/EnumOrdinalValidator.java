package com.example.carpark.common.validate.validator;

import com.example.carpark.common.validate.annotations.EnumOrdinal;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumOrdinalValidator implements ConstraintValidator<EnumOrdinal, CharSequence> {
    private List<Integer> acceptedValues;

    @Override
    public void initialize(EnumOrdinal annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::ordinal)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(Integer.parseInt(value.toString()));
    }
}
