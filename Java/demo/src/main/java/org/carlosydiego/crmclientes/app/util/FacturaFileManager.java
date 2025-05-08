package org.carlosydiego.crmclientes.app.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import org.carlosydiego.crmclientes.app.model.Factura;


public class FacturaFileManager {
    //Nombre del directorio para guardar las facturas
    private static final String FACTURAS_DIRECTORY = "facturas";

    //Crear el directorio si no existe
    static {
        File directory = new File(FACTURAS_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    

    //Comprobar si el archivo existe
    public static boolean existeArchivoFactura(Long facturaId) {
        String filePath = getFacturaFilePath(facturaId);
        File file = new File(filePath);
        return file.exists();
    }

    //Obtener la ruta absoluta del archivo
    public static String getFacturaRutaAbsoluta(Long facturaId) {
        String filePath = getFacturaFilePath(facturaId);
        File file = new File(filePath);
        return file.getAbsolutePath();
    }

    //Generar el archivo de factura (sin sobreescribir)
    public static String generarArchivoFactura(Factura factura) {
        return generarArchivoFactura(factura, false);
    }
    
    //Metodo para generar el archivo de factura (Si forzarGeneracion es true, se sobreescribe)
    public static String generarArchivoFactura(Factura factura, boolean forzarGeneracion) {
        String filePath = getFacturaFilePath(factura.getID_Factura());
        File file = new File(filePath);
        String rutaAbsoluta = file.getAbsolutePath();
        
        if (!forzarGeneracion && existeArchivoFactura(factura.getID_Factura())) {
            return rutaAbsoluta;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("=================================================");
            writer.newLine();
            writer.write("              FACTURA DE VENTA                   ");
            writer.newLine();
            writer.write("=================================================");
            writer.newLine();
            writer.write("No. Factura: " + factura.getID_Factura());
            writer.newLine();
            writer.write("Fecha: " + factura.getFecha_Venta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            writer.newLine();
            writer.write("=================================================");
            writer.newLine();
            writer.write("CLIENTE: " + factura.getCliente().getNombre_Empresa());
            writer.newLine();
            writer.write("ID Cliente: " + factura.getCliente().getID_Cliente());
            writer.newLine();
            writer.write("=================================================");
            writer.newLine();
            writer.write("                 DETALLES DEL PRODUCTO           ");
            writer.newLine();
            writer.write("                 --------------------            ");
            writer.newLine();
            writer.write("                 " + factura.getProducto().getNombre());
            writer.newLine();
            writer.write("                 Precio: $" + String.format("%.2f", factura.getProducto().getPVP()));
            writer.newLine();
            writer.write("                 Cantidad: " + factura.getCantidad());
            writer.newLine();
            writer.write("                 TOTAL: $" + String.format("%.2f", factura.getTotal()));
            writer.newLine();
            writer.write("=================================================");
            writer.newLine();
            writer.write("Pagado: " + (factura.getPagado().equalsIgnoreCase("Si") ? "Sí" : "No"));
            writer.newLine();
            writer.write("Canal de compra: " + factura.getCanal_Compra());
            writer.newLine();
            writer.write("=================================================");
            writer.newLine();
            writer.write("INFORMACIÓN DEL VENDEDOR:");
            writer.newLine();
            writer.write("ID: " + factura.getEmpleado().getID_Empleado());
            writer.newLine();
            writer.write("Nombre: " + factura.getEmpleado().getNombre() + " " + factura.getEmpleado().getApellido());
            writer.newLine();
            writer.write("Teléfono: " + factura.getEmpleado().getTelfono());
            writer.newLine();
            writer.write("Email: " + factura.getEmpleado().getEmail());
            writer.newLine();
            writer.write("=================================================");
            
            return rutaAbsoluta;
        } catch (IOException e) {
            System.err.println("Error al generar el archivo de factura: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Eliminar el archivo de factura
    public static String eliminarArchivoFactura(Long facturaId) {
        String filePath = getFacturaFilePath(facturaId);
        File file = new File(filePath);
        String rutaAbsoluta = file.getAbsolutePath();
        
        try {
            Files.deleteIfExists(Paths.get(filePath));
            return rutaAbsoluta;
        } catch (IOException e) {
            System.err.println("Error al eliminar el archivo de factura: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //Obtener la ruta del archivo de factura
    private static String getFacturaFilePath(Long facturaId) {
        return FACTURAS_DIRECTORY + File.separator + "factura_" + facturaId + ".txt";
    }
} 