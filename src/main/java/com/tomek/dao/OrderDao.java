package com.tomek.dao;

import com.tomek.model.Order;
import com.tomek.model.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderDao extends GenericDao<Order, Long> {
   // create specific method that allows to add products to order
    // as an argument it takes orders's id and products that are expect to be added to order with provided id
    public void addProductsToOrder(Long orderId, Product... products) {
        Order order = get(orderId);
        if(order != null) {
            for(Product product: products) {
                order.getProducts().add(product);
            }
        }
    }
}