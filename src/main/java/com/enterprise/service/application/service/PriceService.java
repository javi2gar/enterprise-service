package com.enterprise.service.application.service;

import com.enterprise.service.domain.model.Price;
import com.enterprise.service.domain.port.in.read.FindPriceUseCase;
import com.enterprise.service.domain.port.out.read.PriceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService implements FindPriceUseCase {

    private final PriceFinder priceFinder; // PriceFinder is a port of the application layer of read type

    @Override
    public List<Price> findAllPricesByBrandIdAndProductIdAndRequestDate(short brandId, Long productId, LocalDateTime requestDate) {
        return priceFinder.findAllPricesByBrandIdAndProductIdAndRequestDate(brandId, productId, requestDate);
    }

}
