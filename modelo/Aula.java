package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aula implements Comparable<Aula> {

    private int id; // 0 = todos
    private String nombre; // NOT NULL
    private String descripcion; // NOT NULL
    transient private List<Armario> tArmarios; // 1 o +, ------0 = todos------

    // Constructor
    public Aula() {
        this.nombre = "";
        this.descripcion = "";
        tArmarios = new ArrayList<Armario>();
    }

    public Aula(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        tArmarios = new ArrayList<Armario>();
    }

    // GETTERS & SETTERS
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public List<Armario> gettArmarios() { return tArmarios; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void settArmarios(List<Armario> tArmarios) { this.tArmarios = tArmarios; }

    //METODOS
    public static boolean existeAula(int id, ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT count(*) FROM aulas WHERE id = " + id;
            ResultSet rs = conn.getSt().executeQuery(sql);

            return rs.next();
        } catch (SQLException e) {
            throw new Exception("Error existeAula()\n", e);
        }
    }

    public int altaAula(ConexionBD conn) throws Exception {
        // No es necesario hacer un existeAula porque siempre tiene auto_increment.
        try {

            String sql = "INSERT INTO aulas(nombre, descripcion) VALUES('"
                    + this.nombre + "','"
                    + this.descripcion + "')";

            conn.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            // Devolver la ID autogenerada.
            ResultSet generatedKeys = conn.getSt().getGeneratedKeys();
            generatedKeys.next();

            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new Exception("Error altaAula()\n", e);
        }
    }

    public static void bajaAula(int id, ConexionBD conn) throws Exception {
        if (!existeAula(id, conn)) {
            throw new Exception("No existen aulas.");
        }
        try {
            // Borrar primero armarios asociados
            String sql = "DELETE FROM armarios WHERE id_aula = " +id;
            conn.getSt().executeUpdate(sql);
            
            sql = "DELETE FROM aulas WHERE id = " + id;
            conn.getSt().executeUpdate(sql);

        } catch (SQLException e) {
            throw new Exception("Error bajaAula()\n", e);
        }
    }

    public static boolean isEmpty(ConexionBD conn) throws Exception {
        String sql = "SELECT count(*) FROM aulas";
        ResultSet rs = conn.getSt().executeQuery(sql);

        return !rs.next();
    }

    // Listado //
    public static Map<Integer, Aula> listarAulas(ConexionBD conn) throws Exception {
        try {
            Map<Integer, Aula> tAulas = new HashMap<>();
            String sql = "SELECT * FROM aulas";
            ResultSet rs = conn.getSt().executeQuery(sql);

            while (rs.next()) {
                Aula aula = new Aula();
                aula.setId(rs.getInt("id"));
                aula.setNombre(rs.getString("nombre"));
                aula.setDescripcion(rs.getString("descripcion"));
                tAulas.put(aula.getId(), aula);
            }
            
            sql = "SELECT * FROM armarios";
            rs = conn.getSt().executeQuery(sql);
            while (rs.next()) {
                Armario armario = new Armario();
                armario.setId(rs.getInt("id"));
                armario.setIdAula(rs.getInt("id_aula"));
                armario.setNombre(rs.getString("nombre"));
                armario.setDescripcion(rs.getString("descripcion"));
                
                tAulas.get(armario.getIdAula()).gettArmarios().add(armario);
            }

            return tAulas;

        } catch (Exception e) {
            throw new Exception("Error listadoAulas", e);
        }
    }
    
    public static int generarId(ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT max(id) + 1 FROM aulas";
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            int i = rs.getInt(1);
            return i;
        } catch (Exception e) {
            throw new Exception("Error generarId() ", e);
        }
    }
    
    public static void buscarAulas(ConexionBD conn, List<Aula> tAulas, String id, String nombre) {
        try {
            
            String sql = "SELECT * FROM aulas WHERE " +
                            "id LIKE '%" +id +"%' and " +
                            "nombre LIKE '%" +nombre +"%'";
            ResultSet rs = conn.getSt().executeQuery(sql);
            while (rs.next()) {
                Aula aula = new Aula();
                aula.setId(rs.getInt("id"));
                aula.setNombre(rs.getString("nombre"));
                aula.setDescripcion(rs.getString("descripcion"));
                tAulas.add(aula);
            }
            
        } catch (Exception e) {
            
        }
    }
    
    public void recuperarAula(ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT * FROM aulas WHERE id = " +id;
            ResultSet rs = conn.getSt().executeQuery(sql);

            rs.next();
            this.setId(rs.getInt("id"));
            this.setNombre(rs.getString("nombre"));
            this.setDescripcion(rs.getString("descripcion"));
                
        } catch (Exception e) {
            throw new Exception("Error recuperarAula()", e);
        }
    }

    public void updateAula(ConexionBD conn) throws Exception {
        try {
            String sql = "UPDATE aulas SET "+
                    "nombre = '" +nombre +
                    "', descripcion = '" +descripcion +
                    "' WHERE id = " +id;
            conn.getSt().executeUpdate(sql);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error updateAula()", e);
        }
    }
    
    @Override
    public int compareTo(Aula o) {
        return Integer.compare(this.id, o.id);
    }

}
