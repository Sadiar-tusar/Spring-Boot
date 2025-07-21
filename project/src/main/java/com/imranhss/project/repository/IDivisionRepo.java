package com.imranhss.project.repository;

import com.imranhss.project.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDivisionRepo extends JpaRepository<Division, Integer> {
}
