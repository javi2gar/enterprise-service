package com.enterprise.service.price;

import com.enterprise.service.infrastructure.entity.brand.BrandEntity;
import com.enterprise.service.infrastructure.entity.price.PriceEntity;
import com.enterprise.service.infrastructure.entity.product.ProductEntity;
import com.enterprise.service.infrastructure.repository.price.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    short brandId = 1;
    long productId = 35455L;
    LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
    LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

    @BeforeEach
    public void init() {

        BrandEntity brand = new BrandEntity();
        brand.setId(brandId);
        brand.setName("Zara");

        ProductEntity product = new ProductEntity();
        product.setId(productId);
        product.setDescription("T-shirt");
        product.setPurchaseValue(BigDecimal.valueOf(20.00));

        PriceEntity price1 = new PriceEntity();
        price1.setBrandId(brandId);
        price1.setProductId(productId);
        price1.setStartDate(startDate);
        price1.setEndDate(endDate);
        price1.setPrice(BigDecimal.valueOf(35.50));
        price1.setBrand(brand);
        price1.setProduct(product);
    }

    @ParameterizedTest(name = "Test {index}: request at {0} for product {2} for brand {1}")
    @CsvSource({
            "2020-06-14T10:00, 1, 35455",
            "2020-06-14T16:00, 1, 35455",
            "2020-06-14T21:00, 1, 35455",
            "2020-06-15T10:00, 1, 35455",
            "2020-06-16T21:00, 1, 35455"
    })
    @DisplayName("Should return a price for a given date, brand and product.")
    void when_find_price_by_brand_id_and_product_id_and_request_date_between_start_date_and_end_date(LocalDateTime requestDate,
                                                                                                     short brandId,
                                                                                                     long productId) {
        final List<PriceEntity> prices = priceRepository.findAllByBrandIdAndProductIdAndDate(brandId, productId, requestDate);
        prices.forEach(price -> {
            Assertions.assertEquals(brandId, price.getBrandId());
            Assertions.assertEquals(productId, price.getProductId());
            Assertions.assertEquals(brandId, price.getBrand().getId());
            Assertions.assertEquals(productId, price.getProduct().getId());
            Assertions.assertTrue(price.getStartDate().isBefore(requestDate) || price.getStartDate().isEqual(requestDate));
            Assertions.assertTrue(price.getEndDate().isAfter(requestDate) || price.getEndDate().isEqual(requestDate));
        });
    }
}
