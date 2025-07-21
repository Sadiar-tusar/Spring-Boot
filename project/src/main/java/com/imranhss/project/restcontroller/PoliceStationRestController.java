package com.imranhss.project.restcontroller;

import com.imranhss.project.dto.PoliceStationResponceDTO;
import com.imranhss.project.entity.PoliceStation;
import com.imranhss.project.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/policestation/")
public class PoliceStationRestController {

    @Autowired
    private PoliceStationService policeStationService;

    // Create
    @PostMapping("")
    public ResponseEntity<PoliceStation> createPoliceStation(@RequestBody PoliceStation policeStation) {
        PoliceStation created = policeStationService.creat(policeStation);
        return ResponseEntity.ok(created);
    }

    // Get all
    @GetMapping("")
    public ResponseEntity<List<PoliceStationResponceDTO>> getAllPoliceStations() {
        List<PoliceStationResponceDTO> list = policeStationService.getAlPoliceStationResponceDTOS();
        return ResponseEntity.ok(list);
    }

    // Get one by ID
    @GetMapping("/{id}")
    public ResponseEntity<PoliceStation> getPoliceStationById(@PathVariable int id) {
        return policeStationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update by ID
    @PutMapping("/{id}")
    public ResponseEntity<PoliceStation> updatePoliceStation(@PathVariable int id,
                                                             @RequestBody PoliceStation policeStation) {
        try {
            PoliceStation updated = policeStationService.update(id, policeStation);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoliceStation(@PathVariable int id) {
        policeStationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
