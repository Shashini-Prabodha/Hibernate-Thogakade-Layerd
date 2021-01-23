package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.CustomerDAO;
import lk.ijse.hibernate.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO= DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.save(new Customer(dto.getcustId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.update(new Customer(dto.getcustId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws Exception {
        Customer customer=customerDAO.get(id);
        return new CustomerDTO(customer.getCustId(),customer.getName(),customer.getAddress());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        List<Customer> list = customerDAO.getAll();
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();
        for (Customer customer : list) {
            arrayList.add(new CustomerDTO(customer.getCustId(),customer.getName(),customer.getAddress()));
        }
        return arrayList;
    }

    @Override
    public String newCustomerID() throws Exception {

        String lastID = customerDAO.getLastCustomerID();
        System.out.println(lastID);
        if (lastID == null) {
            return "C001";
        } else {
            int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;
            if (newID < 10) {
                return "C00" + newID;
            } else if (newID < 100) {
                return "C0" + newID;
            } else {
                return "C" + newID;
            }
        }

    }
}
