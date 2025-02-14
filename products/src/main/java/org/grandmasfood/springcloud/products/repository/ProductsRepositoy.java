package org.grandmasfood.springcloud.products.repository;

import org.grandmasfood.springcloud.products.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepositoy extends JpaRepository<Products, Long> {

}
