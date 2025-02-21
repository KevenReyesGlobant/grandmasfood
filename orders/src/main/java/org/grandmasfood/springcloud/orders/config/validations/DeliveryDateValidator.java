package org.grandmasfood.springcloud.orders.config.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.grandmasfood.springcloud.orders.config.interfaces.IValidDeliveryDate;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;

public class DeliveryDateValidator implements ConstraintValidator<IValidDeliveryDate, OrdersDTO>{

    @Override
    public void initialize(IValidDeliveryDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(OrdersDTO ordersDTO, ConstraintValidatorContext context) {
        if (ordersDTO.delivered() != null && ordersDTO.delivered()) {
            return ordersDTO.deliveryDate() != null && !ordersDTO.deliveryDate().isEmpty();
        } else {
            return ordersDTO.deliveryDate() == null || ordersDTO.deliveryDate().isEmpty();
        }
    }
}
