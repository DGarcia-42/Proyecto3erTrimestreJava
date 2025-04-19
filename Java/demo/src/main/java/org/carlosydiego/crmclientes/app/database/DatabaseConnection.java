package org.carlosydiego.crmclientes.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection 
{
     private static String db = "jdbc:mysql://localhost:3306/proyectojava";
    private static String user = "root";
    private static String passw = "1234";

    private static Connection connection;

    private DatabaseConnection()
    {
        // Constructor privado para evitar instanciación.
    }

    public static Connection getInstance() throws SQLException
    {

        if (connection == null)
        {
            connection = DriverManager.getConnection (db, user, passw);
        }
        return connection;
    }
}
