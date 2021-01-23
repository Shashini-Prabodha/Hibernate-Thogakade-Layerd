package lk.ijse.hibernate.util;

import lk.ijse.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
   private static FactoryConfiguration factoryConfiguration;
   private SessionFactory sessionFactory;

   private FactoryConfiguration(){

/*       Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class)
               .addAnnotatedClass(Item.class)
              .addAnnotatedClass(Orders.class);
//       addAnnotatedClass(OrderDetails.class);
       sessionFactory=configuration.buildSessionFactory();*/


       Properties properties = new Properties();

       try {
           properties.load(new FileInputStream("D:\\sem 2\\Hibernate\\Hibernate_testing\\src\\hibernate.properties"));
       } catch (IOException e) {
           e.printStackTrace();
       }


       Configuration configuration = new Configuration();

       configuration.configure("hibernate.cfg.xml").addProperties(properties).addAnnotatedClass(Customer.class)
               .addAnnotatedClass(Item.class)
               .addAnnotatedClass(Orders.class);

       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
               .applySettings(configuration.getProperties()).build();

       sessionFactory = configuration.buildSessionFactory(serviceRegistry);
   }
   public static FactoryConfiguration getInstance(){
       return (factoryConfiguration==null)?factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
   }
   public Session getSession(){
       return sessionFactory.openSession();
   }

}
