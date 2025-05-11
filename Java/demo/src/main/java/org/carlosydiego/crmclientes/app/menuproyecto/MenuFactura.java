package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;
import org.carlosydiego.crmclientes.app.util.FacturaFileManager;

public class MenuFactura extends JFrame {
    private FacturaController facturaController;
    private ClienteController clienteController;
    private EmpleadoController empleadoController;
    private ProductoController productoController;

    // Constructor de la clase MenuFactura con su controlador y los necesarios para sus atributos
    public MenuFactura(FacturaController facturaController, 
                      ClienteController clienteController,
                      EmpleadoController empleadoController,
                      ProductoController productoController) {
        this.facturaController = facturaController;
        this.clienteController = clienteController;
        this.empleadoController = empleadoController;
        this.productoController = productoController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Factura");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE FACTURAS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton ListarFacturasBtn = new JButton("Ver todas las facturas");
        ListarFacturasBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ListarFacturasBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ListarFacturasBtn.setBackground(new Color(0, 123, 255));
        ListarFacturasBtn.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(ListarFacturasBtn, gbc);
        ListarFacturasBtn.addActionListener(e -> {
            dispose();
            ListarFacturas();
        });

        JButton BuscarFacturaBtn = new JButton("Buscar factura por ID");
        BuscarFacturaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarFacturaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarFacturaBtn.setBackground(new Color(0, 123, 255));
        BuscarFacturaBtn.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarFacturaBtn, gbc);
        BuscarFacturaBtn.addActionListener(e -> {
            dispose();
            BuscarFactura();
        });

