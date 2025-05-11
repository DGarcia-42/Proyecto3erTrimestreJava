package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuProveedor extends JFrame {
    private ProveedorController proveedorController;

    // Constructor de la clase MenuProveedor con su controlador
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

        JButton TodoProveedor = new JButton("Ver todos los proveedores");
        TodoProveedor.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        TodoProveedor.setFont(new Font("Roboto", Font.BOLD, fontSize));
        TodoProveedor.setBackground(new Color(0, 123, 255));
        TodoProveedor.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(TodoProveedor, gbc);
        TodoProveedor.addActionListener(e -> {
            dispose();
            ListarProveedores();
        });

        JButton BuscarProveedor = new JButton("Buscar proveedor por ID");
        BuscarProveedor.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarProveedor.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarProveedor.setBackground(new Color(0, 123, 255));
        BuscarProveedor.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarProveedor, gbc);
        BuscarProveedor.addActionListener(e -> {
            dispose();
            BuscarProveedor();
        });

        JButton AñadirProveedor = new JButton("Añadir nuevo proveedor");
        AñadirProveedor.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirProveedor.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirProveedor.setBackground(new Color(76, 175, 80));
        AñadirProveedor.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirProveedor, gbc);
        AñadirProveedor.addActionListener(e -> {
            dispose();
            AñadirProveedor();
        });

        JButton ModificarProveedor = new JButton("Actualizar proveedor");
        ModificarProveedor.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ModificarProveedor.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ModificarProveedor.setBackground(new Color(0, 123, 255));
        ModificarProveedor.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ModificarProveedor, gbc);
        ModificarProveedor.addActionListener(e -> {
            dispose();
            ActualizarProveedor();
        });

        JButton EliminarProveedor = new JButton("Eliminar proveedor");
        EliminarProveedor.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarProveedor.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarProveedor.setBackground(new Color(0, 123, 255));
        EliminarProveedor.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarProveedor, gbc);
        EliminarProveedor.addActionListener(e -> {
            dispose();
            EliminarProveedor();
        });

        JButton VolverMenu = new JButton("Volver al menu principal");
        VolverMenu.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        VolverMenu.setFont(new Font("Roboto", Font.BOLD, fontSize));
        VolverMenu.setBackground(new Color(0, 123, 255));
        VolverMenu.setForeground(Color.WHITE);
        gbc.gridy = 6;
        add(VolverMenu, gbc);
        VolverMenu.addActionListener(e -> {
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

      JLabel Title = new JLabel("=== LISTADO DE PROVEEDORES ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
  
      //Se comprueba si hay conexión a la base de datos
      if(proveedorController!=null)
      {
          try
          {
              //Se obtiene todos los proveedores de la base de datos
              List<Proveedor> proveedores = proveedorController.findAll();
              
              //Si no hay proveedores, se muestra un mensaje
              if(proveedores==null || proveedores.isEmpty())
              {
                  JLabel noProveedoresLabel = new JLabel("No hay proveedores registrados en el sistema");
                  noProveedoresLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                  noProveedoresLabel.setHorizontalAlignment(SwingConstants.CENTER);
                  gbc.gridy = 1;
                  frame.add(noProveedoresLabel, gbc);
                  
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
              //Si hay proveedores, se muestra el listado de proveedores
              else
              {
                  JTextArea proveedoresTextArea = new JTextArea();
                  proveedoresTextArea.setEditable(false);
                  proveedoresTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));

                  //Se crea un StringBuilder para almacenar la información de los proveedores
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
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener los proveedores: " + e.getMessage());
          } 
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
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
  
      JLabel Title = new JLabel("=== BUSCAR PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
            //Se crea un JLabel y un JTextField para introducir el ID del proveedor 
            JLabel idLabel = new JLabel("ID del proveedor:");
            idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            frame.add(idLabel, gbc);
  
            JTextField idTextField = new JTextField(10);
            idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
            gbc.gridx = 1;
            frame.add(idTextField, gbc);
  
            //Se crea un JLabel y un JTextArea para mostrar la información del proveedor
            final JLabel infoLabel = new JLabel("Información del proveedor:");
            infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            frame.add(infoLabel, gbc);
            
            final JTextArea proveedorInfo = new JTextArea();
            proveedorInfo.setEditable(false);
            proveedorInfo.setBackground(new Color(240, 240, 240));
            proveedorInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            proveedorInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
            JScrollPane scrollPane = new JScrollPane(proveedorInfo);
            scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
            gbc.gridy = 3;
            frame.add(scrollPane, gbc);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
            gbc.gridy = 4;
            frame.add(statusLabel, gbc);
            
            JButton buscarButton = new JButton("Buscar Proveedor");
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
            gbc.gridy = 5;
            frame.add(volverButton, gbc);

            //Acción para buscar el proveedor
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Se obtiene el ID del proveedor introducido
                        String input = idTextField.getText();
                        long id = Long.parseLong(input);

                        //Se busca el proveedor con el ID introducido
                        Proveedor proveedor = proveedorController.findById(id);

                        //Si el proveedor existe, se muestra la información del proveedor
                        if (proveedor != null) {
                            //Se crea un StringBuilder para almacenar la información del proveedor
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

                            //Se muestra la información del proveedor
                            proveedorInfo.setText(sb.toString());
                            statusLabel.setText("");
                        } else {
                            //Si el proveedor no existe,se muestra un mensaje de error
                            proveedorInfo.setText("");
                            statusLabel.setText("No se encontró un proveedor con el ID: " + id);
                        }
                    } catch (NumberFormatException nfe) {
                        //Si el ID introducido no es un número válido, se muestra un mensaje de error
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        proveedorInfo.setText("");
                    }
                }
            });
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProveedor(proveedorController);
            });
          }
          catch(Exception e)
          {
              System.err.println("Error al buscar el proveedor: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexión a la base de datos");
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
      int fieldWidth = (int) (screenSize.width * 0.18);
      int fieldHeight = (int) (screenSize.height * 0.05);
      int buttonWidth = (int) (screenSize.width * 0.18);
      int buttonHeight = (int) (screenSize.height * 0.06);
      int fontSize = (int) (screenSize.height * 0.022);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = new Insets(10, 0, 10, 0);
  
      JLabel Title = new JLabel("=== AÑADIR PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
            //Se crea un JLabel y un JTextField para introducir cada campo del proveedor
              JLabel cifLabel = new JLabel("CIF (1 letra + 8 números):");
              cifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              frame.add(cifLabel, gbc);
              
              JTextField cifTextField = new JTextField(20);
              cifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              cifTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(cifTextField, gbc);
              
              JLabel nombreLabel = new JLabel("Nombre de la empresa:");
              nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 2;
              frame.add(nombreLabel, gbc);
              
              JTextField nombreTextField = new JTextField(20);
              nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(nombreTextField, gbc);
              
              JLabel responsableLabel = new JLabel("Nombre del responsable:");
              responsableLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 3;
              frame.add(responsableLabel, gbc);
              
              JTextField responsableTextField = new JTextField(20);
              responsableTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              responsableTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(responsableTextField, gbc);
              
              JLabel paisLabel = new JLabel("País:");
              paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 4;
              frame.add(paisLabel, gbc);
              
              JTextField paisTextField = new JTextField(20);
              paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              paisTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(paisTextField, gbc);
              
              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 5;
              frame.add(provinciaLabel, gbc);
              
              JTextField provinciaTextField = new JTextField(20);
              provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              provinciaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(provinciaTextField, gbc);
              
              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 6;
              frame.add(direccionLabel, gbc);
              
              JTextField direccionTextField = new JTextField(20);
              direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              direccionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(direccionTextField, gbc);
              
              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 7;
              frame.add(emailLabel, gbc);
              
              JTextField emailTextField = new JTextField(20);
              emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              emailTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(emailTextField, gbc);
              
              JLabel telefonoLabel = new JLabel("Teléfono (9 dígitos):");
              telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 8;
              frame.add(telefonoLabel, gbc);
              
              JTextField telefonoTextField = new JTextField(20);
              telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              telefonoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(telefonoTextField, gbc);
              
              JLabel cpLabel = new JLabel("Código Postal (5 dígitos):");
              cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 9;
              frame.add(cpLabel, gbc);
              
              JTextField cpTextField = new JTextField(20);
              cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              cpTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(cpTextField, gbc);
              
              JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 10;
              gbc.gridwidth = 2;
              frame.add(statusLabel, gbc);
              
              JButton guardarButton = new JButton("Guardar Proveedor");
              guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              guardarButton.setBackground(new Color(40, 167, 69));
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
              frame.add(volverButton, gbc);
              
              //Acción para guardar el proveedor
              guardarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          //Se obtiene de cada campo del proveedor
                          String cif = cifTextField.getText();
                          String nombre = nombreTextField.getText();
                          String responsable = responsableTextField.getText();
                          String pais = paisTextField.getText();
                          String provincia = provinciaTextField.getText();
                          String direccion = direccionTextField.getText();
                          String email = emailTextField.getText();
                          String telefono = telefonoTextField.getText();
                          String cp = cpTextField.getText();

                          //Se comprueba si hay campos vacíos
                          if(cif.isEmpty() || nombre.isEmpty() || responsable.isEmpty() || 
                             pais.isEmpty() || provincia.isEmpty() || direccion.isEmpty() || 
                             email.isEmpty() || telefono.isEmpty() || cp.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }

                          //Se comprueba si el CIF tiene el formato correcto
                          if(!cif.matches("[A-Za-z]\\d{8}")) {
                              statusLabel.setText("Error: El CIF debe tener 1 letra seguida de 8 números");
                              return;
                          }

                          //Se comprueba si el email tiene el formato correcto
                          if(!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                              statusLabel.setText("Error: El email debe tener un formato válido");
                              return;
                          }

                          //Se comprueba si el teléfono tiene el formato correcto
                          if(!telefono.matches("\\d{9}")) {
                              statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                              return;
                          }

                          //Se comprueba si el código postal tiene el formato correcto
                          if(!cp.matches("\\d{5}")) {
                              statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                              return;
                          }

                          //Se crea un nuevo proveedor
                          
                          Proveedor nuevoProveedor = new Proveedor();
                          nuevoProveedor.setCIF(cif);
                          nuevoProveedor.setNombre(nombre);
                          nuevoProveedor.setNombre_Responsable(responsable);
                          nuevoProveedor.setPais(pais);
                          nuevoProveedor.setProvincia(provincia);
                          nuevoProveedor.setDireccion(direccion);
                          nuevoProveedor.setEmail(email);
                          nuevoProveedor.setTelefono(telefono);
                          nuevoProveedor.setCodigo_Postal(cp);
                          
                          //Se guarda el nuevo proveedor en la base de datos
                          proveedorController.save(nuevoProveedor);

                          statusLabel.setText("Proveedor añadido correctamente.");
                          
                          //Se limpia el formulario
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
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProveedor(proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al añadir el proveedor: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
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
      int fieldWidth = (int) (screenSize.width * 0.18);
      int fieldHeight = (int) (screenSize.height * 0.05);
      int buttonWidth = (int) (screenSize.width * 0.18);
      int buttonHeight = (int) (screenSize.height * 0.06);
      int fontSize = (int) (screenSize.height * 0.022);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = new Insets(10, 0, 10, 0);
  
      JLabel Title = new JLabel("=== ACTUALIZAR PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
            //Se crea un JLabel y un JTextField para introducir el ID del proveedor
              JLabel idLabel = new JLabel("ID del proveedor:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              frame.add(idLabel, gbc);

              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(idTextField, gbc);

              JButton buscarButton = new JButton("Buscar Proveedor");
              buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              gbc.gridx = 0;
              gbc.gridy = 2;
              frame.add(buscarButton, gbc);

              //Se crea un JLabel y un JTextField para mostrar cada campo del proveedor
              JLabel cifLabel = new JLabel("CIF (1 letra + 8 números):");
              cifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 3;
              frame.add(cifLabel, gbc);

              JTextField cifTextField = new JTextField(20);
              cifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              cifTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              cifTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(cifTextField, gbc);

              JLabel nombreLabel = new JLabel("Nombre Empresa:");
              nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 4;
              frame.add(nombreLabel, gbc);

              JTextField nombreTextField = new JTextField(20);
              nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              nombreTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(nombreTextField, gbc);

              JLabel responsableLabel = new JLabel("Nombre Responsable:");
              responsableLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 5;
              frame.add(responsableLabel, gbc);

              JTextField responsableTextField = new JTextField(20);
              responsableTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              responsableTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              responsableTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(responsableTextField, gbc);

              JLabel paisLabel = new JLabel("País:");
              paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 6;
              frame.add(paisLabel, gbc);

              JTextField paisTextField = new JTextField(20);
              paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              paisTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              paisTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(paisTextField, gbc);

              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 7;
              frame.add(provinciaLabel, gbc);

              JTextField provinciaTextField = new JTextField(20);
              provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              provinciaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              provinciaTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(provinciaTextField, gbc);

              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 8;
              frame.add(direccionLabel, gbc);

              JTextField direccionTextField = new JTextField(20);
              direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              direccionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              direccionTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(direccionTextField, gbc);

              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 9;
              frame.add(emailLabel, gbc);

              JTextField emailTextField = new JTextField(20);
              emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              emailTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              emailTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(emailTextField, gbc);

              JLabel telefonoLabel = new JLabel("Teléfono (9 dígitos):");
              telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 10;
              frame.add(telefonoLabel, gbc);

              JTextField telefonoTextField = new JTextField(20);
              telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              telefonoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              telefonoTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(telefonoTextField, gbc);

              JLabel cpLabel = new JLabel("Código Postal (5 dígitos):");
              cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 11;
              frame.add(cpLabel, gbc);

              JTextField cpTextField = new JTextField(20);
              cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              cpTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              cpTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(cpTextField, gbc);

              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 12;
              gbc.gridwidth = 2;
              frame.add(statusLabel, gbc);

              final JButton actualizarButton = new JButton("Actualizar Proveedor");
              actualizarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              actualizarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              actualizarButton.setBackground(new Color(40, 167, 69));
              actualizarButton.setForeground(Color.WHITE);
              actualizarButton.setEnabled(false);
              gbc.gridx = 0;
              gbc.gridy = 13;
              gbc.gridwidth = 1;
              frame.add(actualizarButton, gbc);

              JButton volverButton = new JButton("Volver");
              volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              gbc.gridx = 1;
              frame.add(volverButton, gbc);

              //Se crea un array para almacenar el ID del proveedor
              final Long[] proveedorId = new Long[1];

              //Acción para buscar el proveedor
              buscarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);

                          //Se busca el proveedor con el ID introducido
                          Proveedor proveedor = proveedorController.findById(id);

                          //Si el proveedor existe, se muestra la información del proveedor
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
                              
                            //Se habilitan los campos para que el usuario pueda editarlos
                              cifTextField.setEnabled(true);
                              nombreTextField.setEnabled(true);
                              responsableTextField.setEnabled(true);
                              paisTextField.setEnabled(true);
                              provinciaTextField.setEnabled(true);
                              direccionTextField.setEnabled(true);
                              emailTextField.setEnabled(true);
                              telefonoTextField.setEnabled(true);
                              cpTextField.setEnabled(true);
                              actualizarButton.setEnabled(true);
                              
                              statusLabel.setText("Proveedor encontrado. Puedes editar los campos y actualizar.");
                          } else {
                              //Si el proveedor no existe, se limpia el formulario y se deshabilitan los campos
                              cifTextField.setText("");
                              nombreTextField.setText("");
                              responsableTextField.setText("");
                              paisTextField.setText("");
                              provinciaTextField.setText("");
                              direccionTextField.setText("");
                              emailTextField.setText("");
                              telefonoTextField.setText("");
                              cpTextField.setText("");
                              
                              cifTextField.setEnabled(false);
                              nombreTextField.setEnabled(false);
                              responsableTextField.setEnabled(false);
                              paisTextField.setEnabled(false);
                              provinciaTextField.setEnabled(false);
                              direccionTextField.setEnabled(false);
                              emailTextField.setEnabled(false);
                              telefonoTextField.setEnabled(false);
                              cpTextField.setEnabled(false);
                              actualizarButton.setEnabled(false);
                              
                              statusLabel.setText("No se encontró un proveedor con el ID: " + id);
                          }
                      } catch (NumberFormatException nfe) {
                          //Si el ID introducido no es un número válido,se deshabilitan los campos
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          cifTextField.setEnabled(false);
                          nombreTextField.setEnabled(false);
                          responsableTextField.setEnabled(false);
                          paisTextField.setEnabled(false);
                          provinciaTextField.setEnabled(false);
                          direccionTextField.setEnabled(false);
                          emailTextField.setEnabled(false);
                          telefonoTextField.setEnabled(false);
                          cpTextField.setEnabled(false);
                          actualizarButton.setEnabled(false);
                      }
                  }
              });

              //Acción para actualizar el proveedor
              actualizarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          //Se obtiene de cada campo del proveedor
                          String cif = cifTextField.getText();
                          String nombre = nombreTextField.getText();
                          String responsable = responsableTextField.getText();
                          String pais = paisTextField.getText();
                          String provincia = provinciaTextField.getText();
                          String direccion = direccionTextField.getText();
                          String email = emailTextField.getText();
                          String telefono = telefonoTextField.getText();
                          String cp = cpTextField.getText();

                          //Se comprueba si hay campos vacíos
                          if(cif.isEmpty() || nombre.isEmpty() || responsable.isEmpty() || 
                             pais.isEmpty() || provincia.isEmpty() || direccion.isEmpty() || 
                             email.isEmpty() || telefono.isEmpty() || cp.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }

                          //Se comprueba si el CIF tiene el formato correcto
                          if(!cif.matches("[A-Za-z]\\d{8}")) {
                              statusLabel.setText("Error: El CIF debe tener 1 letra seguida de 8 números");
                              return;
                          }

                          //Se comprueba si el email tiene el formato correcto
                          if(!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                              statusLabel.setText("Error: El email debe tener un formato válido");
                              return;
                          }

                          //Se comprueba si el teléfono tiene el formato correcto
                          if(!telefono.matches("\\d{9}")) {
                              statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                              return;
                          }

                          //Se comprueba si el código postal tiene el formato correcto
                          if(!cp.matches("\\d{5}")) {
                              statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                              return;
                          }

                          //Se crea un nuevo proveedor
                          Proveedor proveedorActualizado = new Proveedor();
                          proveedorActualizado.setID_Proveedor(proveedorId[0]);
                          proveedorActualizado.setCIF(cif);
                          proveedorActualizado.setNombre(nombre);
                          proveedorActualizado.setNombre_Responsable(responsable);
                          proveedorActualizado.setPais(pais);
                          proveedorActualizado.setProvincia(provincia);
                          proveedorActualizado.setDireccion(direccion);
                          proveedorActualizado.setEmail(email);
                          proveedorActualizado.setTelefono(telefono);
                          proveedorActualizado.setCodigo_Postal(cp);

                          //Se actualiza el proveedor en la base de datos
                          proveedorController.save(proveedorActualizado);

                          statusLabel.setText("Proveedor actualizado correctamente.");
                      } catch (Exception ex) {
                          statusLabel.setText("Error al actualizar el proveedor: " + ex.getMessage());
                      }
                  }
              });

              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuProveedor(proveedorController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al actualizar el proveedor: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
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
  
      JLabel Title = new JLabel("=== ELIMINAR PROVEEDOR ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
  
      if(proveedorController!=null)
      {
          try
          {
            //Se crea un JLabel y un JTextField para introducir el ID del proveedor
              JLabel idLabel = new JLabel("ID del proveedor a eliminar:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              frame.add(idLabel, gbc);

              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(idTextField, gbc);

              //Se crea un JLabel y un JTextArea para mostrar la información del proveedor
              final JLabel infoLabel = new JLabel("Información del proveedor:");
              infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 2;
              frame.add(infoLabel, gbc);

              final JTextArea proveedorInfo = new JTextArea();
              proveedorInfo.setEditable(false);
              proveedorInfo.setBackground(new Color(240, 240, 240));
              proveedorInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              proveedorInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
              JScrollPane scrollPane = new JScrollPane(proveedorInfo);
              scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
              gbc.gridy = 3;
              frame.add(scrollPane, gbc);

              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
              gbc.gridy = 4;
              frame.add(statusLabel, gbc);

              final JButton buscarButton = new JButton("Buscar Proveedor");
              buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              gbc.gridx = 0;
              gbc.gridy = 5;
              gbc.gridwidth = 1;
              frame.add(buscarButton, gbc);

              final JButton eliminarButton = new JButton("Eliminar Proveedor");
              eliminarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              eliminarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              eliminarButton.setBackground(new Color(220, 53, 69));
              eliminarButton.setForeground(Color.WHITE);
              eliminarButton.setEnabled(false);
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

              final Long[] proveedorId = new Long[1];

              //Acción para buscar el proveedor
              buscarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);

                          //Se busca el proveedor con el ID introducido
                          Proveedor proveedor = proveedorController.findById(id);
                          if (proveedor != null) {
                              proveedorId[0] = proveedor.getID_Proveedor();

                              //Se crea un StringBuilder para almacenar la información del proveedor
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
                  }
              });

              //Acción para eliminar el proveedor
              eliminarButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          //Se muestra un cuadro de diálogo para eliminar el proveedor
                          int confirmacion = JOptionPane.showConfirmDialog(frame,
                                  "¿Está seguro de que desea eliminar este proveedor?",
                                  "Confirmar eliminación",
                                  JOptionPane.YES_NO_OPTION);

                          //Si el usuario confirma la eliminación, se elimina el proveedor
                          if (confirmacion == JOptionPane.YES_OPTION) {
                              proveedorController.delete(proveedorId[0]);
                              proveedorInfo.setText("");
                              idTextField.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("Proveedor eliminado correctamente.");
                          }
                      } catch (Exception ex) {
                          //Si hay un error, se muestra un mensaje de error
                          statusLabel.setText("Error al eliminar el proveedor: " + ex.getMessage());
                      }
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
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
} 