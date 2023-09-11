package com.example.demo.dao.repo;


import com.example.demo.dao.entity.AppleProductsMapEntity;
import com.example.demo.enums.ProductsModelsEnum;
import com.example.demo.enums.ProductsTypesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppleProductsMapRepo extends JpaRepository<AppleProductsMapEntity, Long> {
    List<AppleProductsMapEntity> findAll();

    AppleProductsMapEntity findByProductTypeAndProductModel(ProductsTypesEnum typesEnum, ProductsModelsEnum modelsEnum);
}
