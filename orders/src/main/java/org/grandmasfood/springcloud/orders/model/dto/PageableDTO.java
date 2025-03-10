package org.grandmasfood.springcloud.orders.model.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageableDTO<T>(List<T> content) {
    public static <T> PageableDTO<T> fromPage(Page<T> page) {
        return new PageableDTO<>(page.getContent());

    }
}