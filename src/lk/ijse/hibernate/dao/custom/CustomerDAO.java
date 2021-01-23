package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.SuperEntity;

import java.io.Serializable;

public interface CustomerDAO extends SuperDAO<Customer, String> {
    public String getLastCustomerID()throws Exception;

}
