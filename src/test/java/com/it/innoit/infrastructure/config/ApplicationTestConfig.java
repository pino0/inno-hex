package com.it.innoit.infrastructure.config;

import com.it.innoit.domain.ports.PriceRepositoryPort;
import com.it.innoit.infrastructure.adapters.repositories.JpaPriceRepository;
import com.it.innoit.infrastructure.adapters.repositories.PriceRepositoryAdapter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.it.innoit.infrastructure.adapters.repositories")
@EntityScan(basePackages = "com.it.innoit.infrastructure.entities")
public class ApplicationTestConfig {

}