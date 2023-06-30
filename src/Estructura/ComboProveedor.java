package Estructura;

/**
 *
 * @author Jhair
 */

public class ComboProveedor {
    private int id;
    private String nombre;
    
    //Constructor
    public ComboProveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    //Metodos Publicos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //To String
    @Override
    public String toString(){
        return this.getNombre();
    }
}
