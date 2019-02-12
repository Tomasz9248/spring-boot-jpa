package com.tomek;

import com.tomek.dao.ClientDao;
import com.tomek.model.Client;
import com.tomek.model.Order;
import com.tomek.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);

        // To remove data add CascadeType.REMOVE in entity
        // set orphanRemoval attribute 'true' to remove data that were connected with deleted objects
        Client client = new Client("Jan", "Kowalski", "Krakowskie przedmieście 23, Warszawa");
        Order order = new Order("z dostawą do domu");
        Product product1 = new Product("Telewizor LG 42'", 4800.0, "dolby surround");
        Product product2 = new Product("Telefon Apple iPhone SE", 2200.0, "pokrowiec gratis");
        order.getProducts().add(product1);
        order.getProducts().add(product2);
        client.addOrder(order);

        ClientDao clientDao = ctx.getBean(ClientDao.class);
        clientDao.save(client);

        clientDao.remove(client);

        ctx.close();
    }
}