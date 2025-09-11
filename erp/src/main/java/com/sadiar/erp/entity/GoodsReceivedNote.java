package com.sadiar.erp.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "goodsreceivednote")
public class GoodsReceivedNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grnNumber;
    private Date receivedDate;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @OneToMany(mappedBy = "grn", cascade = CascadeType.ALL)
    private List<GoodsReceivedNoteItem> items;

    // getters & setters


    public GoodsReceivedNote() {
    }

    public GoodsReceivedNote(Long id, String grnNumber, Date receivedDate, PurchaseOrder purchaseOrder, List<GoodsReceivedNoteItem> items) {
        this.id = id;
        this.grnNumber = grnNumber;
        this.receivedDate = receivedDate;
        this.purchaseOrder = purchaseOrder;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrnNumber() {
        return grnNumber;
    }

    public void setGrnNumber(String grnNumber) {
        this.grnNumber = grnNumber;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public List<GoodsReceivedNoteItem> getItems() {
        return items;
    }

    public void setItems(List<GoodsReceivedNoteItem> items) {
        this.items = items;
    }
}