        JButton AñadirFacturaBtn = new JButton("Añadir nueva factura");
        AñadirFacturaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirFacturaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirFacturaBtn.setBackground(new Color(76, 175, 80));
        AñadirFacturaBtn.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirFacturaBtn, gbc);
        AñadirFacturaBtn.addActionListener(e -> {
            dispose();
            AñadirFactura();
        });

        JButton ActualizarFacturaBtn = new JButton("Actualizar factura");
        ActualizarFacturaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ActualizarFacturaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ActualizarFacturaBtn.setBackground(new Color(0, 123, 255));
        ActualizarFacturaBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ActualizarFacturaBtn, gbc);
        ActualizarFacturaBtn.addActionListener(e -> {
            dispose();
            ActualizarFactura();
        });

        JButton EliminarFacturaBtn = new JButton("Eliminar factura");
        EliminarFacturaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarFacturaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarFacturaBtn.setBackground(new Color(0, 123, 255));
        EliminarFacturaBtn.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarFacturaBtn, gbc);
        EliminarFacturaBtn.addActionListener(e -> {
            dispose();
            EliminarFactura();
        });

        JButton GenerarArchivoBtn = new JButton("Generar archivo de factura");
        GenerarArchivoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        GenerarArchivoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        GenerarArchivoBtn.setBackground(new Color(0, 123, 255));
        GenerarArchivoBtn.setForeground(Color.WHITE);
        gbc.gridy = 6;
        add(GenerarArchivoBtn, gbc);
        GenerarArchivoBtn.addActionListener(e -> {
            dispose();
            GenerarArchivoFacturaPorId();
        });

        JButton VolverBtn = new JButton("Volver al menu principal");
        VolverBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        VolverBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        VolverBtn.setBackground(new Color(0, 123, 255));
        VolverBtn.setForeground(Color.WHITE);
        gbc.gridy = 7;
        add(VolverBtn, gbc);
        VolverBtn.addActionListener(e -> {
            dispose();
            new MenuProyecto();
        });

        setVisible(true);
    }

    private void ListarFacturas() {
        JFrame frame = new JFrame("Listar Facturas");
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

        JLabel Title = new JLabel("=== LISTADO DE FACTURAS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        //Comprobación de si hay conexión a la base de datos
        if(facturaController != null) {
            try {
                //Se obtiene todas las facturas de la base de datos
                List<Factura> facturas = facturaController.findAll();

                //Si no hay facturas registradas, se muestra un mensaje
                if(facturas == null || facturas.isEmpty()) {
                    JLabel noFacturasLabel = new JLabel("No hay facturas registradas en el sistema");
                    noFacturasLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noFacturasLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noFacturasLabel, gbc);
                    
                    JButton volverButton = new JButton("Volver");
                    volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                    volverButton.setBackground(new Color(0, 123, 255));
                    volverButton.setForeground(Color.WHITE);
                    gbc.gridy = 2;
                    frame.add(volverButton, gbc);
                    
                    volverButton.addActionListener(e -> {
                        frame.dispose();
                        new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                    });

                //Si hay facturas registradas, se muestra el listado de facturas
                } else {
                    JTextArea facturasTextArea = new JTextArea();
                    facturasTextArea.setEditable(false);
                    facturasTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));

                    //Se crea un StringBuilder para almacenar el listado de facturas
                    StringBuilder sb = new StringBuilder();
                    for (Factura factura : facturas) {
                        sb.append("ID: ").append(factura.getID_Factura()).append("\n");
                        sb.append("Cliente: ").append(factura.getCliente().getNombre_Empresa())
                          .append(" (ID: ").append(factura.getCliente().getID_Cliente()).append(")\n");
                        sb.append("Empleado: ").append(factura.getEmpleado().getNombre())
                          .append(" ").append(factura.getEmpleado().getApellido())
                          .append(" (ID: ").append(factura.getEmpleado().getID_Empleado()).append(")\n");
                        sb.append("Producto: ").append(factura.getProducto().getNombre())
                          .append(" (ID: ").append(factura.getProducto().getID_Producto()).append(")\n");
                        sb.append("Cantidad: ").append(factura.getCantidad()).append("\n");
                        sb.append("Fecha: ").append(factura.getFecha_Venta()).append("\n");
                        sb.append("Método de Pago: ").append(factura.getCanal_Compra()).append("\n");
                        sb.append("Estado: ").append(factura.getPagado()).append("\n");
                        sb.append("Total: $").append(factura.getTotal()).append("\n");
                        sb.append("------------------------------------------\n");
                    }
                    facturasTextArea.setText(sb.toString());
                    
                    JScrollPane scrollPane = new JScrollPane(facturasTextArea);
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
                        new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                    });
                }
            } catch(Exception e) {
                System.err.println("Error al obtener las facturas: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error al obtener facturas: " + e.getMessage());
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
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
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
                new MenuFactura(facturaController, clienteController, empleadoController, productoController);
            });
        }
    }
    private void BuscarFactura() {
        JFrame frame = new JFrame("Buscar Factura");
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
 
        JLabel Title = new JLabel("=== BUSCAR FACTURA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
      
        if(facturaController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID de la factura
                JLabel idLabel = new JLabel("ID de la factura:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
 
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                //Se crea un JLabel y un JTextArea para mostrar la información de la factura
                final JLabel infoLabel = new JLabel("Información de la factura:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
                
                final JTextArea facturaInfo = new JTextArea();
                facturaInfo.setEditable(false);
                facturaInfo.setBackground(new Color(240, 240, 240));
                facturaInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                facturaInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(facturaInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
                
                JButton buscarButton = new JButton("Buscar Factura");
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

                //Acción para buscar la factura
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene el ID de la factura introducido 
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            statusLabel.setText("Buscando factura...");

                            //Se busca la factura con el ID introducido
                            Factura factura = facturaController.findById(id);

                            //Si la factura existe, se muestra la información de la factura
                            if(factura != null) {
                                facturaInfo.setText(factura.toString());
                                statusLabel.setText("");
                                
                            // Si la factura no existe, se muestra un mensaje de error
                            } else {
                                facturaInfo.setText("");
                                statusLabel.setText("No se encontró una factura con el ID: " + id);
                            }
                        } catch(NumberFormatException nfe) {
                            //Si el ID introducido no es un número, se muestra un mensaje de error
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            facturaInfo.setText("");
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                });
            } catch(Exception e) {
                System.err.println("Error al buscar la factura: " + e.getMessage());
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
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
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
                new MenuFactura(facturaController, clienteController, empleadoController, productoController);
            });
        }
    }

    private void AñadirFactura() {
        JFrame frame = new JFrame("Añadir Factura");
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
      
        JLabel Title = new JLabel("=== AÑADIR NUEVA FACTURA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        frame.add(Title, gbc);
  
        if(facturaController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID del cliente
                JLabel clienteLabel = new JLabel("ID del Cliente:");
                clienteLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(clienteLabel, gbc);
                
                JTextField clienteTextField = new JTextField(10);
                clienteTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                clienteTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(clienteTextField, gbc);
                
                //Se crea un botón para ver los clientes disponibles
                JButton verClientesButton = new JButton("Ver Clientes");
                verClientesButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
                verClientesButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
                verClientesButton.setBackground(new Color(0, 123, 255));
                verClientesButton.setForeground(Color.WHITE);
                gbc.gridx = 2;
                frame.add(verClientesButton, gbc);

                //Acción para ver los clientes disponibles
                verClientesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los clientes de la base de datos
                            List<Cliente> clientes = clienteController.findAll();

                            //Si hay clientes registrados, se muestra el listado de clientes
                            if(clientes != null && !clientes.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar el listado de clientes
                                for(Cliente c : clientes) {
                                    sb.append("ID: ").append(c.getID_Cliente())
                                      .append(" - Nombre: ").append(c.getNombre_Empresa())
                                      .append(" ").append(c.getNombre_Responsable())
                                      .append("\n");
                                }

                                //Se muestra el listado de clientes
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Clientes", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay clientes registrados", 
                                    "Listado de Clientes", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch(Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener clientes: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Se crea un JLabel y un JTextField para introducir el ID del empleado
                JLabel empleadoLabel = new JLabel("ID del Empleado:");
                empleadoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(empleadoLabel, gbc);
                
                JTextField empleadoTextField = new JTextField(10);
                empleadoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                empleadoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(empleadoTextField, gbc);

                //Se crea un botón para ver los empleados disponibles
                JButton verEmpleadosButton = new JButton("Ver Empleados");
                verEmpleadosButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
                verEmpleadosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
                verEmpleadosButton.setBackground(new Color(0, 123, 255));
                verEmpleadosButton.setForeground(Color.WHITE);
                gbc.gridx = 2;
                frame.add(verEmpleadosButton, gbc);

                //Acción para ver los empleados disponibles
                verEmpleadosButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los empleados de la base de datos
                            List<Empleado> empleados = empleadoController.findAll();

                            //Si hay empleados registrados, se muestra el listado de empleados
                            if(empleados != null && !empleados.isEmpty()) {
                                StringBuilder sb = new StringBuilder();
                                for(Empleado emp : empleados) {
                                    sb.append("ID: ").append(emp.getID_Empleado())
                                      .append(" - Nombre: ").append(emp.getNombre())
                                      .append(" ").append(emp.getApellido())
                                      .append("\n");
                                }

                                //Se muestra el listado de empleados
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Empleados", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay empleados registrados", 
                                    "Listado de Empleados", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch(Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener empleados: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Se crea un JLabel para el producto
                JLabel productoLabel = new JLabel("Seleccione un Producto:");
                productoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 3;
                frame.add(productoLabel, gbc);
                
                // Crear ComboBox y Map para seleccionar producto
                final JComboBox<String> productosCombo = new JComboBox<>();
                final Map<String, Long> productosMap = new HashMap<>();
                
                try {
                    //Se obtiene todos los productos de la base de datos
                    List<Producto> productos = productoController.findAll();

                    //Si hay productos registrados, se muestra el listado de productos
                    if(productos != null && !productos.isEmpty()) {
                        for(Producto p : productos) {
                            String key = p.getID_Producto() + " - " + p.getNombre() + " - $" + p.getPVP();
                            productosCombo.addItem(key);
                            productosMap.put(key, p.getID_Producto());
                        }
                    } else {
                        //Si no hay productos registrados, se muestra un mensaje de error
                        JOptionPane.showMessageDialog(frame, 
                            "No hay productos disponibles. Agregue productos primero.", 
                            "Sin productos", JOptionPane.WARNING_MESSAGE);
                    }
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error al cargar productos: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                productosCombo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                productosCombo.setPreferredSize(new Dimension(fieldWidth * 2, fieldHeight));
                gbc.gridy = 4;
                frame.add(productosCombo, gbc);
                
                // Campo para la cantidad
                JLabel cantidadLabel = new JLabel("Cantidad:");
                cantidadLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(cantidadLabel, gbc);
                
                //Se crea un JSpinner para introducir la cantidad de productos
                final JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
                cantidadSpinner.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                cantidadSpinner.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                // Hacer que el campo de texto no sea editable directamente
                ((DefaultEditor) cantidadSpinner.getEditor()).getTextField().setEditable(false);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(cantidadSpinner, gbc);

                //Se crea un JLabel y un JTextField para el resto de datos de la factura
                JLabel fechaLabel = new JLabel("Fecha de Emisión (YYYY-MM-DD):");
                fechaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 1;
                frame.add(fechaLabel, gbc);
                
                JTextField fechaTextField = new JTextField(10);
                fechaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                fechaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(fechaTextField, gbc);
                
                JLabel metodoPagoLabel = new JLabel("Método de Pago:");
                metodoPagoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 1;
                frame.add(metodoPagoLabel, gbc);
                
                String[] metodosPago = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Transferencia"};
                final JComboBox<String> metodoPagoCombo = new JComboBox<>(metodosPago);
                metodoPagoCombo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                metodoPagoCombo.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                metodoPagoCombo.setEnabled(true);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(metodoPagoCombo, gbc);
                
                JLabel estadoLabel = new JLabel("Estado:");
                estadoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 8;
                gbc.gridwidth = 1;
                frame.add(estadoLabel, gbc);
                
                String[] estados = {"Pagada", "Pendiente", "Cancelada"};
                final JComboBox<String> estadoCombo = new JComboBox<>(estados);
                estadoCombo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                estadoCombo.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                estadoCombo.setEnabled(true);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(estadoCombo, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 9;
                gbc.gridwidth = 3;
                frame.add(statusLabel, gbc);
                
                JButton guardarButton = new JButton("Guardar Factura");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(40, 167, 69));
                guardarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 10;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);

                //Acción para guardar la factura
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene el ID del cliente, el empleado y la fecha de la factura
                            String clienteIdStr = clienteTextField.getText().trim();
                            String empleadoIdStr = empleadoTextField.getText().trim();
                            String fechaStr = fechaTextField.getText().trim();

                            //Se comprueba si los campos son obligatorios
                            if(clienteIdStr.isEmpty() || empleadoIdStr.isEmpty() || fechaStr.isEmpty() || 
                               productosCombo.getSelectedItem() == null) {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }

                            //Se comprueba si la fecha tiene el formato correcto
                            if(!fechaStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                statusLabel.setText("Error: La fecha debe tener formato YYYY-MM-DD");
                                return;
                            }

                            //Se obtiene el ID del cliente y el empleado
                            Long clienteId = Long.parseLong(clienteIdStr);
                            Long empleadoId = Long.parseLong(empleadoIdStr);

                            //Se obtiene el cliente y el empleado de la base de datos
                            Cliente cliente = clienteController.findById(clienteId);
                            if(cliente == null) {
                                statusLabel.setText("Error: El cliente con ID " + clienteId + " no existe");
                                return;
                            }

                            //Se obtiene el empleado de la base de datos
                            Empleado empleado = empleadoController.findById(empleadoId);
                            if(empleado == null) {
                                statusLabel.setText("Error: El empleado con ID " + empleadoId + " no existe");
                                return;
                            }

                            //Se obtiene el método de pago y el estado de la factura
                            String metodoPago = (String)metodoPagoCombo.getSelectedItem();
                            String estado = (String)estadoCombo.getSelectedItem();

                            //Se obtiene el producto y la cantidad
                            String selectedItem = (String)productosCombo.getSelectedItem();
                            Long idProducto = productosMap.get(selectedItem);
                            int cantidad = (Integer)cantidadSpinner.getValue();
                            
                            //Se obtiene el producto
                            Producto producto = productoController.findById(idProducto);
                            if(producto == null) {
                                statusLabel.setText("Error: El producto seleccionado no existe");
                                return;
                            }
                            
                            //Se calcula el total
                            double total = producto.getPVP() * cantidad;

                            //Se crea una nueva factura con los datos introducidos
                            Factura factura = new Factura();
                            factura.setCliente(cliente);
                            factura.setEmpleado(empleado);
                            factura.setProducto(producto);
                            factura.setCantidad(cantidad);
                            factura.setFecha_Venta(LocalDate.parse(fechaStr));
                            factura.setCanal_Compra(metodoPago);
                            factura.setPagado(estado);
                            factura.setTotal(total);

                            //Se guarda la factura
                            facturaController.save(factura);
                            Long facturaId = factura.getID_Factura();
                            
                            statusLabel.setText("Factura guardada correctamente con ID: " + facturaId);
                            
                            //Se limpian los campos
                            clienteTextField.setText("");
                            empleadoTextField.setText("");
                            fechaTextField.setText("");
                            cantidadSpinner.setValue(1);
                            if(productosCombo.getItemCount() > 0) {
                                productosCombo.setSelectedIndex(0);
                            }
                            metodoPagoCombo.setSelectedIndex(0);
                            estadoCombo.setSelectedIndex(0);
                            
                        } catch(NumberFormatException nfe) {
                            statusLabel.setText("Error: Los IDs deben ser números válidos");
                        } catch(Exception ex) {
                            statusLabel.setText("Error al guardar la factura: " + ex.getMessage());
                        }
                    }
                });
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                });
                
            } catch(Exception e) {
                System.err.println("Error al añadir la factura: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error: " + e.getMessage());
                errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 3;
                frame.add(errorLabel, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 3;
                frame.add(volverButton, gbc);
                
                volverButton.addActionListener(e2 -> {
                    frame.dispose();
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                });
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            frame.add(volverButton, gbc);
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuFactura(facturaController, clienteController, empleadoController, productoController);
            });
        }
    }
  
    private void ActualizarFactura() {
        JFrame frame = new JFrame("Actualizar Factura");
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

        JLabel Title = new JLabel("=== ACTUALIZAR FACTURA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        frame.add(Title, gbc);

        if(facturaController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID de la factura a actualizar
                JLabel idLabel = new JLabel("ID de la factura a actualizar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
                final JButton buscarButton = new JButton("Buscar Factura");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 2;
                frame.add(buscarButton, gbc);
            
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 3;
                frame.add(statusLabel, gbc);
                
                final JLabel rutaLabel = new JLabel("");
                rutaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 3;
                frame.add(rutaLabel, gbc);

                //Se crea un JLabel y un JTextField para introducir cada campo de la factura
                JLabel clienteLabel = new JLabel("ID del Cliente:");
                clienteLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 4;
                gbc.gridwidth = 1;
                frame.add(clienteLabel, gbc);
                
                final JTextField clienteTextField = new JTextField(10);
                clienteTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                clienteTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                clienteTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(clienteTextField, gbc);
                
                JButton verClientesButton = new JButton("Ver Clientes");
                verClientesButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verClientesButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
                verClientesButton.setBackground(new Color(0, 123, 255));
                verClientesButton.setForeground(Color.WHITE);
                gbc.gridx = 2;
                frame.add(verClientesButton, gbc);
                
                JLabel empleadoLabel = new JLabel("ID del Empleado:");
                empleadoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(empleadoLabel, gbc);
                
                final JTextField empleadoTextField = new JTextField(10);
                empleadoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                empleadoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                empleadoTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(empleadoTextField, gbc);
                
                JButton verEmpleadosButton = new JButton("Ver Empleados");
                verEmpleadosButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verEmpleadosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
                verEmpleadosButton.setBackground(new Color(0, 123, 255));
                verEmpleadosButton.setForeground(Color.WHITE);
                gbc.gridx = 2;
                frame.add(verEmpleadosButton, gbc);
                
                JLabel fechaLabel = new JLabel("Fecha (YYYY-MM-DD):");
                fechaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 6;
                frame.add(fechaLabel, gbc);
                
                final JTextField fechaTextField = new JTextField(10);
                fechaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                fechaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                fechaTextField.setEnabled(false);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(fechaTextField, gbc);
                
                JLabel metodoPagoLabel = new JLabel("Método de Pago:");
                metodoPagoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 1;
                frame.add(metodoPagoLabel, gbc);
                
                String[] metodosPago = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Transferencia"};
                final JComboBox<String> metodoPagoCombo = new JComboBox<>(metodosPago);
                metodoPagoCombo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                metodoPagoCombo.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                metodoPagoCombo.setEnabled(true);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(metodoPagoCombo, gbc);
                
                JLabel estadoLabel = new JLabel("Estado:");
                estadoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 8;
                gbc.gridwidth = 1;
                frame.add(estadoLabel, gbc);
                
                String[] estados = {"Pagada", "Pendiente", "Cancelada"};
                final JComboBox<String> estadoCombo = new JComboBox<>(estados);
                estadoCombo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                estadoCombo.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                estadoCombo.setEnabled(true);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(estadoCombo, gbc);
                
                JLabel productoLabel = new JLabel("ID del Producto:");
                productoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 9;
                gbc.gridwidth = 1;
                frame.add(productoLabel, gbc);
                
                final JTextField productoTextField = new JTextField(10);
                productoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                productoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                productoTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(productoTextField, gbc);
                
                JButton verProductosButton = new JButton("Ver Productos");
                verProductosButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verProductosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
                verProductosButton.setBackground(new Color(0, 123, 255));
                verProductosButton.setForeground(Color.WHITE);
                gbc.gridx = 2;
                frame.add(verProductosButton, gbc);
                
                JLabel cantidadLabel = new JLabel("Cantidad:");
                cantidadLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 10;
                frame.add(cantidadLabel, gbc);
                
                final JTextField cantidadTextField = new JTextField(10);
                cantidadTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                cantidadTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                cantidadTextField.setEnabled(false);
                gbc.gridx = 1;
                gbc.gridwidth = 2;
                frame.add(cantidadTextField, gbc);
                
                final JButton guardarButton = new JButton("Guardar Cambios");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setEnabled(false);
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(76, 175, 80));
                guardarButton.setForeground(Color.WHITE);
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
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);

                //Se crea un array para almacenar el ID de la factura
                final Long[] facturaId = new Long[1];
                
                //Accion para buscar los clientes
                verClientesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los clientes de la base de datos
                            List<Cliente> clientes = clienteController.findAll();

                            //Si hay clientes registrados, se muestra el listado de clientes
                            if (clientes != null && !clientes.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se guarda el listado de clientes en un StringBuilder
                                for (Cliente cliente : clientes) {
                                    sb.append("ID: ").append(cliente.getID_Cliente())
                                      .append(" - Nombre: ").append(cliente.getNombre_Empresa())
                                      .append(" ").append(cliente.getNombre_Responsable())
                                      .append("\n");
                                }

                                //Se muestra el listado de clientes
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Clientes", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay clientes registrados", 
                                    "Listado de Clientes", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener clientes: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Accion para buscar los empleados
                verEmpleadosButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los empleados de la base de datos
                            List<Empleado> empleados = empleadoController.findAll();

                            //Si hay empleados registrados, se muestra el listado de empleados
                            if (empleados != null && !empleados.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se guarda el listado de empleados en un StringBuilder
                                for (Empleado empleado : empleados) {
                                    sb.append("ID: ").append(empleado.getID_Empleado())
                                      .append(" - Nombre: ").append(empleado.getNombre())
                                      .append(" ").append(empleado.getApellido())
                                      .append("\n");
                                }

                                //Se muestra el listado de empleados
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Empleados", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay empleados registrados", 
                                    "Listado de Empleados", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener empleados: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Accion para buscar los productos
                verProductosButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene todos los productos de la base de datos
                            List<Producto> productos = productoController.findAll();

                            //Si hay productos registrados, se muestra el listado de productos
                            if (productos != null && !productos.isEmpty()) {
                                StringBuilder sb = new StringBuilder();

                                //Se guarda el listado de productos en un StringBuilder
                                for (Producto producto : productos) {
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
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener productos: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Accion para buscar la factura
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);
                            
                            statusLabel.setText("Buscando factura...");

                            //Se busca la factura con el ID introducido
                            Factura factura = facturaController.findById(id);

                            //Si la factura existe, se muestra la información de la factura
                            if (factura != null) {
                                facturaId[0] = factura.getID_Factura();
                                
                                clienteTextField.setText(factura.getCliente().getID_Cliente().toString());
                                empleadoTextField.setText(factura.getEmpleado().getID_Empleado().toString());
                                fechaTextField.setText(factura.getFecha_Venta().toString());
                                metodoPagoCombo.setSelectedItem(factura.getCanal_Compra());
                                estadoCombo.setSelectedItem(factura.getPagado());
                                productoTextField.setText(factura.getProducto().getID_Producto().toString());
                                cantidadTextField.setText(factura.getCantidad().toString());

                                //Se habilitan los campos para que el usuario pueda editarlos
                                clienteTextField.setEnabled(true);
                                empleadoTextField.setEnabled(true);
                                fechaTextField.setEnabled(true);
                                metodoPagoCombo.setEnabled(true);
                                estadoCombo.setEnabled(true);
                                productoTextField.setEnabled(true);
                                cantidadTextField.setEnabled(true);
                                guardarButton.setEnabled(true);
                                
                                statusLabel.setText("Factura encontrada. Modifique los campos necesarios.");
                                rutaLabel.setText("");
                            } else {
                                //Si la factura no existe, se limpia el formulario y se deshabilitan los campos
                                statusLabel.setText("No se encontró una factura con el ID: " + id);
                                rutaLabel.setText("");
                                
                                clienteTextField.setText("");
                                empleadoTextField.setText("");
                                fechaTextField.setText("");
                                metodoPagoCombo.setSelectedIndex(0);
                                estadoCombo.setSelectedIndex(0);
                                productoTextField.setText("");
                                cantidadTextField.setText("");
                                
                                clienteTextField.setEnabled(false);
                                empleadoTextField.setEnabled(false);
                                fechaTextField.setEnabled(false);
                                metodoPagoCombo.setEnabled(false);
                                estadoCombo.setEnabled(false);
                                productoTextField.setEnabled(false);
                                cantidadTextField.setEnabled(false);
                                guardarButton.setEnabled(false);
                            }
                        } catch (NumberFormatException nfe) {
                            //Si el ID introducido no es un número, se muestra un mensaje de error
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            rutaLabel.setText("");
                        }
                    }
                });

                //Accion para guardar la factura
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene la factura con el ID introducido
                            Factura facturaExistente = facturaController.findById(facturaId[0]);
                        
                            //Se obtiene el valor de cada campo de la factura
                            String clienteId = clienteTextField.getText();
                            String empleadoId = empleadoTextField.getText();
                            String fecha = fechaTextField.getText();
                            String metodoPago = (String)metodoPagoCombo.getSelectedItem();
                            String estado = (String)estadoCombo.getSelectedItem();
                            String productoId = productoTextField.getText();
                            String cantidad = cantidadTextField.getText();

                            //Si algún campo está vacío, se muestra un mensaje de error
                            if (clienteId.isEmpty() || empleadoId.isEmpty() || fecha.isEmpty() || 
                                metodoPago.isEmpty() || estado.isEmpty() || 
                                productoId.isEmpty() || cantidad.isEmpty()) {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }

                            //Se obtiene el cliente con el ID introducido
                            Cliente cliente = null;
                            try {
                                long idCliente = Long.parseLong(clienteId);
                                cliente = clienteController.findById(idCliente);
                                if (cliente == null) {
                                    statusLabel.setText("Error: Cliente no encontrado");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: ID de cliente inválido");
                                return;
                            }

                            //Se obtiene el empleado con el ID introducido
                            Empleado empleado = null;
                            try {
                                long idEmpleado = Long.parseLong(empleadoId);
                                empleado = empleadoController.findById(idEmpleado);
                                if (empleado == null) {
                                    statusLabel.setText("Error: Empleado no encontrado");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: ID de empleado inválido");
                                return;
                            }

                            //Se pasa la fecha a un formato LocalDate
                            LocalDate fechaVenta = null;
                            try {
                                fechaVenta = LocalDate.parse(fecha);
                            } catch (Exception ex) {
                                statusLabel.setText("Error: Formato de fecha inválido. Use YYYY-MM-DD");
                                return;
                            }

                            //Se obtiene el producto con el ID introducido
                            Producto producto = null;
                            try {
                                long idProducto = Long.parseLong(productoId);
                                producto = productoController.findById(idProducto);
                                if (producto == null) {
                                    statusLabel.setText("Error: Producto no encontrado");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: ID de producto inválido");
                                return;
                            }

                            //Se convierte la cantidad a un entero
                            Integer cantidadProducto = null;
                            try {
                                cantidadProducto = Integer.parseInt(cantidad);
                                if (cantidadProducto <= 0) {
                                    statusLabel.setText("Error: La cantidad debe ser mayor que cero");
                                    return;
                                }

                                //Si la cantidad es mayor que el stock disponible, se muestra un mensaje de error
                                if (!facturaExistente.getProducto().getID_Producto().equals(producto.getID_Producto()) ||
                                    cantidadProducto > facturaExistente.getCantidad()) {
                                    int stockActual = producto.getStock();
                                    if (!facturaExistente.getProducto().getID_Producto().equals(producto.getID_Producto())) {
                                        if (cantidadProducto > stockActual) {
                                            JOptionPane.showMessageDialog(
                                                frame,
                                                "No hay suficiente stock disponible.\nStock actual: " + stockActual + "\nCantidad solicitada: " + cantidadProducto,
                                                "Error de Stock",
                                                JOptionPane.ERROR_MESSAGE
                                            );
                                            statusLabel.setText("Error: La cantidad no puede superar el stock disponible (" + stockActual + ")");
                                            return;
                                        }
                                    } 
                                    //Si la cantidad es mayor que la cantidad de la factura existente, se muestra un mensaje de error
                                    else if (cantidadProducto > facturaExistente.getCantidad()) {
                                        int stockAdicionalRequerido = cantidadProducto - facturaExistente.getCantidad();
                                        if (stockAdicionalRequerido > stockActual) {
                                            JOptionPane.showMessageDialog(
                                                frame,
                                                "No hay suficiente stock disponible para aumentar la cantidad.\nStock actual: " + stockActual + 
                                                "\nCantidad adicional requerida: " + stockAdicionalRequerido,
                                                "Error de Stock",
                                                JOptionPane.ERROR_MESSAGE
                                            );
                                            statusLabel.setText("Error: No hay suficiente stock para aumentar la cantidad");
                                            return;
                                        }
                                    }
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: Cantidad inválida");
                                return;
                            }

                            //Se crea una nueva factura
                            Factura facturaActualizada = new Factura();
                            facturaActualizada.setID_Factura(facturaId[0]);
                            facturaActualizada.setCliente(cliente);
                            facturaActualizada.setEmpleado(empleado);
                            facturaActualizada.setFecha_Venta(fechaVenta);
                            facturaActualizada.setCanal_Compra(metodoPago);
                            facturaActualizada.setPagado(estado);
                            facturaActualizada.setProducto(producto);
                            facturaActualizada.setCantidad(cantidadProducto);
                            facturaActualizada.setTotal(producto.getPVP() * cantidadProducto);

                            //Se guarda la factura
                            try {
                                facturaController.save(facturaActualizada);
                            
                                //Si existe un archivo de factura, se muestra un mensaje para actualizarlo
                                if (FacturaFileManager.existeArchivoFactura(facturaId[0])) {
                                    int respuesta = JOptionPane.showConfirmDialog(
                                        frame,
                                        "Se ha detectado un archivo de factura existente. ¿Desea actualizarlo con los nuevos datos?",
                                        "Actualizar archivo de factura",
                                        JOptionPane.YES_NO_OPTION
                                    );

                                    //Si el usuario confirma la actualización, se genera un nuevo archivo de factura
                                    if (respuesta == JOptionPane.YES_OPTION) {
                                        String rutaArchivo = FacturaFileManager.generarArchivoFactura(facturaActualizada, true);
                                        if (rutaArchivo != null) {
                                            statusLabel.setText("Factura y archivo actualizados correctamente.");
                                            rutaLabel.setText("Ubicación: " + rutaArchivo);
                                        } else {
                                            statusLabel.setText("Factura actualizada, pero hubo un error al actualizar el archivo.");
                                        }
                                     } else {
                                        statusLabel.setText("Factura actualizada correctamente. No se modificó el archivo existente.");
                                        rutaLabel.setText("Ubicación: " + FacturaFileManager.getFacturaRutaAbsoluta(facturaId[0]));
                                     }
                                } else {
                                    statusLabel.setText("Factura actualizada correctamente.");
                                    rutaLabel.setText("");
                                }
                            } catch (RuntimeException ex) {
                                if (ex.getMessage().contains("No hay suficiente stock disponible")) {
                                    JOptionPane.showMessageDialog(
                                        frame,
                                        ex.getMessage(),
                                        "Error de Stock",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                }
                                statusLabel.setText("Error al actualizar la factura: " + ex.getMessage());
                            }
                        } catch (Exception ex) {
                            statusLabel.setText("Error al actualizar la factura: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                });
            } catch(Exception e) {
                System.err.println("Error al actualizar la factura: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error: " + e.getMessage());
                errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 3;
                frame.add(errorLabel, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 3;
                frame.add(volverButton, gbc);
                
                volverButton.addActionListener(e2 -> {
                    frame.dispose();
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                });
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            frame.add(volverButton, gbc);
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuFactura(facturaController, clienteController, empleadoController, productoController);
            });
        }
    }
  
    private void EliminarFactura() {
        JFrame frame = new JFrame("Eliminar Factura");
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

        JLabel Title = new JLabel("=== ELIMINAR FACTURA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if(facturaController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID de la factura a eliminar
                JLabel idLabel = new JLabel("ID de la factura a eliminar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                //Se crea un JLabel y un JTextArea para mostrar la información de la factura
                final JLabel infoLabel = new JLabel("Información de la factura:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
                
                final JTextArea facturaInfo = new JTextArea();
                facturaInfo.setEditable(false);
                facturaInfo.setBackground(new Color(240, 240, 240));
                facturaInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                facturaInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(facturaInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
                
                JButton buscarButton = new JButton("Buscar Factura");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);
                
                final JButton eliminarButton = new JButton("Eliminar Factura");
                eliminarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                eliminarButton.setEnabled(false);
                eliminarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                eliminarButton.setBackground(new Color(220, 53, 69));
                eliminarButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(eliminarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);
                
                final Long[] facturaId = new Long[1];
                
                //Acción para buscar la factura
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene la factura con el ID introducido
                            Factura factura = facturaController.findById(id);

                            //Si la factura existe, se muestra la información de la factura
                            if (factura != null) {
                                facturaId[0] = factura.getID_Factura();
                                facturaInfo.setText(factura.toString());
                                eliminarButton.setEnabled(true);
                                statusLabel.setText("Factura encontrada. Pulse 'Eliminar Factura' para confirmar.");
                            } else {
                                //Si la factura no existe, se limpia el formulario y se deshabilita el botón de eliminar
                                facturaInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró una factura con el ID: " + id);
                            }
                        } catch (NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            facturaInfo.setText("");
                            eliminarButton.setEnabled(false);
                        }
                    }
                });

                //Acción para eliminar la factura
                eliminarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se muestra un mensaje de confirmación para eliminar la factura
                            int confirmacion = JOptionPane.showConfirmDialog(frame, 
                                "¿Está seguro de que desea eliminar esta factura?", 
                                "Confirmar eliminación", 
                                JOptionPane.YES_NO_OPTION);

                            //Si el usuario confirma la eliminación, se elimina la factura
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                facturaController.delete(facturaId[0]);
                                
                                facturaInfo.setText("");
                                idTextField.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("Factura eliminada correctamente.");
                            }
                        } catch (Exception ex) {
                            //Si ocurre un error, se muestra un mensaje de error
                            statusLabel.setText("Error al eliminar la factura: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
                });
            } catch(Exception e) {
                System.err.println("Error al eliminar la factura: " + e.getMessage());
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
                    new MenuFactura(facturaController, clienteController, empleadoController, productoController);
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
                new MenuFactura(facturaController, clienteController, empleadoController, productoController);
            });
        }
    }


 
    private void GenerarArchivoFacturaPorId()
    {
      JFrame frame = new JFrame("Generar Archivo de Factura");
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
  
      JLabel Title = new JLabel("=== GENERAR ARCHIVO DE FACTURA ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      frame.add(Title, gbc);
 
      if(facturaController!=null)
      {
          try
          {
              //Se crea un JLabel y un JTextField para introducir el ID de la factura a generar
              JLabel idLabel = new JLabel("Introduce el ID de la factura:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              frame.add(idLabel, gbc);
 
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(idTextField, gbc);
              
              JButton generarButton = new JButton("Generar");
              generarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              generarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              generarButton.setBackground(new Color(76, 175, 80));
              generarButton.setForeground(Color.WHITE);
              gbc.gridx = 2;
              frame.add(generarButton, gbc);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 3;
              frame.add(statusLabel, gbc);
              
              final JLabel rutaLabel = new JLabel("");
              rutaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 3;
              frame.add(rutaLabel, gbc);

              //Acción para generar el archivo de factura
              generarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      String input = idTextField.getText();
                      try {
                          long id = Long.parseLong(input);
                          statusLabel.setText("Verificando factura...");
                          rutaLabel.setText("");

                          //Se obtiene la factura con el ID introducido
                          Factura factura = facturaController.findById(id);

                          //Si la factura existe, se verifica si existe un archivo de factura
                          if(factura != null) {
                              if (FacturaFileManager.existeArchivoFactura(id)) {
                                  statusLabel.setText("Ya existe un archivo para esta factura. ¿Desea sobrescribirlo?");

                                  //Se muestra un mensaje de confirmación para sobrescribir el archivo de factura
                                  int opcion = JOptionPane.showConfirmDialog(frame, 
                                      "Ya existe un archivo para la factura con ID " + id + ". ¿Desea sobrescribirlo?", 
                                      "Archivo existente", 
                                      JOptionPane.YES_NO_OPTION);

                                  //Si el usuario confirma la sobrescritura, se sobrescribe el archivo de factura
                                  if (opcion == JOptionPane.YES_OPTION) {
                                      FacturaFileManager.eliminarArchivoFactura(id);
                                      String rutaArchivo = FacturaFileManager.generarArchivoFactura(factura, true);
                                      if (rutaArchivo != null) {
                                          statusLabel.setText("Archivo de factura regenerado correctamente.");
                                          rutaLabel.setText("Ubicación: " + rutaArchivo);
                                      } else {
                                          statusLabel.setText("Factura actualizada, pero hubo un error al actualizar el archivo.");
                                      }
                                  } else {
                                      //Si el usuario no confirma la sobrescritura, se muestra un mensaje de cancelación
                                      statusLabel.setText("Operación cancelada por el usuario.");
                                      rutaLabel.setText("Ubicación: " + FacturaFileManager.getFacturaRutaAbsoluta(id));
                                  }
                              } else {
                                  //Si no existe un archivo de factura, se genera uno nuevo
                                  String rutaArchivo = FacturaFileManager.generarArchivoFactura(factura);
                                  if (rutaArchivo != null) {
                                      statusLabel.setText("Archivo generado correctamente.");
                                      rutaLabel.setText("Ubicación: " + rutaArchivo);
                                  } else {
                                      statusLabel.setText("Error al generar el archivo de factura.");
                                  }
                              }
                          } else {
                              statusLabel.setText("No se encontró la factura con el ID especificado.");
                          }
                      } catch (NumberFormatException nfe) {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                      }
                  }
              });
              
              JButton volverButton = new JButton("Volver");
              volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              gbc.gridy = 4;
              frame.add(volverButton, gbc);
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuFactura(facturaController, clienteController, empleadoController, productoController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al generar el archivo de factura: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
} 