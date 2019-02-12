package com.tomek.dao;

import com.tomek.model.Client;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ClientDao extends GenericDao<Client, Long> {
    public void removeAllOrders(Client client) {
        Client managedClient = get(client.getId());
        managedClient.getOrders().clear();
    }
}
