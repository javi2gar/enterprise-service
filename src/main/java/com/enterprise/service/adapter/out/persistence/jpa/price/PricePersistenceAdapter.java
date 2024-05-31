package com.enterprise.service.adapter.out.persistence.jpa.price;

import com.enterprise.service.adapter.out.persistence.exceptions.BadRequestException;
import com.enterprise.service.adapter.out.persistence.exceptions.NotFoundException;
import com.enterprise.service.application.domain.Price;
import com.enterprise.service.application.port.out.read.PriceFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.enterprise.service.application.Constants.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceFinder {

    private final PriceRepository priceRepository;
    private static final PriceDBMapper priceMapper = Mappers.getMapper(PriceDBMapper.class);

    /**
     * Find all prices by brand id, product id and request date
     *
     * @param brandId     the brand id
     * @param productId   the product id
     * @param requestDate the request date
     * @return the list of prices
     */
    @Override
    @Transactional(readOnly = true)
    public List<Price> findAllPricesByBrandIdAndProductIdAndRequestDate(short brandId, Long productId, LocalDateTime requestDate) {

        Collection<PriceEntity> prices;

        if (Objects.nonNull(productId)) {
            prices = priceRepository.findAllByBrandIdAndProductIdAndDate(brandId, productId, requestDate);
        } else {
            log.error(ERROR_MSG_PRICE_BAD_REQUEST);
            throw new BadRequestException(ERROR_CODE_PRICE_BAD_REQUEST, ERROR_MSG_PRICE_BAD_REQUEST);
        }

        if (prices.isEmpty()) {
            log.info(ERROR_MSG_PRICE_NOT_FOUND);
            throw new NotFoundException(ERROR_CODE_PRICE_NOT_FOUND, ERROR_MSG_PRICE_NOT_FOUND);
        } else {
            return prices
                    .stream()
                    .map(priceMapper::mapToDomain)
                    .toList();

        }
    }
}
