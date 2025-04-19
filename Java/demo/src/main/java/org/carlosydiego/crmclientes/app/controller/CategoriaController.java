package org.carlosydiego.crmclientes.app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.carlosydiego.crmclientes.app.database.DatabaseConnection;
import org.carlosydiego.crmclientes.app.model.Categoria;
import org.carlosydiego.crmclientes.app.repository.CategoriaRepository;

public class CategoriaController implements CategoriaRepository<Categoria>
{
    Connection connection;

    public CategoriaController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    private Categoria createCategoria(ResultSet rs) throws SQLException
    {
        Categoria c = new Categoria();
        c.setID_Categoria(rs.getLong("id_categoria"));
        c.setNombre(rs.getString("nombre"));
        return c;
    }

    @Override
    public List<Categoria> findAll()
    {
        List<Categoria> lista = new ArrayList<>();
        String query = "SELECT * FROM categorias";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Categoria c = createCategoria(rs);
                lista.add(c);
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Categoria findById(Long id)
    {
        Categoria c = null;
        String query = "SELECT * FROM categorias WHERE id_categoria = ?";

        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    c = createCategoria(rs);
                }
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return c;
    }

    @Override
    public void save(Categoria c)
    {
        String query;

        if (c.getID_Categoria() == null)
        {
            query = "INSERT INTO categorias (nombre) VALUES (?)";
        }
        else
        {
            query = "UPDATE categorias SET nombre = ? WHERE id_categoria = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setString(1, c.getNombre());
            if (c.getID_Categoria() != null)
            {
                pstmt.setLong(2, c.getID_Categoria());
            }
            pstmt.executeUpdate();
        }
        catch (SQLException sex)
        {
            sex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id)
    {
        String query = "DELETE FROM categorias WHERE id_categoria = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException sex)
        {
            sex.printStackTrace();
        }
    }
} 