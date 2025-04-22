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
        provee.setID_Provee(rs.getLong("ID_Provision"));
        provee.setFecha_Provision(rs.getDate("Fecha_Provision").toLocalDate());
        provee.setCantidad(rs.getInt("Cantidad"));
        provee.setPrecio(rs.getDouble("Precio"));
        
        // Crear y configurar el proveedor con todos sus atributos
        Proveedor proveedor = new Proveedor();
        proveedor.setID_Proveedor(rs.getLong("Proveedor"));
        proveedor.setNombre(rs.getString("nombre_proveedor"));
        proveedor.setNombre_Responsable(rs.getString("nombre_responsable"));
        proveedor.setPais(rs.getString("pais_proveedor"));
        proveedor.setProvincia(rs.getString("provincia_proveedor"));
        proveedor.setDireccion(rs.getString("direccion_proveedor"));
        proveedor.setCodigo_Postal(rs.getString("codigo_postal_proveedor"));
        proveedor.setCIF(rs.getString("cif_proveedor"));
        proveedor.setTelefono(rs.getString("telefono_proveedor"));
        proveedor.setEmail(rs.getString("email_proveedor"));
        provee.setProveedor(proveedor);
        
        // Crear y configurar el producto con todos sus atributos
        Producto producto = new Producto();
        producto.setID_Producto(rs.getLong("Producto_Clave"));
        producto.setNombre(rs.getString("nombre_producto"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setStock(rs.getInt("stock"));
        producto.setPVP(rs.getDouble("pvp"));
        producto.setIVA(rs.getDouble("iva"));

        // Crear y configurar la categoría para el producto
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
        String query = "SELECT p.*, " +
                       "prov.id_proveedor, prov.nombre AS nombre_proveedor, " +
                       "prov.nombre_responsable, prov.pais AS pais_proveedor, " +
                       "prov.provincia AS provincia_proveedor, prov.direccion AS direccion_proveedor, " +
                       "prov.codigo_postal AS codigo_postal_proveedor, prov.cif AS cif_proveedor, " +
                       "prov.telefono AS telefono_proveedor, prov.email AS email_proveedor, " +
                       "prod.id_producto, prod.nombre AS nombre_producto, " +
                       "prod.descripcion, prod.stock, prod.pvp, prod.iva, " +
                       "c.id_categoria, c.nombre AS nombre_categoria " +
                       "FROM provee p " +
                       "INNER JOIN proveedor prov ON p.Proveedor = prov.id_proveedor " +
                       "INNER JOIN producto prod ON p.Producto_Clave = prod.id_producto " +
                       "INNER JOIN categoria c ON prod.Categoria = c.id_categoria";
        
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
        String query = "SELECT p.*, " +
                       "prov.id_proveedor, prov.nombre AS nombre_proveedor, " +
                       "prov.nombre_responsable, prov.pais AS pais_proveedor, " +
                       "prov.provincia AS provincia_proveedor, prov.direccion AS direccion_proveedor, " +
                       "prov.codigo_postal AS codigo_postal_proveedor, prov.cif AS cif_proveedor, " +
                       "prov.telefono AS telefono_proveedor, prov.email AS email_proveedor, " +
                       "prod.id_producto, prod.nombre AS nombre_producto, " +
                       "prod.descripcion, prod.stock, prod.pvp, prod.iva, " +
                       "c.id_categoria, c.nombre AS nombre_categoria " +
                       "FROM provee p " +
                       "INNER JOIN proveedor prov ON p.Proveedor = prov.id_proveedor " +
                       "INNER JOIN producto prod ON p.Producto_Clave = prod.id_producto " +
                       "INNER JOIN categoria c ON prod.Categoria = c.id_categoria " +
                       "WHERE p.ID_Provision = ?";

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
            query = "INSERT INTO provee (Proveedor, Producto_Clave, Fecha_Provision, Cantidad, Precio) VALUES (?, ?, ?, ?, ?)";
        }
        else
        {
            query = "UPDATE provee SET Proveedor = ?, Producto_Clave = ?, Fecha_Provision = ?, Cantidad = ?, Precio = ? WHERE ID_Provision = ?";
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
        String query = "DELETE FROM provee WHERE ID_Provision = ?";
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