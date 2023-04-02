package in.ineuron.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.ineuron.model.Accounts;
import in.ineuron.util.HibernateUtil;

public class FilterAppUsingHQL {

	public static void main(String[] args) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();

			Filter filter = session.enableFilter("get_active_acc");

			filter.setParameter("status1", "blocked");
			filter.setParameter("status2", "closed");

			Query<Accounts> query = session.createQuery("FROM in.ineuron.model.Accounts");

			List<Accounts> list = query.getResultList();

			list.forEach(System.out::println);
			
			session.disableFilter("get_active_acc");
			System.out.println("---------------------------------------");

			Query<Accounts> query2 = session.createQuery("FROM in.ineuron.model.Accounts");

			List<Accounts> list2 = query.getResultList();

			list2.forEach(System.out::println);
			
			

		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

}
