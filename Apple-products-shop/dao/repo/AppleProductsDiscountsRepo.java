package com.example.demo.dao.repo;


import com.example.demo.dao.entity.AppleProductsDiscountsEntity;
import com.example.demo.enums.ProductsStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppleProductsDiscountsRepo extends JpaRepository<AppleProductsDiscountsEntity, Long> {
    List<AppleProductsDiscountsEntity> findAll();

    List<AppleProductsDiscountsEntity> findByDiscountStatus(ProductsStatusEnum statusEnum);

    Optional<AppleProductsDiscountsEntity> findByProductIdAndDiscountStatus(Long id, ProductsStatusEnum statusEnum);

    AppleProductsDiscountsEntity findByProductId(Long id);

}
