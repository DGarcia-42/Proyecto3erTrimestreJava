package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.time.LocalDate;

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
        setLayout(null);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menu Factura");
        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel Title = new JLabel("\n=== GESTIÓN DE FACTURAS ===");
        Title.setBounds(300, 10, 300, 50);
        Title.setFont(new Font("Roboto", Font.BOLD, 14));
        Title.setForeground(new Color(46, 46, 46));
        add(Title);

        JButton ListarFacturasBtn = new JButton("Ver todas las facturas");
        ListarFacturasBtn.setBounds(100, 100, 200, 50);
        ListarFacturasBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        ListarFacturasBtn.setBackground(new Color(0, 123, 255));
        ListarFacturasBtn.setForeground(Color.WHITE);
        add(ListarFacturasBtn);
        ListarFacturasBtn.addActionListener(e -> {
            dispose();
            ListarFacturas();
        });
        
        JButton BuscarFacturaBtn = new JButton("Buscar factura por ID");
        BuscarFacturaBtn.setBounds(100, 150, 200, 50);
        BuscarFacturaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        BuscarFacturaBtn.setBackground(new Color(0, 123, 255));
        BuscarFacturaBtn.setForeground(Color.WHITE);
        add(BuscarFacturaBtn);
        BuscarFacturaBtn.addActionListener(e -> {
            dispose();
            BuscarFactura();
        });
        
        JButton AñadirFacturaBtn = new JButton("Añadir nueva factura");
        AñadirFacturaBtn.setBounds(100, 200, 200, 50);
        AñadirFacturaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        AñadirFacturaBtn.setBackground(new Color(76, 175, 80));
        AñadirFacturaBtn.setForeground(Color.WHITE);
        add(AñadirFacturaBtn);
        AñadirFacturaBtn.addActionListener(e -> {
            dispose();
            AñadirFactura();
        });

        JButton ActualizarFacturaBtn = new JButton("Actualizar factura");
        ActualizarFacturaBtn.setBounds(100, 250, 200, 50);
        ActualizarFacturaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        ActualizarFacturaBtn.setBackground(new Color(0, 123, 255));
        ActualizarFacturaBtn.setForeground(Color.WHITE);
        add(ActualizarFacturaBtn);
        ActualizarFacturaBtn.addActionListener(e -> {
            dispose();
            ActualizarFactura();
        });
        
        JButton EliminarFacturaBtn = new JButton("Eliminar factura");
        EliminarFacturaBtn.setBounds(100, 300, 200, 50);
        EliminarFacturaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        EliminarFacturaBtn.setBackground(new Color(0, 123, 255));
        EliminarFacturaBtn.setForeground(Color.WHITE);
        add(EliminarFacturaBtn);
        EliminarFacturaBtn.addActionListener(e -> {
            dispose();
            EliminarFactura();
        });

        JButton GenerarArchivoBtn = new JButton("Generar archivo de factura");
        GenerarArchivoBtn.setBounds(100, 350, 200, 50);
        GenerarArchivoBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        GenerarArchivoBtn.setBackground(new Color(0, 123, 255));
        GenerarArchivoBtn.setForeground(Color.WHITE);
        add(GenerarArchivoBtn);
        GenerarArchivoBtn.addActionListener(e -> {
            dispose();
            GenerarArchivoFacturaPorId();
        });

        JButton VolverBtn = new JButton("Volver al menu principal");
        VolverBtn.setBounds(100, 400, 200, 50);
        VolverBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        VolverBtn.setBackground(new Color(0, 123, 255));
        VolverBtn.setForeground(Color.WHITE);
        add(VolverBtn);
        VolverBtn.addActionListener(e -> {
            dispose();
            new MenuProyecto();
        });

        setVisible(true);
    }

    private void ListarFacturas()
    {
      JFrame frame = new JFrame("Listar Facturas");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
 
      JLabel Title = new JLabel("\n=== LISTA DE FACTURAS ===");
      Title.setBounds(300, 10, 200, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
 
      if(facturaController!=null)
      {
          try
          {
              List<Factura> facturas = facturaController.findAll();
              if(facturas!=null && !facturas.isEmpty())
              {
                  JPanel panelFacturas = new JPanel();
                  panelFacturas.setLayout(null);
                  
                  JScrollPane scrollPane = new JScrollPane(panelFacturas);
                  scrollPane.setBounds(50, 70, 700, 400);
                  frame.add(scrollPane);
                  
                  panelFacturas.setPreferredSize(new Dimension(680, Math.max(380, facturas.size() * 150)));
                  
                  for(int i = 0; i < facturas.size(); i++)
                  {
                      JTextArea facturaTextArea = new JTextArea(facturas.get(i).toString());
                      facturaTextArea.setBounds(50, 10 + i * 150, 600, 150);
                      facturaTextArea.setEditable(false);
                      facturaTextArea.setBackground(new Color(240, 240, 240));
                      facturaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                      facturaTextArea.setFont(new Font("Roboto", Font.PLAIN, 12));
                      panelFacturas.add(facturaTextArea);
                  }
              }
              else
              {
                  JLabel noFacturasLabel = new JLabel("No hay facturas registradas en el sistema");
                  noFacturasLabel.setBounds(300, 200, 300, 30);
                  noFacturasLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                  frame.add(noFacturasLabel);
              }
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener las facturas: " + e.getMessage());
          } 
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
 
      JButton volverButton = new JButton("Volver");
      volverButton.setBounds(300, 500, 200, 30);
      volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
      volverButton.setBackground(new Color(0, 123, 255));
      volverButton.setForeground(Color.WHITE);
      frame.add(volverButton);
      volverButton.addActionListener(e -> {
          frame.dispose();
          new MenuFactura(facturaController, clienteController, empleadoController, productoController);
      });
    }
    private void AñadirFactura()
    {
      JFrame frame = new JFrame("Añadir Factura");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== AÑADIR NUEVA FACTURA ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(facturaController!=null)
      {
          try
          {
              JLabel clienteLabel = new JLabel("ID del Cliente:");
              clienteLabel.setBounds(100, 70, 200, 30);
              frame.add(clienteLabel);
              
              JTextField clienteTextField = new JTextField(10);
              clienteTextField.setBounds(350, 70, 200, 30);
              frame.add(clienteTextField);
              
              JButton verClientesButton = new JButton("Ver Clientes");
              verClientesButton.setBounds(560, 70, 130, 30);
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
                              dialog.setSize(400, 300);
                              
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
              empleadoLabel.setBounds(100, 110, 200, 30);
              frame.add(empleadoLabel);
              
              JTextField empleadoTextField = new JTextField(10);
              empleadoTextField.setBounds(350, 110, 200, 30);
              frame.add(empleadoTextField);
              
              JButton verEmpleadosButton = new JButton("Ver Empleados");
              verEmpleadosButton.setBounds(560, 110, 130, 30);
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
                              dialog.setSize(400, 300);
                              
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
              fechaLabel.setBounds(100, 150, 200, 30);
              frame.add(fechaLabel);
              
              JTextField fechaTextField = new JTextField(10);
              fechaTextField.setBounds(350, 150, 200, 30);
              frame.add(fechaTextField);
              
              JLabel metodoPagoLabel = new JLabel("Método de Pago:");
              metodoPagoLabel.setBounds(100, 190, 200, 30);
              frame.add(metodoPagoLabel);
              
              JTextField metodoPagoTextField = new JTextField(10);
              metodoPagoTextField.setBounds(350, 190, 200, 30);
              frame.add(metodoPagoTextField);
              
              JLabel estadoLabel = new JLabel("Estado (SI/NO):");
              estadoLabel.setBounds(100, 230, 200, 30);
              frame.add(estadoLabel);
              
              JTextField estadoTextField = new JTextField(10);
              estadoTextField.setBounds(350, 230, 200, 30);
              frame.add(estadoTextField);
              
              JLabel productoLabel = new JLabel("ID del Producto:");
              productoLabel.setBounds(100, 270, 200, 30);
              frame.add(productoLabel);
              
              JTextField productoTextField = new JTextField(10);
              productoTextField.setBounds(350, 270, 200, 30);
              frame.add(productoTextField);
              
              JButton verProductosButton = new JButton("Ver Productos");
              verProductosButton.setBounds(560, 270, 130, 30);
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
              cantidadLabel.setBounds(100, 310, 200, 30);
              frame.add(cantidadLabel);
              
              JTextField cantidadTextField = new JTextField(10);
              cantidadTextField.setBounds(350, 310, 200, 30);
              frame.add(cantidadTextField);
              
              JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 390, 600, 30);
              frame.add(statusLabel);
          
              JButton guardarButton = new JButton("Guardar Factura");
              guardarButton.setBounds(100, 350, 200, 30);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              frame.add(guardarButton);
              guardarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                      try {
                          // Obtener valores de los campos
                          String clienteId = clienteTextField.getText();
                          String empleadoId = empleadoTextField.getText();
                          String fecha = fechaTextField.getText();
                          String metodoPago = metodoPagoTextField.getText();
                          String estado = estadoTextField.getText();
                          String productoId = productoTextField.getText();
                          String cantidad = cantidadTextField.getText();
                          
                          // Verificar que ningún campo esté vacío
                          if (clienteId.isEmpty() || empleadoId.isEmpty() || fecha.isEmpty() || 
                              metodoPago.isEmpty() || estado.isEmpty() || 
                              productoId.isEmpty() || cantidad.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          // Validar formato de estado
                          estado = estado.toUpperCase();
                          if (!estado.equals("SI") && !estado.equals("NO")) {
                              statusLabel.setText("Error: El estado debe ser SI o NO");
                              return;
                          }
                          
                          // Convertir ID de cliente
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
                          
                          // Convertir ID de empleado
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
                          
                          // Convertir fecha
                          LocalDate fechaVenta = null;
                          try {
                              fechaVenta = LocalDate.parse(fecha);
                          } catch (Exception ex) {
                              statusLabel.setText("Error: Formato de fecha inválido. Use YYYY-MM-DD");
                              return;
                          }
                          
                          // Convertir ID de producto
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
                          
                          // Convertir cantidad
                          Integer cantidadProducto = null;
                          try {
                              cantidadProducto = Integer.parseInt(cantidad);
                              if (cantidadProducto <= 0) {
                                  statusLabel.setText("Error: La cantidad debe ser mayor que cero");
                                  return;
                              }
                              if (cantidadProducto > producto.getStock()) {
                                  // Mostrar mensaje de error en un cuadro de diálogo para mayor visibilidad
                                  JOptionPane.showMessageDialog(
                                      frame,
                                      "No hay suficiente stock disponible.\nStock actual: " + producto.getStock() + "\nCantidad solicitada: " + cantidadProducto,
                                      "Error de Stock",
                                      JOptionPane.ERROR_MESSAGE
                                  );
                                  statusLabel.setText("Error: La cantidad no puede superar el stock disponible (" + producto.getStock() + ")");
                                  return;
                              }
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: Cantidad inválida");
                              return;
                          }
                          
                          // Calcular el total
                          double total = producto.getPVP() * cantidadProducto;
                          
                          // Crear factura
                          Factura nuevaFactura = new Factura();
                          nuevaFactura.setCliente(cliente);
                          nuevaFactura.setEmpleado(empleado);
                          nuevaFactura.setFecha_Venta(fechaVenta);
                          nuevaFactura.setCanal_Compra(metodoPago);
                          nuevaFactura.setPagado(estado);
                          nuevaFactura.setProducto(producto);
                          nuevaFactura.setCantidad(cantidadProducto);
                          nuevaFactura.setTotal(total);
                          
                          try {
                              facturaController.save(nuevaFactura);
                              statusLabel.setText("Factura añadida correctamente. Total: " + total + "€");
                              
                              // Limpiar campos
                              clienteTextField.setText("");
                              empleadoTextField.setText("");
                              fechaTextField.setText("");
                              metodoPagoTextField.setText("");
                              estadoTextField.setText("");
                              productoTextField.setText("");
                              cantidadTextField.setText("");
                          } catch (RuntimeException ex) {
                              // Mostrar mensaje de error en un cuadro de diálogo para mayor visibilidad
                              if (ex.getMessage().contains("No hay suficiente stock disponible")) {
                                  JOptionPane.showMessageDialog(
                                      frame,
                                      ex.getMessage(),
                                      "Error de Stock",
                                      JOptionPane.ERROR_MESSAGE
                                  );
                              }
                              statusLabel.setText("Error al guardar la factura: " + ex.getMessage());
                          }
                      } 
                      catch (Exception ex) 
                      {
                          statusLabel.setText("Error al guardar la factura: " + ex.getMessage());
                      }
                  }
              });
              
          
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 350, 200, 30);
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
              System.err.println("Error al añadir la factura: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
  
    private void ActualizarFactura()
    {
      JFrame frame = new JFrame("Actualizar Factura");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                      
                          // Obtener valores de los campos
                          String clienteId = clienteTextField.getText();
                          String empleadoId = empleadoTextField.getText();
                          String fecha = fechaTextField.getText();
                          String metodoPago = metodoPagoTextField.getText();
                          String estado = estadoTextField.getText();
                          String productoId = productoTextField.getText();
                          String cantidad = cantidadTextField.getText();
                          
                          // Validaciones
                          if (clienteId.isEmpty() || empleadoId.isEmpty() || fecha.isEmpty() || 
                              metodoPago.isEmpty() || estado.isEmpty() || 
                              productoId.isEmpty() || cantidad.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          // Validar formato de estado
                          estado = estado.toUpperCase();
                          if (!estado.equals("SI") && !estado.equals("NO")) {
                              statusLabel.setText("Error: El estado debe ser SI o NO");
                              return;
                          }
                          
                          // Convertir ID de cliente
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
                          
                          // Convertir ID de empleado
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
                          
                          // Convertir fecha
                          LocalDate fechaVenta = null;
                          try {
                              fechaVenta = LocalDate.parse(fecha);
                          } catch (Exception ex) {
                              statusLabel.setText("Error: Formato de fecha inválido. Use YYYY-MM-DD");
                              return;
                          }
                          
                          // Convertir ID de producto
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
                          
                          // Convertir cantidad
                          Integer cantidadProducto = null;
                          try {
                              cantidadProducto = Integer.parseInt(cantidad);
                              if (cantidadProducto <= 0) {
                                  statusLabel.setText("Error: La cantidad debe ser mayor que cero");
                                  return;
                              }
                              // Verificar stock si es un producto diferente o si la cantidad aumentó
                              if (!facturaExistente.getProducto().getID_Producto().equals(producto.getID_Producto()) ||
                                  cantidadProducto > facturaExistente.getCantidad()) {
                                  int stockActual = producto.getStock();
                                  // Si cambiamos a otro producto, verificamos el stock completo
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
                                  // Si es el mismo producto pero aumentamos la cantidad
                                  else if (cantidadProducto > facturaExistente.getCantidad()) {
                                      // Calcular cuánto stock adicional necesitamos
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
                          
                          // Crear factura actualizada
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
                          
                              // Verificar si existe un archivo de factura para actualizarlo
                              if (FacturaFileManager.existeArchivoFactura(facturaId[0])) {
                                  int respuesta = JOptionPane.showConfirmDialog(
                                      frame,
                                      "Se ha detectado un archivo de factura existente. ¿Desea actualizarlo con los nuevos datos?",
                                      "Actualizar archivo de factura",
                                      JOptionPane.YES_NO_OPTION
                                  );
                                  
                                  if (respuesta == JOptionPane.YES_OPTION) {
                                      // Generar un nuevo archivo forzando la sobreescritura
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
                              // Mostrar mensaje de error en un cuadro de diálogo para mayor visibilidad
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
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
    private void BuscarFactura()
    {
      JFrame frame = new JFrame("Buscar Factura");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
 
      JLabel Title = new JLabel("\n=== BUSCAR FACTURA ===");
      Title.setBounds(300, 10, 200, 50);
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
            
            JButton buscarButton = new JButton("Buscar");
            buscarButton.setBounds(550, 100, 150, 30);
            buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
            buscarButton.setBackground(new Color(0, 123, 255));
            buscarButton.setForeground(Color.WHITE);
            frame.add(buscarButton);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 150, 600, 30);
            frame.add(statusLabel);
            
            
            final JTextArea facturaTextArea = new JTextArea();
            facturaTextArea.setBounds(100, 190, 600, 180);
            facturaTextArea.setEditable(false);
            facturaTextArea.setBackground(new Color(240, 240, 240));
            facturaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            facturaTextArea.setVisible(false);
            frame.add(facturaTextArea);
            
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = idTextField.getText();
                    try
                    {
                        long id = Long.parseLong(input);
                        statusLabel.setText("Buscando factura...");
                        
                        Factura factura = facturaController.findById(id);
                        if(factura!=null)
                        {
                            statusLabel.setText("Factura encontrada:");
                            facturaTextArea.setText(factura.toString());
                            facturaTextArea.setVisible(true);
                            frame.repaint();
                        }
                        else
                        {
                            statusLabel.setText("Factura no encontrada");
                            facturaTextArea.setVisible(false);
                            frame.repaint();
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        facturaTextArea.setVisible(false);
                        frame.repaint();
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
              System.err.println("Error al buscar la factura: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
 
    private void GenerarArchivoFacturaPorId()
    {
      JFrame frame = new JFrame("Generar Archivo de Factura");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                              // Verificar si ya existe un archivo para esta factura
                              if (FacturaFileManager.existeArchivoFactura(id)) {
                                  statusLabel.setText("Ya existe un archivo para esta factura. ¿Desea sobrescribirlo?");
                                  
                                  int opcion = JOptionPane.showConfirmDialog(frame, 
                                      "Ya existe un archivo para la factura con ID " + id + ". ¿Desea sobrescribirlo?", 
                                      "Archivo existente", 
                                      JOptionPane.YES_NO_OPTION);
                                  
                                  if (opcion == JOptionPane.YES_OPTION) {
                                      // Si el usuario confirma, eliminar el archivo existente y crear uno nuevo
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
                                  // Si no existe, generarlo
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