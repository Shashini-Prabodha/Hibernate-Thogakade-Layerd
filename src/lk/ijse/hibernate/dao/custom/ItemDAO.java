package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.SuperEntity;

import java.io.Serializable;

public interface ItemDAO extends SuperDAO<Item, String> {
    public String getLastItemID() throws Exception;
}
