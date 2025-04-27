package org.carlosydiego.crmclientes.app.menuproyecto;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    private JButton TodoEmpleado;
    private JButton BuscarEmpleado;
    private JButton ModificarEmpleado;
    private JButton AñadirEmpleado;
    private JButton EliminarEmpleado;
    private JButton TodoFactura;
    private JButton BuscarFactura;
    private JButton ModificarFactura;
    private JButton AñadirFactura;
    private JButton EliminarFactura;
    private JButton GenerarArchivoFactura;

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
            dispose();
            MenuProducto();
        }
    });

    Proveedores = new JButton("Gestion de Proveedores");
    Proveedores.setBounds(100, 250, 200, 50);
    add(Proveedores);
    Proveedores.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MenuProveedor();
        }
    });

    Facturas = new JButton("Gestion de Facturas");
    Facturas.setBounds(100, 300, 200, 50);
    add(Facturas);
    Facturas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MenuFactura();
        }
    });

    Categorias = new JButton("Gestion de Categorias");
    Categorias.setBounds(100, 350, 200, 50);
    add(Categorias);
    Categorias.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MenuCategoria();
        }
    });

    Provees = new JButton("Gestion de Provees");
    Provees.setBounds(100, 400, 200, 50);
    add(Provees);
    Provees.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MenuProvee();
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
        MenuCliente();
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
              MenuCliente();
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
        MenuCliente();
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
                MenuCliente();
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
                MenuCliente();
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
     Title.setBounds(300, 10, 300, 50);
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
         MenuEmpleado();
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
               MenuEmpleado();
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
                MenuEmpleado();
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
                 MenuEmpleado();
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
                 MenuEmpleado();
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

   private void MenuFactura()
   {
     JFrame frame = new JFrame("Menu Factura");
     frame.setLayout(null);
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);

     JLabel Title = new JLabel("\n=== GESTIÓN DE FACTURAS ===");
     Title.setBounds(300, 10, 200, 50);
     frame.add(Title);

     TodoFactura = new JButton("Ver todas las facturas");
     TodoFactura.setBounds(100, 100, 200, 50);
     frame.add(TodoFactura);
     TodoFactura.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             ListarFacturas();
         }
     });
     
     BuscarFactura = new JButton("Buscar factura por ID");
     BuscarFactura.setBounds(100, 150, 200, 50);
     frame.add(BuscarFactura);
     BuscarFactura.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             BuscarFactura();
         }
     });
     
     AñadirFactura = new JButton("Crear nueva factura");
     AñadirFactura.setBounds(100, 200, 200, 50);
     frame.add(AñadirFactura);
     AñadirFactura.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             GenerarArchivoFacturaPorId();
         }
     });

     ModificarFactura = new JButton("Actualizar factura");
     ModificarFactura.setBounds(100, 250, 200, 50);
     frame.add(ModificarFactura);
     ModificarFactura.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             ListarFacturas();
         }
     });
     
     EliminarFactura = new JButton("Eliminar factura");
     EliminarFactura.setBounds(100, 300, 200, 50);
     frame.add(EliminarFactura);
     EliminarFactura.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             BuscarFactura();
         }
     });
     
     GenerarArchivoFactura = new JButton("Generar archivo de factura");
     GenerarArchivoFactura.setBounds(100, 350, 200, 50);
     frame.add(GenerarArchivoFactura);
     GenerarArchivoFactura.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             GenerarArchivoFacturaPorId();
         }
     });

     VolverMenu = new JButton("Volver al menu principal");
     VolverMenu.setBounds(100, 400, 200, 50);
     frame.add(VolverMenu);
     VolverMenu.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             new MenuProyecto();
         }
     });
   }

   private void ListarFacturas()
   {
     JFrame frame = new JFrame("Listar Facturas");
     frame.setLayout(null);
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);

     JLabel Title = new JLabel("\n=== LISTA DE FACTURAS ===");
     Title.setBounds(300, 10, 200, 50);
     frame.add(Title);

     if(facturaController!=null)
     {
         try
         {
             List<Factura> facturas = facturaController.findAll();
             if(facturas!=null && !facturas.isEmpty())
             {
                 JPanel panelFacturas = new JPanel();
                 panelFacturas.setLayout(null);
                 
                 JScrollPane scrollPane = new JScrollPane(panelFacturas);
                 scrollPane.setBounds(50, 70, 700, 400);
                 frame.add(scrollPane);
                 
                 panelFacturas.setPreferredSize(new Dimension(680, Math.max(380, facturas.size() * 150)));
                 
                 for(int i = 0; i < facturas.size(); i++)
                 {
                     JTextArea facturaTextArea = new JTextArea(facturas.get(i).toString());
                     facturaTextArea.setBounds(50, 10 + i * 150, 600, 150);
                     facturaTextArea.setEditable(false);
                     facturaTextArea.setBackground(new Color(240, 240, 240));
                     facturaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                     panelFacturas.add(facturaTextArea);
                 }
             }
             else
             {
                 JLabel noFacturasLabel = new JLabel("No hay facturas registradas en el sistema");
                 noFacturasLabel.setBounds(300, 200, 300, 30);
                 frame.add(noFacturasLabel);
             }
         }
         catch(Exception e)
         {
             System.err.println("Error al obtener las facturas: " + e.getMessage());
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
         MenuFactura();
     });
   }

   private void BuscarFactura()
   {
     JFrame frame = new JFrame("Buscar Factura");
     frame.setLayout(null);
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);

     JLabel Title = new JLabel("\n=== BUSCAR FACTURA ===");
     Title.setBounds(300, 10, 200, 50);
     frame.add(Title);
     if(facturaController!=null)
     {
         try
         {
           Long id= 0L;
           boolean idValido = false;
           
           JLabel idLabel = new JLabel("Introduce el ID de la factura:");
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
           
           
           final JTextArea facturaTextArea = new JTextArea();
           facturaTextArea.setBounds(100, 190, 600, 180);
           facturaTextArea.setEditable(false);
           facturaTextArea.setBackground(new Color(240, 240, 240));
           facturaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
           facturaTextArea.setVisible(false);
           frame.add(facturaTextArea);
           
           buscarButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   String input = idTextField.getText();
                   try
                   {
                       long id = Long.parseLong(input);
                       statusLabel.setText("Buscando factura...");
                       
                       Factura factura = facturaController.findById(id);
                       if(factura!=null)
                       {
                           statusLabel.setText("Factura encontrada:");
                           facturaTextArea.setText(factura.toString());
                           facturaTextArea.setVisible(true);
                           frame.repaint();
                       }
                       else
                       {
                           statusLabel.setText("Factura no encontrada");
                           facturaTextArea.setVisible(false);
                           frame.repaint();
                       }
                   }
                   catch(NumberFormatException nfe)
                   {
                       statusLabel.setText("ID inválido. Introduce un número válido.");
                       facturaTextArea.setVisible(false);
                       frame.repaint();
                   }
               }
           });
           
           JButton volverButton = new JButton("Volver");
           volverButton.setBounds(300, 400, 200, 30);
           frame.add(volverButton);
           volverButton.addActionListener(e -> {
               frame.dispose();
               MenuFactura();
           });
         }
         catch(Exception e)
         {
             System.err.println("Error al buscar la factura: " + e.getMessage());
         }
     }
     else
     {
         System.out.println("Error: No hay conexion a la base de datos");
     }
   }

   private void GenerarArchivoFacturaPorId()
   {
     JFrame frame = new JFrame("Generar Archivo de Factura");
     frame.setLayout(null);
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);

     JLabel Title = new JLabel("\n=== GENERAR ARCHIVO DE FACTURA ===");
     Title.setBounds(300, 10, 300, 50);
     frame.add(Title);

     if(facturaController!=null)
     {
         try
         {
             JLabel idLabel = new JLabel("Introduce el ID de la factura:");
             idLabel.setBounds(100, 100, 200, 30);
             frame.add(idLabel);

             JTextField idTextField = new JTextField(10);
             idTextField.setBounds(300, 100, 200, 30);
             frame.add(idTextField);
             
             JButton generarButton = new JButton("Generar");
             generarButton.setBounds(550, 100, 150, 30);
             frame.add(generarButton);
             
             final JLabel statusLabel = new JLabel("");
             statusLabel.setBounds(100, 150, 600, 30);
             frame.add(statusLabel);
             
             generarButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String input = idTextField.getText();
                     try {
                         long id = Long.parseLong(input);
                         statusLabel.setText("Generando archivo...");
                         
                         Factura factura = facturaController.findById(id);
                         if(factura != null) {
                             FacturaFileManager.generarArchivoFactura(factura);
                             statusLabel.setText("Archivo generado correctamente.");
                         } else {
                             statusLabel.setText("No se encontró la factura con el ID especificado.");
                         }
                     } catch (NumberFormatException nfe) {
                         statusLabel.setText("ID inválido. Introduce un número válido.");
                     }
                 }
             });
             
             JButton volverButton = new JButton("Volver");
             volverButton.setBounds(300, 400, 200, 30);
             frame.add(volverButton);
             volverButton.addActionListener(e -> {
                 frame.dispose();
                 MenuFactura();
             });
         }
         catch(Exception e)
         {
             System.err.println("Error al generar el archivo de factura: " + e.getMessage());
         }
     }
     else
     {
         System.out.println("Error: No hay conexion a la base de datos");
     }
   }

   private void MenuCategoria()
   {
     JFrame frame = new JFrame("Menu Categoría");
     frame.setLayout(null);
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);

     JLabel Title = new JLabel("\n=== GESTIÓN DE CATEGORÍAS ===");
     Title.setBounds(300, 10, 300, 50);
     frame.add(Title);

     JButton listarCategoriasButton = new JButton("Ver todas las categorías");
     listarCategoriasButton.setBounds(100, 100, 200, 50);
     frame.add(listarCategoriasButton);
     listarCategoriasButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             ListarCategorias();
         }
     });
     
     JButton añadirCategoriaButton = new JButton("Añadir nueva categoría");
     añadirCategoriaButton.setBounds(100, 150, 200, 50);
     frame.add(añadirCategoriaButton);
     añadirCategoriaButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             AñadirCategoria();
         }
     });
     
     JButton buscarCategoriaButton = new JButton("Buscar categoría por ID");
     buscarCategoriaButton.setBounds(100, 200, 200, 50);
     frame.add(buscarCategoriaButton);
     buscarCategoriaButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             BuscarCategoria();
         }
     });

     JButton actualizarCategoriaButton = new JButton("Actualizar categoría");
     actualizarCategoriaButton.setBounds(100, 250, 200, 50);
     frame.add(actualizarCategoriaButton);
     actualizarCategoriaButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             ActualizarCategoria();
         }
     });

     JButton eliminarCategoriaButton = new JButton("Eliminar categoría");
     eliminarCategoriaButton.setBounds(100, 300, 200, 50);
     frame.add(eliminarCategoriaButton);
     eliminarCategoriaButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             frame.dispose();
             EliminarCategoria();
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

   private void ListarCategorias()
   {
     JFrame frame = new JFrame("Listar Categorías");
     frame.setLayout(null);
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);

     JLabel Title = new JLabel("\n=== LISTA DE CATEGORÍAS ===");
     Title.setBounds(300, 10, 200, 50);
     frame.add(Title);

     if(categoriaController!=null)
     {
         try
         {
             List<Categoria> categorias = categoriaController.findAll();
             if(categorias!=null && !categorias.isEmpty())
             {
                 JPanel panelCategorias = new JPanel();
                 panelCategorias.setLayout(null);
                 
                 JScrollPane scrollPane = new JScrollPane(panelCategorias);
                 scrollPane.setBounds(50, 70, 700, 400);
                 frame.add(scrollPane);
                 
                 panelCategorias.setPreferredSize(new Dimension(680, Math.max(380, categorias.size() * 150)));
                 
                 for(int i = 0; i < categorias.size(); i++)
                 {
                     JTextArea categoriaTextArea = new JTextArea(categorias.get(i).toString());
                     categoriaTextArea.setBounds(50, 10 + i * 150, 600, 130);
                     categoriaTextArea.setEditable(false);
                     categoriaTextArea.setBackground(new Color(240, 240, 240));
                     categoriaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                     panelCategorias.add(categoriaTextArea);
                 }
             }
             else
             {
                 JLabel noCategoriasLabel = new JLabel("No hay categorías registradas en el sistema");
                 noCategoriasLabel.setBounds(300, 200, 300, 30);
                 frame.add(noCategoriasLabel);
             }
         }
         catch(Exception e)
         {
             System.err.println("Error al obtener las categorías: " + e.getMessage());
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
         MenuCategoria();
     });
   }

   private void AñadirCategoria()
   {
     JFrame frame = new JFrame("Añadir Categoría");
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);
     frame.setLayout(null);

     JLabel Title = new JLabel("\n=== AÑADIR NUEVA CATEGORÍA ===");
     Title.setBounds(300, 10, 300, 50);
     frame.add(Title);

     if(categoriaController!=null)
     {
         try
         {
         
             JLabel nombreLabel = new JLabel("Nombre:");
             nombreLabel.setBounds(100, 70, 200, 30);
             frame.add(nombreLabel);
             
             JTextField nombreTextField = new JTextField(10);
             nombreTextField.setBounds(350, 70, 200, 30);
             frame.add(nombreTextField);
             
             
             JLabel statusLabel = new JLabel("");
             statusLabel.setBounds(100, 470, 400, 30);
             frame.add(statusLabel);
         
             JButton guardarButton = new JButton("Guardar Categoría");
             guardarButton.setBounds(100, 430, 200, 30);
             frame.add(guardarButton);
             guardarButton.addActionListener(new ActionListener() 
             {
                 @Override
                 public void actionPerformed(ActionEvent e) 
                 {
                     try {
                         // Obtener valores de los campos
                         String nombre = nombreTextField.getText();
                         
                         // Verificar que el campo no esté vacío
                         if (nombre.isEmpty()) {
                             statusLabel.setText("Error: El nombre es obligatorio");
                             return;
                         }
                         
                         // Crear y guardar nueva categoría
                         Categoria nuevaCategoria = new Categoria(null, nombre);
                         
                         categoriaController.save(nuevaCategoria);
                         
                         statusLabel.setText("Categoría añadida correctamente.");
                         
                         nombreTextField.setText("");
                     } 
                     catch (Exception ex) 
                     {
                         statusLabel.setText("Error al guardar la categoría: " + ex.getMessage());
                     }
                 }
             });
             
         
             JButton volverButton = new JButton("Volver");
             volverButton.setBounds(350, 430, 200, 30);
             frame.add(volverButton);
             volverButton.addActionListener(e -> {
                 frame.dispose();
                 MenuCategoria();
             });
         }
         catch(Exception e)
         {
             System.err.println("Error al añadir la categoría: " + e.getMessage());
         }
     }
     else
     {
         System.out.println("Error: No hay conexion a la base de datos");
     }
   }

   private void EliminarCategoria()
   {
     JFrame frame = new JFrame("Eliminar Categoría");
     frame.setSize(800, 600);
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.setVisible(true);
     frame.setLayout(null);

     JLabel Title = new JLabel("\n=== ELIMINAR CATEGORÍA ===");
     Title.setBounds(300, 10, 300, 50);
     frame.add(Title);

     if(categoriaController!=null)
     {
         try
         {
             
             JLabel idLabel = new JLabel("ID de la categoría a eliminar:");
             idLabel.setBounds(100, 100, 200, 30);
             frame.add(idLabel);
             
             JTextField idTextField = new JTextField(10);
             idTextField.setBounds(300, 100, 200, 30);
             frame.add(idTextField);
             
             
             final JLabel infoLabel = new JLabel("Información de la categoría:");
             infoLabel.setBounds(100, 150, 500, 30);
             frame.add(infoLabel);
             
             final JTextArea categoriaInfo = new JTextArea();
             categoriaInfo.setBounds(100, 190, 600, 100);
             categoriaInfo.setEditable(false);
             categoriaInfo.setBackground(new Color(240, 240, 240));
             categoriaInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
             frame.add(categoriaInfo);
             
             
             final JLabel statusLabel = new JLabel("");
             statusLabel.setBounds(100, 350, 600, 30);
             frame.add(statusLabel);
             
         
             final JButton buscarButton = new JButton("Buscar Categoría");
             buscarButton.setBounds(550, 100, 150, 30);
             frame.add(buscarButton);
             
             final JButton eliminarButton = new JButton("Eliminar Categoría");
             eliminarButton.setBounds(200, 400, 200, 30);
             eliminarButton.setEnabled(false);
             frame.add(eliminarButton);
             
             JButton volverButton = new JButton("Volver");
             volverButton.setBounds(420, 400, 200, 30);
             frame.add(volverButton);
             
             final Long[] categoriaId = new Long[1];
             
             buscarButton.addActionListener(new ActionListener() 
             {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     try {
                         String input = idTextField.getText();
                         long id = Long.parseLong(input);
                         
                         Categoria categoria = categoriaController.findById(id);
                         if (categoria != null) {
                     
                             categoriaId[0] = categoria.getID_Categoria();
                             
                             categoriaInfo.setText(categoria.toString());
                             eliminarButton.setEnabled(true);
                             statusLabel.setText("Categoría encontrada. Pulse 'Eliminar Categoría' para confirmar.");
                         } 
                         else 
                         {
                             categoriaInfo.setText("");
                             eliminarButton.setEnabled(false);
                             statusLabel.setText("No se encontró una categoría con el ID: " + id);
                         }
                     } catch (NumberFormatException nfe) {
                         statusLabel.setText("ID inválido. Introduce un número válido.");
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
                             "¿Está seguro de que desea eliminar esta categoría?", 
                             "Confirmar eliminación", 
                             JOptionPane.YES_NO_OPTION);
                         
                         if (confirmacion == JOptionPane.YES_OPTION) 
                         {
                             categoriaController.delete(categoriaId[0]);
                             
                        
                             categoriaInfo.setText("");
                             idTextField.setText("");
                             eliminarButton.setEnabled(false);
                             statusLabel.setText("Categoría eliminada correctamente.");
                         }
                     } 
                     catch (Exception ex) 
                     {
                         statusLabel.setText("Error al eliminar la categoría: " + ex.getMessage());
                     }
                 }
             });
             
             volverButton.addActionListener(e -> {
                 frame.dispose();
                 MenuCategoria();
             });
         }
         catch(Exception e)
         {
             System.err.println("Error al eliminar la categoría: " + e.getMessage());
         }
     }
     else
     {
         System.out.println("Error: No hay conexion a la base de datos");
     }
   }



  private void BuscarCategoria()
  {
    JFrame frame = new JFrame("Buscar Categoría");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== BUSCAR CATEGORÍA ===");
    Title.setBounds(300, 10, 200, 50);
    frame.add(Title);
    
    if(categoriaController!=null)
    {
        try
        {
          Long id= 0L;
          boolean idValido = false;
          
          JLabel idLabel = new JLabel("Introduce el ID de la categoría:");
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
          
          final JTextArea categoriaTextArea = new JTextArea();
          categoriaTextArea.setBounds(100, 190, 600, 180);
          categoriaTextArea.setEditable(false);
          categoriaTextArea.setBackground(new Color(240, 240, 240));
          categoriaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
          categoriaTextArea.setVisible(false);
          frame.add(categoriaTextArea);
          
          buscarButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String input = idTextField.getText();
                  try
                  {
                      long id = Long.parseLong(input);
                      statusLabel.setText("Buscando categoría...");
                      
                      Categoria categoria = categoriaController.findById(id);
                      if(categoria!=null)
                      {
                          statusLabel.setText("Categoría encontrada:");
                          categoriaTextArea.setText(categoria.toString());
                          categoriaTextArea.setVisible(true);
                          frame.repaint();
                      }
                      else
                      {
                          statusLabel.setText("Categoría no encontrada");
                          categoriaTextArea.setVisible(false);
                          frame.repaint();
                      }
                  }
                  catch(NumberFormatException nfe)
                  {
                      statusLabel.setText("ID inválido. Introduce un número válido.");
                      categoriaTextArea.setVisible(false);
                      frame.repaint();
                  }
              }
          });
          
          JButton volverButton = new JButton("Volver");
          volverButton.setBounds(300, 400, 200, 30);
          frame.add(volverButton);
          volverButton.addActionListener(e -> {
              frame.dispose();
              MenuCategoria();
          });
        }
        catch(Exception e)
        {
            System.err.println("Error al buscar la categoría: " + e.getMessage());
        }
    }
    else
    {
        System.out.println("Error: No hay conexion a la base de datos");
    }
  }

  private void ActualizarCategoria()
  {
    JFrame frame = new JFrame("Actualizar Categoría");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setLayout(null);

    JLabel Title = new JLabel("\n=== ACTUALIZAR CATEGORÍA ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    if(categoriaController!=null)
    {
        try
        {
            JLabel idLabel = new JLabel("ID de la categoría a actualizar:");
            idLabel.setBounds(100, 70, 200, 30);
            frame.add(idLabel);
            
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 70, 200, 30);
            frame.add(idTextField);
            
            final JButton buscarButton = new JButton("Buscar Categoría");
            buscarButton.setBounds(520, 70, 150, 30);
            frame.add(buscarButton);
        
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 110, 600, 30);
            frame.add(statusLabel);
            
            JLabel nombreLabel = new JLabel("Nombre:");
            nombreLabel.setBounds(100, 150, 200, 30);
            frame.add(nombreLabel);
            
            final JTextField nombreTextField = new JTextField(10);
            nombreTextField.setBounds(300, 150, 200, 30);
            nombreTextField.setEnabled(false);
            frame.add(nombreTextField);
            
            final JButton guardarButton = new JButton("Guardar Cambios");
            guardarButton.setBounds(100, 200, 200, 30);
            guardarButton.setEnabled(false);
            frame.add(guardarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(350, 200, 200, 30);
            frame.add(volverButton);
            
            final Long[] categoriaId = new Long[1];
            
            buscarButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String input = idTextField.getText();
                        long id = Long.parseLong(input);
                        
                        Categoria categoria = categoriaController.findById(id);
                        if (categoria != null) {
                            categoriaId[0] = categoria.getID_Categoria();
                            
                            nombreTextField.setText(categoria.getNombre());
                            
                            nombreTextField.setEnabled(true);
                            guardarButton.setEnabled(true);
                            
                            statusLabel.setText("Categoría encontrada. Modifique los campos o manten los mismos valores (o deje en blanco).");
                        } 
                        else 
                        {
                            statusLabel.setText("No se encontró una categoría con el ID: " + id);
                            
                            nombreTextField.setText("");
                            
                            nombreTextField.setEnabled(false);
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
                        Categoria categoriaExistente = categoriaController.findById(categoriaId[0]);
                    
                        // Obtener valores de los campos
                        String nombre = nombreTextField.getText();
                        
                        // Usar valores existentes si los campos están vacíos
                        if (nombre.isEmpty()) {
                            nombre = categoriaExistente.getNombre();
                        }
                        
                        // Crear y guardar categoría actualizada
                        Categoria categoriaActualizada = new Categoria(categoriaId[0], nombre);
                        
                        categoriaController.save(categoriaActualizada);
                        
                        statusLabel.setText("Categoría actualizada correctamente.");
                    } catch (Exception ex) {
                        statusLabel.setText("Error al actualizar la categoría: " + ex.getMessage());
                    }
                }
            });
            
            volverButton.addActionListener(e -> {
                frame.dispose();
                MenuCategoria();
            });
        }
        catch(Exception e)
        {
            System.err.println("Error al actualizar la categoría: " + e.getMessage());
        }
    }
    else
    {
        System.out.println("Error: No hay conexion a la base de datos");
    }
  }

  private void MenuProducto()
  {
    JFrame frame = new JFrame("Menu Producto");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== GESTIÓN DE PRODUCTOS ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    JButton ListarProductosBtn = new JButton("Ver todos los productos");
    ListarProductosBtn.setBounds(100, 100, 200, 50);
    frame.add(ListarProductosBtn);
    ListarProductosBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            ListarProductos();
        }
    });
    
    JButton BuscarProductoBtn = new JButton("Buscar producto por ID");
    BuscarProductoBtn.setBounds(100, 150, 200, 50);
    frame.add(BuscarProductoBtn);
    BuscarProductoBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            BuscarProducto();
        }
    });
    
    JButton AñadirProductoBtn = new JButton("Añadir nuevo producto");
    AñadirProductoBtn.setBounds(100, 200, 200, 50);
    frame.add(AñadirProductoBtn);
    AñadirProductoBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            AñadirProducto();
        }
    });

    JButton ActualizarProductoBtn = new JButton("Actualizar producto");
    ActualizarProductoBtn.setBounds(100, 250, 200, 50);
    frame.add(ActualizarProductoBtn);
    ActualizarProductoBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            ActualizarProducto();
        }
    });
    
    JButton EliminarProductoBtn = new JButton("Eliminar producto");
    EliminarProductoBtn.setBounds(100, 300, 200, 50);
    frame.add(EliminarProductoBtn);
    EliminarProductoBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            EliminarProducto();
        }
    });

    JButton VolverBtn = new JButton("Volver al menu principal");
    VolverBtn.setBounds(100, 350, 200, 50);
    frame.add(VolverBtn);
    VolverBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new MenuProyecto();
        }
    });
  }

  private void ListarProductos()
  {
    JFrame frame = new JFrame("Listar Productos");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== LISTA DE PRODUCTOS ===");
    Title.setBounds(300, 10, 200, 50);
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
                    panelProductos.add(productoTextArea);
                }
            }
            else
            {
                JLabel noProductosLabel = new JLabel("No hay productos registrados en el sistema");
                noProductosLabel.setBounds(300, 200, 300, 30);
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
    frame.add(volverButton);
    volverButton.addActionListener(e -> {
        frame.dispose();
        MenuProducto();
    });
  }

  private void BuscarProducto()
  {
    JFrame frame = new JFrame("Buscar Producto");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== BUSCAR PRODUCTO ===");
    Title.setBounds(300, 10, 200, 50);
    frame.add(Title);
    if(productoController!=null)
    {
        try
        {
          JLabel idLabel = new JLabel("Introduce el ID del producto:");
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
          
          final JTextArea productoTextArea = new JTextArea();
          productoTextArea.setBounds(100, 190, 600, 180);
          productoTextArea.setEditable(false);
          productoTextArea.setBackground(new Color(240, 240, 240));
          productoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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
          frame.add(volverButton);
          volverButton.addActionListener(e -> {
              frame.dispose();
              MenuProducto();
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
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== AÑADIR NUEVO PRODUCTO ===");
    Title.setBounds(300, 10, 300, 50);
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
            frame.add(guardarButton);
            guardarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
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
                        
                        // Crear y guardar el producto si todas las validaciones son correctas
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
                        
                        // Limpiar los campos después de guardar
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
            frame.add(volverButton);
            volverButton.addActionListener(e -> {
                frame.dispose();
                MenuProducto();
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
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProducto();
        });
    }
  }

  private void ActualizarProducto()
  {
    JFrame frame = new JFrame("Actualizar Producto");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== ACTUALIZAR PRODUCTO ===");
    Title.setBounds(300, 10, 300, 50);
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
            frame.add(verProveedoresButton);
            
            final JButton guardarButton = new JButton("Guardar Cambios");
            guardarButton.setBounds(100, 450, 200, 30);
            guardarButton.setEnabled(false);
            frame.add(guardarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(350, 450, 200, 30);
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
                MenuProducto();
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
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProducto();
        });
    }
  }

  private void EliminarProducto()
  {
    JFrame frame = new JFrame("Eliminar Producto");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== ELIMINAR PRODUCTO ===");
    Title.setBounds(300, 10, 300, 50);
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
            frame.add(buscarButton);
            
            final JButton eliminarButton = new JButton("Eliminar Producto");
            eliminarButton.setBounds(200, 400, 200, 30);
            eliminarButton.setEnabled(false);
            frame.add(eliminarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(420, 400, 200, 30);
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
                MenuProducto();
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
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProducto();
        });
    }
  }

  private void MenuProvee()
  {
    JFrame frame = new JFrame("Menu Provee");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== GESTIÓN DE PROVISIONES ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    JButton ListarProveesBtn = new JButton("Ver todas las provisiones");
    ListarProveesBtn.setBounds(100, 100, 200, 50);
    frame.add(ListarProveesBtn);
    ListarProveesBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            ListarProvees();
        }
    });
    
    JButton BuscarProveeBtn = new JButton("Buscar provisión por ID");
    BuscarProveeBtn.setBounds(100, 150, 200, 50);
    frame.add(BuscarProveeBtn);
    BuscarProveeBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            BuscarProvee();
        }
    });
    
    JButton AñadirProveeBtn = new JButton("Añadir nueva provisión");
    AñadirProveeBtn.setBounds(100, 200, 200, 50);
    frame.add(AñadirProveeBtn);
    AñadirProveeBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            AñadirProvee();
        }
    });

    JButton ActualizarProveeBtn = new JButton("Actualizar provisión");
    ActualizarProveeBtn.setBounds(100, 250, 200, 50);
    frame.add(ActualizarProveeBtn);
    ActualizarProveeBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            ActualizarProvee();
        }
    });
    
    JButton EliminarProveeBtn = new JButton("Eliminar provisión");
    EliminarProveeBtn.setBounds(100, 300, 200, 50);
    frame.add(EliminarProveeBtn);
    EliminarProveeBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            EliminarProvee();
        }
    });

    JButton VolverBtn = new JButton("Volver al menu principal");
    VolverBtn.setBounds(100, 350, 200, 50);
    frame.add(VolverBtn);
    VolverBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new MenuProyecto();
        }
    });
  }

  private void ListarProvees()
  {
    JFrame frame = new JFrame("Listar Provisiones");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== LISTA DE PROVISIONES ===");
    Title.setBounds(300, 10, 200, 50);
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
                    panelProvees.add(proveeTextArea);
                }
            }
            else
            {
                JLabel noProveesLabel = new JLabel("No hay provisiones registradas en el sistema");
                noProveesLabel.setBounds(300, 200, 300, 30);
                frame.add(noProveesLabel);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error al obtener las provisiones: " + e.getMessage());
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
        MenuProvee();
    });
  }

  private void BuscarProvee()
  {
    JFrame frame = new JFrame("Buscar Provisión");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== BUSCAR PROVISIÓN ===");
    Title.setBounds(300, 10, 200, 50);
    frame.add(Title);
    if(proveeController!=null)
    {
        try
        {
          JLabel idLabel = new JLabel("Introduce el ID de la provisión:");
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
          
          final JTextArea proveeTextArea = new JTextArea();
          proveeTextArea.setBounds(100, 190, 600, 180);
          proveeTextArea.setEditable(false);
          proveeTextArea.setBackground(new Color(240, 240, 240));
          proveeTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
          proveeTextArea.setVisible(false);
          frame.add(proveeTextArea);
          
          buscarButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String input = idTextField.getText();
                  try
                  {
                      long id = Long.parseLong(input);
                      statusLabel.setText("Buscando provisión...");
                      
                      Provee provee = proveeController.findById(id);
                      if(provee!=null)
                      {
                          statusLabel.setText("Provisión encontrada:");
                          proveeTextArea.setText(provee.toString());
                          proveeTextArea.setVisible(true);
                          frame.repaint();
                      }
                      else
                      {
                          statusLabel.setText("Provisión no encontrada");
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
          frame.add(volverButton);
          volverButton.addActionListener(e -> {
              frame.dispose();
              MenuProvee();
          });
        }
        catch(Exception e)
        {
            System.err.println("Error al buscar la provisión: " + e.getMessage());
        }
    }
    else
    {
        System.out.println("Error: No hay conexion a la base de datos");
    }
  }

  private void AñadirProvee()
  {
    JFrame frame = new JFrame("Añadir Provisión");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== AÑADIR NUEVA PROVISIÓN ===");
    Title.setBounds(300, 10, 300, 50);
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
            frame.add(guardarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(420, 370, 200, 30);
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
                        
                        statusLabel.setText("Provisión guardada correctamente");
                        
                        proveedorTextField.setText("");
                        productoTextField.setText("");
                        cantidadTextField.setText("");
                        precioTextField.setText("");
                        fechaTextField.setText("");
                        
                    } catch (Exception ex) {
                        statusLabel.setText("Error al guardar la provisión: " + ex.getMessage());
                    }
                }
            });
            
            volverButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MenuProvee();
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
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProvee();
        });
    }
  }

  private void ActualizarProvee()
  {
    JFrame frame = new JFrame("Actualizar Provisión");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== ACTUALIZAR PROVISIÓN ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    if(proveeController!=null && productoController!=null && proveedorController!=null)
    {
        try
        {
            JLabel idLabel = new JLabel("ID de la provisión a actualizar:");
            idLabel.setBounds(100, 70, 200, 30);
            frame.add(idLabel);
            
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 70, 200, 30);
            frame.add(idTextField);
            
            final JButton buscarButton = new JButton("Buscar Provisión");
            buscarButton.setBounds(520, 70, 150, 30);
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
            verProveedoresButton.setEnabled(false);
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
            verProductosButton.setEnabled(false);
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
            guardarButton.setEnabled(false);
            frame.add(guardarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(350, 360, 200, 30);
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
                        
                        statusLabel.setText("Buscando provisión...");
                        
                        Provee provee = proveeController.findById(id);
                        if (provee != null) {
                            proveeId[0] = provee.getID_Provee();
                            
                            proveedorTextField.setText(String.valueOf(provee.getProveedor().getID_Proveedor()));
                            productoTextField.setText(String.valueOf(provee.getProducto().getID_Producto()));
                            cantidadTextField.setText(String.valueOf(provee.getCantidad()));
                            precioTextField.setText(String.valueOf(provee.getPrecio()));
                            fechaTextField.setText(provee.getFecha_Provision() != null ? 
                                provee.getFecha_Provision().toString() : "");
                            
                            statusLabel.setText("Provisión encontrada. Modifique los campos o manten los mismos valores (o deje en blanco).");
                            
                            proveedorTextField.setEnabled(true);
                            productoTextField.setEnabled(true);
                            cantidadTextField.setEnabled(true);
                            precioTextField.setEnabled(true);
                            fechaTextField.setEnabled(true);
                            verProveedoresButton.setEnabled(true);
                            verProductosButton.setEnabled(true);
                            guardarButton.setEnabled(true);
                        } else {
                            statusLabel.setText("No se encontró una provisión con el ID: " + id);
                            
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
                        statusLabel.setText("Error al buscar la provisión: " + ex.getMessage());
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
                        
                        statusLabel.setText("Provisión actualizada correctamente");
                        
                    } catch (NumberFormatException nfe) {
                        statusLabel.setText("Error: Asegúrate de que los IDs, cantidad y precio son valores numéricos");
                    } catch (DateTimeParseException dtpe) {
                        statusLabel.setText("Error: El formato de fecha debe ser YYYY-MM-DD");
                    } catch (Exception ex) {
                        statusLabel.setText("Error al actualizar la provisión: " + ex.getMessage());
                    }
                }
            });
            
            volverButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MenuProvee();
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
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProvee();
        });
    }
  }

  private void EliminarProvee()
  {
    JFrame frame = new JFrame("Eliminar Provisión");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== ELIMINAR PROVISIÓN ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    if(proveeController!=null)
    {
        try
        {
            JLabel idLabel = new JLabel("ID de la provisión a eliminar:");
            idLabel.setBounds(100, 100, 200, 30);
            frame.add(idLabel);
            
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 100, 200, 30);
            frame.add(idTextField);
            
            final JLabel infoLabel = new JLabel("Información de la provisión:");
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
            
            final JButton buscarButton = new JButton("Buscar Provisión");
            buscarButton.setBounds(550, 100, 150, 30);
            frame.add(buscarButton);
            
            final JButton eliminarButton = new JButton("Eliminar Provisión");
            eliminarButton.setBounds(200, 400, 200, 30);
            eliminarButton.setEnabled(false);
            frame.add(eliminarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(420, 400, 200, 30);
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
                            statusLabel.setText("Provisión encontrada. Pulse 'Eliminar Provisión' para confirmar.");
                        } 
                        else 
                        {
                            proveeInfo.setText("");
                            eliminarButton.setEnabled(false);
                            statusLabel.setText("No se encontró una provisión con el ID: " + id);
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
                            statusLabel.setText("Provisión eliminada correctamente.");
                        }
                    } 
                    catch (Exception ex) 
                    {
                        statusLabel.setText("Error al eliminar la provisión: " + ex.getMessage());
                    }
                }
            });
            
            volverButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    frame.dispose();
                    MenuProvee();
                }
            });
        }
        catch(Exception e)
        {
            System.err.println("Error al eliminar la provisión: " + e.getMessage());
        }
    }
    else
    {
        JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
        errorLabel.setBounds(100, 100, 500, 30);
        frame.add(errorLabel);
        
        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(300, 500, 200, 30);
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProvee();
        });
    }
  }

  private void MenuProveedor()
  {
    JFrame frame = new JFrame("Menu Proveedor");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== GESTIÓN DE PROVEEDORES ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    JButton ListarProveedoresBtn = new JButton("Ver todos los proveedores");
    ListarProveedoresBtn.setBounds(100, 100, 200, 50);
    frame.add(ListarProveedoresBtn);
    ListarProveedoresBtn.addActionListener(e -> {
        frame.dispose();
        ListarProveedores();
    });
    
    JButton BuscarProveedorBtn = new JButton("Buscar proveedor por ID");
    BuscarProveedorBtn.setBounds(100, 150, 200, 50);
    frame.add(BuscarProveedorBtn);
    BuscarProveedorBtn.addActionListener(e -> {
        frame.dispose();
        BuscarProveedor();
    });
    
    JButton AñadirProveedorBtn = new JButton("Añadir nuevo proveedor");
    AñadirProveedorBtn.setBounds(100, 200, 200, 50);
    frame.add(AñadirProveedorBtn);
    AñadirProveedorBtn.addActionListener(e -> {
        frame.dispose();
        AñadirProveedor();
    });

    JButton ActualizarProveedorBtn = new JButton("Actualizar proveedor");
    ActualizarProveedorBtn.setBounds(100, 250, 200, 50);
    frame.add(ActualizarProveedorBtn);
    ActualizarProveedorBtn.addActionListener(e -> {
        frame.dispose();
        ActualizarProveedor();
    });
    
    JButton EliminarProveedorBtn = new JButton("Eliminar proveedor");
    EliminarProveedorBtn.setBounds(100, 300, 200, 50);
    frame.add(EliminarProveedorBtn);
    EliminarProveedorBtn.addActionListener(e -> {
        frame.dispose();
        EliminarProveedor();
    });

    JButton VolverBtn = new JButton("Volver al menu principal");
    VolverBtn.setBounds(100, 350, 200, 50);
    frame.add(VolverBtn);
    VolverBtn.addActionListener(e -> {
        frame.dispose();
        new MenuProyecto();
    });
  }

  private void ListarProveedores()
  {
    JFrame frame = new JFrame("Listar Proveedores");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== LISTA DE PROVEEDORES ===");
    Title.setBounds(300, 10, 200, 50);
    frame.add(Title);

    if(proveedorController!=null)
    {
        try
        {
            List<Proveedor> proveedores = proveedorController.findAll();
            if(proveedores!=null)
            {
                JPanel panelProveedores = new JPanel();
                panelProveedores.setLayout(null);
                
                JScrollPane scrollPane = new JScrollPane(panelProveedores);
                scrollPane.setBounds(50, 70, 700, 400);
                frame.add(scrollPane);
                
                panelProveedores.setPreferredSize(new Dimension(680, Math.max(380, proveedores.size() * 150)));
                
                for(int i = 0; i < proveedores.size(); i++)
                {
                    JTextArea proveedorTextArea = new JTextArea(proveedores.get(i).toString());
                    proveedorTextArea.setBounds(50, 10 + i * 150, 600, 150);
                    proveedorTextArea.setEditable(false);
                    proveedorTextArea.setBackground(new Color(240, 240, 240));
                    proveedorTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    panelProveedores.add(proveedorTextArea);
                }
            }
            else
            {
                JLabel noProveedoresLabel = new JLabel("No hay proveedores registrados en el sistema");
                noProveedoresLabel.setBounds(300, 200, 300, 30);
                frame.add(noProveedoresLabel);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error al obtener los proveedores: " + e.getMessage());
        } 
    }
    else
    {
        System.out.println("Error: No hay conexión a la base de datos");
    }

    JButton volverButton = new JButton("Volver");
    volverButton.setBounds(300, 500, 200, 30);
    frame.add(volverButton);
    volverButton.addActionListener(e -> {
        frame.dispose();
        MenuProveedor();
    });
  }

  private void BuscarProveedor()
  {
    JFrame frame = new JFrame("Buscar Proveedor");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== BUSCAR PROVEEDOR ===");
    Title.setBounds(300, 10, 200, 50);
    frame.add(Title);

    if(proveedorController!=null)
    {
        try
        {
            JLabel idLabel = new JLabel("ID del proveedor:");
            idLabel.setBounds(100, 100, 150, 30);
            frame.add(idLabel);

            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(260, 100, 200, 30);
            frame.add(idTextField);
            
            JButton buscarButton = new JButton("Buscar");
            buscarButton.setBounds(480, 100, 100, 30);
            frame.add(buscarButton);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 150, 600, 30);
            frame.add(statusLabel);
            
            final JTextArea proveedorTextArea = new JTextArea();
            proveedorTextArea.setBounds(100, 190, 600, 200);
            proveedorTextArea.setEditable(false);
            proveedorTextArea.setBackground(new Color(240, 240, 240));
            proveedorTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            proveedorTextArea.setVisible(false);
            frame.add(proveedorTextArea);
            
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
                        proveedorTextArea.setText(proveedor.toString());
                        proveedorTextArea.setVisible(true);
                        frame.repaint();
                    }
                    else
                    {
                        statusLabel.setText("Proveedor no encontrado");
                        proveedorTextArea.setVisible(false);
                        frame.repaint();
                    }
                }
                catch(NumberFormatException nfe)
                {
                    statusLabel.setText("ID inválido. Introduce un número válido.");
                    proveedorTextArea.setVisible(false);
                    frame.repaint();
                }
            });
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(300, 420, 200, 30);
            frame.add(volverButton);
            volverButton.addActionListener(e -> {
                frame.dispose();
                MenuProveedor();
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
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== AÑADIR NUEVO PROVEEDOR ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    if(proveedorController!=null)
    {
        try
        {
            JLabel cifLabel = new JLabel("CIF (1 letra + 8 números):");
            cifLabel.setBounds(100, 70, 200, 30);
            frame.add(cifLabel);
            
            JTextField cifTextField = new JTextField(10);
            cifTextField.setBounds(350, 70, 200, 30);
            frame.add(cifTextField);
            
            JLabel nombreLabel = new JLabel("Nombre de la empresa:");
            nombreLabel.setBounds(100, 110, 200, 30);
            frame.add(nombreLabel);
            
            JTextField nombreTextField = new JTextField(10);
            nombreTextField.setBounds(350, 110, 200, 30);
            frame.add(nombreTextField);
            
            JLabel responsableLabel = new JLabel("Nombre del responsable:");
            responsableLabel.setBounds(100, 150, 200, 30);
            frame.add(responsableLabel);
            
            JTextField responsableTextField = new JTextField(10);
            responsableTextField.setBounds(350, 150, 200, 30);
            frame.add(responsableTextField);
            
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
            
            JLabel telefonoLabel = new JLabel("Teléfono (9 dígitos):");
            telefonoLabel.setBounds(100, 350, 200, 30);
            frame.add(telefonoLabel);
            
            JTextField telefonoTextField = new JTextField(10);
            telefonoTextField.setBounds(350, 350, 200, 30);
            frame.add(telefonoTextField);
            
            JLabel cpLabel = new JLabel("Código Postal (5 dígitos):");
            cpLabel.setBounds(100, 390, 200, 30);
            frame.add(cpLabel);
            
            JTextField cpTextField = new JTextField(10);
            cpTextField.setBounds(350, 390, 200, 30);
            frame.add(cpTextField);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 430, 600, 30);
            frame.add(statusLabel);
            
            JButton guardarButton = new JButton("Guardar Proveedor");
            guardarButton.setBounds(200, 470, 200, 30);
            frame.add(guardarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(420, 470, 200, 30);
            frame.add(volverButton);
            
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
                MenuProveedor();
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
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProveedor();
        });
    }
  }

  private void ActualizarProveedor()
  {
    JFrame frame = new JFrame("Actualizar Proveedor");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== ACTUALIZAR PROVEEDOR ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    if(proveedorController!=null)
    {
        try
        {
            JLabel idLabel = new JLabel("ID del proveedor a actualizar:");
            idLabel.setBounds(100, 70, 200, 30);
            frame.add(idLabel);
            
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 70, 200, 30);
            frame.add(idTextField);
            
            final JButton buscarButton = new JButton("Buscar Proveedor");
            buscarButton.setBounds(520, 70, 150, 30);
            frame.add(buscarButton);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 110, 600, 30);
            frame.add(statusLabel);
            
            JLabel cifLabel = new JLabel("CIF:");
            cifLabel.setBounds(100, 150, 200, 30);
            frame.add(cifLabel);
            
            final JTextField cifTextField = new JTextField(10);
            cifTextField.setBounds(300, 150, 200, 30);
            cifTextField.setEnabled(false);
            frame.add(cifTextField);
            
            JLabel nombreLabel = new JLabel("Nombre de la empresa:");
            nombreLabel.setBounds(100, 190, 200, 30);
            frame.add(nombreLabel);
            
            final JTextField nombreTextField = new JTextField(10);
            nombreTextField.setBounds(300, 190, 200, 30);
            nombreTextField.setEnabled(false);
            frame.add(nombreTextField);
            
            JLabel responsableLabel = new JLabel("Nombre del responsable:");
            responsableLabel.setBounds(100, 230, 200, 30);
            frame.add(responsableLabel);
            
            final JTextField responsableTextField = new JTextField(10);
            responsableTextField.setBounds(300, 230, 200, 30);
            responsableTextField.setEnabled(false);
            frame.add(responsableTextField);
            
            JLabel paisLabel = new JLabel("País:");
            paisLabel.setBounds(100, 270, 200, 30);
            frame.add(paisLabel);
            
            final JTextField paisTextField = new JTextField(10);
            paisTextField.setBounds(300, 270, 200, 30);
            paisTextField.setEnabled(false);
            frame.add(paisTextField);
            
            JLabel provinciaLabel = new JLabel("Provincia:");
            provinciaLabel.setBounds(100, 310, 200, 30);
            frame.add(provinciaLabel);
            
            final JTextField provinciaTextField = new JTextField(10);
            provinciaTextField.setBounds(300, 310, 200, 30);
            provinciaTextField.setEnabled(false);
            frame.add(provinciaTextField);
            
            JLabel direccionLabel = new JLabel("Dirección:");
            direccionLabel.setBounds(100, 350, 200, 30);
            frame.add(direccionLabel);
            
            final JTextField direccionTextField = new JTextField(10);
            direccionTextField.setBounds(300, 350, 200, 30);
            direccionTextField.setEnabled(false);
            frame.add(direccionTextField);
            
            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setBounds(100, 390, 200, 30);
            frame.add(emailLabel);
            
            final JTextField emailTextField = new JTextField(10);
            emailTextField.setBounds(300, 390, 200, 30);
            emailTextField.setEnabled(false);
            frame.add(emailTextField);
            
            JLabel telefonoLabel = new JLabel("Teléfono:");
            telefonoLabel.setBounds(100, 430, 200, 30);
            frame.add(telefonoLabel);
            
            final JTextField telefonoTextField = new JTextField(10);
            telefonoTextField.setBounds(300, 430, 200, 30);
            telefonoTextField.setEnabled(false);
            frame.add(telefonoTextField);
            
            JLabel cpLabel = new JLabel("Código Postal:");
            cpLabel.setBounds(100, 470, 200, 30);
            frame.add(cpLabel);
            
            final JTextField cpTextField = new JTextField(10);
            cpTextField.setBounds(300, 470, 200, 30);
            cpTextField.setEnabled(false);
            frame.add(cpTextField);
            
            final JButton guardarButton = new JButton("Guardar Cambios");
            guardarButton.setBounds(100, 510, 200, 30);
            guardarButton.setEnabled(false);
            frame.add(guardarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(350, 510, 200, 30);
            frame.add(volverButton);
            
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
                        
                        statusLabel.setText("Proveedor encontrado. Modifique los campos o manten los mismos valores (o deje en blanco).");
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
                MenuProveedor();
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
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProveedor();
        });
    }
  }

  private void EliminarProveedor()
  {
    JFrame frame = new JFrame("Eliminar Proveedor");
    frame.setLayout(null);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setVisible(true);

    JLabel Title = new JLabel("\n=== ELIMINAR PROVEEDOR ===");
    Title.setBounds(300, 10, 300, 50);
    frame.add(Title);

    if(proveedorController!=null)
    {
        try
        {
            JLabel idLabel = new JLabel("ID del proveedor a eliminar:");
            idLabel.setBounds(100, 100, 200, 30);
            frame.add(idLabel);
            
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 100, 200, 30);
            frame.add(idTextField);
            
            final JLabel infoLabel = new JLabel("Información del proveedor:");
            infoLabel.setBounds(100, 150, 500, 30);
            frame.add(infoLabel);
            
            final JTextArea proveedorInfo = new JTextArea();
            proveedorInfo.setBounds(100, 190, 600, 150);
            proveedorInfo.setEditable(false);
            proveedorInfo.setBackground(new Color(240, 240, 240));
            proveedorInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            frame.add(proveedorInfo);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 350, 600, 30);
            frame.add(statusLabel);
            
            final JButton buscarButton = new JButton("Buscar Proveedor");
            buscarButton.setBounds(550, 100, 150, 30);
            frame.add(buscarButton);
            
            final JButton eliminarButton = new JButton("Eliminar Proveedor");
            eliminarButton.setBounds(200, 400, 200, 30);
            eliminarButton.setEnabled(false);
            frame.add(eliminarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(420, 400, 200, 30);
            frame.add(volverButton);
            
            final Long[] proveedorId = new Long[1];
            
            buscarButton.addActionListener(e -> {
                try {
                    String input = idTextField.getText();
                    long id = Long.parseLong(input);
                    
                    Proveedor proveedor = proveedorController.findById(id);
                    if (proveedor != null) {
                        proveedorId[0] = proveedor.getID_Proveedor();
                        proveedorInfo.setText(proveedor.toString());
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
                MenuProveedor();
            });
        }
        catch(Exception e)
        {
            System.err.println("Error al eliminar el proveedor: " + e.getMessage());
        }
    }
    else
    {
        JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
        errorLabel.setBounds(100, 100, 500, 30);
        frame.add(errorLabel);
        
        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(300, 500, 200, 30);
        frame.add(volverButton);
        volverButton.addActionListener(e -> {
            frame.dispose();
            MenuProveedor();
        });
    }
  }
}
