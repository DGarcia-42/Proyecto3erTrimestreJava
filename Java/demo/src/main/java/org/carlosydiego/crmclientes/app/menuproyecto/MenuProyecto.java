package org.carlosydiego.crmclientes.app.menuproyecto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import org.carlosydiego.crmclientes.app.controller.CategoriaController;
import org.carlosydiego.crmclientes.app.controller.ClienteController;
import org.carlosydiego.crmclientes.app.controller.EmpleadoController;
import org.carlosydiego.crmclientes.app.controller.FacturaController;
import org.carlosydiego.crmclientes.app.controller.ProductoController;
import org.carlosydiego.crmclientes.app.controller.ProveeController;
import org.carlosydiego.crmclientes.app.controller.ProveedorController;
import org.carlosydiego.crmclientes.app.database.DatabaseConnection;
import org.carlosydiego.crmclientes.app.model.Categoria;
import org.carlosydiego.crmclientes.app.model.Cliente;
import org.carlosydiego.crmclientes.app.model.Empleado;
import org.carlosydiego.crmclientes.app.model.Factura;
import org.carlosydiego.crmclientes.app.model.Producto;
import org.carlosydiego.crmclientes.app.model.Proveedor;

public class MenuProyecto 
{
    private Scanner scanner;
    private ClienteController clienteController;
    private EmpleadoController empleadoController;
    private ProductoController productoController;
    private ProveedorController proveedorController;
    private FacturaController facturaController;
    private CategoriaController categoriaController;
    private ProveeController proveeController;
    private Connection connection;
    
    public MenuProyecto() 
    {
        this.scanner = new Scanner(System.in);
        inicializarControladores();
    }
    
    private void inicializarControladores() 
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
    
