

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
public static void main(String[] args) {
EntityManagerFactory factory = Persistence.createEntityManagerFactory("a");
EntityManager em = factory.createEntityManager();
em.getTransaction().begin();

Address ad1 = new Address();
ad1.setHno(123);
ad1.setColony("lbnagar");
ad1.setCity("hyd");
em.persist(ad1);

Employee e = new Employee();
e.setEid(1);
e.setEname("suresh");
e.setEsal(20000);
e.setAdd(ad1);
em.persist(e);

em.getTransaction().commit();
em.close();
factory.close();
}
}
