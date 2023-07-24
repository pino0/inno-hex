package com.it.innoit.infrastructure.config;

import com.it.innoit.application.services.DomainPriceService;
import com.it.innoit.domain.ports.PriceRepositoryPort;
import com.it.innoit.infrastructure.adapters.repositories.JpaPriceRepository;
import com.it.innoit.infrastructure.adapters.repositories.PriceRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public DomainPriceService priceService(PriceRepositoryPort priceRepositoryPort){

        return new DomainPriceService(priceRepositoryPort);
    }

    @Bean
    public PriceRepositoryPort priceRepositoryPort(JpaPriceRepository jpaPriceRepository){

        return new PriceRepositoryAdapter(jpaPriceRepository);
    }
}
