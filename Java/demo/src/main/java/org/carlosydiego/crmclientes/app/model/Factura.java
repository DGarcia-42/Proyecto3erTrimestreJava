package org.carlosydiego.crmclientes.app.model;

import java.time.LocalDate;

public class Factura 
{
    private Long ID_Factura;
    private LocalDate Fecha_Venta;
    private String Canal_Compra;
    private Integer Cantidad;
    private Producto producto;
    private String Pagado;
    private Empleado empleado;
    private Cliente cliente;
    private Double Total;

    public Factura(){}

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

    public Long getID_Factura() {return ID_Factura;}
    public void setID_Factura(Long ID_Factura) {this.ID_Factura = ID_Factura;}
    public LocalDate getFecha_Venta() {return Fecha_Venta;}
    public void setFecha_Venta(LocalDate Fecha_Venta) {this.Fecha_Venta = Fecha_Venta;}
    public String getCanal_Compra() {return Canal_Compra;}
    public void setCanal_Compra(String Canal_Compra) {this.Canal_Compra = Canal_Compra;}
    public Integer getCantidad() {return Cantidad;}
    public void setCantidad(Integer Cantidad) {this.Cantidad = Cantidad;}
    public Producto getProducto() {return producto;}
    public void setProducto(Producto producto) {this.producto = producto;}
    public String getPagado() {return Pagado;}
    public void setPagado(String Pagado) {this.Pagado = Pagado;}
    public Empleado getEmpleado() {return empleado;}
    public void setEmpleado(Empleado empleado) {this.empleado = empleado;}
    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    public Double getTotal() {return Total;}
    public void setTotal(Double Total) {this.Total = Total;}

    @Override
    public String toString() 
    {
        return "Factura [ID_Factura=" + ID_Factura + ", Fecha_Venta=" + Fecha_Venta + ", Canal_Compra=" + Canal_Compra 
                + ", Cantidad=" + Cantidad + ", producto=" + producto + ", Pagado=" + Pagado + ", empleado=" + empleado 
                + ", cliente=" + cliente + ", Total=" + Total + "]";
    }
}
