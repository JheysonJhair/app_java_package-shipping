package Estructura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhair
 */

public class VehiculoDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Registrar vehiculo DAO
    public boolean RegistrarVehiculo(Vehiculo vl){
        String sql = "INSERT INTO tVehiculo (placa, marca, color, configuracion) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vl.getPlaca());
            ps.setString(2, vl.getMarca());
            ps.setString(3, vl.getColor());
            ps.setString(4, vl.getConfiguracion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
   //Listar vehiculo DAO
   public List ListarVehiculo(){
       List<Vehiculo> ListaVl = new ArrayList();
       String sql = "SELECT * FROM tVehiculo";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Vehiculo v1 = new Vehiculo();
               v1.setId(rs.getInt("id"));
               v1.setPlaca(rs.getString("placa"));
               v1.setMarca(rs.getString("marca"));
               v1.setColor(rs.getString("color"));
               v1.setConfiguracion(rs.getString("configuracion"));
               ListaVl.add(v1);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaVl;
   }
   
   //Eliminar Vehiculo DAO
   public boolean EliminarVehiculo(int id){
       String sql = "DELETE FROM tVehiculo WHERE id = ?";
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
   
   //Modificar Vehiculo DAO
   public boolean ModificarVehiculo(Vehiculo v1){
       String sql = "UPDATE tVehiculo SET placa = ?, marca = ?, color = ?, configuracion = ? WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);   
           ps.setString(1, v1.getPlaca());
           ps.setString(2, v1.getMarca());
           ps.setString(3, v1.getColor());
           ps.setString(4, v1.getConfiguracion());
           ps.setInt(5, v1.getId());
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
   
   //Buscar Vehiculo DAO
   public Vehiculo BuscarVehiculo(String placa){
       Vehiculo v1 = new Vehiculo();
       String sql = "SELECT * FROM tVehiculo WHERE placa = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, placa);
           rs = ps.executeQuery();
           if (rs.next()) {

               v1.setId(rs.getInt("id"));
               v1.setMarca(rs.getString("marca"));
               v1.setColor(rs.getString("color"));
               v1.setConfiguracion(rs.getString("configuracion"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return v1;
   }
}
