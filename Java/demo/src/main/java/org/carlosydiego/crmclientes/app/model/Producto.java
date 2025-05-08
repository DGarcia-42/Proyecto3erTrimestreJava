package org.carlosydiego.crmclientes.app.model;

public class Producto 
{
    //Atributos de la clase Producto
    private Long ID_Producto;
    private String Nombre;
    private String Descripcion;
    private Integer Stock;
    private Double PVP;
    private Double IVA;
    private Categoria categoria;
    private Proveedor proveedor;

    //Constructor por defecto
    public Producto(){}

    //Constructor con todos los atributos
    public Producto(Long ID_Producto, String Nombre, String Descripcion, Integer Stock, Double PVP, Double IVA, Categoria categoria, Proveedor proveedor) 
    {
        this.ID_Producto = ID_Producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Stock = Stock;
        this.PVP = PVP;
        this.IVA = IVA;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    //Getters y setters
    public Long getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(Long iD_Producto) {
        ID_Producto = iD_Producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public Double getPVP() {
        return PVP;
    }

    public void setPVP(Double pVP) {
        PVP = pVP;
    }

    public Double getIVA() {
        return IVA;
    }

    public void setIVA(Double iVA) {
        IVA = iVA;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    //Metodo toString
    @Override
    public String toString() 
    {
        return "Producto [ID_Producto=" + ID_Producto + "]" + "\n" +
               "Nombre: " + Nombre + "\n" +
               "Descripción: " + Descripcion + "\n" +
               "Stock: " + Stock + "\n" +
               "PVP: " + PVP + "\n" +
               "IVA: " + IVA + "\n" +
               "Categoría: " + (categoria != null ? categoria.getNombre() : "N/A") + "\n" +
               "Proveedor: " + (proveedor != null ? proveedor.getNombre() : "N/A");
    }


}
