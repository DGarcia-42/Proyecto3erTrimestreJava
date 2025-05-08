package org.carlosydiego.crmclientes.app.menuproyecto;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

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
    getContentPane().setBackground(new Color(245, 247, 250));

    JLabel Title = new JLabel("\n=== SISTEMA CRM CLIENTES ===");
    Title.setBounds(300, 10, 300, 50);
    Title.setFont(new Font("Roboto", Font.BOLD, 14));
    Title.setForeground(new Color(46, 46, 46));
    add(Title);

        JButton Clientes = new JButton("Gestión de Clientes");
    Clientes.setBounds(100, 100, 200, 50);
    Clientes.setFont(new Font("Roboto", Font.BOLD, 14));
    Clientes.setBackground(new Color(0, 123, 255));
    Clientes.setForeground(Color.WHITE);
    add(Clientes);
        Clientes.addActionListener(e -> {
            dispose();
            new MenuCliente(clienteController);
    });

        JButton Empleados = new JButton("Gestión de Empleados");
    Empleados.setBounds(100, 150, 200, 50);
    Empleados.setFont(new Font("Roboto", Font.BOLD, 14));
    Empleados.setBackground(new Color(0, 123, 255));
    Empleados.setForeground(Color.WHITE);
    add(Empleados);
        Empleados.addActionListener(e -> {
            dispose();
            new MenuEmpleado(empleadoController);
    });

        JButton Productos = new JButton("Gestión de Productos");
    Productos.setBounds(100, 200, 200, 50);
    Productos.setFont(new Font("Roboto", Font.BOLD, 14));
    Productos.setBackground(new Color(0, 123, 255));
    Productos.setForeground(Color.WHITE);
    add(Productos);
        Productos.addActionListener(e -> {
            dispose();
            new MenuProducto(productoController, categoriaController, proveedorController);
    });

        JButton Proveedores = new JButton("Gestión de Proveedores");
    Proveedores.setBounds(100, 250, 200, 50);
    Proveedores.setFont(new Font("Roboto", Font.BOLD, 14));
    Proveedores.setBackground(new Color(0, 123, 255));
    Proveedores.setForeground(Color.WHITE);
    add(Proveedores);
        Proveedores.addActionListener(e -> {
            dispose();
            new MenuProveedor(proveedorController);
    });

        JButton Facturas = new JButton("Gestión de Facturas");
    Facturas.setBounds(100, 300, 200, 50);
    Facturas.setFont(new Font("Roboto", Font.BOLD, 14));
    Facturas.setBackground(new Color(0, 123, 255));
    Facturas.setForeground(Color.WHITE);
    add(Facturas);
        Facturas.addActionListener(e -> {
            dispose();
            new MenuFactura(facturaController, clienteController, empleadoController, productoController);
    });

        JButton Categorias = new JButton("Gestión de Categorías");
    Categorias.setBounds(100, 350, 200, 50);
    Categorias.setFont(new Font("Roboto", Font.BOLD, 14));
    Categorias.setBackground(new Color(0, 123, 255));
    Categorias.setForeground(Color.WHITE);
    add(Categorias);
        Categorias.addActionListener(e -> {
            dispose();
            new MenuCategoria(categoriaController);
    });

        JButton Provees = new JButton("Gestión de Pedidos");
    Provees.setBounds(100, 400, 200, 50);
    Provees.setFont(new Font("Roboto", Font.BOLD, 14));
    Provees.setBackground(new Color(0, 123, 255));
    Provees.setForeground(Color.WHITE);
    add(Provees);
        Provees.addActionListener(e -> {
            dispose();
            new MenuProvee(proveeController, productoController, proveedorController);
    });

        JButton Salir = new JButton("Salir");
    Salir.setBounds(100, 450, 200, 50);
    Salir.setFont(new Font("Roboto", Font.BOLD, 14));
    Salir.setBackground(new Color(220, 53, 69));
    Salir.setForeground(Color.WHITE);
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
