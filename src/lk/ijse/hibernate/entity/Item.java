package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Item implements SuperEntity{
    @Id
    private String code;
    private String description;
    private double price;
    private int qtyOnHand;
    @ManyToMany(mappedBy = "items")
    private List<Orders> orders;
    public Item() {
    }

    public Item(String code, String description, double price, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
    }

    public Item(String code, String description, double price, int qtyOnHand, List<Orders> orders) {
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

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                ", orders=" + orders +
                '}';
    }
}
