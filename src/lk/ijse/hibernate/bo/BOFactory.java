package lk.ijse.hibernate.bo;

import lk.ijse.hibernate.bo.custom.impl.CustomerBOImpl;
import lk.ijse.hibernate.bo.custom.impl.ItemBOImpl;
import lk.ijse.hibernate.bo.custom.impl.OrderBOImpl;
import lk.ijse.hibernate.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.ItemDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.OrderDAOImpl;

public class BOFactory {
    private static BOFactory daoFactory;

    private BOFactory(){

    }
    public static BOFactory getInstance(){
        return (daoFactory == null) ? daoFactory = new BOFactory() : daoFactory;
    }
    public <T extends SuperBO> T getBO(BOType daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            default:
                return null;
        }

    }
}
