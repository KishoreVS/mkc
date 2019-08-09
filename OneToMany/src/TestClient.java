
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestClient {
public static void main(String[] args) {

EntityManagerFactory factory = Persistence
.createEntityManagerFactory("a");
EntityManager em = factory.createEntityManager();
em.getTransaction().begin();

Vendor v=new Vendor();
v.setVendorId(100);
v.setVendorName("alchemy");

Customers c1=new Customers();
c1.setCustomerId(500);
c1.setCustomerName("bvrit");

Customers c2=new Customers();
c2.setCustomerId(501);
c2.setCustomerName("capgemini");

Set s=new HashSet();
s.add(c1);
s.add(c2);

v.setChildren(s);

em.persist(v);


em.getTransaction().commit();
em.close();
factory.close();


}
}

