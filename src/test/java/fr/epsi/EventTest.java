package fr.epsi;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventTest extends JpaTestCase 
{
	private static EntityManagerFactory entityManagerFactory;

	@BeforeClass
	public static void createEntityManagerFactory() 
	{
		entityManagerFactory = Persistence.createEntityManagerFactory("EpsiPersistenceUnit");
	}

	
	@Test
	public void createEvent()
	{
		
	/*	entityManagerFactory = Persistence.createEntityManagerFactory("EpsiPersistenceUnit");
		entityManager = entityManagerFactory.createEntityManager();
		
		Event event = new Event();
		event.setTitle("Partiels");
		event.setDescription("A vos révisions je vous prie.");
		event.setBeginDate( new GregorianCalendar(2014,07,14) );
		event.setAllDay(false);
		
		
		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.persist(event);
			
			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.getTransaction().commit();
				
				Event eventSaved = entityManager.find(Event.class, 1);
				assertEquals(event.getTitle(), eventSaved.getTitle());
				assertEquals(event.getDescription(), eventSaved.getDescription());
				assertEquals(event.getBeginDate(), eventSaved.getBeginDate());
				assertEquals(event.isAllDay(), eventSaved.isAllDay());
				
				entityManager.close();
				entityManagerFactory.close();
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
		*/
	}
	


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Création EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EpsiPersistenceUnit"); 

		// ********************** Insertion de 2 evenements dans la table		
		// Création EntityManager et Transaction		
		EntityManager em = emf.createEntityManager();   
		EntityTransaction transac = em.getTransaction();
		transac.begin();
		//	    Création évenement 1
		Event e1 = new Event();
		e1.setTitle("Titre de l'event 1");
		e1.setDescription("Description de l'évènement 1");
		e1.setBeginDate(new GregorianCalendar());
		e1.setAllDay(false);
		//		 Création évenement 2
		Event e2 = new Event();
		e2.setTitle("Titre de l'event 2");
		e2.setDescription("Description de l'évènement 2");
		e2.setBeginDate(new GregorianCalendar());
		e2.setAllDay(true);
		// Stockage des évenements dans la table
		em.persist(e1);
		em.persist(e2);
		transac.commit();
		// Fermeture EntityManager	       
		em.close(); 

		// ************ Lecture dans la table

		EntityManager em2 = emf.createEntityManager();   
		EntityTransaction transac2 = em2.getTransaction();
		transac2.begin();
		// Récupération de l'evenement d'Id = 2 et affichage dans la console pour vérification
		Event ev = em2.find(Event.class, 2);
		System.out.println("Event : [id] = " + ev.getId() + "\t" +
				"[title] = " + ev.getTitle() + "\t" +
				"[desc] = " + ev.getDescription() + "\t" + 
				"[date] = " + ev.getBeginDate().getTime().toLocaleString() + "\t" + 
				"[allDay] = " + ev.isAllDay());

		transac2.commit();
		em2.close(); 

		emf.close();

	}
	
	

}
