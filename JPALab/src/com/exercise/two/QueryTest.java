package com.exercise.two;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QueryTest {

	public static void main(String[] args) {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("kum");
		
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		
	    BookTable b1 = new BookTable(123, "Faster Fene",50);
	    BookTable b2 = new BookTable(124, "harry potter",1000);
	    BookTable b3 = new BookTable(125, "Fasteasfr Fene",53240);
	    
	    em.persist(b1);
	    em.persist(b2);
	    em.persist(b3);
	    
	    AuthorTable a1 = new AuthorTable(b1, "b.r.bhagwat");
	    AuthorTable a2 = new AuthorTable(b2, "b.r.bhagwat");
		
	    em.getTransaction().commit();
	    
	    
	    
	}
}
