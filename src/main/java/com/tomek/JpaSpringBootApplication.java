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

        Client client = new Client("Sherlock", "Holmes", "221b Baker Street, London");
        ClientDao clientDao = ctx.getBean(ClientDao.class);
        clientDao.save(client);
        System.out.println(client);

        Order order = new Order("ASUS Vivobook", "SSD 512 GB, home delivery");
        order.setClient(client);
        OrderDao orderDao = ctx.getBean(OrderDao.class);
        orderDao.save(order);

        Order getOrder = orderDao.get(1L);
        System.out.println(getOrder);

        client.setOrders(Arrays.asList(order));
        clientDao.update(client);
        /*Relationship is two way
        its possible to access client via order
        and check orders by client
        */
        System.out.println(client.getOrders());
        System.out.println(order.getClient());

        ctx.close();
    }
}