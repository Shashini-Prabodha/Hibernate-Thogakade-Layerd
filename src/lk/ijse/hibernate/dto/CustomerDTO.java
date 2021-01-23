package lk.ijse.hibernate.dto;

public class CustomerDTO {
    private String custId;
    private String name;
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(String custId, String name, String address) {
        this.custId = custId;
        this.name = name;
        this.address = address;
    }

    public String getcustId() {
        return custId;
    }

    public void setcustId(String custId) {
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

    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
