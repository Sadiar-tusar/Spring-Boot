package com.sadiar.erp.repository;

import com.sadiar.erp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {

    List<Product> findByStockQtyLessThanEqual(Integer stockLevel);
}
