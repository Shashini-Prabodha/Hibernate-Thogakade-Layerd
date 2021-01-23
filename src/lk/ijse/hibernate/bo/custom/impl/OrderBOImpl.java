package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.OrderBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.OrderDAO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO= DAOFactory.getInstance().getDAO(DAOType.ORDER);

    @Override
    public boolean saveOrder(OrderDTO dto) throws Exception {
        return orderDAO.save(new Orders(dto.getOrderId(),dto.getCustId(),dto.getDate()));
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws Exception {
      return orderDAO.update(new Orders(dto.getOrderId(),dto.getCustId(),dto.getDate()));

    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        return orderDAO.delete(id);
    }

    @Override
    public OrderDTO getOrder(String id) throws Exception {
        Orders orders = orderDAO.get(id);
        return new OrderDTO(orders.getOrderId(),orders.getCustId(),orders.getDate());
    }

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws Exception {
        List<Orders> all = orderDAO.getAll();
        ArrayList<OrderDTO> list = new ArrayList<>();
        for (OrderDTO dto : list) {
            list.add(new OrderDTO(dto.getOrderId(),
                    dto.getCustId(),
                    dto.getDate())
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
