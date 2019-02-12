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

        // create client and save client in db
        Client client = new Client("Sherlock", "Holmes", "Baker Street 221b, London");
        ClientDao clientDao = ctx.getBean(ClientDao.class);
        clientDao.save(client);
        // print client's data
        System.out.println(client);
        // create and save order in db
        Order order = new Order("home delivery");
        order.setClient(client);
        OrderDao orderDao = ctx.getBean(OrderDao.class);
        orderDao.save(order);
        // create and save products in db
        Product product1 = new Product("Apple iPhone 8", 3800.0, "extra glass protection");
        Product product2 = new Product("wooden pipe", 500.0, "no tobacco");
        ProductDao productDao = ctx.getBean(ProductDao.class);
        productDao.save(product1);
        productDao.save(product2);
        //add products to specific order
        orderDao.addProductsToOrder(order.getId(), product1, product2);

        ctx.close();
    }
}