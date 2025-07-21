package com.imranhss.project.service;

import com.imranhss.project.dto.DistrictResponseDTO;
import com.imranhss.project.entity.District;
import com.imranhss.project.entity.Division;
import com.imranhss.project.repository.IDistrictRepo;
import com.imranhss.project.repository.IDivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private IDistrictRepo districtRepo;

    @Autowired
    private IDivisionRepo divisionRepo;

    public void save(District district){
        if (district.getDivision() !=null){
            int divId = district.getDivision().getId();
            Division division =divisionRepo.findById(divId).orElseThrow(()-> new RuntimeException("Division not found with this id"+divId));

            district.setDivision(division);
        }
    }

    public List<District> getAllDistrict(){

        return districtRepo.findAll();
    }

    public List<DistrictResponseDTO> getAlDistrictResponseDTOS(){
        List<District> districts = getAllDistrict();

        return districts.stream().map(d->{
            DistrictResponseDTO dto= new DistrictResponseDTO();
            dto.setId(d.getId());
            dto.setName(d.getName());

            List<Integer> psIds= d.getPoliceStations().stream().map(ps-> ps.getId()).toList();

            dto.setPoliceStations(psIds);
            return dto;
        }).toList();
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
