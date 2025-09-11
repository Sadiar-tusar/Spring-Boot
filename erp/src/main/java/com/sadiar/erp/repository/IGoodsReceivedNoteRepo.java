package com.sadiar.erp.repository;

import com.sadiar.erp.entity.GoodsReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoodsReceivedNoteRepo extends JpaRepository<GoodsReceivedNote,Long> {
}
