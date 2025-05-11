package org.carlosydiego.crmclientes.app.controller;

import java.sql.Connection;
import java.sql.Date;
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
import org.carlosydiego.crmclientes.app.model.Proveedor;
import org.carlosydiego.crmclientes.app.repository.FacturaRepository;
import org.carlosydiego.crmclientes.app.util.FacturaFileManager;

public class FacturaController implements FacturaRepository<Factura>
{
    Connection connection;
    
    public FacturaController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    public Factura createFactura(ResultSet rs) throws SQLException
    {
        Factura f = new Factura();
        f.setID_Factura(rs.getLong("ID_Factura"));
        f.setFecha_Venta(rs.getDate("Fecha_Venta").toLocalDate());
        f.setCanal_Compra(rs.getString("Canal_Compra"));
        f.setCantidad(rs.getInt("Cantidad"));
        f.setPagado(rs.getString("Pagado"));
        f.setTotal(rs.getDouble("Total"));
        
        // Crear y establecer el producto
        Producto p = new Producto();
        p.setID_Producto(rs.getLong("Producto"));
        p.setNombre(rs.getString("nombre_producto"));
        p.setPVP(rs.getDouble("pvp"));
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setIVA(rs.getDouble("iva"));
        
        // Crear y establecer la categoría del producto
        Categoria c = new Categoria();
        c.setID_Categoria(rs.getLong("id_categoria"));
        c.setNombre(rs.getString("nombre_categoria"));
        p.setCategoria(c);
        
        // Crear y establecer el proveedor si existe
        Long idProveedor = rs.getLong("id_proveedor");
        if (!rs.wasNull()) {
            Proveedor prov = new Proveedor();
            prov.setID_Proveedor(idProveedor);
            prov.setNombre(rs.getString("nombre_proveedor"));
            prov.setNombre_Responsable(rs.getString("nombre_responsable"));
            prov.setPais(rs.getString("pais_proveedor"));
            prov.setProvincia(rs.getString("provincia_proveedor"));
            prov.setDireccion(rs.getString("direccion_proveedor"));
            prov.setCodigo_Postal(rs.getString("codigo_postal_proveedor"));
            prov.setCIF(rs.getString("cif_proveedor"));
            prov.setTelefono(rs.getString("telefono_proveedor"));
            prov.setEmail(rs.getString("email_proveedor"));
            p.setProveedor(prov);
        } else {
            p.setProveedor(null);
        }

        f.setProducto(p);
        
        // Crear y establecer el empleado
        Empleado e = new Empleado();
        e.setID_Empleado(rs.getLong("Empleado"));
        e.setNombre(rs.getString("nombre_empleado"));
        e.setApellido(rs.getString("apellido_empleado"));
        e.setNIF(rs.getString("nif_empleado"));
        e.setDireccion(rs.getString("direccion_empleado"));
        e.setCodigo_Postal(rs.getString("codigo_postal_empleado"));
        e.setProvincia(rs.getString("provincia_empleado"));
        e.setPais(rs.getString("pais_empleado"));
        e.setTelfono(rs.getString("telefono_empleado"));
        e.setEmail(rs.getString("email_empleado"));
        f.setEmpleado(e);
        
        // Crear y establecer el cliente
        Cliente cl = new Cliente();
        cl.setID_Cliente(rs.getLong("Cliente"));
        cl.setNombre_Empresa(rs.getString("nombre_empresa"));
        cl.setNombre_Responsable(rs.getString("nombre_responsable"));
        cl.setPais(rs.getString("pais_cliente"));
        cl.setProvincia(rs.getString("provincia_cliente"));
        cl.setDireccion(rs.getString("direccion_cliente"));
        cl.setCodigo_Postal(rs.getString("codigo_postal_cliente"));
        cl.setCIF(rs.getString("cif_cliente"));
        cl.setTelefono(rs.getString("telefono_cliente"));
        cl.setEmail(rs.getString("email_cliente"));
        f.setCliente(cl);
        
        return f;
    }

    
    @Override
    public List<Factura> findAll()
    {
        List<Factura> lista = new ArrayList<>();
        String query = "SELECT f.*, " +
                       "p.nombre AS nombre_producto, p.pvp, p.stock, p.descripcion, p.iva, " +
                       "c.id_categoria, c.nombre AS nombre_categoria, " +
                       "pr.id_proveedor, pr.nombre AS nombre_proveedor, " +
                       "pr.nombre_responsable, pr.pais AS pais_proveedor, " +
                       "pr.provincia AS provincia_proveedor, pr.direccion AS direccion_proveedor, " +
                       "pr.codigo_postal AS codigo_postal_proveedor, pr.cif AS cif_proveedor, " +
                       "pr.telefono AS telefono_proveedor, pr.email AS email_proveedor, " + 
                       "e.nombre AS nombre_empleado, e.apellido AS apellido_empleado, " +
                       "e.nif AS nif_empleado, e.direccion AS direccion_empleado, " +
                       "e.codigo_postal AS codigo_postal_empleado, e.provincia AS provincia_empleado, " +
                       "e.pais AS pais_empleado, e.telefono AS telefono_empleado, " +
                       "e.email AS email_empleado, " +
                       "cl.nombre_empresa, cl.nombre_responsable, cl.pais AS pais_cliente, " +
                       "cl.provincia AS provincia_cliente, cl.direccion AS direccion_cliente, " +
                       "cl.codigo_postal AS codigo_postal_cliente, cl.cif AS cif_cliente, " +
                       "cl.telefono AS telefono_cliente, cl.email AS email_cliente " +
                       "FROM factura f " +
                       "INNER JOIN producto p ON f.Producto = p.id_producto " +
                       "INNER JOIN categoria c ON p.Categoria = c.id_categoria " +
                       "LEFT JOIN proveedor pr ON p.Proveedor_Clave = pr.id_proveedor " +
                       "INNER JOIN empleado e ON f.Empleado = e.id_empleado " +
                       "INNER JOIN cliente cl ON f.Cliente = cl.id_cliente";
        
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
        String query = "SELECT f.*, " +
                       "p.nombre AS nombre_producto, p.pvp, p.stock, p.descripcion, p.iva, " +
                       "c.id_categoria, c.nombre AS nombre_categoria, " + 
                       "pr.id_proveedor, pr.nombre AS nombre_proveedor, " +
                       "pr.nombre_responsable, pr.pais AS pais_proveedor, " +
                       "pr.provincia AS provincia_proveedor, pr.direccion AS direccion_proveedor, " +
                       "pr.codigo_postal AS codigo_postal_proveedor, pr.cif AS cif_proveedor, " +
                       "pr.telefono AS telefono_proveedor, pr.email AS email_proveedor, " +
                       "e.nombre AS nombre_empleado, e.apellido AS apellido_empleado, " +
                       "e.nif AS nif_empleado, e.direccion AS direccion_empleado, " +
                       "e.codigo_postal AS codigo_postal_empleado, e.provincia AS provincia_empleado, " +
                       "e.pais AS pais_empleado, e.telefono AS telefono_empleado, " +
                       "e.email AS email_empleado, " +
                       "cl.nombre_empresa, cl.nombre_responsable, cl.pais AS pais_cliente, " +
                       "cl.provincia AS provincia_cliente, cl.direccion AS direccion_cliente, " +
                       "cl.codigo_postal AS codigo_postal_cliente, cl.cif AS cif_cliente, " +
                       "cl.telefono AS telefono_cliente, cl.email AS email_cliente " +
                       "FROM factura f " +
                       "INNER JOIN producto p ON f.Producto = p.id_producto " +
                       "INNER JOIN categoria c ON p.Categoria = c.id_categoria " +
                       "LEFT JOIN proveedor pr ON p.Proveedor_Clave = pr.id_proveedor " +
                       "INNER JOIN empleado e ON f.Empleado = e.id_empleado " +
                       "INNER JOIN cliente cl ON f.Cliente = cl.id_cliente " +
                       "WHERE f.ID_Factura = ?";

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
    public void save(Factura factura)
    {
        String query;
        if (factura.getID_Factura() == null)
        {
            query = "INSERT INTO factura (Pagado, Empleado, Cliente, Producto, Total, " +
                    "Canal_Compra, Cantidad, Fecha_Venta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
            {
                // Obtenemos el producto actual 
                String queryProducto = "SELECT * FROM producto WHERE id_producto = ?";
                try (PreparedStatement psProducto = connection.prepareStatement(queryProducto)) {
                    psProducto.setLong(1, factura.getProducto().getID_Producto());
                    try (ResultSet rsProducto = psProducto.executeQuery()) {
                        if (rsProducto.next()) {
                            int stockActual = rsProducto.getInt("stock");
                            int cantidadVendida = factura.getCantidad();
                            
                            // Verificamos que hay suficiente stock
                            if (stockActual < cantidadVendida) {
                                throw new RuntimeException("No hay suficiente stock disponible. Stock actual: " + stockActual);
                            }
                            
                            // Continuar con la inserción de la factura
                            ps.setString(1, factura.getPagado());
                            ps.setLong(2, factura.getEmpleado().getID_Empleado());
                            ps.setLong(3, factura.getCliente().getID_Cliente());
                            ps.setLong(4, factura.getProducto().getID_Producto());
                            ps.setDouble(5, factura.getTotal());
                            ps.setString(6, factura.getCanal_Compra());
                            ps.setInt(7, factura.getCantidad());
                            ps.setDate(8, Date.valueOf(factura.getFecha_Venta()));

                            int filasAfectadas = ps.executeUpdate();
                            if (filasAfectadas == 0)
                            {
                                return;
                            }

                            try (ResultSet generatedKeys = ps.getGeneratedKeys())
                            {
                                if (generatedKeys.next())
                                {
                                    factura.setID_Factura(generatedKeys.getLong(1));
                                    
                                    // Actualizar el stock del producto
                                    String updateProducto = "UPDATE producto SET stock = ? WHERE id_producto = ?";
                                    try (PreparedStatement psUpdate = connection.prepareStatement(updateProducto)) {
                                        int nuevoStock = stockActual - cantidadVendida;
                                        psUpdate.setInt(1, nuevoStock);
                                        psUpdate.setLong(2, factura.getProducto().getID_Producto());
                                        psUpdate.executeUpdate();
                                    }
                                }
                            }
                        } else {
                            throw new RuntimeException("El producto con ID " + factura.getProducto().getID_Producto() + " no existe");
                        }
                    }
                }
            }
            catch (SQLException e)
            {
                System.err.println("Error al guardar la factura: " + e.getMessage());
                throw new RuntimeException("Error en la base de datos: " + e.getMessage());
            }
        }
        else
        {
            query = "UPDATE factura SET Pagado = ?, Empleado = ?, Cliente = ?, Producto = ?, " +
                    "Total = ?, Canal_Compra = ?, Cantidad = ?, Fecha_Venta = ? WHERE ID_Factura = ?";
            try (PreparedStatement ps = connection.prepareStatement(query))
            {
                // Primero obtenemos la factura original para saber la cantidad anterior
                Factura facturaOriginal = findById(factura.getID_Factura());
                if (facturaOriginal != null) {
                    Long productoOriginalId = facturaOriginal.getProducto().getID_Producto();
                    int cantidadOriginal = facturaOriginal.getCantidad();
                    
                    // Obtenemos el producto actual
                    String queryProducto = "SELECT * FROM producto WHERE id_producto = ?";
                    try (PreparedStatement psProducto = connection.prepareStatement(queryProducto)) {
                        psProducto.setLong(1, factura.getProducto().getID_Producto());
                        try (ResultSet rsProducto = psProducto.executeQuery()) {
                            if (rsProducto.next()) {
                                int stockActual = rsProducto.getInt("stock");
                                
                                // Si el producto ha cambiado o la cantidad ha cambiado, actualizamos stocks
                                if (!productoOriginalId.equals(factura.getProducto().getID_Producto()) ||
                                    cantidadOriginal != factura.getCantidad()) {
                                    
                                    // Verificamos si hay stock suficiente para el nuevo producto
                                    if (stockActual < factura.getCantidad()) {
                                        throw new RuntimeException("No hay suficiente stock disponible. Stock actual: " + stockActual);
                                    }
                                    
                                    // Actualizamos la factura
                                    ps.setString(1, factura.getPagado());
                                    ps.setLong(2, factura.getEmpleado().getID_Empleado());
                                    ps.setLong(3, factura.getCliente().getID_Cliente());
                                    ps.setLong(4, factura.getProducto().getID_Producto());
                                    ps.setDouble(5, factura.getTotal());
                                    ps.setString(6, factura.getCanal_Compra());
                                    ps.setInt(7, factura.getCantidad());
                                    ps.setDate(8, Date.valueOf(factura.getFecha_Venta()));
                                    ps.setLong(9, factura.getID_Factura());

                                    int filasAfectadas = ps.executeUpdate();
                                    if (filasAfectadas > 0) {
                                        // Devolvemos el stock al producto original si es diferente
                                        if (!productoOriginalId.equals(factura.getProducto().getID_Producto())) {
                                            String updateProductoOriginal = "UPDATE producto SET stock = stock + ? WHERE id_producto = ?";
                                            try (PreparedStatement psUpdateOrig = connection.prepareStatement(updateProductoOriginal)) {
                                                psUpdateOrig.setInt(1, cantidadOriginal);
                                                psUpdateOrig.setLong(2, productoOriginalId);
                                                psUpdateOrig.executeUpdate();
                                            }
                                            
                                            // Restamos stock al nuevo producto
                                            String updateProductoNuevo = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";
                                            try (PreparedStatement psUpdateNuevo = connection.prepareStatement(updateProductoNuevo)) {
                                                psUpdateNuevo.setInt(1, factura.getCantidad());
                                                psUpdateNuevo.setLong(2, factura.getProducto().getID_Producto());
                                                psUpdateNuevo.executeUpdate();
                                            }
                                        } else if (cantidadOriginal != factura.getCantidad()) {
                                            // Si es el mismo producto pero cantidad diferente, ajustamos la diferencia
                                            int diferencia = factura.getCantidad() - cantidadOriginal;
                                            String updateProducto = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";
                                            try (PreparedStatement psUpdate = connection.prepareStatement(updateProducto)) {
                                                psUpdate.setInt(1, diferencia);
                                                psUpdate.setLong(2, factura.getProducto().getID_Producto());
                                                psUpdate.executeUpdate();
                                            }
                                        }
                                    }
                                } else {
                                    // Si no hay cambios en producto o cantidad, solo actualizamos la factura
                                    ps.setString(1, factura.getPagado());
                                    ps.setLong(2, factura.getEmpleado().getID_Empleado());
                                    ps.setLong(3, factura.getCliente().getID_Cliente());
                                    ps.setLong(4, factura.getProducto().getID_Producto());
                                    ps.setDouble(5, factura.getTotal());
                                    ps.setString(6, factura.getCanal_Compra());
                                    ps.setInt(7, factura.getCantidad());
                                    ps.setDate(8, Date.valueOf(factura.getFecha_Venta()));
                                    ps.setLong(9, factura.getID_Factura());
                                    ps.executeUpdate();
                                }
                            } else {
                                throw new RuntimeException("El producto con ID " + factura.getProducto().getID_Producto() + " no existe");
                            }
                        }
                    }
                } else {
                    throw new RuntimeException("No se encontró la factura original con ID " + factura.getID_Factura());
                }
            }
            catch (SQLException e)
            {
                System.err.println("Error al actualizar la factura: " + e.getMessage());
                throw new RuntimeException("Error en la base de datos: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Long id)
    {
        String sql = "DELETE FROM factura WHERE ID_Factura = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setLong(1, id);

            int filasAfectadas = ps.executeUpdate();
            

            if (filasAfectadas > 0)
            {
                FacturaFileManager.eliminarArchivoFactura(id);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error al eliminar la factura: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

} 