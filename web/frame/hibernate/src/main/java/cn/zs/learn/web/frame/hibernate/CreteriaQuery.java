package cn.zs.learn.web.frame.hibernate;

import java.util.List;

import cn.zs.learn.web.frame.hibernate.xmlMapper.pojo.Employee;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


/*
 * Criteria：標準，準則
 * Restriction：限制，拘束
*/
public class CreteriaQuery {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
       
            Criteria criteria = session.createCriteria(Employee.class);
//            List list = criteria.list();
//            System.out.println(list);

                        //  可添加限制，如eq,gt,lt,like,ilike,between,isNull,isNotNull,isEmpty
//            criteria.add(Restrictions.eq("id", 1));
            
//            criteria.add(Restrictions.like("firstName", "zara%"));
            
//            criteria.add(Restrictions.between("salary", 1000, 10000));
            
                        // 可組合使用
            Criterion gt = Restrictions.gt("salary", 2000);
            Criterion like = Restrictions.like("firstName", "zara%");
            LogicalExpression or = Restrictions.or(gt,like);
            LogicalExpression and = Restrictions.and(gt,like);
            
//            criteria.add(or);
//            criteria.add(and);
            
                    // 排序
//           criteria.addOrder(Order.desc("salary"));
      
                    //預測與聚合 rowCount,avg,countDistinct,max,min,sum
//           criteria.setProjection(Projections.rowCount());
//           criteria.setProjection(Projections.avg("salary"));
           
           
           System.out.println(criteria.list());
            
           
           
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
