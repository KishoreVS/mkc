import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();

		Session session = factory.openSession();

//		Transaction tran = session.beginTransaction();
//
//		Employee emp = new Employee(1234567, "satish");
//  
//		session.save(emp);
//		tran.commit();
		Employee emp2 = (Employee) session.get(Employee.class, new Integer(1234567));
		System.out.println(emp2);
		System.out.println(emp2.getEid());
		System.out.println(emp2.getEname());
		// if (tran.getStatus().equals(TransactionStatus.ACTIVE))
		
		session.close();
		factory.close();
		// tran.commit();

	}
}
