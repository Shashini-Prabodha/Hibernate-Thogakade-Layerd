package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOType;
import lk.ijse.hibernate.bo.custom.ItemBO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.view.TM.CasheirTM;
import lk.ijse.hibernate.view.TM.ItemTM;

import java.util.List;
import java.util.Optional;

public class ItemFormController {
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDescription;
    public JFXButton btnSave;
    public Label lblID;
    public JFXTextField txtUPrice;
    public TableView<ItemTM> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colRemove;
    public JFXButton btnUpdate;
    public JFXButton btnNew;
    public JFXButton btnExit;

    ItemBO itemBO = BOFactory.getInstance().getBO(BOType.ITEM);

    public void initialize() {

        lblID.setText(genarateNewID());

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitP"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllItem();
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });

    }

    private void setData(ItemTM tm) {
        try {
            txtDescription.setText(tm.getDescription());
            txtUPrice.setText(tm.getUnitP() + "");
            txtQtyOnHand.setText(tm.getQty() + "");
            lblID.setText(tm.getCode());
        } catch (NullPointerException e) {
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        ItemDTO item = new ItemDTO(lblID.getText(), txtDescription.getText(), Double.parseDouble(txtUPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
        try {
            if (itemBO.saveItem(item)) {
                JFXButton btn = new JFXButton("Remove");
                ItemTM tm = new ItemTM((lblID.getText()), txtDescription.getText(), Double.parseDouble(txtUPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()), btn);

                new Alert(Alert.AlertType.CONFIRMATION, "Item Saved...!").show();
                loadAllItem();
                txtDescription.clear();
                txtQtyOnHand.clear();
                txtUPrice.clear();
                lblID.setText(genarateNewID());
                txtDescription.focusColorProperty();


            } else {
                new Alert(Alert.AlertType.ERROR, "Failed...!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String genarateNewID() {
        String s = null;
        try {
            s = itemBO.newItemID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private void loadAllItem() {
        ObservableList<ItemTM> list = FXCollections.observableArrayList();
        try {
            List<ItemDTO> dtoList = itemBO.getAllItem();
            for (ItemDTO dto : dtoList) {
                JFXButton btn = new JFXButton("Delete");
                ItemTM tm = new ItemTM(dto.getCode(), dto.getDescription(), dto.getPrice(), dto.getQtyOnHand(), btn);
                list.add(tm);

                btn.setStyle("-fx-background-color: #81ecec; ");
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (itemBO.deleteItem(tm.getCode())) {
                                new Alert(Alert.AlertType.INFORMATION, "Delete Item").show();
                                loadAllItem();
                                return;
                            } else {
                            }
                            new Alert(Alert.AlertType.ERROR, "Delete Failed").show();

                        } else {

                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblItem.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ItemDTO dto = new ItemDTO(lblID.getText(), txtDescription.getText(), Double.parseDouble(txtUPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
        try {
            if (itemBO.updateItem(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Update Successfull..!").show();
                loadAllItem();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Failed....!").show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        lblID.setText(genarateNewID());
        txtUPrice.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
