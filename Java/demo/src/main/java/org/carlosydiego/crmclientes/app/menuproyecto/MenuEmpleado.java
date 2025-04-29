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
        setLayout(null);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menu Empleado");

        JLabel Title = new JLabel("\n=== GESTIÓN DE EMPLEADOS ===");
        Title.setBounds(300, 10, 300, 50);
        add(Title);

        JButton ListarEmpleadosBtn = new JButton("Ver todos los empleados");
        ListarEmpleadosBtn.setBounds(100, 100, 200, 50);
        add(ListarEmpleadosBtn);
        ListarEmpleadosBtn.addActionListener(e -> {
            dispose();
            ListarEmpleados();
        });
        
        JButton BuscarEmpleadoBtn = new JButton("Buscar empleado por ID");
        BuscarEmpleadoBtn.setBounds(100, 150, 200, 50);
        add(BuscarEmpleadoBtn);
        BuscarEmpleadoBtn.addActionListener(e -> {
            dispose();
            BuscarEmpleado();
        });
        
        JButton AñadirEmpleadoBtn = new JButton("Añadir nuevo empleado");
        AñadirEmpleadoBtn.setBounds(100, 200, 200, 50);
        add(AñadirEmpleadoBtn);
        AñadirEmpleadoBtn.addActionListener(e -> {
            dispose();
            AñadirEmpleado();
        });

        JButton ActualizarEmpleadoBtn = new JButton("Actualizar empleado");
        ActualizarEmpleadoBtn.setBounds(100, 250, 200, 50);
        add(ActualizarEmpleadoBtn);
        ActualizarEmpleadoBtn.addActionListener(e -> {
            dispose();
            ActualizarEmpleado();
        });
        
        JButton EliminarEmpleadoBtn = new JButton("Eliminar empleado");
        EliminarEmpleadoBtn.setBounds(100, 300, 200, 50);
        add(EliminarEmpleadoBtn);
        EliminarEmpleadoBtn.addActionListener(e -> {
            dispose();
            EliminarEmpleado();
        });

        JButton VolverBtn = new JButton("Volver al menu principal");
        VolverBtn.setBounds(100, 350, 200, 50);
        add(VolverBtn);
        VolverBtn.addActionListener(e -> {
            dispose();
            new MenuProyecto();
        });

        setVisible(true);
    }

    
 
    private void ListarEmpleados()
    {
      JFrame frame = new JFrame("Listar Empleados");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
 
      JLabel Title = new JLabel("\n=== LISTA DE EMPLEADOS ===");
      Title.setBounds(300, 10, 200, 50);
      frame.add(Title);
 
      if(empleadoController!=null)
      {
          try
          {
              List<Empleado> empleados = empleadoController.findAll();
              if(empleados!=null && !empleados.isEmpty())
              {
                  JPanel panelEmpleados = new JPanel();
                  panelEmpleados.setLayout(null);
                  
                  JScrollPane scrollPane = new JScrollPane(panelEmpleados);
                  scrollPane.setBounds(50, 70, 700, 400);
                  frame.add(scrollPane);
                  
                  panelEmpleados.setPreferredSize(new Dimension(680, Math.max(380, empleados.size() * 150)));
                  
                  for(int i = 0; i < empleados.size(); i++)
                  {
                      JTextArea empleadoTextArea = new JTextArea(empleados.get(i).toString());
                      empleadoTextArea.setBounds(50, 10 + i * 150, 600, 150);
                      empleadoTextArea.setEditable(false);
                      empleadoTextArea.setBackground(new Color(240, 240, 240));
                      empleadoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                      panelEmpleados.add(empleadoTextArea);
                  }
              }
              else
              {
                  JLabel noEmpleadosLabel = new JLabel("No hay empleados registrados en el sistema");
                  noEmpleadosLabel.setBounds(300, 200, 300, 30);
                  frame.add(noEmpleadosLabel);
              }
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener los empleados: " + e.getMessage());
          } 
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
 
      JButton volverButton = new JButton("Volver");
      volverButton.setBounds(300, 500, 200, 30);
      frame.add(volverButton);
      volverButton.addActionListener(e -> {
          frame.dispose();
          new MenuEmpleado(empleadoController);
      });
    }
 
    private void BuscarEmpleado()
    {
      JFrame frame = new JFrame("Buscar Empleado");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
 
      JLabel Title = new JLabel("\n=== BUSCAR EMPLEADO ===");
      Title.setBounds(300, 10, 200, 50);
      frame.add(Title);
      if(empleadoController!=null)
      {
          try
          {

            
            JLabel idLabel = new JLabel("Introduce el ID del empleado:");
            idLabel.setBounds(100, 100, 200, 30);
            frame.add(idLabel);
 
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 100, 200, 30);
            frame.add(idTextField);
            
            JButton buscarButton = new JButton("Buscar");
            buscarButton.setBounds(550, 100, 150, 30);
            frame.add(buscarButton);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 150, 600, 30);
            frame.add(statusLabel);
            
            final JTextArea empleadoTextArea = new JTextArea();
            empleadoTextArea.setBounds(100, 190, 600, 180);
            empleadoTextArea.setEditable(false);
            empleadoTextArea.setBackground(new Color(240, 240, 240));
            empleadoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            empleadoTextArea.setVisible(false);
            frame.add(empleadoTextArea);
            
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = idTextField.getText();
                    try
                    {
                        long id = Long.parseLong(input);
                        statusLabel.setText("Buscando empleado...");
                        
                        Empleado empleado = empleadoController.findById(id);
                        if(empleado!=null)
                        {
                            statusLabel.setText("Empleado encontrado:");
                            empleadoTextArea.setText(empleado.toString());
                            empleadoTextArea.setVisible(true);
                            frame.repaint();
                        }
                        else
                        {
                            statusLabel.setText("Empleado no encontrado");
                            empleadoTextArea.setVisible(false);
                            frame.repaint();
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        empleadoTextArea.setVisible(false);
                        frame.repaint();
                    }
                }
            });
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(300, 400, 200, 30);
            frame.add(volverButton);
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuEmpleado(empleadoController);
            });
          }
          catch(Exception e)
          {
              System.err.println("Error al buscar el empleado: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
 
    private void AñadirEmpleado()
    {
      JFrame frame = new JFrame("Añadir Empleado");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLayout(null);
 
      JLabel Title = new JLabel("\n=== AÑADIR NUEVO EMPLEADO ===");
      Title.setBounds(300, 10, 300, 50);
      frame.add(Title);
 
      if(empleadoController!=null)
      {
          try
          {
             JLabel nombreLabel = new JLabel("Nombre:");
             nombreLabel.setBounds(100, 70, 200, 30);
             frame.add(nombreLabel);
             
             JTextField nombreTextField = new JTextField(10);
             nombreTextField.setBounds(350, 70, 200, 30);
             frame.add(nombreTextField);
             
             JLabel apellidoLabel = new JLabel("Apellido:");
             apellidoLabel.setBounds(100, 110, 200, 30);
             frame.add(apellidoLabel);
             
             JTextField apellidoTextField = new JTextField(10);
             apellidoTextField.setBounds(350, 110, 200, 30);
             frame.add(apellidoTextField);
             
             JLabel nifLabel = new JLabel("NIF (formato: 8 números + 1 letra):");
             nifLabel.setBounds(100, 150, 250, 30);
             frame.add(nifLabel);
             
             JTextField nifTextField = new JTextField(10);
             nifTextField.setBounds(350, 150, 200, 30);
             frame.add(nifTextField);
             
             JLabel direccionLabel = new JLabel("Dirección:");
             direccionLabel.setBounds(100, 190, 200, 30);
             frame.add(direccionLabel);
             
             JTextField direccionTextField = new JTextField(10);
             direccionTextField.setBounds(350, 190, 200, 30);
             frame.add(direccionTextField);
             
             JLabel cpLabel = new JLabel("Código Postal:");
             cpLabel.setBounds(100, 230, 200, 30);
             frame.add(cpLabel);
             
             JTextField cpTextField = new JTextField(10);
             cpTextField.setBounds(350, 230, 200, 30);
             frame.add(cpTextField);
             
             JLabel provinciaLabel = new JLabel("Provincia:");
             provinciaLabel.setBounds(100, 270, 200, 30);
             frame.add(provinciaLabel);
             
             JTextField provinciaTextField = new JTextField(10);
             provinciaTextField.setBounds(350, 270, 200, 30);
             frame.add(provinciaTextField);
             
             JLabel paisLabel = new JLabel("País:");
             paisLabel.setBounds(100, 310, 200, 30);
             frame.add(paisLabel);
             
             JTextField paisTextField = new JTextField(10);
             paisTextField.setBounds(350, 310, 200, 30);
             frame.add(paisTextField);
             
             JLabel telefonoLabel = new JLabel("Teléfono:");
             telefonoLabel.setBounds(100, 350, 200, 30);
             frame.add(telefonoLabel);
             
             JTextField telefonoTextField = new JTextField(10);
             telefonoTextField.setBounds(350, 350, 200, 30);
             frame.add(telefonoTextField);
             
             JLabel emailLabel = new JLabel("Email:");
             emailLabel.setBounds(100, 390, 200, 30);
             frame.add(emailLabel);
             
             JTextField emailTextField = new JTextField(10);
             emailTextField.setBounds(350, 390, 200, 30);
             frame.add(emailTextField);
             
             JLabel statusLabel = new JLabel("");
             statusLabel.setBounds(100, 470, 400, 30);
             frame.add(statusLabel);
             
             JButton guardarButton = new JButton("Guardar Empleado");
             guardarButton.setBounds(100, 430, 200, 30);
             frame.add(guardarButton);
             guardarButton.addActionListener(new ActionListener() 
             {
                 @Override
                 public void actionPerformed(ActionEvent e) 
                 {
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
                     } 
                     catch (Exception ex) 
                     {
                         statusLabel.setText("Error al guardar el empleado: " + ex.getMessage());
                     }
                 }
             });
             
             JButton volverButton = new JButton("Volver");
             volverButton.setBounds(350, 430, 200, 30);
             frame.add(volverButton);
             volverButton.addActionListener(e -> {
                 frame.dispose();
                 new MenuEmpleado(empleadoController);
             });
          }
          catch(Exception e)
          {
              System.err.println("Error al añadir el empleado: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
 
    private void ActualizarEmpleado()
    {
      JFrame frame = new JFrame("Actualizar Empleado");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLayout(null);
 
      JLabel Title = new JLabel("\n=== ACTUALIZAR EMPLEADO ===");
      Title.setBounds(300, 10, 300, 50);
      frame.add(Title);
 
      if(empleadoController!=null)
      {
          try
          {
              
              JLabel idLabel = new JLabel("ID del empleado a actualizar:");
              idLabel.setBounds(100, 70, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 70, 200, 30);
              frame.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Empleado");
              buscarButton.setBounds(520, 70, 150, 30);
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
              
              JLabel apellidoLabel = new JLabel("Apellido:");
              apellidoLabel.setBounds(100, 190, 200, 30);
              frame.add(apellidoLabel);
              
              final JTextField apellidoTextField = new JTextField(10);
              apellidoTextField.setBounds(350, 190, 200, 30);
              apellidoTextField.setEnabled(false);
              frame.add(apellidoTextField);
              
              JLabel nifLabel = new JLabel("NIF:");
              nifLabel.setBounds(100, 230, 200, 30);
              frame.add(nifLabel);
              
              final JTextField nifTextField = new JTextField(10);
              nifTextField.setBounds(350, 230, 200, 30);
              nifTextField.setEnabled(false);
              frame.add(nifTextField);
              
              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setBounds(100, 270, 200, 30);
              frame.add(direccionLabel);
              
              final JTextField direccionTextField = new JTextField(10);
              direccionTextField.setBounds(350, 270, 200, 30);
              direccionTextField.setEnabled(false);
              frame.add(direccionTextField);
              
              JLabel cpLabel = new JLabel("Código Postal:");
              cpLabel.setBounds(100, 310, 200, 30);
              frame.add(cpLabel);
              
              final JTextField cpTextField = new JTextField(10);
              cpTextField.setBounds(350, 310, 200, 30);
              cpTextField.setEnabled(false);
              frame.add(cpTextField);
              
              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setBounds(100, 350, 200, 30);
              frame.add(provinciaLabel);
              
              final JTextField provinciaTextField = new JTextField(10);
              provinciaTextField.setBounds(350, 350, 200, 30);
              provinciaTextField.setEnabled(false);
              frame.add(provinciaTextField);
              
              JLabel paisLabel = new JLabel("País:");
              paisLabel.setBounds(100, 390, 200, 30);
              frame.add(paisLabel);
              
              final JTextField paisTextField = new JTextField(10);
              paisTextField.setBounds(350, 390, 200, 30);
              paisTextField.setEnabled(false);
              frame.add(paisTextField);
              
              JLabel telefonoLabel = new JLabel("Teléfono:");
              telefonoLabel.setBounds(100, 430, 200, 30);
              frame.add(telefonoLabel);
              
              final JTextField telefonoTextField = new JTextField(10);
              telefonoTextField.setBounds(350, 430, 200, 30);
              telefonoTextField.setEnabled(false);
              frame.add(telefonoTextField);
              
              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setBounds(100, 470, 200, 30);
              frame.add(emailLabel);
              
              final JTextField emailTextField = new JTextField(10);
              emailTextField.setBounds(350, 470, 200, 30);
              emailTextField.setEnabled(false);
              frame.add(emailTextField);
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setBounds(100, 510, 200, 30);
              guardarButton.setEnabled(false);
              frame.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 510, 200, 30);
              frame.add(volverButton);
              
              
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
 
    private void EliminarEmpleado()
    {
      JFrame frame = new JFrame("Eliminar Empleado");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLayout(null);
 
      JLabel Title = new JLabel("\n=== ELIMINAR EMPLEADO ===");
      Title.setBounds(300, 10, 300, 50);
      frame.add(Title);
 
      if(empleadoController!=null)
      {
          try
          {
              
              JLabel idLabel = new JLabel("ID del empleado a eliminar:");
              idLabel.setBounds(100, 100, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 100, 200, 30);
              frame.add(idTextField);
              
              
              final JLabel infoLabel = new JLabel("Información del empleado:");
              infoLabel.setBounds(100, 150, 500, 30);
              frame.add(infoLabel);
              
              final JTextArea empleadoInfo = new JTextArea();
              empleadoInfo.setBounds(100, 190, 600, 100);
              empleadoInfo.setEditable(false);
              empleadoInfo.setBackground(new Color(240, 240, 240));
              empleadoInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              frame.add(empleadoInfo);
              
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 350, 600, 30);
              frame.add(statusLabel);
              
          
              final JButton buscarButton = new JButton("Buscar Empleado");
              buscarButton.setBounds(550, 100, 150, 30);
              frame.add(buscarButton);
              
              final JButton eliminarButton = new JButton("Eliminar Empleado");
              eliminarButton.setBounds(200, 400, 200, 30);
              eliminarButton.setEnabled(false);
              frame.add(eliminarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(420, 400, 200, 30);
              frame.add(volverButton);
              
              
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