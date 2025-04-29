package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuCliente extends JFrame {
    private ClienteController clienteController;

    public MenuCliente(ClienteController clienteController) {
        this.clienteController = clienteController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(null);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menu Cliente");

        JLabel Title = new JLabel("\n=== GESTIÓN DE CLIENTES ===");
        Title.setBounds(300, 10, 200, 50);
        add(Title);

        JButton TodoCliente = new JButton("Ver todos los clientes");
        TodoCliente.setBounds(100, 100, 200, 50);
        add(TodoCliente);
        TodoCliente.addActionListener(e -> {
            dispose();
            ListarClientes();
        });
        
        JButton BuscarCliente = new JButton("Buscar cliente por ID");
        BuscarCliente.setBounds(100, 150, 200, 50);
        add(BuscarCliente);
        BuscarCliente.addActionListener(e -> {
            dispose();
            BuscarCliente();
        });
        
        JButton AñadirCliente = new JButton("Añadir nuevo cliente");
        AñadirCliente.setBounds(100, 200, 200, 50);
        add(AñadirCliente);
        AñadirCliente.addActionListener(e -> {
            dispose();
            AñadirCliente();
        });

        JButton ModificarCliente = new JButton("Actualizar cliente");
        ModificarCliente.setBounds(100, 250, 200, 50);
        add(ModificarCliente);
        ModificarCliente.addActionListener(e -> {
            dispose();
            ActualizarCliente();
        });
        
        JButton EliminarCliente = new JButton("Eliminar cliente");
        EliminarCliente.setBounds(100, 300, 200, 50);
        add(EliminarCliente);
        EliminarCliente.addActionListener(e -> {
            dispose();
            EliminarCliente();
        });

        JButton VolverMenu = new JButton("Volver al menu principal");
        VolverMenu.setBounds(100, 350, 200, 50);
        add(VolverMenu);
        VolverMenu.addActionListener(e -> {
            dispose();
            new MenuProyecto();
        });

        setVisible(true);
    }

    private void ListarClientes()
    {
      JFrame frame = new JFrame("Listar Clientes");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
  
      JLabel Title = new JLabel("\n=== LISTA DE CLIENTES ===");
      Title.setBounds(300, 10, 200, 50);
      frame.add(Title);
  
      if(clienteController!=null)
      {
          try
          {
              List<Cliente> clientes = clienteController.findAll();
              if(clientes!=null)
              {
                  JPanel panelClientes = new JPanel();
                  panelClientes.setLayout(null);
                  
                  JScrollPane scrollPane = new JScrollPane(panelClientes);
                  scrollPane.setBounds(50, 70, 700, 400);
                  frame.add(scrollPane);
                  
                  panelClientes.setPreferredSize(new Dimension(680, Math.max(380, clientes.size() * 150)));
                  
                  for(int i = 0; i < clientes.size(); i++)
                  {
                      JTextArea clienteTextArea = new JTextArea(clientes.get(i).toString());
                      clienteTextArea.setBounds(50, 10 + i * 150, 600, 150);
                      clienteTextArea.setEditable(false);
                      clienteTextArea.setBackground(new Color(240, 240, 240));
                      clienteTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                      panelClientes.add(clienteTextArea);
                  }
              }
              else
              {
                  JLabel noClientesLabel = new JLabel("No hay clientes registrados en el sistema");
                  noClientesLabel.setBounds(300, 200, 300, 30);
                  frame.add(noClientesLabel);
              }
          }
          catch(Exception e)
          {
              System.err.println("Error al obtener los clientes: " + e.getMessage());
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
          new MenuCliente(clienteController);
      });
    }
  
    private void BuscarCliente()
    {
      JFrame frame = new JFrame("Buscar Cliente");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
  
      JLabel Title = new JLabel("\n=== BUSCAR CLIENTE ===");
      Title.setBounds(300, 10, 200, 50);
      frame.add(Title);
      if(clienteController!=null)
      {
          try
          {

            
            JLabel idLabel = new JLabel("Introduce el ID del cliente:");
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
            
            final JTextArea clienteTextArea = new JTextArea();
            clienteTextArea.setBounds(100, 190, 600, 180);
            clienteTextArea.setEditable(false);
            clienteTextArea.setBackground(new Color(240, 240, 240));
            clienteTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            clienteTextArea.setVisible(false);
            frame.add(clienteTextArea);
            
            // Crear y añadir el botón Volver antes de configurar los ActionListeners
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(300, 400, 200, 30);
            frame.add(volverButton);
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuCliente(clienteController);
            });
            
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = idTextField.getText();
                    try
                    {
                        long id = Long.parseLong(input);
                        statusLabel.setText("Buscando cliente...");
                        
                        Cliente cliente = clienteController.findById(id);
                        if(cliente!=null)
                        {
                            statusLabel.setText("Cliente encontrado:");
                            clienteTextArea.setText(cliente.toString());
                            clienteTextArea.setVisible(true);
                            // Asegurarse de que el botón Volver sigue visible
                            volverButton.setVisible(true);
                            frame.repaint();
                        }
                        else
                        {
                            statusLabel.setText("Cliente no encontrado");
                            clienteTextArea.setVisible(false);
                            // Asegurarse de que el botón Volver sigue visible
                            volverButton.setVisible(true);
                            frame.repaint();
                        }
                    }
                    catch(NumberFormatException nfe)
                    {
                        statusLabel.setText("ID inválido. Introduce un número válido.");
                        clienteTextArea.setVisible(false);
                        // Asegurarse de que el botón Volver sigue visible
                        volverButton.setVisible(true);
                        frame.repaint();
                    }
                }
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
  
    private void AñadirCliente()
    {
      JFrame frame = new JFrame("Añadir Cliente");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== AÑADIR NUEVO CLIENTE ===");
      Title.setBounds(300, 10, 300, 50);
      frame.add(Title);
  
      if(clienteController!=null)
      {
          try
          {
          
              JLabel cifLabel = new JLabel("CIF (formato: 1 letra + 8 números):");
              cifLabel.setBounds(100, 70, 250, 30);
              frame.add(cifLabel);
              
              JTextField cifTextField = new JTextField(10);
              cifTextField.setBounds(350, 70, 200, 30);
              frame.add(cifTextField);
              
          
              JLabel nombreEmpresaLabel = new JLabel("Nombre de la Empresa:");
              nombreEmpresaLabel.setBounds(100, 110, 200, 30);
              frame.add(nombreEmpresaLabel);
              
              JTextField nombreEmpresaTextField = new JTextField(10);
              nombreEmpresaTextField.setBounds(350, 110, 200, 30);
              frame.add(nombreEmpresaTextField);
              
          
              JLabel nombreResponsableLabel = new JLabel("Nombre del Responsable:");
              nombreResponsableLabel.setBounds(100, 150, 200, 30);
              frame.add(nombreResponsableLabel);
              
              JTextField nombreResponsableTextField = new JTextField(10);
              nombreResponsableTextField.setBounds(350, 150, 200, 30);
              frame.add(nombreResponsableTextField);
              
      
              JLabel paisLabel = new JLabel("País:");
              paisLabel.setBounds(100, 190, 200, 30);
              frame.add(paisLabel);
              
              JTextField paisTextField = new JTextField(10);
              paisTextField.setBounds(350, 190, 200, 30);
              frame.add(paisTextField);
              
              
              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setBounds(100, 230, 200, 30);
              frame.add(provinciaLabel);
              
              JTextField provinciaTextField = new JTextField(10);
              provinciaTextField.setBounds(350, 230, 200, 30);
              frame.add(provinciaTextField);
          
              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setBounds(100, 270, 200, 30);
              frame.add(direccionLabel);
              
              JTextField direccionTextField = new JTextField(10);
              direccionTextField.setBounds(350, 270, 200, 30);
              frame.add(direccionTextField);
              
              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setBounds(100, 310, 200, 30);
              frame.add(emailLabel);
              
              JTextField emailTextField = new JTextField(10);
              emailTextField.setBounds(350, 310, 200, 30);
              frame.add(emailTextField);
              
              JLabel telefonoLabel = new JLabel("Teléfono:");
              telefonoLabel.setBounds(100, 350, 200, 30);
              frame.add(telefonoLabel);
              
              JTextField telefonoTextField = new JTextField(10);
              telefonoTextField.setBounds(350, 350, 200, 30);
              frame.add(telefonoTextField);
              
      
              JLabel cpLabel = new JLabel("Código Postal:");
              cpLabel.setBounds(100, 390, 200, 30);
              frame.add(cpLabel);
              
              JTextField cpTextField = new JTextField(10);
              cpTextField.setBounds(350, 390, 200, 30);
              frame.add(cpTextField);
              
      
              JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 470, 400, 30);
              frame.add(statusLabel);
          
              JButton guardarButton = new JButton("Guardar Cliente");
              guardarButton.setBounds(100, 430, 200, 30);
              frame.add(guardarButton);
              guardarButton.addActionListener(new ActionListener() 
              {
                  @Override
     public void actionPerformed(ActionEvent e)
     {
                      try {
                      
                          String cif = cifTextField.getText();
                          String nombreEmpresa = nombreEmpresaTextField.getText();
                          String nombreResponsable = nombreResponsableTextField.getText();
                          String pais = paisTextField.getText();
                          String provincia = provinciaTextField.getText();
                          String direccion = direccionTextField.getText();
                          String email = emailTextField.getText();
                          String telefono = telefonoTextField.getText();
                          String codigoPostal = cpTextField.getText();
                          
                          // Verificar que ningún campo esté vacío
                          if (cif.isEmpty() || nombreEmpresa.isEmpty() || nombreResponsable.isEmpty() || 
                              pais.isEmpty() || provincia.isEmpty() || direccion.isEmpty() || 
                              email.isEmpty() || telefono.isEmpty() || codigoPostal.isEmpty()) {
                              statusLabel.setText("Error: Todos los campos son obligatorios");
                              return;
                          }
                          
                          // Validar formato de CIF
                          if (!cif.matches("[A-Z]\\d{8}")) {
                              statusLabel.setText("Error: El CIF debe tener 1 letra seguida de 8 números");
                              return;
                          }
                          
                          // Validar formato de email
                          if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                              statusLabel.setText("Error: El email debe tener un formato válido");
                              return;
                          }
                          
                          // Validar formato de teléfono
                          if (!telefono.matches("\\d{9}")) {
                              statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                              return;
                          }
                          
                          // Validar formato de código postal
                          if (!codigoPostal.matches("\\d{5}")) {
                              statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                              return;
                          }
                          
                      
                          Cliente nuevoCliente = new Cliente(cif, nombreEmpresa, nombreResponsable, 
                              pais, provincia, direccion, email, telefono, codigoPostal);
                          
                          clienteController.save(nuevoCliente);
                          
                          statusLabel.setText("Cliente añadido correctamente.");
                          
                      
                          cifTextField.setText("");
                          nombreEmpresaTextField.setText("");
                          nombreResponsableTextField.setText("");
                          paisTextField.setText("");
                          provinciaTextField.setText("");
                          direccionTextField.setText("");
                          emailTextField.setText("");
                          telefonoTextField.setText("");
                          cpTextField.setText("");
                      } 
                      catch (Exception ex) 
                      {
                          statusLabel.setText("Error al guardar el cliente: " + ex.getMessage());
                      }
                  }
              });
              
          
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 430, 200, 30);
              frame.add(volverButton);
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuCliente(clienteController);
              });
          }
          catch(Exception e)
          {
              System.err.println("Error al añadir el cliente: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
    
    private void ActualizarCliente()
    {
      JFrame frame = new JFrame("Actualizar Cliente");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ACTUALIZAR CLIENTE ===");
      Title.setBounds(300, 10, 300, 50);
      frame.add(Title);
  
      if(clienteController!=null)
      {
          try
          {
              
              JLabel idLabel = new JLabel("ID del cliente a actualizar:");
              idLabel.setBounds(100, 70, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 70, 200, 30);
              frame.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Cliente");
              buscarButton.setBounds(520, 70, 150, 30);
              frame.add(buscarButton);
          
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 110, 600, 30);
              frame.add(statusLabel);
              
              
              JLabel cifLabel = new JLabel("CIF:");
              cifLabel.setBounds(100, 150, 200, 30);
              frame.add(cifLabel);
              
              final JTextField cifTextField = new JTextField(10);
              cifTextField.setBounds(350, 150, 200, 30);
              cifTextField.setEnabled(false);
              frame.add(cifTextField);
              
              JLabel nombreEmpresaLabel = new JLabel("Nombre de la Empresa:");
              nombreEmpresaLabel.setBounds(100, 190, 200, 30);
              frame.add(nombreEmpresaLabel);
              
              final JTextField nombreEmpresaTextField = new JTextField(10);
              nombreEmpresaTextField.setBounds(350, 190, 200, 30);
              nombreEmpresaTextField.setEnabled(false);
              frame.add(nombreEmpresaTextField);
              
              JLabel nombreResponsableLabel = new JLabel("Nombre del Responsable:");
              nombreResponsableLabel.setBounds(100, 230, 200, 30);
              frame.add(nombreResponsableLabel);
              
              final JTextField nombreResponsableTextField = new JTextField(10);
              nombreResponsableTextField.setBounds(350, 230, 200, 30);
              nombreResponsableTextField.setEnabled(false);
              frame.add(nombreResponsableTextField);
              
              JLabel paisLabel = new JLabel("País:");
              paisLabel.setBounds(100, 270, 200, 30);
              frame.add(paisLabel);
              
              final JTextField paisTextField = new JTextField(10);
              paisTextField.setBounds(350, 270, 200, 30);
              paisTextField.setEnabled(false);
              frame.add(paisTextField);
              
              JLabel provinciaLabel = new JLabel("Provincia:");
              provinciaLabel.setBounds(100, 310, 200, 30);
              frame.add(provinciaLabel);
              
              final JTextField provinciaTextField = new JTextField(10);
              provinciaTextField.setBounds(350, 310, 200, 30);
              provinciaTextField.setEnabled(false);
              frame.add(provinciaTextField);
              
              JLabel direccionLabel = new JLabel("Dirección:");
              direccionLabel.setBounds(100, 350, 200, 30);
              frame.add(direccionLabel);
              
              final JTextField direccionTextField = new JTextField(10);
              direccionTextField.setBounds(350, 350, 200, 30);
              direccionTextField.setEnabled(false);
              frame.add(direccionTextField);
              
              JLabel emailLabel = new JLabel("Email:");
              emailLabel.setBounds(100, 390, 200, 30);
              frame.add(emailLabel);
              
              final JTextField emailTextField = new JTextField(10);
              emailTextField.setBounds(350, 390, 200, 30);
              emailTextField.setEnabled(false);
              frame.add(emailTextField);
              
              JLabel telefonoLabel = new JLabel("Teléfono:");
              telefonoLabel.setBounds(100, 430, 200, 30);
              frame.add(telefonoLabel);
              
              final JTextField telefonoTextField = new JTextField(10);
              telefonoTextField.setBounds(350, 430, 200, 30);
              telefonoTextField.setEnabled(false);
              frame.add(telefonoTextField);
              
              JLabel cpLabel = new JLabel("Código Postal:");
              cpLabel.setBounds(100, 470, 200, 30);
              frame.add(cpLabel);
              
              final JTextField cpTextField = new JTextField(10);
              cpTextField.setBounds(350, 470, 200, 30);
              cpTextField.setEnabled(false);
              frame.add(cpTextField);
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setBounds(100, 510, 200, 30);
              guardarButton.setEnabled(false);
              frame.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(350, 510, 200, 30);
              frame.add(volverButton);
              
              
              final Long[] clienteId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Cliente cliente = clienteController.findById(id);
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
                              
                      
                              cifTextField.setEnabled(true);
                              nombreEmpresaTextField.setEnabled(true);
                              nombreResponsableTextField.setEnabled(true);
                              paisTextField.setEnabled(true);
                              provinciaTextField.setEnabled(true);
                              direccionTextField.setEnabled(true);
                              emailTextField.setEnabled(true);
                              telefonoTextField.setEnabled(true);
                              cpTextField.setEnabled(true);
                              guardarButton.setEnabled(true);
                              
                              statusLabel.setText("Cliente encontrado. Modifique los campos o manten los mismos valores (o deje en blanco).");
                          } 
                          else 
                          {
                              statusLabel.setText("No se encontró un cliente con el ID: " + id);
                              
                          
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
                          Cliente clienteExistente = clienteController.findById(clienteId[0]);
                      
                          // Obtener valores de los campos
                          String cif = cifTextField.getText();
                          String nombreEmpresa = nombreEmpresaTextField.getText();
                          String nombreResponsable = nombreResponsableTextField.getText();
                          String pais = paisTextField.getText();
                          String provincia = provinciaTextField.getText();
                          String direccion = direccionTextField.getText();
                          String email = emailTextField.getText();
                          String telefono = telefonoTextField.getText();
                          String codigoPostal = cpTextField.getText();
                          
                          // Usar valores existentes si los campos están vacíos
                          if (cif.isEmpty()) {
                              cif = clienteExistente.getCIF();
                          }
                          
                          if (nombreEmpresa.isEmpty()) {
                              nombreEmpresa = clienteExistente.getNombre_Empresa();
                          }
                          
                          if (nombreResponsable.isEmpty()) {
                              nombreResponsable = clienteExistente.getNombre_Responsable();
                          }
                          
                          if (pais.isEmpty()) {
                              pais = clienteExistente.getPais();
                          }
                          
                          if (provincia.isEmpty()) {
                              provincia = clienteExistente.getProvincia();
                          }
                          
                          if (direccion.isEmpty()) {
                              direccion = clienteExistente.getDireccion();
                          }
                          
                          if (email.isEmpty()) {
                              email = clienteExistente.getEmail();
                          }
                          
                          if (telefono.isEmpty()) {
                              telefono = clienteExistente.getTelefono();
                          }
                          
                          if (codigoPostal.isEmpty()) {
                              codigoPostal = clienteExistente.getCodigo_Postal();
                          }
                          
                          // Validar formatos
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
                          
                          if (!codigoPostal.matches("\\d{5}")) {
                              statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                              return;
                          }
                          
                          // Crear cliente actualizado
                          Cliente clienteActualizado = new Cliente(cif, nombreEmpresa, nombreResponsable, 
                              pais, provincia, direccion, email, telefono, codigoPostal);
                          clienteActualizado.setID_Cliente(clienteId[0]);
                          
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
          }
          catch(Exception e)
          {
              System.err.println("Error al actualizar el cliente: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
    }
    
    private void EliminarCliente()
    {
      JFrame frame = new JFrame("Eliminar Cliente");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLayout(null);
  
      JLabel Title = new JLabel("\n=== ELIMINAR CLIENTE ===");
      Title.setBounds(300, 10, 300, 50);
      frame.add(Title);
  
      if(clienteController!=null)
      {
          try
          {
              
              JLabel idLabel = new JLabel("ID del cliente a eliminar:");
              idLabel.setBounds(100, 100, 200, 30);
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 100, 200, 30);
              frame.add(idTextField);
              
              
              final JLabel infoLabel = new JLabel("Información del cliente:");
              infoLabel.setBounds(100, 150, 500, 30);
              frame.add(infoLabel);
              
              final JTextArea clienteInfo = new JTextArea();
              clienteInfo.setBounds(100, 190, 600, 100);
              clienteInfo.setEditable(false);
              clienteInfo.setBackground(new Color(240, 240, 240));
              clienteInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
              frame.add(clienteInfo);
              
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 350, 600, 30);
              frame.add(statusLabel);
              
          
              final JButton buscarButton = new JButton("Buscar Cliente");
              buscarButton.setBounds(550, 100, 150, 30);
              frame.add(buscarButton);
              
              final JButton eliminarButton = new JButton("Eliminar Cliente");
              eliminarButton.setBounds(200, 400, 200, 30);
              eliminarButton.setEnabled(false);
              frame.add(eliminarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(420, 400, 200, 30);
              frame.add(volverButton);
              
              
              final Long[] clienteId = new Long[1];
              
              buscarButton.addActionListener(new ActionListener() 
              {
                  @Override
                  public void actionPerformed(ActionEvent e) 
                  {
                      try 
                      {
                          String input = idTextField.getText();
                          long id = Long.parseLong(input);
                          
                          Cliente cliente = clienteController.findById(id);
                          if (cliente != null) 
                          {
                              
                              clienteId[0] = cliente.getID_Cliente();
                              clienteInfo.setText(cliente.toString());
                              eliminarButton.setEnabled(true);
                              statusLabel.setText("Cliente encontrado. Pulse 'Eliminar Cliente' para confirmar.");
                          } 
                          else 
                          {
                              clienteInfo.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("No se encontró un cliente con el ID: " + id);
                          }
                      } 
                      catch (NumberFormatException nfe) 
                      {
                          statusLabel.setText("ID inválido. Introduce un número válido.");
                          clienteInfo.setText("");
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
                              "¿Está seguro de que desea eliminar este cliente?", 
                              "Confirmar eliminación", 
                              JOptionPane.YES_NO_OPTION);
                          
                          if (confirmacion == JOptionPane.YES_OPTION) 
                          {
                              clienteController.delete(clienteId[0]);
                              
                          
                              clienteInfo.setText("");
                              idTextField.setText("");
                              eliminarButton.setEnabled(false);
                              statusLabel.setText("Cliente eliminado correctamente.");
                          }
                      } 
                      catch (Exception ex) 
                      {
                          statusLabel.setText("Error al eliminar el cliente: " + ex.getMessage());
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
              System.err.println("Error al eliminar el cliente: " + e.getMessage());
          }
      }
      else
      {
          System.out.println("Error: No hay conexion a la base de datos");
      }
     } 
} 