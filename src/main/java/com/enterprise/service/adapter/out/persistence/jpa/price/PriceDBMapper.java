package com.enterprise.service.adapter.out.persistence.jpa.price;

import com.enterprise.service.application.domain.Price;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper
public interface PriceDBMapper {
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "feeName", ignore = true)
    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "pvp", source = "price")
    Price mapToDomain(PriceEntity priceEntity);

    @AfterMapping
     default void trimTargetStringFields(@MappingTarget Price target, PriceEntity source) {

        if (Objects.nonNull(source.getFee().getName())) {
            target.setFeeName(source.getFee().getName().strip());
        } else {
            target.setFeeName("");
        }
    }

}
