package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetails implements SuperEntity{
    @Id
    private String orderId;
    private String code;
    private int orderQty;

    public OrderDetails() {
    }

    public OrderDetails(String orderId, String code, int orderQty) {
        this.orderId = orderId;
        this.code = code;
        this.orderQty = orderQty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", code='" + code + '\'' +
                ", orderQty=" + orderQty +
                '}';
    }
}
