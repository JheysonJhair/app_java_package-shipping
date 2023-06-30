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

public class ConductorDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    //Registrar conductor DAO
    public boolean RegistrarConductor(Conductor cr){
        String sql = "INSERT INTO tConductor (dni, nombre, licencia, direccion, telefono, vehiculo) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cr.getDni());
            ps.setString(2, cr.getNombre());
            ps.setString(3, cr.getLicencia());
            ps.setString(4, cr.getDireccion());
            ps.setString(5, cr.getTelefono());
            ps.setString(6, cr.getVehiculoCond());
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
    
   //Listar Conductor DAO
   public List ListarConductor(){
       List<Conductor> ListaCR = new ArrayList();
       String sql = "SELECT * FROM tConductor";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Conductor cr = new Conductor();
               cr.setId(rs.getInt("id"));
               cr.setDni(rs.getString("dni"));
               cr.setNombre(rs.getString("nombre"));
               cr.setLicencia(rs.getString("licencia"));
               cr.setDireccion(rs.getString("direccion"));
               cr.setTelefono(rs.getString("telefono"));
               cr.setVehiculoCond(rs.getString("vehiculo"));
               ListaCR.add(cr);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaCR;
   }
   
   //Eliminar Conductor DAO
   public boolean EliminarConductor(int id){
       String sql = "DELETE FROM tConductor WHERE id = ?";
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
   
   //Modificar Conductor DAO
   public boolean ModificarConductor(Conductor cr){
       String sql = "UPDATE tConductor SET dni = ?, nombre = ?, licencia = ?, direccion = ?, telefono = ?, vehiculo = ? WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);   
           ps.setString(1, cr.getDni());
           ps.setString(2, cr.getNombre());
           ps.setString(3, cr.getLicencia());
           ps.setString(4, cr.getDireccion());
           ps.setString(5, cr.getTelefono());
           ps.setString(6, cr.getVehiculoCond());
           ps.setInt(7, cr.getId());
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
   
   //Buscar Conductor DAO
   public Conductor BuscarConductor(int dni){
       Conductor cr = new Conductor();
       String sql = "SELECT * FROM tConductor WHERE dni = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, dni);
           rs = ps.executeQuery();
           if (rs.next()) {
               cr.setId(rs.getInt("id"));
               cr.setNombre(rs.getString("nombre"));
               cr.setLicencia(rs.getString("licencia"));
               cr.setDireccion(rs.getString("direccion"));
               cr.setTelefono(rs.getString("telefono"));
               cr.setVehiculoCond(rs.getString("vehiculo"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return cr;
   }
}
