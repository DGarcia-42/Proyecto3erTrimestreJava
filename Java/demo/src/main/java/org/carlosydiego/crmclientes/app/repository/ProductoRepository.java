package org.carlosydiego.crmclientes.app.repository;

import java.util.List;

public interface ProductoRepository <Producto> {
    
    List<Producto> findAll();
    Producto findById(Long id);
    void save(Producto producto);
    void delete(Long id);
} 