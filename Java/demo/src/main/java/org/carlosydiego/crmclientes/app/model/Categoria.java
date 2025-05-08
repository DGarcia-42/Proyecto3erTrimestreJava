package org.carlosydiego.crmclientes.app.model;

public class Categoria 
{
    //Atributos de la clase Categoría
    private Long ID_Categoria;
    private String Nombre;

    //Constructor por defecto
    public Categoria(){}

    //Constructor con todos los atributos
    public Categoria(Long ID_Categoria, String Nombre) 
    {
        this.ID_Categoria = ID_Categoria;
        this.Nombre = Nombre;
    }

    //Getters y setters
    
    public Long getID_Categoria() {
        return ID_Categoria;
    }

    public void setID_Categoria(Long iD_Categoria) {
        ID_Categoria = iD_Categoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    //Metodo toString
    @Override
    public String toString() 
    {
        return "Categoría [ID_Categoria=" + ID_Categoria + "]" + "\n" +
               "Nombre: " + Nombre;
    }



    
}
