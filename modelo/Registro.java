package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Registro implements Comparable<Registro> {
    
    private String numRef;
    private long idProducto;
    private String nombre;
    private LocalDate fecAlta;
    private LocalDate fecBaja;
    private String causaBaja;

    public Registro() {
        numRef = "";
        idProducto = 0L;
        nombre = "";
        fecAlta = LocalDate.now();
        fecBaja = null;
        causaBaja = "";
    }

    public Registro(long idProducto, String numRef) {
        this.numRef = numRef;
        this.idProducto = idProducto;
        fecAlta = LocalDate.now();
        fecBaja = null;
        causaBaja = "";
    }

    public Registro(String numRef, long idProducto, String nombre, String causaBaja) {
        this.numRef = numRef;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.fecAlta = LocalDate.now();
        this.fecBaja = null;
        this.causaBaja = causaBaja;
    }

    public String getNumRef() { return numRef; }
    public long getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
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
    
    public void setNumRef(String numRef) { this.numRef = numRef; }
    public void setIdProducto(long idProducto) { this.idProducto = idProducto; }
    public void setNombre(String nombre) { this.nombre = nombre; }
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
            
            String sql = "INSERT INTO registros VALUES('" +
                    this.numRef +"', " +
                    this.idProducto + ", '" +
                    this.nombre +"', '" +
                    Date.valueOf(fecAlta) +"', "+
                    ((fecBaja==null)?null:"'" +getFecBaja() +"'") +", '" +
                    this.causaBaja + "')";
            
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
    
    @Override
    public int compareTo(Registro o) {
        if (fecAlta.isBefore(o.fecAlta)) {
            return 1;
        } else if (fecAlta.isAfter(fecAlta)) {
            return -1;
        } else {
            return 0;
        }
    }
    
    public static LinkedList<Registro> listarRegistro(ConexionBD conn) throws Exception {
        try {
            LinkedList<Registro> tRegistros = new LinkedList<>();
            String sql = "SELECT * FROM registros";
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
            throw new Exception("Error listarRegistro\n", e);
        }
        
    }

    public String datosIterador() {
        return ("NUMREF: " +this.getNumRef() +". ID: " +this.getIdProducto() 
                +".\tNOMBRE: " +this.getNombre() +". FECHA_ALTA: " +this.getFecAlta()
                +". FECHA_BAJA: " +this.getFecBaja() +"\t\t. CAUSA: " +this.getCausaBaja());
    }
    
}
