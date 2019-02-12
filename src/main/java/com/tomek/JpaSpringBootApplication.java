package com.tomek;

import com.tomek.dao.ClientDao;
import com.tomek.dao.OrderDao;
import com.tomek.dao.ProductDao;
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
        /*
        The main difference between the two types of fetching is a moment when data gets loaded into a memory.
        One Client can have multiple Orders.
        Eager loading strategy - if we load the User data, it will also load up all orders associated with it and will store it in a memory.
        Lazy loading - if we pull up a UserLazy, OrderDetail data won’t be initialized and loaded into a memory until an explicit call is made to it.
        @OneToOne and @ManyToOne default loading is EAGER
        @OneToMany and @ManyToMany default loading is LAZY
        To change loading strategy use (fetch = FetchTYPE.LAZY/EAGER) [check entities]
         */

        // create and save new client in db
        Client client = new Client("Jan", "Kowalski", "Krakowskie przedmieście 23, Warszawa");
        ClientDao clientDao = ctx.getBean(ClientDao.class);
        clientDao.save(client);
        // create new order
        Order order = new Order("z dostawą do domu");
        // assign client to created order
        order.setClient(client);
        OrderDao orderDao = ctx.getBean(OrderDao.class);
        // save order with assigned client info
        orderDao.save(order);
        // create new products and save in db
        Product product1 = new Product("Telewizor LG 42'", 4800.0, "dolby surround");
        Product product2 = new Product("Telefon Apple iPhone SE", 2200.0, "pokrowiec gratis");
        ProductDao productDao = ctx.getBean(ProductDao.class);
        productDao.save(product1);
        productDao.save(product2);
        // add SAVED products to SAVED order
        orderDao.addProductsToOrder(order.getId(), product1, product2);
        // get client from db with all added data from related tables
        Client getClient = clientDao.get(client.getId());
        System.out.println("\n"+getClient);

        /* Client has assigned 1 order but Hibernate doubles it
           Automatic query is similar to SELECT * from client_order JOIN order_products ON id_order=order_id
           and we get also order data twice in database.
           To avoid that add @Fetch(FetchMode.SELECT) in Order class
           !!! In real life its important to think whether its necessary to use eager loading that can significantly slow down application
           Common solution is to use pagination - load fe. 20 records at once. This is used by fe allegro when searching items.
         */

        ctx.close();
    }
}