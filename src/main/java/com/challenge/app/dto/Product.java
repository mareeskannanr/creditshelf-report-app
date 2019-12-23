package com.challenge.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.challenge.app.utils.AppConstants.ProductDTO.*;

@Data
public class Product {

    @JsonProperty(PRODUCT_NAME)
    private String productName;

    @JsonProperty(QUANTITY)
    private Long quantity;

    @JsonProperty(SALE_PRICE)
    private String salePrice;

}
