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
        setLayout(null);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menu Proveedor");

        JLabel Title = new JLabel("\n=== GESTIÓN DE PROVEEDORES ===");
        Title.setBounds(300, 10, 300, 50);
        add(Title);

        JButton ListarProveedoresBtn = new JButton("Ver todos los proveedores");
        ListarProveedoresBtn.setBounds(100, 100, 200, 50);
        add(ListarProveedoresBtn);
        ListarProveedoresBtn.addActionListener(e -> {
            dispose();
            ListarProveedores();
        });
        
        JButton BuscarProveedorBtn = new JButton("Buscar proveedor por ID");
        BuscarProveedorBtn.setBounds(100, 150, 200, 50);
        add(BuscarProveedorBtn);
        BuscarProveedorBtn.addActionListener(e -> {
            dispose();
            BuscarProveedor();
        });
        
        JButton AñadirProveedorBtn = new JButton("Añadir nuevo proveedor");
        AñadirProveedorBtn.setBounds(100, 200, 200, 50);
        add(AñadirProveedorBtn);
        AñadirProveedorBtn.addActionListener(e -> {
            dispose();
            AñadirProveedor();
        });

        JButton ActualizarProveedorBtn = new JButton("Actualizar proveedor");
        ActualizarProveedorBtn.setBounds(100, 250, 200, 50);
        add(ActualizarProveedorBtn);
        ActualizarProveedorBtn.addActionListener(e -> {
            dispose();
            ActualizarProveedor();
        });
        
        JButton EliminarProveedorBtn = new JButton("Eliminar proveedor");
        EliminarProveedorBtn.setBounds(100, 300, 200, 50);
        add(EliminarProveedorBtn);
        EliminarProveedorBtn.addActionListener(e -> {
            dispose();
            EliminarProveedor();
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
          new MenuProveedor(proveedorController);
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
                  new MenuProveedor(proveedorController);
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
              new MenuProveedor(proveedorController);
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
                  new MenuProveedor(proveedorController);
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
              new MenuProveedor(proveedorController);
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
          JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
          errorLabel.setBounds(100, 100, 500, 30);
          frame.add(errorLabel);
          
          JButton volverButton = new JButton("Volver");
          volverButton.setBounds(300, 500, 200, 30);
          frame.add(volverButton);
          volverButton.addActionListener(e -> {
              frame.dispose();
              new MenuProveedor(proveedorController);
          });
      }
    }
} 