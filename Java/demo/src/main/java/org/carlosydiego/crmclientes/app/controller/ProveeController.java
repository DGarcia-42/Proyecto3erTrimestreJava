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
import org.carlosydiego.crmclientes.app.model.Producto;
import org.carlosydiego.crmclientes.app.model.Provee;
import org.carlosydiego.crmclientes.app.model.Proveedor;
import org.carlosydiego.crmclientes.app.repository.ProveeRepository;

public class ProveeController implements ProveeRepository<Provee>
{
    Connection connection;

    public ProveeController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    public Provee createProvee(ResultSet rs) throws SQLException
    {
        Provee provee = new Provee();
        provee.setID_Provee(rs.getLong("id_provee"));
        provee.setFecha_Provision(rs.getDate("fecha_provision").toLocalDate());
        provee.setCantidad(rs.getInt("cantidad"));
        provee.setPrecio(rs.getDouble("precio"));
        
        
        Proveedor proveedor = new Proveedor();
        proveedor.setID_Proveedor(rs.getLong("id_proveedor"));
        proveedor.setNombre(rs.getString("nombre_proveedor"));
        provee.setProveedor(proveedor);
        
    
        Producto producto = new Producto();
        producto.setID_Producto(rs.getLong("id_producto"));
        producto.setNombre(rs.getString("nombre_producto"));
        

        Categoria categoria = new Categoria();
        categoria.setID_Categoria(rs.getLong("id_categoria"));
        categoria.setNombre(rs.getString("nombre_categoria"));
        producto.setCategoria(categoria);
        
        provee.setProducto(producto);
        
        return provee;
    }

    @Override
    public List<Provee> findAll()
    {
        List<Provee> lista = new ArrayList<>();
        String query = "SELECT p.*, prov.nombre AS nombre_proveedor, " +
                       "prod.nombre AS nombre_producto, " +
                       "c.id_categoria, c.nombre AS nombre_categoria " +
                       "FROM provee p " +
                       "INNER JOIN proveedor prov ON p.id_proveedor = prov.id_proveedor " +
                       "INNER JOIN producto prod ON p.id_producto = prod.id_productos " +
                       "INNER JOIN categoria c ON prod.id_categoria = c.id_categoria";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Provee p = createProvee(rs);
                lista.add(p);
            }
            if (lista.isEmpty())
            {
                System.out.println("No hay registros de provisiones en la base de datos");
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Provee findById(Long id)
    {
        Provee p = null;
        String query = "SELECT p.*, prov.nombre AS nombre_proveedor, " +
                       "prod.nombre AS nombre_producto, " +
                       "c.id_categoria, c.nombre AS nombre_categoria " +
                       "FROM provee p " +
                       "INNER JOIN proveedor prov ON p.id_proveedor = prov.id_proveedor " +
                       "INNER JOIN producto prod ON p.id_producto = prod.id_productos " +
                       "INNER JOIN categoria c ON prod.id_categoria = c.id_categoria " +
                       "WHERE p.id_provee = ?";

        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    p = createProvee(rs);
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
    public void save(Provee p)
    {
        String query;

        if (p.getID_Provee() == null)
        {
            query = "INSERT INTO provee (id_proveedor, id_producto, fecha_provision, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
        }
        else
        {
            query = "UPDATE provee SET id_proveedor = ?, id_producto = ?, fecha_provision = ?, cantidad = ?, precio = ? WHERE id_provee = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setLong(1, p.getProveedor().getID_Proveedor());
            pstmt.setLong(2, p.getProducto().getID_Producto());
            pstmt.setDate(3, java.sql.Date.valueOf(p.getFecha_Provision()));
            pstmt.setInt(4, p.getCantidad());
            pstmt.setDouble(5, p.getPrecio());
            
            if (p.getID_Provee() != null)
            {
                pstmt.setLong(6, p.getID_Provee());
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
        String query = "DELETE FROM provee WHERE id_provee = ?";
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