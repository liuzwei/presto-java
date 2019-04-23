package com.aura.prestojava.bean;

public class AgeQueryVO {
    private Integer age;
    private Long totalPrice;

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "AgeQueryVO{" +
                "age=" + age +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
