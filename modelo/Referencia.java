package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Referencia {
    
    private String numRef;
    private long idProducto;
    private boolean baja;
    private List<Registro> tRegistro;

    public Referencia() {
        this.numRef = "";
        this.idProducto = 0;
        this.baja = false;
        tRegistro = new ArrayList<>();
    }
    
    public Referencia(String numRef) {
        this.numRef = numRef;
        this.idProducto = 0L;
        baja = false;
        tRegistro = new ArrayList<>();
    }
    
    public Referencia(String numRef, long idProducto, boolean baja) {
        this.numRef = numRef;
        this.idProducto = idProducto;
        this.baja = baja;
        tRegistro = new ArrayList<>();
    }

    // GETTERS AND SETTERS
    public String getNumRef() { return numRef; }
    public long getIdProducto() { return idProducto; }
    public boolean isBaja() { return baja; }
    public List<Registro> gettRegistro() { return tRegistro; }
    
    public void setNumRef(String numRef) { this.numRef = numRef; }
    public void setIdProducto(long idProducto) { this.idProducto = idProducto; }
    public void setBaja(boolean baja) { this.baja = baja; }
    public void settRegistro(List<Registro> tRegistro) { this.tRegistro = tRegistro; }
    
    //Alta
    public static boolean existeReferencia(String numRef, ConexionBD conn) throws Exception {
        try {
            
            String sql = "SELECT count(*) FROM referencias WHERE num_ref = '" +numRef +"'";
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            return rs.getInt(1) > 0;
        } catch (Exception e) {
            throw new Exception("Error existeReferencia\n", e);
        }
    }
    
    public void altaReferencia(ConexionBD conn) throws Exception {
        try {
            
            String sql = "INSERT INTO referencias (num_ref, id_producto, baja) VALUES('"
                    +this.numRef +"', "
                    +this.idProducto +", "
                    +this.baja +")";
            conn.getSt().executeUpdate(sql);
            
        } catch (SQLException e) {
            throw new Exception("Error altaReferencia\n", e);
        }
    }
    
    public static void bajaReferencia(String numRef, ConexionBD conn) throws Exception {
        try {
            
            String sql = "UPDATE referencias SET baja = " +true
                            +" WHERE num_ref = '" +numRef +"'";
            conn.getSt().executeUpdate(sql);
            
        } catch (Exception e) {
            throw new Exception("Error bajaReferencia()\n", e);
        }
    }
    
    public static List<String> getReferenciasCreadas(long idProducto, ConexionBD conn) throws Exception {
        try {
            
            String sql = "SELECT num_ref FROM referencias WHERE id_producto = " +idProducto;
            ResultSet rs = conn.getSt().executeQuery(sql);
            List<String> referencias = new ArrayList<>();
            
            while (rs.next()) {
                referencias.add(rs.getString(1));
            }
            
            return referencias;
        } catch (Exception e) {
            throw new Exception("Error getReferencia\n", e);
        }
    }
    
    public static void setBajaReferencia(String numRef, ConexionBD conn) throws Exception {
        try {
            
            String sql = "UPDATE referencias SET baja = true WHERE num_ref = '" +numRef +"'";
            conn.getSt().executeUpdate(sql);
            
        } catch (Exception e) {
            throw new Exception("Error setBajaReferencia\n", e);
        }
    }

    public static List<Registro> listarRegistro(String numRef, ConexionBD conn) throws Exception {
        List<Registro> tRegistros = new ArrayList<>();
        try{
            
            String sql = "SELECT * FROM registros WHERE num_ref = '" +numRef +"'";
            ResultSet rs = conn.getSt().executeQuery(sql);
            
            while(rs.next()) {
                Registro registro = new Registro();
                registro.setNumRef(rs.getString("num_ref"));
                registro.setIdProducto(rs.getLong("id_producto"));
                registro.setNombre(rs.getString("nombre"));
                Date fecAl = rs.getDate("fecha_alta");
                registro.setFecAlta(((fecAl==null)?null:fecAl.toLocalDate()));
                Date fecBa = rs.getDate("fecha_baja");
                registro.setFecBaja((fecBa==null)?null:fecBa.toLocalDate());
                registro.setCausaBaja(rs.getString("causa_baja"));
                tRegistros.add(registro);
            }
            
            return tRegistros;
        } catch (Exception e) {
            throw new Exception("Error listarProductos\n", e);
        }
    }
    
}
