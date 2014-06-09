package fr.epsi;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity
@Table(name="events_01")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="events_id")
		private Integer id;
		@Column(name="events_title", nullable=false)
		private String title;
		@Column(name="events_description")
		private String description;
		@Column(name="events_beginDate")
		@Temporal(TemporalType.DATE)
		private Calendar beginDate;
		@Column(name="events_allDay")
		private boolean allDay;


		public Event() {
			super();
		}
		// Getters et setters

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Calendar getBeginDate() {
			return beginDate;
		}
		public void setBeginDate(Calendar beginDate) {
			this.beginDate = beginDate;
		}
		public boolean isAllDay() {
			return allDay;
		}
		public void setAllDay(boolean allDay) {
			this.allDay = allDay;
		}

}

