package com.tomek;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
       SpringApplication.run(JpaSpringBootApplication.class, args);

       // In this case Spring creates tables client and client_order
        // client table has access to client_order data based on client_id field defined in Client class

    }
}

