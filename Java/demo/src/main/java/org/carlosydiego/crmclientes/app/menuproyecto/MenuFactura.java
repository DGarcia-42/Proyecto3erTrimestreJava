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

      JLabel Title = new JLabel("=== LISTA DE FACTURAS ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
 
      if(facturaController!=null) {
          try {
              List<Factura> facturas = facturaController.findAll();
              if(facturas!=null && !facturas.isEmpty()) {
                  JPanel panelFacturas = new JPanel();
                  panelFacturas.setLayout(null);
                  
                  JScrollPane scrollPane = new JScrollPane(panelFacturas);
                  scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                  gbc.gridy = 1;
                  frame.add(scrollPane, gbc);
                  
                  panelFacturas.setPreferredSize(new Dimension(textAreaWidth - 50, Math.max(textAreaHeight - 50, facturas.size() * 150)));
                  
                  for(int i = 0; i < facturas.size(); i++) {
                      JTextArea facturaTextArea = new JTextArea(facturas.get(i).toString());
                      facturaTextArea.setBounds(50, 10 + i * 150, textAreaWidth - 150, 130);
                      facturaTextArea.setEditable(false);
                      facturaTextArea.setBackground(new Color(240, 240, 240));
                      facturaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                      facturaTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                      panelFacturas.add(facturaTextArea);
                  }
              } else {
                  JLabel noFacturasLabel = new JLabel("No hay facturas registradas en el sistema");
                  noFacturasLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                  noFacturasLabel.setHorizontalAlignment(SwingConstants.CENTER);
                  gbc.gridy = 1;
                  frame.add(noFacturasLabel, gbc);
              }
          } catch(Exception e) {
              System.err.println("Error al obtener las facturas: " + e.getMessage());
          } 
      } else {
          System.out.println("Error: No hay conexion a la base de datos");
      }
 
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
  
      if(facturaController!=null) {
          try {
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
              
              JButton verClientesButton = new JButton("Ver Clientes");
              verClientesButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              verClientesButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              verClientesButton.setBackground(new Color(0, 123, 255));
              verClientesButton.setForeground(Color.WHITE);
              gbc.gridx = 2;
              frame.add(verClientesButton, gbc);
              
              verClientesButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Cliente> clientes = clienteController.findAll();
                          if(clientes != null && !clientes.isEmpty()) {
                              StringBuilder sb = new StringBuilder();
                              for(Cliente c : clientes) {
                                  sb.append("ID: ").append(c.getID_Cliente())
                                    .append(" - Nombre: ").append(c.getNombre_Empresa())
                                    .append(" ").append(c.getNombre_Responsable())
                                    .append("\n");
                              }
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
              
              JButton verEmpleadosButton = new JButton("Ver Empleados");
              verEmpleadosButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              verEmpleadosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              verEmpleadosButton.setBackground(new Color(0, 123, 255));
              verEmpleadosButton.setForeground(Color.WHITE);
              gbc.gridx = 2;
              frame.add(verEmpleadosButton, gbc);
              
              verEmpleadosButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Empleado> empleados = empleadoController.findAll();
                          if(empleados != null && !empleados.isEmpty()) {
                              StringBuilder sb = new StringBuilder();
                              for(Empleado emp : empleados) {
                                  sb.append("ID: ").append(emp.getID_Empleado())
                                    .append(" - Nombre: ").append(emp.getNombre())
                                    .append(" ").append(emp.getApellido())
                                    .append("\n");
                              }
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
              
              JLabel productosLabel = new JLabel("Productos:");
              productosLabel.setFont(new Font("Roboto", Font.BOLD, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.gridwidth = 3;
              frame.add(productosLabel, gbc);
              
              final JPanel productosPanel = new JPanel(new GridBagLayout());
              productosPanel.setBackground(new Color(245, 247, 250));
              GridBagConstraints panelGbc = new GridBagConstraints();
              panelGbc.fill = GridBagConstraints.HORIZONTAL;
              panelGbc.insets = new Insets(5, 5, 5, 5);
              
              JScrollPane productosScrollPane = new JScrollPane(productosPanel);
              productosScrollPane.setPreferredSize(new Dimension((int)(screenSize.width * 0.6), (int)(screenSize.height * 0.2)));
              gbc.gridy = 4;
              frame.add(productosScrollPane, gbc);
              
              final Map<Long, Integer> productosCantidad = new HashMap<>();
              
              JButton agregarProductoButton = new JButton("Agregar Producto");
              agregarProductoButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              agregarProductoButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
              agregarProductoButton.setBackground(new Color(40, 167, 69));
              agregarProductoButton.setForeground(Color.WHITE);
              gbc.gridy = 5;
              frame.add(agregarProductoButton, gbc);
              
              final int[] productoRow = {0};
              
              agregarProductoButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Producto> productos = productoController.findAll();
                          if(productos != null && !productos.isEmpty()) {
                              
                              JDialog dialog = new JDialog(frame, "Seleccionar Producto", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(500, 300);
                              dialog.setLocationRelativeTo(frame);
                              
                              JPanel dialogPanel = new JPanel(new GridBagLayout());
                              GridBagConstraints dgbc = new GridBagConstraints();
                              dgbc.fill = GridBagConstraints.HORIZONTAL;
                              dgbc.insets = new Insets(5, 5, 5, 5);
                              
                              JComboBox<String> productosCombo = new JComboBox<>();
                              Map<String, Long> productosMap = new HashMap<>();
                              
                              for(Producto p : productos) {
                                  String key = p.getID_Producto() + " - " + p.getNombre() + " - $" + p.getPVP();
                                  productosCombo.addItem(key);
                                  productosMap.put(key, p.getID_Producto());
                              }
                              
                              dgbc.gridx = 0;
                              dgbc.gridy = 0;
                              dgbc.gridwidth = 2;
                              dialogPanel.add(new JLabel("Seleccione un producto:"), dgbc);
                              
                              dgbc.gridy = 1;
                              dialogPanel.add(productosCombo, dgbc);
                              
                              dgbc.gridy = 2;
                              dgbc.gridwidth = 1;
                              dialogPanel.add(new JLabel("Cantidad:"), dgbc);
                              
                              JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
                              dgbc.gridx = 1;
                              dialogPanel.add(cantidadSpinner, dgbc);
                              
                              JPanel buttonPanel = new JPanel();
                              JButton agregarButton = new JButton("Agregar");
                              JButton cancelarButton = new JButton("Cancelar");
                              buttonPanel.add(agregarButton);
                              buttonPanel.add(cancelarButton);
                              
                              agregarButton.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent e) {
                                      try {
                                          String selectedItem = (String)productosCombo.getSelectedItem();
                                          Long idProducto = productosMap.get(selectedItem);
                                          int cantidad = (Integer)cantidadSpinner.getValue();
                                          
                                          if(productosCantidad.containsKey(idProducto)) {
                                              JOptionPane.showMessageDialog(dialog, 
                                                  "Este producto ya está en la factura. Modifique su cantidad.", 
                                                  "Duplicado", JOptionPane.WARNING_MESSAGE);
                                              return;
                                          }
                                          
                                          productosCantidad.put(idProducto, cantidad);
                                          
                                          Producto producto = productoController.findById(idProducto);
                                          
                                          panelGbc.gridx = 0;
                                          panelGbc.gridy = productoRow[0]++;
                                          panelGbc.gridwidth = 1;
                                          
                                          JLabel productoLabel = new JLabel(producto.getNombre() + " - $" + producto.getPVP());
                                          productosPanel.add(productoLabel, panelGbc);
                                          
                                          panelGbc.gridx = 1;
                                          JLabel cantidadLabel = new JLabel("Cantidad: " + cantidad);
                                          productosPanel.add(cantidadLabel, panelGbc);
                                          
                                          panelGbc.gridx = 2;
                                          JLabel totalLabel = new JLabel("Total: $" + (producto.getPVP() * cantidad));
                                          productosPanel.add(totalLabel, panelGbc);
                                          
                                          panelGbc.gridx = 3;
                                          JButton removeButton = new JButton("X");
                                          removeButton.setBackground(Color.RED);
                                          removeButton.setForeground(Color.WHITE);
                                          productosPanel.add(removeButton, panelGbc);
                                          
                                          removeButton.addActionListener(new ActionListener() {
                                              @Override
                                              public void actionPerformed(ActionEvent e) {
                                                  productosCantidad.remove(idProducto);
                                                  productosPanel.remove(productoLabel);
                                                  productosPanel.remove(cantidadLabel);
                                                  productosPanel.remove(totalLabel);
                                                  productosPanel.remove(removeButton);
                                                  productosPanel.revalidate();
                                                  productosPanel.repaint();
                                              }
                                          });
                                          
                                          productosPanel.revalidate();
                                          productosPanel.repaint();
                                          dialog.dispose();
                                      } catch(Exception ex) {
                                          JOptionPane.showMessageDialog(dialog, 
                                              "Error al agregar producto: " + ex.getMessage(), 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                                      }
                                  }
                              });
                              
                              cancelarButton.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent e) {
                                      dialog.dispose();
                                  }
                              });
                              
                              dialog.add(dialogPanel, BorderLayout.CENTER);
                              dialog.add(buttonPanel, BorderLayout.SOUTH);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, 
                                  "No hay productos disponibles. Agregue productos primero.", 
                                  "Sin productos", JOptionPane.WARNING_MESSAGE);
                          }
                      } catch(Exception ex) {
                          JOptionPane.showMessageDialog(frame, 
                              "Error al cargar productos: " + ex.getMessage(), 
                              "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
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
              JComboBox<String> metodoPagoCombo = new JComboBox<>(metodosPago);
              metodoPagoCombo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
              metodoPagoCombo.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
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
              JComboBox<String> estadoCombo = new JComboBox<>(estados);
              estadoCombo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
              estadoCombo.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
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
              
              guardarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String clienteIdStr = clienteTextField.getText().trim();
                          String empleadoIdStr = empleadoTextField.getText().trim();
                          String fechaStr = fechaTextField.getText().trim();
                          
                          if(clienteIdStr.isEmpty() || empleadoIdStr.isEmpty() || fechaStr.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          if(productosCantidad.isEmpty()) {
                              statusLabel.setText("Error: Debe agregar al menos un producto");
                              return;
                          }
                          
                          if(!fechaStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                              statusLabel.setText("Error: La fecha debe tener formato YYYY-MM-DD");
                              return;
                          }
                          
                          Long clienteId = Long.parseLong(clienteIdStr);
                          Long empleadoId = Long.parseLong(empleadoIdStr);
                          
                          Cliente cliente = clienteController.findById(clienteId);
                          if(cliente == null) {
                              statusLabel.setText("Error: El cliente con ID " + clienteId + " no existe");
                              return;
                          }
                          
                          Empleado empleado = empleadoController.findById(empleadoId);
                          if(empleado == null) {
                              statusLabel.setText("Error: El empleado con ID " + empleadoId + " no existe");
                              return;
                          }
                          
                          String metodoPago = (String)metodoPagoCombo.getSelectedItem();
                          String estado = (String)estadoCombo.getSelectedItem();
                          
                          double total = 0.0;
                          for(Map.Entry<Long, Integer> entry : productosCantidad.entrySet()) {
                              Producto producto = productoController.findById(entry.getKey());
                              total += producto.getPVP() * entry.getValue();
                          }
                          
                          Factura factura = new Factura();
                          factura.setCliente(cliente);
                          
                          factura.setEmpleado(empleado);
                          
                          factura.setFecha_Venta(LocalDate.parse(fechaStr));
                          
                          factura.setCanal_Compra(metodoPago);
                          factura.setPagado(estado);
                          
                          factura.setTotal(total);
                          
                          facturaController.save(factura);
                          Long facturaId = factura.getID_Factura();
                          
                          for(Map.Entry<Long, Integer> entry : productosCantidad.entrySet()) {
                              Producto producto = productoController.findById(entry.getKey());
                              factura.setProducto(producto);
                              factura.setCantidad(entry.getValue());
                              facturaController.save(factura);
                          }
                          
                          statusLabel.setText("Factura guardada correctamente con ID: " + facturaId);
                          
                          clienteTextField.setText("");
                          empleadoTextField.setText("");
                          fechaTextField.setText("");
                          metodoPagoCombo.setSelectedIndex(0);
                          estadoCombo.setSelectedIndex(0);
                          productosCantidad.clear();
                          productosPanel.removeAll();
                          productoRow[0] = 0;
                          productosPanel.revalidate();
                          productosPanel.repaint();
                          
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
          }
      } else {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
  
    private void ActualizarFactura()
    {
      JFrame frame = new JFrame("Actualizar Factura");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ACTUALIZAR FACTURA ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(facturaController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID de la factura a actualizar:");
              idLabel.setBounds(100, 70, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 70, 200, 30);
              frame.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Factura");
              buscarButton.setBounds(520, 70, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
          
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 110, 600, 30);
              frame.add(statusLabel);
              
              final JLabel rutaLabel = new JLabel("");
              rutaLabel.setBounds(100, 140, 600, 30);
              frame.add(rutaLabel);
              
              JLabel clienteLabel = new JLabel("ID del Cliente:");
              clienteLabel.setBounds(100, 180, 200, 30);
              frame.add(clienteLabel);
              
              final JTextField clienteTextField = new JTextField(10);
              clienteTextField.setBounds(300, 180, 200, 30);
              clienteTextField.setEnabled(false);
              frame.add(clienteTextField);
              
              JButton verClientesButton = new JButton("Ver Clientes");
              verClientesButton.setBounds(520, 180, 130, 30);
              verClientesButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verClientesButton.setBackground(new Color(0, 123, 255));
              verClientesButton.setForeground(Color.WHITE);
              frame.add(verClientesButton);
              verClientesButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Cliente> clientes = clienteController.findAll();
                          if (clientes != null && !clientes.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Clientes Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(600, 400);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Cliente cliente : clientes) {
                                  JLabel clienteLabel = new JLabel(cliente.toString());
                                  clienteLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  clienteLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                                  panel.add(clienteLabel);
                              }
                              
                              JButton cerrarButton = new JButton("Cerrar");
                              cerrarButton.addActionListener(ae -> dialog.dispose());
                              dialog.add(cerrarButton, BorderLayout.SOUTH);
                              
                              dialog.setLocationRelativeTo(frame);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, "No hay clientes disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                      } catch (Exception ex) {
                          JOptionPane.showMessageDialog(frame, "Error al cargar clientes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
              JLabel empleadoLabel = new JLabel("ID del Empleado:");
              empleadoLabel.setBounds(100, 220, 200, 30);
              frame.add(empleadoLabel);
              
              final JTextField empleadoTextField = new JTextField(10);
              empleadoTextField.setBounds(300, 220, 200, 30);
              empleadoTextField.setEnabled(false);
              frame.add(empleadoTextField);
              
              JButton verEmpleadosButton = new JButton("Ver Empleados");
              verEmpleadosButton.setBounds(520, 220, 130, 30);
              verEmpleadosButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verEmpleadosButton.setBackground(new Color(0, 123, 255));
              verEmpleadosButton.setForeground(Color.WHITE);
              frame.add(verEmpleadosButton);
              verEmpleadosButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Empleado> empleados = empleadoController.findAll();
                          if (empleados != null && !empleados.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Empleados Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(600, 400);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Empleado empleado : empleados) {
                                  JLabel empleadoLabel = new JLabel(empleado.toString());
                                  empleadoLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  empleadoLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                                  panel.add(empleadoLabel);
                              }
                              
                              JButton cerrarButton = new JButton("Cerrar");
                              cerrarButton.addActionListener(ae -> dialog.dispose());
                              dialog.add(cerrarButton, BorderLayout.SOUTH);
                              
                              dialog.setLocationRelativeTo(frame);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, "No hay empleados disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                      } catch (Exception ex) {
                          JOptionPane.showMessageDialog(frame, "Error al cargar empleados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
              JLabel fechaLabel = new JLabel("Fecha (YYYY-MM-DD):");
              fechaLabel.setBounds(100, 260, 200, 30);
              frame.add(fechaLabel);
              
              final JTextField fechaTextField = new JTextField(10);
              fechaTextField.setBounds(300, 260, 200, 30);
              fechaTextField.setEnabled(false);
              frame.add(fechaTextField);
              
              JLabel metodoPagoLabel = new JLabel("Método de Pago:");
              metodoPagoLabel.setBounds(100, 300, 200, 30);
              frame.add(metodoPagoLabel);
              
              final JTextField metodoPagoTextField = new JTextField(10);
              metodoPagoTextField.setBounds(300, 300, 200, 30);
              metodoPagoTextField.setEnabled(false);
              frame.add(metodoPagoTextField);
              
              JLabel estadoLabel = new JLabel("Estado (SI/NO):");
              estadoLabel.setBounds(100, 340, 200, 30);
              frame.add(estadoLabel);
              
              final JTextField estadoTextField = new JTextField(10);
              estadoTextField.setBounds(300, 340, 200, 30);
              estadoTextField.setEnabled(false);
              frame.add(estadoTextField);
              
              JLabel productoLabel = new JLabel("ID del Producto:");
              productoLabel.setBounds(100, 380, 200, 30);
              frame.add(productoLabel);
              
              final JTextField productoTextField = new JTextField(10);
              productoTextField.setBounds(300, 380, 200, 30);
              productoTextField.setEnabled(false);
              frame.add(productoTextField);
              
              JButton verProductosButton = new JButton("Ver Productos");
              verProductosButton.setBounds(520, 380, 130, 30);
              verProductosButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verProductosButton.setBackground(new Color(0, 123, 255));
              verProductosButton.setForeground(Color.WHITE);
              frame.add(verProductosButton);
              verProductosButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Producto> productos = productoController.findAll();
                          if (productos != null && !productos.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Productos Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(400, 300);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Producto producto : productos) {
                                  JLabel productoLabel = new JLabel(producto.toString());
                                  productoLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  productoLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                                  panel.add(productoLabel);
                              }
                              
                              JButton cerrarButton = new JButton("Cerrar");
                              cerrarButton.addActionListener(ae -> dialog.dispose());
                              dialog.add(cerrarButton, BorderLayout.SOUTH);
                              
                              dialog.setLocationRelativeTo(frame);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, "No hay productos disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                      } catch (Exception ex) {
                          JOptionPane.showMessageDialog(frame, "Error al cargar productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
              JLabel cantidadLabel = new JLabel("Cantidad:");
              cantidadLabel.setBounds(100, 420, 200, 30);
              frame.add(cantidadLabel);
              
              final JTextField cantidadTextField = new JTextField(10);
              cantidadTextField.setBounds(300, 420, 200, 30);
              cantidadTextField.setEnabled(false);
              frame.add(cantidadTextField);
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setBounds(100, 460, 200, 30);
              guardarButton.setEnabled(false);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              frame.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 460, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
              
              final Long[] facturaId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Factura factura = facturaController.findById(id);
                          if (factura != null) {
                              facturaId[0] = factura.getID_Factura();
                              
                              clienteTextField.setText(factura.getCliente().getID_Cliente().toString());
                              empleadoTextField.setText(factura.getEmpleado().getID_Empleado().toString());
                              fechaTextField.setText(factura.getFecha_Venta().toString());
                              metodoPagoTextField.setText(factura.getCanal_Compra());
                              estadoTextField.setText(factura.getPagado());
                              productoTextField.setText(factura.getProducto().getID_Producto().toString());
                              cantidadTextField.setText(factura.getCantidad().toString());
                              
                              clienteTextField.setEnabled(true);
                              empleadoTextField.setEnabled(true);
                              fechaTextField.setEnabled(true);
                              metodoPagoTextField.setEnabled(true);
                              estadoTextField.setEnabled(true);
                              productoTextField.setEnabled(true);
                              cantidadTextField.setEnabled(true);
                              guardarButton.setEnabled(true);
                              
                              statusLabel.setText("Factura encontrada. Modifique los campos necesarios.");
                              rutaLabel.setText("");
                          } 
                          else 
                          {
                              statusLabel.setText("No se encontró una factura con el ID: " + id);
                              rutaLabel.setText("");
                              
                              clienteTextField.setText("");
                              empleadoTextField.setText("");
                              fechaTextField.setText("");
                              metodoPagoTextField.setText("");
                              estadoTextField.setText("");
                              productoTextField.setText("");
                              cantidadTextField.setText("");
                              
                              clienteTextField.setEnabled(false);
                              empleadoTextField.setEnabled(false);
                              fechaTextField.setEnabled(false);
                              metodoPagoTextField.setEnabled(false);
                              estadoTextField.setEnabled(false);
                              productoTextField.setEnabled(false);
                              cantidadTextField.setEnabled(false);
                              guardarButton.setEnabled(false);
                          }
                      } catch (NumberFormatException nfe) {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          rutaLabel.setText("");
                      }
                  }
              });
              
              guardarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          Factura facturaExistente = facturaController.findById(facturaId[0]);
                      
                          String clienteId = clienteTextField.getText();
                          String empleadoId = empleadoTextField.getText();
                          String fecha = fechaTextField.getText();
                          String metodoPago = metodoPagoTextField.getText();
                          String estado = estadoTextField.getText();
                          String productoId = productoTextField.getText();
                          String cantidad = cantidadTextField.getText();
                          
                          if (clienteId.isEmpty() || empleadoId.isEmpty() || fecha.isEmpty() || 
                              metodoPago.isEmpty() || estado.isEmpty() || 
                              productoId.isEmpty() || cantidad.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          estado = estado.toUpperCase();
                          if (!estado.equals("SI") && !estado.equals("NO")) {
                              statusLabel.setText("Error: El estado debe ser SI o NO");
                              return;
                          }
                          
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
                          
                          LocalDate fechaVenta = null;
                          try {
                              fechaVenta = LocalDate.parse(fecha);
                          } catch (Exception ex) {
                              statusLabel.setText("Error: Formato de fecha inválido. Use YYYY-MM-DD");
                              return;
                          }
                          
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
                          
                          Integer cantidadProducto = null;
                          try {
                              cantidadProducto = Integer.parseInt(cantidad);
                              if (cantidadProducto <= 0) {
                                  statusLabel.setText("Error: La cantidad debe ser mayor que cero");
                                  return;
                              }
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
                          
                          try {
                              facturaController.save(facturaActualizada);
                          
                              if (FacturaFileManager.existeArchivoFactura(facturaId[0])) {
                                  int respuesta = JOptionPane.showConfirmDialog(
                                      frame,
                                      "Se ha detectado un archivo de factura existente. ¿Desea actualizarlo con los nuevos datos?",
                                      "Actualizar archivo de factura",
                                      JOptionPane.YES_NO_OPTION
                                  );
                                  
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
          }
          catch(Exception e)
          {
              System.err.println("Error al actualizar la factura: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
  
    private void EliminarFactura()
    {
      JFrame frame = new JFrame("Eliminar Factura");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ELIMINAR FACTURA ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(facturaController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID de la factura a eliminar:");
              idLabel.setBounds(100, 100, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 100, 200, 30);
              frame.add(idTextField);
              
              final JLabel infoLabel = new JLabel("Información de la factura:");
              infoLabel.setBounds(100, 150, 500, 30);
              frame.add(infoLabel);
              
              final JTextArea facturaInfo = new JTextArea();
              facturaInfo.setBounds(100, 190, 600, 150);
              facturaInfo.setEditable(false);
              facturaInfo.setBackground(new Color(240, 240, 240));
              facturaInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              frame.add(facturaInfo);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 350, 600, 30);
              frame.add(statusLabel);
              
              final JButton buscarButton = new JButton("Buscar Factura");
              buscarButton.setBounds(550, 100, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
              
              final JButton eliminarButton = new JButton("Eliminar Factura");
              eliminarButton.setBounds(200, 400, 200, 30);
              eliminarButton.setEnabled(false);
              eliminarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              eliminarButton.setBackground(new Color(0, 123, 255));
              eliminarButton.setForeground(Color.WHITE);
              frame.add(eliminarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(420, 400, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
              
              final Long[] facturaId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) 
                  {
                      try 
                      {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Factura factura = facturaController.findById(id);
                          if (factura != null) 
                          {
                              facturaId[0] = factura.getID_Factura();
                              facturaInfo.setText(factura.toString());
                              eliminarButton.setEnabled(true);
                              statusLabel.setText("Factura encontrada. Pulse 'Eliminar Factura' para confirmar.");
                          } 
                          else 
                          {
                              facturaInfo.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("No se encontró una factura con el ID: " + id);
                          }
                      } 
                      catch (NumberFormatException nfe) 
                      {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          facturaInfo.setText("");
                          eliminarButton.setEnabled(false);
                      }
                  }
              });
              
              eliminarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) 
                  {
                      try 
                      {
                          int confirmacion = JOptionPane.showConfirmDialog(frame, 
                              "¿Está seguro de que desea eliminar esta factura?", 
                              "Confirmar eliminación", 
                              JOptionPane.YES_NO_OPTION);
                          
                          if (confirmacion == JOptionPane.YES_OPTION) 
                          {
                              facturaController.delete(facturaId[0]);
                              
                              facturaInfo.setText("");
                              idTextField.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("Factura eliminada correctamente.");
                          }
                      } 
                      catch (Exception ex) 
                      {
                          statusLabel.setText("Error al eliminar la factura: " + ex.getMessage());
                      }
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuFactura(facturaController, clienteController, empleadoController, productoController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al eliminar la factura: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
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
      
      if(facturaController!=null) {
          try {
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
            
            JButton buscarButton = new JButton("Buscar");
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
            
            final JTextArea facturaTextArea = new JTextArea();
            facturaTextArea.setEditable(false);
            facturaTextArea.setBackground(new Color(240, 240, 240));
            facturaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            facturaTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
            facturaTextArea.setVisible(false);
            JScrollPane scrollPane = new JScrollPane(facturaTextArea);
            scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
            gbc.gridy = 4;
            frame.add(scrollPane, gbc);
            
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = idTextField.getText();
                    try {
                        long id = Long.parseLong(input);
                        statusLabel.setText("Buscando factura...");
                        
                        Factura factura = facturaController.findById(id);
                        if(factura!=null) {
                            statusLabel.setText("Factura encontrada:");
                            facturaTextArea.setText(factura.toString());
                            facturaTextArea.setVisible(true);
                            scrollPane.setVisible(true);
                            frame.revalidate();
                            frame.repaint();
                        } else {
                            statusLabel.setText("Factura no encontrada");
                            facturaTextArea.setVisible(false);
                            scrollPane.setVisible(false);
                            frame.revalidate();
                            frame.repaint();
                        }
                    } catch(NumberFormatException nfe) {
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        facturaTextArea.setVisible(false);
                        scrollPane.setVisible(false);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            });
            
            JButton volverButton = new JButton("Volver");
            volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            gbc.gridy = 5;
            frame.add(volverButton, gbc);
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuFactura(facturaController, clienteController, empleadoController, productoController);
            });
          } catch(Exception e) {
              System.err.println("Error al buscar la factura: " + e.getMessage());
          }
      } else {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
 
    private void GenerarArchivoFacturaPorId()
    {
      JFrame frame = new JFrame("Generar Archivo de Factura");
      frame.setLayout(null);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
 
      JLabel Title = new JLabel("\n=== GENERAR ARCHIVO DE FACTURA ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
 
      if(facturaController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("Introduce el ID de la factura:");
              idLabel.setBounds(100, 100, 200, 30);
              frame.add(idLabel);
 
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 100, 200, 30);
              frame.add(idTextField);
              
              JButton generarButton = new JButton("Generar");
              generarButton.setBounds(550, 100, 150, 30);
              generarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              generarButton.setBackground(new Color(76, 175, 80));
              generarButton.setForeground(Color.WHITE);
              frame.add(generarButton);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 150, 600, 30);
              frame.add(statusLabel);
              
              final JLabel rutaLabel = new JLabel("");
              rutaLabel.setBounds(100, 180, 600, 30);
              frame.add(rutaLabel);
              
              generarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      String input = idTextField.getText();
                      try {
                          long id = Long.parseLong(input);
                          statusLabel.setText("Verificando factura...");
                          rutaLabel.setText("");
                          
                          Factura factura = facturaController.findById(id);
                          if(factura != null) {
                              if (FacturaFileManager.existeArchivoFactura(id)) {
                                  statusLabel.setText("Ya existe un archivo para esta factura. ¿Desea sobrescribirlo?");
                                  
                                  int opcion = JOptionPane.showConfirmDialog(frame, 
                                      "Ya existe un archivo para la factura con ID " + id + ". ¿Desea sobrescribirlo?", 
                                      "Archivo existente", 
                                      JOptionPane.YES_NO_OPTION);
                                  
                                  if (opcion == JOptionPane.YES_OPTION) {
                                      FacturaFileManager.eliminarArchivoFactura(id);
                                      String rutaArchivo = FacturaFileManager.generarArchivoFactura(factura, true);
                                      if (rutaArchivo != null) {
                                          statusLabel.setText("Archivo de factura regenerado correctamente.");
                                          rutaLabel.setText("Ubicación: " + rutaArchivo);
                                      } else {
                                          statusLabel.setText("Error al regenerar el archivo de factura.");
                                      }
                                  } else {
                                      statusLabel.setText("Operación cancelada por el usuario.");
                                      rutaLabel.setText("Ubicación: " + FacturaFileManager.getFacturaRutaAbsoluta(id));
                                  }
                              } else {
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
              volverButton.setBounds(300, 400, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
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