package in.ineuron.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import in.ineuron.model.Accounts;
import in.ineuron.util.HibernateUtil;

public class FilterAppUsingNativeQuery {

	public static void main(String[] args) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();

			// Filters are not supported by Native Query becoz it needs DB specific query
			// and it won't refer entity class
			Filter filter = session.enableFilter("get_active_acc");
			filter.setParameter("status1", "blocked");
			filter.setParameter("status2", "closed");

			NativeQuery nativeQuery = session.createSQLQuery("SELECT * FROM accounts");

			nativeQuery.addEntity(Accounts.class);

			List<Accounts> list = nativeQuery.getResultList();

			list.forEach(System.out::println);

			session.disableFilter("get_active_acc");

			System.out.println("______________________________");

			NativeQuery nativeQuery2 = session.createSQLQuery("SELECT * FROM accounts");

			nativeQuery2.addEntity(Accounts.class);

			List list2 = nativeQuery2.getResultList();

			list2.forEach(System.out::println);

		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

}
