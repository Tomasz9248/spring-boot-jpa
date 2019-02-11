package com.tomek.dao;

import com.tomek.model.Client;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository //  class is Spring component
@Transactional // Spring manages transactions
public class ClientDao extends GenericDao<Client, Long> {
    //GenericDao is generic class so can be used on different objects with different keys
    // no need to implement any methods here. Spring does it for us!
}

