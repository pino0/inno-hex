package com.it.innoit.application.usecases;

import com.it.innoit.domain.models.PriceAggregate;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RetrievePricePVP {

    Optional<PriceAggregate> getPVPForProductAndBrand(Long brandId, Long productId, LocalDateTime dateTime);
}
