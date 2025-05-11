package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuProvee extends JFrame {
    private ProveeController proveeController;
    private ProductoController productoController;
    private ProveedorController proveedorController;

    //Constructor de la clase MenuProvee con su controlador y los necesarios para sus atributos
    public MenuProvee(ProveeController proveeController,
                     ProductoController productoController,
                     ProveedorController proveedorController) {
        this.proveeController = proveeController;
        this.productoController = productoController;
        this.proveedorController = proveedorController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Provee");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE PEDIDOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton ListarProveesBtn = new JButton("Ver todos los pedidos");
        ListarProveesBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ListarProveesBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ListarProveesBtn.setBackground(new Color(0, 123, 255));
        ListarProveesBtn.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(ListarProveesBtn, gbc);
        ListarProveesBtn.addActionListener(e -> {
            dispose();
            ListarProvees();
        });
        
        JButton BuscarProveeBtn = new JButton("Buscar pedido por ID");
        BuscarProveeBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarProveeBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarProveeBtn.setBackground(new Color(0, 123, 255));
        BuscarProveeBtn.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarProveeBtn, gbc);
        BuscarProveeBtn.addActionListener(e -> {
            dispose();
            BuscarProvee();
        });
        
        JButton AñadirProveeBtn = new JButton("Añadir nuevo pedido");
        AñadirProveeBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirProveeBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirProveeBtn.setBackground(new Color(76, 175, 80));
        AñadirProveeBtn.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirProveeBtn, gbc);
        AñadirProveeBtn.addActionListener(e -> {
            dispose();
            AñadirProvee();
        });

        JButton ActualizarProveeBtn = new JButton("Actualizar pedido");
        ActualizarProveeBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ActualizarProveeBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ActualizarProveeBtn.setBackground(new Color(0, 123, 255));
        ActualizarProveeBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ActualizarProveeBtn, gbc);
        ActualizarProveeBtn.addActionListener(e -> {
            dispose();
            ActualizarProvee();
        });
        
        JButton EliminarProveeBtn = new JButton("Eliminar pedido");
        EliminarProveeBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarProveeBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarProveeBtn.setBackground(new Color(0, 123, 255));
        EliminarProveeBtn.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarProveeBtn, gbc);
        EliminarProveeBtn.addActionListener(e -> {
            dispose();
            EliminarProvee();
        });

        JButton VolverBtn = new JButton("Volver al menu principal");
        VolverBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        VolverBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        VolverBtn.setBackground(new Color(0, 123, 255));
        VolverBtn.setForeground(Color.WHITE);
        gbc.gridy = 6;
        add(VolverBtn, gbc);
        VolverBtn.addActionListener(e -> {
            dispose();
            new MenuProyecto();
        });

        setVisible(true);
    }

    private void ListarProvees() {
        JFrame frame = new JFrame("Listar Pedidos");
        frame.setLayout(new GridBagLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int textAreaWidth = (int) (screenSize.width * 0.7);
        int textAreaHeight = (int) (screenSize.height * 0.6);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== LISTA DE PEDIDOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
  
        //Comprobación de si hay conexión a la base de datos
        if(proveeController != null) {
            try {
                //Se obtiene todos los pedidos de la base de datos
                List<Provee> provees = proveeController.findAll();

                //Si no hay pedidos registrados, se muestra un mensaje 
                if(provees == null || provees.isEmpty()) {
                    JLabel noProveesLabel = new JLabel("No hay pedidos registrados en el sistema");
                    noProveesLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noProveesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noProveesLabel, gbc);
                    
                    JButton volverButton = new JButton("Volver");
                    volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                    volverButton.setBackground(new Color(0, 123, 255));
                    volverButton.setForeground(Color.WHITE);
                    gbc.gridy = 2;
                    frame.add(volverButton, gbc);
                    
                    volverButton.addActionListener(e -> {
                        frame.dispose();
                        new MenuProvee(proveeController, productoController, proveedorController);
                    });
                // Si hay pedidos registrados, se muestra el listado de pedidos
                } else {
                    JTextArea proveesTextArea = new JTextArea();
                    proveesTextArea.setEditable(false);
                    proveesTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));

                    //Se crea un StringBuilder para almacenar la información de los pedidos
                    StringBuilder sb = new StringBuilder();
                    for (Provee provee : provees) {
                        sb.append("ID Pedido: ").append(provee.getID_Provee()).append("\n");
                        sb.append("Proveedor: ").append(provee.getProveedor().getNombre()).append(" (ID: ")
                            .append(provee.getProveedor().getID_Proveedor()).append(")\n");
                        sb.append("Producto: ").append(provee.getProducto().getNombre()).append(" (ID: ")
                            .append(provee.getProducto().getID_Producto()).append(")\n");
                        sb.append("Cantidad: ").append(provee.getCantidad()).append("\n");
                        sb.append("Precio: $").append(provee.getPrecio()).append("\n");
                        sb.append("Fecha de Provisión: ").append(provee.getFecha_Provision()).append("\n");
                        sb.append("Total: $").append(provee.getPrecio() * provee.getCantidad()).append("\n");
                        sb.append("------------------------------------------\n");
                    }
                    proveesTextArea.setText(sb.toString());
                    
                    JScrollPane scrollPane = new JScrollPane(proveesTextArea);
                    scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                    gbc.gridy = 1;
                    frame.add(scrollPane, gbc);
                    
                    JButton volverButton = new JButton("Volver");
                    volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                    volverButton.setBackground(new Color(0, 123, 255));
                    volverButton.setForeground(Color.WHITE);
                    gbc.gridy = 2;
                    frame.add(volverButton, gbc);
                    
                    volverButton.addActionListener(e -> {
                        frame.dispose();
                        new MenuProvee(proveeController, productoController, proveedorController);
                    });
                }
            } catch(Exception e) {
                System.err.println("Error al obtener los pedidos: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error al obtener pedidos: " + e.getMessage());
                errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                gbc.gridy = 1;
                frame.add(errorLabel, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridy = 2;
                frame.add(volverButton, gbc);
                
                volverButton.addActionListener(e2 -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            } 
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
            gbc.gridy = 1;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            gbc.gridy = 2;
            frame.add(volverButton, gbc);
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProvee(proveeController, productoController, proveedorController);
            });
        }
    }
  
    private void BuscarProvee() {
        JFrame frame = new JFrame("Buscar Pedido");
        frame.setLayout(new GridBagLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fieldWidth = (int) (screenSize.width * 0.18);
        int fieldHeight = (int) (screenSize.height * 0.05);
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int textAreaWidth = (int) (screenSize.width * 0.5);
        int textAreaHeight = (int) (screenSize.height * 0.2);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
  
        JLabel Title = new JLabel("=== BUSCAR PEDIDO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
      
        if(proveeController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID del pedido
                JLabel idLabel = new JLabel("ID del pedido:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
  
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
                //Se crea un JLabel y un JTextArea para mostrar la información del pedido
                final JLabel infoLabel = new JLabel("Información del pedido:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
                
                final JTextArea proveeInfo = new JTextArea();
                proveeInfo.setEditable(false);
                proveeInfo.setBackground(new Color(240, 240, 240));
                proveeInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                proveeInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(proveeInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
                
                JButton buscarButton = new JButton("Buscar Pedido");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                
                //Acción para buscar el pedido
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene el ID del pedido introducido
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene el pedido con el ID introducido
                            Provee provee = proveeController.findById(id);

                            //Si el pedido existe, se muestra la información del pedido
                            if(provee != null) {
                                //Se crea un StringBuilder para almacenar la información del pedido
                                StringBuilder sb = new StringBuilder();
                                sb.append("ID Pedido: ").append(provee.getID_Provee()).append("\n");
                                sb.append("Proveedor: ").append(provee.getProveedor().getNombre()).append(" (ID: ")
                                    .append(provee.getProveedor().getID_Proveedor()).append(")\n");
                                sb.append("Producto: ").append(provee.getProducto().getNombre()).append(" (ID: ")
                                    .append(provee.getProducto().getID_Producto()).append(")\n");
                                sb.append("Cantidad: ").append(provee.getCantidad()).append("\n");
                                sb.append("Precio: $").append(provee.getPrecio()).append("\n");
                                sb.append("Fecha de Provisión: ").append(provee.getFecha_Provision()).append("\n");
                                sb.append("Total: $").append(provee.getPrecio() * provee.getCantidad()).append("\n");

                                proveeInfo.setText(sb.toString());
                                statusLabel.setText("");
                            //Si el pedido no existe, se muestra un mensaje de error
                            } else {
                                proveeInfo.setText("");
                                statusLabel.setText("No se encontró un pedido con el ID: " + id);
                            }
                        } catch(NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            proveeInfo.setText("");
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            } catch(Exception e) {
                System.err.println("Error al buscar el pedido: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error: " + e.getMessage());
                errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                frame.add(errorLabel, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);
                
                volverButton.addActionListener(e2 -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            frame.add(volverButton, gbc);
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProvee(proveeController, productoController, proveedorController);
            });
        }
    }
  
    private void AñadirProvee() {
        JFrame frame = new JFrame("Añadir Pedido");
        frame.setLayout(new GridBagLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fieldWidth = (int) (screenSize.width * 0.18);
        int fieldHeight = (int) (screenSize.height * 0.05);
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
  
        JLabel Title = new JLabel("=== AÑADIR NUEVO PEDIDO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
  
        if(proveeController != null && productoController != null && proveedorController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID del proveedor
                JLabel proveedorLabel = new JLabel("ID del Proveedor:");
                proveedorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(proveedorLabel, gbc);
                
                JTextField proveedorTextField = new JTextField(20);
                proveedorTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                proveedorTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(proveedorTextField, gbc);

                //Se crea un botón para ver los proveedores
                JButton verProveedoresButton = new JButton("Ver Proveedores");
                verProveedoresButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verProveedoresButton.setBackground(new Color(0, 123, 255));
                verProveedoresButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(verProveedoresButton, gbc);

                //Se crea un JLabel y un JTextField para introducir el ID del producto
                JLabel productoLabel = new JLabel("ID del Producto:");
                productoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 1;
                frame.add(productoLabel, gbc);
                
                JTextField productoTextField = new JTextField(20);
                productoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                productoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(productoTextField, gbc);

                //Se crea un botón para ver los productos
                JButton verProductosButton = new JButton("Ver Productos");
                verProductosButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verProductosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verProductosButton.setBackground(new Color(0, 123, 255));
                verProductosButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                frame.add(verProductosButton, gbc);

                //Se crea un JLabel y un JTextField para introducir el resto de atributos del pedido
                JLabel cantidadLabel = new JLabel("Cantidad:");
                cantidadLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(cantidadLabel, gbc);
                
                JTextField cantidadTextField = new JTextField(20);
                cantidadTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                cantidadTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(cantidadTextField, gbc);
                
                JLabel precioLabel = new JLabel("Precio:");
                precioLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 6;
                frame.add(precioLabel, gbc);
                
                JTextField precioTextField = new JTextField(20);
                precioTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                precioTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(precioTextField, gbc);
                
                JLabel fechaLabel = new JLabel("Fecha de Provisión (YYYY-MM-DD):");
                fechaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                frame.add(fechaLabel, gbc);
                
                JTextField fechaTextField = new JTextField(20);
                fechaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                fechaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(fechaTextField, gbc);
                
                JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 8;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);
                
                JButton guardarButton = new JButton("Guardar Pedido");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(40, 167, 69));
                guardarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 9;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                
                //Acción para ver los proveedores
                verProveedoresButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los proveedores de la base de datos
                            List<Proveedor> proveedores = proveedorController.findAll();

                            //Si hay proveedores, se muestra el listado de proveedores
                            if (proveedores != null && !proveedores.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de los proveedores
                                for (Proveedor proveedor : proveedores) {
                                    sb.append("ID: ").append(proveedor.getID_Proveedor())
                                      .append(" - Nombre: ").append(proveedor.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de proveedores
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay proveedores disponibles", 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al cargar proveedores: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción para ver los productos
                verProductosButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los productos de la base de datos
                            List<Producto> productos = productoController.findAll();

                            //Si hay productos, se muestra el listado de productos
                            if (productos != null && !productos.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de los productos
                                for (Producto producto : productos) {
                                    sb.append("ID: ").append(producto.getID_Producto())
                                      .append(" - Nombre: ").append(producto.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de productos
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Productos", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay productos disponibles", 
                                    "Listado de Productos", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al cargar productos: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción para guardar el pedido
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se comprueba si todos los campos están rellenos
                            if (proveedorTextField.getText().isEmpty() || productoTextField.getText().isEmpty() 
                                || cantidadTextField.getText().isEmpty() || precioTextField.getText().isEmpty()
                                || fechaTextField.getText().isEmpty()) {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }

                            //Se obtiene el ID del proveedor, del producto, la cantidad y el precio
                            long proveedorId;
                            long productoId;
                            int cantidad;
                            double precio;

                            //Se comprueba si el ID del proveedor es un número válido
                            try {
                                proveedorId = Long.parseLong(proveedorTextField.getText());
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El ID de proveedor debe ser un número");
                                return;
                            }

                            //Se comprueba si el ID del producto es un número válido
                            try {
                                productoId = Long.parseLong(productoTextField.getText());
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El ID de producto debe ser un número");
                                return;
                            }

                            //Se comprueba si la cantidad es un número válido
                            try {
                                cantidad = Integer.parseInt(cantidadTextField.getText());
                                if (cantidad <= 0) {
                                    statusLabel.setText("Error: La cantidad debe ser mayor que cero");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: La cantidad debe ser un número entero");
                                return;
                            }

                            //Se comprueba si el precio es un número válido
                            try {
                                precio = Double.parseDouble(precioTextField.getText());
                                if (precio <= 0) {
                                    statusLabel.setText("Error: El precio debe ser mayor que cero");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El precio debe ser un número decimal válido");
                                return;
                            }

                            //Se obtiene la fecha de provisión
                            LocalDate fechaProvision;

                            //Se comprueba si la fecha de provisión es válida
                            try {
                                fechaProvision = LocalDate.parse(fechaTextField.getText());
                                LocalDate hoy = LocalDate.now();
                                if (fechaProvision.isAfter(hoy)) {
                                    int confirmacion = JOptionPane.showConfirmDialog(
                                        frame,
                                        "La fecha de provisión es futura. ¿Desea continuar?",
                                        "Confirmación de fecha",
                                        JOptionPane.YES_NO_OPTION
                                    );
                                    if (confirmacion != JOptionPane.YES_OPTION) {
                                        return;
                                    }
                                }
                            } catch (DateTimeParseException dtpe) {
                                statusLabel.setText("Error: El formato de fecha debe ser YYYY-MM-DD");
                                return;
                            }
                            
                            Proveedor proveedor = proveedorController.findById(proveedorId);
                            Producto producto = productoController.findById(productoId);

                            //Se comprueba si el proveedor existe
                            if (proveedor == null) {
                                statusLabel.setText("Error: El proveedor con ID " + proveedorId + " no existe");
                                return;
                            }

                            //Se comprueba si el producto existe
                            if (producto == null) {
                                statusLabel.setText("Error: El producto con ID " + productoId + " no existe");
                                return;
                            }

                            //Se crea un nuevo pedido
                            Provee provee = new Provee();
                            provee.setProveedor(proveedor);
                            provee.setProducto(producto);
                            provee.setCantidad(cantidad);
                            provee.setPrecio(precio);
                            provee.setFecha_Provision(fechaProvision);

                            //Se guarda el pedido en la base de datos
                            proveeController.save(provee);

                            statusLabel.setText("Pedido guardado correctamente");
                            
                            //Se limpia el formulario
                            proveedorTextField.setText("");
                            productoTextField.setText("");
                            cantidadTextField.setText("");
                            precioTextField.setText("");
                            fechaTextField.setText("");
                            
                        } catch (Exception ex) {
                            statusLabel.setText("Error al guardar el pedido: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            } catch(Exception e) {
                System.err.println("Error: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error: " + e.getMessage());
                errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                frame.add(errorLabel, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);
                
                volverButton.addActionListener(e2 -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            frame.add(volverButton, gbc);
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProvee(proveeController, productoController, proveedorController);
            });
        }
    }
  
    private void ActualizarProvee() {
        JFrame frame = new JFrame("Actualizar Pedido");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fieldWidth = (int) (screenSize.width * 0.18);
        int fieldHeight = (int) (screenSize.height * 0.05);
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
  
        JLabel Title = new JLabel("=== ACTUALIZAR PEDIDO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
  
        if(proveeController != null && productoController != null && proveedorController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID del pedido a actualizar
                JLabel idLabel = new JLabel("ID del pedido a actualizar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
                //Se crea un botón para buscar el pedido
                final JButton buscarButton = new JButton("Buscar Pedido");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(buscarButton, gbc);

                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 3;
                frame.add(statusLabel, gbc);
                
                //Se crea un JLabel y un JTextField para introducir el ID del proveedor
                JLabel proveedorLabel = new JLabel("ID del Proveedor:");
                proveedorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 1;
                frame.add(proveedorLabel, gbc);
                
                final JTextField proveedorTextField = new JTextField(10);
                proveedorTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                proveedorTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                proveedorTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(proveedorTextField, gbc);

                //Se crea un botón para ver los proveedores
                final JButton verProveedoresButton = new JButton("Ver Proveedores");
                verProveedoresButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verProveedoresButton.setBackground(new Color(0, 123, 255));
                verProveedoresButton.setForeground(Color.WHITE);
                verProveedoresButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 2;
                frame.add(verProveedoresButton, gbc);

                //Se crea un JLabel y un JTextField para introducir el ID del producto
                JLabel productoLabel = new JLabel("ID del Producto:");
                productoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 1;
                frame.add(productoLabel, gbc);
                
                final JTextField productoTextField = new JTextField(10);
                productoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                productoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                productoTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(productoTextField, gbc);
                
                final JButton verProductosButton = new JButton("Ver Productos");
                verProductosButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verProductosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verProductosButton.setBackground(new Color(0, 123, 255));
                verProductosButton.setForeground(Color.WHITE);
                verProductosButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 2;
                frame.add(verProductosButton, gbc);

                //Se crea un JLabel y un JTextField para introducir el resto de atributos del pedido
                JLabel cantidadLabel = new JLabel("Cantidad:");
                cantidadLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 8;
                gbc.gridwidth = 1;
                frame.add(cantidadLabel, gbc);
                
                final JTextField cantidadTextField = new JTextField(10);
                cantidadTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                cantidadTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                cantidadTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(cantidadTextField, gbc);
                
                JLabel precioLabel = new JLabel("Precio:");
                precioLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 9;
                frame.add(precioLabel, gbc);
                
                final JTextField precioTextField = new JTextField(10);
                precioTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                precioTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                precioTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(precioTextField, gbc);
                
                JLabel fechaLabel = new JLabel("Fecha Provisión (YYYY-MM-DD):");
                fechaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 10;
                frame.add(fechaLabel, gbc);
                
                final JTextField fechaTextField = new JTextField(10);
                fechaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                fechaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                fechaTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(fechaTextField, gbc);
                
                final JButton guardarButton = new JButton("Actualizar Pedido");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(40, 167, 69));
                guardarButton.setForeground(Color.WHITE);
                guardarButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 11;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);

                //Se crea un array para almacenar el ID del pedido
                final Long[] proveeId = new Long[1];
                
                //Acción para ver los proveedores
                verProveedoresButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los proveedores de la base de datos
                            List<Proveedor> proveedores = proveedorController.findAll();

                            //Si hay proveedores, se muestra el listado de proveedores
                            if (proveedores != null && !proveedores.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de los proveedores
                                for(Proveedor proveedor : proveedores) {
                                    sb.append("ID: ").append(proveedor.getID_Proveedor())
                                      .append(" - Nombre: ").append(proveedor.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de proveedores
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay proveedores registrados", 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch(Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener proveedores: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción para ver los productos
                verProductosButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los productos de la base de datos
                            List<Producto> productos = productoController.findAll();

                            //Si hay productos, se muestra el listado de productos
                            if (productos != null && !productos.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de los productos
                                for(Producto producto : productos) {
                                    sb.append("ID: ").append(producto.getID_Producto())
                                      .append(" - Nombre: ").append(producto.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de productos
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Productos", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay productos registrados", 
                                    "Listado de Productos", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch(Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener productos: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción para buscar el pedido
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            statusLabel.setText("Buscando pedido...");
                            
                            //Se busca el pedido con el ID introducido
                            Provee provee = proveeController.findById(id);

                            //Si el pedido existe, se muestra la información del pedido
                            if (provee != null) {
                                proveeId[0] = provee.getID_Provee();

                                proveedorTextField.setText(String.valueOf(provee.getProveedor().getID_Proveedor()));

                                productoTextField.setText(String.valueOf(provee.getProducto().getID_Producto()));

                                cantidadTextField.setText(String.valueOf(provee.getCantidad()));

                                precioTextField.setText(String.valueOf(provee.getPrecio()));
                                fechaTextField.setText(provee.getFecha_Provision() != null ? 
                                    provee.getFecha_Provision().toString() : "");
                                
                                //Se habilitan los campos para que el usuario pueda editarlos
                                proveedorTextField.setEnabled(true);
                                productoTextField.setEnabled(true);
                                cantidadTextField.setEnabled(true);
                                precioTextField.setEnabled(true);
                                fechaTextField.setEnabled(true);
                                verProveedoresButton.setEnabled(true);
                                verProductosButton.setEnabled(true);
                                guardarButton.setEnabled(true);
                                
                                statusLabel.setText("Pedido encontrado. Puedes editar los campos y actualizar.");
                            } else {
                                //Si el pedido no existe, se limpia el formulario y se deshabilitan los campos
                                statusLabel.setText("No se encontró un pedido con el ID: " + id);
                                
                                proveedorTextField.setText("");
                                productoTextField.setText("");
                                cantidadTextField.setText("");
                                precioTextField.setText("");
                                fechaTextField.setText("");
                                
                                proveedorTextField.setEnabled(false);
                                productoTextField.setEnabled(false);
                                cantidadTextField.setEnabled(false);
                                precioTextField.setEnabled(false);
                                fechaTextField.setEnabled(false);
                                verProveedoresButton.setEnabled(false);
                                verProductosButton.setEnabled(false);
                                guardarButton.setEnabled(false);
                            }
                        } catch (NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                        } catch (Exception ex) {
                            statusLabel.setText("Error al buscar el pedido: " + ex.getMessage());
                        }
                    }
                });

                //Acción para actualizar el pedido
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene el pedido original
                            Provee proveeOriginal = proveeController.findById(proveeId[0]);

                            //Si el pedido no existe, se muestra un mensaje de error
                            if (proveeOriginal == null) {
                                statusLabel.setText("Error: No se pudo recuperar el pedido original.");
                                return;
                            }

                            //Se comprueba si todos los campos están rellenos
                            if (proveedorTextField.getText().isEmpty() || productoTextField.getText().isEmpty() 
                                || cantidadTextField.getText().isEmpty() || precioTextField.getText().isEmpty()
                                || fechaTextField.getText().isEmpty()) {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }

                            //Se obtiene el ID del proveedor, del producto, la cantidad y el precio
                            long proveedorId;
                            long productoId;
                            int cantidad;
                            double precio;
                            LocalDate fechaProvision;
                            
                            //Se comprueba si el ID del proveedor es un número válido
                            try {
                                proveedorId = Long.parseLong(proveedorTextField.getText());
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El ID de proveedor debe ser un número válido");
                                return;
                            }

                            //Se comprueba si el ID del producto es un número válido
                            try {
                                productoId = Long.parseLong(productoTextField.getText());
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El ID de producto debe ser un número válido");
                                return;
                            }

                            //Se comprueba si la cantidad es un número válido
                            try {
                                cantidad = Integer.parseInt(cantidadTextField.getText());
                                if (cantidad <= 0) {
                                    statusLabel.setText("Error: La cantidad debe ser mayor que cero");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: La cantidad debe ser un número entero válido");
                                return;
                            }

                            //Se comprueba si el precio es un número válido
                            try {
                                precio = Double.parseDouble(precioTextField.getText());
                                if (precio <= 0) {
                                    statusLabel.setText("Error: El precio debe ser mayor que cero");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El precio debe ser un número decimal válido");
                                return;
                            }

                            //Se comprueba si la fecha de provisión es válida
                            try {
                                fechaProvision = LocalDate.parse(fechaTextField.getText());
                            } catch (DateTimeParseException dtpe) {
                                statusLabel.setText("Error: El formato de fecha debe ser YYYY-MM-DD");
                                return;
                            }

                            //Se obtiene el proveedor y el producto
                            Proveedor proveedor = proveedorController.findById(proveedorId);
                            Producto producto = productoController.findById(productoId);

                            //Si el proveedor no existe, se muestra un mensaje de error
                            if (proveedor == null) {
                                statusLabel.setText("Error: El proveedor con ID " + proveedorId + " no existe");
                                return;
                            }

                            //Si el producto no existe, se muestra un mensaje de error
                            if (producto == null) {
                                statusLabel.setText("Error: El producto con ID " + productoId + " no existe");
                                return;
                            }

                            //Se crea un nuevo pedido
                            Provee proveeActualizado = new Provee();
                            proveeActualizado.setID_Provee(proveeId[0]);
                            proveeActualizado.setProveedor(proveedor);
                            proveeActualizado.setProducto(producto);
                            proveeActualizado.setCantidad(cantidad);
                            proveeActualizado.setPrecio(precio);
                            proveeActualizado.setFecha_Provision(fechaProvision);

                            //Se guarda el pedido en la base de datos
                            proveeController.save(proveeActualizado);

                            statusLabel.setText("Pedido actualizado correctamente");
                        } catch (Exception ex) {
                            statusLabel.setText("Error al actualizar el pedido: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            } catch(Exception e) {
                System.err.println("Error: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error: " + e.getMessage());
                errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                frame.add(errorLabel, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);
                
                volverButton.addActionListener(e2 -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            frame.add(volverButton, gbc);
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProvee(proveeController, productoController, proveedorController);
            });
        }
    }
  
    private void EliminarProvee() {
        JFrame frame = new JFrame("Eliminar Pedido");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fieldWidth = (int) (screenSize.width * 0.18);
        int fieldHeight = (int) (screenSize.height * 0.05);
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int textAreaWidth = (int) (screenSize.width * 0.5);
        int textAreaHeight = (int) (screenSize.height * 0.2);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
  
        JLabel Title = new JLabel("=== ELIMINAR PEDIDO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
  
        if(proveeController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID del pedido a eliminar
                JLabel idLabel = new JLabel("ID del pedido a eliminar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                //Se crea un JLabel y un JTextArea para mostrar la información del pedido
                final JLabel infoLabel = new JLabel("Información del pedido:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
                
                final JTextArea proveeInfo = new JTextArea();
                proveeInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                proveeInfo.setEditable(false);
                proveeInfo.setBackground(new Color(240, 240, 240));
                proveeInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                JScrollPane scrollPane = new JScrollPane(proveeInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
                
                final JButton buscarButton = new JButton("Buscar Pedido");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);
                
                final JButton eliminarButton = new JButton("Eliminar Pedido");
                eliminarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                eliminarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                eliminarButton.setBackground(new Color(220, 53, 69));
                eliminarButton.setForeground(Color.WHITE);
                eliminarButton.setEnabled(false);
                gbc.gridx = 1;
                frame.add(eliminarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);
                
                final Long[] proveeId = new Long[1];

                //Acción para buscar el pedido
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se busca el pedido con el ID introducido
                            Provee provee = proveeController.findById(id);

                            //Si el pedido existe, se muestra la información del pedido
                            if (provee != null) {
                                proveeId[0] = provee.getID_Provee();

                                StringBuilder sb = new StringBuilder();
                                sb.append("ID Pedido: ").append(provee.getID_Provee()).append("\n");
                                sb.append("Proveedor: ").append(provee.getProveedor().getNombre()).append(" (ID: ")
                                    .append(provee.getProveedor().getID_Proveedor()).append(")\n");
                                sb.append("Producto: ").append(provee.getProducto().getNombre()).append(" (ID: ")
                                    .append(provee.getProducto().getID_Producto()).append(")\n");
                                sb.append("Cantidad: ").append(provee.getCantidad()).append("\n");
                                sb.append("Precio: $").append(provee.getPrecio()).append("\n");
                                sb.append("Fecha de Provisión: ").append(provee.getFecha_Provision()).append("\n");
                                sb.append("Total: $").append(provee.getPrecio() * provee.getCantidad()).append("\n");

                                proveeInfo.setText(sb.toString());

                                eliminarButton.setEnabled(true);

                                //Se muestra un mensaje de éxito
                                statusLabel.setText("Pedido encontrado. Pulse 'Eliminar Pedido' para confirmar.");
                            } else {
                                //Si el pedido no existe, se limpia el formulario y se deshabilita el botón para eliminar el pedido
                                proveeInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró un pedido con el ID: " + id);
                            }
                        } catch (NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            proveeInfo.setText("");
                            eliminarButton.setEnabled(false);
                        }
                    }
                });

                //Acción para eliminar el pedido
                eliminarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se muestra un mensaje de confirmación
                            int confirmacion = JOptionPane.showConfirmDialog(frame, 
                                "¿Está seguro de que desea eliminar este pedido?", 
                                "Confirmar eliminación", 
                                JOptionPane.YES_NO_OPTION);

                            //Si el usuario confirma la eliminación, se elimina el pedido
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                proveeController.delete(proveeId[0]);

                                proveeInfo.setText("");
                                idTextField.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("Pedido eliminado correctamente.");
                            }
                        } catch (Exception ex) {
                            //Si ocurre un error, se muestra un mensaje de error
                            statusLabel.setText("Error al eliminar el pedido: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            } catch(Exception e) {
                System.err.println("Error al eliminar el pedido: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error: " + e.getMessage());
                errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                frame.add(errorLabel, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);
                
                volverButton.addActionListener(e2 -> {
                    frame.dispose();
                    new MenuProvee(proveeController, productoController, proveedorController);
                });
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            frame.add(volverButton, gbc);
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProvee(proveeController, productoController, proveedorController);
            });
        }
    }
} 