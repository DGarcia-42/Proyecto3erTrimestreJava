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
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Categoria");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE CATEGORÍAS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton ListarCategoriasBtn = new JButton("Ver todas las categorías");
        ListarCategoriasBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ListarCategoriasBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ListarCategoriasBtn.setBackground(new Color(0, 123, 255));
        ListarCategoriasBtn.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(ListarCategoriasBtn, gbc);
        ListarCategoriasBtn.addActionListener(e -> {
            dispose();
            ListarCategorias();
        });
        
        JButton BuscarCategoriaBtn = new JButton("Buscar categoría por ID");
        BuscarCategoriaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarCategoriaBtn.setBackground(new Color(0, 123, 255));
        BuscarCategoriaBtn.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarCategoriaBtn, gbc);
        BuscarCategoriaBtn.addActionListener(e -> {
            dispose();
            BuscarCategoria();
        });
        
        JButton AñadirCategoriaBtn = new JButton("Añadir nueva categoría");
        AñadirCategoriaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirCategoriaBtn.setBackground(new Color(76, 175, 80));
        AñadirCategoriaBtn.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirCategoriaBtn, gbc);
        AñadirCategoriaBtn.addActionListener(e -> {
            dispose();
            AñadirCategoria();
        });

        JButton ActualizarCategoriaBtn = new JButton("Actualizar categoría");
        ActualizarCategoriaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ActualizarCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ActualizarCategoriaBtn.setBackground(new Color(0, 123, 255));
        ActualizarCategoriaBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ActualizarCategoriaBtn, gbc);
        ActualizarCategoriaBtn.addActionListener(e -> {
            dispose();
            ActualizarCategoria();
        });
        
        JButton EliminarCategoriaBtn = new JButton("Eliminar categoría");
        EliminarCategoriaBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarCategoriaBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarCategoriaBtn.setBackground(new Color(0, 123, 255));
        EliminarCategoriaBtn.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarCategoriaBtn, gbc);
        EliminarCategoriaBtn.addActionListener(e -> {
            dispose();
            EliminarCategoria();
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

    private void ListarCategorias() {
        JFrame frame = new JFrame("Listar Categorías");
        frame.setLayout(new GridBagLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int panelWidth = (int) (screenSize.width * 0.7);
        int panelHeight = (int) (screenSize.height * 0.6);
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== LISTA DE CATEGORÍAS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        frame.add(Title, gbc);

        if (categoriaController != null) {
            try {
                List<Categoria> categorias = categoriaController.findAll();
                if (categorias != null && !categorias.isEmpty()) {
                    JPanel panelCategorias = new JPanel();
                    panelCategorias.setLayout(null);
                    
                    JScrollPane scrollPane = new JScrollPane(panelCategorias);
                    scrollPane.setPreferredSize(new Dimension(panelWidth, panelHeight));
                    gbc.gridy = 1;
                    frame.add(scrollPane, gbc);
                    
                    panelCategorias.setPreferredSize(new Dimension(panelWidth - 50, Math.max(panelHeight - 50, categorias.size() * 150)));
                    
                    for (int i = 0; i < categorias.size(); i++) {
                        JTextArea categoriaTextArea = new JTextArea(categorias.get(i).toString());
                        categoriaTextArea.setBounds(50, 10 + i * 150, panelWidth - 150, 130);
                        categoriaTextArea.setEditable(false);
                        categoriaTextArea.setBackground(new Color(240, 240, 240));
                        categoriaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        categoriaTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                        panelCategorias.add(categoriaTextArea);
                    }
                } else {
                    JLabel noCategoriasLabel = new JLabel("No hay categorías registradas en el sistema");
                    noCategoriasLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noCategoriasLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noCategoriasLabel, gbc);
                }
            } catch (Exception e) {
                System.err.println("Error al obtener las categorías: " + e.getMessage());
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
            new MenuCategoria(categoriaController);
        });
    }

    private void AñadirCategoria()
    {
      JFrame frame = new JFrame("Añadir Categoría");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.getContentPane().setBackground(new Color(245, 247, 250));
      frame.setLayout(new GridBagLayout());
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

      JLabel Title = new JLabel("=== AÑADIR NUEVA CATEGORÍA ===");
      Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
      Title.setForeground(new Color(46, 46, 46));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      gbc.gridy = 0;
      frame.add(Title, gbc);

      JLabel nombreLabel = new JLabel("Nombre:");
      nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
      gbc.gridy = 1;
      frame.add(nombreLabel, gbc);

      JTextField nombreTextField = new JTextField(10);
      nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
      nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
      gbc.gridy = 2;
      frame.add(nombreTextField, gbc);

      JLabel statusLabel = new JLabel("");
      statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
      gbc.gridy = 3;
      frame.add(statusLabel, gbc);

      JButton guardarButton = new JButton("Guardar Categoría");
      guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
      guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
      guardarButton.setBackground(new Color(76, 175, 80));
      guardarButton.setForeground(Color.WHITE);
      gbc.gridy = 4;
      frame.add(guardarButton, gbc);
      guardarButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              try {
                  String nombre = nombreTextField.getText();
                  if (nombre.isEmpty()) {
                      statusLabel.setText("Error: El nombre es obligatorio");
                      return;
                  }
                  Categoria nuevaCategoria = new Categoria(null, nombre);
                  categoriaController.save(nuevaCategoria);
                  statusLabel.setText("Categoría añadida correctamente.");
                  nombreTextField.setText("");
              } catch (Exception ex) {
                  statusLabel.setText("Error al guardar la categoría: " + ex.getMessage());
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
          new MenuCategoria(categoriaController);
      });
    }
 
    private void EliminarCategoria() {
        JFrame frame = new JFrame("Eliminar Categoría");
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

        JLabel Title = new JLabel("=== ELIMINAR CATEGORÍA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (categoriaController != null) {
            try {
                JLabel idLabel = new JLabel("ID de la categoría a eliminar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);

                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                final JLabel infoLabel = new JLabel("Información de la categoría:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);

                final JTextArea categoriaInfo = new JTextArea();
                categoriaInfo.setEditable(false);
                categoriaInfo.setBackground(new Color(240, 240, 240));
                categoriaInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                categoriaInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(categoriaInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);

                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);

                final JButton buscarButton = new JButton("Buscar Categoría");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);

                final JButton eliminarButton = new JButton("Eliminar Categoría");
                eliminarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                eliminarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                eliminarButton.setBackground(new Color(0, 123, 255));
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

                final Long[] categoriaId = new Long[1];

                buscarButton.addActionListener(new ActionListener() {
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
                            } else {
                                categoriaInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró una categoría con el ID: " + id);
                            }
                        } catch (NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                        }
                    }
                });

                eliminarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int confirmacion = JOptionPane.showConfirmDialog(frame,
                                "¿Está seguro de que desea eliminar esta categoría?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION);

                            if (confirmacion == JOptionPane.YES_OPTION) {
                                categoriaController.delete(categoriaId[0]);
                                categoriaInfo.setText("");
                                idTextField.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("Categoría eliminada correctamente.");
                            }
                        } catch (Exception ex) {
                            statusLabel.setText("Error al eliminar la categoría: " + ex.getMessage());
                        }
                    }
                });

                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuCategoria(categoriaController);
                });
            } catch (Exception e) {
                System.err.println("Error al eliminar la categoría: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
 
    private void BuscarCategoria() {
        JFrame frame = new JFrame("Buscar Categoría");
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

        JLabel Title = new JLabel("=== BUSCAR CATEGORÍA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (categoriaController != null) {
            try {
                JLabel idLabel = new JLabel("Introduce el ID de la categoría:");
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
                gbc.gridy = 2;
                frame.add(buscarButton, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 3;
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);
                
                final JTextArea categoriaTextArea = new JTextArea();
                categoriaTextArea.setEditable(false);
                categoriaTextArea.setBackground(new Color(240, 240, 240));
                categoriaTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                categoriaTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                categoriaTextArea.setVisible(false);
                JScrollPane scrollPane = new JScrollPane(categoriaTextArea);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 4;
                frame.add(scrollPane, gbc);
                
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = idTextField.getText();
                        try {
                            long id = Long.parseLong(input);
                            statusLabel.setText("Buscando categoría...");
                            
                            Categoria categoria = categoriaController.findById(id);
                            if (categoria != null) {
                                statusLabel.setText("Categoría encontrada:");
                                categoriaTextArea.setText(categoria.toString());
                                categoriaTextArea.setVisible(true);
                                scrollPane.setVisible(true);
                                frame.revalidate();
                                frame.repaint();
                            } else {
                                statusLabel.setText("Categoría no encontrada");
                                categoriaTextArea.setVisible(false);
                                scrollPane.setVisible(false);
                                frame.revalidate();
                                frame.repaint();
                            }
                        } catch (NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            categoriaTextArea.setVisible(false);
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
                    new MenuCategoria(categoriaController);
                });
            } catch (Exception e) {
                System.err.println("Error al buscar la categoría: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
 
    private void ActualizarCategoria() {
        JFrame frame = new JFrame("Actualizar Categoría");
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

        JLabel Title = new JLabel("=== ACTUALIZAR CATEGORÍA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (categoriaController != null) {
            try {
                JLabel idLabel = new JLabel("ID de la categoría a actualizar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);

                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                final JButton buscarButton = new JButton("Buscar Categoría");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridy = 2;
                frame.add(buscarButton, gbc);

                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
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

                final JButton guardarButton = new JButton("Guardar Cambios");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(76, 175, 80));
                guardarButton.setForeground(Color.WHITE);
                guardarButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(guardarButton, gbc);

                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);

                final Long[] categoriaId = new Long[1];

                buscarButton.addActionListener(new ActionListener() {
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

                                statusLabel.setText("Categoría encontrada. Modifique los campos o mantenga los mismos valores (o deje en blanco).");
                            } else {
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

                guardarButton.addActionListener(new ActionListener() {
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
            } catch (Exception e) {
                System.err.println("Error al actualizar la categoría: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
 
} 