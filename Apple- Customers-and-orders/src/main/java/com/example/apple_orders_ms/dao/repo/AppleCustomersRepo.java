package com.example.apple_orders_ms.dao.repo;

import com.example.apple_orders_ms.dao.entity.AppleCustomersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleCustomersRepo extends JpaRepository<AppleCustomersEntity,Long> {

    Page<AppleCustomersEntity> findByName(String name, Pageable pageable);

    Page<AppleCustomersEntity> findBySurname(String surname, Pageable pageable);
}
