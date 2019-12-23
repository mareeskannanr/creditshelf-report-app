package com.challenge.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.challenge.app.utils.AppConstants.Company.*;

@Data
@Entity
@Table(name = COMPANIES)
@EqualsAndHashCode(exclude = "products")
@ToString(exclude = "products")
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = COMPANY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

}