package cn.zs.learn.web.frame.hibernate;

import cn.zs.learn.web.frame.hibernate.xmlMapper.pojo.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class BatchProcess {
    public static void main(String[] args) {
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;        
        try {
            transaction=session.beginTransaction();
            
            for(int i=0;i<100000;i++) {
                Employee employee = new Employee("fname"+i, "lname"+i, 1+i);
                session.save(employee);
            }
            
//            for(int i=0;i<100000;i++) {
//                Employee employee = new Employee("fname"+i, "lname"+i, 1+i);
//                session.save(employee);
//                if(i%50 == 0) {
//                    session.flush();
//                    session.clear();
//                }
//            }          
            
            transaction.commit();            
                
        }catch(HibernateException e) {
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            //  不使用的话程序不会停止
            sessionFactory.close();
            
        }
    }
}
