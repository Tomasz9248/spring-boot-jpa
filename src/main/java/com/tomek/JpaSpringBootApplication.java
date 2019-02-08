package com.tomek;

import com.tomek.dao.BookDao;
import com.tomek.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//mark it as SpringBootApp
@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);
        // rest of the code doesnt change
        // there is no JpaConfig class. All setup (database url, username, password, initial size etc.) is made in application.properties file
        // SpringBoot does everything automatically
        BookDao bookDao = ctx.getBean(BookDao.class);
        // save book to database
        Book book = new Book("2323232323", "Michael Jordan. The Life", "Roland Lazenby");
        bookDao.save(book);
        // read book from database based on id
        Book bookGet = bookDao.get(1L);
        System.out.println(bookGet);
        ctx.close(); // close context
    }
}

/* Thats how the starting class would look like without spring boot
It should be marked with @EnableTransactionManager and create @Bean PlatformTransactionManager

@Configuration
@EnableTransactionManagement
public class JpaConfig {
//...

    @Bean
    public PlatformTransactionManager createTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager(emf);
        return txManager;
    }
}
*/

