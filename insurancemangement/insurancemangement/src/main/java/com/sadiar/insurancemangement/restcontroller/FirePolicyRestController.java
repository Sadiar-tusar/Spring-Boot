package com.sadiar.insurancemangement.restcontroller;

import com.sadiar.insurancemangement.entity.FirePolicy;
import com.sadiar.insurancemangement.service.FirePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/firepolicy")
@CrossOrigin("*")
public class FirePolicyRestController {

    @Autowired
    private FirePolicyService firePolicyService;

    @GetMapping
    public ResponseEntity<List<FirePolicy>> getAllFirePolicy() {
        List<FirePolicy> firePolicies = firePolicyService.getAllFirePolicy();

        return ResponseEntity.ok(firePolicies);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFirePolicy(@RequestBody FirePolicy firePolicy) {
        firePolicyService.saveFirePolicy(firePolicy);
        return ResponseEntity.status(HttpStatus.CREATED).body("Policy saved successfully.");
    }
}
