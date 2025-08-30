package com.sadiar.insurancemangement.service;

import com.sadiar.insurancemangement.entity.Account;
import com.sadiar.insurancemangement.entity.Payment;
import com.sadiar.insurancemangement.repository.IPaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {

    private final AccountService accountService;
    private final IPaymentRepository paymentRepository;

    public PaymentService(AccountService accountService, IPaymentRepository paymentRepository) {
        this.accountService = accountService;
        this.paymentRepository = paymentRepository;
    }

    // Pay premium from user → company
    @Transactional
    public void payPremium(int id, Double amount) {

        Account userAccount = accountService.getUserAccount(id);
        Account companyAccount = accountService.getCompanyAccount();

        if (userAccount.getAmount() < amount) {
            throw new RuntimeException("Insufficient balance!");
        }

        // Transfer money
        userAccount.setAmount(userAccount.getAmount() - amount);
        companyAccount.setAmount(companyAccount.getAmount() + amount);

        // Save updated accounts
        accountService.depositMoney(id, 0.0); // saves user account
        accountService.getCompanyAccount(); // saves company account via repository
        // Alternatively, create saveAccount(Account account) method in AccountService for clarity

        // Save payment record
        Payment payment = new Payment();
        payment.setUser(userAccount.getUser());
        payment.setAmount(amount);
        payment.setPaymentDate(new Date());
        payment.setPaymentMode("ACCOUNT_TRANSFER"); // or UPI/CARD
        paymentRepository.save(payment);
    }
}
