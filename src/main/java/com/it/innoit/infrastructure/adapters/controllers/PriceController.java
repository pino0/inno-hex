package com.it.innoit.infrastructure.adapters.controllers;

import com.it.innoit.application.services.PriceService;
import com.it.innoit.domain.exceptions.InvalidRequestException;
import com.it.innoit.domain.exceptions.PriceNotFoundException;
import com.it.innoit.domain.models.PriceAggregate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<?> getPVPForProductAndBrand(
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
            @RequestParam(required = true) Long productId,
            @RequestParam(required = true) Long brandId) {

        Optional<PriceAggregate> price = priceService.getPVPForProductAndBrand(brandId, productId, dateTime);
        if(price.orElse(null) == null){
            throw new PriceNotFoundException("Price not found.");
        }

        return ResponseEntity.ok(price.get());
    }
}
