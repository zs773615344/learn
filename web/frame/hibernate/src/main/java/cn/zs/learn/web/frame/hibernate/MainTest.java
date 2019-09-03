package cn.zs.learn.web.frame.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class MainTest {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        
        try {
            tx=session.beginTransaction();
//            Employee employee1 = new Employee("zhang","san",1300);
//            Employee employee2 = new Employee("li","si",2400);
//            employeeID = (Integer) session.save(employee1);
//            Event event = new Event("zhangsan",new Date());
//            Long save =  (Long) session.save(event);
            
            
            tx.commit();            
                
        }catch(HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            //  不使用的话程序不会停止
            sessionFactory.close();
            
        }
    }
}
