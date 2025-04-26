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


