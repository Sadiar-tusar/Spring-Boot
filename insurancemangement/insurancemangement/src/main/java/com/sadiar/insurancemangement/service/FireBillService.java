package com.sadiar.insurancemangement.service;

import com.sadiar.insurancemangement.entity.FireBill;
import com.sadiar.insurancemangement.entity.FirePolicy;
import com.sadiar.insurancemangement.repository.IFireBillRepository;
import com.sadiar.insurancemangement.repository.IFirePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireBillService {

    @Autowired
    private IFireBillRepository fireBillRepository;

    @Autowired
    private IFirePolicyRepository firePolicyRepository;

    public List<FireBill> getAllBill() {
        return fireBillRepository.findAll();
    }

    //save fire bill
    public FireBill saveFireBill(FireBill fireBill) {
        FirePolicy firePolicy=firePolicyRepository.findById(fireBill.getFirePolicy().getId())
                .orElseThrow(()->new RuntimeException("Fire Policy Not Found"));

        fireBill.setFirePolicy(firePolicy);

        return fireBillRepository.save(fireBill);

    }

    // Update an existing Bill by ID
    public FireBill updateFireBill(FireBill updatedBill, int id) {
        // Fetch the existing bill
        FireBill existingBill = fireBillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with ID: " + id));

        // Update fields
        existingBill.setFire(updatedBill.getFire());
        existingBill.setRsd(updatedBill.getRsd());

        // Tax rate is fixed at 15%, no update needed

        // Recalculate premiums
        calculatePremiums(existingBill);

        // Save the updated bill
        return fireBillRepository.save(existingBill);
    }

    // Delete a Bill by ID
    public void deleteBill(int id) {
        if (!fireBillRepository.existsById(id)) {
            throw new RuntimeException("Bill not found with ID: " + id);
        }
        fireBillRepository.deleteById(id);
    }

    // Get a Bill by its ID
    public FireBill getBillById(int id) {
        return fireBillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with ID: " + id));
    }



    // Calculation method for premiums
    private void calculatePremiums(FireBill fireBill) {
        double fireRate = fireBill.getFire() / 100; // Fire rate in percentage
        double rsdRate = fireBill.getRsd() / 100;   // RSD rate in percentage
        double taxRate = fireBill.getTax() / 100;   // Tax rate in percentage

        // Get the sum insured from the related Policy
        double sumInsured = fireBill.getFirePolicy().getSumInsured();

        // Calculate net premium
        double netPremium = (sumInsured * fireRate) + (sumInsured * rsdRate);
        fireBill.setNetPremium(roundToTwoDecimalPlaces(netPremium));

        // Calculate tax on net premium
        double tax = netPremium * taxRate;

        // Calculate gross premium
        double grossPremium = netPremium + tax;
        fireBill.setGrossPremium(roundToTwoDecimalPlaces(grossPremium));
    }

    // Method to round to two decimal places
    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // Update all bills when policy sumInsured changes
    public void updateBillsForFirePolicy(FirePolicy updatedFirePolicy) {
        // Fetch bills associated with the policy
        List<FireBill> bills = fireBillRepository.findBillsByPolicyId(updatedFirePolicy.getId());

        // Update each bill
        for (FireBill bill : bills) {
            bill.setFirePolicy(updatedFirePolicy); // Update policy reference
            calculatePremiums(bill); // Recalculate premiums
            fireBillRepository.save(bill); // Save the updated bill
        }
    }




    // Find bills by policyholder name
    public List<FireBill> getBillsByPolicyholder(String policyholder) {
        return fireBillRepository.findBillsByPolicyholder(policyholder);
    }

    // Find bills by the associated policy ID
    public List<FireBill> findBillByPolicyId(int policyId) {
        return fireBillRepository.findBillsByPolicyId(policyId);
    }

}
