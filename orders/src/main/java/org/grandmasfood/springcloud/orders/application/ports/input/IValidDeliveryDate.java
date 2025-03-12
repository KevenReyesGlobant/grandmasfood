package org.grandmasfood.springcloud.orders.application.ports.input;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.grandmasfood.springcloud.orders.domain.validations.DeliveryDateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DeliveryDateValidator.class)
public @interface IValidDeliveryDate {
    String message() default "Invalid delivery date configuration";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}