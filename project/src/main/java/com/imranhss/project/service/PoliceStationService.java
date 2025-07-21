package com.imranhss.project.service;

import com.imranhss.project.dto.PoliceStationResponceDTO;
import com.imranhss.project.entity.District;
import com.imranhss.project.entity.PoliceStation;
import com.imranhss.project.repository.IDistrictRepo;
import com.imranhss.project.repository.IPoliceStationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {

    @Autowired
    private IPoliceStationRepo policeStationRepo;

    @Autowired
    private IDistrictRepo districtRepo;

    public List<PoliceStationResponceDTO> getAlPoliceStationResponceDTOS(){
        return policeStationRepo.findAll().stream().map(policeStation -> {
            PoliceStationResponceDTO dto= new PoliceStationResponceDTO();
            dto.setId(policeStation.getId());
            dto.setName(policeStation.getName());

            if(policeStation.getDistrict() != null){
                dto.setDistrictId(policeStation.getDistrict().getId());
                dto.setDistrictName(policeStation.getDistrict().getName());
            }
            return dto;
        }).toList();
    }

    @Transactional
    public PoliceStation creat(PoliceStation policeStation){
        if(policeStation.getDistrict() != null){
            int districtId= policeStation.getDistrict().getId();
            District district= districtRepo.findById(districtId).orElseThrow(()-> new RuntimeException("District not found by id"+ districtId));
            policeStation.setDistrict(district);
        }
        return policeStationRepo.save(policeStation);
    }

    // Update by ID
    public PoliceStation update(int id, PoliceStation updatedPoliceStation) {
        PoliceStation existing = policeStationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("PoliceStation not found with id " + id));

        existing.setName(updatedPoliceStation.getName());

        if (updatedPoliceStation.getDistrict() != null) {
            // Optionally verify district exists
            District district = districtRepo.findById(updatedPoliceStation.getDistrict().getId())
                    .orElseThrow(() -> new RuntimeException("District not found with id " + updatedPoliceStation.getDistrict().getId()));
            existing.setDistrict(district);
        }

        return policeStationRepo.save(existing);
    }

    public void saveOrUpdate(PoliceStation policeStation) {
        policeStationRepo.save(policeStation);
    }

    public List<PoliceStation> findAll() {
        return policeStationRepo.findAll();
    }

    public Optional<PoliceStation> findById(Integer id) {
        return policeStationRepo.findById(id);
    }

    public void deleteById(Integer id){
        policeStationRepo.deleteById(id);
    }

    // Delete by ID
    public void delete(int id) {
        policeStationRepo.deleteById(id);
    }
}
