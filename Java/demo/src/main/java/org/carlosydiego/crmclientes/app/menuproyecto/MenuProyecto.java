package org.carlosydiego.crmclientes.app.menuproyecto;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.database.DatabaseConnection;
import org.carlosydiego.crmclientes.app.model.*;
import org.carlosydiego.crmclientes.app.util.FacturaFileManager;

public class MenuProyecto extends JFrame
{
    private JButton VolverMenu;
    private JButton Clientes;
    private JButton Empleados;
    private JButton Productos;
    private JButton Proveedores;
    private JButton Facturas;
    private JButton Categorias;
    private JButton Provees;
    private JButton Salir;
    private JButton TodoCliente;
    private JButton BuscarCliente;
    private JButton ModificarCliente;
    private JButton AñadirCliente;
    private JButton EliminarCliente;
    private JButton ActualizarCliente;
    // Botones para el menú de empleado
    private JButton TodoEmpleado;
    private JButton BuscarEmpleado;
    private JButton ModificarEmpleado;
    private JButton AñadirEmpleado;
    private JButton EliminarEmpleado;
    private JButton ActualizarEmpleado;

   public MenuProyecto()
   {
    inicializarControladores();
    setVisible(true);
    setLayout(null);
    setTitle("Menu Proyecto");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JLabel Title = new JLabel("\n=== SISTEMA CRM CLIENTES ===");
    Title.setBounds(300, 10, 200, 50);
    add(Title);


    Clientes = new JButton("Gestion de Clientes");
    Clientes.setBounds(100, 100, 200, 50);
    add(Clientes);
    Clientes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MenuCliente();
        }
    });

    Empleados = new JButton("Gestion de Empleados");
    Empleados.setBounds(100, 150, 200, 50);
    add(Empleados);
    Empleados.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MenuEmpleado();
        }
    });

    Productos = new JButton("Gestion de Productos");
    Productos.setBounds(100, 200, 200, 50);
    add(Productos);
    Productos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar cuando se tenga la funcionalidad
            JOptionPane.showMessageDialog(null, "Funcionalidad no implementada aún");
        }
    });

    Proveedores = new JButton("Gestion de Proveedores");
    Proveedores.setBounds(100, 250, 200, 50);
    add(Proveedores);
    Proveedores.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar cuando se tenga la funcionalidad
            JOptionPane.showMessageDialog(null, "Funcionalidad no implementada aún");
        }
    });

    Facturas = new JButton("Gestion de Facturas");
    Facturas.setBounds(100, 300, 200, 50);
    add(Facturas);
    Facturas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar cuando se tenga la funcionalidad
            JOptionPane.showMessageDialog(null, "Funcionalidad no implementada aún");
        }
    });

    Categorias = new JButton("Gestion de Categorias");
    Categorias.setBounds(100, 350, 200, 50);
    add(Categorias);
    Categorias.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar cuando se tenga la funcionalidad
            JOptionPane.showMessageDialog(null, "Funcionalidad no implementada aún");
        }
    });

    Provees = new JButton("Gestion de Provees");
    Provees.setBounds(100, 400, 200, 50);
    add(Provees);
    Provees.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar cuando se tenga la funcionalidad
            JOptionPane.showMessageDialog(null, "Funcionalidad no implementada aún");
        }
    });

    Salir = new JButton("Salir");
    Salir.setBounds(100, 450, 200, 50);
    add(Salir);
    Salir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });


   }

  private void MenuCliente()
  {

    JFrame frame = new JFrame("Menu Cliente");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== GESTIÓN DE CLIENTES ===");
    Title.setBounds(300, 10, 200, 50);
    frame.add(Title);

    TodoCliente = new JButton("Ver todos los clientes");
    TodoCliente.setBounds(100, 100, 200, 50);
    frame.add(TodoCliente);
    TodoCliente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            ListarClientes();
        }
    });
    
    BuscarCliente = new JButton("Buscar cliente por ID");
    BuscarCliente.setBounds(100, 150, 200, 50);
    frame.add(BuscarCliente);
    BuscarCliente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            BuscarCliente();
        }
    });
    
    AñadirCliente = new JButton("Añadir nuevo cliente");
    AñadirCliente.setBounds(100, 200, 200, 50);
    frame.add(AñadirCliente);
    AñadirCliente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            AñadirCliente();
        }
    });

    ModificarCliente = new JButton("Actualizar cliente");
    ModificarCliente.setBounds(100, 250, 200, 50);
    frame.add(ModificarCliente);
    ModificarCliente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            ActualizarCliente();
        }
    });
    
    EliminarCliente = new JButton("Eliminar cliente");
    EliminarCliente.setBounds(100, 300, 200, 50);
    frame.add(EliminarCliente);
    EliminarCliente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            EliminarCliente();
        }
    });

    VolverMenu = new JButton("Volver al menu principal");
    VolverMenu.setBounds(100, 350, 200, 50);
    frame.add(VolverMenu);
    VolverMenu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new MenuProyecto();
        }
    });
    
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
                    clienteTextArea.setBounds(50, 10 + i * 150, 600, 130);
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
    volverButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MenuCliente();
        }
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
          Long id= 0L;
          boolean idValido = false;
          
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
          
          // Área para mostrar la información del cliente (inicialmente invisible)
          final JTextArea clienteTextArea = new JTextArea();
          clienteTextArea.setBounds(100, 190, 600, 180);
          clienteTextArea.setEditable(false);
          clienteTextArea.setBackground(new Color(240, 240, 240));
          clienteTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
          clienteTextArea.setVisible(false);
          frame.add(clienteTextArea);
          
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
                          frame.repaint();
                      }
                      else
                      {
                          statusLabel.setText("Cliente no encontrado");
                          clienteTextArea.setVisible(false);
                          frame.repaint();
                      }
                  }
                  catch(NumberFormatException nfe)
                  {
                      statusLabel.setText("ID inválido. Introduce un número válido.");
                      clienteTextArea.setVisible(false);
                      frame.repaint();
                  }
              }
          });
          
          JButton volverButton = new JButton("Volver");
          volverButton.setBounds(300, 400, 200, 30);
          frame.add(volverButton);
          volverButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  frame.dispose();
                  MenuCliente();
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
                        if (cif.isEmpty() || !cif.matches("[A-Z]\\d{8}")) 
                        {
                            statusLabel.setText("Error: El CIF debe tener 1 letra seguida de 8 números.");
                            return;
                        }
                        
                        String nombreEmpresa = nombreEmpresaTextField.getText();
                        if (nombreEmpresa.isEmpty()) 
                        {
                            statusLabel.setText("Error: El nombre de la empresa no puede estar vacío.");
                            return;
                        }
                        
                        String nombreResponsable = nombreResponsableTextField.getText();
                        if (nombreResponsable.isEmpty()) 
                        {
                            statusLabel.setText("Error: El nombre del responsable no puede estar vacío.");
                            return;
                        }
                        
                        String pais = paisTextField.getText();
                        if (pais.isEmpty()) 
                        {
                            statusLabel.setText("Error: El país no puede estar vacío.");
                            return;
                        }
                        
                        String provincia = provinciaTextField.getText();
                        if (provincia.isEmpty()) 
                        {
                            statusLabel.setText("Error: La provincia no puede estar vacía.");
                            return;
                        }
                        
                        String direccion = direccionTextField.getText();
                        if (direccion.isEmpty()) 
                        {
                            statusLabel.setText("Error: La dirección no puede estar vacía.");
                            return;
                        }
                        
                        String email = emailTextField.getText();
                        if (email.isEmpty()) 
                        {
                            statusLabel.setText("Error: El email no puede estar vacío.");
                            return;
                        }
                        else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                        {
                            statusLabel.setText("Error: El email debe tener un formato válido.");
                            return;
                        }
                        
                        String telefono = telefonoTextField.getText();
                        if (telefono.isEmpty()) 
                        {
                            statusLabel.setText("Error: El teléfono no puede estar vacío.");
                            return;
                        }
                        else if (!telefono.matches("\\d{9}")) 
                        {
                            statusLabel.setText("Error: El teléfono debe contener exactamente 9 números.");
                            return;
                        }
                        
                        String codigoPostal = cpTextField.getText();
                        if (codigoPostal.isEmpty()) 
                        {
                            statusLabel.setText("Error: El código postal no puede estar vacío.");
                            return;
                        }
                        else if (!codigoPostal.matches("\\d{5}")) 
                        {
                            statusLabel.setText("Error: El código postal debe contener exactamente 5 números.");
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
            volverButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    frame.dispose();
                    MenuCliente();
                }
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
            
            // Botón de búsqueda junto al campo de ID
            final JButton buscarButton = new JButton("Buscar Cliente");
            buscarButton.setBounds(520, 70, 150, 30);
            frame.add(buscarButton);
        
            // Crear label de estado en la parte superior para mostrar mensajes inmediatos
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
            
            // Botones
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
                            
                            statusLabel.setText("Cliente encontrado. Modifique los campos y guarde los cambios.");
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
                    
                        String cif = cifTextField.getText();
                        if (cif.isEmpty()) {
                            cif = clienteExistente.getCIF();
                        } else if (!cif.matches("[A-Z]\\d{8}")) {
                            statusLabel.setText("Error: El CIF debe tener 1 letra seguida de 8 números.");
                            return;
                        }
                        
                        String nombreEmpresa = nombreEmpresaTextField.getText();
                        if (nombreEmpresa.isEmpty()) {
                            nombreEmpresa = clienteExistente.getNombre_Empresa();
                        }
                        
                        String nombreResponsable = nombreResponsableTextField.getText();
                        if (nombreResponsable.isEmpty()) {
                            nombreResponsable = clienteExistente.getNombre_Responsable();
                        }
                        
                        String pais = paisTextField.getText();
                        if (pais.isEmpty()) {
                            pais = clienteExistente.getPais();
                        }
                        
                        String provincia = provinciaTextField.getText();
                        if (provincia.isEmpty()) {
                            provincia = clienteExistente.getProvincia();
                        }
                        
                        String direccion = direccionTextField.getText();
                        if (direccion.isEmpty()) {
                            direccion = clienteExistente.getDireccion();
                        }
                        
                        String email = emailTextField.getText();
                        if (email.isEmpty()) {
                            email = clienteExistente.getEmail();
                        }
                        else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                            statusLabel.setText("Error: El email debe tener un formato válido.");
                            return;
                        }
                        
                        String telefono = telefonoTextField.getText();
                        if (telefono.isEmpty()) {
                            telefono = clienteExistente.getTelefono();
                        }
                        else if (!telefono.matches("\\d{9}")) {
                            statusLabel.setText("Error: El teléfono debe contener exactamente 9 números.");
                            return;
                        }
                        
                        String codigoPostal = cpTextField.getText();
                        if (codigoPostal.isEmpty()) {
                            codigoPostal = clienteController.findById(clienteId[0]).getCodigo_Postal();
                        }
                        else if (!codigoPostal.matches("\\d{5}")) {
                            statusLabel.setText("Error: El código postal debe contener exactamente 5 números.");
                            return;
                        }
                        
                    
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
            
            volverButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MenuCliente();
                }
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
            
            volverButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    frame.dispose();
                    MenuCliente();
                }
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

   protected Connection connection;
   protected ClienteController clienteController;
   protected EmpleadoController empleadoController;
   protected ProductoController productoController;
   protected ProveedorController proveedorController;
   protected FacturaController facturaController;
   protected CategoriaController categoriaController;
   protected ProveeController proveeController;
   protected Scanner scanner;
   
   protected void inicializarControladores() 
   {
       try 
       {
           this.connection = DatabaseConnection.getInstance();
           
           this.clienteController = new ClienteController();
           this.empleadoController = new EmpleadoController();
           this.productoController = new ProductoController();
           this.proveedorController = new ProveedorController();
           this.facturaController = new FacturaController();
           this.categoriaController = new CategoriaController();
           this.proveeController = new ProveeController();
           
           System.out.println("Conexión a la base de datos establecida correctamente");
       } 
       catch (SQLException e) 
       {
           System.err.println("Error al conectar con la base de datos: " + e.getMessage());
           e.printStackTrace();
       }
   }
   
   protected boolean VerificarDato(String dato) 
   {
       if (dato.isEmpty())
       {
           System.out.println("El dato no puede estar vacío.");
           return false;
       }
       return true;
   }

   private void MenuEmpleado()
   {
     JFrame frame = new JFrame("Menu Empleado");
     frame.setLayout(null);
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);

     JLabel Title = new JLabel("\n=== GESTIÓN DE EMPLEADOS ===");
     Title.setBounds(300, 10, 200, 50);
     frame.add(Title);

     TodoEmpleado = new JButton("Ver todos los empleados");
     TodoEmpleado.setBounds(100, 100, 200, 50);
     frame.add(TodoEmpleado);
     TodoEmpleado.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             ListarEmpleados();
         }
     });
     
     BuscarEmpleado = new JButton("Buscar empleado por ID");
     BuscarEmpleado.setBounds(100, 150, 200, 50);
     frame.add(BuscarEmpleado);
     BuscarEmpleado.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             BuscarEmpleado();
         }
     });
     
     AñadirEmpleado = new JButton("Añadir nuevo empleado");
     AñadirEmpleado.setBounds(100, 200, 200, 50);
     frame.add(AñadirEmpleado);
     AñadirEmpleado.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             AñadirEmpleado();
         }
     });

     ModificarEmpleado = new JButton("Actualizar empleado");
     ModificarEmpleado.setBounds(100, 250, 200, 50);
     frame.add(ModificarEmpleado);
     ModificarEmpleado.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             ActualizarEmpleado();
         }
     });
     
     EliminarEmpleado = new JButton("Eliminar empleado");
     EliminarEmpleado.setBounds(100, 300, 200, 50);
     frame.add(EliminarEmpleado);
     EliminarEmpleado.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             EliminarEmpleado();
         }
     });

     VolverMenu = new JButton("Volver al menu principal");
     VolverMenu.setBounds(100, 350, 200, 50);
     frame.add(VolverMenu);
     VolverMenu.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             new MenuProyecto();
         }
     });
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
                     empleadoTextArea.setBounds(50, 10 + i * 150, 600, 130);
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
     volverButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             MenuEmpleado();
         }
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
           Long id= 0L;
           boolean idValido = false;
           
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
           
           // Área para mostrar la información del empleado (inicialmente invisible)
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
           volverButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   frame.dispose();
                   MenuEmpleado();
               }
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
                        if (nombre.isEmpty()) 
                        {
                            statusLabel.setText("Error: El nombre no puede estar vacío.");
                            return;
                        }
                        
                        String apellido = apellidoTextField.getText();
                        if (apellido.isEmpty()) 
                        {
                            statusLabel.setText("Error: El apellido no puede estar vacío.");
                            return;
                        }
                        
                        String nif = nifTextField.getText();
                        if (nif.isEmpty() || !nif.matches("\\d{8}[A-Za-z]")) 
                        {
                            statusLabel.setText("Error: El NIF debe tener 8 números seguidos de 1 letra.");
                            return;
                        }
                        
                        String direccion = direccionTextField.getText();
                        if (direccion.isEmpty()) 
                        {
                            statusLabel.setText("Error: La dirección no puede estar vacía.");
                            return;
                        }
                        
                        String codigoPostal = cpTextField.getText();
                        if (codigoPostal.isEmpty()) 
                        {
                            statusLabel.setText("Error: El código postal no puede estar vacío.");
                            return;
                        }
                        else if (!codigoPostal.matches("\\d{5}")) 
                        {
                            statusLabel.setText("Error: El código postal debe contener exactamente 5 números.");
                            return;
                        }
                        
                        String provincia = provinciaTextField.getText();
                        if (provincia.isEmpty()) 
                        {
                            statusLabel.setText("Error: La provincia no puede estar vacía.");
                            return;
                        }
                        
                        String pais = paisTextField.getText();
                        if (pais.isEmpty()) 
                        {
                            statusLabel.setText("Error: El país no puede estar vacío.");
                            return;
                        }
                        
                        String telefono = telefonoTextField.getText();
                        if (telefono.isEmpty()) 
                        {
                            statusLabel.setText("Error: El teléfono no puede estar vacío.");
                            return;
                        }
                        else if (!telefono.matches("\\d{9}")) 
                        {
                            statusLabel.setText("Error: El teléfono debe contener exactamente 9 números.");
                            return;
                        }
                        
                        String email = emailTextField.getText();
                        if (email.isEmpty()) 
                        {
                            statusLabel.setText("Error: El email no puede estar vacío.");
                            return;
                        }
                        else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                        {
                            statusLabel.setText("Error: El email debe tener un formato válido.");
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
            volverButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    frame.dispose();
                    MenuEmpleado();
                }
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
                             
                             statusLabel.setText("Empleado encontrado. Modifique los campos y guarde los cambios.");
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
                     
                         String nombre = nombreTextField.getText();
                         if (nombre.isEmpty()) {
                             nombre = empleadoExistente.getNombre();
                         }
                         
                         String apellido = apellidoTextField.getText();
                         if (apellido.isEmpty()) {
                             apellido = empleadoExistente.getApellido();
                         }
                         
                         String nif = nifTextField.getText();
                         if (nif.isEmpty()) {
                             nif = empleadoExistente.getNIF();
                         } else if (!nif.matches("\\d{8}[A-Za-z]")) {
                             statusLabel.setText("Error: El NIF debe tener 8 números seguidos de 1 letra.");
                             return;
                         }
                         
                         String direccion = direccionTextField.getText();
                         if (direccion.isEmpty()) {
                             direccion = empleadoExistente.getDireccion();
                         }
                         
                         String codigoPostal = cpTextField.getText();
                         if (codigoPostal.isEmpty()) {
                             codigoPostal = empleadoExistente.getCodigo_Postal();
                         } else if (!codigoPostal.matches("\\d{5}")) {
                             statusLabel.setText("Error: El código postal debe contener exactamente 5 números.");
                             return;
                         }
                         
                         String provincia = provinciaTextField.getText();
                         if (provincia.isEmpty()) {
                             provincia = empleadoExistente.getProvincia();
                         }
                         
                         String pais = paisTextField.getText();
                         if (pais.isEmpty()) {
                             pais = empleadoExistente.getPais();
                         }
                         
                         String telefono = telefonoTextField.getText();
                         if (telefono.isEmpty()) {
                             telefono = empleadoExistente.getTelfono();
                         } else if (!telefono.matches("\\d{9}")) {
                             statusLabel.setText("Error: El teléfono debe contener exactamente 9 números.");
                             return;
                         }
                         
                         String email = emailTextField.getText();
                         if (email.isEmpty()) {
                             email = empleadoExistente.getEmail();
                         } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                             statusLabel.setText("Error: El email debe tener un formato válido.");
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
             
             volverButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     frame.dispose();
                     MenuEmpleado();
                 }
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
             
             volverButton.addActionListener(new ActionListener() 
             {
                 @Override
                 public void actionPerformed(ActionEvent e) 
                 {
                     frame.dispose();
                     MenuEmpleado();
                 }
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


