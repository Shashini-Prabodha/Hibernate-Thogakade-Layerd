package lk.ijse.hibernate.view.TM;

import com.jfoenix.controls.JFXButton;

public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private JFXButton btn;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, JFXButton btn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", btn=" + btn +
                '}';
    }
}
