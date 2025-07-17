package com.imranhss.project.service;

import com.imranhss.project.entity.District;
import com.imranhss.project.repository.IDistrictRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private IDistrictRepo districtRepo;

    public void save(District district){
        districtRepo.save(district);
    }

    public List<District> getAllDistrict(){

        return districtRepo.findAll();
    }

    public District getDistrictbyId(int id){
        return districtRepo.findById(id).get();
    }

    public void deleteDistrictById(int id){
        districtRepo.deleteById(id);
    }

    public District getDistrictByName(String name){
        return districtRepo.findByName(name);
    }
}
