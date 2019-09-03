package cn.zs.learn.web.frame.hibernate;

import java.util.List;

import cn.zs.learn.web.frame.hibernate.annotationMapper.pojo.AnnoEmployee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Hql {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM AnnoEmployee";
//            List<AnnoEmployee> list = session.createQuery(hql).list();
            
            String hql1 = "FROM AnnoEmployee  E WHERE E.id = ?";
            Query query = session.createQuery(hql1);
            query.setParameter(0, 1);
            
            String hql2 = "FROM AnnoEmployee  E WHERE E.id = :id";
//            Query query = session.createQuery(hql2);
//            query.setParameter("id", 3);
            
            List<AnnoEmployee> list = query.list();
            
            String hql3 = "FROM AnnoEmployee  E order by E.id desc";
            String hql4 = "FROM AnnoEmployee  E group by E.salary";
//            List<EmployeeCopy> list = session.createQuery(hql4).list();
            
                        // 分页查询
//            Query query = session.createQuery("From AnnoEmployee  ");
//            query.setFirstResult(3);
//            query.setMaxResults(4);
//            List<EmployeeCopy> list = query.list();
            
            for (AnnoEmployee per : list) {
                System.out.println(per.toString());
                
            }
            
            // 聚集函数
            String hql5 = "SELECT count(distinct E.firstName) FROM AnnoEmployee  E";
            String hql6 = "SELECT count(*) FROM AnnoEmployee  E";
            String hql7 = "SELECT max(E.id) FROM AnnoEmployee  E";
            String hql8 = "SELECT avg(E.id) FROM AnnoEmployee  E";
//            Query query = session.createQuery(hql5);
//            List result = query.list();
//            System.out.println(result.get(0));
            
            String hql9 = "DELETE FROM AnnoEmployee   AS E WHERE E.id=:id";
//            Query query = session.createQuery(hql9);
//            query.setParameter("id", 8);
//            int delete = query.executeUpdate();
//            System.out.println(delete);
            
            String hql10 = "UPDATE AnnoEmployee  E set salary = :salary "
                    + "WHERE id = :employee_id";
//            Query query = session.createQuery(hql10);
//            query.setParameter("salary", 1000);
//            query.setParameter("employee_id", 1);
//            int update = query.executeUpdate();
//            System.out.println(update);
            
            // HQL 只有当记录从一个对象插入到另一个对象时才支持 INSERT INTO 语句
            // String hql11 = "INSERT INTO Employee(firstName, lastName, salary)" +
            // "SELECT firstName, lastName, salary FROM old_employee";
            
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
