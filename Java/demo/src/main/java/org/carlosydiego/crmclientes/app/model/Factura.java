package org.carlosydiego.crmclientes.app.model;

import java.time.LocalDate;

public class Factura 
{
    //Atributos de la clase Factura
    private Long ID_Factura;
    private LocalDate Fecha_Venta;
    private String Canal_Compra;
    private Integer Cantidad;
    private Producto producto;
    private String Pagado;
    private Empleado empleado;
    private Cliente cliente;
    private Double Total;

    //Constructor por defecto
    public Factura(){}

    //Constructor con todos los atributos
    public Factura(Long ID_Factura, LocalDate Fecha_Venta, String Canal_Compra, Integer Cantidad, 
                  Producto producto, String Pagado, Empleado empleado, Cliente cliente, Double Total) 
    {
        this.ID_Factura = ID_Factura;
        this.Fecha_Venta = Fecha_Venta;
        this.Canal_Compra = Canal_Compra;
        this.Cantidad = Cantidad;
        this.producto = producto;
        this.Pagado = Pagado;
        this.empleado = empleado;
        this.cliente = cliente;
        this.Total = Total;
    }

    //Getters y setters
    public Long getID_Factura() {
        return ID_Factura;
    }

    public void setID_Factura(Long iD_Factura) {
        ID_Factura = iD_Factura;
    }

    public LocalDate getFecha_Venta() {
        return Fecha_Venta;
    }

    public void setFecha_Venta(LocalDate fecha_Venta) {
        Fecha_Venta = fecha_Venta;
    }

    public String getCanal_Compra() {
        return Canal_Compra;
    }

    public void setCanal_Compra(String canal_Compra) {
        Canal_Compra = canal_Compra;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getPagado() {
        return Pagado;
    }

    public void setPagado(String pagado) {
        Pagado = pagado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    //Metodo toString
    @Override
    public String toString() 
    {
        return "Factura [ID_Factura=" + ID_Factura + "]" + "\n" +
               "Fecha de Venta: " + Fecha_Venta + "\n" +
               "Canal de Compra: " + Canal_Compra + "\n" +
               "Producto: " + (producto != null ? producto.getNombre() : "N/A") + "\n" +
               "Cantidad: " + Cantidad + "\n" +
               "Total: " + Total + "\n" +
               "Estado: " + Pagado + "\n" +
               "Cliente: " + (cliente != null ? cliente.getNombre_Empresa() : "N/A") + "\n" +
               "Empleado: " + (empleado != null ? empleado.getNombre() + " " + empleado.getApellido() : "N/A");
    }


}
