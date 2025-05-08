package org.carlosydiego.crmclientes.app.model;

public class Empleado 
{
    //Atributos de la clase Empleado
    private Long ID_Empleado;
    private String Nombre;
    private String Apellido;
    private String NIF;
    private String Direccion;
    private String Codigo_Postal;
    private String Provincia;
    private String Pais;
    private String Telfono;
    private String Email;

    //Constructor por defecto
    public Empleado(){}

    //Constructor con todos los atributos
    public Empleado(Long ID_Empleado, String Nombre, String Apellido, String NIF, String Direccion, String Codigo_Postal, String Provincia, String País, String Telfono, String Email) 
    {
        this.ID_Empleado = ID_Empleado;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.NIF = NIF;
        this.Direccion = Direccion;
        this.Codigo_Postal = Codigo_Postal;
        this.Provincia = Provincia;
        this.Pais = País;
        this.Telfono = Telfono;
        this.Email = Email;
    }

    //Getters y setters
    public Long getID_Empleado() {
        return ID_Empleado;
    }

    public void setID_Empleado(Long iD_Empleado) {
        ID_Empleado = iD_Empleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String nIF) {
        NIF = nIF;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) {
        Codigo_Postal = codigo_Postal;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getTelfono() {
        return Telfono;
    }

    public void setTelfono(String telfono) {
        Telfono = telfono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    //Metodo toString
    @Override
    public String toString() 
    {
        return "Empleado [ID_Empleado=" + ID_Empleado + ", NIF=" + NIF + "]" + "\n" +
               "Nombre: " + Nombre + "\n" +
               "Apellido: " + Apellido + "\n" +
               "Dirección: " + Direccion + "\n" +
               "Provincia: " + Provincia + "\n" +
               "País: " + Pais + "\n" +
               "Código Postal: " + Codigo_Postal + "\n" +
               "Email: " + Email + "\n" +
               "Teléfono: " + Telfono;
    }



    

}
