package com.example.demo.dao.entity;

import com.example.demo.enums.ProductsStatusEnum;
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


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "apple_products_discounts")
public class AppleProductsDiscountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    private Integer discountPercentage;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long productId;

    @Enumerated(EnumType.STRING)
    private ProductsStatusEnum discountStatus;

}