    public void mostrarMenuPrincipal() 
    {
        boolean salir = false;
        
        while (!salir) 
        {
            System.out.println("\n=== SISTEMA CRM CLIENTES ===");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Empleados");
            System.out.println("3. Gestión de Productos");
            System.out.println("4. Gestión de Proveedores");
            System.out.println("5. Gestión de Facturas");
            System.out.println("6. Gestión de Categorías");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            switch (opcion) 
            {
                case 1:
                    mostrarMenuClientes();
                    break;
                case 2:
                    mostrarMenuEmpleados();
                    break;
                case 3:
                    mostrarMenuProductos();
                    break;
                case 4:
                    mostrarMenuProveedores();
                    break;
                case 5:
                    mostrarMenuFacturas();
                    break;
                case 6:
                    mostrarMenuCategorias();
                    break;
                case 0:
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema CRM!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private void mostrarMenuClientes() 
    {
        boolean volver = false;
        
        while (!volver) 
        {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Ver todos los clientes");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Añadir nuevo cliente");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    System.out.println("Lista de clientes...");
                    if (clienteController != null) 
                    {
                        try 
                        {
                            clienteController.findAll().forEach(System.out::println);
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al obtener la lista de clientes: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID del cliente: ");
                    Long idCliente = scanner.nextLong();
                    System.out.println("Buscando cliente con ID " + idCliente + "...");
                    if (clienteController != null) 
                    {
                        try 
                        {
                            Cliente cliente = clienteController.findById(idCliente);
                            if (cliente != null) 
                            {
                                System.out.println(cliente);
                            } 
                            else 
                            {
                                System.out.println("No se encontró un cliente con el ID: " + idCliente);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al buscar el cliente: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 3:
                    if (clienteController != null) 
                    {
                        try 
                        {
                            System.out.println("Añadiendo nuevo cliente...");
                            scanner.nextLine();
                            System.out.print("Ingrese el CIF del cliente: ");
                            String cif = scanner.nextLine();
                            if (!VerificarDato(cif))
                            {
                                break;
                            }
                            if (!cif.matches("[A-Z]\\d{8}")) 
                            {
                                System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                                break;
                            }
                            System.out.print("Ingrese el nombre de la empresa: ");
                            String nombreEmpresa = scanner.nextLine();
                            if (!VerificarDato(nombreEmpresa))
                            {
                                break;
                            }
                            System.out.print("Ingrese el nombre del responsable de la empresa: ");
                            String nombreResponsable = scanner.nextLine();
                            if (!VerificarDato(nombreResponsable))
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese el pais del cliente: ");
                            String pais = scanner.nextLine();
                            if (!VerificarDato(pais))
                            {
                                break;
                            }
                            System.out.print("Ingrese la provincia del cliente: ");
                            String provincia = scanner.nextLine();
                            if (!VerificarDato(provincia))
                            {
                                break;
                            }
                            System.out.print("Ingrese la dirección del cliente: ");
                            String direccion = scanner.nextLine();
                            if (!VerificarDato(direccion))
                            {
                                break;
                            }
                            System.out.print("Ingrese el email del cliente: ");
                            String email = scanner.nextLine();
                            if (!VerificarDato(email))
                            {
                                break;
                            }
                            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                            {
                                System.out.println("El email debe ser válido.");
                                break;
                            }
                            System.out.print("Ingrese el teléfono del cliente: ");
                            String telefono = scanner.nextLine();
                            if (!VerificarDato(telefono))
                            {
                                break;
                            }
                            if (!telefono.matches("\\d+")) 
                            {
                                System.out.println("El teléfono debe contener solo números.");
                                break;
                            }
                            System.out.print("Ingrese el código postal del cliente: ");
                            String codigoPostal = scanner.nextLine();
                            if (!VerificarDato(codigoPostal))
                            {
                                break;
                            }
                            if (!codigoPostal.matches("\\d{5}")) 
                            {
                                System.out.println("El código postal debe tener 5 números.");
                                break;
                            }
                            Cliente cliente = new Cliente(cif, nombreEmpresa, nombreResponsable, pais, provincia, direccion, email, telefono, codigoPostal);
                            clienteController.save(cliente);
                            System.out.println("Cliente añadido correctamente.");
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al añadir el cliente: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 4:
                    if (clienteController != null) 
                    {
                        try 
                        {
                            System.out.println("Actualizando cliente...");
                            System.out.print("Ingrese el ID del cliente: ");
                            Long idClienteActualizar = scanner.nextLong();
                            scanner.nextLine(); 
                            
                            Cliente clienteExistente = clienteController.findById(idClienteActualizar);
                            if (clienteExistente != null) 
                            {
                                System.out.println("Cliente encontrado: " + clienteExistente);
                                System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                                
                                System.out.print("Ingrese el nuevo CIF del cliente [" + clienteExistente.getCIF() + "]: ");
                                String nuevoCIF = scanner.nextLine();
                                if (nuevoCIF.isEmpty())
                                {
                                    nuevoCIF = clienteExistente.getCIF();
                                }
                                if (!nuevoCIF.matches("[A-Z]\\d{8}")) 
                                {
                                    System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                                    break;
                                }
                                
                                System.out.print("Ingrese el nuevo nombre de la empresa [" + clienteExistente.getNombre_Empresa() + "]: ");
                                String nuevoNombreEmpresa = scanner.nextLine();
                                if (nuevoNombreEmpresa.isEmpty())
                                {
                                    nuevoNombreEmpresa = clienteExistente.getNombre_Empresa();
                                }
                                
                                System.out.print("Ingrese el nuevo nombre del responsable [" + clienteExistente.getNombre_Responsable() + "]: ");
                                String nuevoNombreResponsable = scanner.nextLine();
                                if (nuevoNombreResponsable.isEmpty())
                                {
                                    nuevoNombreResponsable = clienteExistente.getNombre_Responsable();
                                }
                                
                                System.out.print("Ingrese el nuevo país [" + clienteExistente.getPais() + "]: ");
                                String nuevoPais = scanner.nextLine();
                                if (nuevoPais.isEmpty())
                                {
                                    nuevoPais = clienteExistente.getPais();
                                }
                                
                                
                                System.out.print("Ingrese la nueva provincia [" + clienteExistente.getProvincia() + "]: ");
                                String nuevaProvincia = scanner.nextLine();
                                if (nuevaProvincia.isEmpty())
                                {
                                    nuevaProvincia = clienteExistente.getProvincia();
                                }
                                
                                System.out.print("Ingrese la nueva dirección [" + clienteExistente.getDireccion() + "]: ");
                                String nuevaDireccion = scanner.nextLine();
                                if (nuevaDireccion.isEmpty())
                                {
                                    nuevaDireccion = clienteExistente.getDireccion();
                                }
                                
                                System.out.print("Ingrese el nuevo email [" + clienteExistente.getEmail() + "]: ");
                                String nuevoEmail = scanner.nextLine();
                                if (nuevoEmail.isEmpty())
                                {
                                    nuevoEmail = clienteExistente.getEmail();
                                }

                                if (!nuevoEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                                {
                                    System.out.println("El email debe ser válido.");
                                    break;
                                }

                                System.out.print("Ingrese el nuevo teléfono [" + clienteExistente.getTelefono() + "]: ");
                                String nuevoTelefono = scanner.nextLine();
                                if (nuevoTelefono.isEmpty())
                                {
                                    nuevoTelefono = clienteExistente.getTelefono();
                                }

                                if (!nuevoTelefono.matches("\\d+")) 
                                {
                                    System.out.println("El teléfono debe contener solo números.");
                                    break;
                                }

                                System.out.print("Ingrese el nuevo código postal [" + clienteExistente.getCodigo_Postal() + "]: ");
                                String nuevoCodigoPostal = scanner.nextLine();
                                if (nuevoCodigoPostal.isEmpty())
                                {
                                    nuevoCodigoPostal = clienteExistente.getCodigo_Postal();
                                }
                                if (!nuevoCodigoPostal.matches("\\d{5}")) 
                                {
                                    System.out.println("El código postal debe tener 5 números.");
                                    break;
                                }
                                

                                Cliente clienteActualizado = new Cliente(nuevoCIF, nuevoNombreEmpresa, nuevoNombreResponsable, nuevoPais, nuevaProvincia, nuevaDireccion, nuevoEmail, nuevoTelefono, nuevoCodigoPostal);
                                clienteActualizado.setID_Cliente(idClienteActualizar);
                                clienteController.save(clienteActualizado);
                                System.out.println("Cliente actualizado correctamente.");
                            } 
                            else 
                            {
                                System.out.println("No se encontró el cliente con ID: " + idClienteActualizar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al actualizar el cliente: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 5:
                    if (clienteController != null) 
                    {
                        try 
                        {
                            System.out.println("Eliminando cliente...");
                            System.out.print("Ingrese el ID del cliente a eliminar: ");
                            Long idClienteEliminar = scanner.nextLong();
                            
                            Cliente clienteExistente = clienteController.findById(idClienteEliminar);
                            if (clienteExistente != null) 
                            {
                                System.out.println("Se eliminará el siguiente cliente: " + clienteExistente);
                                System.out.print("¿Está seguro? (S/N): ");
                                scanner.nextLine(); 
                                String confirmacion = scanner.nextLine().toUpperCase();
                                
                                if (confirmacion.equals("S")) 
                                {
                                    clienteController.delete(idClienteEliminar);
                                    System.out.println("Cliente eliminado correctamente.");
                                } 
                                else 
                                {
                                    System.out.println("Operación cancelada.");
                                }
                            } 
                            else 
                            {
                                System.out.println("No se encontró el cliente con ID: " + idClienteEliminar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al eliminar el cliente: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private void mostrarMenuEmpleados() 
    {
        boolean volver = false;
        
        while (!volver) 
        {
            System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
            System.out.println("1. Ver todos los empleados");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Añadir nuevo empleado");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    System.out.println("Lista de empleados...");
                    if (empleadoController != null) 
                    {
                        try 
                        {
                            empleadoController.findAll().forEach(System.out::println);
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al obtener la lista de empleados: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID del empleado: ");
                    Long idEmpleado = scanner.nextLong();
                    System.out.println("Buscando empleado con ID " + idEmpleado + "...");
                    if (empleadoController != null) 
                    {
                        try 
                        {
                            Empleado empleado = empleadoController.findById(idEmpleado);
                            if (empleado != null) 
                            {
                                System.out.println(empleado);
                            } 
                            else 
                            {
                                System.out.println("No se encontró un empleado con el ID: " + idEmpleado);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al buscar el empleado: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 3:
                    if (empleadoController != null) 
                    {
                        try 
                        {
                            System.out.println("Añadiendo nuevo empleado...");
                            scanner.nextLine();
                            System.out.print("Ingrese el DNI del empleado: ");
                            String dni = scanner.nextLine();
                            if (!VerificarDato(dni)) 
                            {
                                break;
                            }
                            if (!dni.matches("\\d{8}[A-Z]")) 
                            {
                                System.out.println("El DNI debe tener 8 números seguidos de 1 letra.");
                                break;
                            }
                            
                            System.out.print("Ingrese el nombre del empleado: ");
                            String nombre = scanner.nextLine();
                            if (!VerificarDato(nombre)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese los apellidos del empleado: ");
                            String apellidos = scanner.nextLine();
                            if (!VerificarDato(apellidos)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese el email del empleado: ");
                            String email = scanner.nextLine();
                            if (!VerificarDato(email)) 
                            {
                                break;
                            }
                            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                            {
                                System.out.println("El email debe ser válido.");
                                break;
                            }
                            
                            System.out.print("Ingrese el teléfono del empleado: ");
                            String telefono = scanner.nextLine();
                            if (!VerificarDato(telefono)) 
                            {
                                break;
                            }
                            if (!telefono.matches("\\d+")) 
                            {
                                System.out.println("El teléfono debe contener solo números.");
                                break;
                            }
                            
                            System.out.print("Ingrese el puesto del empleado: ");
                            String puesto = scanner.nextLine();
                            if (!VerificarDato(puesto)) 
                            {
                                break;
                            }
                            
                            Empleado empleado = new Empleado();
                            empleado.setNIF(dni);
                            empleado.setNombre(nombre);
                            empleado.setApellido(apellidos);
                            empleado.setEmail(email);
                            empleado.setTelfono(telefono);
                            empleadoController.save(empleado);
                            System.out.println("Empleado añadido correctamente.");
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al añadir el empleado: " + e.getMessage());
                        }
                    }
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 4:
                    if (empleadoController != null) 
                    {
                        try 
                        {
                            System.out.println("Actualizando empleado...");
                            System.out.print("Ingrese el ID del empleado: ");
                            Long idEmpleadoActualizar = scanner.nextLong();
                            scanner.nextLine(); 
                            
                            Empleado empleadoExistente = empleadoController.findById(idEmpleadoActualizar);
                            if (empleadoExistente != null) 
                            {
                                System.out.println("Empleado encontrado: " + empleadoExistente);
                                System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                                
                                System.out.print("Ingrese el nuevo DNI [" + empleadoExistente.getNIF() + "]: ");
                                String nuevoDNI = scanner.nextLine();
                                if (nuevoDNI.isEmpty()) 
                                {
                                    nuevoDNI = empleadoExistente.getNIF();
                                }
                                
                                System.out.print("Ingrese el nuevo nombre [" + empleadoExistente.getNombre() + "]: ");
                                String nuevoNombre = scanner.nextLine();
                                if (nuevoNombre.isEmpty()) 
                                {
                                    nuevoNombre = empleadoExistente.getNombre();
                                }
                                
                                System.out.print("Ingrese los nuevos apellidos [" + empleadoExistente.getApellido() + "]: ");
                                String nuevosApellidos = scanner.nextLine();
                                if (nuevosApellidos.isEmpty()) 
                                {
                                    nuevosApellidos = empleadoExistente.getApellido();
                                }
                                
                                System.out.print("Ingrese el nuevo email [" + empleadoExistente.getEmail() + "]: ");
                                String nuevoEmail = scanner.nextLine();
                                if (nuevoEmail.isEmpty()) 
                                {
                                    nuevoEmail = empleadoExistente.getEmail();
                                }
                                
                                System.out.print("Ingrese el nuevo teléfono [" + empleadoExistente.getTelfono() + "]: ");
                                String nuevoTelefono = scanner.nextLine();
                                if (nuevoTelefono.isEmpty()) 
                                {
                                    nuevoTelefono = empleadoExistente.getTelfono();
                                }
                                
                                Empleado empleadoActualizado = new Empleado();
                                empleadoActualizado.setID_Empleado(idEmpleadoActualizar);
                                empleadoActualizado.setNombre(nuevoNombre);
                                empleadoActualizado.setApellido(nuevosApellidos);
                                empleadoActualizado.setNIF(nuevoDNI);
                                empleadoActualizado.setEmail(nuevoEmail);
                                empleadoActualizado.setTelfono(nuevoTelefono);
                                empleadoController.save(empleadoActualizado);
                                System.out.println("Empleado actualizado correctamente.");
                            } 
                            else 
                            {
                                System.out.println("No se encontró el empleado con ID: " + idEmpleadoActualizar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al actualizar el empleado: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 5:
                    if (empleadoController != null) 
                    {
                        try 
                        {
                            System.out.println("Eliminando empleado...");
                            System.out.print("Ingrese el ID del empleado a eliminar: ");
                            Long idEmpleadoEliminar = scanner.nextLong();
                            
                            Empleado empleadoExistente = empleadoController.findById(idEmpleadoEliminar);
                            if (empleadoExistente != null) 
                            {
                                System.out.println("Se eliminará el siguiente empleado: " + empleadoExistente);
                                System.out.print("¿Está seguro? (S/N): ");
                                scanner.nextLine(); 
                                String confirmacion = scanner.nextLine().toUpperCase();
                                
                                if (confirmacion.equals("S")) 
                                {
                                    empleadoController.delete(idEmpleadoEliminar);
                                    System.out.println("Empleado eliminado correctamente.");
                                } 
                                else 
                                {
                                    System.out.println("Operación cancelada.");
                                }
                            } 
                            else 
                            {
                                System.out.println("No se encontró el empleado con ID: " + idEmpleadoEliminar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al eliminar el empleado: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private void mostrarMenuProductos() 
    {
        boolean volver = false;
        
        while (!volver) 
        {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Añadir nuevo producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    System.out.println("Lista de productos...");
                    if (productoController != null) 
                    {
                        try 
                        {
                            productoController.findAll().forEach(System.out::println);
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al obtener la lista de productos: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID del producto: ");
                    Long idProducto = scanner.nextLong();
                    System.out.println("Buscando producto con ID " + idProducto + "...");
                    if (productoController != null) 
                    {
                        try 
                        {
                            Producto producto = productoController.findById(idProducto);
                            if (producto != null) 
                            {
                                System.out.println(producto);
                            } 
                            else 
                            {
                                System.out.println("No se encontró un producto con el ID: " + idProducto);
                            }
                        } catch (Exception e) 
                        {
                            System.err.println("Error al buscar el producto: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 3:
                    if (productoController != null) 
                    {
                        try 
                        {
                            System.out.println("Añadiendo nuevo producto...");
                            scanner.nextLine(); // Limpiar buffer
                            
                            System.out.print("Ingrese el nombre del producto: ");
                            String nombre = scanner.nextLine();
                            if (!VerificarDato(nombre)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese la descripción del producto: ");
                            String descripcion = scanner.nextLine();
                            if (!VerificarDato(descripcion)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese el precio del producto: ");
                            double precio = scanner.nextDouble();
                            scanner.nextLine(); // Limpiar buffer
                            
                            System.out.print("Ingrese el stock del producto: ");
                            int stock = scanner.nextInt();
                            scanner.nextLine(); // Limpiar buffer
                            
                            System.out.println("Categorías disponibles:");
                            categoriaController.findAll().forEach(System.out::println);
                            System.out.print("Ingrese el ID de la categoría: ");
                            Long idCategoria = scanner.nextLong();
                            scanner.nextLine(); // Limpiar buffer
                            
                            Categoria categoria = categoriaController.findById(idCategoria);
                            if (categoria == null) 
                            {
                                System.out.println("La categoría seleccionada no existe.");
                                break;
                            }
                            
                            Producto producto = new Producto();
                            producto.setNombre(nombre);
                            producto.setDescripcion(descripcion);
                            producto.setPVP(precio);
                            producto.setStock(stock);
                            producto.setCategoria(categoria);
                            productoController.save(producto);
                            System.out.println("Producto añadido correctamente.");
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al añadir el producto: " + e.getMessage());
                        }
                    }
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 4:
                    if (productoController != null) 
                    {
                        try 
                        {
                            System.out.println("Actualizando producto...");
                            System.out.print("Ingrese el ID del producto: ");
                            Long idProductoActualizar = scanner.nextLong();
                            scanner.nextLine(); // Limpiar buffer
                            
                            Producto productoExistente = productoController.findById(idProductoActualizar);
                            if (productoExistente != null) 
                            {
                                System.out.println("Producto encontrado: " + productoExistente);
                                System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                                
                                System.out.print("Ingrese el nuevo nombre [" + productoExistente.getNombre() + "]: ");
                                String nuevoNombre = scanner.nextLine();
                                if (nuevoNombre.isEmpty()) 
                                {
                                    nuevoNombre = productoExistente.getNombre();
                                }
                                
                                System.out.print("Ingrese la nueva descripción [" + productoExistente.getDescripcion() + "]: ");
                                String nuevaDescripcion = scanner.nextLine();
                                if (nuevaDescripcion.isEmpty()) 
                                {
                                    nuevaDescripcion = productoExistente.getDescripcion();
                                }
                                
                                System.out.print("Ingrese el nuevo precio [" + productoExistente.getPVP() + "]: ");
                                String precioStr = scanner.nextLine();
                                double nuevoPrecio = productoExistente.getPVP();
                                if (!precioStr.isEmpty()) 
                                {
                                    try 
                                    {
                                        nuevoPrecio = Double.parseDouble(precioStr);
                                    } 
                                    catch (NumberFormatException e) 
                                    {
                                        System.out.println("Precio no válido. Se mantendrá el valor actual.");
                                    }
                                }
                                
                                System.out.print("Ingrese el nuevo stock [" + productoExistente.getStock() + "]: ");
                                String stockStr = scanner.nextLine();
                                int nuevoStock = productoExistente.getStock();
                                if (!stockStr.isEmpty()) 
                                {
                                    try 
                                    {
                                        nuevoStock = Integer.parseInt(stockStr);
                                    } 
                                    catch (NumberFormatException e) 
                                    {
                                        System.out.println("Stock no válido. Se mantendrá el valor actual.");
                                    }
                                }
                                
                                System.out.println("¿Desea cambiar la categoría? (S/N): ");
                                String cambiarCategoria = scanner.nextLine().toUpperCase();
                                Categoria nuevaCategoria = productoExistente.getCategoria();
                                
                                if (cambiarCategoria.equals("S")) 
                                {
                                    System.out.println("Categorías disponibles:");
                                    categoriaController.findAll().forEach(System.out::println);
                                    System.out.print("Ingrese el ID de la nueva categoría: ");
                                    Long idNuevaCategoria = scanner.nextLong();
                                    scanner.nextLine(); // Limpiar buffer
                                    
                                    nuevaCategoria = categoriaController.findById(idNuevaCategoria);
                                    if (nuevaCategoria == null) 
                                    {
                                        System.out.println("La categoría seleccionada no existe. Se mantendrá la categoría actual.");
                                        nuevaCategoria = productoExistente.getCategoria();
                                    }
                                }
                                
                                Producto productoActualizado = new Producto();
                                productoActualizado.setID_Producto(idProductoActualizar);
                                productoActualizado.setNombre(nuevoNombre);
                                productoActualizado.setDescripcion(nuevaDescripcion);
                                productoActualizado.setStock(nuevoStock);
                                productoActualizado.setPVP(nuevoPrecio);
                                productoActualizado.setCategoria(nuevaCategoria);
                                productoController.save(productoActualizado);
                                System.out.println("Producto actualizado correctamente.");
                            }
                            else 
                            {
                                System.out.println("No se encontró el producto con ID: " + idProductoActualizar);
                            }
                        } catch (Exception e) 
                        {
                            System.err.println("Error al actualizar el producto: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 5:
                    if (productoController != null) 
                    {
                        try 
                        {
                            System.out.println("Eliminando producto...");
                            System.out.print("Ingrese el ID del producto a eliminar: ");
                            Long idProductoEliminar = scanner.nextLong();
                            
                            Producto productoExistente = productoController.findById(idProductoEliminar);
                            if (productoExistente != null) 
                            {
                                System.out.println("Se eliminará el siguiente producto: " + productoExistente);
                                System.out.print("¿Está seguro? (S/N): ");
                                scanner.nextLine(); 
                                String confirmacion = scanner.nextLine().toUpperCase();
                                
                                if (confirmacion.equals("S")) 
                                {
                                    productoController.delete(idProductoEliminar);
                                    System.out.println("Producto eliminado correctamente.");
                                } 
                                else 
                                {
                                    System.out.println("Operación cancelada.");
                                }
                            } 
                            else 
                            {
                                System.out.println("No se encontró el producto con ID: " + idProductoEliminar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al eliminar el producto: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private void mostrarMenuProveedores() 
    {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE PROVEEDORES ===");
            System.out.println("1. Ver todos los proveedores");
            System.out.println("2. Buscar proveedor por ID");
            System.out.println("3. Añadir nuevo proveedor");
            System.out.println("4. Actualizar proveedor");
            System.out.println("5. Eliminar proveedor");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    System.out.println("Lista de proveedores...");
                    if (proveedorController != null) 
                    {
                        try 
                        {
                            proveedorController.findAll().forEach(System.out::println);
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al obtener la lista de proveedores: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID del proveedor: ");
                    Long idProveedor = scanner.nextLong();
                    System.out.println("Buscando proveedor con ID " + idProveedor + "...");
                    if (proveedorController != null) 
                    {
                        try 
                        {
                            Proveedor proveedor = proveedorController.findById(idProveedor);
                            if (proveedor != null) 
                            {
                                System.out.println(proveedor);
                            } 
                            else 
                            {
                                System.out.println("No se encontró un proveedor con el ID: " + idProveedor);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al buscar el proveedor: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 3:
                    if (proveedorController != null) 
                    {
                        try 
                        {
                            System.out.println("Añadiendo nuevo proveedor...");
                            scanner.nextLine(); // Limpiar buffer
                            
                            System.out.print("Ingrese el CIF del proveedor: ");
                            String cif = scanner.nextLine();
                            if (!VerificarDato(cif)) 
                            {
                                break;
                            }
                            if (!cif.matches("[A-Z]\\d{8}")) 
                            {
                                System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                                break;
                            }
                            
                            System.out.print("Ingrese el nombre de la empresa: ");
                            String nombreEmpresa = scanner.nextLine();
                            if (!VerificarDato(nombreEmpresa)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese el nombre del responsable: ");
                            String nombreResponsable = scanner.nextLine();
                            if (!VerificarDato(nombreResponsable)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese el país del proveedor: ");
                            String pais = scanner.nextLine();
                            if (!VerificarDato(pais)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese la provincia del proveedor: ");
                            String provincia = scanner.nextLine();
                            if (!VerificarDato(provincia)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese la dirección del proveedor: ");
                            String direccion = scanner.nextLine();
                            if (!VerificarDato(direccion)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese el email del proveedor: ");
                            String email = scanner.nextLine();
                            if (!VerificarDato(email)) 
                            {
                                break;
                            }
                            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                            {
                                System.out.println("El email debe ser válido.");
                                break;
                            }
                            
                            System.out.print("Ingrese el teléfono del proveedor: ");
                            String telefono = scanner.nextLine();
                            if (!VerificarDato(telefono)) 
                            {
                                break;
                            }
                            if (!telefono.matches("\\d+")) 
                            {
                                System.out.println("El teléfono debe contener solo números.");
                                break;
                            }
                            
                            System.out.print("Ingrese el código postal del proveedor: ");
                            String codigoPostal = scanner.nextLine();
                            if (!VerificarDato(codigoPostal)) 
                            {
                                break;
                            }
                            if (!codigoPostal.matches("\\d{5}")) 
                            {
                                System.out.println("El código postal debe tener 5 números.");
                                break;
                            }
                            
                            Proveedor proveedor = new Proveedor();
                            proveedor.setCIF(cif);
                            proveedor.setNombre(nombreEmpresa);
                            proveedor.setNombre_Responsable(nombreResponsable);
                            proveedor.setPais(pais);
                            proveedor.setProvincia(provincia);
                            proveedor.setDireccion(direccion);
                            proveedor.setEmail(email);
                            proveedor.setTelefono(telefono);
                            proveedor.setCodigo_Postal(codigoPostal);
                            proveedorController.save(proveedor);
                            System.out.println("Proveedor añadido correctamente.");
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al añadir el proveedor: " + e.getMessage());
                        }
                    }
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 4:
                    if (proveedorController != null) {
                        try 
                        {
                            System.out.println("Actualizando proveedor...");
                            System.out.print("Ingrese el ID del proveedor: ");
                            Long idProveedorActualizar = scanner.nextLong();
                            scanner.nextLine(); 
                            
                            Proveedor proveedorExistente = proveedorController.findById(idProveedorActualizar);
                            if (proveedorExistente != null) 
                            {
                                System.out.println("Proveedor encontrado: " + proveedorExistente);
                                System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                                
                                System.out.print("Ingrese el nuevo CIF [" + proveedorExistente.getCIF() + "]: ");
                                String nuevoCIF = scanner.nextLine();
                                if (nuevoCIF.isEmpty()) 
                                {
                                    nuevoCIF = proveedorExistente.getCIF();
                                }
                                if (!nuevoCIF.matches("[A-Z]\\d{8}")) 
                                {
                                    System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                                    break;
                                }
                                
                                System.out.print("Ingrese el nuevo nombre de la empresa [" + proveedorExistente.getNombre() + "]: ");
                                String nuevoNombreEmpresa = scanner.nextLine();
                                if (nuevoNombreEmpresa.isEmpty()) 
                                {
                                    nuevoNombreEmpresa = proveedorExistente.getNombre();
                                }
                                
                                System.out.print("Ingrese el nuevo nombre del responsable [" + proveedorExistente.getNombre_Responsable() + "]: ");
                                String nuevoNombreResponsable = scanner.nextLine();
                                if (nuevoNombreResponsable.isEmpty()) 
                                {
                                    nuevoNombreResponsable = proveedorExistente.getNombre_Responsable();
                                }
                                
                                System.out.print("Ingrese el nuevo país [" + proveedorExistente.getPais() + "]: ");
                                String nuevoPais = scanner.nextLine();
                                if (nuevoPais.isEmpty()) 
                                {
                                    nuevoPais = proveedorExistente.getPais();
                                }
                                
                                System.out.print("Ingrese la nueva provincia [" + proveedorExistente.getProvincia() + "]: ");
                                String nuevaProvincia = scanner.nextLine();
                                if (nuevaProvincia.isEmpty()) 
                                {
                                    nuevaProvincia = proveedorExistente.getProvincia();
                                }
                                
                                System.out.print("Ingrese la nueva dirección [" + proveedorExistente.getDireccion() + "]: ");
                                String nuevaDireccion = scanner.nextLine();
                                if (nuevaDireccion.isEmpty()) 
                                {
                                    nuevaDireccion = proveedorExistente.getDireccion();
                                }
                                
                                System.out.print("Ingrese el nuevo email [" + proveedorExistente.getEmail() + "]: ");
                                String nuevoEmail = scanner.nextLine();
                                if (nuevoEmail.isEmpty()) 
                                {
                                    nuevoEmail = proveedorExistente.getEmail();
                                }
                                if (!nuevoEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) 
                                {
                                    System.out.println("El email debe ser válido.");
                                    break;
                                }
                                
                                System.out.print("Ingrese el nuevo teléfono [" + proveedorExistente.getTelefono() + "]: ");
                                String nuevoTelefono = scanner.nextLine();
                                if (nuevoTelefono.isEmpty()) 
                                {
                                    nuevoTelefono = proveedorExistente.getTelefono();
                                }
                                if (!nuevoTelefono.matches("\\d+")) 
                                {
                                    System.out.println("El teléfono debe contener solo números.");
                                    break;
                                }
                                
                                System.out.print("Ingrese el nuevo código postal [" + proveedorExistente.getCodigo_Postal() + "]: ");
                                String nuevoCodigoPostal = scanner.nextLine();
                                if (nuevoCodigoPostal.isEmpty()) 
                                {
                                    nuevoCodigoPostal = proveedorExistente.getCodigo_Postal();
                                }
                                if (!nuevoCodigoPostal.matches("\\d{5}")) 
                                {
                                    System.out.println("El código postal debe tener 5 números.");
                                    break;
                                }
                                
                                Proveedor proveedorActualizado = new Proveedor();
                                proveedorActualizado.setID_Proveedor(idProveedorActualizar);
                                proveedorActualizado.setCIF(nuevoCIF);
                                proveedorActualizado.setNombre(nuevoNombreEmpresa);
                                proveedorActualizado.setNombre_Responsable(nuevoNombreResponsable);
                                proveedorActualizado.setPais(nuevoPais);
                                proveedorActualizado.setProvincia(nuevaProvincia);
                                proveedorActualizado.setDireccion(nuevaDireccion);
                                proveedorActualizado.setEmail(nuevoEmail);
                                proveedorActualizado.setTelefono(nuevoTelefono);
                                proveedorActualizado.setCodigo_Postal(nuevoCodigoPostal);
                                proveedorController.save(proveedorActualizado);
                                System.out.println("Proveedor actualizado correctamente.");
                            } 
                            else
                            {
                                System.out.println("No se encontró el proveedor con ID: " + idProveedorActualizar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al actualizar el proveedor: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 5:
                    if (proveedorController != null) 
                    {
                        try 
                        {
                            System.out.println("Eliminando proveedor...");
                            System.out.print("Ingrese el ID del proveedor a eliminar: ");
                            Long idProveedorEliminar = scanner.nextLong();
                            
                            Proveedor proveedorExistente = proveedorController.findById(idProveedorEliminar);
                            if (proveedorExistente != null) 
                            {
                                System.out.println("Se eliminará el siguiente proveedor: " + proveedorExistente);
                                System.out.print("¿Está seguro? (S/N): ");
                                scanner.nextLine(); 
                                String confirmacion = scanner.nextLine().toUpperCase();
                                
                                if (confirmacion.equals("S")) 
                                {
                                    proveedorController.delete(idProveedorEliminar);
                                    System.out.println("Proveedor eliminado correctamente.");
                                } 
                                else 
                                {
                                    System.out.println("Operación cancelada.");
                                }
                            } 
                            else 
                            {
                                System.out.println("No se encontró el proveedor con ID: " + idProveedorEliminar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al eliminar el proveedor: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private void mostrarMenuFacturas() 
    {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE FACTURAS ===");
            System.out.println("1. Ver todas las facturas");
            System.out.println("2. Buscar factura por ID");
            System.out.println("3. Crear nueva factura");
            System.out.println("4. Actualizar factura");
            System.out.println("5. Eliminar factura");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    System.out.println("Lista de facturas...");
                    if (facturaController != null) 
                    {
                        try 
                        {
                            facturaController.findAll().forEach(System.out::println);
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al obtener la lista de facturas: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la factura: ");
                    Long idFactura = scanner.nextLong();
                    System.out.println("Buscando factura con ID " + idFactura + "...");
                    if (facturaController != null) 
                    {
                        try 
                        {
                            Factura factura = facturaController.findById(idFactura);
                            if (factura != null) 
                            {
                                System.out.println(factura);
                                System.out.println("Detalles de la factura:");
                                
                            } 
                            else 
                            {
                                System.out.println("No se encontró una factura con el ID: " + idFactura);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al buscar la factura: " + e.getMessage());
                        }
                    }
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 3:
                    if (facturaController != null) 
                    {
                        try 
                        {
                            System.out.println("Creando nueva factura...");
                            scanner.nextLine(); 
                            
                            System.out.println("Clientes disponibles:");
                            clienteController.findAll().forEach(System.out::println);
                            System.out.print("Ingrese el ID del cliente: ");
                            Long idCliente = scanner.nextLong();
                            scanner.nextLine(); 
                            
                            Cliente cliente = clienteController.findById(idCliente);
                            if (cliente == null) 
                            {
                                System.out.println("El cliente seleccionado no existe.");
                                break;
                            }
                            
                    
                            System.out.println("Empleados disponibles:");
                            empleadoController.findAll().forEach(System.out::println);
                            System.out.print("Ingrese el ID del empleado: ");
                            Long idEmpleado = scanner.nextLong();
                            scanner.nextLine(); 
                            
                            Empleado empleado = empleadoController.findById(idEmpleado);
                            if (empleado == null) 
                            {
                                System.out.println("El empleado seleccionado no existe.");
                                break;
                            }
                            
                            System.out.print("Ingrese la fecha de la factura (YYYY-MM-DD): ");
                            String fechaStr = scanner.nextLine();
                            
                            System.out.print("Ingrese el método de pago: ");
                            String metodoPago = scanner.nextLine();
                            if (!VerificarDato(metodoPago)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese el estado de la factura (Pendiente/Pagada/Cancelada): ");
                            String estado = scanner.nextLine();
                            if (!VerificarDato(estado)) 
                            {
                                break;
                            }
                            
                            
                            Factura factura = new Factura();
                            factura.setCliente(cliente);
                            factura.setEmpleado(empleado);
                            factura.setFecha_Venta(LocalDate.parse(fechaStr));
                            factura.setCanal_Compra(metodoPago);
                            factura.setPagado(estado);
                            facturaController.save(factura);
                            
                            boolean seguirAgregando = true;
                            double totalFactura = 0.0;
                            
                            while (seguirAgregando) 
                            {
                                
                                System.out.println("Productos disponibles:");
                                productoController.findAll().forEach(System.out::println);
                                System.out.print("Ingrese el ID del producto a añadir: ");
                                Long idProducto = scanner.nextLong();
                                scanner.nextLine(); 
                                
                                Producto producto = productoController.findById(idProducto);
                                if (producto == null) 
                                {
                                    System.out.println("El producto seleccionado no existe.");
                                    continue;
                                }
                                
                                System.out.print("Ingrese la cantidad: ");
                                int cantidad = scanner.nextInt();
                                scanner.nextLine();
                                
                                if (cantidad <= 0 || cantidad > producto.getStock()) 
                                {
                                    System.out.println("La cantidad debe ser mayor que 0 y no puede superar el stock disponible (" + producto.getStock() + ").");
                                    continue;
                                }
                                
                                double precioUnitario = producto.getPVP();
                                double subtotal = precioUnitario * cantidad;
                                totalFactura += subtotal;
                                
                                
                                System.out.print("¿Desea agregar otro producto? (S/N): ");
                                String respuesta = scanner.nextLine().toUpperCase();
                                if (!respuesta.equals("S")) 
                                {
                                    seguirAgregando = false;
                                }
                            }
                            
                            
                            factura.setTotal(totalFactura);
                            facturaController.save(factura);
                            
                            System.out.println("Factura creada correctamente con un total de: " + totalFactura + "€");
                            
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al crear la factura: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 4:
                    if (facturaController != null) 
                    {
                        try 
                        {
                            System.out.println("Actualizando factura...");
                            System.out.print("Ingrese el ID de la factura: ");
                            Long idFacturaActualizar = scanner.nextLong();
                            scanner.nextLine(); 
                            
                            Factura facturaExistente = facturaController.findById(idFacturaActualizar);
                            if (facturaExistente != null) 
                            {
                                System.out.println("Factura encontrada: " + facturaExistente);
                                System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                                
                                System.out.print("¿Desea cambiar el cliente? (S/N): ");
                                String cambiarCliente = scanner.nextLine().toUpperCase();
                                Cliente nuevoCliente = facturaExistente.getCliente();
                                
                                if (cambiarCliente.equals("S")) 
                                {
                                    System.out.println("Clientes disponibles:");
                                    clienteController.findAll().forEach(System.out::println);
                                    System.out.print("Ingrese el ID del nuevo cliente: ");
                                    Long idNuevoCliente = scanner.nextLong();
                                    scanner.nextLine(); 
                                    
                                    nuevoCliente = clienteController.findById(idNuevoCliente);
                                    if (nuevoCliente == null) 
                                    {
                                        System.out.println("El cliente seleccionado no existe. Se mantendrá el cliente actual.");
                                        nuevoCliente = facturaExistente.getCliente();
                                    }
                                }
                                
                                System.out.print("¿Desea cambiar el empleado? (S/N): ");
                                String cambiarEmpleado = scanner.nextLine().toUpperCase();
                                Empleado nuevoEmpleado = facturaExistente.getEmpleado();
                                
                                if (cambiarEmpleado.equals("S")) 
                                {
                                    System.out.println("Empleados disponibles:");
                                    empleadoController.findAll().forEach(System.out::println);
                                    System.out.print("Ingrese el ID del nuevo empleado: ");
                                    Long idNuevoEmpleado = scanner.nextLong();
                                    scanner.nextLine(); 
                                    
                                    nuevoEmpleado = empleadoController.findById(idNuevoEmpleado);
                                    if (nuevoEmpleado == null) 
                                    {
                                        System.out.println("El empleado seleccionado no existe. Se mantendrá el empleado actual.");
                                        nuevoEmpleado = facturaExistente.getEmpleado();
                                    }
                                }
                                
                                System.out.print("Ingrese la nueva fecha (YYYY-MM-DD) [" + facturaExistente.getFecha_Venta() + "]: ");
                                String nuevaFechaStr = scanner.nextLine();
                                LocalDate nuevaFecha;
                                if (nuevaFechaStr.isEmpty()) 
                                {
                                    nuevaFecha = facturaExistente.getFecha_Venta();
                                } 
                                else 
                                {
                                    nuevaFecha = LocalDate.parse(nuevaFechaStr);
                                }
                                
                                System.out.print("Ingrese el nuevo método de pago [" + facturaExistente.getCanal_Compra() + "]: ");
                                String nuevoMetodoPago = scanner.nextLine();
                                if (nuevoMetodoPago.isEmpty()) 
                                {
                                    nuevoMetodoPago = facturaExistente.getCanal_Compra();
                                }
                                
                                System.out.print("Ingrese el nuevo estado (Pendiente/Pagada/Cancelada) [" + facturaExistente.getPagado() + "]: ");
                                String nuevoEstado = scanner.nextLine();
                                if (nuevoEstado.isEmpty()) 
                                {
                                    nuevoEstado = facturaExistente.getPagado();
                                }
                                
                                Factura facturaActualizada = new Factura();
                                facturaActualizada.setID_Factura(idFacturaActualizar);
                                facturaActualizada.setCliente(nuevoCliente);
                                facturaActualizada.setEmpleado(nuevoEmpleado);
                                facturaActualizada.setFecha_Venta(nuevaFecha);
                                facturaActualizada.setCanal_Compra(nuevoMetodoPago);
                                facturaActualizada.setPagado(nuevoEstado);
                                facturaActualizada.setTotal(facturaExistente.getTotal());
                                
                                facturaController.save(facturaActualizada);
                                System.out.println("Factura actualizada correctamente.");
                                
                            } 
                            else 
                            {
                                System.out.println("No se encontró la factura con ID: " + idFacturaActualizar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al actualizar la factura: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 5:
                    if (facturaController != null) 
                    {
                        try 
                        {
                            System.out.println("Eliminando factura...");
                            System.out.print("Ingrese el ID de la factura a eliminar: ");
                            Long idFacturaEliminar = scanner.nextLong();
                            
                            Factura facturaExistente = facturaController.findById(idFacturaEliminar);
                            if (facturaExistente != null) 
                            {
                                System.out.println("Se eliminará la siguiente factura: " + facturaExistente);
                                System.out.print("¿Está seguro? (S/N): ");
                                scanner.nextLine(); 
                                String confirmacion = scanner.nextLine().toUpperCase();
                                
                                if (confirmacion.equals("S")) 
                                {
                                    
                                    facturaController.delete(idFacturaEliminar);
                                    System.out.println("Factura eliminada correctamente.");
                                } 
                                else 
                                {
                                    System.out.println("Operación cancelada.");
                                }
                            } 
                            else 
                            {
                                System.out.println("No se encontró la factura con ID: " + idFacturaEliminar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al eliminar la factura: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private void mostrarMenuCategorias() 
    {
        boolean volver = false;
        
        while (!volver) 
        {
            System.out.println("\n=== GESTIÓN DE CATEGORÍAS ===");
            System.out.println("1. Ver todas las categorías");
            System.out.println("2. Buscar categoría por ID");
            System.out.println("3. Añadir nueva categoría");
            System.out.println("4. Actualizar categoría");
            System.out.println("5. Eliminar categoría");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    System.out.println("Lista de categorías...");
                    if (categoriaController != null) 
                    {
                        try 
                        {
                            categoriaController.findAll().forEach(System.out::println);
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al obtener la lista de categorías: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la categoría: ");
                    Long idCategoria = scanner.nextLong();
                    System.out.println("Buscando categoría con ID " + idCategoria + "...");
                    if (categoriaController != null) 
                    {
                        try 
                        {
                            Categoria categoria = categoriaController.findById(idCategoria);
                            if (categoria != null) 
                            {
                                System.out.println(categoria);
                            } 
                            else 
                            {
                                System.out.println("No se encontró una categoría con el ID: " + idCategoria);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al buscar la categoría: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 3:
                    if (categoriaController != null) 
                    {
                        try 
                        {
                            System.out.println("Añadiendo nueva categoría...");
                            scanner.nextLine(); 
                            
                            System.out.print("Ingrese el nombre de la categoría: ");
                            String nombre = scanner.nextLine();
                            if (!VerificarDato(nombre)) 
                            {
                                break;
                            }
                            
                            System.out.print("Ingrese la descripción de la categoría: ");
                            String descripcion = scanner.nextLine();
                            if (!VerificarDato(descripcion)) 
                            {
                                break;
                            }
                            
                            Categoria categoria = new Categoria();
                            categoria.setNombre(nombre);
                            categoriaController.save(categoria);
                            System.out.println("Categoría añadida correctamente.");
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al añadir la categoría: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 4:
                    if (categoriaController != null) 
                    {
                        try 
                        {
                            System.out.println("Actualizando categoría...");
                            System.out.print("Ingrese el ID de la categoría: ");
                            Long idCategoriaActualizar = scanner.nextLong();
                            scanner.nextLine(); 
                            
                            Categoria categoriaExistente = categoriaController.findById(idCategoriaActualizar);
                            if (categoriaExistente != null) 
                            {
                                System.out.println("Categoría encontrada: " + categoriaExistente);
                                System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                                
                                System.out.print("Ingrese el nuevo nombre [" + categoriaExistente.getNombre() + "]: ");
                                String nuevoNombre = scanner.nextLine();
                                if (nuevoNombre.isEmpty()) 
                                {
                                    nuevoNombre = categoriaExistente.getNombre();
                                }
                                
                                System.out.print("Ingrese la nueva descripción [" + categoriaExistente.getNombre() + "]: ");
                                String nuevaDescripcion = scanner.nextLine();
                                if (nuevaDescripcion.isEmpty()) 
                                {
                                    nuevaDescripcion = categoriaExistente.getNombre();
                                }
                                
                                Categoria categoriaActualizada = new Categoria();
                                categoriaActualizada.setID_Categoria(idCategoriaActualizar);
                                categoriaActualizada.setNombre(nuevoNombre);
                                categoriaController.save(categoriaActualizada);
                                System.out.println("Categoría actualizada correctamente.");
                            } 
                            else 
                            {
                                System.out.println("No se encontró la categoría con ID: " + idCategoriaActualizar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al actualizar la categoría: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 5:
                    if (categoriaController != null) 
                    {
                        try 
                        {
                            System.out.println("Eliminando categoría...");
                            System.out.print("Ingrese el ID de la categoría a eliminar: ");
                            Long idCategoriaEliminar = scanner.nextLong();
                            
                            Categoria categoriaExistente = categoriaController.findById(idCategoriaEliminar);
                            if (categoriaExistente != null) 
                            {
                                boolean tieneProdudos = false;
                                for (Producto producto : productoController.findAll()) 
                                {
                                    if (producto.getCategoria().getID_Categoria().equals(idCategoriaEliminar)) 
                                    {
                                        tieneProdudos = true;
                                        break;
                                    }
                                }
                                
                                if (tieneProdudos) 
                                {
                                    System.out.println("No se puede eliminar la categoría porque tiene productos asociados.");
                                    break;
                                }
                                
                                System.out.println("Se eliminará la siguiente categoría: " + categoriaExistente);
                                System.out.print("¿Está seguro? (S/N): ");
                                scanner.nextLine(); 
                                String confirmacion = scanner.nextLine().toUpperCase();
                                
                                if (confirmacion.equals("S")) 
                                {
                                    categoriaController.delete(idCategoriaEliminar);
                                    System.out.println("Categoría eliminada correctamente.");
                                }
                                else 
                                {
                                    System.out.println("Operación cancelada.");
                                }
                            } 
                            else 
                            {
                                System.out.println("No se encontró la categoría con ID: " + idCategoriaEliminar);
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.err.println("Error al eliminar la categoría: " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private boolean VerificarDato(String dato) 
    {
        if (dato.isEmpty())
        {
            System.out.println("El dato no puede estar vacío.");
            return false;
        }
        return true;
    }

    
    public void cerrarScanner() 
    {
        if (scanner != null) 
        {
            scanner.close();
        }
    }
}
