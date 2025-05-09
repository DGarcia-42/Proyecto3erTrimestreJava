package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuProducto extends JFrame {
    private ProductoController productoController;
    private CategoriaController categoriaController;
    private ProveedorController proveedorController;

    public MenuProducto(ProductoController productoController,
                       CategoriaController categoriaController,
                       ProveedorController proveedorController) {
        this.productoController = productoController;
        this.categoriaController = categoriaController;
        this.proveedorController = proveedorController;
        initializeMenu();
    }

    private void initializeMenu() {
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

        JButton ListarProductosBtn = new JButton("Ver todos los productos");
        ListarProductosBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ListarProductosBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ListarProductosBtn.setBackground(new Color(0, 123, 255));
        ListarProductosBtn.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(ListarProductosBtn, gbc);
        ListarProductosBtn.addActionListener(e -> {
            dispose();
            ListarProductos();
        });

        JButton BuscarProductoBtn = new JButton("Buscar producto por ID");
        BuscarProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarProductoBtn.setBackground(new Color(0, 123, 255));
        BuscarProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarProductoBtn, gbc);
        BuscarProductoBtn.addActionListener(e -> {
            dispose();
            BuscarProducto();
        });

        JButton AñadirProductoBtn = new JButton("Añadir nuevo producto");
        AñadirProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirProductoBtn.setBackground(new Color(76, 175, 80));
        AñadirProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirProductoBtn, gbc);
        AñadirProductoBtn.addActionListener(e -> {
            dispose();
            AñadirProducto();
        });

        JButton ActualizarProductoBtn = new JButton("Actualizar producto");
        ActualizarProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ActualizarProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ActualizarProductoBtn.setBackground(new Color(0, 123, 255));
        ActualizarProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ActualizarProductoBtn, gbc);
        ActualizarProductoBtn.addActionListener(e -> {
            dispose();
            ActualizarProducto();
        });

        JButton EliminarProductoBtn = new JButton("Eliminar producto");
        EliminarProductoBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarProductoBtn.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarProductoBtn.setBackground(new Color(0, 123, 255));
        EliminarProductoBtn.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarProductoBtn, gbc);
        EliminarProductoBtn.addActionListener(e -> {
            dispose();
            EliminarProducto();
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

    private void ListarProductos() {
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

        JLabel Title = new JLabel("=== LISTA DE PRODUCTOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if(productoController!=null) {
            try {
                List<Producto> productos = productoController.findAll();
                if(productos!=null && !productos.isEmpty()) {
                    JPanel panelProductos = new JPanel();
                    panelProductos.setLayout(null);
                    
                    JScrollPane scrollPane = new JScrollPane(panelProductos);
                    scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                    gbc.gridy = 1;
                    frame.add(scrollPane, gbc);
                    
                    panelProductos.setPreferredSize(new Dimension(textAreaWidth - 50, Math.max(textAreaHeight - 50, productos.size() * 150)));
                    
                    for(int i = 0; i < productos.size(); i++) {
                        JTextArea productoTextArea = new JTextArea(productos.get(i).toString());
                        productoTextArea.setBounds(50, 10 + i * 150, textAreaWidth - 150, 130);
                        productoTextArea.setEditable(false);
                        productoTextArea.setBackground(new Color(240, 240, 240));
                        productoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        productoTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 4));
                        panelProductos.add(productoTextArea);
                    }
                } else {
                    JLabel noProductosLabel = new JLabel("No hay productos registrados en el sistema");
                    noProductosLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noProductosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noProductosLabel, gbc);
                }
            } catch(Exception e) {
                System.err.println("Error al obtener los productos: " + e.getMessage());
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
            new MenuProducto(productoController, categoriaController, proveedorController);
        });
    }

    private void BuscarProducto() {
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

        if(productoController!=null) {
            try {
                JLabel idLabel = new JLabel("Introduce el ID del producto:");
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
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(buscarButton, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 3;
                frame.add(statusLabel, gbc);
                
                final JTextArea productoTextArea = new JTextArea();
                productoTextArea.setEditable(false);
                productoTextArea.setBackground(new Color(240, 240, 240));
                productoTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                productoTextArea.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                productoTextArea.setVisible(false);
                JScrollPane scrollPane = new JScrollPane(productoTextArea);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 4;
                frame.add(scrollPane, gbc);
                
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = idTextField.getText();
                        try {
                            long id = Long.parseLong(input);
                            statusLabel.setText("Buscando producto...");
                            
                            Producto producto = productoController.findById(id);
                            if(producto!=null) {
                                statusLabel.setText("Producto encontrado:");
                                productoTextArea.setText(producto.toString());
                                productoTextArea.setVisible(true);
                                scrollPane.setVisible(true);
                                frame.revalidate();
                                frame.repaint();
                            } else {
                                statusLabel.setText("Producto no encontrado");
                                productoTextArea.setVisible(false);
                                scrollPane.setVisible(false);
                                frame.revalidate();
                                frame.repaint();
                            }
                        } catch(NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            productoTextArea.setVisible(false);
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
                    new MenuProducto(productoController, categoriaController, proveedorController);
                });
            } catch(Exception e) {
                System.err.println("Error al buscar el producto: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }

    private void AñadirProducto() {
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
 
        JLabel Title = new JLabel("=== AÑADIR NUEVO PRODUCTO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
 
        if(productoController!=null) {
            try {
                JLabel nombreLabel = new JLabel("Nombre:");
                nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(nombreLabel, gbc);
                
                JTextField nombreTextField = new JTextField(10);
                nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(nombreTextField, gbc);
                
                JLabel descripcionLabel = new JLabel("Descripción:");
                descripcionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(descripcionLabel, gbc);
                
                JTextField descripcionTextField = new JTextField(10);
                descripcionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                descripcionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(descripcionTextField, gbc);
                
                JLabel precioLabel = new JLabel("Precio:");
                precioLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 3;
                frame.add(precioLabel, gbc);
                
                JTextField precioTextField = new JTextField(10);
                precioTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                precioTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(precioTextField, gbc);
                
                JLabel stockLabel = new JLabel("Stock:");
                stockLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                frame.add(stockLabel, gbc);
                
                JTextField stockTextField = new JTextField(10);
                stockTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                stockTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(stockTextField, gbc);
                
                JLabel categoriaLabel = new JLabel("ID de Categoría:");
                categoriaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(categoriaLabel, gbc);
                
                JTextField categoriaTextField = new JTextField(10);
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
                
                verCategoriasButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            List<Categoria> categorias = categoriaController.findAll();
                            if(categorias != null && !categorias.isEmpty()) {
                                StringBuilder sb = new StringBuilder();
                                for(Categoria c : categorias) {
                                    sb.append("ID: ").append(c.getID_Categoria())
                                      .append(" - Nombre: ").append(c.getNombre())
                                      .append("\n");
                                }
                                JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(sb.toString())), 
                                    "Listado de Categorías", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No hay categorías registradas", 
                                    "Listado de Categorías", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch(Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al obtener categorías: " + ex.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                
                JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);
                
                JButton guardarButton = new JButton("Guardar Producto");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(76, 175, 80));
                guardarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 8;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String nombre = nombreTextField.getText();
                            String descripcion = descripcionTextField.getText();
                            String precioStr = precioTextField.getText();
                            String stockStr = stockTextField.getText();
                            String categoriaStr = categoriaTextField.getText();
                            
                            // Verificar que ningún campo esté vacío
                            if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || 
                                stockStr.isEmpty() || categoriaStr.isEmpty()) {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }
                            
                            // Validar precio
                            double precio;
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
                            
                            // Validar stock
                            int stock;
                            try {
                                stock = Integer.parseInt(stockStr);
                                if (stock < 0) {
                                    statusLabel.setText("Error: El stock no puede ser negativo");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El stock debe ser un número entero");
                                return;
                            }
                            
                            // Validar categoría
                            long categoriaId;
                            try {
                                categoriaId = Long.parseLong(categoriaStr);
                                
                                // Verificar que la categoría exista
                                Categoria categoria = categoriaController.findById(categoriaId);
                                if (categoria == null) {
                                    statusLabel.setText("Error: La categoría con ID " + categoriaId + " no existe");
                                    return;
                                }
                            } catch (NumberFormatException nfe) {
                                statusLabel.setText("Error: El ID de categoría debe ser un número entero");
                                return;
                            }
                            
                            Producto nuevoProducto = new Producto(null, nombre, descripcion, precio, stock, categoriaId);
                            
                            productoController.save(nuevoProducto);
                            
                            statusLabel.setText("Producto añadido correctamente");
                            
                            nombreTextField.setText("");
                            descripcionTextField.setText("");
                            precioTextField.setText("");
                            stockTextField.setText("");
                            categoriaTextField.setText("");
                        } catch (Exception ex) {
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
            } catch(Exception e) {
                System.err.println("Error al añadir el producto: " + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }

    private void ActualizarProducto() {
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
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if(productoController!=null && categoriaController!=null && proveedorController!=null) {
            try {
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
                
                JLabel nombreLabel = new JLabel("Nombre:");
                nombreLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                frame.add(nombreLabel, gbc);
                
                final JTextField nombreTextField = new JTextField(10);
                nombreTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nombreTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
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
                gbc.gridx = 1;
                frame.add(categoriaTextField, gbc);
                
                final JButton verCategoriasButton = new JButton("Ver Categorías");
                verCategoriasButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verCategoriasButton.setBackground(new Color(0, 123, 255));
                verCategoriasButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 10;
                gbc.gridwidth = 2;
                frame.add(verCategoriasButton, gbc);
                
                JLabel proveedorLabel = new JLabel("ID de Proveedor:");
                proveedorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 11;
                frame.add(proveedorLabel, gbc);
                
                final JTextField proveedorTextField = new JTextField(10);
                proveedorTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                proveedorTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(proveedorTextField, gbc);
                
                final JButton verProveedoresButton = new JButton("Ver Proveedores");
                verProveedoresButton.setFont(new Font("Roboto", Font.BOLD, fontSize - 2));
                verProveedoresButton.setBackground(new Color(0, 123, 255));
                verProveedoresButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 12;
                gbc.gridwidth = 2;
                frame.add(verProveedoresButton, gbc);
                
                final JButton guardarButton = new JButton("Guardar Cambios");
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(76, 175, 80));
                guardarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 13;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                
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
                    new MenuProducto(productoController, categoriaController, proveedorController);
                });
            } catch(Exception e) {
                System.err.println("Error al actualizar producto: " + e.getMessage());
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            gbc.gridy = 2;
            frame.add(volverButton, gbc);
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProducto(productoController, categoriaController, proveedorController);
            });
        }
    }

    private void EliminarProducto() {
        JFrame frame = new JFrame("Eliminar Producto");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());

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

        JLabel Title = new JLabel("=== ELIMINAR PRODUCTO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if(productoController!=null) {
            try {
                JLabel idLabel = new JLabel("ID del producto a eliminar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
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
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                frame.add(productoInfo, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);
                
                final JButton buscarButton = new JButton("Buscar Producto");
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);
                
                final JButton eliminarButton = new JButton("Eliminar Producto");
                eliminarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                eliminarButton.setBackground(new Color(0, 123, 255));
                eliminarButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(eliminarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 2;
                frame.add(volverButton, gbc);
                
                final Long[] productoId = new Long[1];
                
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);
                            
                            Producto producto = productoController.findById(id);
                            if (producto != null) {
                                productoId[0] = producto.getID_Producto();
                                productoInfo.setText(producto.toString());
                                eliminarButton.setEnabled(true);
                                statusLabel.setText("Producto encontrado. Pulse 'Eliminar Producto' para confirmar.");
                            } else {
                                productoInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró un producto con el ID: " + id);
                            }
                        } catch (NumberFormatException nfe) {
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            productoInfo.setText("");
                            eliminarButton.setEnabled(false);
                        }
                    }
                });
                
                eliminarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int confirmacion = JOptionPane.showConfirmDialog(frame, 
                                "¿Está seguro de que desea eliminar este producto?", 
                                "Confirmar eliminación", 
                                JOptionPane.YES_NO_OPTION);
                            
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                productoController.delete(productoId[0]);
                                
                                productoInfo.setText("");
                                idTextField.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("Producto eliminado correctamente.");
                            }
                        } catch (Exception ex) {
                            statusLabel.setText("Error al eliminar el producto: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> {
                    frame.dispose();
                    new MenuProducto(productoController, categoriaController, proveedorController);
                });
            } catch(Exception e) {
                System.err.println("Error al eliminar el producto: " + e.getMessage());
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No hay conexión a la base de datos");
            errorLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            frame.add(errorLabel, gbc);
            
            JButton volverButton = new JButton("Volver");
            volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
            volverButton.setBackground(new Color(0, 123, 255));
            volverButton.setForeground(Color.WHITE);
            gbc.gridy = 2;
            frame.add(volverButton, gbc);
            volverButton.addActionListener(e -> {
                frame.dispose();
                new MenuProducto(productoController, categoriaController, proveedorController);
            });
        }
    }
} 