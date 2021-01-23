package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ItemDTO;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public boolean saveItem(ItemDTO dto) throws Exception;

    public boolean updateItem(ItemDTO dto) throws Exception;

    public boolean deleteItem(String id) throws Exception;

    public ItemDTO getItem(String id) throws Exception;

    public ArrayList<ItemDTO> getAllItem() throws Exception;

    public String newItemID() throws Exception;

}
