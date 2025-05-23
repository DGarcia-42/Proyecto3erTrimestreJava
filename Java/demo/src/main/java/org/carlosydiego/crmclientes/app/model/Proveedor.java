package org.carlosydiego.crmclientes.app.model;

public class Proveedor 
{
    //Atributos de la clase Proveedor
    private Long ID_Proveedor;
    private String Nombre;
    private String Nombre_Responsable;
    private String Pais;
    private String Provincia;
    private String Direccion;
    private String Codigo_Postal;
    private String CIF;
    private String Telefono;
    private String Email;
    
    //Constructor por defecto
    public Proveedor(){}

    //Constructor con todos los atributos
    public Proveedor(Long ID_Proveedor, String Nombre, String Nombre_Responsable, String Pais, String Provincia, String Direccion, String Codigo_Postal, String CIF, String Telefono, String Email) 
    {
        this.ID_Proveedor = ID_Proveedor;
        this.Nombre = Nombre;
        this.Nombre_Responsable = Nombre_Responsable;
        this.Pais = Pais;
        this.Provincia = Provincia;
        this.Direccion = Direccion;
        this.Codigo_Postal = Codigo_Postal;
        this.CIF = CIF;
        this.Telefono = Telefono;
        this.Email = Email;
    }

    //Getters y setters
    public Long getID_Proveedor() 
    {
        return ID_Proveedor;
    }

    public void setID_Proveedor(Long iD_Proveedor) 
    {
        ID_Proveedor = iD_Proveedor;
    }

    public String getNombre() 
    {
        return Nombre;
    }

    public void setNombre(String nombre) 
    {
        Nombre = nombre;
    }

    public String getNombre_Responsable() 
    {
        return Nombre_Responsable;
    }

    public void setNombre_Responsable(String nombre_Responsable) 
    {
        Nombre_Responsable = nombre_Responsable;
    }

    public String getPais() 
    {
        return Pais;
    }

    public void setPais(String pais) 
    {
        Pais = pais;
    }

    public String getProvincia() 
    {
        return Provincia;
    }

    public void setProvincia(String provincia) 
    {
        Provincia = provincia;
    }

    public String getDireccion() 
    {
        return Direccion;
    }

    public void setDireccion(String direccion) 
    {
        Direccion = direccion;
    }

    public String getCodigo_Postal() 
    {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) 
    {
        Codigo_Postal = codigo_Postal;
    }

    public String getCIF() 
    {
        return CIF;
    }

    public void setCIF(String cIF) 
    {
        CIF = cIF;
    }

    public String getTelefono() 
    {
        return Telefono;
    }

    public void setTelefono(String telefono) 
    {
        Telefono = telefono;
    }

    public String getEmail() 
    {
        return Email;
    }

    public void setEmail(String email) 
    {
        Email = email;
    }

    //Metodo toString
    @Override
    public String toString() 
    {
        return "Proveedor [ID_Proveedor=" + ID_Proveedor + ", CIF=" + CIF + "]" + "\n" +
               "Nombre: " + Nombre + "\n" +
               "Responsable: " + Nombre_Responsable + "\n" +
               "Dirección: " + Direccion + "\n" +
               "Provincia: " + Provincia + "\n" +
               "País: " + Pais + "\n" +
               "Código Postal: " + Codigo_Postal + "\n" +
               "Email: " + Email + "\n" +
               "Teléfono: " + Telefono;
    }
}
