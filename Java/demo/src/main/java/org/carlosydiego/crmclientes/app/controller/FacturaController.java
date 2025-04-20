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
import org.carlosydiego.crmclientes.app.model.Cliente;
import org.carlosydiego.crmclientes.app.model.Empleado;
import org.carlosydiego.crmclientes.app.model.Factura;
import org.carlosydiego.crmclientes.app.model.Producto;
import org.carlosydiego.crmclientes.app.repository.FacturaRepository;

public class FacturaController implements FacturaRepository<Factura>
{
    Connection connection;
    
    public FacturaController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    private Factura createFactura(ResultSet rs) throws SQLException
    {
        Factura f = new Factura();
        f.setID_Factura(rs.getLong("id_factura"));
        f.setFecha_Venta(rs.getDate("fecha_venta").toLocalDate());
        f.setCanal_Compra(rs.getString("canal_compra"));
        f.setCantidad(rs.getInt("cantidad"));
        f.setPagado(rs.getString("pagado"));
        f.setTotal(rs.getDouble("total"));
        
        // Crear y establecer el producto
        Producto p = new Producto();
        p.setID_Producto(rs.getLong("id_producto"));
        p.setNombre(rs.getString("nombre_producto"));
        p.setPVP(rs.getDouble("pvp"));
        
        // Crear y establecer la categoría del producto
        Categoria c = new Categoria();
        c.setID_Categoria(rs.getLong("id_categoria"));
        c.setNombre(rs.getString("nombre_categoria"));
        p.setCategoria(c);
        
        f.setProducto(p);
        
        // Crear y establecer el empleado
        Empleado e = new Empleado();
        e.setID_Empleado(rs.getLong("id_empleado"));
        e.setNombre(rs.getString("nombre_empleado"));
        e.setApellido(rs.getString("apellido_empleado"));
        f.setEmpleado(e);
        
        // Crear y establecer el cliente
        Cliente cl = new Cliente();
        cl.setID_Cliente(rs.getLong("id_cliente"));
        cl.setNombre_Empresa(rs.getString("nombre_empresa"));
        cl.setNombre_Responsable(rs.getString("nombre_responsable"));
        f.setCliente(cl);
        
        return f;
    }

    @Override
    public List<Factura> findAll()
    {
        List<Factura> lista = new ArrayList<>();
        String query = "SELECT f.*, p.nombre AS nombre_producto, p.pvp, c.id_categoria, c.nombre AS nombre_categoria, " +
                       "e.nombre AS nombre_empleado, e.apellido AS apellido_empleado, " + 
                       "cl.nombre_empresa, cl.nombre_responsable " +
                       "FROM factura f " +
                       "INNER JOIN producto p ON f.id_producto = p.id_productos " +
                       "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                       "INNER JOIN empleado e ON f.id_empleado = e.id_empleado " +
                       "INNER JOIN cliente cl ON f.id_cliente = cl.id_cliente";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Factura f = createFactura(rs);
                lista.add(f);
            }
            if (lista.isEmpty())
            {
                System.out.println("No hay facturas en la base de datos");
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Factura findById(Long id)
    {
        Factura f = null;
        String query = "SELECT f.*, p.nombre AS nombre_producto, p.pvp, c.id_categoria, c.nombre AS nombre_categoria, " +
                       "e.nombre AS nombre_empleado, e.apellido AS apellido_empleado, " + 
                       "cl.nombre_empresa, cl.nombre_responsable " +
                       "FROM factura f " +
                       "INNER JOIN producto p ON f.id_producto = p.id_productos " +
                       "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                       "INNER JOIN empleado e ON f.id_empleado = e.id_empleado " +
                       "INNER JOIN cliente cl ON f.id_cliente = cl.id_cliente " +
                       "WHERE f.id_factura = ?";

        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    f = createFactura(rs);
                }
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return f;
    }

    @Override
    public void save(Factura f)
    {
        String query;

        if (f.getID_Factura() == null)
        {
            query = "INSERT INTO factura (fecha_venta, canal_compra, cantidad, id_producto, pagado, id_empleado, id_cliente, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        }
        else
        {
            query = "UPDATE factura SET fecha_venta = ?, canal_compra = ?, cantidad = ?, id_producto = ?, pagado = ?, id_empleado = ?, id_cliente = ?, total = ? WHERE id_factura = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setDate(1, java.sql.Date.valueOf(f.getFecha_Venta()));
            pstmt.setString(2, f.getCanal_Compra());
            pstmt.setInt(3, f.getCantidad());
            pstmt.setLong(4, f.getProducto().getID_Producto());
            pstmt.setString(5, f.getPagado());
            pstmt.setLong(6, f.getEmpleado().getID_Empleado());
            pstmt.setLong(7, f.getCliente().getID_Cliente());
            pstmt.setDouble(8, f.getTotal());
            
            if (f.getID_Factura() != null)
            {
                pstmt.setLong(9, f.getID_Factura());
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
        String query = "DELETE FROM factura WHERE id_factura = ?";
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