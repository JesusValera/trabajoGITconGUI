package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Registro implements Comparable<Registro> {
    
    private int id;
    private String numRef;
    private long idProducto;
    private LocalDate fecAlta;
    private LocalDate fecBaja;
    private String causaBaja;
    private String accion;

    public Registro() {
        id = 0;
        numRef = "";
        idProducto = 0L;
        fecAlta = LocalDate.now();
        fecBaja = null;
        causaBaja = "";
        accion = "ALTA";
    }

    public Registro(long idProducto, String numRef) {
        id = 0;
        this.numRef = numRef;
        this.idProducto = idProducto;
        fecAlta = LocalDate.now();
        fecBaja = null;
        causaBaja = "";
        accion = "ALTA";
    }

    public Registro(String numRef, long idProducto, String nombre, String causaBaja) {
        id = 0;
        this.numRef = numRef;
        this.idProducto = idProducto;
        this.fecAlta = LocalDate.now();
        this.fecBaja = null;
        this.causaBaja = causaBaja;
        this.accion = "ALTA";
    }

    public int getId() { return id; }
    public String getNumRef() { return numRef; }
    public long getIdProducto() { return idProducto; }
    public String getCausaBaja() { return causaBaja; }
    public String getFecAlta() {
        if (fecAlta != null) {
            return fecAlta.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        }
        return "";
    }
    public String getFecBaja() {
        if (fecBaja != null) {
            return fecBaja.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        }
        return "";
    }
    public String getAccion() { return accion; }
    
    public void setId(int id) { this.id = id; }
    public void setNumRef(String numRef) { this.numRef = numRef; }
    public void setIdProducto(long idProducto) { this.idProducto = idProducto; }
    public void setCausaBaja(String causaBaja) { this.causaBaja = causaBaja; }
    public void setFecAlta(LocalDate fecAlta) { this.fecAlta = fecAlta; }
    public void setFecAlta(String fecAlta) {
        this.fecAlta = LocalDate.of(Integer.parseInt(fecAlta.substring(6, 10)),
                                    Integer.parseInt(fecAlta.substring(3, 5)),
                                    Integer.parseInt(fecAlta.substring(0, 2)));
    }
    public void setFecBaja(LocalDate fecBaja) { this.fecBaja = fecBaja; }
    public void setFecBaja(String fecBaja) {
        this.fecBaja = LocalDate.of(Integer.parseInt(fecBaja.substring(6, 10)),
                                    Integer.parseInt(fecBaja.substring(3, 5)),
                                    Integer.parseInt(fecBaja.substring(0, 2)));
    }
    public static LocalDate setFecBajaStatic(String fecBaja) {
        return LocalDate.of(Integer.parseInt(fecBaja.substring(6, 10)),
                                    Integer.parseInt(fecBaja.substring(3, 5)),
                                    Integer.parseInt(fecBaja.substring(0, 2)));
    }
    public void setAccion(String accion) { this.accion = accion; }

    // METODOS
    public static boolean existeRegistro(ConexionBD conn, String numRef) throws Exception {
        try {
            
            String sql = "SELECT count(*) FROM registros WHERE num_ref = '" +numRef +"'";
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            return rs.getInt(1) > 0;
        } catch (Exception e) {
            throw new Exception("Error existeRegistro\n", e);
        }
    }
    
    public void altaRegistro(ConexionBD conn) throws Exception {
        try {
            String sql = "INSERT INTO registros VALUES(" +
                    this.id +", '" +
                    this.numRef +"', " +
                    this.idProducto + ", '" +
                    Date.valueOf(fecAlta) +"', "+
                    ((fecBaja==null)?null:"'" +TransformarFechaBBDD(getFecBaja()) +"'") +", '" +
                    this.causaBaja +"', '" + 
                    this.accion +"')";
            
            conn.getSt().executeUpdate(sql);
            
        } catch (Exception e) {
            throw new Exception("Error altaRegistro\n", e);
        }
    }
    
    public static void bajaRegistro(String numRef, String fecBaja, String causa, ConexionBD conn) throws Exception {
        if (!existeRegistro(conn, numRef)) {
            throw new Exception("No existe el registro");
        }
        try {
            String sql = "UPDATE registros SET " +
                    "fecha_baja = '" +Date.valueOf(setFecBajaStatic(fecBaja))+
                    "', causa_baja = '" +causa +
                    "' WHERE num_ref = '" +numRef +"'";
            conn.getSt().executeUpdate(sql);
            
        } catch (Exception e) {
            throw new Exception("Error bajaRegistro\n", e);
        }
    }
    
    public static int generarId(ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT IFNULL(max(id), 0) + 1 FROM registros";
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            int i = rs.getInt(1);
            return i;
        } catch (Exception e) {
            throw new Exception("Error generarId() ", e);
        }
    }
    
    public static void buscarRegistro(ConexionBD conn, List<Registro> tRegistros, String idProducto, String numRef, String fechaAlta1, String fechaAlta2, String fechaBaja1, String fechaBaja2) throws Exception {
        try {
            String sql = "SELECT * FROM registros WHERE "
                    + "id_producto LIKE '%" +idProducto +"%' AND "
                    + "num_ref LIKE '%" +numRef +"%' AND "
                    + "(fecha_alta between " +fechaAlta1 +" AND " +fechaAlta2 +") AND "
                    + "(fecha_baja between " +fechaBaja1 +" AND " +fechaBaja2 +" OR fecha_baja IS NULL)";
            ResultSet rs = conn.getSt().executeQuery(sql);
            while(rs.next()) {
                Registro registro = new Registro();
                registro.setNumRef(rs.getString("num_ref"));
                registro.setIdProducto(rs.getLong("id_producto"));
                Date fecAl = rs.getDate("fecha_alta");
                registro.setFecAlta(((fecAl==null)?null:fecAl.toLocalDate()));
                Date fecBa = rs.getDate("fecha_baja");
                registro.setFecBaja((fecBa==null)?null:fecBa.toLocalDate());
                registro.setCausaBaja(rs.getString("causa_baja"));
                registro.setAccion(rs.getString("accion"));
                tRegistros.add(registro);
            }
        } catch (Exception e) {
            throw new Exception("Error buscarRegistro\n", e);
        }
        
    }
    
    public static String obtenerFechaMenor(ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT MIN(fecha_alta) FROM registros";
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            return rs.getString(1);
        } catch (Exception e) {
            throw new Exception("Error obtenerFechaMenor()", e);
        }
    }
    
    public static String obtenerFechaMayor(ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT MAX(fecha_baja) FROM registros";
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            return rs.getString(1);
        } catch (Exception e) {
            throw new Exception("Error obtenerFechaMenor()", e);
        }
    }
    /**
     * Metodo estatico que sirve para transformar la fecha escrita en formato de BBDD MySQL.
     * 
     * @param fecha
     * @return 
     */
    public static String TransformarFechaBBDD(String fecha) {
        LocalDate fechita = LocalDate.of(Integer.parseInt(fecha.substring(6, 10)),
                                    Integer.parseInt(fecha.substring(3, 5)),
                                    Integer.parseInt(fecha.substring(0, 2)));
        if (fechita != null) {
            return fechita.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
        }
        return "";
    }

    @Override
    public int compareTo(Registro o) {
        return String.valueOf(this.getId()).compareTo(String.valueOf(o.getId()));
    }
    
    /**
     * Metodos antiguos para el programa desde consola.
     * @param conn
     * @return
     * @throws Exception 
     */
    public static LinkedList<Registro> listarRegistro(ConexionBD conn) throws Exception {
        try {
            LinkedList<Registro> tRegistros = new LinkedList<>();
            String sql = "SELECT * FROM registros";
            ResultSet rs = conn.getSt().executeQuery(sql);
            while(rs.next()) {
                Registro registro = new Registro();
                registro.setNumRef(rs.getString("num_ref"));
                registro.setIdProducto(rs.getLong("id_producto"));
                Date fecAl = rs.getDate("fecha_alta");
                registro.setFecAlta(((fecAl==null)?null:fecAl.toLocalDate()));
                Date fecBa = rs.getDate("fecha_baja");
                registro.setFecBaja((fecBa==null)?null:fecBa.toLocalDate());
                registro.setCausaBaja(rs.getString("causa_baja"));
                tRegistros.add(registro);
            }
            
            return tRegistros;
        } catch (Exception e) {
            throw new Exception("Error listarRegistro\n", e);
        }
        
    }

    public String datosIterador() {
        return ("NUMREF: " +this.getNumRef() +". ID: " +this.getIdProducto() 
                +".\t FECHA_ALTA: " +this.getFecAlta()
                +". FECHA_BAJA: " +this.getFecBaja() +"\t\t. CAUSA: " +this.getCausaBaja());
    }
    
}
