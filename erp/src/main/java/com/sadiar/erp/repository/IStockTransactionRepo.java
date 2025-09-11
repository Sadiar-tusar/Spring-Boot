package com.sadiar.erp.repository;

import com.sadiar.erp.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockTransactionRepo extends JpaRepository<StockTransaction,Long> {

    List<StockTransaction> findByProductId(Long productId);
}
