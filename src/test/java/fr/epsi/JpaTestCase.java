package fr.epsi;

import fr.epsi.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;

public abstract class JpaTestCase 
{
	private static EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	private void testCreate()
	{
		// Créer user en local
		User user = new User();
		user.setLogin("toto");
		user.setPass("P@ssword");
		user.setEmail("toto.mail@gmail.com");
		
		// Demander insertion dans la base de données
		entityManager.persist(user);
		
		// Charger
		user = entityManager.find(User.class, 1);
		
		assertEquals( "toto", user.getLogin() );
	}
}
