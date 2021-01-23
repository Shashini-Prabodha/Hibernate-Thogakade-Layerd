package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer implements SuperEntity {
    @Id
    private String custId;
    private String name;
    private String address;
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    public Customer() {
    }

    public Customer(String custId, String name, String address) {
        this.custId = custId;
        this.name = name;
        this.address = address;
    }

    public Customer(String custId, String name, String address, List<Orders> orders) {
        this.custId = custId;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
