package org.grandmasfood.springcloud.orders.domain.validations;

import jakarta.validation.ConstraintDefinitionException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.grandmasfood.springcloud.orders.application.ports.input.IValidDeliveryDate;
import org.grandmasfood.springcloud.orders.domain.model.Order;

public class DeliveryDateValidator implements ConstraintValidator<IValidDeliveryDate, Order> {


    @Override
    public void initialize(IValidDeliveryDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext context) {
        // Si delivered es false, deliveryDate debe ser null
        if (Boolean.FALSE.equals(order.isDelivered())) {
            if (order.getDeliveryDate() != null) {
                throw new ConstraintDefinitionException("If delivered is false, delivery date must be null");
            }
            return true;
        }

        // Si delivered es true, deliveryDate no puede ser null
        if (Boolean.TRUE.equals(order.isDelivered()) && order.getDeliveryDate() == null) {
            throw new ConstraintDefinitionException("If delivered is true, delivery date cannot be empty");
        }

        return true;
    }



}