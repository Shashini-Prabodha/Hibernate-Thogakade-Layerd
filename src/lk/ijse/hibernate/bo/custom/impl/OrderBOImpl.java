package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.OrderBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.OrderDAO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER);

    @Override
    public boolean saveOrder(OrderDTO dto) throws Exception {
        CustomerDTO customer = dto.getCustomer();
        Customer cust = new Customer(customer.getcustId(), customer.getName(), customer.getAddress());
        List<ItemDTO> items = dto.getItems();
        ArrayList<Item> list=new ArrayList<>();
        for (ItemDTO item : items) {
            list.add(new Item(item.getCode(),item.getDescription(),item.getPrice(),item.getQtyOnHand()));
        }
        return orderDAO.save(new Orders(dto.getOrderId(), dto.getDate(), cust,list));
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws Exception {
        CustomerDTO customer = dto.getCustomer();
        Customer cust = new Customer(customer.getcustId(), customer.getName(), customer.getAddress());
        return orderDAO.update(new Orders(dto.getOrderId(), dto.getDate(), cust));

    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDTO getOrder(String id) throws Exception {
        Orders orders = orderDAO.get(id);
        Customer customer = orders.getCustomer();
        CustomerDTO customerDTO = new CustomerDTO(customer.getCustId(), customer.getName(), customer.getAddress());
        return new OrderDTO(orders.getOrderId(), orders.getDate(), customerDTO);
    }

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws Exception {
        List<Orders> all = orderDAO.getAll();
        ArrayList<OrderDTO> list = new ArrayList<>();

        for (Orders ord : all) {
            Customer customer = ord.getCustomer();
            CustomerDTO customerDTO = new CustomerDTO(customer.getCustId(), customer.getName(), customer.getAddress());
            list.add(new OrderDTO(ord.getOrderId(),
                    ord.getDate(),
                    customerDTO)
            );
        }
        return list;
    }

    @Override
    public String orderID() throws Exception {
        String lastID = orderDAO.getLastOrderID();
        System.out.println(lastID);
        if (lastID == null) {
            return "R001";
        } else {
            int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;
            if (newID < 10) {
                return "R00" + newID;
            } else if (newID < 100) {
                return "R0" + newID;
            } else {
                return "R" + newID;
            }
        }
    }
}
