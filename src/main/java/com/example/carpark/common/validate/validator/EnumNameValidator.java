package com.example.carpark.common.validate.validator;

import com.example.carpark.common.validate.annotations.EnumName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumNameValidator implements ConstraintValidator<EnumName, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumName annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
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
