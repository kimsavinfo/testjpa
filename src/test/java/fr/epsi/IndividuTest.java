package fr.epsi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IndividuTest extends JpaTestCase 
{
	private static EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	@BeforeClass
	public static void createEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("individuPersistenceUnit");
	}

	@AfterClass
	public static void closeEntityManagerFactory() {
		entityManagerFactory.close();
	}

	@Before
	public void createEntityManager() {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void closeEntityManager() {
		entityManager.close();
	}
	
	@Test
	public void testCreates() 
	{
		entityManager.getTransaction().begin();
		
		Individu individu = new Individu();
		
		individu.setNom("Anonymous");
		individu.setPrenom("Toto");
		individu.setAge(20);
		
		entityManager.persist(individu);
		
		individu = entityManager.find(Individu.class, 1);
		
		
		
		entityManager.remove(individu);
	}
}
