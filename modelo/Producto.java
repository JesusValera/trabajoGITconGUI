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
    private Integer idArmario; // 0 = no asignado
    private String nombre; // NOT NULL
    private List<Referencia> tReferencias;
    private CATEGORIA categoria;
    private String descripcion; // NULL

    public Producto() {
        idArmario = null;
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
    public Integer getIdArmario() { return idArmario; }
    public String getNombre() { return nombre; }
    public List<Referencia> gettReferencias() { return tReferencias; }
    public CATEGORIA getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    
    public void setId(long id) { this.id = id; }
    public void setIdArmario(Integer idArmario) { this.idArmario = idArmario; }
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
    
    public void altaProducto(ConexionBD conn) throws Exception {
        try {
            String sql = "INSERT INTO productos VALUES("+
                    this.id +", " +
                    ((this.idArmario==null)?null:this.idArmario) +", '"+
                    this.nombre +"', '"+
                    ((this.categoria==null)?"":this.categoria.toString()) +"', '"+
                    this.descripcion +"')";
            
            conn.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = conn.getSt().getGeneratedKeys();
            generatedKeys.next();
            
            //return generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new Exception("Error altaProducto\n", e);
        }
    }
    
    public static void bajaProducto(long id, ConexionBD conn) throws Exception {
        if (!existeProducto(id, conn)) {
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
    
    public static void buscarProductos(ConexionBD conn, List<Producto> tProductos, String id, String idArmario, String nombre, String categoria) throws Exception {
        try {
            String sql = "SELECT * FROM productos WHERE " +
                            "id LIKE '%" +id +"%' AND " +
                            "(id_armario LIKE '%" +idArmario +"%' " +
                            "OR id_armario IS NULL) AND " +
                            "nombre LIKE '%" +nombre +"%' AND " +
                            "(categoria = '" +categoria +"')";
            ResultSet rs = conn.getSt().executeQuery(sql);
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getLong("id"));
                producto.setIdArmario(rs.getInt("id_armario"));
                if (rs.wasNull()) {
                    producto.setIdArmario(null);
                }
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                if (rs.getString("categoria").equals("")) {
                    producto.setCategoria(null);
                } else {
                    producto.setCategoria(CATEGORIA.valueOf(rs.getString("categoria")));
                }
                tProductos.add(producto);
            }
        } catch (Exception e) {
            throw new Exception("Error buscarProductos()", e);
        }
    }
    
    public static int generarId(ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT IFNULL(max(id), 0) + 1 FROM productos";
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            int i = rs.getInt(1);
            return i;
        } catch (Exception e) {
            throw new Exception("Error generarId() ", e);
        }
    }
    
    public void recuperarProducto(ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT * FROM productos WHERE id = " +id;
            ResultSet rs = conn.getSt().executeQuery(sql);

            rs.next();
            this.setId(rs.getInt("id"));
            this.setIdArmario(rs.getInt("id_armario"));
            if (rs.wasNull()) {
                this.setIdArmario(null);
            }
            this.setNombre(rs.getString("nombre"));
            this.setDescripcion(rs.getString("descripcion"));
            //this.setCategoria(CATEGORIA.valueOf(rs.getString("categoria")));
            if (rs.getString("categoria").equals("")) {
                this.setCategoria(null);
            } else {
                this.setCategoria(CATEGORIA.valueOf(rs.getString("categoria")));
            }    
        } catch (Exception e) {
            throw new Exception("Error recuperarProducto()", e);
        }
    }
    
    public void updateProducto(ConexionBD conn) throws Exception {
        try {
            String sql = "UPDATE productos SET "
                    + "id_armario = " +idArmario +", "
                    + "nombre = '" +nombre +"', "
                    + "descripcion = '" +descripcion +"', "
                    + "categoria = '" +categoria
                    + "' WHERE id = " +id;
            conn.getSt().executeUpdate(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error updateProducto()", e);
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
        return String.valueOf(this.getId()).compareTo(String.valueOf(o.getId()));
    }
    
}