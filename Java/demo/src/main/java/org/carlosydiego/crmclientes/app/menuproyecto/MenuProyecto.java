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

public class MenuProyecto extends JFrame implements ActionListener
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

   public MenuProyecto()
   {
    inicializarControladores();
    setVisible(true);
    setTitle("Menu Proyecto");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JLabel Title = new JLabel("\n=== SISTEMA CRM CLIENTES ===");
    Title.setBounds(300, 10, 200, 50);
    add(Title);


    Clientes = new JButton("Gestion de Clientes");
    Clientes.setBounds(100, 100, 200, 50);
    add(Clientes);
    Clientes.addActionListener(this);

    Empleados = new JButton("Gestion de Empleados");
    Empleados.setBounds(100, 150, 200, 50);
    add(Empleados);
    Empleados.addActionListener(this);

    Productos = new JButton("Gestion de Productos");
    Productos.setBounds(100, 200, 200, 50);
    add(Productos);
    Productos.addActionListener(this);

    Proveedores = new JButton("Gestion de Proveedores");
    Proveedores.setBounds(100, 250, 200, 50);
    add(Proveedores);
    Proveedores.addActionListener(this);

    Facturas = new JButton("Gestion de Facturas");
    Facturas.setBounds(100, 300, 200, 50);
    add(Facturas);
    Facturas.addActionListener(this);

    Categorias = new JButton("Gestion de Categorias");
    Categorias.setBounds(100, 350, 200, 50);
    add(Categorias);
    Categorias.addActionListener(this);

    Provees = new JButton("Gestion de Provees");
    Provees.setBounds(100, 400, 200, 50);
    add(Provees);
    Provees.addActionListener(this);

    Salir = new JButton("Salir");
    Salir.setBounds(100, 450, 200, 50);
    add(Salir);
    Salir.addActionListener(this);


   }

  private void MenuCliente()
  {


    JFrame frame = new JFrame("Menu Cliente");
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
    BuscarCliente.addActionListener(this);
    
    AñadirCliente = new JButton("Añadir nuevo cliente");
    AñadirCliente.setBounds(100, 200, 200, 50);
    frame.add(AñadirCliente);
    AñadirCliente.addActionListener(this);

    ModificarCliente = new JButton("Actualizar cliente");
    ModificarCliente.setBounds(100, 200, 200, 50);
    frame.add(ModificarCliente);
    ModificarCliente.addActionListener(this);
    
    EliminarCliente = new JButton("Eliminar cliente");
    EliminarCliente.setBounds(100, 250, 200, 50);
    frame.add(EliminarCliente);
    EliminarCliente.addActionListener(this);

    VolverMenu = new JButton("Volver al menu principal");
    VolverMenu.setBounds(100, 250, 200, 50);
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
                for(int i = 0; i < clientes.size(); i++)
                {
                    JLabel clienteLabel = new JLabel(clientes.get(i).toString());
                    clienteLabel.setBounds(100, 100 + i * 50, 200, 50);
                    frame.add(clienteLabel);
                    
                }
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

  }

  private void BuscarCliente()
  {
    JFrame frame = new JFrame("Buscar Cliente");
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
          idLabel.setBounds(100, 100, 200, 50);
          frame.add(idLabel);

          JTextField idTextField = new JTextField(10);
          idTextField.setBounds(100, 150, 200, 50);
          frame.add(idTextField);
          
          final JLabel clienteLabel = new JLabel("Buscando cliente...");
          clienteLabel.setBounds(100, 300, 200, 50);
          frame.add(clienteLabel);

          JButton buscarButton = new JButton("Buscar");
          buscarButton.setBounds(100, 200, 200, 50);
          frame.add(buscarButton);
          buscarButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String input = idTextField.getText();
                  try
                  {
                      long id = Long.parseLong(input);
                      boolean idValido = true;
                      
                      clienteLabel.setText("Buscando cliente...");
                      
                      Cliente cliente = clienteController.findById(id);
                      if(cliente!=null)
                      {
                          clienteLabel.setText("Cliente encontrado: ");
                          JLabel clienteInfo = new JLabel(cliente.toString());
                          clienteInfo.setBounds(100, 350, 200, 50);
                          frame.add(clienteInfo);
                          frame.repaint();
                      }
                      else
                      {
                          clienteLabel.setText("Cliente no encontrado");
                      }
                  }
                  catch(NumberFormatException nfe)
                  {
                      JLabel errorLabel = new JLabel("ID invalido. Introduce un numero valido.");
                      errorLabel.setBounds(100, 250, 200, 50);
                      frame.add(errorLabel);
                      frame.repaint();
                  }
              }
          });
          
          JButton volverButton = new JButton("Volver");
          volverButton.setBounds(100, 300, 200, 50);
          frame.add(volverButton);
          volverButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  frame.dispose();
                  BuscarCliente();
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
            idTextField.setBounds(350, 70, 200, 30);
            frame.add(idTextField);
            
        
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 470, 400, 30);
            frame.add(statusLabel);
            
            
            JLabel cifLabel = new JLabel("CIF:");
            cifLabel.setBounds(100, 110, 200, 30);
            frame.add(cifLabel);
            
            final JTextField cifTextField = new JTextField(10);
            cifTextField.setBounds(350, 110, 200, 30);
            cifTextField.setEnabled(false);
            frame.add(cifTextField);
            
            JLabel nombreEmpresaLabel = new JLabel("Nombre de la Empresa:");
            nombreEmpresaLabel.setBounds(100, 150, 200, 30);
            frame.add(nombreEmpresaLabel);
            
            final JTextField nombreEmpresaTextField = new JTextField(10);
            nombreEmpresaTextField.setBounds(350, 150, 200, 30);
            nombreEmpresaTextField.setEnabled(false);
            frame.add(nombreEmpresaTextField);
            
            JLabel nombreResponsableLabel = new JLabel("Nombre del Responsable:");
            nombreResponsableLabel.setBounds(100, 190, 200, 30);
            frame.add(nombreResponsableLabel);
            
            final JTextField nombreResponsableTextField = new JTextField(10);
            nombreResponsableTextField.setBounds(350, 190, 200, 30);
            nombreResponsableTextField.setEnabled(false);
            frame.add(nombreResponsableTextField);
            
            JLabel paisLabel = new JLabel("País:");
            paisLabel.setBounds(100, 230, 200, 30);
            frame.add(paisLabel);
            
            final JTextField paisTextField = new JTextField(10);
            paisTextField.setBounds(350, 230, 200, 30);
            paisTextField.setEnabled(false);
            frame.add(paisTextField);
            
            JLabel provinciaLabel = new JLabel("Provincia:");
            provinciaLabel.setBounds(100, 270, 200, 30);
            frame.add(provinciaLabel);
            
            final JTextField provinciaTextField = new JTextField(10);
            provinciaTextField.setBounds(350, 270, 200, 30);
            provinciaTextField.setEnabled(false);
            frame.add(provinciaTextField);
            
            JLabel direccionLabel = new JLabel("Dirección:");
            direccionLabel.setBounds(100, 310, 200, 30);
            frame.add(direccionLabel);
            
            final JTextField direccionTextField = new JTextField(10);
            direccionTextField.setBounds(350, 310, 200, 30);
            direccionTextField.setEnabled(false);
            frame.add(direccionTextField);
            
            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setBounds(100, 350, 200, 30);
            frame.add(emailLabel);
            
            final JTextField emailTextField = new JTextField(10);
            emailTextField.setBounds(350, 350, 200, 30);
            emailTextField.setEnabled(false);
            frame.add(emailTextField);
            
            JLabel telefonoLabel = new JLabel("Teléfono:");
            telefonoLabel.setBounds(100, 390, 200, 30);
            frame.add(telefonoLabel);
            
            final JTextField telefonoTextField = new JTextField(10);
            telefonoTextField.setBounds(350, 390, 200, 30);
            telefonoTextField.setEnabled(false);
            frame.add(telefonoTextField);
            
            JLabel cpLabel = new JLabel("Código Postal:");
            cpLabel.setBounds(100, 430, 200, 30);
            frame.add(cpLabel);
            
            final JTextField cpTextField = new JTextField(10);
            cpTextField.setBounds(350, 430, 200, 30);
            cpTextField.setEnabled(false);
            frame.add(cpTextField);
            
            // Botones
            final JButton buscarButton = new JButton("Buscar Cliente");
            buscarButton.setBounds(600, 70, 150, 30);
            frame.add(buscarButton);
            
            final JButton guardarButton = new JButton("Guardar Cambios");
            guardarButton.setBounds(100, 470, 200, 30);
            guardarButton.setEnabled(false);
            frame.add(guardarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(350, 470, 200, 30);
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
            frame.add(clienteInfo);
            
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 350, 400, 30);
            frame.add(statusLabel);
            
        
            final JButton buscarButton = new JButton("Buscar Cliente");
            buscarButton.setBounds(550, 100, 150, 30);
            frame.add(buscarButton);
            
            final JButton eliminarButton = new JButton("Eliminar Cliente");
            eliminarButton.setBounds(100, 300, 200, 30);
            eliminarButton.setEnabled(false);
            frame.add(eliminarButton);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setBounds(350, 300, 200, 30);
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

   public void actionPerformed(ActionEvent e)
   {
    if (e.getSource() == Clientes)
    {
        dispose();
        MenuCliente();
    }

    if (e.getSource() == Salir)
    {
        dispose();
    } 

    

    if (e.getSource() == VolverMenu)
    {
        dispose();
        new MenuProyecto();
    }
    if (e.getSource() == TodoCliente)
    {
        dispose();
        ListarClientes();
    }
    if (e.getSource() == BuscarCliente)
    {
        dispose();
        BuscarCliente();
    }
    if (e.getSource() == AñadirCliente)
    {
        dispose();
        AñadirCliente();
    }
    if (e.getSource() == ModificarCliente)
    {
        dispose();
        ActualizarCliente();
    }
    if (e.getSource() == EliminarCliente)
    {
        dispose();
        EliminarCliente();
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
}


