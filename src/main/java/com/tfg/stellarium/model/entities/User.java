package com.tfg.stellarium.model.entities;

import javax.persistence.*;
import java.util.Objects;
/**
 * The Class User.
 */

@Entity
@Table(name = "usuario")
public class User {

	/**
	 * The Enum RoleType.
	 */
	public enum RoleType {
		/** The user. */
		ADMIN,
		CLIENT
	}

	/** The id. */
	private Long id;

	/** The user login. */
	private String login;
	
	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;
	
	/** The password. */
	private String password;

	/** The role. */
	private RoleType role;
	
	/** The email. */
	private String email;
	
	public User() {}

	public User(String login, String firstName, String lastName, String password, String email, RoleType role) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	
	
	
	public RoleType getRole() {
		return role;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(RoleType role) {
		this.role = role;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, login, password, role);
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && role == other.role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", role=" + role + ", email="
				+ email + "]";
	}


	

	
}