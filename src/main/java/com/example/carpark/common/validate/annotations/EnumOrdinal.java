package com.example.carpark.common.validate.annotations;

import com.example.carpark.common.validate.validator.EnumNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumNameValidator.class)
public @interface EnumOrdinal {
    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of enum {enumClass} ordinal";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}