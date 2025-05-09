package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuProveedor extends JFrame {
    private ProveedorController proveedorController;

    public MenuProveedor(ProveedorController proveedorController) {
        this.proveedorController = proveedorController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Proveedor");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE PROVEEDORES ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton ListarProveedoresBtn = new JButton("Ver todos los proveedores");
        ListarProveedoresBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ListarProveedoresBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ListarProveedoresBtn.setBackground(new Color(0, 123, 255));
        ListarProveedoresBtn.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(ListarProveedoresBtn, gbc);
        ListarProveedoresBtn.addActionListener(e -> {
            dispose();
            ListarProveedores();
        });

        JButton BuscarProveedorBtn = new JButton("Buscar proveedor por ID");
        BuscarProveedorBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarProveedorBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarProveedorBtn.setBackground(new Color(0, 123, 255));
        BuscarProveedorBtn.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarProveedorBtn, gbc);
        BuscarProveedorBtn.addActionListener(e -> {
            dispose();
            BuscarProveedor();
        });

        JButton AñadirProveedorBtn = new JButton("Añadir nuevo proveedor");
        AñadirProveedorBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirProveedorBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirProveedorBtn.setBackground(new Color(76, 175, 80));
        AñadirProveedorBtn.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirProveedorBtn, gbc);
        AñadirProveedorBtn.addActionListener(e -> {
            dispose();
            AñadirProveedor();
        });

        JButton ActualizarProveedorBtn = new JButton("Actualizar proveedor");
        ActualizarProveedorBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ActualizarProveedorBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ActualizarProveedorBtn.setBackground(new Color(0, 123, 255));
        ActualizarProveedorBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ActualizarProveedorBtn, gbc);
        ActualizarProveedorBtn.addActionListener(e -> {
            dispose();
            ActualizarProveedor();
        });

        JButton EliminarProveedorBtn = new JButton("Eliminar proveedor");
        EliminarProveedorBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarProveedorBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarProveedorBtn.setBackground(new Color(0, 123, 255));
        EliminarProveedorBtn.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarProveedorBtn, gbc);
        EliminarProveedorBtn.addActionListener(e -> {
            dispose();
            EliminarProveedor();
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

    private void ListarProveedores()
    {
      JFrame frame = new JFrame("Listar Proveedores");
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

      JLabel Title = new JLabel("=== LISTA DE PROVEEDORES ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
              List<Proveedor> proveedores = proveedorController.findAll();
              if(proveedores!=null && !proveedores.isEmpty())
              {
                  JTextArea proveedoresTextArea = new JTextArea();
                  proveedoresTextArea.setEditable(false);
                  proveedoresTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));
                  
                  StringBuilder sb = new StringBuilder();
                  for (Proveedor proveedor : proveedores) {
                      sb.append("ID: ").append(proveedor.getID_Proveedor()).append("\n");
                      sb.append("CIF: ").append(proveedor.getCIF()).append("\n");
                      sb.append("Empresa: ").append(proveedor.getNombre()).append("\n");
                      sb.append("Responsable: ").append(proveedor.getNombre_Responsable()).append("\n");
                      sb.append("País: ").append(proveedor.getPais()).append("\n");
                      sb.append("Provincia: ").append(proveedor.getProvincia()).append("\n");
                      sb.append("Dirección: ").append(proveedor.getDireccion()).append("\n");
                      sb.append("Email: ").append(proveedor.getEmail()).append("\n");
                      sb.append("Teléfono: ").append(proveedor.getTelefono()).append("\n");
                      sb.append("Código Postal: ").append(proveedor.getCodigo_Postal()).append("\n");
                      sb.append("------------------------------------------\n");
                  }
                  proveedoresTextArea.setText(sb.toString());
                  
                  JScrollPane scrollPane = new JScrollPane(proveedoresTextArea);
                  scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                  gbc.gridy = 1;
                  frame.add(scrollPane, gbc);
              }
              else
              {
                  JLabel noProveedoresLabel = new JLabel("No hay proveedores registrados en el sistema");
                  noProveedoresLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                  noProveedoresLabel.setHorizontalAlignment(SwingConstants.CENTER);
                  gbc.gridy = 1;
                  frame.add(noProveedoresLabel, gbc);
              }
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener los proveedores: " + e.getMessage());
              JLabel errorLabel = new JLabel("Error al obtener proveedores: " + e.getMessage());
              errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
              gbc.gridy = 1;
              frame.add(errorLabel, gbc);
          } 
      }
      else
      {
          JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
          errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
          errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
          new MenuProveedor(proveedorController);
      });
    }
  
    private void BuscarProveedor()
    {
      JFrame frame = new JFrame("Buscar Proveedor");
      frame.setLayout(new GridBagLayout());
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int buttonWidth = (int) (screenSize.width * 0.18);
      int buttonHeight = (int) (screenSize.height * 0.06);
      int textFieldWidth = (int) (screenSize.width * 0.2);
      int labelWidth = (int) (screenSize.width * 0.15);
      int textAreaWidth = (int) (screenSize.width * 0.6);
      int textAreaHeight = (int) (screenSize.height * 0.4);
      int fontSize = (int) (screenSize.height * 0.022);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 10, 10, 10);
  
      JLabel Title = new JLabel("=== BUSCAR PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID del proveedor:");
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
              
              final JTextArea proveedorTextArea = new JTextArea();
              proveedorTextArea.setEditable(false);
              proveedorTextArea.setBackground(new Color(240, 240, 240));
              proveedorTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              proveedorTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 4));
              proveedorTextArea.setVisible(false);
              
              JScrollPane scrollPane = new JScrollPane(proveedorTextArea);
              scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.gridwidth = 3;
              gbc.fill = GridBagConstraints.BOTH;
              frame.add(scrollPane, gbc);
              
              buscarButton.addActionListener(e -> {
                  String input = idTextField.getText();
                  try
                  {
                      long id = Long.parseLong(input);
                      statusLabel.setText("Buscando proveedor...");
                      
                      Proveedor proveedor = proveedorController.findById(id);
                      if(proveedor != null)
                      {
                          statusLabel.setText("Proveedor encontrado:");
                          
                          StringBuilder sb = new StringBuilder();
                          sb.append("ID: ").append(proveedor.getID_Proveedor()).append("\n");
                          sb.append("CIF: ").append(proveedor.getCIF()).append("\n");
                          sb.append("Empresa: ").append(proveedor.getNombre()).append("\n");
                          sb.append("Responsable: ").append(proveedor.getNombre_Responsable()).append("\n");
                          sb.append("País: ").append(proveedor.getPais()).append("\n");
                          sb.append("Provincia: ").append(proveedor.getProvincia()).append("\n");
                          sb.append("Dirección: ").append(proveedor.getDireccion()).append("\n");
                          sb.append("Email: ").append(proveedor.getEmail()).append("\n");
                          sb.append("Teléfono: ").append(proveedor.getTelefono()).append("\n");
                          sb.append("Código Postal: ").append(proveedor.getCodigo_Postal()).append("\n");
                          
                          proveedorTextArea.setText(sb.toString());
                          proveedorTextArea.setVisible(true);
                          scrollPane.setVisible(true);
                      }
                      else
                      {
                          statusLabel.setText("Proveedor no encontrado");
                          proveedorTextArea.setVisible(false);
                          scrollPane.setVisible(false);
                      }
                  }
                  catch(NumberFormatException nfe)
                  {
                      statusLabel.setText("ID inválido. Introduce un número válido.");
                      proveedorTextArea.setVisible(false);
                      scrollPane.setVisible(false);
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
                  new MenuProveedor(proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al buscar el proveedor: " + e.getMessage());
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
                  new MenuProveedor(proveedorController);
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
              new MenuProveedor(proveedorController);
          });
      }
    }
  
    private void AñadirProveedor()
    {
      JFrame frame = new JFrame("Añadir Proveedor");
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
  
      JLabel Title = new JLabel("=== AÑADIR NUEVO PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
              // CIF
              JLabel cifLabel = new JLabel("CIF (1 letra + 8 números):");
              cifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(cifLabel, gbc);
              
              JTextField cifTextField = new JTextField(10);
              cifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              cifTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 1;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(cifTextField, gbc);
              
              // Nombre Empresa
              JLabel nombreLabel = new JLabel("Nombre de la empresa:");
              nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(nombreLabel, gbc);
              
              JTextField nombreTextField = new JTextField(10);
              nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              nombreTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 2;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(nombreTextField, gbc);
              
              // Responsable
              JLabel responsableLabel = new JLabel("Nombre del responsable:");
              responsableLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(responsableLabel, gbc);
              
              JTextField responsableTextField = new JTextField(10);
              responsableTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              responsableTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 3;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(responsableTextField, gbc);
              
              // País
              JLabel paisLabel = new JLabel("País:");
              paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(paisLabel, gbc);
              
              JTextField paisTextField = new JTextField(10);
              paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              paisTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(paisTextField, gbc);
              
              // Provincia
              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(provinciaLabel, gbc);
              
              JTextField provinciaTextField = new JTextField(10);
              provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              provinciaTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(provinciaTextField, gbc);
              
              // Dirección
              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 6;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(direccionLabel, gbc);
              
              JTextField direccionTextField = new JTextField(10);
              direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              direccionTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 6;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(direccionTextField, gbc);
              
              // Email
              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 7;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(emailLabel, gbc);
              
              JTextField emailTextField = new JTextField(10);
              emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              emailTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 7;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(emailTextField, gbc);
              
              // Teléfono
              JLabel telefonoLabel = new JLabel("Teléfono (9 dígitos):");
              telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 8;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(telefonoLabel, gbc);
              
              JTextField telefonoTextField = new JTextField(10);
              telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              telefonoTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 8;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(telefonoTextField, gbc);
              
              // Código Postal
              JLabel cpLabel = new JLabel("Código Postal (5 dígitos):");
              cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 9;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(cpLabel, gbc);
              
              JTextField cpTextField = new JTextField(10);
              cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              cpTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              gbc.gridx = 1;
              gbc.gridy = 9;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(cpTextField, gbc);
              
              // Status
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 10;
              gbc.gridwidth = 2;
              gbc.anchor = GridBagConstraints.CENTER;
              frame.add(statusLabel, gbc);
              
              // Botones
              JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              JButton guardarButton = new JButton("Guardar Proveedor");
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
              gbc.gridy = 11;
              gbc.gridwidth = 2;
              gbc.anchor = GridBagConstraints.CENTER;
              frame.add(buttonPanel, gbc);
              
              guardarButton.addActionListener(e -> {
                  try {
                      String cif = cifTextField.getText();
                      String nombre = nombreTextField.getText();
                      String responsable = responsableTextField.getText();
                      String pais = paisTextField.getText();
                      String provincia = provinciaTextField.getText();
                      String direccion = direccionTextField.getText();
                      String email = emailTextField.getText();
                      String telefono = telefonoTextField.getText();
                      String cp = cpTextField.getText();
                      
                      if (cif.isEmpty() || nombre.isEmpty() || responsable.isEmpty() || pais.isEmpty() ||
                          provincia.isEmpty() || direccion.isEmpty() || email.isEmpty() || telefono.isEmpty() || cp.isEmpty()) {
                          statusLabel.setText("Error: Todos los campos son obligatorios");
                          return;
                      }
                      
                      if (!cif.matches("[A-Z]\\d{8}")) {
                          statusLabel.setText("Error: El CIF debe tener 1 letra seguida de 8 números");
                          return;
                      }
                      
                      if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                          statusLabel.setText("Error: El email debe tener un formato válido");
                          return;
                      }
                      
                      if (!telefono.matches("\\d{9}")) {
                          statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                          return;
                      }
                      
                      if (!cp.matches("\\d{5}")) {
                          statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                          return;
                      }
                      
                      Proveedor proveedor = new Proveedor();
                      proveedor.setCIF(cif);
                      proveedor.setNombre(nombre);
                      proveedor.setNombre_Responsable(responsable);
                      proveedor.setPais(pais);
                      proveedor.setProvincia(provincia);
                      proveedor.setDireccion(direccion);
                      proveedor.setEmail(email);
                      proveedor.setTelefono(telefono);
                      proveedor.setCodigo_Postal(cp);
                      
                      proveedorController.save(proveedor);
                      
                      statusLabel.setText("Proveedor guardado correctamente");
                      
                      cifTextField.setText("");
                      nombreTextField.setText("");
                      responsableTextField.setText("");
                      paisTextField.setText("");
                      provinciaTextField.setText("");
                      direccionTextField.setText("");
                      emailTextField.setText("");
                      telefonoTextField.setText("");
                      cpTextField.setText("");
                      
                  } catch (Exception ex) {
                      statusLabel.setText("Error al guardar el proveedor: " + ex.getMessage());
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProveedor(proveedorController);
              });
          }
          catch(Exception e)
          {
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
                  new MenuProveedor(proveedorController);
              });
          }
      }
      else
      {
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
              new MenuProveedor(proveedorController);
          });
      }
    }
  
    private void ActualizarProveedor()
    {
      JFrame frame = new JFrame("Actualizar Proveedor");
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
  
      JLabel Title = new JLabel("=== ACTUALIZAR PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
              // Panel de búsqueda
              JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              JLabel idLabel = new JLabel("ID del proveedor a actualizar:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              searchPanel.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              idTextField.setPreferredSize(new Dimension(textFieldWidth / 2, buttonHeight - 10));
              searchPanel.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Proveedor");
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              buscarButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              searchPanel.add(buscarButton);
              
              gbc.gridx = 0;
              gbc.gridy = 1;
              gbc.gridwidth = 2;
              frame.add(searchPanel, gbc);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 2;
              frame.add(statusLabel, gbc);
              
              // CIF
              JLabel cifLabel = new JLabel("CIF:");
              cifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 3;
              gbc.gridwidth = 1;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(cifLabel, gbc);
              
              final JTextField cifTextField = new JTextField(10);
              cifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              cifTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              cifTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 3;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(cifTextField, gbc);
              
              // Nombre Empresa
              JLabel nombreLabel = new JLabel("Nombre de la empresa:");
              nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(nombreLabel, gbc);
              
              final JTextField nombreTextField = new JTextField(10);
              nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              nombreTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              nombreTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 4;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(nombreTextField, gbc);
              
              // Responsable
              JLabel responsableLabel = new JLabel("Nombre del responsable:");
              responsableLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(responsableLabel, gbc);
              
              final JTextField responsableTextField = new JTextField(10);
              responsableTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              responsableTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              responsableTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 5;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(responsableTextField, gbc);
              
              // País
              JLabel paisLabel = new JLabel("País:");
              paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 6;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(paisLabel, gbc);
              
              final JTextField paisTextField = new JTextField(10);
              paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              paisTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              paisTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 6;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(paisTextField, gbc);
              
              // Provincia
              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 7;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(provinciaLabel, gbc);
              
              final JTextField provinciaTextField = new JTextField(10);
              provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              provinciaTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              provinciaTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 7;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(provinciaTextField, gbc);
              
              // Dirección
              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 8;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(direccionLabel, gbc);
              
              final JTextField direccionTextField = new JTextField(10);
              direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              direccionTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              direccionTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 8;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(direccionTextField, gbc);
              
              // Email
              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 9;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(emailLabel, gbc);
              
              final JTextField emailTextField = new JTextField(10);
              emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              emailTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              emailTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 9;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(emailTextField, gbc);
              
              // Teléfono
              JLabel telefonoLabel = new JLabel("Teléfono:");
              telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 10;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(telefonoLabel, gbc);
              
              final JTextField telefonoTextField = new JTextField(10);
              telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              telefonoTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              telefonoTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 10;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(telefonoTextField, gbc);
              
              // Código Postal
              JLabel cpLabel = new JLabel("Código Postal:");
              cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 11;
              gbc.anchor = GridBagConstraints.EAST;
              frame.add(cpLabel, gbc);
              
              final JTextField cpTextField = new JTextField(10);
              cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              cpTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              cpTextField.setEnabled(false);
              gbc.gridx = 1;
              gbc.gridy = 11;
              gbc.anchor = GridBagConstraints.WEST;
              frame.add(cpTextField, gbc);
              
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
              gbc.gridy = 12;
              gbc.gridwidth = 2;
              gbc.anchor = GridBagConstraints.CENTER;
              frame.add(buttonPanel, gbc);
              
              final Long[] proveedorId = new Long[1];
              
              buscarButton.addActionListener(e -> {
                  try {
                      String input = idTextField.getText();
                      long id = Long.parseLong(input);
                      
                      statusLabel.setText("Buscando proveedor...");
                      
                      Proveedor proveedor = proveedorController.findById(id);
                      if (proveedor != null) {
                          proveedorId[0] = proveedor.getID_Proveedor();
                          
                          cifTextField.setText(proveedor.getCIF());
                          nombreTextField.setText(proveedor.getNombre());
                          responsableTextField.setText(proveedor.getNombre_Responsable());
                          paisTextField.setText(proveedor.getPais());
                          provinciaTextField.setText(proveedor.getProvincia());
                          direccionTextField.setText(proveedor.getDireccion());
                          emailTextField.setText(proveedor.getEmail());
                          telefonoTextField.setText(proveedor.getTelefono());
                          cpTextField.setText(proveedor.getCodigo_Postal());
                          
                          cifTextField.setEnabled(true);
                          nombreTextField.setEnabled(true);
                          responsableTextField.setEnabled(true);
                          paisTextField.setEnabled(true);
                          provinciaTextField.setEnabled(true);
                          direccionTextField.setEnabled(true);
                          emailTextField.setEnabled(true);
                          telefonoTextField.setEnabled(true);
                          cpTextField.setEnabled(true);
                          guardarButton.setEnabled(true);
                          
                          statusLabel.setText("Proveedor encontrado. Modifique los campos o mantenga los mismos valores.");
                      } else {
                          statusLabel.setText("No se encontró un proveedor con el ID: " + id);
                          
                          cifTextField.setEnabled(false);
                          nombreTextField.setEnabled(false);
                          responsableTextField.setEnabled(false);
                          paisTextField.setEnabled(false);
                          provinciaTextField.setEnabled(false);
                          direccionTextField.setEnabled(false);
                          emailTextField.setEnabled(false);
                          telefonoTextField.setEnabled(false);
                          cpTextField.setEnabled(false);
                          guardarButton.setEnabled(false);
                      }
                  } catch (NumberFormatException nfe) {
                      statusLabel.setText("ID inválido. Introduce un número válido.");
                  } catch (Exception ex) {
                      statusLabel.setText("Error al buscar el proveedor: " + ex.getMessage());
                  }
              });
              
              guardarButton.addActionListener(e -> {
                  try {
                      String cif = cifTextField.getText();
                      String nombre = nombreTextField.getText();
                      String responsable = responsableTextField.getText();
                      String pais = paisTextField.getText();
                      String provincia = provinciaTextField.getText();
                      String direccion = direccionTextField.getText();
                      String email = emailTextField.getText();
                      String telefono = telefonoTextField.getText();
                      String cp = cpTextField.getText();
                      
                      if (cif.isEmpty() || nombre.isEmpty() || responsable.isEmpty() || pais.isEmpty() ||
                          provincia.isEmpty() || direccion.isEmpty() || email.isEmpty() || telefono.isEmpty() || cp.isEmpty()) {
                          statusLabel.setText("Error: Todos los campos son obligatorios");
                          return;
                      }
                      
                      if (!cif.matches("[A-Z]\\d{8}")) {
                          statusLabel.setText("Error: El CIF debe tener 1 letra seguida de 8 números");
                          return;
                      }
                      
                      if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                          statusLabel.setText("Error: El email debe tener un formato válido");
                          return;
                      }
                      
                      if (!telefono.matches("\\d{9}")) {
                          statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                          return;
                      }
                      
                      if (!cp.matches("\\d{5}")) {
                          statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                          return;
                      }
                      
                      Proveedor proveedor = new Proveedor();
                      proveedor.setID_Proveedor(proveedorId[0]);
                      proveedor.setCIF(cif);
                      proveedor.setNombre(nombre);
                      proveedor.setNombre_Responsable(responsable);
                      proveedor.setPais(pais);
                      proveedor.setProvincia(provincia);
                      proveedor.setDireccion(direccion);
                      proveedor.setEmail(email);
                      proveedor.setTelefono(telefono);
                      proveedor.setCodigo_Postal(cp);
                      
                      proveedorController.save(proveedor);
                      
                      statusLabel.setText("Proveedor actualizado correctamente");
                      
                  } catch (Exception ex) {
                      statusLabel.setText("Error al actualizar el proveedor: " + ex.getMessage());
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProveedor(proveedorController);
              });
          }
          catch(Exception e)
          {
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
                  new MenuProveedor(proveedorController);
              });
          }
      }
      else
      {
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
              new MenuProveedor(proveedorController);
          });
      }
    }
  
    private void EliminarProveedor()
    {
      JFrame frame = new JFrame("Eliminar Proveedor");
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
  
      JLabel Title = new JLabel("=== ELIMINAR PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
              // Panel de búsqueda
              JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
              
              JLabel idLabel = new JLabel("ID del proveedor a eliminar:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              searchPanel.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              idTextField.setPreferredSize(new Dimension(textFieldWidth, buttonHeight - 10));
              searchPanel.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Proveedor");
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 4));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              buscarButton.setPreferredSize(new Dimension(buttonWidth / 2, buttonHeight - 10));
              searchPanel.add(buscarButton);
              
              gbc.gridx = 0;
              gbc.gridy = 1;
              gbc.gridwidth = 3;
              frame.add(searchPanel, gbc);
              
              final JLabel infoLabel = new JLabel("Información del proveedor:");
              infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 3;
              frame.add(infoLabel, gbc);
              
              final JTextArea proveedorInfo = new JTextArea();
              proveedorInfo.setEditable(false);
              proveedorInfo.setBackground(new Color(240, 240, 240));
              proveedorInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              proveedorInfo.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 4));
              
              JScrollPane scrollPane = new JScrollPane(proveedorInfo);
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
              
              final JButton eliminarButton = new JButton("Eliminar Proveedor");
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
              
              final Long[] proveedorId = new Long[1];
              
              buscarButton.addActionListener(e -> {
                  try {
                      String input = idTextField.getText();
                      long id = Long.parseLong(input);
                      
                      Proveedor proveedor = proveedorController.findById(id);
                      if (proveedor != null) {
                          proveedorId[0] = proveedor.getID_Proveedor();
                          
                          StringBuilder sb = new StringBuilder();
                          sb.append("ID: ").append(proveedor.getID_Proveedor()).append("\n");
                          sb.append("CIF: ").append(proveedor.getCIF()).append("\n");
                          sb.append("Empresa: ").append(proveedor.getNombre()).append("\n");
                          sb.append("Responsable: ").append(proveedor.getNombre_Responsable()).append("\n");
                          sb.append("País: ").append(proveedor.getPais()).append("\n");
                          sb.append("Provincia: ").append(proveedor.getProvincia()).append("\n");
                          sb.append("Dirección: ").append(proveedor.getDireccion()).append("\n");
                          sb.append("Email: ").append(proveedor.getEmail()).append("\n");
                          sb.append("Teléfono: ").append(proveedor.getTelefono()).append("\n");
                          sb.append("Código Postal: ").append(proveedor.getCodigo_Postal()).append("\n");
                          
                          proveedorInfo.setText(sb.toString());
                          eliminarButton.setEnabled(true);
                          statusLabel.setText("Proveedor encontrado. Pulse 'Eliminar Proveedor' para confirmar.");
                      } else {
                          proveedorInfo.setText("");
                          eliminarButton.setEnabled(false);
                          statusLabel.setText("No se encontró un proveedor con el ID: " + id);
                      }
                  } catch (NumberFormatException nfe) {
                      statusLabel.setText("ID inválido. Introduce un número válido.");
                      proveedorInfo.setText("");
                      eliminarButton.setEnabled(false);
                  }
              });
              
              eliminarButton.addActionListener(e -> {
                  try {
                      int confirmacion = JOptionPane.showConfirmDialog(frame, 
                          "¿Está seguro de que desea eliminar este proveedor?", 
                          "Confirmar eliminación", 
                          JOptionPane.YES_NO_OPTION);
                      
                      if (confirmacion == JOptionPane.YES_OPTION) {
                          proveedorController.delete(proveedorId[0]);
                          
                          proveedorInfo.setText("");
                          idTextField.setText("");
                          eliminarButton.setEnabled(false);
                          statusLabel.setText("Proveedor eliminado correctamente.");
                      }
                  } catch (Exception ex) {
                      statusLabel.setText("Error al eliminar el proveedor: " + ex.getMessage());
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProveedor(proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al eliminar el proveedor: " + e.getMessage());
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
                  new MenuProveedor(proveedorController);
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
              new MenuProveedor(proveedorController);
          });
      }
    }
} 