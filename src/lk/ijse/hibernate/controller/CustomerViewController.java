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
import lk.ijse.hibernate.bo.custom.impl.CustomerBOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.view.TM.CustomerTM;
import lk.ijse.hibernate.view.TM.ItemTM;

import java.util.List;
import java.util.Optional;

public class CustomerViewController {
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXButton btnSave;
    public Label lblID;
    public JFXButton btnExit;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddr;
    public TableColumn colRemove;
    public JFXButton btnUpdate;
    public JFXButton btnNew;

    CustomerBOImpl customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);


    public void initialize() {

        lblID.setText(generateNewID());

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCustomer();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });

    }

    private void setData(CustomerTM tm) {
        try {
            txtAddress.setText(tm.getAddress());
            txtName.setText(tm.getName());
            lblID.setText(tm.getId());
        } catch (NullPointerException e) {
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = lblID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address);

        try {
            boolean added = customerBO.saveCustomer(customer);


            if (added) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
                generateNewID();
                loadAllCustomer();
            }

            txtName.clear();
            txtAddress.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateNewID() {
        String s = null;
        try {
            s = customerBO.newCustomerID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO = new CustomerDTO(lblID.getText(), txtName.getText(), txtAddress.getText());
        try {
            if (customerBO.updateCustomer(customerDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Update Successfull..!").show();
                loadAllCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Failed....!").show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadAllCustomer() {
        ObservableList<CustomerTM> list = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> dtoList = customerBO.getAllCustomer();
            for (CustomerDTO dto : dtoList) {
                JFXButton btn = new JFXButton("Delete");
                CustomerTM tm = new CustomerTM(dto.getcustId(), dto.getName(), dto.getAddress(), btn);
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
                            if (customerBO.deleteCustomer(tm.getId())) {
                                new Alert(Alert.AlertType.INFORMATION, "Delete Customer..!").show();
                                loadAllCustomer();
                                return;
                            } else {
                            }
                            new Alert(Alert.AlertType.ERROR, "Delete Failed...!").show();

                        } else {

                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblCustomer.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        lblID.setText(generateNewID());
        txtName.clear();
        txtAddress.clear();
    }

}
