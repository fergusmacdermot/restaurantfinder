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

/**
 * Annotation that performs data validation on a msisdn. Checks for not null, length between 10-15
 * and numeric only, allowing + sign at front of number
 * @author MacDermotF
 *
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {ExistingUsernameCheck.class})
@Documented
@ReportAsSingleViolation
public @interface ValidateExistingUserName {
	
    String message() default "Username exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value();


}