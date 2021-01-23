package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class DashBoardController {
    public JFXButton btnCustomer;
    public JFXButton btnItem;
    public JFXButton btnOrder;
    public JFXButton btnAllView;

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        loadUI("CustomerView");
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        loadUI("ItemForm");
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {
        loadUI("CashierForm");
    }

    public void btnAllViewOnAction(ActionEvent actionEvent) {
    }
    public void loadUI(String location) {
        try {
            URL resource = this.getClass().getResource("/lk/ijse/hibernate/view/" + location + ".fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
