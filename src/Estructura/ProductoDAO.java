package Estructura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jhair
 */

public class ProductoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    //Registrar producto DAO
    public boolean RegistrarProductos(Producto pro){
        String sql = "INSERT INTO tProducto (codigo, nombre, proveedor, cliente, stock, precio, volumen, peso, origen, destino) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getProveedor());
            ps.setString(4, pro.getClientePro());
            ps.setInt(5, pro.getStock());
            ps.setDouble(6, pro.getPrecio());
            ps.setDouble(7, pro.getVolumen());
            ps.setDouble(8, pro.getPeso());
            ps.setString(9, pro.getOrigenPro());
            ps.setString(10, pro.getDestinoPro());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    //Listar Producto DAO
    public List ListarProductos(){
       List<Producto> Listapro = new ArrayList();
       String sql = "SELECT pr.id AS id_proveedor, pr.nombre AS nombre_proveedor, p.* FROM tProveedor pr INNER JOIN tProducto p ON pr.id = p.proveedor ORDER BY p.id DESC";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Producto pro = new Producto();
               pro.setId(rs.getInt("id"));
               pro.setCodigo(rs.getString("codigo"));
               pro.setNombre(rs.getString("nombre"));
               pro.setProveedor(rs.getInt("id_proveedor"));
               pro.setProveedorPro(rs.getString("nombre_proveedor"));
               pro.setClientePro(rs.getString("cliente"));
               pro.setStock(rs.getInt("stock"));
               pro.setPrecio(rs.getDouble("precio"));
               pro.setVolumen(rs.getDouble("volumen"));
               pro.setPeso(rs.getDouble("peso"));
               pro.setOrigenPro(rs.getString("origen"));
               pro.setDestinoPro(rs.getString("destino"));
               Listapro.add(pro);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return Listapro;
   }
    
    
    //Eliminar Producto DAO
    public boolean EliminarProductos(int id){
       String sql = "DELETE FROM tProducto WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
    
    //Modificar producto DAO
    public boolean ModificarProductos(Producto pro){
       String sql = "UPDATE tProducto SET codigo = ?, nombre = ?, proveedor = ?, cliente = ?, stock = ?, precio = ?, volumen = ?, peso = ?, origen = ?, destino = ? WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, pro.getCodigo());
           ps.setString(2, pro.getNombre());
           ps.setInt(3, pro.getProveedor());
           ps.setString(4, pro.getClientePro());
           ps.setInt(5, pro.getStock());
           ps.setDouble(6, pro.getPrecio());
           ps.setDouble(7, pro.getVolumen());
           ps.setDouble(8, pro.getPeso());
           ps.setString(9, pro.getOrigenPro());
           ps.setString(10, pro.getDestinoPro());
           ps.setInt(11, pro.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
    
    //Buscar Producto DAO
    public Producto BuscarPro(String cod){
        Producto producto = new Producto();
        String sql = "SELECT * FROM tProducto WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setClientePro(rs.getString("cliente"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setVolumen(rs.getDouble("volumen"));
                producto.setPeso(rs.getDouble("peso"));
                producto.setOrigenPro(rs.getString("origen"));
                producto.setDestinoPro(rs.getString("destino"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
    
    //ID produycto y proveedor
    public Producto BuscarId(int id){
        Producto pro = new Producto();
        String sql = "SELECT pr.id AS id_proveedor, pr.nombre AS nombre_proveedor, p.* FROM tProveedor pr INNER JOIN tProducto p ON p.proveedor = pr.id WHERE p.id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getInt("proveedor"));
                pro.setProveedorPro(rs.getString("nombre_proveedor"));
                pro.setClientePro(rs.getString("cliente"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setPrecio(rs.getDouble("volumen"));
                pro.setPrecio(rs.getDouble("peso"));
                pro.setOrigenPro(rs.getString("origen"));
                pro.setDestinoPro(rs.getString("destino"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pro;
    }
    
    //Buscar Proveedor DAO
    public Proveedor BuscarProveedor(String nombre){
        Proveedor pr = new Proveedor();
        String sql = "SELECT * FROM tProveedor WHERE nombre = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                pr.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pr;
    }
    //Buscar Proveedor DAO
    public Proveedor BuscarCliente(String dni){
        Proveedor pr = new Proveedor();
        String sql = "SELECT * FROM tCliente WHERE dni = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                pr.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pr;
    }
}
