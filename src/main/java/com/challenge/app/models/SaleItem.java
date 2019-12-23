package com.challenge.app.models;

import lombok.Data;

import javax.persistence.*;

import java.math.BigDecimal;

import static com.challenge.app.utils.AppConstants.SaleItem.*;

@Entity
@Data
@Table(name = SALE_ITEMS)
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long productId;

    @ManyToOne
    private Product product;

    private String currency;

    private Long quantity;

    private BigDecimal salePrice;

    @ManyToOne
    @JoinColumn(name = SALE_ID, nullable = false)
    private Sale sale;

}
