package fr.epsi;

import static org.junit.Assert.assertEquals;

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
	public void test() 
	{
		assertEquals("ok","ok");
		
		entityManager.getTransaction().begin();

		Individu individu = new Individu();
		individu.setNom("Anonymous");
		individu.setPrenom("Toto");
		individu.setAge(20);

		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.persist(individu);

			individu.setAge(40);

			entityManager.merge(individu);

			entityManager.remove(individu);

			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.getTransaction().commit();
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
	}
}
