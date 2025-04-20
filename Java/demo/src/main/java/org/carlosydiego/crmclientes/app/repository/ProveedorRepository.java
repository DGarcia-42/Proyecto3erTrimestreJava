package org.carlosydiego.crmclientes.app.repository;

import java.util.List;

public interface ProveedorRepository <Proveedor> 
{
    
    List<Proveedor> findAll();
    Proveedor findById(Long id);
    void save(Proveedor proveedor);
    void delete(Long id);
} 