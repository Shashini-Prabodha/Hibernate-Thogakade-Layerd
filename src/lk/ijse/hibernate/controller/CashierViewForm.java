package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.hibernate.bo.custom.OrderBO;
import lk.ijse.hibernate.bo.custom.impl.CustomerBOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.view.TM.CasheirTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class CashierViewForm {
    public Label lblDate;
    public JFXComboBox<String> cmbItemID;
    public JFXTextField txtItemDescription;
    public Label lblStock;
    public Label lblUPrice;
    public Label lblTime;
    public JFXButton btnPlaceOrder;
    public JFXButton btnAddtoCart;
    public JFXButton btnCustomerAdd;
    public TextField txtQty;
    public JFXTextField txtCustomerAddress;
    public JFXComboBox cmbCustName;
    public TableView<CasheirTM> tblOrderDetail;
    public TableColumn colItemId, colItemName, colQty, colDelete, colUnitPrice;
    public Label lblTot;
    public Label lblOrderId;
    public JFXButton btnHome;
    public JFXButton btnLogout;
    public JFXComboBox<String> cmbCustId;
    public JFXTextField txtCustomerName;


    CustomerBOImpl customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);
    OrderBO orderBO = BOFactory.getInstance().getBO(BOType.ORDER);
    ItemBO itemBO = BOFactory.getInstance().getBO(BOType.ITEM);

    public void initialize() {
        loadCustomers();
        loadArryaList();
        loadItems();
       lblOrderId.setText(genarateOrderID());


        lblDate.setText(String.valueOf(LocalDate.now()));

        colItemId.setCellValueFactory(new PropertyValueFactory<>("storeID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("type"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnStore"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblOrderDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private String genarateOrderID() {
        String orderID = null;
        try {
            orderID = orderBO.orderID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderID;
    }

    private void loadCustomers() {
        try {
            List<CustomerDTO> list = customerBO.getAllCustomer();
            for (CustomerDTO dto : list) {
                cmbCustId.getItems().add(dto.getcustId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItems() {
        try {
            List<ItemDTO> list = itemBO.getAllItem();
            for (ItemDTO dto : list) {
                cmbItemID.getItems().add(dto.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //set data to click table
    private void setData(CasheirTM tm) {
        try {
            txtQty.setText(String.valueOf(tm.getQtyOnStore()));
            txtItemDescription.setText(tm.getType());
            cmbItemID.setValue(tm.getStoreID());
        } catch (NullPointerException e) {

        }
    }

    public void cmbItemIDOnAction(ActionEvent actionEvent) {

        String itemID = cmbItemID.getValue();
        try {
            ItemDTO item = itemBO.getItem(itemID);
            txtItemDescription.setText(item.getDescription());
            lblStock.setText(String.valueOf(item.getQtyOnHand()));
            lblUPrice.setText(String.valueOf(item.getPrice()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
    }

    public void btnCustomerAddOnAction(ActionEvent actionEvent) {
    }

    ObservableList<CasheirTM> list = FXCollections.observableArrayList();

    public void btnAddtoCartOnAtioon(ActionEvent actionEvent) {
        try {

            if (!cmbCustId.getSelectionModel().isEmpty()) {

                int itemQty = itemBO.getItem(String.valueOf(cmbItemID.getValue())).getQtyOnHand();

                if (Integer.parseInt(txtQty.getText()) <= itemQty) {
                    JFXButton btn = new JFXButton("Remove");
                    CasheirTM tm = new CasheirTM((cmbItemID.getValue()), txtItemDescription.getText(), Integer.parseInt(txtQty.getText()),Double.parseDouble(lblUPrice.getText()),  btn);

                    if (list.isEmpty()) {
                        list.add(tm);
                        btnOnAction(btn);
                        tblOrderDetail.setItems(list);
                        // loadArryaList();
                        getItemTotal();
                        tblOrderDetail.refresh();
                        return;

                    } else {
                        for (int i = 0; i < tblOrderDetail.getItems().size(); i++) {
                            String cmb = cmbItemID.getValue();

                            if (list.get(i).getStoreID().equals(cmb)) {
                                int stoke = list.get(i).getQtyOnStore();
                                //if ((stoke + Integer.parseInt(txtQty.getText())) <= store) {
                                list.get(i).setQtyOnStore(list.get(i).getQtyOnStore() + Integer.parseInt(txtQty.getText()));
                                getItemTotal();
                                tblOrderDetail.refresh();
                               /* } else {
                                    new Alert(Alert.AlertType.ERROR, "Out Of Stock...!").show();
                                    txtQty.requestFocus();
                                }*/
                                return;
                            }
                        }
                    }
                    list.add(tm);
                    btnOnAction(btn);

                    tblOrderDetail.setItems(list);
                    loadArryaList();
                    getItemTotal();
                    tblOrderDetail.refresh();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Out Of Stock...!").show();
                }
            } else {
                cmbCustName.focusColorProperty();
            }
        } catch (Exception exception) {
            exception.printStackTrace();

        }
    }

    void btnOnAction(JFXButton btn) {
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
                    int index = tblOrderDetail.getSelectionModel().getFocusedIndex();
                    list.remove(index);
                    tblOrderDetail.refresh();
                } else {
                    //no
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            OrderDTO orderDTO = new OrderDTO(lblOrderId.getText(), cmbCustId.getValue(), Date.valueOf(lblDate.getText()));
            boolean saved = orderBO.saveOrder(orderDTO);
            boolean updated = true;
            ArrayList<ItemDTO> itemList = getAllTblItemData();

            for (ItemDTO dto : itemList) {
                updated = itemBO.updateItem(dto);
                if (!updated) {
                    new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                }
            }
            if (saved && updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Completed...!" + "\n Total is :" + lblTot.getText() + " Rs. ").show();
                genarateOrderID();
                list.clear();
                tblOrderDetail.refresh();
                reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void reset() {
        try {
            txtItemDescription.clear();
            txtCustomerName.clear();
            lblTot.setText("0.0");
            txtCustomerAddress.clear();

        } catch (NullPointerException e) {

        }

    }

    private ArrayList<ItemDTO> getAllItemData;

    private ArrayList<ItemDTO> getAllTblItemData() {
        try {

            getAllItemData = new ArrayList<>();
            int dataFilledrowCount = colItemId.getTableView().getItems().size();

            for (int i = 0; i < dataFilledrowCount; i++) {

                String itemcode = (String) colItemId.getCellData(i);
                String name = (String) colItemName.getCellData(i);
                int orderQty = (int) colQty.getCellData(i);

                int onStock = itemBO.getItem(itemcode).getQtyOnHand();

                double unitPrice = (double) colUnitPrice.getCellData(i);
                ItemDTO dto = new ItemDTO(itemcode, name, unitPrice, (onStock - orderQty));

                getAllItemData.add(dto);
            }

        } catch (Exception e) {
        }
        return getAllItemData;

    }

    public void cmbCustIdOnAction(ActionEvent actionEvent) {
        String cust = cmbCustId.getValue();
        try {
            CustomerDTO customer = customerBO.getCustomer(cust);
            txtCustomerAddress.setText(customer.getAddress());
            txtCustomerName.setText(customer.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.close();
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
    }

    ArrayList<String> customerNameList;


    private void loadArryaList() {

        try {
            customerNameList = new ArrayList<>();
            ArrayList<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            for (CustomerDTO dto : allCustomer) {
                System.out.println(dto.getName());
                customerNameList.add(dto.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double fullPayment = 0.0;

    //get Total
    private double getItemTotal() {
        double total = 0;
        int qty = Integer.parseInt(txtQty.getText());
        double uPrice = Double.parseDouble(lblUPrice.getText());
        total = qty * uPrice;
        fullPayment += total;
        lblTot.setText(fullPayment + "");
        return total;
    }
}
