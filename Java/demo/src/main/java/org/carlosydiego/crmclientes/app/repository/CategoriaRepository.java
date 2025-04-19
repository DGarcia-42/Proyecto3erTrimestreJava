package org.carlosydiego.crmclientes.app.repository;

import java.util.List;


public interface CategoriaRepository <Categoria>
{

    List<Categoria> findAll();
    Categoria findById(Long id);
    void save(Categoria categoria);
    void delete(Long id);
}
