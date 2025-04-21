package org.carlosydiego.crmclientes.app.menuproyecto;

import org.carlosydiego.crmclientes.app.controller.*;
import org.carlosydiego.crmclientes.app.database.DatabaseConnection;
import org.carlosydiego.crmclientes.app.model.Cliente;
import org.carlosydiego.crmclientes.app.model.Categoria;
import org.carlosydiego.crmclientes.app.model.Empleado;
import org.carlosydiego.crmclientes.app.model.Factura;
import org.carlosydiego.crmclientes.app.model.Producto;
import org.carlosydiego.crmclientes.app.model.Proveedor;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Submenus {
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
    
    protected void listarClientes() 
    {
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
    }
    
    protected void buscarClientePorId() 
    {
        if (clienteController != null) 
        {
            try 
            {
                System.out.println("Buscando cliente...");
                Long idCliente = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del cliente: ");
                        idCliente = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                System.out.println("Buscando cliente con ID " + idCliente + "...");
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
    }
    
    protected void añadirCliente() 
    {
        if (clienteController != null) 
        {
            try 
            {
                System.out.println("Añadiendo nuevo cliente...");
                scanner.nextLine();
                
                String cif = "";
                boolean cifValido = false;
                while (!cifValido) {
                    System.out.print("Ingrese el CIF del cliente: ");
                    cif = scanner.nextLine();
                    if (cif.isEmpty()) {
                        System.out.println("El CIF no puede estar vacío.");
                    } else if (!cif.matches("[A-Z]\\d{8}")) {
                        System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                    } else {
                        cifValido = true;
                    }
                }
                
                String nombreEmpresa = "";
                boolean nombreEmpresaValido = false;
                while (!nombreEmpresaValido) {
                    System.out.print("Ingrese el nombre de la empresa: ");
                    nombreEmpresa = scanner.nextLine();
                    if (nombreEmpresa.isEmpty()) {
                        System.out.println("El nombre de la empresa no puede estar vacío.");
                    } else {
                        nombreEmpresaValido = true;
                    }
                }
                
                String nombreResponsable = "";
                boolean nombreResponsableValido = false;
                while (!nombreResponsableValido) {
                    System.out.print("Ingrese el nombre del responsable de la empresa: ");
                    nombreResponsable = scanner.nextLine();
                    if (nombreResponsable.isEmpty()) {
                        System.out.println("El nombre del responsable no puede estar vacío.");
                    } else {
                        nombreResponsableValido = true;
                    }
                }
                
                String pais = "";
                boolean paisValido = false;
                while (!paisValido) {
                    System.out.print("Ingrese el país del cliente: ");
                    pais = scanner.nextLine();
                    if (pais.isEmpty()) {
                        System.out.println("El país no puede estar vacío.");
                    } else {
                        paisValido = true;
                    }
                }
                
                String provincia = "";
                boolean provinciaValida = false;
                while (!provinciaValida) {
                    System.out.print("Ingrese la provincia del cliente: ");
                    provincia = scanner.nextLine();
                    if (provincia.isEmpty()) {
                        System.out.println("La provincia no puede estar vacía.");
                    } else {
                        provinciaValida = true;
                    }
                }
                
                String direccion = "";
                boolean direccionValida = false;
                while (!direccionValida) {
                    System.out.print("Ingrese la dirección del cliente: ");
                    direccion = scanner.nextLine();
                    if (direccion.isEmpty()) {
                        System.out.println("La dirección no puede estar vacía.");
                    } else {
                        direccionValida = true;
                    }
                }
                
                String email = "";
                boolean emailValido = false;
                while (!emailValido) {
                    System.out.print("Ingrese el email del cliente: ");
                    email = scanner.nextLine();
                    if (email.isEmpty()) {
                        System.out.println("El email no puede estar vacío.");
                    } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                        System.out.println("El email debe tener un formato válido.");
                    } else {
                        emailValido = true;
                    }
                }
                
                String telefono = "";
                boolean telefonoValido = false;
                while (!telefonoValido) {
                    System.out.print("Ingrese el teléfono del cliente: ");
                    telefono = scanner.nextLine();
                    if (telefono.isEmpty()) {
                        System.out.println("El teléfono no puede estar vacío.");
                    } else if (!telefono.matches("\\d+")) {
                        System.out.println("El teléfono debe contener solo números.");
                    } else {
                        telefonoValido = true;
                    }
                }
                
                String codigoPostal = "";
                boolean cpValido = false;
                while (!cpValido) {
                    System.out.print("Ingrese el código postal del cliente: ");
                    codigoPostal = scanner.nextLine();
                    if (codigoPostal.isEmpty()) {
                        System.out.println("El código postal no puede estar vacío.");
                    } else if (!codigoPostal.matches("\\d{5}")) {
                        System.out.println("El código postal debe tener 5 números.");
                    } else {
                        cpValido = true;
                    }
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
    }
    
    protected void actualizarCliente() 
    {
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
                    
                    String nuevoCIF = "";
                    boolean cifValido = false;
                    while (!cifValido) {
                        System.out.print("Ingrese el nuevo CIF del cliente [" + clienteExistente.getCIF() + "]: ");
                        nuevoCIF = scanner.nextLine();
                        if (nuevoCIF.isEmpty()) {
                            nuevoCIF = clienteExistente.getCIF();
                            cifValido = true;
                        } else if (!nuevoCIF.matches("[A-Z]\\d{8}")) {
                            System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                        } else {
                            cifValido = true;
                        }
                    }
                    
                    String nuevoNombreEmpresa = "";
                    boolean nombreEmpresaValido = false;
                    while (!nombreEmpresaValido) {
                        System.out.print("Ingrese el nuevo nombre de la empresa [" + clienteExistente.getNombre_Empresa() + "]: ");
                        nuevoNombreEmpresa = scanner.nextLine();
                        if (nuevoNombreEmpresa.isEmpty()) {
                            nuevoNombreEmpresa = clienteExistente.getNombre_Empresa();
                            nombreEmpresaValido = true;
                        } else {
                            nombreEmpresaValido = true;
                        }
                    }
                    
                    String nuevoNombreResponsable = "";
                    boolean nombreResponsableValido = false;
                    while (!nombreResponsableValido) {
                        System.out.print("Ingrese el nuevo nombre del responsable [" + clienteExistente.getNombre_Responsable() + "]: ");
                        nuevoNombreResponsable = scanner.nextLine();
                        if (nuevoNombreResponsable.isEmpty()) {
                            nuevoNombreResponsable = clienteExistente.getNombre_Responsable();
                            nombreResponsableValido = true;
                        } else {
                            nombreResponsableValido = true;
                        }
                    }
                    
                    String nuevoPais = "";
                    boolean paisValido = false;
                    while (!paisValido) {
                        System.out.print("Ingrese el nuevo país [" + clienteExistente.getPais() + "]: ");
                        nuevoPais = scanner.nextLine();
                        if (nuevoPais.isEmpty()) {
                            nuevoPais = clienteExistente.getPais();
                            paisValido = true;
                        } else {
                            paisValido = true;
                        }
                    }
                    
                    String nuevaProvincia = "";
                    boolean provinciaValida = false;
                    while (!provinciaValida) {
                        System.out.print("Ingrese la nueva provincia [" + clienteExistente.getProvincia() + "]: ");
                        nuevaProvincia = scanner.nextLine();
                        if (nuevaProvincia.isEmpty()) {
                            nuevaProvincia = clienteExistente.getProvincia();
                            provinciaValida = true;
                        } else {
                            provinciaValida = true;
                        }
                    }
                    
                    String nuevaDireccion = "";
                    boolean direccionValida = false;
                    while (!direccionValida) {
                        System.out.print("Ingrese la nueva dirección [" + clienteExistente.getDireccion() + "]: ");
                        nuevaDireccion = scanner.nextLine();
                        if (nuevaDireccion.isEmpty()) {
                            nuevaDireccion = clienteExistente.getDireccion();
                            direccionValida = true;
                        } else {
                            direccionValida = true;
                        }
                    }
                    
                    String nuevoEmail = "";
                    boolean emailValido = false;
                    while (!emailValido) {
                        System.out.print("Ingrese el nuevo email [" + clienteExistente.getEmail() + "]: ");
                        nuevoEmail = scanner.nextLine();
                        if (nuevoEmail.isEmpty()) {
                            nuevoEmail = clienteExistente.getEmail();
                            emailValido = true;
                        } else if (!nuevoEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                            System.out.println("El email debe tener un formato válido.");
                        } else {
                            emailValido = true;
                        }
                    }
                    
                    String nuevoTelefono = "";
                    boolean telefonoValido = false;
                    while (!telefonoValido) {
                        System.out.print("Ingrese el nuevo teléfono [" + clienteExistente.getTelefono() + "]: ");
                        nuevoTelefono = scanner.nextLine();
                        if (nuevoTelefono.isEmpty()) {
                            nuevoTelefono = clienteExistente.getTelefono();
                            telefonoValido = true;
                        } else if (!nuevoTelefono.matches("\\d+")) {
                            System.out.println("El teléfono debe contener solo números.");
                        } else {
                            telefonoValido = true;
                        }
                    }
                    
                    String nuevoCodigoPostal = "";
                    boolean cpValido = false;
                    while (!cpValido) {
                        System.out.print("Ingrese el nuevo código postal [" + clienteExistente.getCodigo_Postal() + "]: ");
                        nuevoCodigoPostal = scanner.nextLine();
                        if (nuevoCodigoPostal.isEmpty()) {
                            nuevoCodigoPostal = clienteExistente.getCodigo_Postal();
                            cpValido = true;
                        } else if (!nuevoCodigoPostal.matches("\\d{5}")) {
                            System.out.println("El código postal debe tener 5 números.");
                        } else {
                            cpValido = true;
                        }
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
    }
    
    protected void eliminarCliente() 
    {
        if (clienteController != null) 
        {
            try 
            {
                System.out.println("Eliminando cliente...");
                System.out.print("Ingrese el ID del cliente: ");
                Long idClienteEliminar = scanner.nextLong();
                scanner.nextLine(); 
                
                Cliente clienteExistente = clienteController.findById(idClienteEliminar);
                if (clienteExistente != null) 
                {
                    System.out.println("Cliente encontrado: " + clienteExistente);
                    boolean respuestaValida = false;
                    String respuesta = "";
                    
                    while (!respuestaValida) {
                        System.out.print("¿Está seguro que desea eliminar este cliente? (S/N): ");
                        respuesta = scanner.nextLine().trim().toUpperCase();
                        
                        if (respuesta.equals("S") || respuesta.equals("N")) {
                            respuestaValida = true;
                        } else {
                            System.out.println("Por favor, ingrese S para confirmar o N para cancelar.");
                        }
                    }
                    
                    if (respuesta.equals("S")) 
                    {
                        clienteController.delete(idClienteEliminar);
                        System.out.println("Cliente eliminado correctamente.");
                    } 
                    else 
                    {
                        System.out.println("Operación cancelada por el usuario.");
                    }
                } 
                else 
                {
                    System.out.println("No se encontró el cliente con ID: " + idClienteEliminar);
                }
            } 
            catch (InputMismatchException e) 
            {
                System.err.println("Error: Debe ingresar un número válido para el ID.");
                scanner.nextLine(); 
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
    }

    protected void listarEmpleados() 
    {
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
    }
    
    protected void buscarEmpleadoPorId() 
    {
        if (empleadoController != null) 
        {
            try 
            {
                System.out.println("Buscando empleado...");
                Long idEmpleado = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del empleado: ");
                        idEmpleado = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                System.out.println("Buscando empleado con ID " + idEmpleado + "...");
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
    }
    
    protected void añadirEmpleado() 
    {
        if (empleadoController != null) 
        {
            try 
            {
                System.out.println("Añadiendo nuevo empleado...");
                scanner.nextLine();
                
                String dni = "";
                boolean dniValido = false;
                while (!dniValido) {
                    System.out.print("Ingrese el DNI del empleado: ");
                    dni = scanner.nextLine();
                    if (dni.isEmpty()) {
                        System.out.println("El DNI no puede estar vacío.");
                    } else if (!dni.matches("\\d{8}[A-Z]")) {
                        System.out.println("El DNI debe tener 8 números seguidos de 1 letra.");
                    } else {
                        dniValido = true;
                    }
                }
                
                String nombre = "";
                boolean nombreValido = false;
                while (!nombreValido) {
                    System.out.print("Ingrese el nombre del empleado: ");
                    nombre = scanner.nextLine();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                    } else {
                        nombreValido = true;
                    }
                }
                
                String apellidos = "";
                boolean apellidosValidos = false;
                while (!apellidosValidos) {
                    System.out.print("Ingrese los apellidos del empleado: ");
                    apellidos = scanner.nextLine();
                    if (apellidos.isEmpty()) {
                        System.out.println("Los apellidos no pueden estar vacíos.");
                    } else {
                        apellidosValidos = true;
                    }
                }
                
                String direccion = "";
                boolean direccionValida = false;
                while (!direccionValida) {
                    System.out.print("Ingrese la dirección del empleado: ");
                    direccion = scanner.nextLine();
                    if (direccion.isEmpty()) {
                        System.out.println("La dirección no puede estar vacía.");
                    } else {
                        direccionValida = true;
                    }
                }
                
                String codigoPostal = "";
                boolean cpValido = false;
                while (!cpValido) {
                    System.out.print("Ingrese el código postal del empleado: ");
                    codigoPostal = scanner.nextLine();
                    if (codigoPostal.isEmpty()) {
                        System.out.println("El código postal no puede estar vacío.");
                    } else if (!codigoPostal.matches("\\d{5}")) {
                        System.out.println("El código postal debe tener 5 números.");
                    } else {
                        cpValido = true;
                    }
                }
                
                String provincia = "";
                boolean provinciaValida = false;
                while (!provinciaValida) {
                    System.out.print("Ingrese la provincia del empleado: ");
                    provincia = scanner.nextLine();
                    if (provincia.isEmpty()) {
                        System.out.println("La provincia no puede estar vacía.");
                    } else {
                        provinciaValida = true;
                    }
                }
                
                String pais = "";
                boolean paisValido = false;
                while (!paisValido) {
                    System.out.print("Ingrese el país del empleado: ");
                    pais = scanner.nextLine();
                    if (pais.isEmpty()) {
                        System.out.println("El país no puede estar vacío.");
                    } else {
                        paisValido = true;
                    }
                }
                
                String email = "";
                boolean emailValido = false;
                while (!emailValido) {
                    System.out.print("Ingrese el email del empleado: ");
                    email = scanner.nextLine();
                    if (email.isEmpty()) {
                        System.out.println("El email no puede estar vacío.");
                    } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                        System.out.println("El email debe tener un formato válido.");
                    } else {
                        emailValido = true;
                    }
                }
                
                String telefono = "";
                boolean telefonoValido = false;
                while (!telefonoValido) {
                    System.out.print("Ingrese el teléfono del empleado: ");
                    telefono = scanner.nextLine();
                    if (telefono.isEmpty()) {
                        System.out.println("El teléfono no puede estar vacío.");
                    } else if (!telefono.matches("\\d+")) {
                        System.out.println("El teléfono debe contener solo números.");
                    } else {
                        telefonoValido = true;
                    }
                }
                
                Empleado empleado = new Empleado();
                empleado.setNIF(dni);
                empleado.setNombre(nombre);
                empleado.setApellido(apellidos);
                empleado.setDireccion(direccion);
                empleado.setCodigo_Postal(codigoPostal);
                empleado.setProvincia(provincia);
                empleado.setPaís(pais);
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
    }
    
    protected void actualizarEmpleado() 
    {
        if (empleadoController != null) 
        {
            try 
            {
                System.out.println("Actualizando empleado...");
                Long idEmpleadoActualizar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del empleado: ");
                        idEmpleadoActualizar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Empleado empleadoExistente = empleadoController.findById(idEmpleadoActualizar);
                if (empleadoExistente != null) 
                {
                    System.out.println("Empleado encontrado: " + empleadoExistente);
                    System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                    
                    String nuevoDNI = "";
                    boolean dniValido = false;
                    while (!dniValido) {
                        System.out.print("Ingrese el nuevo DNI [" + empleadoExistente.getNIF() + "]: ");
                        nuevoDNI = scanner.nextLine();
                        if (nuevoDNI.isEmpty()) {
                            nuevoDNI = empleadoExistente.getNIF();
                            dniValido = true;
                        } else if (!nuevoDNI.matches("\\d{8}[A-Z]")) {
                            System.out.println("El DNI debe tener 8 números seguidos de 1 letra.");
                        } else {
                            dniValido = true;
                        }
                    }
                    
                    String nuevoNombre = "";
                    boolean nombreValido = false;
                    while (!nombreValido) {
                        System.out.print("Ingrese el nuevo nombre [" + empleadoExistente.getNombre() + "]: ");
                        nuevoNombre = scanner.nextLine();
                        if (nuevoNombre.isEmpty()) {
                            nuevoNombre = empleadoExistente.getNombre();
                            nombreValido = true;
                        } else {
                            nombreValido = true;
                        }
                    }
                    
                    String nuevosApellidos = "";
                    boolean apellidosValidos = false;
                    while (!apellidosValidos) {
                        System.out.print("Ingrese los nuevos apellidos [" + empleadoExistente.getApellido() + "]: ");
                        nuevosApellidos = scanner.nextLine();
                        if (nuevosApellidos.isEmpty()) {
                            nuevosApellidos = empleadoExistente.getApellido();
                            apellidosValidos = true;
                        } else {
                            apellidosValidos = true;
                        }
                    }
                    
                    String nuevaDireccion = "";
                    boolean direccionValida = false;
                    while (!direccionValida) {
                        System.out.print("Ingrese la nueva dirección [" + empleadoExistente.getDireccion() + "]: ");
                        nuevaDireccion = scanner.nextLine();
                        if (nuevaDireccion.isEmpty()) {
                            nuevaDireccion = empleadoExistente.getDireccion();
                            direccionValida = true;
                        } else {
                            direccionValida = true;
                        }
                    }
                    
                    String nuevoCodigoPostal = "";
                    boolean cpValido = false;
                    while (!cpValido) {
                        System.out.print("Ingrese el nuevo código postal [" + empleadoExistente.getCodigo_Postal() + "]: ");
                        nuevoCodigoPostal = scanner.nextLine();
                        if (nuevoCodigoPostal.isEmpty()) {
                            nuevoCodigoPostal = empleadoExistente.getCodigo_Postal();
                            cpValido = true;
                        } else if (!nuevoCodigoPostal.matches("\\d{5}")) {
                            System.out.println("El código postal debe tener 5 números.");
                        } else {
                            cpValido = true;
                        }
                    }
                    
                    String nuevaProvincia = "";
                    boolean provinciaValida = false;
                    while (!provinciaValida) {
                        System.out.print("Ingrese la nueva provincia [" + empleadoExistente.getProvincia() + "]: ");
                        nuevaProvincia = scanner.nextLine();
                        if (nuevaProvincia.isEmpty()) {
                            nuevaProvincia = empleadoExistente.getProvincia();
                            provinciaValida = true;
                        } else {
                            provinciaValida = true;
                        }
                    }
                    
                    String nuevoPais = "";
                    boolean paisValido = false;
                    while (!paisValido) {
                        System.out.print("Ingrese el nuevo país [" + empleadoExistente.getPaís() + "]: ");
                        nuevoPais = scanner.nextLine();
                        if (nuevoPais.isEmpty()) {
                            nuevoPais = empleadoExistente.getPaís();
                            paisValido = true;
                        } else {
                            paisValido = true;
                        }
                    }
                    
                    String nuevoEmail = "";
                    boolean emailValido = false;
                    while (!emailValido) {
                        System.out.print("Ingrese el nuevo email [" + empleadoExistente.getEmail() + "]: ");
                        nuevoEmail = scanner.nextLine();
                        if (nuevoEmail.isEmpty()) {
                            nuevoEmail = empleadoExistente.getEmail();
                            emailValido = true;
                        } else if (!nuevoEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                            System.out.println("El email debe tener un formato válido.");
                        } else {
                            emailValido = true;
                        }
                    }
                    
                    String nuevoTelefono = "";
                    boolean telefonoValido = false;
                    while (!telefonoValido) {
                        System.out.print("Ingrese el nuevo teléfono [" + empleadoExistente.getTelfono() + "]: ");
                        nuevoTelefono = scanner.nextLine();
                        if (nuevoTelefono.isEmpty()) {
                            nuevoTelefono = empleadoExistente.getTelfono();
                            telefonoValido = true;
                        } else if (!nuevoTelefono.matches("\\d+")) {
                            System.out.println("El teléfono debe contener solo números.");
                        } else {
                            telefonoValido = true;
                        }
                    }
                    
                    Empleado empleadoActualizado = new Empleado();
                    empleadoActualizado.setID_Empleado(idEmpleadoActualizar);
                    empleadoActualizado.setNombre(nuevoNombre);
                    empleadoActualizado.setApellido(nuevosApellidos);
                    empleadoActualizado.setNIF(nuevoDNI);
                    empleadoActualizado.setDireccion(nuevaDireccion);
                    empleadoActualizado.setCodigo_Postal(nuevoCodigoPostal);
                    empleadoActualizado.setProvincia(nuevaProvincia);
                    empleadoActualizado.setPaís(nuevoPais);
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
    }
    
    protected void eliminarEmpleado() 
    {
        if (empleadoController != null) 
        {
            try 
            {
                System.out.println("Eliminando empleado...");
                Long idEmpleadoEliminar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del empleado a eliminar: ");
                        idEmpleadoEliminar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Empleado empleadoExistente = empleadoController.findById(idEmpleadoEliminar);
                if (empleadoExistente != null) 
                {
                    System.out.println("Se eliminará el siguiente empleado: " + empleadoExistente);
                    
                    boolean respuestaValida = false;
                    String respuesta = "";
                    
                    while (!respuestaValida) {
                        System.out.print("¿Está seguro que desea eliminar este empleado? (S/N): ");
                        respuesta = scanner.nextLine().trim().toUpperCase();
                        
                        if (respuesta.equals("S") || respuesta.equals("N")) {
                            respuestaValida = true;
                        } else {
                            System.out.println("Por favor, ingrese S para confirmar o N para cancelar.");
                        }
                    }
                    
                    if (respuesta.equals("S")) 
                    {
                        empleadoController.delete(idEmpleadoEliminar);
                        System.out.println("Empleado eliminado correctamente.");
                    } 
                    else 
                    {
                        System.out.println("Operación cancelada por el usuario.");
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
    }

    protected void listarProductos() 
    {
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
    }
    
    protected void buscarProductoPorId() 
    {
        if (productoController != null) 
        {
            try 
            {
                System.out.println("Buscando producto...");
                Long idProducto = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del producto: ");
                        idProducto = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                System.out.println("Buscando producto con ID " + idProducto + "...");
                Producto producto = productoController.findById(idProducto);
                if (producto != null) 
                {
                    System.out.println(producto);
                } 
                else 
                {
                    System.out.println("No se encontró un producto con el ID: " + idProducto);
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error al buscar el producto: " + e.getMessage());
            }
        } 
        else 
        {
            System.err.println("Error: No hay conexión con la base de datos");
        }
    }
    
    protected void añadirProducto() 
    {
        if (productoController != null) 
        {
            try 
            {
                System.out.println("Añadiendo nuevo producto...");
                scanner.nextLine(); 
                
                String nombre = "";
                boolean nombreValido = false;
                while (!nombreValido) {
                    System.out.print("Ingrese el nombre del producto: ");
                    nombre = scanner.nextLine();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                    } else {
                        nombreValido = true;
                    }
                }
                
                String descripcion = "";
                boolean descripcionValida = false;
                while (!descripcionValida) {
                    System.out.print("Ingrese la descripción del producto: ");
                    descripcion = scanner.nextLine();
                    if (descripcion.isEmpty()) {
                        System.out.println("La descripción no puede estar vacía.");
                    } else {
                        descripcionValida = true;
                    }
                }
                
                double precio = 0.0;
                boolean precioValido = false;
                while (!precioValido) {
                    try {
                        System.out.print("Ingrese el precio del producto: ");
                        precio = Double.parseDouble(scanner.nextLine());
                        if (precio <= 0) {
                            System.out.println("El precio debe ser mayor que cero.");
                        } else {
                            precioValido = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico válido para el precio.");
                    }
                }
                
                double iva = 0.0;
                boolean ivaValido = false;
                while (!ivaValido) {
                    try {
                        System.out.print("Ingrese el IVA del producto (ej: 21 para 21%): ");
                        iva = Double.parseDouble(scanner.nextLine());
                        if (iva < 0) {
                            System.out.println("El IVA no puede ser negativo.");
                        } else {
                            ivaValido = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico válido para el IVA.");
                    }
                }
                
                int stock = 0;
                boolean stockValido = false;
                while (!stockValido) {
                    try {
                        System.out.print("Ingrese el stock del producto: ");
                        stock = Integer.parseInt(scanner.nextLine());
                        if (stock < 0) {
                            System.out.println("El stock no puede ser negativo.");
                        } else {
                            stockValido = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico entero para el stock.");
                    }
                }
                
                System.out.println("Categorías disponibles:");
                categoriaController.findAll().forEach(System.out::println);
                
                Long idCategoria = 0L;
                boolean categoriaValida = false;
                Categoria categoria = null;
                
                while (!categoriaValida) {
                    try {
                        System.out.print("Ingrese el ID de la categoría: ");
                        idCategoria = Long.parseLong(scanner.nextLine());
                        categoria = categoriaController.findById(idCategoria);
                        if (categoria == null) {
                            System.out.println("La categoría seleccionada no existe.");
                        } else {
                            categoriaValida = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico válido para el ID de categoría.");
                    }
                }
                
                System.out.println("Proveedores disponibles:");
                proveedorController.findAll().forEach(System.out::println);
                
                Long idProveedor = 0L;
                boolean proveedorValido = false;
                Proveedor proveedor = null;
                
                while (!proveedorValido) {
                    try {
                        System.out.print("Ingrese el ID del proveedor (0 para ninguno): ");
                        idProveedor = Long.parseLong(scanner.nextLine());
                        
                        if (idProveedor == 0) {
                            proveedorValido = true;
                        } else {
                            proveedor = proveedorController.findById(idProveedor);
                            if (proveedor == null) {
                                System.out.println("El proveedor seleccionado no existe.");
                            } else {
                                proveedorValido = true;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico válido para el ID de proveedor.");
                    }
                }
                
                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setPVP(precio);
                producto.setIVA(iva);
                producto.setStock(stock);
                producto.setCategoria(categoria);
                producto.setProveedor(proveedor);
                
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
    }
    
    protected void actualizarProducto() 
    {
        if (productoController != null) 
        {
            try 
            {
                System.out.println("Actualizando producto...");
                Long idProductoActualizar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del producto: ");
                        idProductoActualizar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Producto productoExistente = productoController.findById(idProductoActualizar);
                if (productoExistente != null) 
                {
                    System.out.println("Producto encontrado: " + productoExistente);
                    System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                    
                    String nuevoNombre = "";
                    boolean nombreValido = false;
                    while (!nombreValido) {
                        System.out.print("Ingrese el nuevo nombre [" + productoExistente.getNombre() + "]: ");
                        nuevoNombre = scanner.nextLine();
                        if (nuevoNombre.isEmpty()) {
                            nuevoNombre = productoExistente.getNombre();
                            nombreValido = true;
                        } else {
                            nombreValido = true;
                        }
                    }
                    
                    String nuevaDescripcion = "";
                    boolean descripcionValida = false;
                    while (!descripcionValida) {
                        System.out.print("Ingrese la nueva descripción [" + productoExistente.getDescripcion() + "]: ");
                        nuevaDescripcion = scanner.nextLine();
                        if (nuevaDescripcion.isEmpty()) {
                            nuevaDescripcion = productoExistente.getDescripcion();
                            descripcionValida = true;
                        } else {
                            descripcionValida = true;
                        }
                    }
                    
                    double nuevoPrecio = productoExistente.getPVP();
                    boolean precioValido = false;
                    while (!precioValido) {
                        try {
                            System.out.print("Ingrese el nuevo precio [" + productoExistente.getPVP() + "]: ");
                            String precioStr = scanner.nextLine();
                            if (precioStr.isEmpty()) {
                                precioValido = true;
                            } else {
                                nuevoPrecio = Double.parseDouble(precioStr);
                                if (nuevoPrecio <= 0) {
                                    System.out.println("El precio debe ser mayor que cero.");
                                } else {
                                    precioValido = true;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Precio no válido. Debe ingresar un valor numérico.");
                        }
                    }
                    
                    double nuevoIVA = (productoExistente.getIVA() != null) ? productoExistente.getIVA() : 0.0;
                    boolean ivaValido = false;
                    while (!ivaValido) {
                        try {
                            System.out.print("Ingrese el nuevo IVA (ej: 21 para 21%) [" + nuevoIVA + "]: ");
                            String ivaStr = scanner.nextLine();
                            if (ivaStr.isEmpty()) {
                                ivaValido = true;
                            } else {
                                nuevoIVA = Double.parseDouble(ivaStr);
                                if (nuevoIVA < 0) {
                                    System.out.println("El IVA no puede ser negativo.");
                                } else {
                                    ivaValido = true;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("IVA no válido. Debe ingresar un valor numérico.");
                        }
                    }
                    
                    int nuevoStock = productoExistente.getStock();
                    boolean stockValido = false;
                    while (!stockValido) {
                        try {
                            System.out.print("Ingrese el nuevo stock [" + productoExistente.getStock() + "]: ");
                            String stockStr = scanner.nextLine();
                            if (stockStr.isEmpty()) {
                                stockValido = true;
                            } else {
                                nuevoStock = Integer.parseInt(stockStr);
                                if (nuevoStock < 0) {
                                    System.out.println("El stock no puede ser negativo.");
                                } else {
                                    stockValido = true;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Stock no válido. Debe ingresar un valor numérico entero.");
                        }
                    }
                    
                    Categoria nuevaCategoria = productoExistente.getCategoria();
                    boolean categoriaValida = false;
                    
                    while (!categoriaValida) {
                        System.out.print("¿Desea cambiar la categoría? (S/N): ");
                        String cambiarCategoria = scanner.nextLine().toUpperCase();
                        
                        if (cambiarCategoria.equals("S")) {
                            System.out.println("Categorías disponibles:");
                            categoriaController.findAll().forEach(System.out::println);
                            
                            try {
                                System.out.print("Ingrese el ID de la nueva categoría: ");
                                Long idNuevaCategoria = Long.parseLong(scanner.nextLine());
                                Categoria tempCategoria = categoriaController.findById(idNuevaCategoria);
                                
                                if (tempCategoria == null) {
                                    System.out.println("La categoría seleccionada no existe. Se mantendrá la categoría actual.");
                                } else {
                                    nuevaCategoria = tempCategoria;
                                }
                                categoriaValida = true;
                            } catch (NumberFormatException e) {
                                System.out.println("ID de categoría no válido. Debe ingresar un valor numérico.");
                            }
                        } else if (cambiarCategoria.equals("N")) {
                            categoriaValida = true;
                        } else {
                            System.out.println("Respuesta no válida. Ingrese S o N.");
                        }
                    }
                    
                    Proveedor nuevoProveedor = productoExistente.getProveedor();
                    boolean proveedorValido = false;
                    
                    while (!proveedorValido) {
                        System.out.print("¿Desea cambiar el proveedor? (S/N): ");
                        String cambiarProveedor = scanner.nextLine().toUpperCase();
                        
                        if (cambiarProveedor.equals("S")) {
                            System.out.println("Proveedores disponibles:");
                            proveedorController.findAll().forEach(System.out::println);
                            
                            try {
                                System.out.print("Ingrese el ID del nuevo proveedor (0 para ninguno): ");
                                Long idNuevoProveedor = Long.parseLong(scanner.nextLine());
                                
                                if (idNuevoProveedor == 0) {
                                    nuevoProveedor = null;
                                    proveedorValido = true;
                                } else {
                                    Proveedor tempProveedor = proveedorController.findById(idNuevoProveedor);
                                    
                                    if (tempProveedor == null) {
                                        System.out.println("El proveedor seleccionado no existe. Se mantendrá el proveedor actual.");
                                    } else {
                                        nuevoProveedor = tempProveedor;
                                    }
                                    proveedorValido = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("ID de proveedor no válido. Debe ingresar un valor numérico.");
                            }
                        } else if (cambiarProveedor.equals("N")) {
                            proveedorValido = true;
                        } else {
                            System.out.println("Respuesta no válida. Ingrese S o N.");
                        }
                    }
                    
                    Producto productoActualizado = new Producto();
                    productoActualizado.setID_Producto(idProductoActualizar);
                    productoActualizado.setNombre(nuevoNombre);
                    productoActualizado.setDescripcion(nuevaDescripcion);
                    productoActualizado.setStock(nuevoStock);
                    productoActualizado.setPVP(nuevoPrecio);
                    productoActualizado.setIVA(nuevoIVA);
                    productoActualizado.setCategoria(nuevaCategoria);
                    productoActualizado.setProveedor(nuevoProveedor);
                    productoController.save(productoActualizado);
                    System.out.println("Producto actualizado correctamente.");
                }
                else 
                {
                    System.out.println("No se encontró el producto con ID: " + idProductoActualizar);
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error al actualizar el producto: " + e.getMessage());
            }
        } 
        else 
        {
            System.err.println("Error: No hay conexión con la base de datos");
        }
    }
    
    protected void eliminarProducto() 
    {
        if (productoController != null) 
        {
            try 
            {
                System.out.println("Eliminando producto...");
                Long idProductoEliminar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del producto a eliminar: ");
                        idProductoEliminar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Producto productoExistente = productoController.findById(idProductoEliminar);
                if (productoExistente != null) 
                {
                    System.out.println("Se eliminará el siguiente producto: " + productoExistente);
                    
                    boolean respuestaValida = false;
                    String respuesta = "";
                    
                    while (!respuestaValida) {
                        System.out.print("¿Está seguro que desea eliminar este producto? (S/N): ");
                        respuesta = scanner.nextLine().trim().toUpperCase();
                        
                        if (respuesta.equals("S") || respuesta.equals("N")) {
                            respuestaValida = true;
                        } else {
                            System.out.println("Por favor, ingrese S para confirmar o N para cancelar.");
                        }
                    }
                    
                    if (respuesta.equals("S")) 
                    {
                        productoController.delete(idProductoEliminar);
                        System.out.println("Producto eliminado correctamente.");
                    } 
                    else 
                    {
                        System.out.println("Operación cancelada por el usuario.");
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
    }

    protected void listarProveedores() 
    {
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
    }
    
    protected void buscarProveedorPorId() 
    {
        if (proveedorController != null) 
        {
            try 
            {
                System.out.println("Buscando proveedor...");
                Long idProveedor = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del proveedor: ");
                        idProveedor = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                System.out.println("Buscando proveedor con ID " + idProveedor + "...");
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
    }
    
    protected void añadirProveedor() 
    {
        if (proveedorController != null) 
        {
            try 
            {
                System.out.println("Añadiendo nuevo proveedor...");
                scanner.nextLine(); 
                
                String cif = "";
                boolean cifValido = false;
                while (!cifValido) {
                    System.out.print("Ingrese el CIF del proveedor: ");
                    cif = scanner.nextLine();
                    if (cif.isEmpty()) {
                        System.out.println("El CIF no puede estar vacío.");
                    } else if (!cif.matches("[A-Z]\\d{8}")) {
                        System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                    } else {
                        cifValido = true;
                    }
                }
                
                String nombreEmpresa = "";
                boolean nombreEmpresaValido = false;
                while (!nombreEmpresaValido) {
                    System.out.print("Ingrese el nombre de la empresa: ");
                    nombreEmpresa = scanner.nextLine();
                    if (nombreEmpresa.isEmpty()) {
                        System.out.println("El nombre de la empresa no puede estar vacío.");
                    } else {
                        nombreEmpresaValido = true;
                    }
                }
                
                String nombreResponsable = "";
                boolean nombreResponsableValido = false;
                while (!nombreResponsableValido) {
                    System.out.print("Ingrese el nombre del responsable: ");
                    nombreResponsable = scanner.nextLine();
                    if (nombreResponsable.isEmpty()) {
                        System.out.println("El nombre del responsable no puede estar vacío.");
                    } else {
                        nombreResponsableValido = true;
                    }
                }
                
                String pais = "";
                boolean paisValido = false;
                while (!paisValido) {
                    System.out.print("Ingrese el país del proveedor: ");
                    pais = scanner.nextLine();
                    if (pais.isEmpty()) {
                        System.out.println("El país no puede estar vacío.");
                    } else {
                        paisValido = true;
                    }
                }
                
                String provincia = "";
                boolean provinciaValida = false;
                while (!provinciaValida) {
                    System.out.print("Ingrese la provincia del proveedor: ");
                    provincia = scanner.nextLine();
                    if (provincia.isEmpty()) {
                        System.out.println("La provincia no puede estar vacía.");
                    } else {
                        provinciaValida = true;
                    }
                }
                
                String direccion = "";
                boolean direccionValida = false;
                while (!direccionValida) {
                    System.out.print("Ingrese la dirección del proveedor: ");
                    direccion = scanner.nextLine();
                    if (direccion.isEmpty()) {
                        System.out.println("La dirección no puede estar vacía.");
                    } else {
                        direccionValida = true;
                    }
                }
                
                String email = "";
                boolean emailValido = false;
                while (!emailValido) {
                    System.out.print("Ingrese el email del proveedor: ");
                    email = scanner.nextLine();
                    if (email.isEmpty()) {
                        System.out.println("El email no puede estar vacío.");
                    } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                        System.out.println("El email debe tener un formato válido.");
                    } else {
                        emailValido = true;
                    }
                }
                
                String telefono = "";
                boolean telefonoValido = false;
                while (!telefonoValido) {
                    System.out.print("Ingrese el teléfono del proveedor: ");
                    telefono = scanner.nextLine();
                    if (telefono.isEmpty()) {
                        System.out.println("El teléfono no puede estar vacío.");
                    } else if (!telefono.matches("\\d+")) {
                        System.out.println("El teléfono debe contener solo números.");
                    } else {
                        telefonoValido = true;
                    }
                }
                
                String codigoPostal = "";
                boolean cpValido = false;
                while (!cpValido) {
                    System.out.print("Ingrese el código postal del proveedor: ");
                    codigoPostal = scanner.nextLine();
                    if (codigoPostal.isEmpty()) {
                        System.out.println("El código postal no puede estar vacío.");
                    } else if (!codigoPostal.matches("\\d{5}")) {
                        System.out.println("El código postal debe tener 5 números.");
                    } else {
                        cpValido = true;
                    }
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
    }
    
    protected void actualizarProveedor() 
    {
        if (proveedorController != null) {
            try 
            {
                System.out.println("Actualizando proveedor...");
                Long idProveedorActualizar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del proveedor: ");
                        idProveedorActualizar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Proveedor proveedorExistente = proveedorController.findById(idProveedorActualizar);
                if (proveedorExistente != null) 
                {
                    System.out.println("Proveedor encontrado: " + proveedorExistente);
                    System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                    
                    String nuevoCIF = "";
                    boolean cifValido = false;
                    while (!cifValido) {
                        System.out.print("Ingrese el nuevo CIF [" + proveedorExistente.getCIF() + "]: ");
                        nuevoCIF = scanner.nextLine();
                        if (nuevoCIF.isEmpty()) {
                            nuevoCIF = proveedorExistente.getCIF();
                            cifValido = true;
                        } else if (!nuevoCIF.matches("[A-Z]\\d{8}")) {
                            System.out.println("El CIF debe tener 1 letra seguida de 8 números.");
                        } else {
                            cifValido = true;
                        }
                    }
                    
                    String nuevoNombreEmpresa = "";
                    boolean nombreEmpresaValido = false;
                    while (!nombreEmpresaValido) {
                        System.out.print("Ingrese el nuevo nombre de la empresa [" + proveedorExistente.getNombre() + "]: ");
                        nuevoNombreEmpresa = scanner.nextLine();
                        if (nuevoNombreEmpresa.isEmpty()) {
                            nuevoNombreEmpresa = proveedorExistente.getNombre();
                            nombreEmpresaValido = true;
                        } else {
                            nombreEmpresaValido = true;
                        }
                    }
                    
                    String nuevoNombreResponsable = "";
                    boolean nombreResponsableValido = false;
                    while (!nombreResponsableValido) {
                        System.out.print("Ingrese el nuevo nombre del responsable [" + proveedorExistente.getNombre_Responsable() + "]: ");
                        nuevoNombreResponsable = scanner.nextLine();
                        if (nuevoNombreResponsable.isEmpty()) {
                            nuevoNombreResponsable = proveedorExistente.getNombre_Responsable();
                            nombreResponsableValido = true;
                        } else {
                            nombreResponsableValido = true;
                        }
                    }
                    
                    String nuevoPais = "";
                    boolean paisValido = false;
                    while (!paisValido) {
                        System.out.print("Ingrese el nuevo país [" + proveedorExistente.getPais() + "]: ");
                        nuevoPais = scanner.nextLine();
                        if (nuevoPais.isEmpty()) {
                            nuevoPais = proveedorExistente.getPais();
                            paisValido = true;
                        } else {
                            paisValido = true;
                        }
                    }
                    
                    String nuevaProvincia = "";
                    boolean provinciaValida = false;
                    while (!provinciaValida) {
                        System.out.print("Ingrese la nueva provincia [" + proveedorExistente.getProvincia() + "]: ");
                        nuevaProvincia = scanner.nextLine();
                        if (nuevaProvincia.isEmpty()) {
                            nuevaProvincia = proveedorExistente.getProvincia();
                            provinciaValida = true;
                        } else {
                            provinciaValida = true;
                        }
                    }
                    
                    String nuevaDireccion = "";
                    boolean direccionValida = false;
                    while (!direccionValida) {
                        System.out.print("Ingrese la nueva dirección [" + proveedorExistente.getDireccion() + "]: ");
                        nuevaDireccion = scanner.nextLine();
                        if (nuevaDireccion.isEmpty()) {
                            nuevaDireccion = proveedorExistente.getDireccion();
                            direccionValida = true;
                        } else {
                            direccionValida = true;
                        }
                    }
                    
                    String nuevoEmail = "";
                    boolean emailValido = false;
                    while (!emailValido) {
                        System.out.print("Ingrese el nuevo email [" + proveedorExistente.getEmail() + "]: ");
                        nuevoEmail = scanner.nextLine();
                        if (nuevoEmail.isEmpty()) {
                            nuevoEmail = proveedorExistente.getEmail();
                            emailValido = true;
                        } else if (!nuevoEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                            System.out.println("El email debe tener un formato válido.");
                        } else {
                            emailValido = true;
                        }
                    }
                    
                    String nuevoTelefono = "";
                    boolean telefonoValido = false;
                    while (!telefonoValido) {
                        System.out.print("Ingrese el nuevo teléfono [" + proveedorExistente.getTelefono() + "]: ");
                        nuevoTelefono = scanner.nextLine();
                        if (nuevoTelefono.isEmpty()) {
                            nuevoTelefono = proveedorExistente.getTelefono();
                            telefonoValido = true;
                        } else if (!nuevoTelefono.matches("\\d+")) {
                            System.out.println("El teléfono debe contener solo números.");
                        } else {
                            telefonoValido = true;
                        }
                    }
                    
                    String nuevoCodigoPostal = "";
                    boolean cpValido = false;
                    while (!cpValido) {
                        System.out.print("Ingrese el nuevo código postal [" + proveedorExistente.getCodigo_Postal() + "]: ");
                        nuevoCodigoPostal = scanner.nextLine();
                        if (nuevoCodigoPostal.isEmpty()) {
                            nuevoCodigoPostal = proveedorExistente.getCodigo_Postal();
                            cpValido = true;
                        } else if (!nuevoCodigoPostal.matches("\\d{5}")) {
                            System.out.println("El código postal debe tener 5 números.");
                        } else {
                            cpValido = true;
                        }
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
    }
    
    protected void eliminarProveedor() 
    {
        if (proveedorController != null) 
        {
            try 
            {
                System.out.println("Eliminando proveedor...");
                Long idProveedorEliminar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID del proveedor a eliminar: ");
                        idProveedorEliminar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Proveedor proveedorExistente = proveedorController.findById(idProveedorEliminar);
                if (proveedorExistente != null) 
                {
                    System.out.println("Se eliminará el siguiente proveedor: " + proveedorExistente);
                    
                    boolean respuestaValida = false;
                    String respuesta = "";
                    
                    while (!respuestaValida) {
                        System.out.print("¿Está seguro que desea eliminar este proveedor? (S/N): ");
                        respuesta = scanner.nextLine().trim().toUpperCase();
                        
                        if (respuesta.equals("S") || respuesta.equals("N")) {
                            respuestaValida = true;
                        } else {
                            System.out.println("Por favor, ingrese S para confirmar o N para cancelar.");
                        }
                    }
                    
                    if (respuesta.equals("S")) 
                    {
                        proveedorController.delete(idProveedorEliminar);
                        System.out.println("Proveedor eliminado correctamente.");
                    } 
                    else 
                    {
                        System.out.println("Operación cancelada por el usuario.");
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
    }

    protected void listarCategorias() 
    {
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
    }
    
    protected void buscarCategoriaPorId() 
    {
        if (categoriaController != null) 
        {
            try 
            {
                System.out.println("Buscando categoría...");
                Long idCategoria = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID de la categoría: ");
                        idCategoria = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                System.out.println("Buscando categoría con ID " + idCategoria + "...");
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
    }
    
    protected void añadirCategoria() 
    {
        if (categoriaController != null) 
        {
            try 
            {
                System.out.println("Añadiendo nueva categoría...");
                scanner.nextLine(); 
                
                String nombre = "";
                boolean nombreValido = false;
                while (!nombreValido) {
                    System.out.print("Ingrese el nombre de la categoría: ");
                    nombre = scanner.nextLine();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                    } else {
                        nombreValido = true;
                    }
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
    }
    
    protected void actualizarCategoria() 
    {
        if (categoriaController != null) 
        {
            try 
            {
                System.out.println("Actualizando categoría...");
                Long idCategoriaActualizar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID de la categoría: ");
                        idCategoriaActualizar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
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
            catch (InputMismatchException e) 
            {
                System.err.println("Error: Debe ingresar un número válido para el ID.");
                scanner.nextLine();
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
    }
    
    protected void eliminarCategoria() 
    {
        if (categoriaController != null) 
        {
            try 
            {
                System.out.println("Eliminando categoría...");
                Long idCategoriaEliminar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID de la categoría a eliminar: ");
                        idCategoriaEliminar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Categoria categoriaExistente = categoriaController.findById(idCategoriaEliminar);
                if (categoriaExistente != null) 
                {
                    boolean tieneProductos = false;
                    for (Producto producto : productoController.findAll()) 
                    {
                        if (producto.getCategoria().getID_Categoria().equals(idCategoriaEliminar)) 
                        {
                            tieneProductos = true;
                            break;
                        }
                    }
                    
                    if (tieneProductos) 
                    {
                        System.out.println("No se puede eliminar la categoría porque tiene productos asociados.");
                        return;
                    }
                    
                    System.out.println("Se eliminará la siguiente categoría: " + categoriaExistente);
                    
                    boolean respuestaValida = false;
                    String respuesta = "";
                    
                    while (!respuestaValida) {
                        System.out.print("¿Está seguro que desea eliminar esta categoría? (S/N): ");
                        respuesta = scanner.nextLine().trim().toUpperCase();
                        
                        if (respuesta.equals("S") || respuesta.equals("N")) {
                            respuestaValida = true;
                        } else {
                            System.out.println("Por favor, ingrese S para confirmar o N para cancelar.");
                        }
                    }
                    
                    if (respuesta.equals("S")) 
                    {
                        categoriaController.delete(idCategoriaEliminar);
                        System.out.println("Categoría eliminada correctamente.");
                    }
                    else 
                    {
                        System.out.println("Operación cancelada por el usuario.");
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
    }

    protected void listarFacturas() 
    {
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
    }
    
    protected void buscarFacturaPorId() 
    {
        if (facturaController != null) 
        {
            try 
            {
                System.out.println("Buscando factura...");
                Long idFactura = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID de la factura: ");
                        idFactura = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                System.out.println("Buscando factura con ID " + idFactura + "...");
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
    }
    
    protected void crearFactura() 
    {
        if (facturaController != null) 
        {
            try 
            {
                System.out.println("Creando nueva factura...");
                scanner.nextLine(); 
                
                System.out.println("Clientes disponibles:");
                clienteController.findAll().forEach(System.out::println);
                
                Long idCliente = 0L;
                boolean clienteValido = false;
                Cliente cliente = null;
                
                while (!clienteValido) {
                    try {
                        System.out.print("Ingrese el ID del cliente: ");
                        idCliente = Long.parseLong(scanner.nextLine());
                        cliente = clienteController.findById(idCliente);
                        if (cliente == null) {
                            System.out.println("El cliente seleccionado no existe.");
                        } else {
                            clienteValido = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico válido para el ID de cliente.");
                    }
                }
                
                System.out.println("Empleados disponibles:");
                empleadoController.findAll().forEach(System.out::println);
                
                Long idEmpleado = 0L;
                boolean empleadoValido = false;
                Empleado empleado = null;
                
                while (!empleadoValido) {
                    try {
                        System.out.print("Ingrese el ID del empleado: ");
                        idEmpleado = Long.parseLong(scanner.nextLine());
                        empleado = empleadoController.findById(idEmpleado);
                        if (empleado == null) {
                            System.out.println("El empleado seleccionado no existe.");
                        } else {
                            empleadoValido = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico válido para el ID de empleado.");
                    }
                }
                
                LocalDate fecha = null;
                boolean fechaValida = false;
                
                while (!fechaValida) {
                    try {
                        System.out.print("Ingrese la fecha de la factura (YYYY-MM-DD): ");
                        String fechaStr = scanner.nextLine();
                        fecha = LocalDate.parse(fechaStr);
                        fechaValida = true;
                    } catch (Exception e) {
                        System.out.println("Formato de fecha inválido. Use el formato YYYY-MM-DD.");
                    }
                }
                
                String metodoPago = "";
                boolean metodoPagoValido = false;
                
                while (!metodoPagoValido) {
                    System.out.print("Ingrese el método de pago: ");
                    metodoPago = scanner.nextLine();
                    if (metodoPago.isEmpty()) {
                        System.out.println("El método de pago no puede estar vacío.");
                    } else {
                        metodoPagoValido = true;
                    }
                }
                
                String estado = "";
                boolean estadoValido = false;
                
                while (!estadoValido) {
                    System.out.print("Ingrese el estado de la factura (Pendiente/Pagada/Cancelada): ");
                    estado = scanner.nextLine();
                    if (estado.isEmpty()) {
                        System.out.println("El estado no puede estar vacío.");
                    } else if (!(estado.equalsIgnoreCase("Pendiente") || 
                               estado.equalsIgnoreCase("Pagada") || 
                               estado.equalsIgnoreCase("Cancelada"))) {
                        System.out.println("El estado debe ser 'Pendiente', 'Pagada' o 'Cancelada'.");
                    } else {
                        estadoValido = true;
                    }
                }
                
                Factura factura = new Factura();
                factura.setCliente(cliente);
                factura.setEmpleado(empleado);
                factura.setFecha_Venta(fecha);
                factura.setCanal_Compra(metodoPago);
                factura.setPagado(estado);
                
                System.out.println("Productos disponibles:");
                productoController.findAll().forEach(System.out::println);
                
                Long idProducto = 0L;
                boolean productoValido = false;
                Producto producto = null;
                
                while (!productoValido) {
                    try {
                        System.out.print("Ingrese el ID del producto: ");
                        idProducto = Long.parseLong(scanner.nextLine());
                        producto = productoController.findById(idProducto);
                        if (producto == null) {
                            System.out.println("El producto seleccionado no existe.");
                        } else {
                            productoValido = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico válido para el ID de producto.");
                    }
                }
                
                int cantidad = 0;
                boolean cantidadValida = false;
                
                while (!cantidadValida) {
                    try {
                        System.out.print("Ingrese la cantidad: ");
                        cantidad = Integer.parseInt(scanner.nextLine());
                        if (cantidad <= 0) {
                            System.out.println("La cantidad debe ser mayor que cero.");
                        } else if (cantidad > producto.getStock()) {
                            System.out.println("La cantidad no puede superar el stock disponible (" + producto.getStock() + ").");
                        } else {
                            cantidadValida = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un valor numérico entero para la cantidad.");
                    }
                }
                
                double precioUnitario = producto.getPVP();
                double subtotal = precioUnitario * cantidad;
                
                factura.setProducto(producto);
                factura.setCantidad(cantidad);
                factura.setTotal(subtotal);
                
                facturaController.save(factura);
                System.out.println("Factura creada correctamente con un total de: " + subtotal + "€");
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
    }
    
    protected void actualizarFactura() 
    {
        if (facturaController != null) 
        {
            try 
            {
                System.out.println("Actualizando factura...");
                Long idFacturaActualizar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID de la factura: ");
                        idFacturaActualizar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
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
                    
                    LocalDate nuevaFecha = facturaExistente.getFecha_Venta();
                    boolean fechaValida = false;
                    
                    while (!fechaValida) {
                        try {
                            System.out.print("Ingrese la nueva fecha (YYYY-MM-DD) [" + facturaExistente.getFecha_Venta() + "]: ");
                            String nuevaFechaStr = scanner.nextLine();
                            if (nuevaFechaStr.isEmpty()) {
                                fechaValida = true;
                            } else {
                                nuevaFecha = LocalDate.parse(nuevaFechaStr);
                                fechaValida = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Formato de fecha inválido. Use el formato YYYY-MM-DD.");
                        }
                    }
                    
                    String nuevoMetodoPago = "";
                    boolean metodoPagoValido = false;
                    
                    while (!metodoPagoValido) {
                        System.out.print("Ingrese el nuevo método de pago [" + facturaExistente.getCanal_Compra() + "]: ");
                        nuevoMetodoPago = scanner.nextLine();
                        if (nuevoMetodoPago.isEmpty()) {
                            nuevoMetodoPago = facturaExistente.getCanal_Compra();
                            metodoPagoValido = true;
                        } else {
                            metodoPagoValido = true;
                        }
                    }
                    
                    String nuevoEstado = "";
                    boolean estadoValido = false;
                    
                    while (!estadoValido) {
                        System.out.print("Ingrese el nuevo estado (Pendiente/Pagada/Cancelada) [" + facturaExistente.getPagado() + "]: ");
                        nuevoEstado = scanner.nextLine();
                        if (nuevoEstado.isEmpty()) {
                            nuevoEstado = facturaExistente.getPagado();
                            estadoValido = true;
                        } else if (!(nuevoEstado.equalsIgnoreCase("Pendiente") || 
                                   nuevoEstado.equalsIgnoreCase("Pagada") || 
                                   nuevoEstado.equalsIgnoreCase("Cancelada"))) {
                            System.out.println("El estado debe ser 'Pendiente', 'Pagada' o 'Cancelada'.");
                        } else {
                            estadoValido = true;
                        }
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
    }
    
    protected void eliminarFactura() 
    {
        if (facturaController != null) 
        {
            try 
            {
                System.out.println("Eliminando factura...");
                Long idFacturaEliminar = 0L;
                boolean idValido = false;
                
                while (!idValido) {
                    try {
                        System.out.print("Ingrese el ID de la factura a eliminar: ");
                        idFacturaEliminar = scanner.nextLong();
                        idValido = true;
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Debe ingresar un número válido para el ID.");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
                
                Factura facturaExistente = facturaController.findById(idFacturaEliminar);
                if (facturaExistente != null) 
                {
                    System.out.println("Se eliminará la siguiente factura: " + facturaExistente);
                    
                    boolean respuestaValida = false;
                    String respuesta = "";
                    
                    while (!respuestaValida) {
                        System.out.print("¿Está seguro que desea eliminar esta factura? (S/N): ");
                        respuesta = scanner.nextLine().trim().toUpperCase();
                        
                        if (respuesta.equals("S") || respuesta.equals("N")) {
                            respuestaValida = true;
                        } else {
                            System.out.println("Por favor, ingrese S para confirmar o N para cancelar.");
                        }
                    }
                    
                    if (respuesta.equals("S")) 
                    {
                        facturaController.delete(idFacturaEliminar);
                        System.out.println("Factura eliminada correctamente.");
                    } 
                    else 
                    {
                        System.out.println("Operación cancelada por el usuario.");
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
    }
}
