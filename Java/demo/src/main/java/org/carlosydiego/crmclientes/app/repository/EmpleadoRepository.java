package org.carlosydiego.crmclientes.app.repository;

import java.util.List;

public interface EmpleadoRepository <Empleado> {
    
    List<Empleado> findAll();
    Empleado findById(Long id);
    void save(Empleado empleado);
    void delete(Long id);
} 