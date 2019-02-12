package com.tomek;

import com.tomek.dao.ClientDao;
import com.tomek.dao.OrderDao;
import com.tomek.model.Client;
import com.tomek.model.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);

        /*
        ManyToMany is always presented as two way relationship in database model
        It can be created as one way only in objected model
        This example is extension of last one
        client and order 1:n and n:1 relationship
        ManyToMany relationship is added between order and product
        Database generates 3 basic tables with clients, orders, products
        Since we have n:m relationship generates also order_products table
        order_products table stores only key that are references to both tables
         */

        ctx.close();
    }
}