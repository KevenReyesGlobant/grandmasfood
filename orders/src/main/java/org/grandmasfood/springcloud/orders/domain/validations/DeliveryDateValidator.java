package org.grandmasfood.springcloud.orders.domain.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.grandmasfood.springcloud.orders.application.ports.input.IValidDeliveryDate;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrdersCreateRequestDTO;

public class DeliveryDateValidator implements ConstraintValidator<IValidDeliveryDate, OrdersCreateRequestDTO> {

    @Override
    public void initialize(IValidDeliveryDate constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(OrdersCreateRequestDTO order, ConstraintValidatorContext context) {
        if (order == null) {
            return true; // Consider null value as valid
        }

        if (!order.getDelivered() && order.getDeliveryDate() != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("If delivered is false, delivery date must be null")
                    .addPropertyNode("deliveryDate")
                    .addConstraintViolation();
            return false;
        }

        if (order.getDelivered() && order.getDeliveryDate() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("If delivered is true, delivery date cannot be empty")
                    .addPropertyNode("deliveryDate")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}