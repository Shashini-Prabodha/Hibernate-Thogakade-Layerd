package lk.ijse.hibernate.view.TM;

import com.jfoenix.controls.JFXButton;

public class ItemTM {
    String code;
    String description;
    private double unitP;
    private int qty;
    private JFXButton btn;

    public ItemTM() {
    }

    public ItemTM(String code, String description, double unitP, int qty, JFXButton btn) {
        this.code = code;
        this.description = description;
        this.unitP = unitP;
        this.qty = qty;
        this.btn = btn;
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

    public double getUnitP() {
        return unitP;
    }

    public void setUnitP(double unitP) {
        this.unitP = unitP;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitP=" + unitP +
                ", qty=" + qty +
                ", btn=" + btn +
                '}';
    }
}
