package org.grandmasfood.springcloud.orders.config.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.grandmasfood.springcloud.orders.config.interfaces.IValidDeliveryDate;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;

public class DeliveryDateValidator implements ConstraintValidator<IValidDeliveryDate, OrdersDTO>{

    @Override
    public boolean isValid(OrdersDTO dto, ConstraintValidatorContext context) {
        if (Boolean.TRUE.equals(dto.delivered())) {
            return dto.deliveryDate() != null && !dto.deliveryDate().isEmpty();
        } else {
            return dto.deliveryDate() == null;
        }
    }
}
