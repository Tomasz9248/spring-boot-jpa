package com.tomek.dao;

import com.tomek.model.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ProductDao extends GenericDao<Product, Long> {
}