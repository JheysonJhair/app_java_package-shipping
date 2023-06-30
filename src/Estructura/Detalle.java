package Estructura;

/**
 *
 * @author Jhair
 */

public class Detalle {
    private int id;
    private int id_pro;
    private int cantidad;
    private double precio;
    private int id_Factura;
    
    //DETALLE DE CADA PRODUCTO
    //Constructor simple
    public Detalle(){
        
    }
    
    //Constructor completo
    public Detalle(int id, int id_pro, int cantidad, double precio, int id_Factura) {
        this.id = id;
        this.id_pro = id_pro;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id_Factura = id_Factura;
    }

    //Metodos Publicos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pro() {
        return id_pro;
    }

    public void setId_pro(int id_pro) {
        this.id_pro = id_pro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_Factura() {
        return id_Factura;
    }

    public void setId_Factura(int id_Factura) {
        this.id_Factura = id_Factura;
    }
}
