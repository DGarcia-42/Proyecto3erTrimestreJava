package org.carlosydiego.crmclientes.app.model;

public class Cliente 
{
    private Long ID_Cliente;
    private String CIF;
    private String Nombre_Empresa;
    private String Nombre_Responsable;
    private String Pais;
    private String Provincia;
    private String Direccion;
    private String Email;
    private String Telefono;
    private String Codigo_Postal;    
    
    //Constructor por defecto
    public Cliente(){}

    //Constructor con todos los atributos
    public Cliente(String CIF, String Nombre_Empresa, String Nombre_Responsable, String Pais, String Provincia, String Direccion, String Email, String Telefono, String Codigo_Postal) 
    {
        this.CIF = CIF;
        this.Nombre_Empresa = Nombre_Empresa;
        this.Nombre_Responsable = Nombre_Responsable;
        this.Pais = Pais;
        this.Provincia = Provincia;
        this.Direccion = Direccion;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Codigo_Postal = Codigo_Postal;
    }

    //Getters y setters
    public Long getID_Cliente() 
    {
        return ID_Cliente;
    }

    public void setID_Cliente(Long iD_Cliente) 
    {
        ID_Cliente = iD_Cliente;
    }

    public String getCIF() 
    {
        return CIF;
    }

    public void setCIF(String cIF) 
    {
        CIF = cIF;
    }

    public String getNombre_Empresa() 
    {
        return Nombre_Empresa;
    }

    public void setNombre_Empresa(String nombre_Empresa) 
    {
        Nombre_Empresa = nombre_Empresa;
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

    public String getEmail() 
    {
        return Email;
    }

    public void setEmail(String email) 
    {
        Email = email;
    }

    public String getTelefono() 
    {
        return Telefono;
    }

    public void setTelefono(String telefono) 
    {
        Telefono = telefono;
    }

    public String getCodigo_Postal() 
    {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) 
    {
        Codigo_Postal = codigo_Postal;
    }

    //Metodo toString
    @Override
    public String toString() 
    {
        return "Cliente [ID_Cliente=" + ID_Cliente + ", CIF=" + CIF + "]" + "\n" +
               "Nombre Empresa: " + Nombre_Empresa + "\n" +
               "Responsable: " + Nombre_Responsable + "\n" +
               "Dirección: " + Direccion + "\n" +
               "Provincia: " + Provincia + "\n" +
               "País: " + Pais + "\n" +
               "Código Postal: " + Codigo_Postal + "\n" +
               "Email: " + Email + "\n" +
               "Teléfono: " + Telefono;
    }

    
    
    
}
