package com.challenge.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.challenge.app.utils.AppConstants.SalesReport.*;

@Data
public class SalesReport {

    @JsonProperty(ORDER_NUMBER)
    private String orderNumber;

    @JsonProperty(ORDER_DATE)
    private String orderDate;

    @JsonProperty(TOTAL)
    private String total;

    @JsonIgnore
    private BigDecimal totalAmount;

    @JsonProperty(PRODUCTS)
    private List<Product> products = new ArrayList<>();

}
