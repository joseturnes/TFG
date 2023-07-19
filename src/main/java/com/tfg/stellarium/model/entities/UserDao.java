package com.tfg.stellarium.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The Interface UserDao.
 */
public interface UserDao extends JpaRepository<User, Long> {

	boolean existsByLogin(String login);

	Optional<User> findByLogin(String login);

}
