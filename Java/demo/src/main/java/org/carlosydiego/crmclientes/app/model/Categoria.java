package org.carlosydiego.crmclientes.app.model;

public class Categoria 
{

    private Long ID_Categoria;
    private String Nombre;

    public Categoria(){}
    public Categoria(Long ID_Categoria, String Nombre) 
    {
        this.ID_Categoria = ID_Categoria;
        this.Nombre = Nombre;
    }

    public Long getID_Categoria() {return ID_Categoria;}

    public void setID_Categoria(Long ID_Categoria) {this.ID_Categoria = ID_Categoria;}

    public String getNombre() {return Nombre;}

    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    @Override
    public String toString() 
    {return "Categoria [ID_Categoria=" + ID_Categoria + ", Nombre=" + Nombre + "]";}

    
}
