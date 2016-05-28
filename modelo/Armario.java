package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import modelo.Producto.CATEGORIA;

public class Armario implements Comparable<Armario> {
    
    private int idAula; // 0 = todos
    private int id; // 0 = todos
    private String nombre; // NOT NULL
    private String descripcion; // NOT NULL
    private Map<Long, Producto> tProductos;

    public Armario() {
        this.idAula = 0;
        this.nombre = "";
        this.descripcion = "";
        tProductos = new HashMap<>();
    }

    public Armario(int idAula, String nombre, String descripcion) {
        this.idAula = idAula;
        this.nombre = nombre;
        this.descripcion = descripcion;
        tProductos = new HashMap<>();
    }

    public int getId() { return id; }
    public int getIdAula() { return idAula; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Map<Long, Producto> gettProductos() { return tProductos; }

    public void setId(int id) { this.id = id; }
    public void setIdAula(int idAula) { this.idAula = idAula; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void settProductos(Map<Long, Producto> tProductos) { this.tProductos = tProductos; }
    
    //METODOS
    
    //ALTA, BAJA, LISTADO//
    public static boolean existeArmario(int id, ConexionBD conn) throws Exception {
        try {
            String sql = "SELECT count(*) FROM armarios WHERE id = " +id;
            ResultSet rs = conn.getSt().executeQuery(sql);
            rs.next();
            
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new Exception("Error existeArmario()\n", e);
        }
    }
    
    public int altaArmario(ConexionBD conn) throws Exception{
        // No es necesario hacer un existeAula porque siempre tiene un ID nuevo
        try {
            String sql = "INSERT INTO armarios(id_aula, nombre, descripcion) VALUES(" +this.idAula +", '" +this.nombre +"', '" +this.descripcion +"')";
            conn.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet generatedKeys = conn.getSt().getGeneratedKeys();
            generatedKeys.next();
            
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new Exception("Error altaAula()\n",e);
        }
    }
    
    public static void bajaArmario(int id, ConexionBD conn) throws Exception {
        if (!existeArmario(id, conn)) {
            throw new Exception("No existen armarios.");
        } else {
            try {
                String sql = "DELETE FROM armarios WHERE id = " +id;
                conn.getSt().executeUpdate(sql);
            } catch (SQLException e) {
                throw new Exception("Error bajaArmario()\n", e);
            }
        }
    }
    
    public static Map<Long, Producto> listarProductos(ConexionBD conn) throws Exception {
        Map <Long, Producto> tProductos = new HashMap<>();
        try{
            
            String sql = "SELECT * FROM productos";
            ResultSet rs = conn.getSt().executeQuery(sql);
            while(rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getLong("id"));
                producto.setIdArmario(rs.getInt("id_armario"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(CATEGORIA.valueOf(rs.getString("categoria")));
                producto.setDescripcion(rs.getString("descripcion"));
                tProductos.put(producto.getId(), producto);
            }
            
            sql = "SELECT * FROM referencias";
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
            throw new Exception("Error listarProductos\n", e);
        }
    }
    
    @Override
    public int compareTo(Armario o) {
        if (this.idAula != o.idAula) {
            return Integer.compare(this.idAula, o.idAula);
        } else {
            if (this.id == o.id) {
                return 0;
            } else {
                return Integer.compare(this.id, o.id);
            }
        }
    }
    
}
