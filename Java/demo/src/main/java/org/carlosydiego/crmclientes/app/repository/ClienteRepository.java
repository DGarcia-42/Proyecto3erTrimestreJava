package org.carlosydiego.crmclientes.app.repository;

import java.util.List;

public interface ClienteRepository <Cliente> {
    
    List<Cliente> findAll();
    Cliente findById(Long id);
    void save(Cliente cliente);
    void delete(Long id);
} 