/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestiones;
import CapaDatos.Conexionbd;
import ClasesPojo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author Raul
 */
public class GestionUsuario   implements IGestion  {

    //private ResultSet resultado=null;
    private Statement s = null;
        public Usuario usuario;
        public static int Id;
        public static String Nombre=";";
        public static String Apellido="";
        public static int Cedula;
        

    public GestionUsuario() {
        
        usuario=new Usuario(0,"","","");
        Conexionbd.setUsuario("root");
        Conexionbd.setClave("");
        Conexionbd.setCadenaConexion("jdbc:mysql://localhost/usuariobd");
        
    }
        
        

    /**
     * Get the value of usuario
     *
     * @return the value of usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Set the value of usuario
     *
     * @param usuario new value of usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Insertar() throws SQLException {
        
        try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("insert into usuario (Id,Nombre,Apellido,Cedula) values ("+usuario.getId()+",'"+usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getCedula()+"')");
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
        
    }

    @Override
    public   void Consultar() throws SQLException {       
     try
        {
         

            Conexionbd.getInstancia().conectar();
            Conexionbd.getInstancia().executeQuery("SELECT * FROM usuario where id="+usuario.getId());
            while (Conexionbd.getInstancia().resultado.next()) {
                
                Id=Integer.parseInt(Conexionbd.getInstancia().resultado.getString("id"));
                Nombre=Conexionbd.getInstancia().resultado.getString("nombre");
                Apellido=Conexionbd.getInstancia().resultado.getString("apellido");
                Cedula =Integer.parseInt(Conexionbd.getInstancia().resultado.getString("cedula"));
                
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }          
    }

    @Override
    public void Modificar() throws SQLException {
      try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("UPDATE usuario SET apellido='" +usuario.getApellido()+ "' ,cedula='" +usuario.getCedula()+"' ,id='" +usuario.getId()+ "' ,nombre='" +usuario.getNombre()+"' WHERE Id="+usuario.getId());        
         //Conexionbd.getInstancia().ejecutar("insert into usuario (Id,Nombre,Apellido,Cedula) values ("+usuario.getId()+",'"+usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getCedula()+"')");
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
    }

    @Override
    public void Eliminar() throws SQLException {
     try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("DELETE FROM usuario where Id=" +usuario.getId());        
         
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
    }
    
    
}
