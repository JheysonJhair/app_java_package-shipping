package Estructura;

/**
 *
 * @author Jhair
 */

public class Conductor {
    private int id;
    private String dni;
    private String nombre;
    private String licencia;
    private String direccion;
    private String telefono;
    private String vehiculoCond;

    //Constructor simple
    public Conductor() {
    }
    //Constructor completo
    public Conductor(int id, String dni, String nombre, String licencia, String direccion, String telefono, String vehiculoCond) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.licencia = licencia;
        this.direccion = direccion;
        this.telefono = telefono;
        this.vehiculoCond = vehiculoCond;
    }
    //Metodos publicos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }   
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    } 
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getVehiculoCond() {
        return vehiculoCond;
    }
    public void setVehiculoCond(String vehiculoCond) {
        this.vehiculoCond = vehiculoCond;
    }
}
