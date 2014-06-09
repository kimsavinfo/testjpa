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
	public void testCRUD()
	{	
		testCreate();
		testUpdate();
		testDelete();
	}
	
	private void testCreate()
	{		
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
				
				Event eventSaved = entityManager.find(Event.class, 2);
				assertEquals(event.getTitle(), eventSaved.getTitle());
				assertEquals(event.getDescription(), eventSaved.getDescription());
				assertEquals(event.getBeginDate(), eventSaved.getBeginDate());
				assertEquals(event.isAllDay(), eventSaved.isAllDay());
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
	}
	
	private void testUpdate()
	{		
		String title = "Le nouveau titre";
		Event event = entityManager.find(Event.class, 2);
		event.setTitle(title);
		
		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.persist(event);
			
			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.getTransaction().commit();
				
				Event eventSaved = entityManager.find(Event.class, 2);
				assertEquals(title, eventSaved.getTitle());
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
	}
	
	private void testDelete()
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
		Event event = new Event();
		event.setTitle("Partiels");
		event.setDescription("A vos révisions je vous prie.");
		event.setBeginDate( new GregorianCalendar(2014,07,14) );
		event.setAllDay(false);
		
		entityManager.getTransaction().begin();
		boolean transactionOk = false;
		try {
			entityManager.persist(event);
			
			event.setTitle("Soutenances");
			
			entityManager.merge(event);
			
			transactionOk = true;
		}
		finally {
			if(transactionOk) {
				entityManager.getTransaction().commit();
				
				Event eventSaved = entityManager.find(Event.class, 1);
				assertEquals(event.getTitle(), eventSaved.getTitle());
			}
			else {
				entityManager.getTransaction().rollback();
			}
		}
	}

}
