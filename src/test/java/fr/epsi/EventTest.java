package fr.epsi;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventTest extends JpaTestCase
{

	@Test
	public void testCreate()
	{		
		Event event = new Event();
		event.setTitle("Partiels");
		event.setDescription("A vos révisions je vous prie.");
		event.setBeginDate( new GregorianCalendar(2014,07,14) );
		event.setAllDay(false);
		enregistrerEvent(event);

		Event eventSaved = entityManager.find(Event.class,event.getId());
		assertEquals(event.getTitle(), eventSaved.getTitle());
		assertEquals(event.getDescription(), eventSaved.getDescription());
		assertEquals(event.getBeginDate(), eventSaved.getBeginDate());
		assertEquals(event.isAllDay(), eventSaved.isAllDay());



	}

	@Test
	public void testUpdate()
	{		
		String title = "Le nouveau titre";

		Event event = new Event();
		event.setTitle("titre original");
		enregistrerEvent(event);

		Event eventSaved = entityManager.find(Event.class,event.getId());
		eventSaved.setTitle(title);
		enregistrerEvent(eventSaved);


	}

	@Test
	public void testDelete()
	{		
		Event event = entityManager.find(Event.class, 2);

		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.persist(event);

			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.remove(event);

				entityManager.getTransaction().commit();

				assertNull(entityManager.find(Event.class, 2));
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
	}

	@Test
	public void testMerge()
	{		
		Event sameEvent = new Event();
		Event event = new Event();
		event.setTitle("Partiels");
		event.setDescription("A vos révisions je vous prie.");
		event.setBeginDate( new GregorianCalendar(2014,07,14) );
		event.setAllDay(false);
	


		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.persist(event);

		
			sameEvent.setId(event.getId());
			sameEvent.setTitle("Soutenances");
			sameEvent = entityManager.merge(sameEvent);

			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.getTransaction().commit();

				assertEquals("Soutenances", sameEvent.getTitle());
				assertEquals(event.getDescription(), sameEvent.getDescription());
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
	}

	@Test
	public void testDetached()
	{		
		Event event = entityManager.find(Event.class, 1);
		String oldTtle = event.getTitle();
		String newTitle = "Soutenances 1";

		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.detach(event);
			event.setTitle(newTitle);

			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.getTransaction().commit();

				Event eventSaved = entityManager.find(Event.class, 1);
				assertEquals(oldTtle, eventSaved.getTitle());
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
	}

	@Test
	public void testRefresh()
	{		
		Event event = new Event();
		event.setTitle("tritre original");
		enregistrerEvent(event);
		
		Event savedEvent = entityManager.find(Event.class, event.getId());
		String oldTtle = event.getTitle();
		String newTitle = "Soutenances 2";

		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			savedEvent.setTitle(newTitle);
			entityManager.refresh(savedEvent);

			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.getTransaction().commit();

				Event eventSaved = entityManager.find(Event.class,savedEvent.getId());
				assertEquals(oldTtle, eventSaved.getTitle());
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}

	}

	private void enregistrerEvent(Event event){
		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.persist(event);

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
