package org.carlosydiego.crmclientes.app.repository;

import java.util.List;

public interface ProveeRepository <Provee> {
    
    List<Provee> findAll();
    Provee findById(Long id);
    void save(Provee provee);
    void delete(Long id);
} 