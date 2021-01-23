package lk.ijse.hibernate.dto;

import lk.ijse.hibernate.entity.Item;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private Date date;
    private CustomerDTO customer;

    private List<ItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, Date date, CustomerDTO customer) {
        this.orderId = orderId;
        this.date = date;
        this.customer = customer;
    }

    public OrderDTO(String orderId, Date date, CustomerDTO customer, List<ItemDTO> items) {
        this.orderId = orderId;
        this.date = date;
        this.customer = customer;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customer=" + customer +
                ", items=" + items +
                '}';
    }
}
