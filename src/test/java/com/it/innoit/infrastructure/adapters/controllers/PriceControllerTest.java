package com.it.innoit.infrastructure.adapters.controllers;

import com.it.innoit.application.services.PriceService;
import com.it.innoit.domain.models.PriceAggregate;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
    public void testGetPriceAt10AMOnDay14() throws Exception {

        LocalDateTime dateTime = LocalDateTime.of(2020, 7, 14, 10, 0);
        Long brandId = 1L;
        Long productId = 35455L;

        // Mocking the service response
        PriceAggregate priceAggregate = new PriceAggregate(brandId, productId, dateTime,
                dateTime.plusHours(2), 1L, 1, 25.45, "EUR");
        when(priceService.getPVPForProductAndBrand(any(), any(), any())).thenReturn(Optional.of(priceAggregate));

        mockMvc.perform(get("/prices")
                .param("dateTime", "2023-07-14T10:00")
                .param("brandId", String.valueOf(brandId))
                .param("productId", String.valueOf(productId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value("1"))
                .andExpect(jsonPath("$.price").value("25.45"));
    }
}
