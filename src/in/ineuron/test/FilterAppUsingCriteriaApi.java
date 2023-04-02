package in.ineuron.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.ineuron.model.Accounts;
import in.ineuron.util.HibernateUtil;

public class FilterAppUsingCriteriaApi {

	public static void main(String[] args) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();
			Filter filter = session.enableFilter("get_active_acc");
			filter.setParameter("status1", "blocked");
			filter.setParameter("status2", "closed");

			Criteria criteria = session.createCriteria(Accounts.class);

			List<Accounts> list = criteria.list();

			list.forEach(System.out::println);

			session.disableFilter("get_active_acc");

			System.out.println("__________________________");

			Criteria criteria2 = session.createCriteria(Accounts.class);

			List<Accounts> list2 = criteria2.list();

			list2.forEach(System.out::println);

		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

}
