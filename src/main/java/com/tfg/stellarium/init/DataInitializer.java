package com.tfg.stellarium.init;

import com.tfg.stellarium.model.entities.User;
import com.tfg.stellarium.model.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User usuarioBase = new User();
        usuarioBase.setLogin("admin");
        usuarioBase.setPassword(passwordEncoder.encode("admin"));
        usuarioBase.setFirstName("John");
        usuarioBase.setLastName("Doe");
        usuarioBase.setRole(User.RoleType.ADMIN);
        usuarioBase.setEmail("john.doe@example.com");

        if(!userDao.existsByLogin("admin")){
            userDao.save(usuarioBase);
        }
        }
}
