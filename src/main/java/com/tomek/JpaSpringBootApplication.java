package com.tomek;

import com.tomek.dao.UserDao;
import com.tomek.model.User;
import com.tomek.model.UserDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);

        UserDao userDao = ctx.getBean(UserDao.class);
        User user = new User("johnny234", "strongPass", "johnny@gmail.com");
        // save User object without details
        userDao.save(user);

        //add details
        UserDetails details = new UserDetails("John", "Kowalski", "Krakowska 55, Warszawa");
        user.setDetails(details);
        userDao.update(user);

        //update password and name
        user.setPassword("passPass");
        user.getDetails().setFirstName("Piotr");
        userDao.update(user);

        //get status and print
        User userFromDb = userDao.get(1L);
        System.out.println(userFromDb);

        //remove object
        userDao.delete(user);

        //get status again
        userFromDb = userDao.get(1L);
        System.out.println(userFromDb);

        ctx.close();
    }
}

