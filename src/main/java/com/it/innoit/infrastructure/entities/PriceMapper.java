package com.it.innoit.infrastructure.entities;

import com.it.innoit.domain.models.PriceAggregate;

public class PriceMapper {

    private PriceMapper() {
    }

    public static PriceAggregate toDomain(PriceEntity priceEntity) {
        return new PriceAggregate(
                priceEntity.getBrandId()
                , priceEntity.getProductId()
                , priceEntity.getStartDate()
                , priceEntity.getEndDate()
                , priceEntity.getPriceList()
                , priceEntity.getPriority()
                , priceEntity.getPrice()
                , priceEntity.getCurrency()
        );
    }

    public static PriceEntity toEntity(PriceAggregate priceAggregate) {
        return new PriceEntity(
                priceAggregate.getBrandId()
                , priceAggregate.getProductId()
                , priceAggregate.getStartDate()
                , priceAggregate.getEndDate()
                , priceAggregate.getPriceList()
                , priceAggregate.getPriority()
                , priceAggregate.getPrice()
                , priceAggregate.getCurrency());
    }
}
