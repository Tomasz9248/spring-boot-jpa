package com.tomek.dao;

import com.tomek.model.Order;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderDao extends GenericDao<Order, Long> {
    //GenericDao is generic class so can be used on different objects with different keys
    // no need to implement any methods here. Spring does it for us!
}