package org.carlosydiego.crmclientes.app.menuproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;

public class MenuEmpleado extends JFrame 
{
    private EmpleadoController empleadoController;

    // Constructor de la clase MenuEmpleado con su controlador
    public MenuEmpleado(EmpleadoController empleadoController) 
    {
        this.empleadoController = empleadoController;
        initializeMenu();
    }

    private void initializeMenu() 
    {
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Menu Empleado");
        getContentPane().setBackground(new Color(245, 247, 250));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) (screenSize.width * 0.18);
        int buttonHeight = (int) (screenSize.height * 0.06);
        int fontSize = (int) (screenSize.height * 0.022);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel Title = new JLabel("=== GESTIÓN DE EMPLEADOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(Title, gbc);

        JButton TodoEmpleado = new JButton("Ver todos los empleados");
        TodoEmpleado.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        TodoEmpleado.setFont(new Font("Roboto", Font.BOLD, fontSize));
        TodoEmpleado.setBackground(new Color(0, 123, 255));
        TodoEmpleado.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(TodoEmpleado, gbc);
        TodoEmpleado.addActionListener(e -> 
        {
            dispose();
            ListarEmpleados();
        });

        JButton BuscarEmpleado = new JButton("Buscar empleado por ID");
        BuscarEmpleado.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        BuscarEmpleado.setFont(new Font("Roboto", Font.BOLD, fontSize));
        BuscarEmpleado.setBackground(new Color(0, 123, 255));
        BuscarEmpleado.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(BuscarEmpleado, gbc);
        BuscarEmpleado.addActionListener(e -> 
        {
            dispose();
            BuscarEmpleado();
        });

        JButton AñadirEmpleado = new JButton("Añadir nuevo empleado");
        AñadirEmpleado.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AñadirEmpleado.setFont(new Font("Roboto", Font.BOLD, fontSize));
        AñadirEmpleado.setBackground(new Color(76, 175, 80));
        AñadirEmpleado.setForeground(Color.WHITE);
        gbc.gridy = 3;
        add(AñadirEmpleado, gbc);
        AñadirEmpleado.addActionListener(e -> 
        {
            dispose();
            AñadirEmpleado();
        });

        JButton ModificarEmpleado = new JButton("Actualizar empleado");
        ModificarEmpleado.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        ModificarEmpleado.setFont(new Font("Roboto", Font.BOLD, fontSize));
        ModificarEmpleado.setBackground(new Color(0, 123, 255));
        ModificarEmpleado.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(ModificarEmpleado, gbc);
        ModificarEmpleado.addActionListener(e -> 
        {
            dispose();
            ActualizarEmpleado();
        });

        JButton EliminarEmpleado = new JButton("Eliminar empleado");
        EliminarEmpleado.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        EliminarEmpleado.setFont(new Font("Roboto", Font.BOLD, fontSize));
        EliminarEmpleado.setBackground(new Color(0, 123, 255));
        EliminarEmpleado.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(EliminarEmpleado, gbc);
        EliminarEmpleado.addActionListener(e -> 
        {
            dispose();
            EliminarEmpleado();
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

    private void ListarEmpleados() 
    {
        JFrame frame = new JFrame("Listar Empleados");
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

        JLabel Title = new JLabel("=== LISTADO DE EMPLEADOS ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        //Se comprueba si hay conexión a la base de datos
        if (empleadoController != null) 
        {
            try 
            {
                //Se obtiene todos los empleados de la base de datos
                List<Empleado> empleados = empleadoController.findAll();

                //Si no hay empleados, se muestra un mensaje
                if (empleados == null || empleados.isEmpty()) 
                {
                    JLabel noEmpleadosLabel = new JLabel("No hay empleados registrados en el sistema");
                    noEmpleadosLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                    noEmpleadosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    gbc.gridy = 1;
                    frame.add(noEmpleadosLabel, gbc);

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
                        new MenuEmpleado(empleadoController);
                    });
                //Si hay empleados, se muestra el listado de empleados
                } 
                else 
                {
                    JTextArea empleadosTextArea = new JTextArea();
                    empleadosTextArea.setEditable(false);
                    empleadosTextArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize - 2));

                    //Se crea un StringBuilder para almacenar el listado de empleados
                    StringBuilder sb = new StringBuilder();
                    for (Empleado empleado : empleados) 
                    {
                        sb.append("ID: ").append(empleado.getID_Empleado()).append("\n");
                        sb.append("Nombre: ").append(empleado.getNombre()).append("\n");
                        sb.append("Apellido: ").append(empleado.getApellido()).append("\n");
                        sb.append("NIF: ").append(empleado.getNIF()).append("\n");
                        sb.append("Dirección: ").append(empleado.getDireccion()).append("\n");
                        sb.append("Código Postal: ").append(empleado.getCodigo_Postal()).append("\n");
                        sb.append("Provincia: ").append(empleado.getProvincia()).append("\n");
                        sb.append("País: ").append(empleado.getPais()).append("\n");
                        sb.append("Teléfono: ").append(empleado.getTelfono()).append("\n");
                        sb.append("Email: ").append(empleado.getEmail()).append("\n");
                        sb.append("------------------------------------------\n");
                    }
                    empleadosTextArea.setText(sb.toString());
                    
                    JScrollPane scrollPane = new JScrollPane(empleadosTextArea);
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
                        new MenuEmpleado(empleadoController);
                    });
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error al obtener los empleados: " + e.getMessage());
            }
        } 
        else 
        {
            System.out.println("Error: No hay conexion a la base de datos");
        }
    }

    private void BuscarEmpleado() 
    {
        JFrame frame = new JFrame("Buscar Empleado");
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
  
        JLabel Title = new JLabel("=== BUSCAR EMPLEADO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);
      
        if (empleadoController != null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para introducir el ID del empleado
                JLabel idLabel = new JLabel("ID del empleado:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);

                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);

                //Se crea un JLabel y un JTextArea para mostrar la información del empleado
                final JLabel infoLabel = new JLabel("Información del empleado:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
            
                final JTextArea empleadoInfo = new JTextArea();
                empleadoInfo.setEditable(false);
                empleadoInfo.setBackground(new Color(240, 240, 240));
                empleadoInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                empleadoInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(empleadoInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
            
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
            
                JButton buscarButton = new JButton("Buscar Empleado");
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

                //Acción para buscar el empleado
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el ID del empleado introducido
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se busca el empleado en la base de datos
                            Empleado empleado = empleadoController.findById(id);

                            //Si el empleado existe, se muestra la información del empleado
                            if (empleado != null) 
                            {
                                empleadoInfo.setText(empleado.toString());
                                statusLabel.setText("");
                            } 
                            else 
                            {
                                //Si el empleado no existe, se muestra un mensaje de error
                                empleadoInfo.setText("");
                                statusLabel.setText("No se encontró un empleado con el ID: " + id);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            //Si el ID introducido no es un número, se muestra un mensaje de error
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            empleadoInfo.setText("");
                        }
                    }
                });
            
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuEmpleado(empleadoController);
                });
            } 
            catch (Exception e) 
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

        JLabel Title = new JLabel("=== AÑADIR EMPLEADO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (empleadoController != null) 
        {
            try 
            {
                //Se crea un JLabel y un JTextField para cada campo del empleado
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
                
                JLabel apellidoLabel = new JLabel("Apellido:");
                apellidoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                frame.add(apellidoLabel, gbc);
                
                JTextField apellidoTextField = new JTextField(20);
                apellidoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                apellidoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(apellidoTextField, gbc);
                
                JLabel nifLabel = new JLabel("NIF (8 números + 1 letra):");
                nifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 3;
                frame.add(nifLabel, gbc);
                
                JTextField nifTextField = new JTextField(20);
                nifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nifTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(nifTextField, gbc);
                
                JLabel direccionLabel = new JLabel("Dirección:");
                direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 4;
                frame.add(direccionLabel, gbc);
                
                JTextField direccionTextField = new JTextField(20);
                direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                direccionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(direccionTextField, gbc);
                
                JLabel cpLabel = new JLabel("Código Postal (5 dígitos):");
                cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(cpLabel, gbc);
                
                JTextField cpTextField = new JTextField(20);
                cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                cpTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(cpTextField, gbc);
                
                JLabel provinciaLabel = new JLabel("Provincia:");
                provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 6;
                frame.add(provinciaLabel, gbc);
                
                JTextField provinciaTextField = new JTextField(20);
                provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                provinciaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(provinciaTextField, gbc);
                
                JLabel paisLabel = new JLabel("País:");
                paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                frame.add(paisLabel, gbc);
                
                JTextField paisTextField = new JTextField(20);
                paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                paisTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(paisTextField, gbc);
                
                JLabel telefonoLabel = new JLabel("Teléfono (9 dígitos):");
                telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 8;
                frame.add(telefonoLabel, gbc);
                
                JTextField telefonoTextField = new JTextField(20);
                telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                telefonoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(telefonoTextField, gbc);
                
                JLabel emailLabel = new JLabel("Email:");
                emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 9;
                frame.add(emailLabel, gbc);
                
                JTextField emailTextField = new JTextField(20);
                emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                emailTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(emailTextField, gbc);
                
                JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 10;
                gbc.gridwidth = 2;
                frame.add(statusLabel, gbc);
                
                JButton guardarButton = new JButton("Guardar Empleado");
                guardarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                guardarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                guardarButton.setBackground(new Color(40, 167, 69));
                guardarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 11;
                gbc.gridwidth = 1;
                frame.add(guardarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                
                guardarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String nombre = nombreTextField.getText();
                            String apellido = apellidoTextField.getText();
                            String nif = nifTextField.getText();
                            String direccion = direccionTextField.getText();
                            String codigoPostal = cpTextField.getText();
                            String provincia = provinciaTextField.getText();
                            String pais = paisTextField.getText();
                            String telefono = telefonoTextField.getText();
                            String email = emailTextField.getText();
                            
                            if (nombre.isEmpty() || apellido.isEmpty() || nif.isEmpty() || 
                                direccion.isEmpty() || codigoPostal.isEmpty() || provincia.isEmpty() || 
                                pais.isEmpty() || telefono.isEmpty() || email.isEmpty()) 
                            {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }
                            
                            if (!nif.matches("\\d{8}[A-Za-z]")) 
                            {
                                statusLabel.setText("Error: El NIF debe tener 8 números seguidos de 1 letra");
                                return;
                            }
                            
                            if (!codigoPostal.matches("\\d{5}")) 
                            {
                                statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                                return;
                            }
                            
                            if (!telefono.matches("\\d{9}")) 
                            {
                                statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                                return;
                            }
                            
                            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                            {
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
                
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuEmpleado(empleadoController);
                });
            } 
            catch (Exception e) 
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

        JLabel Title = new JLabel("=== ACTUALIZAR EMPLEADO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (empleadoController != null) 
        {
            try 
            {
                JLabel idLabel = new JLabel("ID del empleado:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
                JButton buscarButton = new JButton("Buscar Empleado");
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
                
                JLabel apellidoLabel = new JLabel("Apellido:");
                apellidoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 5;
                frame.add(apellidoLabel, gbc);
                
                final JTextField apellidoTextField = new JTextField(10);
                apellidoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                apellidoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                apellidoTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(apellidoTextField, gbc);
                
                JLabel nifLabel = new JLabel("NIF (8 números + 1 letra):");
                nifLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 6;
                frame.add(nifLabel, gbc);
                
                final JTextField nifTextField = new JTextField(10);
                nifTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                nifTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                nifTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(nifTextField, gbc);
                
                JLabel direccionLabel = new JLabel("Dirección:");
                direccionLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 7;
                frame.add(direccionLabel, gbc);
                
                final JTextField direccionTextField = new JTextField(10);
                direccionTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                direccionTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                direccionTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(direccionTextField, gbc);
                
                JLabel cpLabel = new JLabel("Código Postal (5 dígitos):");
                cpLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 8;
                frame.add(cpLabel, gbc);
                
                final JTextField cpTextField = new JTextField(10);
                cpTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                cpTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                cpTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(cpTextField, gbc);
                
                JLabel provinciaLabel = new JLabel("Provincia:");
                provinciaLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 9;
                frame.add(provinciaLabel, gbc);
                
                final JTextField provinciaTextField = new JTextField(10);
                provinciaTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                provinciaTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                provinciaTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(provinciaTextField, gbc);
                
                JLabel paisLabel = new JLabel("País:");
                paisLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 10;
                frame.add(paisLabel, gbc);
                
                final JTextField paisTextField = new JTextField(10);
                paisTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                paisTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                paisTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(paisTextField, gbc);
                
                JLabel telefonoLabel = new JLabel("Teléfono (9 dígitos):");
                telefonoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 11;
                frame.add(telefonoLabel, gbc);
                
                final JTextField telefonoTextField = new JTextField(10);
                telefonoTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                telefonoTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                telefonoTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(telefonoTextField, gbc);
                
                JLabel emailLabel = new JLabel("Email:");
                emailLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 12;
                frame.add(emailLabel, gbc);
                
                final JTextField emailTextField = new JTextField(10);
                emailTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                emailTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                emailTextField.setEnabled(false);
                gbc.gridx = 1;
                frame.add(emailTextField, gbc);
                
                final JButton actualizarButton = new JButton("Actualizar Empleado");
                actualizarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                actualizarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                actualizarButton.setBackground(new Color(40, 167, 69));
                actualizarButton.setForeground(Color.WHITE);
                actualizarButton.setEnabled(false);
                gbc.gridx = 0;
                gbc.gridy = 13;
                frame.add(actualizarButton, gbc);
                
                JButton volverButton = new JButton("Volver");
                volverButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                volverButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                volverButton.setBackground(new Color(0, 123, 255));
                volverButton.setForeground(Color.WHITE);
                gbc.gridx = 1;
                frame.add(volverButton, gbc);
                
                //Se crea un array para almacenar el ID del empleado
                final Long[] empleadoId = new Long[1];
                
                //Acción para buscar el empleado
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se obtiene el empleado con el ID introducido
                            Empleado empleado = empleadoController.findById(id);

                            //Si el empleado existe, se muestra la información del empleado
                            if (empleado != null) 
                            {
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
                                
                                //Se habilitan los campos para que el usuario pueda editarlos
                                nombreTextField.setEnabled(true);
                                apellidoTextField.setEnabled(true);
                                nifTextField.setEnabled(true);
                                direccionTextField.setEnabled(true);
                                cpTextField.setEnabled(true);
                                provinciaTextField.setEnabled(true);
                                paisTextField.setEnabled(true);
                                telefonoTextField.setEnabled(true);
                                emailTextField.setEnabled(true);
                                actualizarButton.setEnabled(true);
                                
                                statusLabel.setText("Empleado encontrado. Puedes editar los campos y actualizar.");
                            } 
                            else 
                            {
                                //Si el empleado no existe, se limpia el formulario y se deshabilita el botón de actualizar
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
                                actualizarButton.setEnabled(false);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            //Si el ID introducido no es un número, se deshabilita el botón de actualizar
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            nombreTextField.setEnabled(false);
                            apellidoTextField.setEnabled(false);
                            nifTextField.setEnabled(false);
                            direccionTextField.setEnabled(false);
                            cpTextField.setEnabled(false);
                            provinciaTextField.setEnabled(false);
                            paisTextField.setEnabled(false);
                            telefonoTextField.setEnabled(false);
                            emailTextField.setEnabled(false);
                            actualizarButton.setEnabled(false);
                        }
                    }
                });
                
                //Acción para actualizar el empleado
                actualizarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se obtiene el valor de cada campo del formulario
                            String nombre = nombreTextField.getText();
                            String apellido = apellidoTextField.getText();
                            String nif = nifTextField.getText();
                            String direccion = direccionTextField.getText();
                            String codigoPostal = cpTextField.getText();
                            String provincia = provinciaTextField.getText();
                            String pais = paisTextField.getText();
                            String telefono = telefonoTextField.getText();
                            String email = emailTextField.getText();
                            
                            //Se comprueba si algún campo está vacío
                            if (nombre.isEmpty() || apellido.isEmpty() || nif.isEmpty() || 
                                direccion.isEmpty() || codigoPostal.isEmpty() || provincia.isEmpty() || 
                                pais.isEmpty() || telefono.isEmpty() || email.isEmpty()) 
                            {
                                statusLabel.setText("Error: Todos los campos son obligatorios");
                                return;
                            }

                            //Se comprueba si el NIF tiene el formato correcto
                            if (!nif.matches("\\d{8}[A-Za-z]")) 
                            {
                                statusLabel.setText("Error: El NIF debe tener 8 números seguidos de 1 letra");
                                return;
                            }

                            //Se comprueba si el código postal tiene el formato correcto
                            if (!codigoPostal.matches("\\d{5}")) 
                            {
                                statusLabel.setText("Error: El código postal debe tener 5 dígitos");
                                return;
                            }

                            //Se comprueba si el teléfono tiene el formato correcto
                            if (!telefono.matches("\\d{9}")) 
                            {
                                statusLabel.setText("Error: El teléfono debe tener 9 dígitos");
                                return;
                            }

                            //Se comprueba si el email tiene el formato correcto
                            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                            {
                                statusLabel.setText("Error: El email debe tener un formato válido");
                                return;
                            }

                            //Se crea un nuevo empleado con los valores introducidos
                            Empleado empleadoActualizado = new Empleado(empleadoId[0], nombre, apellido, nif, direccion, codigoPostal, provincia, pais, telefono, email);

                            //Se actualiza el empleado en la base de datos
                            empleadoController.save(empleadoActualizado);

                            statusLabel.setText("Empleado actualizado correctamente.");
                        } 
                        catch (Exception ex) 
                        {
                            statusLabel.setText("Error al actualizar el empleado: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuEmpleado(empleadoController);
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

        JLabel Title = new JLabel("=== ELIMINAR EMPLEADO ===");
        Title.setFont(new Font("Roboto", Font.BOLD, fontSize));
        Title.setForeground(new Color(46, 46, 46));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(Title, gbc);

        if (empleadoController != null) 
        {
            try 
            {
                // Se crea un JLabel y un JTextField para introducir el ID del empleado
                JLabel idLabel = new JLabel("ID del empleado a eliminar:");
                idLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                frame.add(idLabel, gbc);
                
                JTextField idTextField = new JTextField(10);
                idTextField.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                idTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
                gbc.gridx = 1;
                frame.add(idTextField, gbc);
                
                // Se crea un JLabel y un JTextArea para mostrar la información del empleado
                final JLabel infoLabel = new JLabel("Información del empleado:");
                infoLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize));
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                frame.add(infoLabel, gbc);
                
                final JTextArea empleadoInfo = new JTextArea();
                empleadoInfo.setEditable(false);
                empleadoInfo.setBackground(new Color(240, 240, 240));
                empleadoInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                empleadoInfo.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                JScrollPane scrollPane = new JScrollPane(empleadoInfo);
                scrollPane.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
                gbc.gridy = 3;
                frame.add(scrollPane, gbc);
                
                final JLabel statusLabel = new JLabel("");
                statusLabel.setFont(new Font("Roboto", Font.PLAIN, fontSize - 2));
                gbc.gridy = 4;
                frame.add(statusLabel, gbc);
                
                final JButton buscarButton = new JButton("Buscar Empleado");
                buscarButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                buscarButton.setFont(new Font("Roboto", Font.BOLD, fontSize));
                buscarButton.setBackground(new Color(0, 123, 255));
                buscarButton.setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 5;
                gbc.gridwidth = 1;
                frame.add(buscarButton, gbc);
                
                final JButton eliminarButton = new JButton("Eliminar Empleado");
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
                
                final Long[] empleadoId = new Long[1];
                
                //Acción para buscar el empleado
                buscarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            String input = idTextField.getText();
                            long id = Long.parseLong(input);

                            //Se busca el empleado en la base de datos
                            Empleado empleado = empleadoController.findById(id);

                            //Si el empleado existe, se muestra la información del empleado
                            if (empleado != null) 
                            {
                                empleadoId[0] = empleado.getID_Empleado();
                                empleadoInfo.setText(empleado.toString());
                                eliminarButton.setEnabled(true);
                                statusLabel.setText("Empleado encontrado. Pulse 'Eliminar Empleado' para confirmar.");
                            } 
                            else 
                            {
                                //Si el empleado no existe, se limpia el formulario y se deshabilita el botón de eliminar
                                empleadoInfo.setText("");
                                eliminarButton.setEnabled(false);
                                statusLabel.setText("No se encontró un empleado con el ID: " + id);
                            }
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            //Si el ID introducido no es un número, se deshabilita el botón de eliminar
                            statusLabel.setText("ID inválido. Introduce un número válido.");
                            empleadoInfo.setText("");
                            eliminarButton.setEnabled(false);
                        }
                    }
                });

                //Acción para eliminar el empleado
                eliminarButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try 
                        {
                            //Se muestra un mensaje de confirmación para eliminar el empleado
                            int confirmacion = JOptionPane.showConfirmDialog(frame, 
                                "¿Está seguro de que desea eliminar este empleado?", 
                                "Confirmar eliminación", 
                                JOptionPane.YES_NO_OPTION);

                            //Si el usuario confirma la eliminación, se elimina el empleado
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
                            //Si ocurre un error, se muestra un mensaje de error
                            statusLabel.setText("Error al eliminar el empleado: " + ex.getMessage());
                        }
                    }
                });
                
                volverButton.addActionListener(e -> 
                {
                    frame.dispose();
                    new MenuEmpleado(empleadoController);
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