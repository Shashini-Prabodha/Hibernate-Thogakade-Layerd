package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.Orders;

public interface OrderDAO extends SuperDAO<Orders, String> {
    public String getLastOrderID() throws Exception;
}
