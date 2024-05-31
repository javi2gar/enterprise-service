package com.enterprise.service.price;

import com.enterprise.service.adapter.out.persistence.jpa.price.PriceEntity;
import com.enterprise.service.adapter.out.persistence.jpa.price.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
class PricesPersistenceAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    private final PriceEntity price1 = new PriceEntity();
    private final PriceEntity price2 = new PriceEntity();
    private final PriceEntity price3 = new PriceEntity();
    private final PriceEntity price4 = new PriceEntity();

    @BeforeEach
    void init() {

        short brandId = 1;
        long productId = 35455L;

        price1.setBrandId(brandId);
        price1.setProductId(productId);
        price1.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        price1.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        price2.setBrandId(brandId);
        price2.setProductId(productId);
        price2.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        price2.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0));

        price3.setBrandId(brandId);
        price3.setProductId(productId);
        price3.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        price3.setEndDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0));

        price4.setBrandId(brandId);
        price4.setProductId(productId);
        price4.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        price4.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
    }


    @ParameterizedTest(name = "Test {index}: request at {0} for product {2} for brand {1}")
    @CsvSource({
            "2020-06-14T10:00, 1, 35455",
            "2020-06-14T16:00, 1, 35455",
            "2020-06-14T21:00, 1, 35455",
            "2020-06-15T10:00, 1, 35455",
    })
    @DisplayName("Should return a price for a given date, brand and product.")
    void when_find_price_by_brand_id_and_product_id_and_request_date_between_start_date_and_end_date_result_is_ok(
            String requestDate, short brandId, long productId) {

        List<PriceEntity> prices = List.of(price1, price2, price3, price4);

        Mockito.when(priceRepository
                .findAllByBrandIdAndProductIdAndDate(brandId, productId, LocalDateTime.parse(requestDate))).thenReturn(prices);

        List<PriceEntity> result = priceRepository
                .findAllByBrandIdAndProductIdAndDate(brandId, productId, LocalDateTime.parse(requestDate));

        Assertions.assertEquals(4, result.size());
    }
}
