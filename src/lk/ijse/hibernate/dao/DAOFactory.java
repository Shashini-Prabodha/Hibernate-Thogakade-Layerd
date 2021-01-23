package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.ItemDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.OrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
 public <T extends SuperDAO> T getDAO(DAOType daoType){
     switch (daoType){
         case CUSTOMER:
             return (T) new CustomerDAOImpl();
         case ITEM:
             return (T) new ItemDAOImpl();
         case ORDER:
             return (T) new OrderDAOImpl();
         default:
             return null;
     }

 }


}
