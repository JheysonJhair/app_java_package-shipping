package Estructura;

/**
 *
 * @author Jhair
 */

public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private int proveedor;
    private String proveedorPro;
    private String clientePro;
    private int stock;
    private double precio;
    private double volumen;
    private double peso;
    private String origenPro;
    private String destinoPro;

    //Constructor simple
    public Producto(){
        
    }

    //Contructor completo
    public Producto(int id, String codigo, String nombre, int proveedor, String proveedorPro,String clientePro, int stock, double precio, double volumen, double peso, String origenPro,String destinoPro) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.proveedorPro = proveedorPro;
        this.clientePro = clientePro;
        this.stock = stock;
        this.precio = precio;
        this.volumen = volumen;
        this.peso = peso;
        this.origenPro = origenPro;
        this.destinoPro = destinoPro;
    }

    //Metodos Publicos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public String getProveedorPro() {
        return proveedorPro;
    }

    public void setProveedorPro(String proveedorPro) {
        this.proveedorPro = proveedorPro;
    }
    
    public String getClientePro() {
        return clientePro;
    }

    public void setClientePro(String clientePro) {
        this.clientePro = clientePro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public String getOrigenPro() {
        return origenPro;
    }

    public void setOrigenPro(String origenPro) {
        this.origenPro = origenPro;
    }
    
    public String getDestinoPro() {
        return destinoPro;
    }

    public void setDestinoPro(String destinoPro) {
        this.destinoPro = destinoPro;
    }
}
