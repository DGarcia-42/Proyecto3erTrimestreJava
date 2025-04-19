package org.carlosydiego.crmclientes.app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.carlosydiego.crmclientes.app.database.DatabaseConnection;
import org.carlosydiego.crmclientes.app.model.Empleado;
import org.carlosydiego.crmclientes.app.repository.EmpleadoRepository;

public class EmpleadoController implements EmpleadoRepository<Empleado>
{
    Connection connection;

    public EmpleadoController() throws SQLException
    {
        connection = DatabaseConnection.getInstance();
    }

    private Empleado createEmpleado(ResultSet rs) throws SQLException
    {
        Empleado e = new Empleado();
        e.setID_Empleado(rs.getLong("id_empleado"));
        e.setNombre(rs.getString("nombre"));
        e.setApellido(rs.getString("apellido"));
        e.setNIF(rs.getString("nif"));
        e.setDireccion(rs.getString("direccion"));
        e.setCodigo_Postal(rs.getString("codigo_postal"));
        e.setProvincia(rs.getString("provincia"));
        e.setPaís(rs.getString("pais"));
        e.setTelfono(rs.getString("telefono"));
        e.setEmail(rs.getString("email"));
        return e;
    }

    @Override
    public List<Empleado> findAll()
    {
        List<Empleado> lista = new ArrayList<>();
        String query = "SELECT * FROM empleados";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Empleado e = createEmpleado(rs);
                lista.add(e);
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Empleado findById(Long id)
    {
        Empleado e = null;
        String query = "SELECT * FROM empleados WHERE id_empleado = ?";

        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    e = createEmpleado(rs);
                }
            }
        }
        catch (SQLException sex) 
        {
            sex.printStackTrace();
        }
        return e;
    }

    @Override
    public void save(Empleado e)
    {
        String query;

        if (e.getID_Empleado() == null)
        {
            query = "INSERT INTO empleados (nombre, apellido, nif, direccion, codigo_postal, provincia, pais, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        else
        {
            query = "UPDATE empleados SET nombre = ?, apellido = ?, nif = ?, direccion = ?, codigo_postal = ?, provincia = ?, pais = ?, telefono = ?, email = ? WHERE id_empleado = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setString(1, e.getNombre());
            pstmt.setString(2, e.getApellido());
            pstmt.setString(3, e.getNIF());
            pstmt.setString(4, e.getDireccion());
            pstmt.setString(5, e.getCodigo_Postal());
            pstmt.setString(6, e.getProvincia());
            pstmt.setString(7, e.getPaís());
            pstmt.setString(8, e.getTelfono());
            pstmt.setString(9, e.getEmail());
            
            if (e.getID_Empleado() != null)
            {
                pstmt.setLong(10, e.getID_Empleado());
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
        String query = "DELETE FROM empleados WHERE id_empleado = ?";
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