package com.enterprise.service.infrastructure.controller.price;

import com.enterprise.service.domain.model.Price;
import com.enterprise.service.domain.port.in.read.FindPriceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.enterprise.service.infrastructure.utils.SwaggerConstants.*;

@RestController
@RequestMapping(path = "/api/v1/prices")
@Validated
@AllArgsConstructor
public class PriceController {

    private final FindPriceUseCase findPriceUseCase;

    @Operation(summary = "Get all prices according to brand and product on a date"
            , responses = {
            @ApiResponse(responseCode = "200"
                    , description = "All prices returned"
                    , content = {@Content(mediaType = "application/json"
                    , schema = @Schema(implementation = PriceResponse.class))})
            , @ApiResponse(responseCode = "400"
            , description = "Invalid fields"
            , content = @Content)
            , @ApiResponse(responseCode = "401"
            , description = "Unauthorized"
            , content = @Content)
            , @ApiResponse(responseCode = "403"
            , description = "Forbidden"
            , content = @Content)
            , @ApiResponse(responseCode = "404"
            , description = "Prices not found"
            , content = @Content)
            , @ApiResponse(responseCode = "500"
            , description = "Internal server error"
            , content = @Content)
    })
    @GetMapping
    public ResponseEntity<PriceResponse> getAllPricesByBrandIdAndProductIdAndRequestDate(
            @RequestHeader("brand-id")
            @Max(value = BRAND_ID_MAX_VALUE, message = BRAND_ERROR_MESSAGE)
            @Min(value = BRAND_ID_MIN_VALUE, message = BRAND_ERROR_MESSAGE)
            short brandId
            , @RequestParam
            @Max(value = PRODUCT_ID_MAX_VALUE, message = PRODUCT_ERROR_MESSAGE)
            @Min(value = PRODUCT_ID_MIN_VALUE, message = PRODUCT_ERROR_MESSAGE)
            Long productId
            , @RequestParam
            @Parameter(name = "requestDate", description = PRICE_DATE_FORMAT_DESCRIPTION + PRICE_DATE_FORMAT)
            @Schema(example = "2024-06-24T08:00:00.000")
            @DateTimeFormat(iso = DateTimeFormat.ISO.NONE, pattern = PRICE_DATE_FORMAT)
            LocalDateTime requestDate) {
        List<Price> prices = findPriceUseCase.findAllPricesByBrandIdAndProductIdAndRequestDate(brandId, productId, requestDate);
        return prices == null || prices.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity
                .ok()
                .body(PriceResponse.builder()
                        .prices(prices)
                        .build());
    }

}
