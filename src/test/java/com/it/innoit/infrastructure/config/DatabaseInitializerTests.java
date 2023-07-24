package com.it.innoit.infrastructure.config;

import com.it.innoit.infrastructure.adapters.repositories.JpaPriceRepository;
import com.it.innoit.infrastructure.entities.PriceEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DatabaseInitializerTests implements CommandLineRunner {

    private final JpaPriceRepository jpaPriceRepository;

    public DatabaseInitializerTests(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public void run(String... args) {
        initializeDatabaseWithTestData();
    }

    private void initializeDatabaseWithTestData() {
        // Insert example data into the H2 database
        PriceEntity price1 = new PriceEntity(1L, 35455L, LocalDateTime.of(2020, 06, 14, 00, 00, 00),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L, 0, 35.50, "EUR");

        PriceEntity price2 = new PriceEntity(1L, 35455L, LocalDateTime.of(2020, 06, 14, 15, 00, 00),
                LocalDateTime.of(2020, 06, 14, 18, 30, 00), 2L, 1, 25.45, "EUR");

        PriceEntity price3 = new PriceEntity(1L, 35455L, LocalDateTime.of(2020, 06, 15, 00, 00, 00),
                LocalDateTime.of(2020, 06, 15, 11, 00, 00), 3L, 1, 30.50, "EUR");

        PriceEntity price4 = new PriceEntity(1L, 35455L, LocalDateTime.of(2020, 06, 15, 16, 00, 00),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4L, 1, 38.95, "EUR");

        jpaPriceRepository.saveAll(Arrays.asList(price1, price2, price3, price4));
    }
}
