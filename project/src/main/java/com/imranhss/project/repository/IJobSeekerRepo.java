package com.imranhss.project.repository;

import com.imranhss.project.entity.Jobseeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobSeekerRepo extends JpaRepository<Jobseeker, Long> {
}
