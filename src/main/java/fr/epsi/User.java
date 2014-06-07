package fr.epsi;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="users_id")
	private Integer id;
	@Column(name="users_login")
	private String login;
	@Column(name="users_email")
	private String email;
	@Column(name="users_pass")
	private String pass;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Calendar updateDate;

	public User() {
	}

	public User(String login, String email, String pass) {
		this.login = login;
		this.email = email;
		this.pass = pass;
	}

	public boolean equals(User  u){
		boolean returnValue = true;

		if ((u != null) && (this.login != null) 
				&& (this.pass != null) && (this.id != null) ){
			if ((!this.login.equals(u.getLogin()))
					|| (!this.pass.equals(u.getPass()))
					|| (!this.id.equals(u.getId()))){
				returnValue = false;
			}
		}
		else
			returnValue = false;

		return returnValue;	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Calendar getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}

}