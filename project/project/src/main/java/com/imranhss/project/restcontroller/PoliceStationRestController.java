package com.imranhss.project.restcontroller;

import com.imranhss.project.entity.PoliceStation;
import com.imranhss.project.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/policestation/")
public class PoliceStationRestController {

    @Autowired
    private PoliceStationService policeStationService;

    @PostMapping("")
    public void save(@RequestBody PoliceStation policeStation){
        policeStationService.saveOrUpdate(policeStation);
    }


    @GetMapping("")
    public List<PoliceStation> getAll(){
        return policeStationService.findAll();
    }

    public Optional<PoliceStation> findById(Integer id){
        return policeStationService.findById(id);
    }
}
