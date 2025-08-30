package com.sadiar.insurancemangement.repository;

import com.sadiar.insurancemangement.entity.CompanyVoltAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyVoltRepository extends JpaRepository<CompanyVoltAccount,Long> {
}
