package com.sadiar.insurancemangement.restcontroller;

import com.sadiar.insurancemangement.entity.Account;
import com.sadiar.insurancemangement.entity.FireMoneyReceipt;
import com.sadiar.insurancemangement.service.AccountService;
import com.sadiar.insurancemangement.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {

    private final AccountService accountService;
    private final PaymentService paymentService;

    public PaymentRestController(AccountService accountService, PaymentService paymentService) {
        this.accountService = accountService;
        this.paymentService = paymentService;
    }

    // Deposit money into user account
    @PostMapping("/deposit/{id}")
    public ResponseEntity<String> deposit(@PathVariable int id, @RequestParam Double amount) {
        try {
            accountService.depositMoney(id, amount);
            return ResponseEntity.ok("Deposit successful. Amount: " + amount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Deposit failed: " + e.getMessage());
        }
    }

//    // User pays to Company Volt Account
    @PostMapping("/pay/{id}")
    public ResponseEntity<String> payPremium(@PathVariable int id, @RequestParam Double amount) {
        try {
             accountService.payToVolt(id, amount);
            return ResponseEntity.ok("message");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Payment failed: " + e.getMessage());
        }
    }

//    @PostMapping("/pay/{id}")
//    public Account payPremium(@RequestBody FireMoneyReceipt b,
//                            @RequestParam int billId) {
//        return moneyReceiptService.createFireMoneyReceipt( b, billId);
//    }

    // Get user account balance
    @GetMapping("/balance/{id}")
    public ResponseEntity<Double> getUserBalance(@PathVariable int id) {
        try {
            Double balance = accountService.getUserBalance(id);
            return ResponseEntity.ok(balance);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get company account balance
    @GetMapping("/company-balance")
    public ResponseEntity<Double> getCompanyBalance() {
        try {
            Double balance = accountService.getCompanyBalance();
            return ResponseEntity.ok(balance);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
