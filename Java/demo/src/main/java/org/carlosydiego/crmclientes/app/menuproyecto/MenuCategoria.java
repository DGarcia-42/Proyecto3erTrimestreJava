package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuCategoria extends JFrame 
{
    private CategoriaController categoriaController;

    // Constructor de la clase MenuCategoria con su controlador
    public MenuCategoria(CategoriaController categoriaController) 
    {
        this.categoriaController = categoriaController;
        initializeMenu();
    }

    private void initializeMenu() 
    {
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

        JButton TodoCategoria = new JButton("Ver todas las categorías");
        TodoCategoria.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        TodoCategoria.setFont(new Font("Roboto", Font.BOLD, fontSize));
        TodoCategoria.setBackground(new Color(0, 123, 255));
        TodoCategoria.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(TodoCategoria, gbc);
        TodoCategoria.addActionListener(e -> 
        {
            dispose();
            ListarCategorias();
        });
        
        JButton BuscarCategoria = new JButton("Buscar categoría por ID");
        BuscarCategoria.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarCategoria.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarCategoria.setBackground(new Color(0, 123, 255));
        BuscarCategoria.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarCategoria, gbc);
        BuscarCategoria.addActionListener(e -> 
        {
            dispose();
            BuscarCategoria();
        });
        
        JButton AñadirCategoria = new JButton("Añadir nueva categoría");
        AñadirCategoria.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirCategoria.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirCategoria.setBackground(new Color(76, 175, 80));
        AñadirCategoria.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirCategoria, gbc);
        AñadirCategoria.addActionListener(e -> 
        {
            dispose();
            AñadirCategoria();
        });

        JButton ModificarCategoria = new JButton("Actualizar categoría");
        ModificarCategoria.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ModificarCategoria.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ModificarCategoria.setBackground(new Color(0, 123, 255));
        ModificarCategoria.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ModificarCategoria, gbc);
        ModificarCategoria.addActionListener(e -> 
        {
            dispose();
            ActualizarCategoria();
        });
        
        JButton EliminarCategoria = new JButton("Eliminar categoría");
        EliminarCategoria.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarCategoria.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarCategoria.setBackground(new Color(0, 123, 255));
        EliminarCategoria.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarCategoria, gbc);
        EliminarCategoria.addActionListener(e -> 
        {
            dispose();
            EliminarCategoria();
        });

        JButton VolverMenu = new JButton("Volver al menu principal");
        VolverMenu.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        VolverMenu.setFont(new Font("Roboto", Font.BOLD, fontSize));
        VolverMenu.setBackground(new Color(0, 123, 255));
        VolverMenu.setForeground(Color.WHITE);
        gbc.gridy = 6;
        add(VolverMenu, gbc);
        VolverMenu.addActionListener(e -> 
        {
            dispose();
            new MenuProyecto();
        });

        setVisible(true);
    }

    private void ListarCategorias() 
    {
        JFrame frame = new JFrame("Listar Categorías");
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
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== LISTADO DE CATEGORÍAS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        //Comprobación de si hay conexión a la base de datos
        if (categoriaController != null) 
        {
            try 
            {
                //Se obtiene todas las categorías de la base de datos
                List<Categoria> categorias = categoriaController.findAll();
                
                //Si no hay categorías registradas, se muestra un mensaje
                if (categorias == null || categorias.isEmpty()) 
                {
                    JLabel noCategoriasLabel = new JLabel("No hay categorías registradas en el sistema");
                    noCategoriasLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noCategoriasLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noCategoriasLabel, gbc);
                    
                    JButton volverButton = new JButton("Volver");
                    volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                    volverButton.setBackground(new Color(0, 123, 255));
                    volverButton.setForeground(Color.WHITE);
                    gbc.gridy = 2;
                    frame.add(volverButton, gbc);
                    
                    volverButton.addActionListener(e -> 
                    {
                        frame.dispose();
                        new MenuCategoria(categoriaController);
                    });
                } 
                else 
                {
                    //Si hay categorías registradas, se muestra el listado de categorías
                    JTextArea categoriasTextArea = new JTextArea();
                    categoriasTextArea.setEditable(false);
                    categoriasTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));

                    //Se crea un StringBuilder para almacenar el listado de categorías
                    StringBuilder sb = new StringBuilder();
                    for (Categoria categoria : categorias) 
                    {
                        sb.append("ID: ").append(categoria.getID_Categoria()).append("\n");
                        sb.append("Nombre: ").append(categoria.getNombre()).append("\n");
                        sb.append("------------------------------------------\n");
                    }
                    categoriasTextArea.setText(sb.toString());
                    
                    JScrollPane scrollPane = new JScrollPane(categoriasTextArea);
                    scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                    gbc.gridy = 1;
                    frame.add(scrollPane, gbc);
                    
                    JButton volverButton = new JButton("Volver");
                    volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                    volverButton.setBackground(new Color(0, 123, 255));
                    volverButton.setForeground(Color.WHITE);
                    gbc.gridy = 2;
                    frame.add(volverButton, gbc);
                    
                    volverButton.addActionListener(e -> 
                    {
                        frame.dispose();
                        new MenuCategoria(categoriaController);
                    });
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error al obtener las categorías: " + e.getMessage());
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
      
        if (categoriaController != null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para introducir el ID de la categoría
                JLabel idLabel = new JLabel("ID de la categoría:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);

                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                //Se crea un JLabel y un JTextArea para mostrar la información de la categoría
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
            
                JButton buscarButton = new JButton("Buscar Categoría");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);
            
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                gbc.gridy = 5;
                frame.add(volverButton, gbc);

                //Acción para buscar la categoría
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el ID de la categoría introducido
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene la categoría con el ID introducido
                            Categoria categoria = categoriaController.findById(id);

                            //Si la categoría existe, se muestra la información de la categoría
                            if (categoria != null) {
                                categoriaInfo.setText(categoria.toString());
                                statusLabel.setText("");
                            } 
                            else 
                            {
                                //Si la categoría no existe, se muestra un mensaje de error
                                categoriaInfo.setText("");
                                statusLabel.setText("No se encontró una categoría con el ID: " + id);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            categoriaInfo.setText("");
                        }
                    }
                });
            
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuCategoria(categoriaController);
                });
            } 
            catch (Exception e) 
            {
                System.err.println("Error al buscar la categoría: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
 
    private void AñadirCategoria() 
    {
        JFrame frame = new JFrame("Añadir Categoría");
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

        JLabel Title = new JLabel("=== AÑADIR CATEGORÍA ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (categoriaController != null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para cada campo de categoria
                JLabel nombreLabel = new JLabel("Nombre:");
                nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(nombreLabel, gbc);

                JTextField nombreTextField = new JTextField(20);
                nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(nombreTextField, gbc);

                JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);

                JButton guardarButton = new JButton("Guardar Categoría");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(40, 167, 69));
                guardarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);

                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);

                //Acción para guardar la categoría
                guardarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el nombre de la categoría y se comprueba si está vacío
                            String nombre = nombreTextField.getText();

                            //Si el nombre está vacío, se muestra un mensaje de error
                            if (nombre.isEmpty()) 
                            {
                                statusLabel.setText("Error: El nombre es obligatorio");
                                return;
                            }

                            //Se crea una nueva categoría
                            Categoria nuevaCategoria = new Categoria(null, nombre);

                            //Se guarda la categoría en la base de datos
                            categoriaController.save(nuevaCategoria);

                            statusLabel.setText("Categoría añadida correctamente.");

                            //Se limpia el campo de texto
                            nombreTextField.setText("");
                        } 
                        catch (Exception ex) 
                        {
                            statusLabel.setText("Error al guardar la categoría: " + ex.getMessage());
                        }
                    }
                });

                //Acción para volver al menu de categorías
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuCategoria(categoriaController);
                });
            } 
            catch (Exception e) 
            {
                System.err.println("Error al añadir la categoría: " + e.getMessage());
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

        if (categoriaController != null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para introducir el ID de la categoría
                JLabel idLabel = new JLabel("ID de la categoría:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);

                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                JButton buscarButton = new JButton("Buscar Categoría");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(buscarButton, gbc);

                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);

                //Se crea un JLabel y un JTextField para introducir el nombre de la categoría
                JLabel nombreLabel = new JLabel("Nombre:");
                nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 1;
                frame.add(nombreLabel, gbc);

                final JTextField nombreTextField = new JTextField(10);
                nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                nombreTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(nombreTextField, gbc);

                final JButton actualizarButton = new JButton("Actualizar Categoría");
                actualizarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                actualizarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                actualizarButton.setBackground(new Color(40, 167, 69));
                actualizarButton.setForeground(Color.WHITE);
                actualizarButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(actualizarButton, gbc);

                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);

                //Se crea un array para almacenar el ID de la categoría
                final Long[] categoriaId = new Long[1];

                //Acción para buscar la categoría
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene la categoría con el ID introducido
                            Categoria categoria = categoriaController.findById(id);

                            //Si la categoría existe, se muestra la información de la categoría y se habilita el botón de actualizar
                            if (categoria != null) 
                            {
                                categoriaId[0] = categoria.getID_Categoria();
                                nombreTextField.setText(categoria.getNombre());
                                nombreTextField.setEnabled(true);
                                actualizarButton.setEnabled(true);
                                statusLabel.setText("Categoría encontrada. Puedes editar los campos y actualizar.");
                            } 
                            else 
                            {
                                //Si la categoría no existe, se muestra un mensaje de error y se deshabilita el botón de actualizar
                                nombreTextField.setText("");
                                nombreTextField.setEnabled(false);
                                actualizarButton.setEnabled(false);
                                statusLabel.setText("No se encontró una categoría con el ID: " + id);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            //Si el ID introducido no es válido, se muestra un mensaje de error y se deshabilita el botón de actualizar
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            nombreTextField.setEnabled(false);
                            actualizarButton.setEnabled(false);
                        }
                    }
                });

                //Acción para actualizar la categoría
                actualizarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String nombre = nombreTextField.getText();

                            //Si el nombre de la categoría está vacío, se muestra un mensaje de error y se deshabilita el botón de actualizar
                            if(nombre.isEmpty()) 
                            {
                                statusLabel.setText("Error: El nombre es obligatorio");
                                return;
                            }

                            //Se crea una nueva categoría con el ID y el nombre introducido
                            Categoria categoriaActualizada = new Categoria(categoriaId[0], nombre);

                            //Se actualiza la categoría
                            categoriaController.save(categoriaActualizada);

                            statusLabel.setText("Categoría actualizada correctamente.");
                        } 
                        catch (Exception ex) 
                        {
                            statusLabel.setText("Error al actualizar la categoría: " + ex.getMessage());
                        }
                    }
                });

                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuCategoria(categoriaController);
                });
            } 
            catch (Exception e) 
            {
                System.err.println("Error al actualizar la categoría: " + e.getMessage());
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

        if (categoriaController != null) 
        {
            try 
            {
                //Se crea un JLabe y un JTextField para introducir el ID de la categoría
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
                //Se crea un JLabel y un JTextArea para mostrar la información de la categoría
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

                final Long[] categoriaId = new Long[1];

                //Acción para buscar la categoría
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene la categoría con el ID introducido
                            Categoria categoria = categoriaController.findById(id);

                            //Si la categoría existe, se muestra la información de la categoría y se habilita el botón de eliminar
                            if (categoria != null) 
                            {
                                categoriaId[0] = categoria.getID_Categoria();
                                categoriaInfo.setText(categoria.toString());
                                eliminarButton.setEnabled(true);
                                statusLabel.setText("Categoría encontrada. Pulse 'Eliminar Categoría' para confirmar.");
                            } 
                            else 
                            {
                                //Si la categoría no existe, se muestra un mensaje de error y se deshabilita el botón de eliminar
                                categoriaInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró una categoría con el ID: " + id);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            categoriaInfo.setText("");
                            eliminarButton.setEnabled(false);
                        }
                    }
                });

                //Acción para eliminar la categoría
                eliminarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se muestra un mensaje de confirmación para eliminar la categoría
                            int confirmacion = JOptionPane.showConfirmDialog(frame,
                                "¿Está seguro de que desea eliminar esta categoría?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION);

                            //Si el usuario confirma la eliminación, se elimina la categoría
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                categoriaController.delete(categoriaId[0]);
                                categoriaInfo.setText("");
                                idTextField.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("Categoría eliminada correctamente.");
                            }
                        } 
                        catch (Exception ex) 
                        {
                            //Si ocurre un error, se muestra un mensaje de error
                            statusLabel.setText("Error al eliminar la categoría: " + ex.getMessage());
                        }
                    }
                });

                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuCategoria(categoriaController);
                });
            } 
            catch (Exception e) 
            {
                System.err.println("Error al eliminar la categoría: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
 


 
} 