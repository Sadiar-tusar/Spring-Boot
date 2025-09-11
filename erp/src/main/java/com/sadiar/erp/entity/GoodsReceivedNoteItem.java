package com.sadiar.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "goodsreceiveitem")
public class GoodsReceivedNoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GoodsReceivedNote grn;

    @ManyToOne
    private Product product;

    private int receivedQuantity;

    // getters & setters

    public GoodsReceivedNoteItem() {
    }

    public GoodsReceivedNoteItem(Long id, GoodsReceivedNote grn, Product product, int receivedQuantity) {
        this.id = id;
        this.grn = grn;
        this.product = product;
        this.receivedQuantity = receivedQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsReceivedNote getGrn() {
        return grn;
    }

    public void setGrn(GoodsReceivedNote grn) {
        this.grn = grn;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }
}
