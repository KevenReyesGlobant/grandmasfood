package org.grandmasfood.springcloud.orders.config.interfaces;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.grandmasfood.springcloud.orders.config.validations.DeliveryDateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy= DeliveryDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IValidDeliveryDate {
    String message() default "Delivery date is required and cannot be blank when order is marked as delivered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}