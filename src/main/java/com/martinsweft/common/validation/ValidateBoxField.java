package com.martinsweft.common.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@NotBlank(message="{notValid}")
@Size(min=1,max=64, message="Too long")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ReportAsSingleViolation
public @interface ValidateBoxField {
	
    String message() default "{notValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value();

}