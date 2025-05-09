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
      gbc.gridy = 0;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = new Insets(10, 0, 10, 0);

      JLabel Title = new JLabel("=== LISTA DE PEDIDOS ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
  
      if(proveeController!=null)
      {
          try
          {
              List<Provee> provees = proveeController.findAll();
              if(provees!=null && !provees.isEmpty())
              {
                  JTextArea proveesTextArea = new JTextArea();
                  proveesTextArea.setEditable(false);
                  proveesTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));
                  
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
              }
              else
              {
                  JLabel noProveesLabel = new JLabel("No hay pedidos registrados en el sistema");
                  noProveesLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                  noProveesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                  gbc.gridy = 1;
                  frame.add(noProveesLabel, gbc);
              }
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener los pedidos: " + e.getMessage());
              JLabel errorLabel = new JLabel("Error al obtener pedidos: " + e.getMessage());
              errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridy = 1;
              frame.add(errorLabel, gbc);
          } 
      }
      else
      {
          JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
          errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
          gbc.gridy = 1;
          frame.add(errorLabel, gbc);
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
          new MenuProvee(proveeController, productoController, proveedorController);
      });
    }
  
    private void BuscarProvee()
    {
      JFrame frame = new JFrame("Buscar Pedido");
      frame.setLayout(new GridBagLayout());
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int buttonWidth = (int) (screenSize.width * 0.18);
      int buttonHeight = (int) (screenSize.height * 0.06);
      int textFieldWidth = (int) (screenSize.width * 0.2);
      int textAreaWidth = (int) (screenSize.width * 0.6);
      int textAreaHeight = (int) (screenSize.height * 0.4);
      int fontSize = (int) (screenSize.height * 0.022);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 10, 10, 10);
  
      JLabel Title = new JLabel("=== BUSCAR PEDIDO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
      
      if(proveeController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("Introduce el ID del pedido:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              gbc.fill = GridBagConstraints.HORIZONTAL;
              frame.add(idLabel, gbc);
  
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              idTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight));
              gbc.gridx = 1;
              gbc.gridy = 1;
              frame.add(idTextField, gbc);
              
              JButton buscarButton = new JButton("Buscar");
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              buscarButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight));
              gbc.gridx = 2;
              gbc.gridy = 1;
              frame.add(buscarButton, gbc);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 3;
              frame.add(statusLabel, gbc);
              
              final JTextArea proveeTextArea = new JTextArea();
              proveeTextArea.setEditable(false);
              proveeTextArea.setBackground(new Color(240, 240, 240));
              proveeTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              proveeTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 4));
              proveeTextArea.setVisible(false);
              
              JScrollPane scrollPane = new JScrollPane(proveeTextArea);
              scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.gridwidth = 3;
              gbc.fill = GridBagConstraints.BOTH;
              frame.add(scrollPane, gbc);
              
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
                              
                              proveeTextArea.setText(sb.toString());
                              proveeTextArea.setVisible(true);
                              scrollPane.setVisible(true);
                          }
                          else
                          {
                              statusLabel.setText("Pedido no encontrado");
                              proveeTextArea.setVisible(false);
                              scrollPane.setVisible(false);
                          }
                      }
                      catch(NumberFormatException nfe)
                      {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          proveeTextArea.setVisible(false);
                          scrollPane.setVisible(false);
                      }
                  }
              });
              
              JButton volverButton = new JButton("Volver");
              volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              gbc.gridx = 0;
              gbc.gridy = 4;
              gbc.gridwidth = 3;
              gbc.fill = GridBagConstraints.NONE;
              gbc.anchor = GridBagConstraints.CENTER;
              frame.add(volverButton, gbc);
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProvee(proveeController, productoController, proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al buscar el pedido: " + e.getMessage());
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
                  new MenuProvee(proveeController, productoController, proveedorController);
              });
          }
      }
      else
      {
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
              new MenuProvee(proveeController, productoController, proveedorController);
          });
      }
    }
  
    private void AñadirProvee()
    {
      JFrame frame = new JFrame("Añadir Pedido");
      frame.setLayout(new GridBagLayout());
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int buttonWidth = (int) (screenSize.width * 0.18);
      int buttonHeight = (int) (screenSize.height * 0.06);
      int textFieldWidth = (int) (screenSize.width * 0.25);
      int fontSize = (int) (screenSize.height * 0.022);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 10, 5, 10);
  
      JLabel Title = new JLabel("=== AÑADIR NUEVO PEDIDO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
  
      if(proveeController!=null && productoController!=null && proveedorController!=null)
      {
          try
          {
              // ID Proveedor
              JLabel proveedorLabel = new JLabel("ID del Proveedor:");
              proveedorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(proveedorLabel, gbc);
              
              JTextField proveedorTextField = new JTextField(10);
              proveedorTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              proveedorTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 1;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(proveedorTextField, gbc);
              
              JButton verProveedoresButton = new JButton("Ver Proveedores");
              verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              verProveedoresButton.setBackground(new Color(0, 123, 255));
              verProveedoresButton.setForeground(Color.WHITE);
              verProveedoresButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              gbc.gridx = 2;
              gbc.gridy = 1;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(verProveedoresButton, gbc);
              
              // ID Producto
              JLabel productoLabel = new JLabel("ID del Producto:");
              productoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(productoLabel, gbc);
              
              JTextField productoTextField = new JTextField(10);
              productoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              productoTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 2;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(productoTextField, gbc);
              
              JButton verProductosButton = new JButton("Ver Productos");
              verProductosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              verProductosButton.setBackground(new Color(0, 123, 255));
              verProductosButton.setForeground(Color.WHITE);
              verProductosButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              gbc.gridx = 2;
              gbc.gridy = 2;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(verProductosButton, gbc);
              
              // Cantidad
              JLabel cantidadLabel = new JLabel("Cantidad:");
              cantidadLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(cantidadLabel, gbc);
              
              JTextField cantidadTextField = new JTextField(10);
              cantidadTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              cantidadTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 3;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(cantidadTextField, gbc);
              
              // Precio
              JLabel precioLabel = new JLabel("Precio:");
              precioLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(precioLabel, gbc);
              
              JTextField precioTextField = new JTextField(10);
              precioTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              precioTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(precioTextField, gbc);
              
              // Fecha Provisión
              JLabel fechaLabel = new JLabel("Fecha de Provisión (YYYY-MM-DD):");
              fechaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(fechaLabel, gbc);
              
              JTextField fechaTextField = new JTextField(10);
              fechaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              fechaTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(fechaTextField, gbc);
              
              // Status
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 6;
              gbc.gridwidth = 3;
              gbc.anchor = GridBagConstraints.CENTER;
              frame.add(statusLabel, gbc);
              
              // Botones
              JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              JButton guardarButton = new JButton("Guardar Pedido");
              guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buttonPanel.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buttonPanel.add(volverButton);
              
              gbc.gridx = 0;
              gbc.gridy = 7;
              gbc.gridwidth = 3;
              gbc.anchor = GridBagConstraints.CENTER;
              frame.add(buttonPanel, gbc);
              
              verProveedoresButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          List<Proveedor> proveedores = proveedorController.findAll();
                          if (proveedores != null && !proveedores.isEmpty()) {
                              JDialog dialog = new JDialog(frame, "Proveedores Disponibles", true);
                              dialog.setLayout(new BorderLayout());
                              dialog.setSize(500, 400);
                              
                              JTextArea proveedoresTextArea = new JTextArea();
                              proveedoresTextArea.setEditable(false);
                              proveedoresTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                              
                              StringBuilder sb = new StringBuilder();
                              for (Proveedor proveedor : proveedores) {
                                  sb.append("ID: ").append(proveedor.getID_Proveedor()).append("\n");
                                  sb.append("Empresa: ").append(proveedor.getNombre()).append("\n");
                                  sb.append("------------------------------------------\n");
                              }
                              proveedoresTextArea.setText(sb.toString());
                              
                              JScrollPane scrollPane = new JScrollPane(proveedoresTextArea);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
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
                              
                              JTextArea productosTextArea = new JTextArea();
                              productosTextArea.setEditable(false);
                              productosTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                              
                              StringBuilder sb = new StringBuilder();
                              for (Producto producto : productos) {
                                  sb.append("ID: ").append(producto.getID_Producto()).append("\n");
                                  sb.append("Nombre: ").append(producto.getNombre()).append("\n");
                                  sb.append("------------------------------------------\n");
                              }
                              productosTextArea.setText(sb.toString());
                              
                              JScrollPane scrollPane = new JScrollPane(productosTextArea);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
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
                  new MenuProvee(proveeController, productoController, proveedorController);
              });
          }
      }
      else
      {
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
              new MenuProvee(proveeController, productoController, proveedorController);
          });
      }
    }
  
    private void ActualizarProvee()
    {
      JFrame frame = new JFrame("Actualizar Pedido");
      frame.setLayout(new GridBagLayout());
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int buttonWidth = (int) (screenSize.width * 0.18);
      int buttonHeight = (int) (screenSize.height * 0.06);
      int textFieldWidth = (int) (screenSize.width * 0.25);
      int fontSize = (int) (screenSize.height * 0.022);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 10, 5, 10);
  
      JLabel Title = new JLabel("=== ACTUALIZAR PEDIDO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
  
      if(proveeController!=null && productoController!=null && proveedorController!=null)
      {
          try
          {
              // Panel de búsqueda
              JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              JLabel idLabel = new JLabel("ID del pedido a actualizar:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              searchPanel.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              idTextField.setPreferredSize(new Dimension(textFieldWidth / 2, buttonHeight - 10));
              searchPanel.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Pedido");
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              buscarButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              searchPanel.add(buscarButton);
              
              gbc.gridx = 0;
              gbc.gridy = 1;
              gbc.gridwidth = 3;
              frame.add(searchPanel, gbc);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 3;
              frame.add(statusLabel, gbc);
              
              // ID Proveedor
              JLabel proveedorLabel = new JLabel("ID del Proveedor:");
              proveedorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.gridwidth = 1;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(proveedorLabel, gbc);
              
              final JTextField proveedorTextField = new JTextField(10);
              proveedorTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              proveedorTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              proveedorTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 3;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(proveedorTextField, gbc);
              
              final JButton verProveedoresButton = new JButton("Ver Proveedores");
              verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              verProveedoresButton.setBackground(new Color(0, 123, 255));
              verProveedoresButton.setForeground(Color.WHITE);
              verProveedoresButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              verProveedoresButton.setEnabled(false);
              gbc.gridx = 2;
              gbc.gridy = 3;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(verProveedoresButton, gbc);
              
              // ID Producto
              JLabel productoLabel = new JLabel("ID del Producto:");
              productoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(productoLabel, gbc);
              
              final JTextField productoTextField = new JTextField(10);
              productoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              productoTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              productoTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(productoTextField, gbc);
              
              final JButton verProductosButton = new JButton("Ver Productos");
              verProductosButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              verProductosButton.setBackground(new Color(0, 123, 255));
              verProductosButton.setForeground(Color.WHITE);
              verProductosButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              verProductosButton.setEnabled(false);
              gbc.gridx = 2;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(verProductosButton, gbc);
              
              // Cantidad
              JLabel cantidadLabel = new JLabel("Cantidad:");
              cantidadLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(cantidadLabel, gbc);
              
              final JTextField cantidadTextField = new JTextField(10);
              cantidadTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              cantidadTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              cantidadTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(cantidadTextField, gbc);
              
              // Precio
              JLabel precioLabel = new JLabel("Precio:");
              precioLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 6;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(precioLabel, gbc);
              
              final JTextField precioTextField = new JTextField(10);
              precioTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              precioTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              precioTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 6;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(precioTextField, gbc);
              
              // Fecha Provisión
              JLabel fechaLabel = new JLabel("Fecha Provisión (YYYY-MM-DD):");
              fechaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 7;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(fechaLabel, gbc);
              
              final JTextField fechaTextField = new JTextField(10);
              fechaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              fechaTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              fechaTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 7;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(fechaTextField, gbc);
              
              // Botones
              JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              guardarButton.setEnabled(false);
              guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buttonPanel.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buttonPanel.add(volverButton);
              
              gbc.gridx = 0;
              gbc.gridy = 8;
              gbc.gridwidth = 3;
              gbc.anchor = GridBagConstraints.CENTER;
              frame.add(buttonPanel, gbc);
              
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
                              
                              JTextArea proveedoresTextArea = new JTextArea();
                              proveedoresTextArea.setEditable(false);
                              proveedoresTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                              
                              StringBuilder sb = new StringBuilder();
                              for (Proveedor proveedor : proveedores) {
                                  sb.append("ID: ").append(proveedor.getID_Proveedor()).append("\n");
                                  sb.append("Empresa: ").append(proveedor.getNombre()).append("\n");
                                  sb.append("------------------------------------------\n");
                              }
                              proveedoresTextArea.setText(sb.toString());
                              
                              JScrollPane scrollPane = new JScrollPane(proveedoresTextArea);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
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
                              
                              JTextArea productosTextArea = new JTextArea();
                              productosTextArea.setEditable(false);
                              productosTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                              
                              StringBuilder sb = new StringBuilder();
                              for (Producto producto : productos) {
                                  sb.append("ID: ").append(producto.getID_Producto()).append("\n");
                                  sb.append("Nombre: ").append(producto.getNombre()).append("\n");
                                  sb.append("------------------------------------------\n");
                              }
                              productosTextArea.setText(sb.toString());
                              
                              JScrollPane scrollPane = new JScrollPane(productosTextArea);
                              dialog.add(scrollPane, BorderLayout.CENTER);
                              
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
                              
                              statusLabel.setText("Pedido encontrado. Modifique los campos necesarios.");
                              
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
                              statusLabel.setText("Error: No se encontró el pedido a actualizar");
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
                  new MenuProvee(proveeController, productoController, proveedorController);
              });
          }
      }
      else
      {
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
              new MenuProvee(proveeController, productoController, proveedorController);
          });
      }
    }
  
    private void EliminarProvee()
    {
      JFrame frame = new JFrame("Eliminar Pedido");
      frame.setLayout(new GridBagLayout());
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int buttonWidth = (int) (screenSize.width * 0.18);
      int buttonHeight = (int) (screenSize.height * 0.06);
      int textFieldWidth = (int) (screenSize.width * 0.2);
      int textAreaWidth = (int) (screenSize.width * 0.6);
      int textAreaHeight = (int) (screenSize.height * 0.25);
      int fontSize = (int) (screenSize.height * 0.022);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 10, 10, 10);
  
      JLabel Title = new JLabel("=== ELIMINAR PEDIDO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
  
      if(proveeController!=null)
      {
          try
          {
              // Panel de búsqueda
              JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              JLabel idLabel = new JLabel("ID del pedido a eliminar:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              searchPanel.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              idTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              searchPanel.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Pedido");
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              buscarButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              searchPanel.add(buscarButton);
              
              gbc.gridx = 0;
              gbc.gridy = 1;
              gbc.gridwidth = 3;
              frame.add(searchPanel, gbc);
              
              final JLabel infoLabel = new JLabel("Información del pedido:");
              infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 3;
              frame.add(infoLabel, gbc);
              
              final JTextArea proveeInfo = new JTextArea();
              proveeInfo.setEditable(false);
              proveeInfo.setBackground(new Color(240, 240, 240));
              proveeInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              proveeInfo.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 4));
              
              JScrollPane scrollPane = new JScrollPane(proveeInfo);
              scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.gridwidth = 3;
              gbc.fill = GridBagConstraints.BOTH;
              frame.add(scrollPane, gbc);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 4;
              gbc.gridwidth = 3;
              gbc.fill = GridBagConstraints.HORIZONTAL;
              frame.add(statusLabel, gbc);
              
              // Panel de botones
              JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              final JButton eliminarButton = new JButton("Eliminar Pedido");
              eliminarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
              eliminarButton.setBackground(new Color(220, 53, 69));
              eliminarButton.setForeground(Color.WHITE);
              eliminarButton.setEnabled(false);
              eliminarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buttonPanel.add(eliminarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buttonPanel.add(volverButton);
              
              gbc.gridx = 0;
              gbc.gridy = 5;
              gbc.gridwidth = 3;
              gbc.fill = GridBagConstraints.NONE;
              frame.add(buttonPanel, gbc);
              
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
                              "¿Está seguro de que desea eliminar este pedido?", 
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
                  new MenuProvee(proveeController, productoController, proveedorController);
              });
          }
      }
      else
      {
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
              new MenuProvee(proveeController, productoController, proveedorController);
          });
      }
    }
} 