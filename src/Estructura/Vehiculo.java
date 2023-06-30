package Estructura;

/**
 *
 * @author Jhair
 */

public class Vehiculo {
    private int id;
    private String placa;
    private String marca;
    private String color;
    private String configuracion;

    //Constructor simple
    public Vehiculo() {
    }
    //Constructor completo
    public Vehiculo(int id, String placa, String marca, String color, String configuracion) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.configuracion = configuracion;
    }
    //Metodos publicos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    } 
}
