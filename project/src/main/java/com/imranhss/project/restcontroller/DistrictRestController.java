package com.imranhss.project.restcontroller;

import com.imranhss.project.dto.DistrictResponseDTO;
import com.imranhss.project.entity.District;
import com.imranhss.project.service.DistrictService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/district")
public class DistrictRestController {

    @Autowired
    private DistrictService districtService;

    @PostMapping("")
    public ResponseEntity<String> saveDistrict(@RequestBody District district){
        try {
            districtService.save(district);
            return ResponseEntity.ok("Succes");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("")
    public ResponseEntity<List<DistrictResponseDTO>> getDistricts() {

        try {
            List<DistrictResponseDTO> dList = districtService.getAlDistrictResponseDTOS();

            return ResponseEntity.ok(dList);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
