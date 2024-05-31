package com.enterprise.service.application.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Price {

    private Long productId;
    private short brandId;
    private String feeName;
    private String startDate;
    private String endDate;
    private BigDecimal pvp;

}
