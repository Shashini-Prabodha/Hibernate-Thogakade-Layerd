package lk.ijse.hibernate.dto;

import lk.ijse.hibernate.entity.Orders;

import java.util.List;

public class ItemDTO {
    private String code;
    private String description;
    private double price;
    private int qtyOnHand;
    private List<OrderDTO> orders;


    public ItemDTO() {
    }

    public ItemDTO(String code, String description, double price, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
    }

    public ItemDTO(String code, String description, double price, int qtyOnHand, List<OrderDTO> orders) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
        this.orders = orders;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                ", orders=" + orders +
                '}';
    }
}
