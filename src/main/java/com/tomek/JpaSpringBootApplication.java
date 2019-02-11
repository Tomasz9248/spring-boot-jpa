package com.tomek;

import com.tomek.dao.UserDao;
import com.tomek.dao.UserDetailsDao;
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
        UserDetails userDetails = new UserDetails("John", "Kowalski", "Krakowska 55, Warszawa");
        user.setDetails(userDetails);
        userDao.save(user);

        // relationship is two way now so its possible to get User data from UserDetails as well
        UserDetailsDao userDetailsDao = ctx.getBean(UserDetailsDao.class);
        UserDetails getUserDetails = userDetailsDao.get(1L);
        System.out.println(getUserDetails.getUser());

        ctx.close();
    }
}

