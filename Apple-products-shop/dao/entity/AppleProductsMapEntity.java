package com.example.demo.dao.entity;


import com.example.demo.enums.ProductsModelsEnum;
import com.example.demo.enums.ProductsNameEnum;
import com.example.demo.enums.ProductsTypesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "apple_products_map")
public class AppleProductsMapEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductsNameEnum productName;

    @Enumerated(EnumType.STRING)
    private ProductsTypesEnum productType;

    @Enumerated(EnumType.STRING)
    private ProductsModelsEnum productModel;

    private LocalDate dateOfIssue;

    private LocalDateTime creationDate;

    private LocalDateTime updatedDate;
}
