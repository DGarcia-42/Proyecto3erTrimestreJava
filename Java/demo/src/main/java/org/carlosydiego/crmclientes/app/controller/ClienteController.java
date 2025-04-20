package org.carlosydiego.crmclientes.app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.carlosydiego.crmclientes.app.database.DatabaseConnection;
import org.carlosydiego.crmclientes.app.model.Cliente;
import org.carlosydiego.crmclientes.app.repository.ClienteRepository;

public class ClienteController implements ClienteRepository<Cliente>
{
    Connection connection;

    public ClienteController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    private Cliente createCliente(ResultSet rs) throws SQLException
    {
        Cliente c = new Cliente();
        c.setID_Cliente(rs.getLong("id_cliente"));
        c.setCIF(rs.getString("cif"));
        c.setNombre_Empresa(rs.getString("nombre_empresa"));
        c.setNombre_Responsable(rs.getString("nombre_responsable"));
        c.setPais(rs.getString("pais"));
        c.setProvincia(rs.getString("provincia"));
        c.setDireccion(rs.getString("direccion"));
        c.setEmail(rs.getString("email"));
        c.setTelefono(rs.getString("telefono"));
        c.setCodigo_Postal(rs.getString("codigo_postal"));
        return c;
    }

    @Override
    public List<Cliente> findAll()
    {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Cliente c = createCliente(rs);
                lista.add(c);
            }
            if (lista.isEmpty())
            {
                System.out.println("No hay clientes en la base de datos");
            }
        } 
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Cliente findById(Long id)
    {
        Cliente c = null;
        String query = "SELECT * FROM cliente WHERE id_cliente = ?";

        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    c = createCliente(rs);
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
    public void save(Cliente c)
    {
        String query;

        if (c.getID_Cliente() == null)
        {
            query = "INSERT INTO cliente (cif, nombre_empresa, nombre_responsable, pais, provincia, direccion, email, telefono, codigo_postal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        else
        {
            query = "UPDATE cliente SET cif = ?, nombre_empresa = ?, nombre_responsable = ?, pais = ?, provincia = ?, direccion = ?, email = ?, telefono = ?, codigo_postal = ? WHERE id_cliente = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setString(1, c.getCIF());
            pstmt.setString(2, c.getNombre_Empresa());
            pstmt.setString(3, c.getNombre_Responsable());
            pstmt.setString(4, c.getPais());
            pstmt.setString(5, c.getProvincia());
            pstmt.setString(6, c.getDireccion());
            pstmt.setString(7, c.getEmail());
            pstmt.setString(8, c.getTelefono());
            pstmt.setString(9, c.getCodigo_Postal());
            
            if (c.getID_Cliente() != null)
            {
                pstmt.setLong(10, c.getID_Cliente());
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
        String query = "DELETE FROM cliente WHERE id_cliente = ?";
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