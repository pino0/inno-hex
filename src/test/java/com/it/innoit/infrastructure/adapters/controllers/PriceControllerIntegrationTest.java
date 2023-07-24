package com.it.innoit.infrastructure.adapters.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.it.innoit.domain.models.PriceAggregate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    public void test1_GetPriceAt10AMOnDay14() throws Exception {
        String url = "/prices?dateTime=2020-06-14T10:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        PriceAggregate priceResponse = mapper.readValue(response.getBody(), PriceAggregate.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(priceResponse.getBrandId()).isEqualTo(1L);
        assertThat(priceResponse.getProductId()).isEqualTo(35455);
        assertThat(priceResponse.getPrice()).isEqualTo(35.50);
        assertThat(priceResponse.getPriceList()).isEqualTo(1);
    }

    @Test
    public void test2_GetPriceAt04PMOnDay14() throws Exception {
        String url = "/prices?dateTime=2020-06-14T16:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        PriceAggregate priceResponse = mapper.readValue(response.getBody(), PriceAggregate.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(priceResponse.getBrandId()).isEqualTo(1L);
        assertThat(priceResponse.getProductId()).isEqualTo(35455);
        assertThat(priceResponse.getPrice()).isEqualTo(25.45);
        assertThat(priceResponse.getPriceList()).isEqualTo(2);
    }

    @Test
    public void test3_GetPriceAt09PMOnDay14() throws Exception {
        String url = "/prices?dateTime=2020-06-14T21:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        PriceAggregate priceResponse = mapper.readValue(response.getBody(), PriceAggregate.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(priceResponse.getBrandId()).isEqualTo(1L);
        assertThat(priceResponse.getProductId()).isEqualTo(35455);
        assertThat(priceResponse.getPrice()).isEqualTo(35.50);
        assertThat(priceResponse.getPriceList()).isEqualTo(1);
    }

    @Test
    public void test4_GetPriceAt10AMOnDay15() throws Exception {
        String url = "/prices?dateTime=2020-06-15T10:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        PriceAggregate priceResponse = mapper.readValue(response.getBody(), PriceAggregate.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(priceResponse.getBrandId()).isEqualTo(1L);
        assertThat(priceResponse.getProductId()).isEqualTo(35455);
        assertThat(priceResponse.getPrice()).isEqualTo(30.50);
        assertThat(priceResponse.getPriceList()).isEqualTo(3);
    }

    @Test
    public void test5_GetPriceAt09PMOnDay16() throws Exception {
        String url = "/prices?dateTime=2020-06-16T21:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        PriceAggregate priceResponse = mapper.readValue(response.getBody(), PriceAggregate.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(priceResponse.getBrandId()).isEqualTo(1L);
        assertThat(priceResponse.getProductId()).isEqualTo(35455);
        assertThat(priceResponse.getPrice()).isEqualTo(38.95);
        assertThat(priceResponse.getPriceList()).isEqualTo(4);
    }

    @Test
    public void testConcurrentRequests() throws Exception {
        String url = "/prices?dateTime=2020-06-16T21:00:00&productId=35455&brandId=1";
        int numberOfThreads = 10;
        int numberOfRequestsPerThread = 100;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < numberOfRequestsPerThread; j++) {
                        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                        PriceAggregate priceResponse = mapper.readValue(response.getBody(), PriceAggregate.class);
                        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }


}
