package com.challenge.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

import java.math.BigDecimal;

import static com.challenge.app.utils.AppConstants.Product.*;

@Data
@Entity
@Table(name = PRODUCTS)
@EqualsAndHashCode(exclude = "company")
@ToString(exclude = "company")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Column(unique = true)
    private String name;

    private BigDecimal purchasePrice;

    private String currency;

    @ManyToOne
    @JoinColumn(name = COMPANY_ID, nullable = false)
    private Company company;

}
