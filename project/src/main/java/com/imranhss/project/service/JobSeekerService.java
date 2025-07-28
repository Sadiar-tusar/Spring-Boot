package com.imranhss.project.service;

import com.imranhss.project.entity.Jobseeker;
import com.imranhss.project.repository.IJobSeekerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerService {

    @Autowired
    private IJobSeekerRepo jobSeekerRepository;

    public List<Jobseeker> getAll() {
        return jobSeekerRepository.findAll();
    }

    public Optional<Jobseeker> getById(Long id) {
        return jobSeekerRepository.findById(id);
    }

    public Jobseeker save(Jobseeker jobSeeker) {
        return jobSeekerRepository.save(jobSeeker);
    }

    public void delete(Long id) {
        jobSeekerRepository.deleteById(id);
    }
}
