package com.example.demo.dao.repo;


import com.example.demo.dao.entity.AppleProductsEntity;
import com.example.demo.enums.ProductsStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppleProductsRepo extends JpaRepository<AppleProductsEntity, Long> {
    List<AppleProductsEntity> findAll();

    List<AppleProductsEntity> findByProductsInStockAndProductsStatus(Integer stock, ProductsStatusEnum statusEnum);

    List<AppleProductsEntity> findByProductsStatus(ProductsStatusEnum productsStatusEnum);
}

