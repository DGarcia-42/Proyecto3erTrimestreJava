package org.carlosydiego.crmclientes.app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.carlosydiego.crmclientes.app.database.DatabaseConnection;
import org.carlosydiego.crmclientes.app.model.Proveedor;
import org.carlosydiego.crmclientes.app.repository.ProveedorRepository;

public class ProveedorController implements ProveedorRepository<Proveedor>
{
    Connection connection;

    public ProveedorController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    public Proveedor createProveedor(ResultSet rs) throws SQLException
    {
        Proveedor p = new Proveedor();
        p.setID_Proveedor(rs.getLong("id_proveedor"));
        p.setNombre(rs.getString("nombre"));
        p.setNombre_Responsable(rs.getString("nombre_responsable"));
        p.setPais(rs.getString("pais"));
        p.setProvincia(rs.getString("provincia"));
        p.setDireccion(rs.getString("direccion"));
        p.setCodigo_Postal(rs.getString("codigo_postal"));
        p.setCIF(rs.getString("cif"));
        p.setTelefono(rs.getString("telefono"));
        p.setEmail(rs.getString("email"));
        return p;
    }

    @Override
    public List<Proveedor> findAll()
    {
        List<Proveedor> lista = new ArrayList<>();
        String query = "SELECT * FROM proveedor";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Proveedor p = createProveedor(rs);
                lista.add(p);
            }
            if (lista.isEmpty())
            {
                System.out.println("No hay proveedores en la base de datos");
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Proveedor findById(Long id)
    {
        Proveedor p = null;
        String query = "SELECT * FROM proveedor WHERE id_proveedor = ?";

        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    p = createProveedor(rs);
                }
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return p;
    }

    @Override
    public void save(Proveedor p)
    {
        String query;

        if (p.getID_Proveedor() == null)
        {
            query = "INSERT INTO proveedor (nombre, nombre_responsable, pais, provincia, direccion, codigo_postal, cif, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        else
        {
            query = "UPDATE proveedor SET nombre = ?, nombre_responsable = ?, pais = ?, provincia = ?, direccion = ?, codigo_postal = ?, cif = ?, telefono = ?, email = ? WHERE id_proveedor = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getNombre_Responsable());
            pstmt.setString(3, p.getPais());
            pstmt.setString(4, p.getProvincia());
            pstmt.setString(5, p.getDireccion());
            pstmt.setString(6, p.getCodigo_Postal());
            pstmt.setString(7, p.getCIF());
            pstmt.setString(8, p.getTelefono());
            pstmt.setString(9, p.getEmail());
            
            if (p.getID_Proveedor() != null)
            {
                pstmt.setLong(10, p.getID_Proveedor());
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
        String query = "DELETE FROM proveedor WHERE id_proveedor = ?";
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