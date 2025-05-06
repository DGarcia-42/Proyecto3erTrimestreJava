package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuCategoria extends JFrame {
    private CategoriaController categoriaController;

    public MenuCategoria(CategoriaController categoriaController) {
        this.categoriaController = categoriaController;
        initializeMenu();
    }

    private void initializeMenu() {
        setLayout(null);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menu Categoria");
        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel Title = new JLabel("\n=== GESTIÓN DE CATEGORÍAS ===");
        Title.setBounds(300, 10, 300, 50);
        Title.setFont(new Font("Roboto", Font.BOLD, 14));
        Title.setForeground(new Color(46, 46, 46));
        add(Title);

        JButton ListarCategoriasBtn = new JButton("Ver todas las categorías");
        ListarCategoriasBtn.setBounds(100, 100, 200, 50);
        ListarCategoriasBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        ListarCategoriasBtn.setBackground(new Color(0, 123, 255));
        ListarCategoriasBtn.setForeground(Color.WHITE);
        add(ListarCategoriasBtn);
        ListarCategoriasBtn.addActionListener(e -> {
            dispose();
            ListarCategorias();
        });
        
        JButton BuscarCategoriaBtn = new JButton("Buscar categoría por ID");
        BuscarCategoriaBtn.setBounds(100, 150, 200, 50);
        BuscarCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        BuscarCategoriaBtn.setBackground(new Color(0, 123, 255));
        BuscarCategoriaBtn.setForeground(Color.WHITE);
        add(BuscarCategoriaBtn);
        BuscarCategoriaBtn.addActionListener(e -> {
            dispose();
            BuscarCategoria();
        });
        
        JButton AñadirCategoriaBtn = new JButton("Añadir nueva categoría");
        AñadirCategoriaBtn.setBounds(100, 200, 200, 50);
        AñadirCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        AñadirCategoriaBtn.setBackground(new Color(76, 175, 80));
        AñadirCategoriaBtn.setForeground(Color.WHITE);
        add(AñadirCategoriaBtn);
        AñadirCategoriaBtn.addActionListener(e -> {
            dispose();
            AñadirCategoria();
        });

        JButton ActualizarCategoriaBtn = new JButton("Actualizar categoría");
        ActualizarCategoriaBtn.setBounds(100, 250, 200, 50);
        ActualizarCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        ActualizarCategoriaBtn.setBackground(new Color(0, 123, 255));
        ActualizarCategoriaBtn.setForeground(Color.WHITE);
        add(ActualizarCategoriaBtn);
        ActualizarCategoriaBtn.addActionListener(e -> {
            dispose();
            ActualizarCategoria();
        });
        
        JButton EliminarCategoriaBtn = new JButton("Eliminar categoría");
        EliminarCategoriaBtn.setBounds(100, 300, 200, 50);
        EliminarCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        EliminarCategoriaBtn.setBackground(new Color(0, 123, 255));
        EliminarCategoriaBtn.setForeground(Color.WHITE);
        add(EliminarCategoriaBtn);
        EliminarCategoriaBtn.addActionListener(e -> {
            dispose();
            EliminarCategoria();
        });

        JButton VolverBtn = new JButton("Volver al menu principal");
        VolverBtn.setBounds(100, 350, 200, 50);
        VolverBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        VolverBtn.setBackground(new Color(0, 123, 255));
        VolverBtn.setForeground(Color.WHITE);
        add(VolverBtn);
        VolverBtn.addActionListener(e -> {
            dispose();
            new MenuProyecto();
        });

        setVisible(true);
    }

    private void ListarCategorias()
    {
      JFrame frame = new JFrame("Listar Categorías");
      frame.setLayout(null);
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
 
      JLabel Title = new JLabel("\n=== LISTA DE CATEGORÍAS ===");
      Title.setBounds(300, 10, 200, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
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
      volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
      volverButton.setBackground(new Color(0, 123, 255));
      volverButton.setForeground(Color.WHITE);
      frame.add(volverButton);
      volverButton.addActionListener(e -> {
          frame.dispose();
          new MenuCategoria(categoriaController);
      });
    }
 
    private void AñadirCategoria()
    {
      JFrame frame = new JFrame("Añadir Categoría");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
 
      JLabel Title = new JLabel("\n=== AÑADIR NUEVA CATEGORÍA ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
 
      if(categoriaController!=null)
      {
          try
          {
          
              JLabel nombreLabel = new JLabel("Nombre:");
              nombreLabel.setBounds(100, 70, 200, 30);
              nombreLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(nombreLabel);
              
              JTextField nombreTextField = new JTextField(10);
              nombreTextField.setBounds(350, 70, 200, 30);
              nombreTextField.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(nombreTextField);
              
              
              JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 470, 400, 30);
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(statusLabel);
          
              JButton guardarButton = new JButton("Guardar Categoría");
              guardarButton.setBounds(100, 430, 200, 30);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
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
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
              frame.add(volverButton);
              volverButton.addActionListener(e -> {
                  frame.dispose();
                  new MenuCategoria(categoriaController);
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
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
 
      JLabel Title = new JLabel("\n=== ELIMINAR CATEGORÍA ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
 
      if(categoriaController!=null)
      {
          try
          {
              
              JLabel idLabel = new JLabel("ID de la categoría a eliminar:");
              idLabel.setBounds(100, 100, 200, 30);
              idLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(300, 100, 200, 30);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, 12));
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
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(statusLabel);
              
          
              final JButton buscarButton = new JButton("Buscar Categoría");
              buscarButton.setBounds(550, 100, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
              
              final JButton eliminarButton = new JButton("Eliminar Categoría");
              eliminarButton.setBounds(200, 400, 200, 30);
              eliminarButton.setEnabled(false);
              eliminarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              eliminarButton.setBackground(new Color(0, 123, 255));
              eliminarButton.setForeground(Color.WHITE);
              frame.add(eliminarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(420, 400, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
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
                  new MenuCategoria(categoriaController);
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
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
 
      JLabel Title = new JLabel("\n=== BUSCAR CATEGORÍA ===");
      Title.setBounds(300, 10, 200, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
      if(categoriaController!=null)
      {
          try
          {
            JLabel idLabel = new JLabel("Introduce el ID de la categoría:");
            idLabel.setBounds(100, 100, 200, 30);
            idLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            frame.add(idLabel);
 
            JTextField idTextField = new JTextField(10);
            idTextField.setBounds(300, 100, 200, 30);
            idTextField.setFont(new Font("Roboto", Font.PLAIN, 12));
            frame.add(idTextField);
            
            JButton buscarButton = new JButton("Buscar");
            buscarButton.setBounds(550, 100, 150, 30);
            buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
            buscarButton.setBackground(new Color(0, 123, 255));
            buscarButton.setForeground(Color.WHITE);
            frame.add(buscarButton);
            
            final JLabel statusLabel = new JLabel("");
            statusLabel.setBounds(100, 150, 600, 30);
            statusLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            frame.add(statusLabel);
            
            final JTextArea categoriaTextArea = new JTextArea();
            categoriaTextArea.setBounds(100, 190, 600, 120);
            categoriaTextArea.setEditable(false);
            categoriaTextArea.setBackground(new Color(240, 240, 240));
            categoriaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            categoriaTextArea.setFont(new Font("Roboto", Font.PLAIN, 12));
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
            volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            frame.add(volverButton);
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuCategoria(categoriaController);
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
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setVisible(true);
      frame.setLayout(null);
 
      JLabel Title = new JLabel("\n=== ACTUALIZAR CATEGORÍA ===");
      Title.setBounds(300, 10, 300, 50);
      Title.setFont(new Font("Roboto", Font.BOLD, 14));
      Title.setForeground(new Color(46, 46, 46));
      frame.add(Title);
 
      if(categoriaController!=null)
      {
          try
          {
              JLabel idLabel = new JLabel("ID de la categoría a actualizar:");
              idLabel.setBounds(100, 70, 200, 30);
              idLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(idLabel);
              
              JTextField idTextField = new JTextField(10);
              idTextField.setBounds(350, 70, 200, 30);
              idTextField.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(idTextField);
              
              final JButton buscarButton = new JButton("Buscar Categoría");
              buscarButton.setBounds(570, 70, 150, 30);
              buscarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              buscarButton.setBackground(new Color(0, 123, 255));
              buscarButton.setForeground(Color.WHITE);
              frame.add(buscarButton);
              
              final JLabel statusLabel = new JLabel("");
              statusLabel.setBounds(100, 110, 600, 30);
              statusLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(statusLabel);
              
              JLabel nombreLabel = new JLabel("Nombre:");
              nombreLabel.setBounds(100, 150, 200, 30);
              nombreLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
              frame.add(nombreLabel);
              
              final JTextField nombreTextField = new JTextField(10);
              nombreTextField.setBounds(350, 150, 200, 30);
              nombreTextField.setFont(new Font("Roboto", Font.PLAIN, 12));
              nombreTextField.setEnabled(false);
              frame.add(nombreTextField);
              
              final JButton guardarButton = new JButton("Guardar Cambios");
              guardarButton.setBounds(150, 250, 200, 30);
              guardarButton.setFont(new Font("Roboto", Font.BOLD, 14));
              guardarButton.setBackground(new Color(76, 175, 80));
              guardarButton.setForeground(Color.WHITE);
              guardarButton.setEnabled(false);
              frame.add(guardarButton);
              
              JButton volverButton = new JButton("Volver");
              volverButton.setBounds(400, 250, 200, 30);
              volverButton.setFont(new Font("Roboto", Font.BOLD, 14));
              volverButton.setBackground(new Color(0, 123, 255));
              volverButton.setForeground(Color.WHITE);
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
                  new MenuCategoria(categoriaController);
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
 
} 