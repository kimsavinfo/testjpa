package fr.epsi;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Abonnement")
public class Abonnement 
{

	@Id
	@Column(name="abonnementId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Basic
	@Column(length = 30, nullable=false)
	private String nom;

}
