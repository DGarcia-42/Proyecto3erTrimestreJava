package org.carlosydiego.crmclientes.app.model;

public class Producto {
    private Long ID_Producto;
    private String Nombre;
    private String Descripcion;
    private Integer Stock;
    private Double PVP;
    private Double IVA;
    private Categoria categoria;
    private Proveedor proveedor;

    public Producto(){}

    public Producto(Long ID_Producto, String Nombre, String Descripcion, Integer Stock, Double PVP, Double IVA, Categoria categoria, Proveedor proveedor) {
        this.ID_Producto = ID_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Stock = Stock;
        this.PVP = PVP;
        this.IVA = IVA;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Long getID_Producto() {return ID_Producto;}
    public void setID_Producto(Long ID_Producto) {this.ID_Producto = ID_Producto;}
    public String getNombre() {return Nombre;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public String getDescripcion() {return Descripcion;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    public Integer getStock() {return Stock;}
    public void setStock(Integer Stock) {this.Stock = Stock;}
    public Double getPVP() {return PVP;}
    public void setPVP(Double PVP) {this.PVP = PVP;}
    public Double getIVA() {return IVA;}
    public void setIVA(Double IVA) {this.IVA = IVA;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public Proveedor getProveedor() {return proveedor;}
    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}

    @Override
    public String toString() {
        return "Producto [ID_Producto=" + ID_Producto + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion 
                + ", Stock=" + Stock + ", PVP=" + PVP + ", IVA=" + IVA + ", Categoria=" + categoria + ", Proveedor=" + proveedor + "]";
    }
}
