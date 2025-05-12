package org.carlosydiego.crmclientes.app.model;

import java.time.LocalDate;

public class Provee 
{
    //Atributos de la clase Provee
    private Long ID_Provee;
    private Proveedor proveedor;
    private Producto producto;
    private LocalDate Fecha_Provision;
    private Integer Cantidad;
    private Double Precio;

    //Constructor por defecto
    public Provee(){}

    //Constructor con todos los atributos
    public Provee(Long ID_Provee, Proveedor proveedor, Producto producto, LocalDate Fecha_Provision, Integer Cantidad, Double Precio) 
    {
        this.ID_Provee = ID_Provee;
        this.proveedor = proveedor;
        this.producto = producto;
        this.Fecha_Provision = Fecha_Provision;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
    }

    //Getters y setters

    public Long getID_Provee() 
    {
        return ID_Provee;
    }

    public void setID_Provee(Long iD_Provee) 
    {
        ID_Provee = iD_Provee;
    }

    public Proveedor getProveedor() 
    {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) 
    {
        this.proveedor = proveedor;
    }

    public Producto getProducto() 
    {
        return producto;
    }

    public void setProducto(Producto producto) 
    {
        this.producto = producto;
    }

    public LocalDate getFecha_Provision() 
    {
        return Fecha_Provision;
    }

    public void setFecha_Provision(LocalDate fecha_Provision) 
    {
        Fecha_Provision = fecha_Provision;
    }

    public Integer getCantidad() 
    {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) 
    {
        Cantidad = cantidad;
    }

    public Double getPrecio() 
    {
        return Precio;
    }

    public void setPrecio(Double precio) 
    {
        Precio = precio;
    }

    //Metodo toString
    @Override
    public String toString() 
    {
        return "Provisión [ID_Provee=" + ID_Provee + "]" + "\n" +
               "Proveedor: " + (proveedor != null ? proveedor.getNombre() : "N/A") + "\n" +
               "Producto: " + (producto != null ? producto.getNombre() : "N/A") + "\n" +
               "Fecha de Provisión: " + Fecha_Provision + "\n" +
               "Cantidad: " + Cantidad + "\n" +
               "Precio: " + Precio;
    }

}
