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
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Principal");
        getContentPane().setBackground(new Color(245, 247, 250));
        inicializarControladores();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== SISTEMA CRM CLIENTES ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton btnClientes = new JButton("Gestión de Clientes");
        btnClientes.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnClientes.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnClientes.setBackground(new Color(0, 123, 255));
        btnClientes.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(btnClientes, gbc);
        btnClientes.addActionListener(e -> {
            dispose();
            new MenuCliente(clienteController);
        });

        JButton btnEmpleados = new JButton("Gestión de Empleados");
        btnEmpleados.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnEmpleados.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnEmpleados.setBackground(new Color(0, 123, 255));
        btnEmpleados.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(btnEmpleados, gbc);
        btnEmpleados.addActionListener(e -> {
            dispose();
            new MenuEmpleado(empleadoController);
        });

        JButton btnProductos = new JButton("Gestión de Productos");
        btnProductos.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnProductos.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnProductos.setBackground(new Color(0, 123, 255));
        btnProductos.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(btnProductos, gbc);
        btnProductos.addActionListener(e -> {
            dispose();
            new MenuProducto(productoController, categoriaController, proveedorController);
        });

        JButton btnProveedores = new JButton("Gestión de Proveedores");
        btnProveedores.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnProveedores.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnProveedores.setBackground(new Color(0, 123, 255));
        btnProveedores.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(btnProveedores, gbc);
        btnProveedores.addActionListener(e -> {
            dispose();
            new MenuProveedor(proveedorController);
        });

        JButton btnFacturas = new JButton("Gestión de Facturas");
        btnFacturas.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnFacturas.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnFacturas.setBackground(new Color(0, 123, 255));
        btnFacturas.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(btnFacturas, gbc);
        btnFacturas.addActionListener(e -> {
            dispose();
            new MenuFactura(facturaController, clienteController, empleadoController, productoController);
        });

        JButton btnCategorias = new JButton("Gestión de Categorías");
        btnCategorias.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnCategorias.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnCategorias.setBackground(new Color(0, 123, 255));
        btnCategorias.setForeground(Color.WHITE);
        gbc.gridy = 6;
        add(btnCategorias, gbc);
        btnCategorias.addActionListener(e -> {
            dispose();
            new MenuCategoria(categoriaController);
        });

        JButton btnPedidos = new JButton("Gestión de Pedidos");
        btnPedidos.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnPedidos.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnPedidos.setBackground(new Color(0, 123, 255));
        btnPedidos.setForeground(Color.WHITE);
        gbc.gridy = 7;
        add(btnPedidos, gbc);
        btnPedidos.addActionListener(e -> {
            dispose();
            new MenuProvee(proveeController, productoController, proveedorController);
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnSalir.setFont(new Font("Roboto", Font.BOLD, fontSize));
        btnSalir.setBackground(new Color(220, 53, 69));
        btnSalir.setForeground(Color.WHITE);
        gbc.gridy = 8;
        add(btnSalir, gbc);
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
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
