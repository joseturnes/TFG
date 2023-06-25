package com.tfg.stellarium.model.services;

import com.tfg.stellarium.model.common.exceptions.DuplicateInstanceException;
import com.tfg.stellarium.model.common.exceptions.InstanceNotFoundException;
import com.tfg.stellarium.model.common.exceptions.NotEnoughBalanceException;
import com.tfg.stellarium.model.entities.User;
import com.tfg.stellarium.model.services.exceptions.IncorrectLoginException;
import com.tfg.stellarium.model.services.exceptions.IncorrectPasswordException;

/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Sign up.
	 *
	 * @param user the user
	 * @throws DuplicateInstanceException the duplicate instance exception
	 */
	void signUp(User user) throws DuplicateInstanceException;
	
	/**
	 * Login.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the user
	 * @throws IncorrectLoginException the incorrect login exception
	 */
	User login(String userName, String password) throws IncorrectLoginException;
	
	/**
	 * Login from id.
	 *
	 * @param id the id
	 * @return the user
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	User loginFromId(Long id) throws InstanceNotFoundException;
	
	/**
	 * Update profile.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @return the user
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	User updateProfile(Long id, String firstName, String lastName, String email) throws InstanceNotFoundException;
	
	/**
	 * Change password.
	 *
	 * @param id the id
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @throws InstanceNotFoundException the instance not found exception
	 * @throws IncorrectPasswordException the incorrect password exception
	 */
	void changePassword(Long id, String oldPassword, String newPassword)
		throws InstanceNotFoundException, IncorrectPasswordException;

}
