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
    
    private static final String FACTURAS_DIRECTORY = "facturas";
    
    static {
        File directory = new File(FACTURAS_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
  
    public static void generarArchivoFactura(Factura factura) {
        String filePath = getFacturaFilePath(factura.getID_Factura());
        
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
            
            System.out.println("Archivo de factura generado correctamente: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo de factura: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public static void eliminarArchivoFactura(Long facturaId) {
        String filePath = getFacturaFilePath(facturaId);
        
        try {
            Files.deleteIfExists(Paths.get(filePath));
            System.out.println("Archivo de factura eliminado correctamente: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al eliminar el archivo de factura: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static String getFacturaFilePath(Long facturaId) {
        return FACTURAS_DIRECTORY + File.separator + "factura_" + facturaId + ".txt";
    }
} 