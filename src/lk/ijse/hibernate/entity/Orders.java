package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Orders implements SuperEntity{
    @Id
    private String orderId;
    private String custId;
    private Date date;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<Item> items;

    public Orders() {
    }

    public Orders(String orderId, String custId, Date date) {
        this.orderId = orderId;
        this.custId = custId;
        this.date = date;
    }

    public Orders(String orderId, String custId, Date date, Customer customer) {
        this.orderId = orderId;
        this.custId = custId;
        this.date = date;
        this.customer = customer;
    }

    public Orders(String orderId, String custId, Date date, Customer customer, List<Item> items) {
        this.orderId = orderId;
        this.custId = custId;
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

    public String getCustId() {
        return custId;
    }


    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", custId='" + custId + '\'' +
                ", date=" + date +
                ", customer=" + customer +
                ", items=" + items +
                '}';
    }
}
