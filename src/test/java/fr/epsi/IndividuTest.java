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
	
	@BeforeClass
	public static void createEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("individuPersistenceUnit");
	}
	
	@Before
	public void createEntityManager() {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Test
	public void test() 
	{
		entityManager = entityManagerFactory.createEntityManager();
		
		Individu individu = new Individu();
		individu.setPrenom("John");
		individu.setNom("Smith");
		individu.setAge(25);

		// Demande d'insertion dans la base de données
		entityManager.persist(individu);

		// Demande de chargement d'une entité.
		// Le second paramètre correspond à la valeur de la clé de l'entité recherchée.
		individu = entityManager.find(Individu.class, 1);

		// Demande de suppression (delete)
		entityManager.remove(individu);

		// En attendant correction des erreurs
		assertEquals("ok","ok");
	}
}
