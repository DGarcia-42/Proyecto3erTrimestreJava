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

    private void ListarProvees()
    {
      JFrame frame = new JFrame("Listar Provees");
      frame.setLayout(null);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
  
      JLabel Title = new JLabel("\n=== LISTA DE PEDIDOS ===");
      Title.setBounds(300, 10, 200, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(proveeController!=null)
      {
          try
          {
              List<Provee> provees = proveeController.findAll();
              if(provees!=null)
              {
                  JPanel panelProvees = new JPanel();
                  panelProvees.setLayout(null);
                  
                  JScrollPane scrollPane = new JScrollPane(panelProvees);
                  scrollPane.setBounds(50, 70, 700, 400);
                  frame.add(scrollPane);
                  
                  panelProvees.setPreferredSize(new Dimension(680, Math.max(380, provees.size() * 150)));
                  
                  for(int i = 0; i < provees.size(); i++)
                  {
                      JTextArea proveeTextArea = new JTextArea(provees.get(i).toString());
                      proveeTextArea.setBounds(50, 10 + i * 150, 600, 130);
                      proveeTextArea.setEditable(false);
                      proveeTextArea.setBackground(new Color(240, 240, 240));
                      proveeTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                      proveeTextArea.setFont(new Font("Roboto", Font.PLAIN, 12));
                      panelProvees.add(proveeTextArea);
                  }
              }
              else
              {
                  JLabel noProveesLabel = new JLabel("No hay pedidos registrados en el sistema");
                  noProveesLabel.setBounds(300, 200, 300, 30);
                  noProveesLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                  frame.add(noProveesLabel);
              }
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener los pedidos: " + e.getMessage());
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
          new MenuProvee(proveeController, productoController, proveedorController);
      });
    }
  
    private void BuscarProvee()
    {
      JFrame frame = new JFrame("Buscar Provee");
      frame.setLayout(null);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
  
      JLabel Title = new JLabel("\n=== BUSCAR PEDIDO ===");
      Title.setBounds(300, 10, 200, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
      if(proveeController!=null)
      {
          try
          {
            JLabel idLabel = new JLabel("Introduce el ID del pedido:");
            idLabel.setBounds(100, 100, 200, 30);
            idLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            frame.add(idLabel);
  
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 100, 200, 30);
            idTextField.setFont(new Font("Roboto", Font.PLAIN, 12));
            frame.add(idTextField);
            
            JButton buscarButton = new JButton("Buscar");
            buscarButton.setBounds(550, 100, 150, 30);
            buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
            buscarButton.setBackground(new Color(0, 123, 255));
            buscarButton.setForeground(Color.WHITE);
            frame.add(buscarButton);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 150, 600, 30);
            statusLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            frame.add(statusLabel);
            
            final JTextArea proveeTextArea = new JTextArea();
            proveeTextArea.setBounds(100, 190, 600, 180);
            proveeTextArea.setEditable(false);
            proveeTextArea.setBackground(new Color(240, 240, 240));
            proveeTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            proveeTextArea.setFont(new Font("Roboto", Font.PLAIN, 12));
            proveeTextArea.setVisible(false);
            frame.add(proveeTextArea);
            
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = idTextField.getText();
                    try
                    {
                        long id = Long.parseLong(input);
                        statusLabel.setText("Buscando pedido...");

                        Provee provee = proveeController.findById(id);
                        if(provee!=null)
                        {
                            statusLabel.setText("Pedido encontrado:");
                            proveeTextArea.setText(provee.toString());
                            proveeTextArea.setVisible(true);
                            frame.repaint();
                        }
                        else
                        {
                            statusLabel.setText("Pedido no encontrado");
                            proveeTextArea.setVisible(false);
                            frame.repaint();
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        proveeTextArea.setVisible(false);
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
                new MenuProvee(proveeController, productoController, proveedorController);
            });
          }
          catch(Exception e)
          {
              System.err.println("Error al buscar el pedido: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
  
    private void AñadirProvee()
    {
      JFrame frame = new JFrame("Añadir Provee");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== AÑADIR NUEVO PEDIDO ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(proveeController!=null && productoController!=null && proveedorController!=null)
      {
          try
          {
              JLabel proveedorLabel = new JLabel("ID del Proveedor:");
              proveedorLabel.setBounds(100, 70, 200, 30);
              frame.add(proveedorLabel);
              
              JTextField proveedorTextField = new JTextField(10);
              proveedorTextField.setBounds(350, 70, 200, 30);
              frame.add(proveedorTextField);
              
              JButton verProveedoresButton = new JButton("Ver Proveedores");
              verProveedoresButton.setBounds(560, 70, 150, 30);
              verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verProveedoresButton.setBackground(new Color(0, 123, 255));
              verProveedoresButton.setForeground(Color.WHITE);
              frame.add(verProveedoresButton);
              verProveedoresButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Proveedor> proveedores = proveedorController.findAll();
                          if (proveedores != null && !proveedores.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Proveedores Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(500, 400);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Proveedor proveedor : proveedores) {
                                  JLabel provLabel = new JLabel(proveedor.toString());
                                  provLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  panel.add(provLabel);
                              }
                              
                              JButton cerrarButton = new JButton("Cerrar");
                              cerrarButton.addActionListener(ae -> dialog.dispose());
                              dialog.add(cerrarButton, BorderLayout.SOUTH);
                              
                              dialog.setLocationRelativeTo(frame);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, "No hay proveedores disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                      } catch (Exception ex) {
                          JOptionPane.showMessageDialog(frame, "Error al cargar proveedores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
              JLabel productoLabel = new JLabel("ID del Producto:");
              productoLabel.setBounds(100, 120, 200, 30);
              frame.add(productoLabel);
              
              JTextField productoTextField = new JTextField(10);
              productoTextField.setBounds(350, 120, 200, 30);
              frame.add(productoTextField);
              
              JButton verProductosButton = new JButton("Ver Productos");
              verProductosButton.setBounds(560, 120, 150, 30);
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
                              dialog.setSize(500, 400);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Producto producto : productos) {
                                  JLabel prodLabel = new JLabel(producto.toString());
                                  prodLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  panel.add(prodLabel);
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
              cantidadLabel.setBounds(100, 170, 200, 30);
              frame.add(cantidadLabel);
              
              JTextField cantidadTextField = new JTextField(10);
              cantidadTextField.setBounds(350, 170, 200, 30);
              frame.add(cantidadTextField);
              
              JLabel precioLabel = new JLabel("Precio:");
              precioLabel.setBounds(100, 220, 200, 30);
              frame.add(precioLabel);
              
              JTextField precioTextField = new JTextField(10);
              precioTextField.setBounds(350, 220, 200, 30);
              frame.add(precioTextField);
              
              JLabel fechaLabel = new JLabel("Fecha de Provisión (YYYY-MM-DD):");
              fechaLabel.setBounds(100, 270, 250, 30);
              frame.add(fechaLabel);
              
              JTextField fechaTextField = new JTextField(10);
              fechaTextField.setBounds(350, 270, 200, 30);
              frame.add(fechaTextField);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 320, 600, 30);
              frame.add(statusLabel);
              
              JButton guardarButton = new JButton("Guardar Provisión");
              guardarButton.setBounds(200, 370, 200, 30);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              frame.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(420, 370, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
              
              guardarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          if (proveedorTextField.getText().isEmpty() || productoTextField.getText().isEmpty() 
                              || cantidadTextField.getText().isEmpty() || precioTextField.getText().isEmpty()
                              || fechaTextField.getText().isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          // Validación de datos numéricos
                          long proveedorId;
                          long productoId;
                          int cantidad;
                          double precio;
                          
                          try {
                              proveedorId = Long.parseLong(proveedorTextField.getText());
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El ID de proveedor debe ser un número");
                              return;
                          }
                          
                          try {
                              productoId = Long.parseLong(productoTextField.getText());
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El ID de producto debe ser un número");
                              return;
                          }
                          
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
                          
                          // Validación de la fecha
                          LocalDate fechaProvision;
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
                          
                          if (proveedor == null) {
                              statusLabel.setText("Error: El proveedor con ID " + proveedorId + " no existe");
                              return;
                          }
                          
                          if (producto == null) {
                              statusLabel.setText("Error: El producto con ID " + productoId + " no existe");
                              return;
                          }
                          
                          Provee provee = new Provee();
                          provee.setProveedor(proveedor);
                          provee.setProducto(producto);
                          provee.setCantidad(cantidad);
                          provee.setPrecio(precio);
                          provee.setFecha_Provision(fechaProvision);
                          
                          proveeController.save(provee);
                          
                          statusLabel.setText("Pedido guardado correctamente");
                          
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
              
              volverButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      frame.dispose();
                      new MenuProvee(proveeController, productoController, proveedorController);
                  }
              });
          }
          catch(Exception e)
          {
              System.err.println("Error: " + e.getMessage());
          }
      }
      else
      {
          JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
          errorLabel.setBounds(100, 100, 500, 30);
          frame.add(errorLabel);
          
          JButton volverButton = new JButton("Volver");
          volverButton.setBounds(300, 500, 200, 30);
          volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
          volverButton.setBackground(new Color(0, 123, 255));
          volverButton.setForeground(Color.WHITE);
          frame.add(volverButton);
          volverButton.addActionListener(e -> {
              frame.dispose();
              new MenuProvee(proveeController, productoController, proveedorController);
          });
      }
    }
  
    private void ActualizarProvee()
    {
      JFrame frame = new JFrame("Actualizar Provee");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ACTUALIZAR PEDIDO ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(proveeController!=null && productoController!=null && proveedorController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID del pedido a actualizar:");
              idLabel.setBounds(100, 70, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 70, 200, 30);
              frame.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Pedido");
              buscarButton.setBounds(520, 70, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
          
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 110, 600, 30);
              frame.add(statusLabel);
              
              JLabel proveedorLabel = new JLabel("ID del Proveedor:");
              proveedorLabel.setBounds(100, 150, 200, 30);
              frame.add(proveedorLabel);
              
              final JTextField proveedorTextField = new JTextField(10);
              proveedorTextField.setBounds(350, 150, 200, 30);
              proveedorTextField.setEnabled(false);
              frame.add(proveedorTextField);
              
              final JButton verProveedoresButton = new JButton("Ver Proveedores");
              verProveedoresButton.setBounds(560, 150, 150, 30);
              verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verProveedoresButton.setBackground(new Color(0, 123, 255));
              verProveedoresButton.setForeground(Color.WHITE);
              frame.add(verProveedoresButton);
              
              JLabel productoLabel = new JLabel("ID del Producto:");
              productoLabel.setBounds(100, 190, 200, 30);
              frame.add(productoLabel);
              
              final JTextField productoTextField = new JTextField(10);
              productoTextField.setBounds(350, 190, 200, 30);
              productoTextField.setEnabled(false);
              frame.add(productoTextField);
              
              final JButton verProductosButton = new JButton("Ver Productos");
              verProductosButton.setBounds(560, 190, 150, 30);
              verProductosButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verProductosButton.setBackground(new Color(0, 123, 255));
              verProductosButton.setForeground(Color.WHITE);
              frame.add(verProductosButton);
              
              JLabel cantidadLabel = new JLabel("Cantidad:");
              cantidadLabel.setBounds(100, 230, 200, 30);
              frame.add(cantidadLabel);
              
              final JTextField cantidadTextField = new JTextField(10);
              cantidadTextField.setBounds(350, 230, 200, 30);
              cantidadTextField.setEnabled(false);
              frame.add(cantidadTextField);
              
              JLabel precioLabel = new JLabel("Precio:");
              precioLabel.setBounds(100, 270, 200, 30);
              frame.add(precioLabel);
              
              final JTextField precioTextField = new JTextField(10);
              precioTextField.setBounds(350, 270, 200, 30);
              precioTextField.setEnabled(false);
              frame.add(precioTextField);
              
              JLabel fechaLabel = new JLabel("Fecha Provisión (YYYY-MM-DD):");
              fechaLabel.setBounds(100, 310, 250, 30);
              frame.add(fechaLabel);
              
              final JTextField fechaTextField = new JTextField(10);
              fechaTextField.setBounds(350, 310, 200, 30);
              fechaTextField.setEnabled(false);
              frame.add(fechaTextField);
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setBounds(100, 360, 200, 30);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              frame.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 360, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
              
              final Long[] proveeId = new Long[1];
              
              verProveedoresButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Proveedor> proveedores = proveedorController.findAll();
                          if (proveedores != null && !proveedores.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Proveedores Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(500, 400);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Proveedor proveedor : proveedores) {
                                  JLabel provLabel = new JLabel(proveedor.toString());
                                  provLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  panel.add(provLabel);
                              }
                              
                              JButton cerrarButton = new JButton("Cerrar");
                              cerrarButton.addActionListener(ae -> dialog.dispose());
                              dialog.add(cerrarButton, BorderLayout.SOUTH);
                              
                              dialog.setLocationRelativeTo(frame);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, "No hay proveedores disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                      } catch (Exception ex) {
                          JOptionPane.showMessageDialog(frame, "Error al cargar proveedores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
              verProductosButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Producto> productos = productoController.findAll();
                          if (productos != null && !productos.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Productos Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(500, 400);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Producto producto : productos) {
                                  JLabel prodLabel = new JLabel(producto.toString());
                                  prodLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  panel.add(prodLabel);
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
              
              buscarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          statusLabel.setText("Buscando pedido...");
                          
                          Provee provee = proveeController.findById(id);
                          if (provee != null) {
                              proveeId[0] = provee.getID_Provee();
                              
                              proveedorTextField.setText(String.valueOf(provee.getProveedor().getID_Proveedor()));
                              productoTextField.setText(String.valueOf(provee.getProducto().getID_Producto()));
                              cantidadTextField.setText(String.valueOf(provee.getCantidad()));
                              precioTextField.setText(String.valueOf(provee.getPrecio()));
                              fechaTextField.setText(provee.getFecha_Provision() != null ? 
                                  provee.getFecha_Provision().toString() : "");
                              
                              statusLabel.setText("Pedido encontrado. Modifique los campos o manten los mismos valores (o deje en blanco).");
                              
                              proveedorTextField.setEnabled(true);
                              productoTextField.setEnabled(true);
                              cantidadTextField.setEnabled(true);
                              precioTextField.setEnabled(true);
                              fechaTextField.setEnabled(true);
                              verProveedoresButton.setEnabled(true);
                              verProductosButton.setEnabled(true);
                              guardarButton.setEnabled(true);
                          } else {
                              statusLabel.setText("No se encontró un pedido con el ID: " + id);
                              
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
              
              guardarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          if (proveedorTextField.getText().isEmpty() || productoTextField.getText().isEmpty() 
                              || cantidadTextField.getText().isEmpty() || precioTextField.getText().isEmpty()
                              || fechaTextField.getText().isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          long proveedorId = Long.parseLong(proveedorTextField.getText());
                          long productoId = Long.parseLong(productoTextField.getText());
                          int cantidad = Integer.parseInt(cantidadTextField.getText());
                          double precio = Double.parseDouble(precioTextField.getText());
                          LocalDate fechaProvision = LocalDate.parse(fechaTextField.getText());
                          
                          Proveedor proveedor = proveedorController.findById(proveedorId);
                          Producto producto = productoController.findById(productoId);
                          
                          if (proveedor == null) {
                              statusLabel.setText("Error: El proveedor con ID " + proveedorId + " no existe");
                              return;
                          }
                          
                          if (producto == null) {
                              statusLabel.setText("Error: El producto con ID " + productoId + " no existe");
                              return;
                          }
                          
                          Provee provee = proveeController.findById(proveeId[0]);
                          if (provee == null) {
                              statusLabel.setText("Error: No se encontró la provisión a actualizar");
                              return;
                          }
                          
                          provee.setProveedor(proveedor);
                          provee.setProducto(producto);
                          provee.setCantidad(cantidad);
                          provee.setPrecio(precio);
                          provee.setFecha_Provision(fechaProvision);
                          
                          proveeController.save(provee);
                          
                          statusLabel.setText("Pedido actualizado correctamente");
                          
                      } catch (NumberFormatException nfe) {
                          statusLabel.setText("Error: Asegúrate de que los IDs, cantidad y precio son valores numéricos");
                      } catch (DateTimeParseException dtpe) {
                          statusLabel.setText("Error: El formato de fecha debe ser YYYY-MM-DD");
                      } catch (Exception ex) {
                          statusLabel.setText("Error al actualizar el pedido: " + ex.getMessage());
                      }
                  }
              });
              
              volverButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      frame.dispose();
                      new MenuProvee(proveeController, productoController, proveedorController);
                  }
              });
          }
          catch(Exception e)
          {
              System.err.println("Error: " + e.getMessage());
          }
      }
      else
      {
          JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
          errorLabel.setBounds(100, 100, 500, 30);
          frame.add(errorLabel);
          
          JButton volverButton = new JButton("Volver");
          volverButton.setBounds(300, 500, 200, 30);
          volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
          volverButton.setBackground(new Color(0, 123, 255));
          volverButton.setForeground(Color.WHITE);
          frame.add(volverButton);
          volverButton.addActionListener(e -> {
              frame.dispose();
              new MenuProvee(proveeController, productoController, proveedorController);
          });
      }
    }
  
    private void EliminarProvee()
    {
      JFrame frame = new JFrame("Eliminar Provee");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ELIMINAR PEDIDO ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(proveeController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID del pedido a eliminar:");
              idLabel.setBounds(100, 100, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 100, 200, 30);
              frame.add(idTextField);
              
              final JLabel infoLabel = new JLabel("Información del pedido:");
              infoLabel.setBounds(100, 150, 500, 30);
              frame.add(infoLabel);
              
              final JTextArea proveeInfo = new JTextArea();
              proveeInfo.setBounds(100, 190, 600, 100);
              proveeInfo.setEditable(false);
              proveeInfo.setBackground(new Color(240, 240, 240));
              proveeInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              frame.add(proveeInfo);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 350, 600, 30);
              frame.add(statusLabel);
              
              final JButton buscarButton = new JButton("Buscar Pedido");
              buscarButton.setBounds(550, 100, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
              
              final JButton eliminarButton = new JButton("Eliminar Pedido");
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
              
              final Long[] proveeId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) 
                  {
                      try 
                      {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Provee provee = proveeController.findById(id);
                          if (provee != null) 
                          {
                              proveeId[0] = provee.getID_Provee();
                              proveeInfo.setText(provee.toString());
                              eliminarButton.setEnabled(true);
                              statusLabel.setText("Pedido encontrado. Pulse 'Eliminar Pedido' para confirmar.");
                          } 
                          else 
                          {
                              proveeInfo.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("No se encontró un pedido con el ID: " + id);
                          }
                      } 
                      catch (NumberFormatException nfe) 
                      {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          proveeInfo.setText("");
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
                              "¿Está seguro de que desea eliminar esta provisión?", 
                              "Confirmar eliminación", 
                              JOptionPane.YES_NO_OPTION);
                          
                          if (confirmacion == JOptionPane.YES_OPTION) 
                          {
                              proveeController.delete(proveeId[0]);
                              
                              proveeInfo.setText("");
                              idTextField.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("Pedido eliminado correctamente.");
                          }
                      } 
                      catch (Exception ex) 
                      {
                          statusLabel.setText("Error al eliminar el pedido: " + ex.getMessage());
                      }
                  }
              });
              
              volverButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) 
                  {
                      frame.dispose();
                      new MenuProvee(proveeController, productoController, proveedorController);
                  }
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al eliminar el pedido: " + e.getMessage());
          }
      }
      else
      {
          JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
          errorLabel.setBounds(100, 100, 500, 30);
          frame.add(errorLabel);
          
          JButton volverButton = new JButton("Volver");
          volverButton.setBounds(300, 500, 200, 30);
          volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
          volverButton.setBackground(new Color(0, 123, 255));
          volverButton.setForeground(Color.WHITE);
          frame.add(volverButton);
          volverButton.addActionListener(e -> {
              frame.dispose();
              new MenuProvee(proveeController, productoController, proveedorController);
          });
      }
    }
} 