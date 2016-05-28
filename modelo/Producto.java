package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producto implements Comparable<Producto> {

    public enum CATEGORIA {Otra, Hardware, Software};
    
    private long id; // automatico
    private int idArmario; // 0 = no asignado
    private String nombre; // NOT NULL
    private List<Referencia> tReferencias;
    private CATEGORIA categoria;
    private String descripcion; // NULL

    public Producto() {
        idArmario = 0;
        nombre = "";
        tReferencias = new ArrayList<>();
        categoria = null;
        descripcion = "";
    }

    public Producto(int idArmario, String nombre, CATEGORIA categoria, String descripcion) {
        this.idArmario = idArmario;
        this.nombre = nombre;
        this.tReferencias = new ArrayList<>();
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public long getId() { return id; }
    public int getIdArmario() { return idArmario; }
    public String getNombre() { return nombre; }
    public List<Referencia> gettReferencias() { return tReferencias; }
    public CATEGORIA getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    
    public void setId(long id) { this.id = id; }
    public void setIdArmario(int idArmario) { this.idArmario = idArmario; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void settReferencias(List<Referencia> tReferencias) { this.tReferencias = tReferencias; }
    public void setCategoria(CATEGORIA categoria) { this.categoria = categoria; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    //METODOS
    
    //altas, bajas y listado por aula/armario
    public static boolean existeProducto(long id, ConexionBD conn) throws Exception {
        String sql;
        try {
            sql = "SELECT count(*) FROM productos WHERE id = " +id;
            ResultSet rs = conn.getSt().executeQuery(sql);
            
            return rs.next();
            
        } catch (Exception e) {
            throw new Exception("Error existeProducto\n", e);
        }
    }
    
    public int altaProducto(ConexionBD conn) throws Exception {
        String sql;
        try {
            
            sql = "INSERT INTO productos(id_armario, nombre, categoria, descripcion) VALUES("+
                    this.idArmario +", '"+
                    this.nombre +"', '"+
                    this.categoria.toString() +"', '"+
                    this.descripcion +"')";
            
            conn.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = conn.getSt().getGeneratedKeys();
            generatedKeys.next();
            
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new Exception("Error altaProducto\n", e);
        }
    }
    
    public static void bajaProducto(long id, ConexionBD conn) throws Exception {
        if (existeProducto(id, conn)) {
            throw new Exception("¡No existe producto!");
        }
        try {
            
            existeProducto(id, conn);
            String sql = "DELETE FROM productos WHERE id = " +id;
            conn.getSt().executeUpdate(sql);
            
        } catch (Exception e) {
            throw new Exception("Error bajaProducto\n", e);
        }
    }
    
    public static Producto getProducto(long id, ConexionBD conn) throws Exception {
        String sql = "SELECT * FROM productos WHERE id = " +id;
        try {
            
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            return new Producto(rs.getInt("id_armario"), rs.getString("nombre"), 
                                CATEGORIA.valueOf(rs.getString("categoria")), 
                                rs.getString("descripcion"));
            
        } catch (Exception e) {
            throw new Exception("Error getProducto\n", e);
        }
    }
    
    public static Map<Long, Producto> listadoProductosDeArmario(ConexionBD conn, String nombreAula) throws Exception {
        Map<Long, Producto> tProductos = new HashMap<>();
        try {
            if (nombreAula.equals("0")) {
                nombreAula = "";
            }
            String sql = "SELECT p.id, p.nombre, p.categoria, p.descripcion" +
                            " FROM armarios arm, aulas au, productos p" +
                            " WHERE arm.id_aula = au.id" +
                            " AND p.id_armario = arm.id" +
                            " AND au.nombre like '%" +nombreAula +"%'";
            ResultSet rs = conn.getSt().executeQuery(sql);
            
            while(rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getLong("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(CATEGORIA.valueOf(rs.getString("categoria")));
                producto.setDescripcion(rs.getString("descripcion"));
                tProductos.put(producto.getId(), producto);
            }
            
            return tProductos;
        } catch (Exception e) {
            throw new Exception("Error listadoProductos\n", e);
        }
    }
    
    public static Map<Long, Producto> listarProductos(int idArm, ConexionBD conn) throws Exception {
        Map<Long, Producto> tProductos = new HashMap<>();
        try {
            String sql = "SELECT p.id, p.nombre, p.categoria, p.descripcion" +
                            " FROM armarios arm, aulas au, productos p" +
                            " WHERE arm.id_aula = au.id" +
                            " AND p.id_armario = arm.id" +
                            " AND p.id_armario = " +idArm;
            ResultSet rs = conn.getSt().executeQuery(sql);
            long idProd = 0;
            while(rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getLong("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(CATEGORIA.valueOf(rs.getString("categoria")));
                producto.setDescripcion(rs.getString("descripcion"));
                tProductos.put(producto.getId(), producto);
                idProd = producto.getId();
            }
            
            sql = "SELECT * FROM referencias WHERE id_producto = " +idProd;
            rs = conn.getSt().executeQuery(sql);
            while(rs.next()) {
                Referencia referencia = new Referencia();
                referencia.setNumRef(rs.getString("num_ref"));
                referencia.setIdProducto(rs.getLong("id_producto"));
                referencia.setBaja(rs.getBoolean("baja"));
                tProductos.get(referencia.getIdProducto()).gettReferencias().add(referencia);
            }
            
            return tProductos;
        } catch (Exception e) {
            throw new Exception("Error listadoProductos\n", e);
        }
    }
    
    @Override
    public int compareTo(Producto o) {
        return this.getNombre().compareToIgnoreCase(o.getNombre());
    }
    
}
