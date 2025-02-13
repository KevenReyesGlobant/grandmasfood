package repository;

import model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
