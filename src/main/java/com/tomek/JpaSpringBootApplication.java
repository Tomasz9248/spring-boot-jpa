package com.tomek;

import com.tomek.dao.ClientDao;
import com.tomek.model.Client;
import com.tomek.model.Order;
import com.tomek.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);

        // to achieve setting up relationships and after saving one object in db that contains all relationships use 'cascade' attribute in relationship type annotation
        // CascadeType.PERSIST/MERGE/REMOVE/REFRESH/DETACH/ALL - each represent one entityManager's method
        Client client = new Client("Sherlock", "Holmes", "Baker Street 221b London");
        Order order = new Order("z dostawą do domu");
        Product product1 = new Product("Telewizor LG 42'", 4800.0, "dolby surround");
        Product product2 = new Product("Telefon Apple iPhone SE", 2200.0, "pokrowiec gratis");
        order.getProducts().add(product1);
        order.getProducts().add(product2);
        client.addOrder(order);

        ClientDao clientDao = ctx.getBean(ClientDao.class);
        clientDao.save(client);

        Client getClient = clientDao.get(client.getId());
        System.out.println(getClient);

        ctx.close();
    }
}