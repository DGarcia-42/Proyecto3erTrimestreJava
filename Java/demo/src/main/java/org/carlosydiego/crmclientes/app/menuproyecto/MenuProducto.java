package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuProducto extends JFrame 
{
    private ProductoController productoController;
    private CategoriaController categoriaController;
    private ProveedorController proveedorController;

    //Constructor de la clase MenuProducto con su controlador y los necesarios para sus atributos
    public MenuProducto(ProductoController productoController,
                       CategoriaController categoriaController,
                       ProveedorController proveedorController) 
    {
        this.productoController = productoController;
        this.categoriaController = categoriaController;
        this.proveedorController = proveedorController;
        initializeMenu();
    }

    private void initializeMenu() 
    {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Producto");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE PRODUCTOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton TodoProducto = new JButton("Ver todos los productos");
        TodoProducto.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        TodoProducto.setFont(new Font("Roboto", Font.BOLD, fontSize));
        TodoProducto.setBackground(new Color(0, 123, 255));
        TodoProducto.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(TodoProducto, gbc);
        TodoProducto.addActionListener(e -> 
        {
            dispose();
            ListarProductos();
        });

        JButton BuscarProducto = new JButton("Buscar producto por ID");
        BuscarProducto.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarProducto.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarProducto.setBackground(new Color(0, 123, 255));
        BuscarProducto.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarProducto, gbc);
        BuscarProducto.addActionListener(e -> 
        {
            dispose();
            BuscarProducto();
        });

        JButton AñadirProducto = new JButton("Añadir nuevo producto");
        AñadirProducto.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirProducto.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirProducto.setBackground(new Color(76, 175, 80));
        AñadirProducto.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirProducto, gbc);
        AñadirProducto.addActionListener(e -> 
        {
            dispose();
            AñadirProducto();
        });

        JButton ModificarProducto = new JButton("Actualizar producto");
        ModificarProducto.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ModificarProducto.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ModificarProducto.setBackground(new Color(0, 123, 255));
        ModificarProducto.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ModificarProducto, gbc);
        ModificarProducto.addActionListener(e -> 
        {
            dispose();
            ActualizarProducto();
        });

        JButton EliminarProducto = new JButton("Eliminar producto");
        EliminarProducto.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarProducto.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarProducto.setBackground(new Color(0, 123, 255));
        EliminarProducto.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarProducto, gbc);
        EliminarProducto.addActionListener(e -> 
        {
            dispose();
            EliminarProducto();
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

    private void ListarProductos() 
    {
        JFrame frame = new JFrame("Listar Productos");
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== LISTADO DE PRODUCTOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        //Comprobación de si hay conexión a la base de datos
        if(productoController!=null) 
        {
            try 
            {
                //Se obtiene todos los productos de la base de datos
                List<Producto> productos = productoController.findAll();

                //Si no hay productos registrados, se muestra un mensaje
                if(productos==null || productos.isEmpty()) 
                {
                    JLabel noProductosLabel = new JLabel("No hay productos registrados en el sistema");
                    noProductosLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noProductosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noProductosLabel, gbc);
                    
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
                        new MenuProducto(productoController, categoriaController, proveedorController);
                    });
                //Si hay productos registrados, se muestra el listado de productos
                } 
                else 
                {
                    JTextArea productosTextArea = new JTextArea();
                    productosTextArea.setEditable(false);
                    productosTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));
                    
                    //Se crea un StringBuilder para almacenar el listado de productos
                    StringBuilder sb = new StringBuilder();
                    for (Producto producto : productos) 
                    {
                        sb.append("ID: ").append(producto.getID_Producto()).append("\n");
                        sb.append("Nombre: ").append(producto.getNombre()).append("\n");
                        sb.append("Descripción: ").append(producto.getDescripcion()).append("\n");
                        sb.append("Precio: ").append(producto.getPVP()).append("\n");
                        sb.append("IVA: ").append(producto.getIVA()).append("\n");
                        sb.append("Stock: ").append(producto.getStock()).append("\n");
                        
                        if (producto.getCategoria() != null) 
                        {
                            sb.append("Categoría: ").append(producto.getCategoria().getNombre())
                              .append(" (ID: ").append(producto.getCategoria().getID_Categoria()).append(")\n");
                        }
                        
                        if (producto.getProveedor() != null) 
                        {
                            sb.append("Proveedor: ").append(producto.getProveedor().getNombre())
                              .append(" (ID: ").append(producto.getProveedor().getID_Proveedor()).append(")\n");
                        }
                        
                        sb.append("------------------------------------------\n");
                    }
                    productosTextArea.setText(sb.toString());
                    
                    JScrollPane scrollPane = new JScrollPane(productosTextArea);
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
                        new MenuProducto(productoController, categoriaController, proveedorController);
                    });
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
    }

    private void BuscarProducto() 
    {
        JFrame frame = new JFrame("Buscar Producto");
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
 
        JLabel Title = new JLabel("=== BUSCAR PRODUCTO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if(productoController!=null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para el ID del producto
                JLabel idLabel = new JLabel("ID del producto:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
 
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
                //Se crea un JLabel y un JTextArea para la información del producto
                final JLabel infoLabel = new JLabel("Información del producto:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
                
                final JTextArea productoInfo = new JTextArea();
                productoInfo.setEditable(false);
                productoInfo.setBackground(new Color(240, 240, 240));
                productoInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                productoInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(productoInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
                
                JButton buscarButton = new JButton("Buscar Producto");
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
                frame.add(volverButton, gbc);
                
                //Acción para buscar el producto
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el ID del producto introducido 
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se busca el producto con el ID introducido
                            Producto producto = productoController.findById(id);

                            //Si el producto existe, se muestra la información del producto
                            if(producto != null) 
                            {
                                productoInfo.setText(producto.toString());
                                statusLabel.setText("");
                            } 
                            else 
                            {
                                //Si el producto no existe, se muestra un mensaje de error
                                productoInfo.setText("");
                                statusLabel.setText("No se encontró un producto con el ID: " + id);
                            }
                        } 
                        catch(NumberFormatException nfe) 
                        {
                            //Si el ID introducido no es un número, se muestra un mensaje de error
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            productoInfo.setText("");
                        }
                    }
                });
                
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuProducto(productoController, categoriaController, proveedorController);
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
 
        JLabel Title = new JLabel("=== AÑADIR PRODUCTO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
 
        if(productoController!=null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para cada campo del producto
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
                
                JLabel descripcionLabel = new JLabel("Descripción:");
                descripcionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(descripcionLabel, gbc);
                
                JTextField descripcionTextField = new JTextField(20);
                descripcionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                descripcionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(descripcionTextField, gbc);
                
                JLabel precioLabel = new JLabel("Precio:");
                precioLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 3;
                frame.add(precioLabel, gbc);
                
                JTextField precioTextField = new JTextField(20);
                precioTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                precioTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(precioTextField, gbc);
                
                JLabel stockLabel = new JLabel("Stock:");
                stockLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                frame.add(stockLabel, gbc);
                
                JTextField stockTextField = new JTextField(20);
                stockTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                stockTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(stockTextField, gbc);
                
                JLabel categoriaLabel = new JLabel("ID de Categoría:");
                categoriaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(categoriaLabel, gbc);
                
                JTextField categoriaTextField = new JTextField(20);
                categoriaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                categoriaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(categoriaTextField, gbc);
                
                JButton verCategoriasButton = new JButton("Ver Categorías");
                verCategoriasButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verCategoriasButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verCategoriasButton.setBackground(new Color(0, 123, 255));
                verCategoriasButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 2;
                frame.add(verCategoriasButton, gbc);
                
                JLabel proveedorLabel = new JLabel("ID de Proveedor:");
                proveedorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 1;
                frame.add(proveedorLabel, gbc);
                
                JTextField proveedorTextField = new JTextField(20);
                proveedorTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                proveedorTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(proveedorTextField, gbc);
                
                JButton verProveedoresButton = new JButton("Ver Proveedores");
                verProveedoresButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verProveedoresButton.setBackground(new Color(0, 123, 255));
                verProveedoresButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 8;
                gbc.gridwidth = 2;
                frame.add(verProveedoresButton, gbc);
                
                JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 9;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);
                
                JButton guardarButton = new JButton("Guardar Producto");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(40, 167, 69));
                guardarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 10;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);
                
                //Acción ver las categorías disponibles
                verCategoriasButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el listado de categorías
                            List<Categoria> categorias = categoriaController.findAll();

                            //Si hay categorías registradas, se muestra el listado de categorías
                            if(categorias != null && !categorias.isEmpty()) 
                            {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de las categorías
                                for(Categoria c : categorias) 
                                {
                                    sb.append("ID: ").append(c.getID_Categoria())
                                      .append(" - Nombre: ").append(c.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de categorías
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Categorías", JOptionPane.INFORMATION_MESSAGE);
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(frame, "No hay categorías registradas", 
                                    "Listado de Categorías", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        catch(Exception ex) 
                        {
                            JOptionPane.showMessageDialog(frame, "Error al obtener categorías: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción ver los proveedores disponibles
                verProveedoresButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el listado de proveedores
                            List<Proveedor> proveedores = proveedorController.findAll();

                            //Si hay proveedores registrados, se muestra el listado de proveedores
                            if(proveedores != null && !proveedores.isEmpty()) 
                            {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de los proveedores
                                for(Proveedor p : proveedores) 
                                {
                                    sb.append("ID: ").append(p.getID_Proveedor())
                                      .append(" - Nombre: ").append(p.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de proveedores
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(frame, "No hay proveedores registrados", 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        catch(Exception ex) 
                        {
                            JOptionPane.showMessageDialog(frame, "Error al obtener proveedores: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción para guardar el producto
                guardarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el valor de cada campo del producto
                            String nombre = nombreTextField.getText();
                            String descripcion = descripcionTextField.getText();
                            String precioStr = precioTextField.getText();
                            String stockStr = stockTextField.getText();
                            String categoriaStr = categoriaTextField.getText();
                            String proveedorStr = proveedorTextField.getText();

                            //Se comprueba si algún campo está vacío
                            if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || 
                                stockStr.isEmpty() || categoriaStr.isEmpty()) 
                            {
                                statusLabel.setText("Error: Los campos nombre, descripción, precio, stock y categoría son obligatorios");
                                return;
                            }

                            //Se comprueba si el precio es un número válido
                            double precio;
                            try 
                            {
                                precio = Double.parseDouble(precioStr);
                                if (precio <= 0) 
                                {
                                    statusLabel.setText("Error: El precio debe ser mayor que cero");
                                    return;
                                }
                            } 
                            catch (NumberFormatException nfe) 
                            {
                                statusLabel.setText("Error: El precio debe ser un número válido");
                                return;
                            }
                            
                            //Se comprueba si el stock es un número válido
                            int stock;
                            try 
                            {
                                stock = Integer.parseInt(stockStr);
                                if (stock < 0) 
                                {
                                    statusLabel.setText("Error: El stock no puede ser negativo");
                                    return;
                                }
                            } 
                            catch (NumberFormatException nfe) 
                            {
                                statusLabel.setText("Error: El stock debe ser un número entero");
                                return;
                            }
                            
                            Categoria categoria = null;
                            try 
                            {
                                long categoriaId = Long.parseLong(categoriaStr);
                                
                                categoria = categoriaController.findById(categoriaId);
                                if (categoria == null) 
                                {
                                    statusLabel.setText("Error: La categoría con ID " + categoriaId + " no existe");
                                    return;
                                }
                            } 
                            catch (NumberFormatException nfe) 
                            {
                                statusLabel.setText("Error: El ID de categoría debe ser un número entero");
                                return;
                            }

                            //Se comprueba si el proveedor es un número válido y si existe
                            Proveedor proveedor = null;
                            if (!proveedorStr.isEmpty()) 
                            {
                                try 
                                {
                                    long proveedorId = Long.parseLong(proveedorStr);
                                    proveedor = proveedorController.findById(proveedorId);
                                    if (proveedor == null) 
                                    {
                                        statusLabel.setText("Error: El proveedor con ID " + proveedorId + " no existe");
                                        return;
                                    }
                                } 
                                catch (NumberFormatException nfe) 
                                {
                                    statusLabel.setText("Error: El ID de proveedor debe ser un número entero");
                                    return;
                                }
                            }

                            //Se crea un nuevo producto con los valores introducidos
                            Producto nuevoProducto = new Producto(nombre, descripcion, stock, precio, 0.21, categoria, proveedor);

                            //Se guarda el nuevo producto
                            productoController.save(nuevoProducto);

                            //Se muestra un mensaje de confirmación
                            statusLabel.setText("Producto añadido correctamente");
                            
                            //Se limpia el formulario
                            nombreTextField.setText("");
                            descripcionTextField.setText("");
                            precioTextField.setText("");
                            stockTextField.setText("");
                            categoriaTextField.setText("");
                            proveedorTextField.setText("");
                        } 
                        catch (Exception ex) 
                        {
                            statusLabel.setText("Error al guardar el producto: " + ex.getMessage());
                        }
                    }
                });
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuProducto(productoController, categoriaController, proveedorController);
                });
            } 
            catch(Exception e) 
            {
                System.err.println("Error al añadir el producto: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }

    private void ActualizarProducto() 
    {
        JFrame frame = new JFrame("Actualizar Producto");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

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

        JLabel Title = new JLabel("=== ACTUALIZAR PRODUCTO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if(productoController!=null && categoriaController!=null && proveedorController!=null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para introducir el ID del producto a actualizar
                JLabel idLabel = new JLabel("ID del producto a actualizar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
                final JButton buscarButton = new JButton("Buscar Producto");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(buscarButton, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 3;
                frame.add(statusLabel, gbc);

                //Se crea un JLabel y un JTextField para introducir cada campo del producto
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
                
                JLabel descripcionLabel = new JLabel("Descripción:");
                descripcionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(descripcionLabel, gbc);
                
                final JTextField descripcionTextField = new JTextField(10);
                descripcionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                descripcionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                descripcionTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(descripcionTextField, gbc);
                
                JLabel precioLabel = new JLabel("Precio (PVP):");
                precioLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 6;
                frame.add(precioLabel, gbc);
                
                final JTextField precioTextField = new JTextField(10);
                precioTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                precioTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                precioTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(precioTextField, gbc);
                
                JLabel ivaLabel = new JLabel("IVA (%):");
                ivaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                frame.add(ivaLabel, gbc);
                
                final JTextField ivaTextField = new JTextField(10);
                ivaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                ivaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                ivaTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(ivaTextField, gbc);
                
                JLabel stockLabel = new JLabel("Stock:");
                stockLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 8;
                frame.add(stockLabel, gbc);
                
                final JTextField stockTextField = new JTextField(10);
                stockTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                stockTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                stockTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(stockTextField, gbc);
                
                JLabel categoriaLabel = new JLabel("ID de Categoría:");
                categoriaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 9;
                frame.add(categoriaLabel, gbc);
                
                final JTextField categoriaTextField = new JTextField(10);
                categoriaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                categoriaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                categoriaTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(categoriaTextField, gbc);
                
                final JButton verCategoriasButton = new JButton("Ver Categorías");
                verCategoriasButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verCategoriasButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verCategoriasButton.setBackground(new Color(0, 123, 255));
                verCategoriasButton.setForeground(Color.WHITE);
                verCategoriasButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 10;
                gbc.gridwidth = 2;
                frame.add(verCategoriasButton, gbc);
                
                JLabel proveedorLabel = new JLabel("ID de Proveedor:");
                proveedorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 11;
                gbc.gridwidth = 1;
                frame.add(proveedorLabel, gbc);
                
                final JTextField proveedorTextField = new JTextField(10);
                proveedorTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                proveedorTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                proveedorTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(proveedorTextField, gbc);
                
                final JButton verProveedoresButton = new JButton("Ver Proveedores");
                verProveedoresButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verProveedoresButton.setBackground(new Color(0, 123, 255));
                verProveedoresButton.setForeground(Color.WHITE);
                verProveedoresButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 12;
                gbc.gridwidth = 2;
                frame.add(verProveedoresButton, gbc);
                
                final JButton actualizarButton = new JButton("Actualizar Producto");
                actualizarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                actualizarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                actualizarButton.setBackground(new Color(40, 167, 69));
                actualizarButton.setForeground(Color.WHITE);
                actualizarButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 13;
                gbc.gridwidth = 1;
                frame.add(actualizarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                
                //Se crea un array para almacenar el ID del producto
                final Long[] productoId = new Long[1];

                //Acción para mostrar el listado de categorías
                verCategoriasButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el listado de categorías
                            List<Categoria> categorias = categoriaController.findAll();

                            //Si hay categorías registradas, se muestra el listado de categorías
                            if (categorias != null && !categorias.isEmpty()) 
                            {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de las categorías
                                for(Categoria c : categorias) 
                                {
                                    sb.append("ID: ").append(c.getID_Categoria())
                                      .append(" - Nombre: ").append(c.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de categorías
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Categorías", JOptionPane.INFORMATION_MESSAGE);
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(frame, "No hay categorías registradas", 
                                    "Listado de Categorías", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        catch(Exception ex) 
                        {
                            JOptionPane.showMessageDialog(frame, "Error al obtener categorías: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción para mostrar el listado de proveedores
                verProveedoresButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el listado de proveedores
                            List<Proveedor> proveedores = proveedorController.findAll();

                            //Si hay proveedores registrados, se muestra el listado de proveedores
                            if (proveedores != null && !proveedores.isEmpty()) 
                            {
                                StringBuilder sb = new StringBuilder();

                                //Se crea un StringBuilder para almacenar la información de los proveedores
                                for(Proveedor p : proveedores) 
                                {
                                    sb.append("ID: ").append(p.getID_Proveedor())
                                      .append(" - Nombre: ").append(p.getNombre())
                                      .append("\n");
                                }

                                //Se muestra el listado de proveedores
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(frame, "No hay proveedores registrados", 
                                    "Listado de Proveedores", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        catch(Exception ex) 
                        {
                            JOptionPane.showMessageDialog(frame, "Error al obtener proveedores: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                //Acción para buscar el producto
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            statusLabel.setText("Buscando producto...");

                            //Se obtiene el producto con el ID introducido
                            Producto producto = productoController.findById(id);

                            //Si el producto existe, se muestra la información del producto
                            if (producto != null) 
                            {
                                productoId[0] = producto.getID_Producto();

                                nombreTextField.setText(producto.getNombre());
                                descripcionTextField.setText(producto.getDescripcion());
                                precioTextField.setText(String.valueOf(producto.getPVP()));
                                ivaTextField.setText(String.valueOf(producto.getIVA()));
                                stockTextField.setText(String.valueOf(producto.getStock()));
                                
                                if (producto.getCategoria() != null) 
                                {
                                    categoriaTextField.setText(String.valueOf(producto.getCategoria().getID_Categoria()));
                                }
                                
                                if (producto.getProveedor() != null) 
                                {
                                    proveedorTextField.setText(String.valueOf(producto.getProveedor().getID_Proveedor()));
                                }
                                
                                //Se habilitan los campos para que el usuario pueda editarlos
                                nombreTextField.setEnabled(true);
                                descripcionTextField.setEnabled(true);
                                precioTextField.setEnabled(true);
                                ivaTextField.setEnabled(true);
                                stockTextField.setEnabled(true);
                                categoriaTextField.setEnabled(true);
                                proveedorTextField.setEnabled(true);
                                verCategoriasButton.setEnabled(true);
                                verProveedoresButton.setEnabled(true);
                                actualizarButton.setEnabled(true);
                                
                                statusLabel.setText("Producto encontrado. Puedes editar los campos y actualizar.");
                            } 
                            else 
                            {
                                //Si el producto no existe, se limpia el formulario y se deshabilitan los campos
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
                                actualizarButton.setEnabled(false);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            //Si el ID introducido no es un número válido, se muestra un mensaje de error
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                        }
                    }
                });

                //Acción para actualizar el producto
                actualizarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el producto original
                            Producto productoOriginal = productoController.findById(productoId[0]);

                            //Si el producto original no existe, se muestra un mensaje de error
                            if (productoOriginal == null) 
                            {
                                statusLabel.setText("Error: No se pudo recuperar el producto original.");
                                return;
                            }

                            //Se obtiene el valor de cada campo del producto
                            String nombre = nombreTextField.getText();
                            String descripcion = descripcionTextField.getText();
                            String precioStr = precioTextField.getText();
                            String ivaStr = ivaTextField.getText();
                            String stockStr = stockTextField.getText();
                            String categoriaStr = categoriaTextField.getText();
                            String proveedorStr = proveedorTextField.getText();

                            //Se comprueba si algún campo está vacío
                            if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || 
                                ivaStr.isEmpty() || stockStr.isEmpty() || categoriaStr.isEmpty()) 
                            {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }

                            //Se comprueba si el precio es un número válido
                            double precio = 0;
                            try 
                            {
                                precio = Double.parseDouble(precioStr);
                                if (precio <= 0) 
                                {
                                    statusLabel.setText("Error: El precio debe ser mayor que cero");
                                    return;
                                }
                            } 
                            catch (NumberFormatException nfe) 
                            {
                                statusLabel.setText("Error: El precio debe ser un número válido");
                                return;
                            }

                            //Se comprueba si el IVA es un número válido
                            double iva = 0;
                            try 
                            {
                                iva = Double.parseDouble(ivaStr);
                                if (iva < 0 || iva > 100) {
                                    statusLabel.setText("Error: El IVA debe estar entre 0 y 100");
                                    return;
                                }
                            } 
                            catch (NumberFormatException nfe) 
                            {
                                statusLabel.setText("Error: El IVA debe ser un número válido");
                                return;
                            }

                            //Se comprueba si el stock es un número válido
                            int stock = 0;
                            try 
                            {
                                stock = Integer.parseInt(stockStr);
                                if (stock < 0) 
                                {
                                    statusLabel.setText("Error: El stock no puede ser negativo");
                                    return;
                                }
                            } 
                            catch (NumberFormatException nfe) 
                            {
                                statusLabel.setText("Error: El stock debe ser un número entero válido");
                                return;
                            }

                            //Se comprueba si la categoría existe
                            Categoria categoria = null;
                            if (!categoriaStr.isEmpty()) 
                            {
                                try 
                                {
                                    long idCategoria = Long.parseLong(categoriaStr);
                                    categoria = categoriaController.findById(idCategoria);
                                    if (categoria == null) 
                                    {
                                        statusLabel.setText("Error: La categoría con ID " + idCategoria + " no existe");
                                        return;
                                    }
                                } 
                                catch (NumberFormatException nfe) 
                                {
                                    statusLabel.setText("Error: El ID de categoría debe ser un número válido");
                                    return;
                                }
                            }

                            //Se comprueba si el proveedor existe
                            Proveedor proveedor = null;
                            if (!proveedorStr.isEmpty()) 
                            {
                                try 
                                {
                                    long idProveedor = Long.parseLong(proveedorStr);
                                    proveedor = proveedorController.findById(idProveedor);
                                    if (proveedor == null) 
                                    {
                                        statusLabel.setText("Error: El proveedor con ID " + idProveedor + " no existe");
                                        return;
                                    }
                                } 
                                catch (NumberFormatException nfe) 
                                {
                                    statusLabel.setText("Error: El ID de proveedor debe ser un número válido");
                                    return;
                                }
                            }

                            //Se crea un nuevo producto con los valores introducidos
                            Producto productoActualizado = new Producto();
                            productoActualizado.setID_Producto(productoId[0]);
                            productoActualizado.setNombre(nombre);
                            productoActualizado.setDescripcion(descripcion);
                            productoActualizado.setPVP(precio);
                            productoActualizado.setIVA(iva);
                            productoActualizado.setStock(stock);
                            productoActualizado.setCategoria(categoria);
                            productoActualizado.setProveedor(proveedor);

                            //Se guarda el producto actualizado
                            productoController.save(productoActualizado);

                            statusLabel.setText("Producto actualizado correctamente");
                        } 
                        catch (Exception ex) 
                        {
                            statusLabel.setText("Error al actualizar el producto: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuProducto(productoController, categoriaController, proveedorController);
                });
            } 
            catch(Exception e) 
            {
                System.err.println("Error al actualizar producto: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }

    private void EliminarProducto() 
    {
        JFrame frame = new JFrame("Eliminar Producto");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

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

        JLabel Title = new JLabel("=== ELIMINAR PRODUCTO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if(productoController!=null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para introducir el ID del producto a eliminar
                JLabel idLabel = new JLabel("ID del producto a eliminar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                //Se crea un JLabel y un JTextArea para mostrar la información del producto
                final JLabel infoLabel = new JLabel("Información del producto:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
                
                final JTextArea productoInfo = new JTextArea();
                productoInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                productoInfo.setEditable(false);
                productoInfo.setBackground(new Color(240, 240, 240));
                productoInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                JScrollPane scrollPane = new JScrollPane(productoInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
                
                final JButton buscarButton = new JButton("Buscar Producto");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);
                
                final JButton eliminarButton = new JButton("Eliminar Producto");
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
                
                final Long[] productoId = new Long[1];
                
                //Acción para buscar el producto
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene el producto con el ID introducido
                            Producto producto = productoController.findById(id);

                            //Si el producto existe, se muestra la información del producto
                            if (producto != null) 
                            {
                                productoId[0] = producto.getID_Producto();
                                productoInfo.setText(producto.toString());
                                eliminarButton.setEnabled(true);
                                statusLabel.setText("Producto encontrado. Pulse 'Eliminar Producto' para confirmar.");
                            } 
                            else 
                            {
                                //Si el producto no existe, se limpia el formulario y se deshabilita el botón de eliminar
                                productoInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró un producto con el ID: " + id);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            //Si el ID introducido no es un número válido, se muestra un mensaje de error
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            productoInfo.setText("");
                            eliminarButton.setEnabled(false);
                        }
                    }
                });

                //Acción para eliminar el producto
                eliminarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se muestra un mensaje de confirmación para eliminar el producto
                            int confirmacion = JOptionPane.showConfirmDialog(frame, 
                                "¿Está seguro de que desea eliminar este producto?", 
                                "Confirmar eliminación", 
                                JOptionPane.YES_NO_OPTION);
                            
                            //Si el usuario confirma la eliminación, se elimina el producto
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                productoController.delete(productoId[0]);

                                productoInfo.setText("");
                                idTextField.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("Producto eliminado correctamente.");
                            }
                        } 
                        catch (Exception ex) 
                        {
                            //Si ocurre un error, se muestra un mensaje de error
                            statusLabel.setText("Error al eliminar el producto: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuProducto(productoController, categoriaController, proveedorController);
                });
            } 
            catch(Exception e) 
            {
                System.err.println("Error al eliminar el producto: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }
} 