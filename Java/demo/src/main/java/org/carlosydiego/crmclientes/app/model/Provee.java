package org.carlosydiego.crmclientes.app.model;

import java.time.LocalDate;

public class Provee 
{
    private Long ID_Provee;
    private Proveedor proveedor;
    private Producto producto;
    private LocalDate Fecha_Provision;
    private Integer Cantidad;
    private Double Precio;

    public Provee(){}
    public Provee(Long ID_Provee, Proveedor proveedor, Producto producto, LocalDate Fecha_Provision, Integer Cantidad, Double Precio) 
    {
        this.ID_Provee = ID_Provee;
        this.proveedor = proveedor;
        this.producto = producto;
        this.Fecha_Provision = Fecha_Provision;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
    }

    public Long getID_Provee() {return ID_Provee;}   

    public void setID_Provee(Long ID_Provee) {this.ID_Provee = ID_Provee;}

    public Proveedor getProveedor() {return proveedor;}

    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}

    public Producto getProducto() {return producto;}

    public void setProducto(Producto producto) {this.producto = producto;}

    public LocalDate getFecha_Provision() {return Fecha_Provision;}

    public void setFecha_Provision(LocalDate Fecha_Provision) {this.Fecha_Provision = Fecha_Provision;}

    public Integer getCantidad() {return Cantidad;}

    public void setCantidad(Integer Cantidad) {this.Cantidad = Cantidad;}

    public Double getPrecio() {return Precio;}

    public void setPrecio(Double Precio) {this.Precio = Precio;}

    @Override
    public String toString() 
    {
        return "ID_Provee=" + ID_Provee + ", Proveedor=" + proveedor + ", Producto=" + producto + ", Fecha_Provision=" + Fecha_Provision 
                + ", Cantidad=" + Cantidad + ", Precio=" + Precio;
    }
}
