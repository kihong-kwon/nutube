package com.kkhstudy.nutube.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = AccountEmailExistingValidator.class)
public @interface AccountEmailExisting {
    String message() default "{AccountEmailExisting}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
