package org.carlosydiego.crmclientes.app.menuproyecto;

import java.util.Scanner;
import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.model.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.carlosydiego.crmclientes.app.database.DatabaseConnection;

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
    
    public MenuProyecto() {
        this.scanner = new Scanner(System.in);
        inicializarControladores();
    }
    
    private void inicializarControladores() {
        try {
            // Intentar establecer la conexión a la base de datos
            this.connection = DatabaseConnection.getInstance();
            
            // Inicializar todos los controladores que necesitan conexión a BD
            this.clienteController = new ClienteController();
            this.empleadoController = new EmpleadoController();
            this.productoController = new ProductoController();
            this.proveedorController = new ProveedorController();
            this.facturaController = new FacturaController();
            this.categoriaController = new CategoriaController();
            this.proveeController = new ProveeController();
            
            System.out.println("Conexión a la base de datos establecida correctamente");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void mostrarMenuPrincipal() {
        boolean salir = false;
        
        while (!salir) {
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
            switch (opcion) {
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
    
    private void mostrarMenuClientes() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Ver todos los clientes");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Añadir nuevo cliente");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Lista de clientes...");
                    if (clienteController != null) {
                        try {
                            clienteController.findAll().forEach(System.out::println);
                        } catch (Exception e) {
                            System.err.println("Error al obtener la lista de clientes: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el ID del cliente: ");
                    Long idCliente = scanner.nextLong();
                    System.out.println("Buscando cliente con ID " + idCliente + "...");
                    if (clienteController != null) {
                        try {
                            Cliente cliente = clienteController.findById(idCliente);
                            if (cliente != null) {
                                System.out.println(cliente);
                            } else {
                                System.out.println("No se encontró un cliente con el ID: " + idCliente);
                            }
                        } catch (Exception e) {
                            System.err.println("Error al buscar el cliente: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 3:
                    if (clienteController != null) {
                        try {
                            System.out.println("Añadiendo nuevo cliente...");
                            scanner.nextLine(); // Limpiar buffer
                            System.out.print("Ingrese el CIF del cliente: ");
                            String cif = scanner.nextLine();
                            if (!VerificarDato(cif))
                            {
                                break;
                            }
                            if (!cif.matches("[A-Z]\\d{8}")) {
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
                            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                                System.out.println("El email debe ser válido.");
                                break;
                            }
                            System.out.print("Ingrese el teléfono del cliente: ");
                            String telefono = scanner.nextLine();
                            if (!VerificarDato(telefono))
                            {
                                break;
                            }
                            if (!telefono.matches("\\d+")) {
                                System.out.println("El teléfono debe contener solo números.");
                                break;
                            }
                            System.out.print("Ingrese el código postal del cliente: ");
                            String codigoPostal = scanner.nextLine();
                            if (!VerificarDato(codigoPostal))
                            {
                                break;
                            }
                            if (!codigoPostal.matches("\\d{5}")) {
                                System.out.println("El código postal debe tener 5 números.");
                                break;
                            }
                            Cliente cliente = new Cliente(cif, nombreEmpresa, nombreResponsable, pais, provincia, direccion, email, telefono, codigoPostal);
                            clienteController.save(cliente);
                            System.out.println("Cliente añadido correctamente.");
                        } catch (Exception e) {
                            System.err.println("Error al añadir el cliente: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 4:
                    if (clienteController != null) {
                        try {
                            System.out.println("Actualizando cliente...");
                            System.out.print("Ingrese el ID del cliente: ");
                            Long idClienteActualizar = scanner.nextLong();
                            scanner.nextLine(); // Limpiar buffer
                            
                            Cliente clienteExistente = clienteController.findById(idClienteActualizar);
                            if (clienteExistente != null) {
                                System.out.println("Cliente encontrado: " + clienteExistente);
                                System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                                
                                System.out.print("Ingrese el nuevo CIF del cliente [" + clienteExistente.getCIF() + "]: ");
                                String nuevoCIF = scanner.nextLine();
                                if (nuevoCIF.isEmpty())
                                {
                                    nuevoCIF = clienteExistente.getCIF();
                                }
                                if (!nuevoCIF.matches("[A-Z]\\d{8}")) {
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
                                if (!nuevoEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                                    System.out.println("El email debe ser válido.");
                                    break;
                                }
                                System.out.print("Ingrese el nuevo teléfono [" + clienteExistente.getTelefono() + "]: ");
                                String nuevoTelefono = scanner.nextLine();
                                if (nuevoTelefono.isEmpty())
                                {
                                    nuevoTelefono = clienteExistente.getTelefono();
                                }
                                if (!nuevoTelefono.matches("\\d+")) {
                                    System.out.println("El teléfono debe contener solo números.");
                                    break;
                                }

                                System.out.print("Ingrese el nuevo código postal [" + clienteExistente.getCodigo_Postal() + "]: ");
                                String nuevoCodigoPostal = scanner.nextLine();
                                if (nuevoCodigoPostal.isEmpty())
                                {
                                    nuevoCodigoPostal = clienteExistente.getCodigo_Postal();
                                }
                                if (!nuevoCodigoPostal.matches("\\d{5}")) {
                                    System.out.println("El código postal debe tener 5 números.");
                                    break;
                                }
                                

                                Cliente clienteActualizado = new Cliente(nuevoCIF, nuevoNombreEmpresa, nuevoNombreResponsable, 
                                                                      nuevoPais, nuevaProvincia, nuevaDireccion, nuevoEmail, nuevoTelefono, nuevoCodigoPostal);
                                clienteActualizado.setID_Cliente(idClienteActualizar);
                                clienteController.save(clienteActualizado);
                                System.out.println("Cliente actualizado correctamente.");
                            } else {
                                System.out.println("No se encontró el cliente con ID: " + idClienteActualizar);
                            }
                        } catch (Exception e) {
                            System.err.println("Error al actualizar el cliente: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Error: No hay conexión con la base de datos");
                    }
                    break;
                case 5:
                    if (clienteController != null) {
                        try {
                            System.out.println("Eliminando cliente...");
                            System.out.print("Ingrese el ID del cliente a eliminar: ");
                            Long idClienteEliminar = scanner.nextLong();
                            
                            Cliente clienteExistente = clienteController.findById(idClienteEliminar);
                            if (clienteExistente != null) {
                                System.out.println("Se eliminará el siguiente cliente: " + clienteExistente);
                                System.out.print("¿Está seguro? (S/N): ");
                                scanner.nextLine(); 
                                String confirmacion = scanner.nextLine().toUpperCase();
                                
                                if (confirmacion.equals("S")) {
                                    clienteController.delete(idClienteEliminar);
                                    System.out.println("Cliente eliminado correctamente.");
                                } else {
                                    System.out.println("Operación cancelada.");
                                }
                            } else {
                                System.out.println("No se encontró el cliente con ID: " + idClienteEliminar);
                            }
                        } catch (Exception e) {
                            System.err.println("Error al eliminar el cliente: " + e.getMessage());
                        }
                    } else {
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
    
    private void mostrarMenuEmpleados() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
            System.out.println("1. Ver todos los empleados");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Añadir nuevo empleado");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Funcionalidad aún no implementada.");
            }
        }
    }
    
    private void mostrarMenuProductos() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Añadir nuevo producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Funcionalidad aún no implementada.");
            }
        }
    }
    
    private void mostrarMenuProveedores() {
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
            
            switch (opcion) {
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Funcionalidad aún no implementada.");
            }
        }
    }
    
    private void mostrarMenuFacturas() {
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
            
            switch (opcion) {
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Funcionalidad aún no implementada.");
            }
        }
    }
    
    private void mostrarMenuCategorias() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE CATEGORÍAS ===");
            System.out.println("1. Ver todas las categorías");
            System.out.println("2. Buscar categoría por ID");
            System.out.println("3. Añadir nueva categoría");
            System.out.println("4. Actualizar categoría");
            System.out.println("5. Eliminar categoría");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Funcionalidad aún no implementada.");
            }
        }
    }

    private boolean VerificarDato(String dato) {
        if (dato.isEmpty())
        {
            System.out.println("El dato no puede estar vacío.");
            return false;
        }
        return true;
    }

    
    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
