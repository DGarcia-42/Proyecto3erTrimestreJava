package org.carlosydiego.crmclientes.app.menuproyecto;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.database.DatabaseConnection;

public class MenuProyecto extends JFrame {
    protected Connection connection;
    protected ClienteController clienteController;
    protected EmpleadoController empleadoController;
    protected ProductoController productoController;
    protected ProveedorController proveedorController;
    protected FacturaController facturaController;
    protected CategoriaController categoriaController;
    protected ProveeController proveeController;

    public MenuProyecto() {
    inicializarControladores();
    setVisible(true);
    setLayout(null);
    setTitle("Menu Proyecto");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JLabel Title = new JLabel("\n=== SISTEMA CRM CLIENTES ===");
    Title.setBounds(300, 10, 200, 50);
    add(Title);

        JButton Clientes = new JButton("Gestion de Clientes");
    Clientes.setBounds(100, 100, 200, 50);
    add(Clientes);
        Clientes.addActionListener(e -> {
            dispose();
            new MenuCliente(clienteController);
    });

        JButton Empleados = new JButton("Gestion de Empleados");
    Empleados.setBounds(100, 150, 200, 50);
    add(Empleados);
        Empleados.addActionListener(e -> {
            dispose();
            new MenuEmpleado(empleadoController);
    });

        JButton Productos = new JButton("Gestion de Productos");
    Productos.setBounds(100, 200, 200, 50);
    add(Productos);
        Productos.addActionListener(e -> {
            dispose();
            new MenuProducto(productoController, categoriaController, proveedorController);
    });

        JButton Proveedores = new JButton("Gestion de Proveedores");
    Proveedores.setBounds(100, 250, 200, 50);
    add(Proveedores);
        Proveedores.addActionListener(e -> {
            dispose();
            new MenuProveedor(proveedorController);
    });

        JButton Facturas = new JButton("Gestion de Facturas");
    Facturas.setBounds(100, 300, 200, 50);
    add(Facturas);
        Facturas.addActionListener(e -> {
            dispose();
            new MenuFactura(facturaController, clienteController, empleadoController, productoController);
    });

        JButton Categorias = new JButton("Gestion de Categorias");
    Categorias.setBounds(100, 350, 200, 50);
    add(Categorias);
        Categorias.addActionListener(e -> {
            dispose();
            new MenuCategoria(categoriaController);
    });

        JButton Provees = new JButton("Gestion de Provees");
    Provees.setBounds(100, 400, 200, 50);
    add(Provees);
        Provees.addActionListener(e -> {
            dispose();
            new MenuProvee(proveeController, productoController, proveedorController);
    });

        JButton Salir = new JButton("Salir");
    Salir.setBounds(100, 450, 200, 50);
    add(Salir);
        Salir.addActionListener(e -> dispose());
    }

    protected void inicializarControladores() {
        try {
           this.connection = DatabaseConnection.getInstance();
           
           this.clienteController = new ClienteController();
           this.empleadoController = new EmpleadoController();
           this.productoController = new ProductoController();
           this.proveedorController = new ProveedorController();
           this.facturaController = new FacturaController();
           this.categoriaController = new CategoriaController();
           this.proveeController = new ProveeController();
           
        } catch (SQLException e) {
           System.err.println("Error al conectar con la base de datos: " + e.getMessage());
           e.printStackTrace();
    }
  }
}
