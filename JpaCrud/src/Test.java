

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Test {

	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("a");
		EntityManager em = emf.createEntityManager();
		//Employee e = new Employee();
		em.getTransaction().begin();
		Employee e = new Employee(15,"astuis",1000);
		em.persist(e);
//		Employee emp2 = (Employee) em.find(Employee.class, new Integer(1));
//		System.out.println(emp2.getId());
//		System.out.println(emp2.getName());
//		System.out.println(emp2.getSalary());
		
		

		em.getTransaction().commit();
		System.out.println("fetched");
	}
}
