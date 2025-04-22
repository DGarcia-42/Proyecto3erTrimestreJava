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
import org.carlosydiego.crmclientes.app.model.Proveedor;
import org.carlosydiego.crmclientes.app.repository.ProductoRepository;

public class ProductoController implements ProductoRepository <Producto>
{
    Connection connection;

    public ProductoController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    public Producto createProduct(ResultSet rs) throws SQLException
    {
        Producto p = new Producto();
        p.setID_Producto(rs.getLong("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setPVP(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setIVA(rs.getDouble("iva"));
        
        Categoria c = new Categoria();
        c.setID_Categoria(rs.getLong("id_categoria"));
        c.setNombre(rs.getString("nombreCategoria"));

        p.setCategoria(c);

        Proveedor prov = new Proveedor();
        prov.setID_Proveedor(rs.getLong("id_proveedor"));
        prov.setNombre(rs.getString("nombreProveedor"));
        prov.setNombre_Responsable(rs.getString("nombreProveedor"));
        prov.setPais(rs.getString("paisProveedor"));
        prov.setProvincia(rs.getString("provinciaProveedor"));
        prov.setDireccion(rs.getString("direccionProveedor"));
        prov.setCodigo_Postal(rs.getString("codigo_postalProveedor"));
        prov.setCIF(rs.getString("cifProveedor"));
        prov.setTelefono(rs.getString("telefonoProveedor"));
        prov.setEmail(rs.getString("emailProveedor"));
        p.setProveedor(prov);

        return p;
    }

    @Override
    public List<Producto> findAll()
    {
        List <Producto> lista = new ArrayList<>();
        String query = "SELECT p.id_producto, p.nombre, p.pvp as precio, p.stock, p.descripcion, p.iva, " +
                       "c.id_categoria, c.nombre AS nombreCategoria, " +
                       "pr.id_proveedor, pr.nombre AS nombreProveedor, " +
                       "pr.nombre_responsable AS nombreResponsable, pr.pais AS paisProveedor, " +
                       "pr.provincia AS provinciaProveedor, pr.direccion AS direccionProveedor, " +
                       "pr.codigo_postal AS codigo_postalProveedor, pr.cif AS cifProveedor, " +
                       "pr.telefono AS telefonoProveedor, pr.email AS emailProveedor " +
                       "FROM producto AS p INNER JOIN categoria AS c ON p.Categoria = c.id_categoria " +
                       "LEFT JOIN proveedor AS pr ON p.Proveedor_Clave = pr.id_proveedor";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Producto p = createProduct(rs);
                lista.add(p);
            }
            if (lista.isEmpty())
            {
                System.out.println("No hay productos en la base de datos");
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Producto findById(Long id_productos) 
    {
        Producto p = null;
        String query = "SELECT p.id_producto, p.nombre, p.pvp as precio, p.stock, p.descripcion, p.iva, " +
                       "c.id_categoria, c.nombre AS nombreCategoria, " +
                       "pr.id_proveedor, pr.nombre AS nombreProveedor, " +
                       "pr.nombre_responsable AS nombreResponsable, pr.pais AS paisProveedor, " +
                       "pr.provincia AS provinciaProveedor, pr.direccion AS direccionProveedor, " +
                       "pr.codigo_postal AS codigo_postalProveedor, pr.cif AS cifProveedor, " +
                       "pr.telefono AS telefonoProveedor, pr.email AS emailProveedor " +
                       "FROM producto AS p INNER JOIN categoria AS c ON p.Categoria = c.id_categoria " +
                       "LEFT JOIN proveedor AS pr ON p.Proveedor_Clave = pr.id_proveedor " +
                       "WHERE p.id_producto = ?";

        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setLong(1, id_productos);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    p = createProduct(rs);
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
    public void save(Producto p) 
    {
        String query;

        if (p.getID_Producto() == null)
        {
            query = "INSERT INTO producto (nombre, descripcion, stock, pvp, iva, Categoria, Proveedor_Clave) VALUES (?, ?, ?, ?, ?, ?, ?)";
        }
        else
        {
            query = "UPDATE producto SET nombre = ?, descripcion = ?, stock = ?, pvp = ?, iva = ?, Categoria = ?, Proveedor_Clave = ? WHERE id_producto = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getDescripcion());
            pstmt.setInt(3, p.getStock());
            pstmt.setDouble(4, p.getPVP());
            pstmt.setDouble(5, p.getIVA());
            pstmt.setLong(6, p.getCategoria().getID_Categoria());
            
            // Manejar el ID del proveedor
            if (p.getProveedor() != null && p.getProveedor().getID_Proveedor() != null) {
                pstmt.setLong(7, p.getProveedor().getID_Proveedor());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            if (p.getID_Producto() != null)
            {
                pstmt.setLong(8, p.getID_Producto());
            }
            pstmt.executeUpdate();
        }
        catch (SQLException sex)
        {
            sex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id_productos) 
    {
        String query = "DELETE FROM producto WHERE id_producto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setLong(1, id_productos);
            pstmt.executeUpdate();
        }
        catch (SQLException sex)
        {
            sex.printStackTrace();
        }
    }
}
