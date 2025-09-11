package com.sadiar.erp.service;

import com.sadiar.erp.entity.PurchaseRequest;
import com.sadiar.erp.repository.*;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final IVendorRepo vendorRepository;
    private final IPurchaseRequestRepo prRepository;
    private final IPurchaseOrderRepo poRepository;
    private final IGoodsReceivedNoteRepo grnRepository;
    private final IVendorPaymentRepo paymentRepository;

    public PurchaseService(IVendorRepo vendorRepository,
                           IPurchaseRequestRepo prRepository,
                           IPurchaseOrderRepo poRepository,
                           IGoodsReceivedNoteRepo grnRepository,
                           IVendorPaymentRepo paymentRepository) {
        this.vendorRepository = vendorRepository;
        this.prRepository = prRepository;
        this.poRepository = poRepository;
        this.grnRepository = grnRepository;
        this.paymentRepository = paymentRepository;
    }

    // Example: Create Purchase Request
    public PurchaseRequest createPurchaseRequest(PurchaseRequest pr) {
        pr.setRequestDate(new Date());
        pr.setStatus("PENDING");
        return prRepository.save(pr);
    }

    // Example: Approve PR and create PO
    public PurchaseOrder createPurchaseOrder(Long prId, Vendor vendor) {
        PurchaseRequest pr = prRepository.findById(prId)
                .orElseThrow(() -> new RuntimeException("PR not found"));

        PurchaseOrder po = new PurchaseOrder();
        po.setOrderDate(new Date());
        po.setVendor(vendor);
        po.setStatus("PENDING");
        // Convert PR items to PO items
        pr.getItems().forEach(prItem -> {
            PurchaseOrderItem poItem = new PurchaseOrderItem();
            poItem.setProduct(prItem.getProduct());
            poItem.setQuantity(prItem.getQuantity());
            poItem.setUnitPrice(prItem.getProduct().getUnitPrice());
            poItem.setPurchaseOrder(po);
            po.getItems().add(poItem);
        });

        return poRepository.save(po);
    }

    // Goods Receive & update stock
    public GoodsReceivedNote receiveGoods(Long poId, List<GoodsReceivedNoteItem> receivedItems) {
        PurchaseOrder po = poRepository.findById(poId)
                .orElseThrow(() -> new RuntimeException("PO not found"));

        GoodsReceivedNote grn = new GoodsReceivedNote();
        grn.setPurchaseOrder(po);
        grn.setReceivedDate(new Date());
        grn.setItems(receivedItems);
        receivedItems.forEach(item -> item.setGrn(grn));

        // Increase stock in Product (assume Product has stockQty)
        receivedItems.forEach(item -> {
            var product = item.getProduct();
            product.setStockQty(product.getStockQty() + item.getReceivedQuantity());
        });

        return grnRepository.save(grn);
    }

    // Payment
    public VendorPayment makePayment(Long poId, double amount) {
        PurchaseOrder po = poRepository.findById(poId)
                .orElseThrow(() -> new RuntimeException("PO not found"));
        VendorPayment payment = new VendorPayment();
        payment.setPurchaseOrder(po);
        payment.setVendor(po.getVendor());
        payment.setAmount(amount);
        payment.setPaymentDate(new Date());
        payment.setStatus("PAID");
        return paymentRepository.save(payment);
    }
}
