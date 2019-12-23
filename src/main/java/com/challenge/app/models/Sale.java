package com.challenge.app.models;

import lombok.Data;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.challenge.app.utils.AppConstants.Sale.*;

@Data
@Entity
@Table(name = SALES)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String companyName;

    @ManyToOne
    private Company company;

    private Long orderNumber;

    private LocalDate orderDate;

    @OneToMany(mappedBy = SALE, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems = new ArrayList<>();

}
