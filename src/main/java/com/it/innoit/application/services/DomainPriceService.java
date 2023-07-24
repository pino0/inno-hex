package com.it.innoit.application.services;

import com.it.innoit.domain.models.PriceAggregate;
import com.it.innoit.domain.ports.PriceRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DomainPriceService implements PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

   public DomainPriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Optional<PriceAggregate> getPVPForProductAndBrand(Long brandId, Long productId, LocalDateTime dateTime) {
        List<PriceAggregate> applicablePrices = priceRepositoryPort.findApplicablePricesFor(brandId, productId, dateTime);

        if (applicablePrices.isEmpty()) {
            return Optional.empty();
        }

        applicablePrices
                .sort(Comparator
                        .comparingInt(PriceAggregate::getPriority)
                        .reversed()
                );

        return Optional.of(applicablePrices.get(0));
    }
}
