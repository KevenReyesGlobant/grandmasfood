package org.grandmasfood.springcloud.orders.config.validations;

import jakarta.validation.ConstraintDefinitionException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.grandmasfood.springcloud.orders.config.interfaces.IValidDeliveryDate;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;

public class DeliveryDateValidator implements ConstraintValidator<IValidDeliveryDate, OrdersDTO> {

    @Override
    public void initialize(IValidDeliveryDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(OrdersDTO order, ConstraintValidatorContext context) {
        // Si delivered es false, deliveryDate debe ser null
        if (Boolean.FALSE.equals(order.delivered())) {
            if (order.deliveryDate() != null) {
                throw new ConstraintDefinitionException("If delivered is false, delivery date must be null");
            }
            return true;
        }

        // Si delivered es true, deliveryDate no puede ser null
        if (Boolean.TRUE.equals(order.delivered()) && order.deliveryDate() == null) {
            throw new ConstraintDefinitionException("If delivered is true, delivery date cannot be empty");
        }

        return true;
    }

}