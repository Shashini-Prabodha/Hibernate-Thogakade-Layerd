package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer(CustomerDTO dto) throws Exception;

    public boolean updateCustomer(CustomerDTO dto) throws Exception;

    public boolean deleteCustomer(String id) throws Exception;

    public CustomerDTO getCustomer(String id) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomer() throws Exception;

    public String newCustomerID() throws Exception;
}
