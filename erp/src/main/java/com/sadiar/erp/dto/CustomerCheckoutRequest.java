package com.sadiar.erp.dto;

import java.util.List;

public class CustomerCheckoutRequest {

    private Long customerId;
    private List<Item> items;

    public static class Item {
        private Long productId;
        private Integer quantity;

        // getters & setters
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    // getters & setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
