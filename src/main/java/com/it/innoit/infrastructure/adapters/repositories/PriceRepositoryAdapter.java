package com.it.innoit.infrastructure.adapters.repositories;

import com.it.innoit.domain.models.PriceAggregate;
import com.it.innoit.domain.ports.PriceRepositoryPort;
import com.it.innoit.infrastructure.entities.PriceMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository){
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public List<PriceAggregate> findApplicablePricesFor(Long brandId, Long productId, LocalDateTime dateTime) {
        return jpaPriceRepository
                .findByBrandIdAndProductIdAndDateTime(brandId, productId, dateTime)
                .stream()
                .map(PriceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
