/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;
import ClasesPojo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raul
 */
public class Conexionbd  {
    
    
 static String query= "";
    public static Connection conectar = null;
   
    private PreparedStatement sentencia=null;
    public ResultSet resultado=null;
    private static Conexionbd instancia;
    private Statement s = null;
    
    
    
    public static Conexionbd getInstancia()
    {
        if (instancia==null)
        {
            instancia=new Conexionbd();
        }
        return instancia;
    }
    private static String usuario;

    /**
     * Get the value of usuario
     *
     * @return the value of usuario
     */
    public static String getUsuario() {
        return usuario;
    }

    /**
     * Set the value of usuario
     *
     * @param usuario new value of usuario
     */
    public static void setUsuario(String usuario) {
        Conexionbd.usuario = usuario;
    }
    
    private static String clave;

    /**
     * Get the value of clave
     *
     * @return the value of clave
     */
    public static String getClave() {
        return clave;
    }

    /**
     * Set the value of clave
     *
     * @param clave new value of clave
     */
    public static void setClave(String clave) {
        Conexionbd.clave = clave;
    }

    private static String cadenaConexion;

    /**
     * Get the value of cadenaConexion
     *
     * @return the value of cadenaConexion
     */
    public static String getCadenaConexion() {
        return cadenaConexion;
    }

    /**
     * Set the value of cadenaConexion
     *
     * @param cadenaConexion new value of cadenaConexion
     */
    public static void setCadenaConexion(String cadenaConexion) {
        Conexionbd.cadenaConexion = cadenaConexion;
    }

    

    public Conexionbd() {
      
    }

    public void conectar()
    {
        try {
            
            conectar = DriverManager.getConnection(cadenaConexion, usuario, clave);
   s=conectar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        } catch (SQLException ex) {
            Logger.getLogger(Conexionbd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconectar()
    {
        try {
            conectar.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexionbd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ejecutar(String cadenaSql)throws SQLException
    {
        try {
            sentencia=conectar.prepareStatement(cadenaSql);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
           throw ex;
        }
        
        
    }
     public void executeQuery(String q) throws SQLException
    {
      query =q;  
    try {
        
        
       resultado= s.executeQuery(query);
    }catch (SQLException ex){
        throw ex;
    }
        
    
    }
    
    
    
    
}
