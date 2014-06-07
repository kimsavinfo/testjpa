package fr.epsi;

import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="Individu")
public class Individu {

	@Id
	@Column(name="individuId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Basic
	@Column(length = 30, nullable=false)
	private String nom;

	@Basic
	@Column(length = 30, nullable=false)
	private String prenom;

	@Column(length = 3, nullable=false)
	private Integer age;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Calendar dateAdhesion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	private Calendar dateModification;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] image;

	@Transient
	private Abonnement abonnement;
	
	public Individu()
	{
		
	}
	
	public Individu(String p_nom, String p_prenom, int p_age)
	{
		nom = p_nom;
		prenom = p_prenom;
		age = p_age;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String p_nom) {
		nom = p_nom;
	}
	
	public String getPreom() {
		return prenom;
	}

	public void setPrenom(String p_prenom) {
		prenom = p_prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int p_age) {
		age = p_age;
	}

}
