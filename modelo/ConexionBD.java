package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private Connection conn;
    private Statement st;

    // Constructor
    public ConexionBD() {
        conn = null;
        st = null;
    }

    // Getters
    public Connection getConn() { return conn; }
    public Statement getSt() { return st; }
    
    // Metodos
    private void crearTablas() throws Exception {
        String sql;
        try {
            // aulas
            sql = "CREATE TABLE IF NOT EXISTS aulas (" +
                    "id INTEGER," +
                    "nombre VARCHAR(20) NOT NULL," +
                    "descripcion VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT aula_id_pk PRIMARY KEY(id)," +
                    "CONSTRAINT aula_nom_uk UNIQUE (id, nombre, descripcion))";
            st.executeUpdate(sql);
            // armarios
            sql = "CREATE TABLE IF NOT EXISTS armarios (" +
                    "id INTEGER," +
                    "id_aula INTEGER NOT NULL," +
                    "nombre VARCHAR(20) NOT NULL," +
                    "descripcion VARCHAR(100) NOT NULL," +
                    "CONSTRAINT arm_idau_id_pk PRIMARY KEY(id)," +
                    "CONSTRAINT arm_idau_fk FOREIGN KEY(id_aula) REFERENCES aulas(id) ON DELETE CASCADE," +
                    "CONSTRAINT arm_nom_uk UNIQUE (id, nombre, descripcion))";
            st.executeUpdate(sql);
            // productos
            sql = "CREATE TABLE IF NOT EXISTS productos (" +
                "id INTEGER," +
                "id_armario INTEGER NULL," +
                "nombre VARCHAR(20) NOT NULL," +
                "categoria VARCHAR(8) NOT NULL," +
                "descripcion VARCHAR(100) NULL," +
                "CONSTRAINT prod_id_pk PRIMARY KEY(id)," +
                "CONSTRAINT prod_idArm_fk FOREIGN KEY(id_armario) REFERENCES armarios(id) ON DELETE CASCADE)";
            st.executeUpdate(sql);
            // referencias
            sql = "CREATE TABLE IF NOT EXISTS referencias (" +
                "num_ref VARCHAR(12)," +
                "id_producto INTEGER NOT NULL," +
                "baja BOOLEAN," +
                "CONSTRAINT ref_idprod_numref_pk PRIMARY KEY(num_ref)," +
                "CONSTRAINT ref_idprod_fk FOREIGN KEY(id_producto) REFERENCES productos(id) ON DELETE CASCADE)";
            st.executeUpdate(sql);
            // registros
            sql = "CREATE TABLE IF NOT EXISTS registros (" +
                "num_ref VARCHAR(12)," +
                "id_producto INTEGER," +
                "nombre VARCHAR(20)," +
                "fecha_alta DATE," +
                "fecha_baja DATE," +
                "causa_baja VARCHAR(100)," +
                "CONSTRAINT reg_idprod_numref_pk PRIMARY KEY(num_ref)," +
                "CONSTRAINT reg_idprod_numref_fk FOREIGN KEY(num_ref) REFERENCES referencias(num_ref) ON DELETE CASCADE);";
            st.executeUpdate(sql);
            
//            // armario "sin id" -> productos sin armario.
//            sql = "INSERT IGNORE INTO aulas VALUES (1, 'Sin Aula', 'Productos que no tienen aula ni armario')";
//            st.executeUpdate(sql);
//            sql = "INSERT IGNORE INTO armarios VALUES (1, 1, 'Sin armario', 'Producto sin armario')";
//            st.executeUpdate(sql);
            
        } catch (SQLException e) {
            throw new Exception("Error al crear las tablas", e);
        }
    }
    
    public void abrirConexion() throws Exception {
        try {
            String url = "jdbc:mysql://localhost:3306/t12p05?useSSL=false";
            conn = DriverManager.getConnection(url, "root", "alumno");
            st = conn.createStatement();
            crearTablas();
        } catch (SQLException e) {
            throw new Exception("Error abrirConexion()!!", e);
        }
    }

    public void cerrarConexion() throws Exception {
        try {
            if (st != null) { st.close(); }
            if (conn != null) { conn.close(); }
        } catch (SQLException e) {
            throw new Exception("Error cerrarConexion()!!", e);
        }
    }
}
