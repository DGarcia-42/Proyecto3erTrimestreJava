package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuCliente extends JFrame {
    private ClienteController clienteController;

    // Constructor de la clase MenuCliente con su controlador
    public MenuCliente(ClienteController clienteController) {
        this.clienteController = clienteController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Cliente");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE CLIENTES ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton TodoCliente = new JButton("Ver todos los clientes");
        TodoCliente.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        TodoCliente.setFont(new Font("Roboto", Font.BOLD, fontSize));
        TodoCliente.setBackground(new Color(0, 123, 255));
        TodoCliente.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(TodoCliente, gbc);
        TodoCliente.addActionListener(e -> {
            dispose();
            ListarClientes();
        });

        JButton BuscarCliente = new JButton("Buscar cliente por ID");
        BuscarCliente.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarCliente.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarCliente.setBackground(new Color(0, 123, 255));
        BuscarCliente.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarCliente, gbc);
        BuscarCliente.addActionListener(e -> {
            dispose();
            BuscarCliente();
        });

        JButton AñadirCliente = new JButton("Añadir nuevo cliente");
        AñadirCliente.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirCliente.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirCliente.setBackground(new Color(76, 175, 80));
        AñadirCliente.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirCliente, gbc);
        AñadirCliente.addActionListener(e -> {
            dispose();
            AñadirCliente();
        });

        JButton ModificarCliente = new JButton("Actualizar cliente");
        ModificarCliente.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ModificarCliente.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ModificarCliente.setBackground(new Color(0, 123, 255));
        ModificarCliente.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ModificarCliente, gbc);
        ModificarCliente.addActionListener(e -> {
            dispose();
            ActualizarCliente();
        });

        JButton EliminarCliente = new JButton("Eliminar cliente");
        EliminarCliente.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarCliente.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarCliente.setBackground(new Color(0, 123, 255));
        EliminarCliente.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarCliente, gbc);
        EliminarCliente.addActionListener(e -> {
            dispose();
            EliminarCliente();
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

    private void ListarClientes() {
        JFrame frame = new JFrame("Listar Clientes");
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

        JLabel Title = new JLabel("=== LISTADO DE CLIENTES ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        //Comprobación de si hay conexión a la base de datos
        if (clienteController != null) {
            try {
                //Se obtiene todos los clientes de la base de datos
                List<Cliente> clientes = clienteController.findAll();

                //Si no hay clientes registrados, se muestra un mensaje y un botón para volver al menu principal
                if (clientes == null || clientes.isEmpty()) {
                    JLabel noClientesLabel = new JLabel("No hay clientes registrados en el sistema");
                    noClientesLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noClientesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noClientesLabel, gbc);
                    
                    JButton volverButton = new JButton("Volver");
                    volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                    volverButton.setBackground(new Color(0, 123, 255));
                    volverButton.setForeground(Color.WHITE);
                    gbc.gridy = 2;
                    frame.add(volverButton, gbc);
                    
                    volverButton.addActionListener(e -> {
                        frame.dispose();
                        new MenuCliente(clienteController);
                    });
                //Si hay clientes registrados, se muestra el listado de clientes
                } else {
                    JTextArea clientesTextArea = new JTextArea();
                    clientesTextArea.setEditable(false);
                    clientesTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));
                    
                    //Se crea un StringBuilder para almacenar el listado de clientes
                    StringBuilder sb = new StringBuilder();
                    for (Cliente cliente : clientes) {
                        sb.append("ID: ").append(cliente.getID_Cliente()).append("\n");
                        sb.append("CIF: ").append(cliente.getCIF()).append("\n");
                        sb.append("Empresa: ").append(cliente.getNombre_Empresa()).append("\n");
                        sb.append("Responsable: ").append(cliente.getNombre_Responsable()).append("\n");
                        sb.append("Dirección: ").append(cliente.getDireccion()).append("\n");
                        sb.append("Provincia: ").append(cliente.getProvincia()).append("\n");
                        sb.append("País: ").append(cliente.getPais()).append("\n");
                        sb.append("Código Postal: ").append(cliente.getCodigo_Postal()).append("\n");
                        sb.append("Teléfono: ").append(cliente.getTelefono()).append("\n");
                        sb.append("Email: ").append(cliente.getEmail()).append("\n");
                        sb.append("------------------------------------------\n");
                    }
                    clientesTextArea.setText(sb.toString());
                    
                    JScrollPane scrollPane = new JScrollPane(clientesTextArea);
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
                        new MenuCliente(clienteController);
                    });
                }
            } catch (Exception e) {
                System.err.println("Error al listar clientes: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
  
    private void BuscarCliente()
    {
      JFrame frame = new JFrame("Buscar Cliente");
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
  
      JLabel Title = new JLabel("=== BUSCAR CLIENTE ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
      
      if(clienteController!=null)
      {
          try
          {
            //Se crea un JLabel y un JTextField para introducir el ID del cliente
            JLabel idLabel = new JLabel("ID del cliente:");
            idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            frame.add(idLabel, gbc);
  
            JTextField idTextField = new JTextField(10);
            idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
            gbc.gridx = 1;
            frame.add(idTextField, gbc);
  
            //Se crea un JLabel y un JTextArea para mostrar la información del cliente
            final JLabel infoLabel = new JLabel("Información del cliente:");
            infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            frame.add(infoLabel, gbc);
            
            final JTextArea clienteInfo = new JTextArea();
            clienteInfo.setEditable(false);
            clienteInfo.setBackground(new Color(240, 240, 240));
            clienteInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            clienteInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
            JScrollPane scrollPane = new JScrollPane(clienteInfo);
            scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
            gbc.gridy = 3;
            frame.add(scrollPane, gbc);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
            gbc.gridy = 4;
            frame.add(statusLabel, gbc);
            
            JButton buscarButton = new JButton("Buscar Cliente");
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

            //Acción para buscar el cliente
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Se obtiene el ID del cliente introducido
                        String input = idTextField.getText();
                        long id = Long.parseLong(input);

                        //Se obtiene el cliente con el ID introducido
                        Cliente cliente = clienteController.findById(id);

                        //Si el cliente existe, se muestra la información del cliente
                        if (cliente != null) {
                            clienteInfo.setText(cliente.toString());
                            statusLabel.setText("");
                        } else {
                            //Si el cliente no existe, se muestra un mensaje de error
                            clienteInfo.setText("");
                            statusLabel.setText("No se encontró un cliente con el ID: " + id);
                        }
                    } catch (NumberFormatException nfe) {
                        //Si el ID introducido no es un número, se muestra un mensaje de error
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        clienteInfo.setText("");
                    }
                }
            });
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuCliente(clienteController);
            });
          }
          catch(Exception e)
          {
              System.err.println("Error al buscar el cliente: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    } 
  
    private void AñadirCliente() {
        JFrame frame = new JFrame("Añadir Cliente");
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

        JLabel Title = new JLabel("=== AÑADIR CLIENTE ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (clienteController != null) {
            try {
                //Se crea un JLabel y un JTextField para cada campo del cliente
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

                JLabel nombreEmpresaLabel = new JLabel("Nombre de la Empresa:");
                nombreEmpresaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(nombreEmpresaLabel, gbc);

                JTextField nombreEmpresaTextField = new JTextField(20);
                nombreEmpresaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreEmpresaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(nombreEmpresaTextField, gbc);

                JLabel nombreResponsableLabel = new JLabel("Nombre del Responsable:");
                nombreResponsableLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 3;
                frame.add(nombreResponsableLabel, gbc);

                JTextField nombreResponsableTextField = new JTextField(20);
                nombreResponsableTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreResponsableTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(nombreResponsableTextField, gbc);

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

                JButton guardarButton = new JButton("Guardar Cliente");
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

                //Acción para guardar el cliente
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene el valor de cada campo del cliente
                            String cif = cifTextField.getText();
                            String nombreEmpresa = nombreEmpresaTextField.getText();
                            String nombreResponsable = nombreResponsableTextField.getText();
                            String pais = paisTextField.getText();
                            String provincia = provinciaTextField.getText();
                            String direccion = direccionTextField.getText();
                            String email = emailTextField.getText();
                            String telefono = telefonoTextField.getText();
                            String cp = cpTextField.getText();

                            //Se comprueba si algún campo está vacío
                            if(cif.isEmpty() || nombreEmpresa.isEmpty() || nombreResponsable.isEmpty() || 
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

                            //Se crea un nuevo cliente con los valores introducidos
                            Cliente nuevoCliente = new Cliente(cif, nombreEmpresa, nombreResponsable, 
                                                            pais, provincia, direccion, email, telefono, cp);

                            //Se guarda el nuevo cliente en la base de datos
                            clienteController.save(nuevoCliente);

                            statusLabel.setText("Cliente añadido correctamente.");
                            
                            //Se limpia el formulario
                            cifTextField.setText("");
                            nombreEmpresaTextField.setText("");
                            nombreResponsableTextField.setText("");
                            paisTextField.setText("");
                            provinciaTextField.setText("");
                            direccionTextField.setText("");
                            emailTextField.setText("");
                            telefonoTextField.setText("");
                            cpTextField.setText("");
                        } catch (Exception ex) {
                            statusLabel.setText("Error al guardar el cliente: " + ex.getMessage());
                        }
                    }
                });

                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuCliente(clienteController);
                });
            } catch(Exception e) {
                System.err.println("Error al añadir el cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
    
    private void ActualizarCliente() {
        JFrame frame = new JFrame("Actualizar Cliente");
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

        JLabel Title = new JLabel("=== ACTUALIZAR CLIENTE ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (clienteController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID del cliente
                JLabel idLabel = new JLabel("ID del cliente:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);

                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                JButton buscarButton = new JButton("Buscar Cliente");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(buscarButton, gbc);

                //Se crea un JLabel y un JTextField para introducir cada campo del cliente
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

                JLabel nombreEmpresaLabel = new JLabel("Nombre Empresa:");
                nombreEmpresaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                frame.add(nombreEmpresaLabel, gbc);

                JTextField nombreEmpresaTextField = new JTextField(20);
                nombreEmpresaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreEmpresaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                nombreEmpresaTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(nombreEmpresaTextField, gbc);

                JLabel nombreResponsableLabel = new JLabel("Nombre Responsable:");
                nombreResponsableLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(nombreResponsableLabel, gbc);

                JTextField nombreResponsableTextField = new JTextField(20);
                nombreResponsableTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreResponsableTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                nombreResponsableTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(nombreResponsableTextField, gbc);

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

                final JButton actualizarButton = new JButton("Actualizar Cliente");
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

                //Se crea un array para almacenar el ID del cliente
                final Long[] clienteId = new Long[1];

                //Acción para buscar el cliente
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene el cliente con el ID introducido
                            Cliente cliente = clienteController.findById(id);

                            //Si el cliente existe, se muestra la información del cliente
                            if (cliente != null) {
                                clienteId[0] = cliente.getID_Cliente();
                                cifTextField.setText(cliente.getCIF());
                                nombreEmpresaTextField.setText(cliente.getNombre_Empresa());
                                nombreResponsableTextField.setText(cliente.getNombre_Responsable());
                                paisTextField.setText(cliente.getPais());
                                provinciaTextField.setText(cliente.getProvincia());
                                direccionTextField.setText(cliente.getDireccion());
                                emailTextField.setText(cliente.getEmail());
                                telefonoTextField.setText(cliente.getTelefono());
                                cpTextField.setText(cliente.getCodigo_Postal());

                                //Se habilitan los campos para que el usuario pueda editarlos
                                cifTextField.setEnabled(true);
                                nombreEmpresaTextField.setEnabled(true);
                                nombreResponsableTextField.setEnabled(true);
                                paisTextField.setEnabled(true);
                                provinciaTextField.setEnabled(true);
                                direccionTextField.setEnabled(true);
                                emailTextField.setEnabled(true);
                                telefonoTextField.setEnabled(true);
                                cpTextField.setEnabled(true);
                                actualizarButton.setEnabled(true);
                                
                                statusLabel.setText("Cliente encontrado. Puedes editar los campos y actualizar.");
                            } else {
                                //Si el cliente no existe, se limpia el formulario y se deshabilitan los campos
                                cifTextField.setText("");
                                nombreEmpresaTextField.setText("");
                                nombreResponsableTextField.setText("");
                                paisTextField.setText("");
                                provinciaTextField.setText("");
                                direccionTextField.setText("");
                                emailTextField.setText("");
                                telefonoTextField.setText("");
                                cpTextField.setText("");
                                
                                cifTextField.setEnabled(false);
                                nombreEmpresaTextField.setEnabled(false);
                                nombreResponsableTextField.setEnabled(false);
                                paisTextField.setEnabled(false);
                                provinciaTextField.setEnabled(false);
                                direccionTextField.setEnabled(false);
                                emailTextField.setEnabled(false);
                                telefonoTextField.setEnabled(false);
                                cpTextField.setEnabled(false);
                                actualizarButton.setEnabled(false);
                                
                                statusLabel.setText("No se encontró un cliente con el ID: " + id);
                            }
                        } catch (NumberFormatException nfe) {
                            //Si el ID introducido no es un número, se deshabilitan los campos
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            cifTextField.setEnabled(false);
                            nombreEmpresaTextField.setEnabled(false);
                            nombreResponsableTextField.setEnabled(false);
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

                //Acción para actualizar el cliente
                actualizarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se obtiene el valor de cada campo del cliente
                            String cif = cifTextField.getText();
                            String nombreEmpresa = nombreEmpresaTextField.getText();
                            String nombreResponsable = nombreResponsableTextField.getText();
                            String pais = paisTextField.getText();
                            String provincia = provinciaTextField.getText();
                            String direccion = direccionTextField.getText();
                            String email = emailTextField.getText();
                            String telefono = telefonoTextField.getText();
                            String cp = cpTextField.getText();

                            //Se comprueba si algún campo está vacío
                            if(cif.isEmpty() || nombreEmpresa.isEmpty() || nombreResponsable.isEmpty() || 
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

                            //Se crea un nuevo cliente con los valores introducidos
                            Cliente clienteActualizado = new Cliente();
                            clienteActualizado.setID_Cliente(clienteId[0]);
                            clienteActualizado.setCIF(cif);
                            clienteActualizado.setNombre_Empresa(nombreEmpresa);
                            clienteActualizado.setNombre_Responsable(nombreResponsable);
                            clienteActualizado.setPais(pais);
                            clienteActualizado.setProvincia(provincia);
                            clienteActualizado.setDireccion(direccion);
                            clienteActualizado.setEmail(email);
                            clienteActualizado.setTelefono(telefono);
                            clienteActualizado.setCodigo_Postal(cp);

                            //Se actualiza el cliente en la base de datos
                            clienteController.save(clienteActualizado);

                            statusLabel.setText("Cliente actualizado correctamente.");
                        } catch (Exception ex) {
                            statusLabel.setText("Error al actualizar el cliente: " + ex.getMessage());
                        }
                    }
                });

                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuCliente(clienteController);
                });
            } catch (Exception e) {
                System.err.println("Error al actualizar el cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
    
    private void EliminarCliente() {
        JFrame frame = new JFrame("Eliminar Cliente");
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

        JLabel Title = new JLabel("=== ELIMINAR CLIENTE ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (clienteController != null) {
            try {
                //Se crea un JLabel y un JTextField para introducir el ID del cliente
                JLabel idLabel = new JLabel("ID del cliente a eliminar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);

                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                //Se crea un JLabel y un JTextArea para mostrar la información del cliente
                final JLabel infoLabel = new JLabel("Información del cliente:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);

                final JTextArea clienteInfo = new JTextArea();
                clienteInfo.setEditable(false);
                clienteInfo.setBackground(new Color(240, 240, 240));
                clienteInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                clienteInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(clienteInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);

                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);

                final JButton buscarButton = new JButton("Buscar Cliente");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);

                final JButton eliminarButton = new JButton("Eliminar Cliente");
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

                final Long[] clienteId = new Long[1];

                //Acción para buscar el cliente
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene el cliente con el ID introducido
                            Cliente cliente = clienteController.findById(id);

                            //Si el cliente existe, se muestra la información del cliente
                            if (cliente != null) {
                                clienteId[0] = cliente.getID_Cliente();
                                clienteInfo.setText(cliente.toString());
                                eliminarButton.setEnabled(true);
                                statusLabel.setText("Cliente encontrado. Pulse 'Eliminar Cliente' para confirmar.");
                            } else {
                                // Si el cliente no existe, se limpia el formulario y se deshabilita el botón de eliminar
                                clienteInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró un cliente con el ID: " + id);
                            }
                        } catch (NumberFormatException nfe) {
                            // Si el ID introducido no es un número, se deshabilita el botón de eliminar
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            clienteInfo.setText("");
                            eliminarButton.setEnabled(false);
                        }
                    }
                });

                //Acción para eliminar el cliente
                eliminarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //Se muestra un mensaje de confirmación para eliminar el cliente
                            int confirmacion = JOptionPane.showConfirmDialog(frame,
                                    "¿Está seguro de que desea eliminar este cliente?",
                                    "Confirmar eliminación",
                                    JOptionPane.YES_NO_OPTION);

                            //Si el usuario confirma la eliminación, se elimina el cliente
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                clienteController.delete(clienteId[0]);
                                clienteInfo.setText("");
                                idTextField.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("Cliente eliminado correctamente.");
                            }
                        } catch (Exception ex) {
                            //Si ocurre un error, se muestra un mensaje de error
                            statusLabel.setText("Error al eliminar el cliente: " + ex.getMessage());
                        }
                    }
                });

                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuCliente(clienteController);
                });
            } catch (Exception e) {
                System.err.println("Error al eliminar el cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    } 
} 