package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.OrderDTO;

import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public boolean saveOrder(OrderDTO dto) throws Exception;

    public boolean updateOrder(OrderDTO dto) throws Exception;

    public boolean deleteOrder(String id) throws Exception;

    public OrderDTO getOrder(String id) throws Exception;

    public ArrayList<OrderDTO> getAllOrder() throws Exception;

    public String orderID() throws Exception;

}
