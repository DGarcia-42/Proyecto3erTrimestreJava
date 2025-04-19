package org.carlosydiego.crmclientes.app.repository;

import java.util.List;

public interface FacturaRepository <Factura> {
    
    List<Factura> findAll();
    Factura findById(Long id);
    void save(Factura factura);
    void delete(Long id);
} 