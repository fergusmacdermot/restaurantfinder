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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Annotation that performs data validation on a msisdn. Checks for not null, length between 10-15
 * and numeric only, allowing + sign at front of number
 * @author MacDermotF
 *
 */
@NotBlank(message="Username is blank")
@Size(min=4,max=10, message="Username wrong size")
@Pattern(regexp = "^[a-zA-Z0-9_]*$", message="Username not valid")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ReportAsSingleViolation
public @interface ValidateUserName {
	
    String message() default "{notValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value();


}