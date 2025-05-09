package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuProducto extends JFrame {
    private ProductoController productoController;
    private CategoriaController categoriaController;
    private ProveedorController proveedorController;

    public MenuProducto(ProductoController productoController,
                       CategoriaController categoriaController,
                       ProveedorController proveedorController) {
        this.productoController = productoController;
        this.categoriaController = categoriaController;
        this.proveedorController = proveedorController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Producto");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE PRODUCTOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton ListarProductosBtn = new JButton("Ver todos los productos");
        ListarProductosBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ListarProductosBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ListarProductosBtn.setBackground(new Color(0, 123, 255));
        ListarProductosBtn.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(ListarProductosBtn, gbc);
        ListarProductosBtn.addActionListener(e -> {
            dispose();
            ListarProductos();
        });

        JButton BuscarProductoBtn = new JButton("Buscar producto por ID");
        BuscarProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarProductoBtn.setBackground(new Color(0, 123, 255));
        BuscarProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarProductoBtn, gbc);
        BuscarProductoBtn.addActionListener(e -> {
            dispose();
            BuscarProducto();
        });

        JButton AñadirProductoBtn = new JButton("Añadir nuevo producto");
        AñadirProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirProductoBtn.setBackground(new Color(76, 175, 80));
        AñadirProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirProductoBtn, gbc);
        AñadirProductoBtn.addActionListener(e -> {
            dispose();
            AñadirProducto();
        });

        JButton ActualizarProductoBtn = new JButton("Actualizar producto");
        ActualizarProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ActualizarProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ActualizarProductoBtn.setBackground(new Color(0, 123, 255));
        ActualizarProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ActualizarProductoBtn, gbc);
        ActualizarProductoBtn.addActionListener(e -> {
            dispose();
            ActualizarProducto();
        });

        JButton EliminarProductoBtn = new JButton("Eliminar producto");
        EliminarProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarProductoBtn.setBackground(new Color(0, 123, 255));
        EliminarProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarProductoBtn, gbc);
        EliminarProductoBtn.addActionListener(e -> {
            dispose();
            EliminarProducto();
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

    private void ListarProductos()
    {
      JFrame frame = new JFrame("Listar Productos");
      frame.setLayout(null);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
  
      JLabel Title = new JLabel("\n=== LISTA DE PRODUCTOS ===");
      Title.setBounds(300, 10, 200, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(productoController!=null)
      {
          try
          {
              List<Producto> productos = productoController.findAll();
              if(productos!=null)
              {
                  JPanel panelProductos = new JPanel();
                  panelProductos.setLayout(null);
                  
                  JScrollPane scrollPane = new JScrollPane(panelProductos);
                  scrollPane.setBounds(50, 70, 700, 400);
                  frame.add(scrollPane);
                  
                  panelProductos.setPreferredSize(new Dimension(680, Math.max(380, productos.size() * 150)));
                  
                  for(int i = 0; i < productos.size(); i++)
                  {
                      JTextArea productoTextArea = new JTextArea(productos.get(i).toString());
                      productoTextArea.setBounds(50, 10 + i * 150, 600, 130);
                      productoTextArea.setEditable(false);
                      productoTextArea.setBackground(new Color(240, 240, 240));
                      productoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                      productoTextArea.setFont(new Font("Roboto", Font.PLAIN, 12));
                      panelProductos.add(productoTextArea);
                  }
              }
              else
              {
                  JLabel noProductosLabel = new JLabel("No hay productos registrados en el sistema");
                  noProductosLabel.setBounds(300, 200, 300, 30);
                  noProductosLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                  frame.add(noProductosLabel);
              }
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener los productos: " + e.getMessage());
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
          new MenuProducto(productoController, categoriaController, proveedorController);
      });
    }
  
    private void BuscarProducto()
    {
      JFrame frame = new JFrame("Buscar Producto");
      frame.setLayout(null);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
  
      JLabel Title = new JLabel("\n=== BUSCAR PRODUCTO ===");
      Title.setBounds(300, 10, 200, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
      if(productoController!=null)
      {
          try
          {
            JLabel idLabel = new JLabel("Introduce el ID del producto:");
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
            
            final JTextArea productoTextArea = new JTextArea();
            productoTextArea.setBounds(100, 190, 600, 180);
            productoTextArea.setEditable(false);
            productoTextArea.setBackground(new Color(240, 240, 240));
            productoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            productoTextArea.setFont(new Font("Roboto", Font.PLAIN, 12));
            productoTextArea.setVisible(false);
            frame.add(productoTextArea);
            
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = idTextField.getText();
                    try
                    {
                        long id = Long.parseLong(input);
                        statusLabel.setText("Buscando producto...");
                        
                        Producto producto = productoController.findById(id);
                        if(producto!=null)
                        {
                            statusLabel.setText("Producto encontrado:");
                            productoTextArea.setText(producto.toString());
                            productoTextArea.setVisible(true);
                            frame.repaint();
                        }
                        else
                        {
                            statusLabel.setText("Producto no encontrado");
                            productoTextArea.setVisible(false);
                            frame.repaint();
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        productoTextArea.setVisible(false);
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
                new MenuProducto(productoController, categoriaController, proveedorController);
            });
          }
          catch(Exception e)
          {
              System.err.println("Error al buscar el producto: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
  
    private void AñadirProducto()
    {
      JFrame frame = new JFrame("Añadir Producto");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== AÑADIR NUEVO PRODUCTO ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(productoController!=null && categoriaController!=null)
      {
          try
          {
              JLabel nombreLabel = new JLabel("Nombre del producto:");
              nombreLabel.setBounds(100, 70, 200, 30);
              frame.add(nombreLabel);
              
              JTextField nombreTextField = new JTextField(10);
              nombreTextField.setBounds(350, 70, 200, 30);
              frame.add(nombreTextField);
              
              JLabel descripcionLabel = new JLabel("Descripción:");
              descripcionLabel.setBounds(100, 110, 200, 30);
              frame.add(descripcionLabel);
              
              JTextField descripcionTextField = new JTextField(10);
              descripcionTextField.setBounds(350, 110, 200, 30);
              frame.add(descripcionTextField);
              
              JLabel precioLabel = new JLabel("Precio (PVP):");
              precioLabel.setBounds(100, 150, 200, 30);
              frame.add(precioLabel);
              
              JTextField precioTextField = new JTextField(10);
              precioTextField.setBounds(350, 150, 200, 30);
              frame.add(precioTextField);
              
              JLabel ivaLabel = new JLabel("IVA (%):");
              ivaLabel.setBounds(100, 190, 200, 30);
              frame.add(ivaLabel);
              
              JTextField ivaTextField = new JTextField(10);
              ivaTextField.setBounds(350, 190, 200, 30);
              frame.add(ivaTextField);
              
              JLabel stockLabel = new JLabel("Stock:");
              stockLabel.setBounds(100, 230, 200, 30);
              frame.add(stockLabel);
              
              JTextField stockTextField = new JTextField(10);
              stockTextField.setBounds(350, 230, 200, 30);
              frame.add(stockTextField);
              
              JLabel categoriaLabel = new JLabel("ID de Categoría:");
              categoriaLabel.setBounds(100, 270, 200, 30);
              frame.add(categoriaLabel);
              
              JTextField categoriaTextField = new JTextField(10);
              categoriaTextField.setBounds(350, 270, 200, 30);
              frame.add(categoriaTextField);
              
              JButton verCategoriasButton = new JButton("Ver Categorías");
              verCategoriasButton.setBounds(560, 270, 130, 30);
              verCategoriasButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verCategoriasButton.setBackground(new Color(0, 123, 255));
              verCategoriasButton.setForeground(Color.WHITE);
              frame.add(verCategoriasButton);
              verCategoriasButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Categoria> categorias = categoriaController.findAll();
                          if (categorias != null && !categorias.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Categorías Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(400, 300);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Categoria cat : categorias) {
                                  JLabel catLabel = new JLabel(cat.toString());
                                  catLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  panel.add(catLabel);
                              }
                              
                              JButton cerrarButton = new JButton("Cerrar");
                              cerrarButton.addActionListener(ae -> dialog.dispose());
                              dialog.add(cerrarButton, BorderLayout.SOUTH);
                              
                              dialog.setLocationRelativeTo(frame);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, "No hay categorías disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                      } catch (Exception ex) {
                          JOptionPane.showMessageDialog(frame, "Error al cargar categorías: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
              JLabel proveedorLabel = new JLabel("ID de Proveedor:");
              proveedorLabel.setBounds(100, 310, 200, 30);
              frame.add(proveedorLabel);
              
              JTextField proveedorTextField = new JTextField(10);
              proveedorTextField.setBounds(350, 310, 200, 30);
              frame.add(proveedorTextField);
              
              JButton verProveedoresButton = new JButton("Ver Proveedores");
              verProveedoresButton.setBounds(560, 310, 130, 30);
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
                              dialog.setSize(400, 300);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Proveedor prov : proveedores) {
                                  JLabel provLabel = new JLabel(prov.toString());
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
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 350, 500, 30);
              frame.add(statusLabel);
              
              JButton guardarButton = new JButton("Guardar Producto");
              guardarButton.setBounds(100, 400, 200, 30);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              frame.add(guardarButton);
              guardarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          
                          String nombre = nombreTextField.getText();
                          String descripcion = descripcionTextField.getText();
                          String precioStr = precioTextField.getText();
                          String ivaStr = ivaTextField.getText();
                          String stockStr = stockTextField.getText();
                          String categoriaStr = categoriaTextField.getText();
                          String proveedorStr = proveedorTextField.getText();
                          
                      
                          if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || 
                              ivaStr.isEmpty() || stockStr.isEmpty() || categoriaStr.isEmpty() || proveedorStr.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          double precio = 0;
                          try {
                              precio = Double.parseDouble(precioStr);
                              if (precio <= 0) {
                                  statusLabel.setText("Error: El precio debe ser mayor que cero");
                                  return;
                              }
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El precio debe ser un número válido");
                              return;
                          }
                          
                      
                          double iva = 0;
                          try {
                              iva = Double.parseDouble(ivaStr);
                              if (iva < 0 || iva > 100) {
                                  statusLabel.setText("Error: El IVA debe estar entre 0 y 100");
                                  return;
                              }
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El IVA debe ser un número válido");
                              return;
                          }
                          
                      
                          int stock = 0;
                          try {
                              stock = Integer.parseInt(stockStr);
                              if (stock < 0) {
                                  statusLabel.setText("Error: El stock no puede ser negativo");
                                  return;
                              }
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El stock debe ser un número entero válido");
                              return;
                          }
                          
                          Categoria categoria = null;
                          if (!categoriaStr.isEmpty()) {
                              try {
                                  long idCategoria = Long.parseLong(categoriaStr);
                                  categoria = categoriaController.findById(idCategoria);
                                  if (categoria == null) {
                                      statusLabel.setText("Error: La categoría con ID " + idCategoria + " no existe");
                                      return;
                                  }
                              } catch (NumberFormatException nfe) {
                                  statusLabel.setText("Error: El ID de categoría debe ser un número válido");
                                  return;
                              }
                          }
                          
                  
                          Proveedor proveedor = null;
                          if (!proveedorStr.isEmpty()) {
                              try {
                                  long idProveedor = Long.parseLong(proveedorStr);
                                  proveedor = proveedorController.findById(idProveedor);
                                  if (proveedor == null) {
                                      statusLabel.setText("Error: El proveedor con ID " + idProveedor + " no existe");
                                      return;
                                  }
                              } catch (NumberFormatException nfe) {
                                  statusLabel.setText("Error: El ID de proveedor debe ser un número válido");
                                  return;
                              }
                          }
                          
                      
                          Producto nuevoProducto = new Producto();
                          nuevoProducto.setNombre(nombre);
                          nuevoProducto.setDescripcion(descripcion);
                          nuevoProducto.setPVP(precio);
                          nuevoProducto.setIVA(iva);
                          nuevoProducto.setStock(stock);
                          nuevoProducto.setCategoria(categoria);
                          nuevoProducto.setProveedor(proveedor);
                          
                          productoController.save(nuevoProducto);
                          
                          statusLabel.setText("Producto añadido correctamente.");
                          
                          nombreTextField.setText("");
                          descripcionTextField.setText("");
                          precioTextField.setText("");
                          ivaTextField.setText("");
                          stockTextField.setText("");
                          categoriaTextField.setText("");
                          proveedorTextField.setText("");
                      } catch (Exception ex) {
                          statusLabel.setText("Error al guardar el producto: " + ex.getMessage());
                      }
                  }
              });
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 400, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProducto(productoController, categoriaController, proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al añadir producto: " + e.getMessage());
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
              new MenuProducto(productoController, categoriaController, proveedorController);
          });
      }
    }
  
    private void ActualizarProducto()
    {
      JFrame frame = new JFrame("Actualizar Producto");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ACTUALIZAR PRODUCTO ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(productoController!=null && categoriaController!=null && proveedorController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID del producto a actualizar:");
              idLabel.setBounds(100, 70, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 70, 200, 30);
              frame.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Producto");
              buscarButton.setBounds(520, 70, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
          
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 110, 600, 30);
              frame.add(statusLabel);
              
              JLabel nombreLabel = new JLabel("Nombre:");
              nombreLabel.setBounds(100, 150, 200, 30);
              frame.add(nombreLabel);
              
              final JTextField nombreTextField = new JTextField(10);
              nombreTextField.setBounds(350, 150, 200, 30);
              nombreTextField.setEnabled(false);
              frame.add(nombreTextField);
              
              JLabel descripcionLabel = new JLabel("Descripción:");
              descripcionLabel.setBounds(100, 190, 200, 30);
              frame.add(descripcionLabel);
              
              final JTextField descripcionTextField = new JTextField(10);
              descripcionTextField.setBounds(350, 190, 200, 30);
              descripcionTextField.setEnabled(false);
              frame.add(descripcionTextField);
              
              JLabel precioLabel = new JLabel("Precio (PVP):");
              precioLabel.setBounds(100, 230, 200, 30);
              frame.add(precioLabel);
              
              final JTextField precioTextField = new JTextField(10);
              precioTextField.setBounds(350, 230, 200, 30);
              precioTextField.setEnabled(false);
              frame.add(precioTextField);
              
              JLabel ivaLabel = new JLabel("IVA (%):");
              ivaLabel.setBounds(100, 270, 200, 30);
              frame.add(ivaLabel);
              
              final JTextField ivaTextField = new JTextField(10);
              ivaTextField.setBounds(350, 270, 200, 30);
              ivaTextField.setEnabled(false);
              frame.add(ivaTextField);
              
              JLabel stockLabel = new JLabel("Stock:");
              stockLabel.setBounds(100, 310, 200, 30);
              frame.add(stockLabel);
              
              final JTextField stockTextField = new JTextField(10);
              stockTextField.setBounds(350, 310, 200, 30);
              stockTextField.setEnabled(false);
              frame.add(stockTextField);
              
              JLabel categoriaLabel = new JLabel("ID de Categoría:");
              categoriaLabel.setBounds(100, 350, 200, 30);
              frame.add(categoriaLabel);
              
              final JTextField categoriaTextField = new JTextField(10);
              categoriaTextField.setBounds(350, 350, 200, 30);
              categoriaTextField.setEnabled(false);
              frame.add(categoriaTextField);
              
              final JButton verCategoriasButton = new JButton("Ver Categorías");
              verCategoriasButton.setBounds(560, 350, 130, 30);
              verCategoriasButton.setEnabled(false);
              verCategoriasButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verCategoriasButton.setBackground(new Color(0, 123, 255));
              verCategoriasButton.setForeground(Color.WHITE);
              frame.add(verCategoriasButton);
              
              JLabel proveedorLabel = new JLabel("ID de Proveedor:");
              proveedorLabel.setBounds(100, 390, 200, 30);
              frame.add(proveedorLabel);
              
              final JTextField proveedorTextField = new JTextField(10);
              proveedorTextField.setBounds(350, 390, 200, 30);
              proveedorTextField.setEnabled(false);
              frame.add(proveedorTextField);
              
              final JButton verProveedoresButton = new JButton("Ver Proveedores");
              verProveedoresButton.setBounds(560, 390, 130, 30);
              verProveedoresButton.setEnabled(false);
              verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, 14));
              verProveedoresButton.setBackground(new Color(0, 123, 255));
              verProveedoresButton.setForeground(Color.WHITE);
              frame.add(verProveedoresButton);
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setBounds(100, 450, 200, 30);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              frame.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 450, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
              
              final Long[] productoId = new Long[1];
              
              verCategoriasButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Categoria> categorias = categoriaController.findAll();
                          if (categorias != null && !categorias.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Categorías Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(400, 300);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Categoria cat : categorias) {
                                  JLabel catLabel = new JLabel(cat.toString());
                                  catLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                  panel.add(catLabel);
                              }
                              
                              JButton cerrarButton = new JButton("Cerrar");
                              cerrarButton.addActionListener(ae -> dialog.dispose());
                              dialog.add(cerrarButton, BorderLayout.SOUTH);
                              
                              dialog.setLocationRelativeTo(frame);
                              dialog.setVisible(true);
                          } else {
                              JOptionPane.showMessageDialog(frame, "No hay categorías disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                      } catch (Exception ex) {
                          JOptionPane.showMessageDialog(frame, "Error al cargar categorías: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              
              verProveedoresButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Proveedor> proveedores = proveedorController.findAll();
                          if (proveedores != null && !proveedores.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Proveedores Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(400, 300);
                              
                              JPanel panel = new JPanel();
                              panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                              
                              JScrollPane scrollPane = new JScrollPane(panel);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
                              for (Proveedor prov : proveedores) {
                                  JLabel provLabel = new JLabel(prov.toString());
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
              
              buscarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          statusLabel.setText("Buscando producto...");
                          
                          Producto producto = productoController.findById(id);
                          if (producto != null) {
                              productoId[0] = producto.getID_Producto();
                              
                              nombreTextField.setText(producto.getNombre());
                              descripcionTextField.setText(producto.getDescripcion());
                              precioTextField.setText(String.valueOf(producto.getPVP()));
                              ivaTextField.setText(String.valueOf(producto.getIVA()));
                              stockTextField.setText(String.valueOf(producto.getStock()));
                              
                              if (producto.getCategoria() != null) {
                                  categoriaTextField.setText(String.valueOf(producto.getCategoria().getID_Categoria()));
                              }
                              
                              if (producto.getProveedor() != null) {
                                  proveedorTextField.setText(String.valueOf(producto.getProveedor().getID_Proveedor()));
                              }
                              
                              nombreTextField.setEnabled(true);
                              descripcionTextField.setEnabled(true);
                              precioTextField.setEnabled(true);
                              ivaTextField.setEnabled(true);
                              stockTextField.setEnabled(true);
                              categoriaTextField.setEnabled(true);
                              proveedorTextField.setEnabled(true);
                              verCategoriasButton.setEnabled(true);
                              verProveedoresButton.setEnabled(true);
                              guardarButton.setEnabled(true);
                              
                              statusLabel.setText("Producto encontrado. Modifique los campos o manten los mismos valores (o deje en blanco).");
                          } else {
                              statusLabel.setText("No se encontró un producto con el ID: " + id);
                              
                              nombreTextField.setText("");
                              descripcionTextField.setText("");
                              precioTextField.setText("");
                              ivaTextField.setText("");
                              stockTextField.setText("");
                              categoriaTextField.setText("");
                              proveedorTextField.setText("");
                              
                              nombreTextField.setEnabled(false);
                              descripcionTextField.setEnabled(false);
                              precioTextField.setEnabled(false);
                              ivaTextField.setEnabled(false);
                              stockTextField.setEnabled(false);
                              categoriaTextField.setEnabled(false);
                              proveedorTextField.setEnabled(false);
                              verCategoriasButton.setEnabled(false);
                              verProveedoresButton.setEnabled(false);
                              guardarButton.setEnabled(false);
                          }
                      } catch (NumberFormatException nfe) {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                      }
                  }
              });
              
              guardarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          Producto productoOriginal = productoController.findById(productoId[0]);
                          if (productoOriginal == null) {
                              statusLabel.setText("Error: No se pudo recuperar el producto original.");
                              return;
                          }
                          
                          // Obtener todos los valores de los campos
                          String nombre = nombreTextField.getText();
                          String descripcion = descripcionTextField.getText();
                          String precioStr = precioTextField.getText();
                          String ivaStr = ivaTextField.getText();
                          String stockStr = stockTextField.getText();
                          String categoriaStr = categoriaTextField.getText();
                          String proveedorStr = proveedorTextField.getText();
                          
                          // Verificar que ninguno de los campos obligatorios esté vacío
                          if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || 
                              ivaStr.isEmpty() || stockStr.isEmpty() || categoriaStr.isEmpty() || proveedorStr.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          // Validar formato y rango de valores para precio
                          double precio = 0;
                          try {
                              precio = Double.parseDouble(precioStr);
                              if (precio <= 0) {
                                  statusLabel.setText("Error: El precio debe ser mayor que cero");
                                  return;
                              }
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El precio debe ser un número válido");
                              return;
                          }
                          
                          // Validar formato y rango de valores para IVA
                          double iva = 0;
                          try {
                              iva = Double.parseDouble(ivaStr);
                              if (iva < 0 || iva > 100) {
                                  statusLabel.setText("Error: El IVA debe estar entre 0 y 100");
                                  return;
                              }
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El IVA debe ser un número válido");
                              return;
                          }
                          
                          // Validar formato y rango de valores para stock
                          int stock = 0;
                          try {
                              stock = Integer.parseInt(stockStr);
                              if (stock < 0) {
                                  statusLabel.setText("Error: El stock no puede ser negativo");
                                  return;
                              }
                          } catch (NumberFormatException nfe) {
                              statusLabel.setText("Error: El stock debe ser un número entero válido");
                              return;
                          }
                          
                          // Validar la existencia de la categoría si se ha proporcionado
                          Categoria categoria = null;
                          if (!categoriaStr.isEmpty()) {
                              try {
                                  long idCategoria = Long.parseLong(categoriaStr);
                                  categoria = categoriaController.findById(idCategoria);
                                  if (categoria == null) {
                                      statusLabel.setText("Error: La categoría con ID " + idCategoria + " no existe");
                                      return;
                                  }
                              } catch (NumberFormatException nfe) {
                                  statusLabel.setText("Error: El ID de categoría debe ser un número válido");
                                  return;
                              }
                          }
                          
                          // Validar la existencia del proveedor si se ha proporcionado
                          Proveedor proveedor = null;
                          if (!proveedorStr.isEmpty()) {
                              try {
                                  long idProveedor = Long.parseLong(proveedorStr);
                                  proveedor = proveedorController.findById(idProveedor);
                                  if (proveedor == null) {
                                      statusLabel.setText("Error: El proveedor con ID " + idProveedor + " no existe");
                                      return;
                                  }
                              } catch (NumberFormatException nfe) {
                                  statusLabel.setText("Error: El ID de proveedor debe ser un número válido");
                                  return;
                              }
                          }
                          
                          // Actualizar y guardar el producto si todas las validaciones son correctas
                          Producto productoActualizado = new Producto();
                          productoActualizado.setID_Producto(productoId[0]);
                          productoActualizado.setNombre(nombre);
                          productoActualizado.setDescripcion(descripcion);
                          productoActualizado.setPVP(precio);
                          productoActualizado.setIVA(iva);
                          productoActualizado.setStock(stock);
                          productoActualizado.setCategoria(categoria);
                          productoActualizado.setProveedor(proveedor);
                          
                          productoController.save(productoActualizado);
                          
                          statusLabel.setText("Producto actualizado correctamente");
                      } catch (Exception ex) {
                          statusLabel.setText("Error al actualizar el producto: " + ex.getMessage());
                      }
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProducto(productoController, categoriaController, proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al actualizar producto: " + e.getMessage());
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
              new MenuProducto(productoController, categoriaController, proveedorController);
          });
      }
    }
  
    private void EliminarProducto()
    {
      JFrame frame = new JFrame("Eliminar Producto");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ELIMINAR PRODUCTO ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
  
      if(productoController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID del producto a eliminar:");
              idLabel.setBounds(100, 100, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 100, 200, 30);
              frame.add(idTextField);
              
              final JLabel infoLabel = new JLabel("Información del producto:");
              infoLabel.setBounds(100, 150, 500, 30);
              frame.add(infoLabel);
              
              final JTextArea productoInfo = new JTextArea();
              productoInfo.setBounds(100, 190, 600, 150);
              productoInfo.setEditable(false);
              productoInfo.setBackground(new Color(240, 240, 240));
              productoInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              frame.add(productoInfo);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 350, 600, 30);
              frame.add(statusLabel);
              
              final JButton buscarButton = new JButton("Buscar Producto");
              buscarButton.setBounds(550, 100, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
              
              final JButton eliminarButton = new JButton("Eliminar Producto");
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
              
              final Long[] productoId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) 
                  {
                      try 
                      {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Producto producto = productoController.findById(id);
                          if (producto != null) 
                          {
                              productoId[0] = producto.getID_Producto();
                              productoInfo.setText(producto.toString());
                              eliminarButton.setEnabled(true);
                              statusLabel.setText("Producto encontrado. Pulse 'Eliminar Producto' para confirmar.");
                          } 
                          else 
                          {
                              productoInfo.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("No se encontró un producto con el ID: " + id);
                          }
                      } 
                      catch (NumberFormatException nfe) 
                      {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          productoInfo.setText("");
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
                              "¿Está seguro de que desea eliminar este producto?", 
                              "Confirmar eliminación", 
                              JOptionPane.YES_NO_OPTION);
                          
                          if (confirmacion == JOptionPane.YES_OPTION) 
                          {
                              productoController.delete(productoId[0]);
                              
                              productoInfo.setText("");
                              idTextField.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("Producto eliminado correctamente.");
                          }
                      } 
                      catch (Exception ex) 
                      {
                          statusLabel.setText("Error al eliminar el producto: " + ex.getMessage());
                      }
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProducto(productoController, categoriaController, proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al eliminar el producto: " + e.getMessage());
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
              new MenuProducto(productoController, categoriaController, proveedorController);
          });
      }
    }
} 