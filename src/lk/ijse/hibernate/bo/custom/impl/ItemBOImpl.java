package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ItemBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO= DAOFactory.getInstance().getDAO(DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws Exception {
        return itemDAO.save(new Item(dto.getCode(),dto.getDescription(),dto.getPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws Exception {
        return itemDAO.update(new Item(dto.getCode(),dto.getDescription(),dto.getPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO getItem(String id) throws Exception {
        Item item = itemDAO.get(id);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getPrice(),item.getQtyOnHand());
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws Exception {
        List<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> list = new ArrayList<>();
        for (Item item : all) {
            list.add(new ItemDTO(item.getCode(),item.getDescription(),item.getPrice(),item.getQtyOnHand()));
        }
        return list;
    }

    @Override
    public String newItemID() throws Exception {
        String lastID =itemDAO.getLastItemID();
        System.out.println(lastID);
        if (lastID == null) {
            return "I001";
        } else {
            int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;
            if (newID < 10) {
                return "I00" + newID;
            } else if (newID < 100) {
                return "I0" + newID;
            } else {
                return "I" + newID;
            }
        }
    }


}
