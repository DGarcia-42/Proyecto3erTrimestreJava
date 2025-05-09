package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuEmpleado extends JFrame {
    private EmpleadoController empleadoController;

    public MenuEmpleado(EmpleadoController empleadoController) {
        this.empleadoController = empleadoController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Empleado");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE EMPLEADOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton ListarEmpleadosBtn = new JButton("Ver todos los empleados");
        ListarEmpleadosBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ListarEmpleadosBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ListarEmpleadosBtn.setBackground(new Color(0, 123, 255));
        ListarEmpleadosBtn.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(ListarEmpleadosBtn, gbc);
        ListarEmpleadosBtn.addActionListener(e -> {
            dispose();
            ListarEmpleados();
        });

        JButton BuscarEmpleadoBtn = new JButton("Buscar empleado por ID");
        BuscarEmpleadoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarEmpleadoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarEmpleadoBtn.setBackground(new Color(0, 123, 255));
        BuscarEmpleadoBtn.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarEmpleadoBtn, gbc);
        BuscarEmpleadoBtn.addActionListener(e -> {
            dispose();
            BuscarEmpleado();
        });

        JButton AñadirEmpleadoBtn = new JButton("Añadir nuevo empleado");
        AñadirEmpleadoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirEmpleadoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirEmpleadoBtn.setBackground(new Color(76, 175, 80));
        AñadirEmpleadoBtn.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirEmpleadoBtn, gbc);
        AñadirEmpleadoBtn.addActionListener(e -> {
            dispose();
            AñadirEmpleado();
        });

        JButton ActualizarEmpleadoBtn = new JButton("Actualizar empleado");
        ActualizarEmpleadoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ActualizarEmpleadoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ActualizarEmpleadoBtn.setBackground(new Color(0, 123, 255));
        ActualizarEmpleadoBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ActualizarEmpleadoBtn, gbc);
        ActualizarEmpleadoBtn.addActionListener(e -> {
            dispose();
            ActualizarEmpleado();
        });

        JButton EliminarEmpleadoBtn = new JButton("Eliminar empleado");
        EliminarEmpleadoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarEmpleadoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarEmpleadoBtn.setBackground(new Color(0, 123, 255));
        EliminarEmpleadoBtn.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarEmpleadoBtn, gbc);
        EliminarEmpleadoBtn.addActionListener(e -> {
            dispose();
            EliminarEmpleado();
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

    private void ListarEmpleados() {
      JFrame frame = new JFrame("Listar Empleados");
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

      JLabel Title = new JLabel("=== LISTA DE EMPLEADOS ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46)); 
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
 
      if(empleadoController!=null) {
          try {
              List<Empleado> empleados = empleadoController.findAll();
              if(empleados!=null && !empleados.isEmpty()) {
                  JPanel panelEmpleados = new JPanel();
                  panelEmpleados.setLayout(null);
                  
                  JScrollPane scrollPane = new JScrollPane(panelEmpleados);
                  scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                  gbc.gridy = 1;
                  frame.add(scrollPane, gbc);
                  
                  panelEmpleados.setPreferredSize(new Dimension(textAreaWidth - 50, Math.max(textAreaHeight - 50, empleados.size() * 150)));
                  
                  for(int i = 0; i < empleados.size(); i++) {
                      JTextArea empleadoTextArea = new JTextArea(empleados.get(i).toString());
                      empleadoTextArea.setBounds(50, 10 + i * 150, textAreaWidth - 150, 130);
                      empleadoTextArea.setEditable(false);
                      empleadoTextArea.setBackground(new Color(240, 240, 240));
                      empleadoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                      empleadoTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                      panelEmpleados.add(empleadoTextArea);
                  }
              } else {
                  JLabel noEmpleadosLabel = new JLabel("No hay empleados registrados en el sistema");
                  noEmpleadosLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                  noEmpleadosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                  gbc.gridy = 1;
                  frame.add(noEmpleadosLabel, gbc);
              }
          } catch(Exception e) {
              System.err.println("Error al obtener los empleados: " + e.getMessage());
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
          new MenuEmpleado(empleadoController);
      });
    }
 
    private void BuscarEmpleado() {
      JFrame frame = new JFrame("Buscar Empleado");
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
 
      JLabel Title = new JLabel("=== BUSCAR EMPLEADO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46)); 
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);

      if(empleadoController!=null) {
          try {
            JLabel idLabel = new JLabel("Introduce el ID del empleado:");
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
            
            final JTextArea empleadoTextArea = new JTextArea();
            empleadoTextArea.setEditable(false);
            empleadoTextArea.setBackground(new Color(240, 240, 240));
            empleadoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            empleadoTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
            empleadoTextArea.setVisible(false);
            JScrollPane scrollPane = new JScrollPane(empleadoTextArea);
            scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
            gbc.gridy = 4;
            frame.add(scrollPane, gbc);
            
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = idTextField.getText();
                    try {
                        long id = Long.parseLong(input);
                        statusLabel.setText("Buscando empleado...");
                        
                        Empleado empleado = empleadoController.findById(id);
                        if(empleado!=null) {
                            statusLabel.setText("Empleado encontrado:");
                            empleadoTextArea.setText(empleado.toString());
                            empleadoTextArea.setVisible(true);
                            scrollPane.setVisible(true);
                            frame.revalidate();
                            frame.repaint();
                        } else {
                            statusLabel.setText("Empleado no encontrado");
                            empleadoTextArea.setVisible(false);
                            scrollPane.setVisible(false);
                            frame.revalidate();
                            frame.repaint();
                        }
                    } catch(NumberFormatException nfe) {
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        empleadoTextArea.setVisible(false);
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
                new MenuEmpleado(empleadoController);
            });
          } catch(Exception e) {
              System.err.println("Error al buscar el empleado: " + e.getMessage());
          }
      } else {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
 
    private void AñadirEmpleado() {
      JFrame frame = new JFrame("Añadir Empleado");
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
 
      JLabel Title = new JLabel("=== AÑADIR NUEVO EMPLEADO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46)); 
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
 
      if(empleadoController!=null) {
          try {
             JLabel nombreLabel = new JLabel("Nombre:");
             nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridy = 1;
             gbc.gridwidth = 1;
             frame.add(nombreLabel, gbc);
             
             JTextField nombreTextField = new JTextField(10);
             nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(nombreTextField, gbc);
             
             JLabel apellidoLabel = new JLabel("Apellido:");
             apellidoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 2;
             frame.add(apellidoLabel, gbc);
             
             JTextField apellidoTextField = new JTextField(10);
             apellidoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             apellidoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(apellidoTextField, gbc);
             
             JLabel nifLabel = new JLabel("NIF (formato: 8 números + 1 letra):");
             nifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 3;
             frame.add(nifLabel, gbc);
             
             JTextField nifTextField = new JTextField(10);
             nifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             nifTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(nifTextField, gbc);
             
             JLabel direccionLabel = new JLabel("Dirección:");
             direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 4;
             frame.add(direccionLabel, gbc);
             
             JTextField direccionTextField = new JTextField(10);
             direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             direccionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(direccionTextField, gbc);
             
             JLabel cpLabel = new JLabel("Código Postal:");
             cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 5;
             frame.add(cpLabel, gbc);
             
             JTextField cpTextField = new JTextField(10);
             cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             cpTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(cpTextField, gbc);
             
             JLabel provinciaLabel = new JLabel("Provincia:");
             provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 6;
             frame.add(provinciaLabel, gbc);
             
             JTextField provinciaTextField = new JTextField(10);
             provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             provinciaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(provinciaTextField, gbc);
             
             JLabel paisLabel = new JLabel("País:");
             paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 7;
             frame.add(paisLabel, gbc);
             
             JTextField paisTextField = new JTextField(10);
             paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             paisTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(paisTextField, gbc);
             
             JLabel telefonoLabel = new JLabel("Teléfono:");
             telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 8;
             frame.add(telefonoLabel, gbc);
             
             JTextField telefonoTextField = new JTextField(10);
             telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             telefonoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(telefonoTextField, gbc);
             
             JLabel emailLabel = new JLabel("Email:");
             emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 9;
             frame.add(emailLabel, gbc);
             
             JTextField emailTextField = new JTextField(10);
             emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             emailTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
             gbc.gridx = 1;
             frame.add(emailTextField, gbc);
             
             JLabel statusLabel = new JLabel("");
             statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
             gbc.gridx = 0;
             gbc.gridy = 10;
             gbc.gridwidth = 2;
             frame.add(statusLabel, gbc);
             
             JButton guardarButton = new JButton("Guardar Empleado");
             guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
             guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
             guardarButton.setBackground(new Color(76, 175, 80)); 
             guardarButton.setForeground(Color.WHITE);
             gbc.gridx = 0;
             gbc.gridy = 11;
             gbc.gridwidth = 1;
             frame.add(guardarButton, gbc);
             guardarButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     try {
                         String nombre = nombreTextField.getText();
                         String apellido = apellidoTextField.getText();
                         String nif = nifTextField.getText();
                         String direccion = direccionTextField.getText();
                         String codigoPostal = cpTextField.getText();
                         String provincia = provinciaTextField.getText();
                         String pais = paisTextField.getText();
                         String telefono = telefonoTextField.getText();
                         String email = emailTextField.getText();
                         
                         // Verificar que ningún campo esté vacío
                         if (nombre.isEmpty() || apellido.isEmpty() || nif.isEmpty() || 
                             direccion.isEmpty() || codigoPostal.isEmpty() || provincia.isEmpty() || 
                             pais.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                             statusLabel.setText("Error: Todos los campos son obligatorios");
                             return;
                         }
                         
                         // Validar formato de NIF
                         if (!nif.matches("\\d{8}[A-Za-z]")) {
                             statusLabel.setText("Error: El NIF debe tener 8 números seguidos de 1 letra");
                             return;
                         }
                         
                         // Validar formato de código postal
                         if (!codigoPostal.matches("\\d{5}")) {
                             statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                             return;
                         }
                         
                         // Validar formato de teléfono
                         if (!telefono.matches("\\d{9}")) {
                             statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                             return;
                         }
                         
                         // Validar formato de email
                         if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                             statusLabel.setText("Error: El email debe tener un formato válido");
                             return;
                         }
                         
                         Empleado nuevoEmpleado = new Empleado(null, nombre, apellido, nif, direccion, codigoPostal, provincia, pais, telefono, email);
                         
                         empleadoController.save(nuevoEmpleado);
                         
                         statusLabel.setText("Empleado añadido correctamente.");
                         
                         nombreTextField.setText("");
                         apellidoTextField.setText("");
                         nifTextField.setText("");
                         direccionTextField.setText("");
                         cpTextField.setText("");
                         provinciaTextField.setText("");
                         paisTextField.setText("");
                         telefonoTextField.setText("");
                         emailTextField.setText("");
                     } catch (Exception ex) {
                         statusLabel.setText("Error al guardar el empleado: " + ex.getMessage());
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
                 new MenuEmpleado(empleadoController);
             });
          } catch(Exception e) {
              System.err.println("Error al añadir el empleado: " + e.getMessage());
          }
      } else {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
 
    private void ActualizarEmpleado() {
      JFrame frame = new JFrame("Actualizar Empleado");
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
 
      JLabel Title = new JLabel("=== ACTUALIZAR EMPLEADO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46)); 
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
 
      if(empleadoController!=null) {
          try {
              JLabel idLabel = new JLabel("ID del empleado a actualizar:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              frame.add(idLabel, gbc);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(idTextField, gbc);
              
              final JButton buscarButton = new JButton("Buscar Empleado");
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
              
              
              JLabel nombreLabel = new JLabel("Nombre:");
              nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 4;
              gbc.gridwidth = 1;
              frame.add(nombreLabel, gbc);
              
              final JTextField nombreTextField = new JTextField(10);
              nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              nombreTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(nombreTextField, gbc);
              
              JLabel apellidoLabel = new JLabel("Apellido:");
              apellidoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 5;
              frame.add(apellidoLabel, gbc);
              
              final JTextField apellidoTextField = new JTextField(10);
              apellidoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              apellidoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              apellidoTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(apellidoTextField, gbc);
              
              JLabel nifLabel = new JLabel("NIF:");
              nifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 6;
              frame.add(nifLabel, gbc);
              
              final JTextField nifTextField = new JTextField(10);
              nifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              nifTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              nifTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(nifTextField, gbc);
              
              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 7;
              frame.add(direccionLabel, gbc);
              
              final JTextField direccionTextField = new JTextField(10);
              direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              direccionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              direccionTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(direccionTextField, gbc);
              
              JLabel cpLabel = new JLabel("Código Postal:");
              cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 8;
              frame.add(cpLabel, gbc);
              
              final JTextField cpTextField = new JTextField(10);
              cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              cpTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              cpTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(cpTextField, gbc);
              
              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 9;
              frame.add(provinciaLabel, gbc);
              
              final JTextField provinciaTextField = new JTextField(10);
              provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              provinciaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              provinciaTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(provinciaTextField, gbc);
              
              JLabel paisLabel = new JLabel("País:");
              paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 10;
              frame.add(paisLabel, gbc);
              
              final JTextField paisTextField = new JTextField(10);
              paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              paisTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              paisTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(paisTextField, gbc);
              
              JLabel telefonoLabel = new JLabel("Teléfono:");
              telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 11;
              frame.add(telefonoLabel, gbc);
              
              final JTextField telefonoTextField = new JTextField(10);
              telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              telefonoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              telefonoTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(telefonoTextField, gbc);
              
              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 12;
              frame.add(emailLabel, gbc);
              
              final JTextField emailTextField = new JTextField(10);
              emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              emailTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              emailTextField.setEnabled(false);
              gbc.gridx = 1;
              frame.add(emailTextField, gbc);
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              guardarButton.setBackground(new Color(76, 175, 80)); 
              guardarButton.setForeground(Color.WHITE);
              guardarButton.setEnabled(false);
              gbc.gridx = 0;
              gbc.gridy = 13;
              frame.add(guardarButton, gbc);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              volverButton.setBackground(new Color(0, 123, 255)); 
              volverButton.setForeground(Color.WHITE);
              gbc.gridx = 1;
              frame.add(volverButton, gbc);
              
              
              final Long[] empleadoId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Empleado empleado = empleadoController.findById(id);
                          if (empleado != null) {
                      
                              empleadoId[0] = empleado.getID_Empleado();
                              
                              nombreTextField.setText(empleado.getNombre());
                              apellidoTextField.setText(empleado.getApellido());
                              nifTextField.setText(empleado.getNIF());
                              direccionTextField.setText(empleado.getDireccion());
                              cpTextField.setText(empleado.getCodigo_Postal());
                              provinciaTextField.setText(empleado.getProvincia());
                              paisTextField.setText(empleado.getPais());
                              telefonoTextField.setText(empleado.getTelfono());
                              emailTextField.setText(empleado.getEmail());
                              
                      
                              nombreTextField.setEnabled(true);
                              apellidoTextField.setEnabled(true);
                              nifTextField.setEnabled(true);
                              direccionTextField.setEnabled(true);
                              cpTextField.setEnabled(true);
                              provinciaTextField.setEnabled(true);
                              paisTextField.setEnabled(true);
                              telefonoTextField.setEnabled(true);
                              emailTextField.setEnabled(true);
                              guardarButton.setEnabled(true);
                              
                              statusLabel.setText("Empleado encontrado. Modifique los campos o manten los mismos valores (o deje en blanco).");
                          } 
                          else 
                          {
                              statusLabel.setText("No se encontró un empleado con el ID: " + id);
                              
                      
                              nombreTextField.setText("");
                              apellidoTextField.setText("");
                              nifTextField.setText("");
                              direccionTextField.setText("");
                              cpTextField.setText("");
                              provinciaTextField.setText("");
                              paisTextField.setText("");
                              telefonoTextField.setText("");
                              emailTextField.setText("");
                              
                              nombreTextField.setEnabled(false);
                              apellidoTextField.setEnabled(false);
                              nifTextField.setEnabled(false);
                              direccionTextField.setEnabled(false);
                              cpTextField.setEnabled(false);
                              provinciaTextField.setEnabled(false);
                              paisTextField.setEnabled(false);
                              telefonoTextField.setEnabled(false);
                              emailTextField.setEnabled(false);
                              guardarButton.setEnabled(false);
                          }
                      } catch (NumberFormatException nfe) {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                      }
                  }
              });
              
              guardarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          Empleado empleadoExistente = empleadoController.findById(empleadoId[0]);
                      
                          // Obtener valores de los campos
                          String nombre = nombreTextField.getText();
                          String apellido = apellidoTextField.getText();
                          String nif = nifTextField.getText();
                          String direccion = direccionTextField.getText();
                          String codigoPostal = cpTextField.getText();
                          String provincia = provinciaTextField.getText();
                          String pais = paisTextField.getText();
                          String telefono = telefonoTextField.getText();
                          String email = emailTextField.getText();
                          
                          // Usar valores existentes si los campos están vacíos
                          if (nombre.isEmpty()) {
                              nombre = empleadoExistente.getNombre();
                          }
                          
                          if (apellido.isEmpty()) {
                              apellido = empleadoExistente.getApellido();
                          }
                          
                          if (nif.isEmpty()) {
                              nif = empleadoExistente.getNIF();
                          }
                          
                          if (direccion.isEmpty()) {
                              direccion = empleadoExistente.getDireccion();
                          }
                          
                          if (codigoPostal.isEmpty()) {
                              codigoPostal = empleadoExistente.getCodigo_Postal();
                          }
                          
                          if (provincia.isEmpty()) {
                              provincia = empleadoExistente.getProvincia();
                          }
                          
                          if (pais.isEmpty()) {
                              pais = empleadoExistente.getPais();
                          }
                          
                          if (telefono.isEmpty()) {
                              telefono = empleadoExistente.getTelfono();
                          }
                          
                          if (email.isEmpty()) {
                              email = empleadoExistente.getEmail();
                          }
                          
                          // Validar formatos
                          if (!nif.matches("\\d{8}[A-Za-z]")) {
                              statusLabel.setText("Error: El NIF debe tener 8 números seguidos de 1 letra");
                              return;
                          }
                          
                          if (!codigoPostal.matches("\\d{5}")) {
                              statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                              return;
                          }
                          
                          if (!telefono.matches("\\d{9}")) {
                              statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                              return;
                          }
                          
                          if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                              statusLabel.setText("Error: El email debe tener un formato válido");
                              return;
                          }
                          
                          Empleado empleadoActualizado = new Empleado(empleadoId[0], nombre, apellido, nif, direccion, codigoPostal, provincia, pais, telefono, email);
                          empleadoActualizado.setID_Empleado(empleadoId[0]);
                          
                          empleadoController.save(empleadoActualizado);
                          
                          statusLabel.setText("Empleado actualizado correctamente.");
                      } catch (Exception ex) {
                          statusLabel.setText("Error al actualizar el empleado: " + ex.getMessage());
                      }
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuEmpleado(empleadoController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al actualizar el empleado: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
 
    private void EliminarEmpleado() {
      JFrame frame = new JFrame("Eliminar Empleado");
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
 
      JLabel Title = new JLabel("=== ELIMINAR EMPLEADO ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46)); 
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      frame.add(Title, gbc);
 
      if(empleadoController!=null) {
          try {
              JLabel idLabel = new JLabel("ID del empleado a eliminar:");
              idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridy = 1;
              gbc.gridwidth = 1;
              frame.add(idLabel, gbc);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
              gbc.gridx = 1;
              frame.add(idTextField, gbc);
              
              final JLabel infoLabel = new JLabel("Información del empleado:");
              infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
              gbc.gridx = 0;
              gbc.gridy = 2;
              gbc.gridwidth = 2;
              frame.add(infoLabel, gbc);
              
              final JTextArea empleadoInfo = new JTextArea();
              empleadoInfo.setEditable(false);
              empleadoInfo.setBackground(new Color(240, 240, 240));
              empleadoInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              empleadoInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
              JScrollPane scrollPane = new JScrollPane(empleadoInfo);
              scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
              gbc.gridy = 3;
              frame.add(scrollPane, gbc);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
              gbc.gridy = 4;
              frame.add(statusLabel, gbc);
              
              final JButton buscarButton = new JButton("Buscar Empleado");
              buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
              buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
              buscarButton.setBackground(new Color(0, 123, 255)); 
              buscarButton.setForeground(Color.WHITE);
              gbc.gridx = 0;
              gbc.gridy = 5;
              gbc.gridwidth = 1;
              frame.add(buscarButton, gbc);
              
              final JButton eliminarButton = new JButton("Eliminar Empleado");
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
              
              final Long[] empleadoId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) 
                  {
                      try 
                      {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Empleado empleado = empleadoController.findById(id);
                          if (empleado != null) 
                          {
                              
                              empleadoId[0] = empleado.getID_Empleado();
                              empleadoInfo.setText(empleado.toString());
                              eliminarButton.setEnabled(true);
                              statusLabel.setText("Empleado encontrado. Pulse 'Eliminar Empleado' para confirmar.");
                          } 
                          else 
                          {
                              empleadoInfo.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("No se encontró un empleado con el ID: " + id);
                          }
                      } 
                      catch (NumberFormatException nfe) 
                      {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          empleadoInfo.setText("");
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
                              "¿Está seguro de que desea eliminar este empleado?", 
                              "Confirmar eliminación", 
                              JOptionPane.YES_NO_OPTION);
                          
                          if (confirmacion == JOptionPane.YES_OPTION) 
                          {
                              empleadoController.delete(empleadoId[0]);
                              
                         
                              empleadoInfo.setText("");
                              idTextField.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("Empleado eliminado correctamente.");
                          }
                      } 
                      catch (Exception ex) 
                      {
                          statusLabel.setText("Error al eliminar el empleado: " + ex.getMessage());
                      }
                  }
              });
              
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuEmpleado(empleadoController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al eliminar el empleado: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
} 