package org.grandmasfood.springcloud.products.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.products.infrastructure.adapters.output.entities.ProductsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ProductsRepositoryIntegrationTest {

    @Autowired
    private ProductsRepositoy productsRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Long activeProductId;
    private UUID activeProductUuid;
    private Long inactiveProductId;

    @BeforeEach
    public void createInsertData() {
        ProductsEntity activeProduct = new ProductsEntity();
        activeProduct.setFantasyName("Grandma's Apple Pie");
        activeProduct.setDescription("Delicious homemade apple pie");
        activeProduct.setPrice(15.99F);
        activeProduct.setUuid(UUID.randomUUID());
        activeProduct.setActive(true);
        activeProduct = entityManager.persistAndFlush(activeProduct);
        activeProductId = activeProduct.getIdProduct();
        activeProductUuid = activeProduct.getUuid();

        ProductsEntity inactiveProduct = new ProductsEntity();
        inactiveProduct.setFantasyName("Old Recipe Cake");
        inactiveProduct.setDescription("Classic family cake recipe");
        inactiveProduct.setPrice(12.50F);
        inactiveProduct.setUuid(UUID.randomUUID());
        inactiveProduct.setActive(false);
        inactiveProduct = entityManager.persistAndFlush(inactiveProduct);
        inactiveProductId = inactiveProduct.getIdProduct();
    }

    @Test
    public void testFindProductsActiveByIdWhenProductExists() {
        ProductsEntity foundProduct = productsRepository.findProductsActiveById(activeProductId);

        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getIdProduct()).isEqualTo(activeProductId);
        assertThat(foundProduct.getActive()).isTrue();
    }

    @Test
    public void testFindProductsActiveByIdWhenProductNotActive() {
        ProductsEntity foundProduct = productsRepository.findProductsActiveById(inactiveProductId);

        assertThat(foundProduct).isNull();
    }

    @Test
    public void testFindProductsActiveByUuIdWhenProductExists() {
        ProductsEntity foundProduct = productsRepository.findProductsActiveByUuId(activeProductUuid);

        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getUuid()).isEqualTo(activeProductUuid);
        assertThat(foundProduct.getActive()).isTrue();
    }

    @Test
    public void testFindProductsActiveByUuIdWhenProductNotActive() {
        ProductsEntity inactiveProduct = entityManager.find(ProductsEntity.class, inactiveProductId);
        ProductsEntity foundProduct = productsRepository.findProductsActiveByUuId(inactiveProduct.getUuid());

        assertThat(foundProduct).isNull();
    }

    @Test
    public void testFindByFantasyNameContainingAndActiveTrue() {
        // Add additional active products for testing
        ProductsEntity anotherActiveProduct = new ProductsEntity();
        anotherActiveProduct.setFantasyName("Grandma's Chocolate Cake");
        anotherActiveProduct.setDescription("Rich chocolate cake");
        anotherActiveProduct.setPrice(16.99F);
        anotherActiveProduct.setUuid(UUID.randomUUID());
        anotherActiveProduct.setActive(true);
        entityManager.persistAndFlush(anotherActiveProduct);

        List<ProductsEntity> foundProducts = productsRepository.findByFantasyNameContainingAndActiveTrue("Grandma's");

        assertThat(foundProducts).hasSize(2);
        assertThat(foundProducts).extracting(ProductsEntity::getFantasyName)
                .containsExactlyInAnyOrder("Grandma's Apple Pie", "Grandma's Chocolate Cake");
        assertThat(foundProducts).allMatch(ProductsEntity::getActive);
    }

    @Test
    public void testFindByFantasyNameContainingWhenNoMatchingActiveProducts() {
        List<ProductsEntity> foundProducts = productsRepository.findByFantasyNameContainingAndActiveTrue("Pizza");

        assertThat(foundProducts).isEmpty();
    }
}