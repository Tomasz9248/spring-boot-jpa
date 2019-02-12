package com.tomek.dao;

import com.tomek.model.Order;
import com.tomek.model.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderDao extends GenericDao<Order, Long> {
    public void addProductsToOrder(Long orderId, Product... products) {
        Order order = get(orderId);
        if(order != null) {
            for(Product product: products) {
                order.getProducts().add(product);
            }
        }
    }
}