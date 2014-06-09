package fr.epsi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class JpaCaseTest {

	private static EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	@BeforeClass
	public static void createEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
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

}
