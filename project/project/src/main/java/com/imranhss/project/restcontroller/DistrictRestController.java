package com.imranhss.project.restcontroller;

import com.imranhss.project.entity.District;
import com.imranhss.project.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/district")
public class DistrictRestController {

    @Autowired
    private DistrictService districtService;

    public ResponseEntity<String> saveDistrict(@RequestBody District district){
        districtService.save(district);
        return ResponseEntity.ok("Succes");
    }
}
