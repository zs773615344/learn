package cn.zs.learn.web.frame.hibernate;

import java.util.List;

import cn.zs.learn.web.frame.hibernate.xmlMapper.pojo.Employee;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class Sql {
    
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String sql = "select * from employee";
//            SQLQuery query = session.createSQLQuery(sql);
//            query.addEntity(Employee.class);
//            List<Employee> list = query.list();
//            for (Employee per:list) {
//                System.out.println(per.toString());
//            }
            
            String sql1 = "select first_name,salary from employee";
//            SQLQuery query = session.createSQLQuery(sql1);
//            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//            List list = query.list();
//            System.out.println(list);
            
            String sql2 = "select * from employee where id = :id";
            SQLQuery query = session.createSQLQuery(sql2);
            query.addEntity(Employee.class);
            query.setParameter("id", 1);
            List list = query.list();
            System.out.println(list);
            
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    
}
