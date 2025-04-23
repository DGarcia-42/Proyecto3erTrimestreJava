package org.carlosydiego.crmclientes.app.menuproyecto;

import java.util.Scanner;



public class MenuProyecto extends Submenus
{
    public MenuProyecto() 
    {
        this.scanner = new Scanner(System.in);
        inicializarControladores();
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
            System.out.println("7. Gestión de Provees");
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
                case 7:
                    mostrarMenuProvees();
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
                    listarClientes();
                    break;
                case 2:
                    buscarClientePorId();
                    break;
                case 3:
                    añadirCliente();
                    break;
                case 4:
                    actualizarCliente();
                    break;
                case 5:
                    eliminarCliente();
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
                    listarEmpleados();
                    break;
                case 2:
                    buscarEmpleadoPorId();
                    break;
                case 3:
                    añadirEmpleado();
                    break;
                case 4:
                    actualizarEmpleado();
                    break;
                case 5:
                    eliminarEmpleado();
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
                    listarProductos();
                    break;
                case 2:
                    buscarProductoPorId();
                    break;
                case 3:
                    añadirProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
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
        
        while (!volver) 
        {
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
                    listarProveedores();
                    break;
                case 2:
                    buscarProveedorPorId();
                    break;
                case 3:
                    añadirProveedor();
                    break;
                case 4:
                    actualizarProveedor();
                    break;
                case 5:
                    eliminarProveedor();
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
        
        while (!volver) 
        {
            System.out.println("\n=== GESTIÓN DE FACTURAS ===");
            System.out.println("1. Ver todas las facturas");
            System.out.println("2. Buscar factura por ID");
            System.out.println("3. Crear nueva factura");
            System.out.println("4. Actualizar factura");
            System.out.println("5. Eliminar factura");
            System.out.println("6. Filtrar facturas por período");
            System.out.println("7. Generar archivo de factura por ID");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    listarFacturas();
                    break;
                case 2:
                    buscarFacturaPorId();
                    break;
                case 3:
                    crearFactura();
                    break;
                case 4:
                    actualizarFactura();
                    break;
                case 5:
                    eliminarFactura();
                    break;
                case 6:
                    filtrarFacturasPorPeriodo();
                    break;
                case 7:
                    generarArchivoFacturaPorId();
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
                    listarCategorias();
                    break;
                case 2:
                    buscarCategoriaPorId();
                    break;
                case 3:
                    añadirCategoria();
                    break;
                case 4:
                    actualizarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void mostrarMenuProvees() 
    {
        boolean volver = false;
        
        while (!volver) 
        {
            System.out.println("\n=== GESTIÓN DE PROVEES ===");
            System.out.println("1. Ver todos los provees");
            System.out.println("2. Buscar provee por ID");
            System.out.println("3. Añadir nuevo provee");
            System.out.println("4. Actualizar provee");
            System.out.println("5. Eliminar provee");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) 
            {
                case 1:
                    listarProvees();
                    break;
                case 2:
                    buscarProveePorId();
                    break;
                case 3:
                    añadirProvee();
                    break;
                case 4:
                    actualizarProvee();
                    break;
                case 5:
                    eliminarProvee();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}

