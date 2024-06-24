package com.enterprise.service.price;

import com.enterprise.service.application.service.PriceService;
import com.enterprise.service.domain.model.Price;
import com.enterprise.service.infrastructure.controller.price.PriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private final static String PRICES_ENDPOINT = "/api/v1/prices";

    Price price1 = new Price();
    Price price2 = new Price();
    Price price3 = new Price();
    Price price4 = new Price();

    @BeforeEach
    public void init() {

        short brandIdMock = 1;
        long productIdMock = 35455L;
        LocalDateTime requestDateMock = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        price1.setBrandId(brandIdMock);
        price1.setProductId(productIdMock);
        price1.setStartDate(String.valueOf(LocalDateTime.of(2020, 6, 14, 0, 0, 0)));
        price1.setEndDate(String.valueOf(LocalDateTime.of(2020, 12, 31, 23, 59, 59)));
        price1.setPvp(BigDecimal.valueOf(35.50));
        price1.setFeeName("Fee 1");

        price2.setBrandId(brandIdMock);
        price2.setProductId(productIdMock);
        price2.setStartDate(String.valueOf(LocalDateTime.of(2020, 6, 14, 15, 0, 0)));
        price2.setEndDate(String.valueOf(LocalDateTime.of(2020, 6, 14, 18, 30, 0)));
        price2.setPvp(BigDecimal.valueOf(25.45));
        price2.setFeeName("Fee 2");

        price3.setBrandId(brandIdMock);
        price3.setProductId(productIdMock);
        price3.setStartDate(String.valueOf(LocalDateTime.of(2020, 6, 15, 0, 0, 0)));
        price3.setEndDate(String.valueOf(LocalDateTime.of(2020, 6, 15, 11, 0, 0)));
        price3.setPvp(BigDecimal.valueOf(30.50));
        price3.setFeeName("Fee 3");

        price4.setBrandId(brandIdMock);
        price4.setProductId(productIdMock);
        price4.setStartDate(String.valueOf(LocalDateTime.of(2020, 6, 15, 0, 0, 0)));
        price4.setEndDate(String.valueOf(LocalDateTime.of(2020, 12, 31, 23, 59, 59)));
        price4.setPvp(BigDecimal.valueOf(38.95));
        price4.setFeeName("Fee 4");

        when(priceService.findAllPricesByBrandIdAndProductIdAndRequestDate(brandIdMock, productIdMock, requestDateMock))
                .thenReturn(List.of(price1, price2, price3, price4));

    }


    @ParameterizedTest(name = "Test {index}: request at {0} for product {2} for brand {1}")
    @CsvSource({
            "2020-06-14T10:00, 1, 35455",
            "2020-06-14T16:00, 1, 35455",
            "2020-06-14T21:00, 1, 35455",
            "2020-06-15T10:00, 1, 35455",
            "2020-06-16T21:00, 1, 35455"
    })
    @DisplayName("Should return a price for a given date, brand and product. And the result is ok.")
    void when_find_price_by_brand_id_and_product_id_and_request_date_between_start_date_and_end_date_result_is_ok(
            LocalDateTime requestDate, short brandId, long productId) throws Exception {

        when(priceService.findAllPricesByBrandIdAndProductIdAndRequestDate(brandId, productId, requestDate))
                .thenReturn(List.of(price1, price2, price3, price4));

        mockMvc.perform(get(PRICES_ENDPOINT)
                        .header("brand-id", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId))
                        .param("requestDate", String.valueOf(requestDate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prices[0].brandId").value(1))
                .andExpect(jsonPath("$.prices[0].productId").value(35455));

        verify(priceService, times(1)).findAllPricesByBrandIdAndProductIdAndRequestDate(brandId, productId, requestDate);
    }


    @ParameterizedTest(name = "Test {index}: request at {0} for product {2} for brand {1}")
    @CsvSource({
            "2020-06-14T10:00, 1, 35456",
            "2020-06-14T16:00, 2, 35455",
            "2019-06-14T21:00, 1, 35455",
            "2025-06-15T10:00, 1, 35455",
            "2020-06-17T00:00, 1, 35455"
    })
    @DisplayName("Should be not return any price for a given date, brand and product. And the result is not found.")
    void when_find_price_by_brand_id_and_product_id_and_request_date_between_start_date_and_end_date_result_is_not_found(
            LocalDateTime requestDate, short brandId, long productId) throws Exception {

        mockMvc.perform(get(PRICES_ENDPOINT)
                        .header("brand-id", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId))
                        .param("requestDate", String.valueOf(requestDate)))
                .andExpect(status().isNotFound());
    }

}

