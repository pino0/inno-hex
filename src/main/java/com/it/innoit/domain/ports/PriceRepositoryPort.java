package com.it.innoit.domain.ports;

import com.it.innoit.domain.models.PriceAggregate;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {

    List<PriceAggregate> findApplicablePricesFor(Long brandId, Long productId, LocalDateTime dateTime);
}
