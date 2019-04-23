package com.aura.prestojava.bean;

public class BrandQueryVO {
    private String brand;
    private Long totalPrice;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BrandQueryVO{" +
                "brand='" + brand + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
