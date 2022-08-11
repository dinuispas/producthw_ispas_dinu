package com.example.producthw.repository;

import com.example.producthw.model.Product;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAll();

    @Modifying
    @Transactional
    @Query("update Product u set u.initialStock = :initialStock where u.id = :id")
    void updateProduct(@Param(value = "id") Integer id, @Param(value = "initialStock") Integer initialStock);

    @Modifying
    @Transactional
    @Query("UPDATE Product t set t.initialStock = t.initialStock - 1 WHERE t.id = :id")
    void updateStock(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Product t set t.initialStock = t.initialStock + 1 WHERE t.id = :id")
    void updateStockPlus(Integer id);




}
