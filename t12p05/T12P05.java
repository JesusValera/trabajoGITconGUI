package t12p05;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import modelo.Armario;
import modelo.Aula;
import modelo.ConexionBD;
import modelo.Producto;
import modelo.Producto.CATEGORIA;
import modelo.Referencia;
import modelo.Registro;

public class T12P05 {

    /**
     * 
     * NO FUNCIONA POR CONSOLA PORQUE ESTA MODIFICADO
     * EL METODO DE ALTA-XXX PARA QUE SE ADAPTE AL GUI.
     * 
     * @param args 
     */
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ConexionBD conn = new ConexionBD();
        int op;

        //------------ CARGAR DATOS -------------//
        System.out.println("CARGANDO DATOS ...");
        
        try {
            conn.abrirConexion();
            System.out.println("DATOS CARGADOS CORRECTAMENTE.");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("¡ERROR!\n" + e.getMessage());
        }
        //---------- FIN CARGAR DATOS -------------//
        do {
            MenuOp();
            op = sc.nextInt();
            System.out.println("");
            switch (op) {
                case 1: // 1.- Alta de Aula.
                {
                    try {

                        String nombreAula;
                        String descripcionAula;

                        do {
                            System.out.print("Nombre Aula: ");
                            nombreAula = sc.next();
                        } while (nombreAula.isEmpty());
                        
                        do {
                            System.out.print("Descripcion Aula: ");
                            descripcionAula = sc.next();
                        } while (descripcionAula.isEmpty());

                        Aula aula = new Aula(nombreAula, descripcionAula);
                        //////////////////////////////int aulaID = aula.altaAula(conn);

                        //////////////////////////////System.out.println("Aula correcta. ID: " + aulaID);

                    } catch (Exception e) {
                        System.out.println("¡¡Error!!" + e.getMessage());
                    }

                    break;
                }
                case 2: // 2.- Baja de Aula.
                {
                    System.out.print("Escribe ID Aula a borrar: ");
                    int idAula2 = sc.nextInt();

                    try {

                        Aula.bajaAula(idAula2, conn);
                        System.out.println("Aula borrada correctamente. ID: " + idAula2);

                    } catch (Exception e) {
                        System.out.println("¡¡Error!!" + e.getMessage());
                    }

                    break;
                }
                case 3: // 3.- Alta de Armario.
                {
                    try {

                        if (Aula.isEmpty(conn)) {
                            System.out.println("¡No se encuentra ninguna aula creada!");
                            break;
                        }
                        int idAula = 0;
                        String nombreArmario;
                        String descripcionArmario;
                        // Evitar que cree un Armario con ID = 0
                        do {
                            System.out.print("ID Aula: ");
                            idAula = sc.nextInt();
                        } while (idAula == 0);
                        do {
                            System.out.print("Nombre Armario: ");
                            nombreArmario = sc.next();
                        } while (nombreArmario.isEmpty());
                        do {
                            System.out.print("Descripcion Armario: ");
                            descripcionArmario = sc.next();
                        } while (descripcionArmario.isEmpty());

                        Armario armario = new Armario(idAula, nombreArmario, descripcionArmario);

                        //////////////////////////////int armarioID = armario.altaArmario(conn);
                        //////////////////////////////System.out.println("Armario correcto. IDAula: " + armario.getIdAula() + ". ID: " + armarioID);

                    } catch (Exception e) {
                        System.out.println("Error\n" + e.getMessage());
                    }

                    break;
                }
                case 4: // 4.- Baja de Armario.
                {
                    System.out.print("Escribe ID Armario a borrar: ");
                    int idArmario4 = sc.nextInt();
                    try {

                        Armario.bajaArmario(idArmario4, conn);
                        System.out.println("Armario borrado correctamente. ID Armario: " + idArmario4);

                    } catch (Exception e) {
                        System.out.println("Error\n" + e.getMessage());
                    }

                    break;
                }
                case 5: // 5.- Alta de Producto.
                {
                    System.out.print("Escribe Nombre: ");
                    String nombre5 = "";
                    while (nombre5.isEmpty()) {
                        nombre5 = sc.next();
                    }
                    System.out.print("Escribe ID Armario (0 sin definir): ");
                    int IDArmario5 = sc.nextInt();
                    if (IDArmario5 != 0) {
                        try {
                            if (!Armario.existeArmario(IDArmario5, conn)) {
                                System.out.println("No existe un armario con el ID introducido!!\n"
                                        + "Pulse 7 para ver la lista actual existente.");
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("¡Error!\n" + e.getMessage());
                        }
                    }

                    System.out.print("Selecciona categoria ([S]oftware, [H]ardware, [O]tro): ");
                    char cat5 = sc.next().charAt(0);
                    CATEGORIA categoria5;
                    if (cat5 == 'S' | cat5 == 's') {
                        categoria5 = Producto.CATEGORIA.Software;
                    } else if (cat5 == 'H' | cat5 == 'h') {
                        categoria5 = Producto.CATEGORIA.Hardware;
                    } else {
                        categoria5 = Producto.CATEGORIA.Otra;
                    }

                    System.out.print("Descripcion producto: ");
                    String descripcion5 = sc.next();

                    Producto producto5 = new Producto(IDArmario5, nombre5, categoria5, descripcion5);
                    int idProducto5;
                    try {
                        idProducto5 = producto5.altaProducto(conn);
                    } catch (Exception e) {
                        System.out.println("¡Producto ya existe!");
                        break;
                    }
                    boolean borrarProducto = true;

                    //Referencia
                    Referencia referencia;
                    String otro;
                    do {
                        System.out.print("Numero referencia: ");
                        String numeroRef = sc.next();
                        referencia = new Referencia(numeroRef, idProducto5, false);
                        try {
                            referencia.altaReferencia(conn);
                            System.out.println("Referencia correcta.");
                            borrarProducto = false;
                            
                            // Registro
                            Registro registro5 = new Registro();
                            registro5.setIdProducto(idProducto5);
                            registro5.setNumRef(numeroRef);
                            registro5.setNombre(nombre5);
                            registro5.altaRegistro(conn);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.print("\nAnadir nueva referencia (s/n)? ");
                        otro = sc.next();
                    } while (otro.equalsIgnoreCase("s"));

                    if (borrarProducto) {
                        try {
                            Producto.bajaProducto(idProducto5, conn);
                        } catch (Exception e) {
                            System.out.println("Debe de crearse minimo una referencia. Producto no creado.");
                        }
                    } else {
                        System.out.println("Producto creado con ID: " + idProducto5 + " y REFERENCIAS:");
                        try {
                            List<String> referencias = Referencia.getReferenciasCreadas(idProducto5, conn);

                            for (String ref : referencias) {
                                System.out.println("\t\t\t\t\t " + ref);
                            }
                        } catch (Exception e) {
                            System.out.println("Error!\n" + e.getMessage());
                        }
                    }

                    break;
                }
                case 6://6.- Baja de Producto.
                {
                    System.out.print("Escribe Numero de Referencia del producto: ");
                    String numRef6 = sc.next();
                    // Comprobar que existe algUn producto con ID y referencia seleccionados
                    try {
                        if (!Referencia.existeReferencia(numRef6, conn)) {
                            System.out.println("No existe producto con numero de referencia seleccionado.");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error getProducto. ¿NO EXISTE?");
                    }

                    try {
                        Referencia.bajaReferencia(numRef6, conn);
                    } catch (Exception e) {
                        System.out.println("Error\n" +e.getMessage());
                    }
                    
                    System.out.print("FECHA BAJA DE PRODUCTO (dd/mm/yyyy): ");
                    String fecBa6 = sc.next();
                    System.out.print("CAUSA DE LA BAJA: ");
                    String causaBaja6 = sc.next();

                    // Dar de baja la referencia y el registro
                    try {
                        Referencia.setBajaReferencia(numRef6, conn);
                        Registro.bajaRegistro(numRef6, fecBa6, causaBaja6, conn);
                        System.out.println("REGISTRO ACTUALIZADO. NUMREF: " + numRef6);
                    } catch (Exception e) {
                        System.out.println("¡ERROR!\n" + e.getMessage());
                    }
                    break;
                }
                case 7: // 7.- Listado de Aulas y Armarios.
                {
                    try {
                        System.out.println("LISTADO AULAS Y ARMARIOS");
                        
                        Map<Integer, Aula> tAulas = Aula.listarAulas(conn);
                        for (Aula t : tAulas.values()) {
                            Collections.sort(t.gettArmarios()); // Ordenar Armarios
                            System.out.println("ID: " + t.getId() + ". NOMBRE: " + t.getNombre());
                            for (Armario ta : t.gettArmarios()) {
                                System.out.println("\tID: " + ta.getId() + ". NOMBRE: " + ta.getNombre());
                            }
                            System.out.println("");
                        }
                    } catch (Exception e) {
                        System.out.println("¡Error!\n" + e.getMessage());
                    }
                    
                    break;
                }
                case 8: // 8.- Listado de Productos de un armario.
                {

                    System.out.print("Escribe el nombre del Aula (0 mostrar todos los productos): ");
                    String nombreAula8 = sc.next();
                    System.out.println("LISTADO DE " + ((nombreAula8.equals("0")) ? "TODOS LOS PRODUCTOS" : "PRODUCTOS EN EL AULA DE " + nombreAula8));
                    try {
                        Map<Long, Producto> tProductos = Producto.listadoProductosDeArmario(conn, nombreAula8);
                        for (Producto tProd : tProductos.values()) {
                            System.out.println("ID: " + tProd.getId() + ". NOMBRE: " + tProd.getNombre()
                                    + ". CATEGORIA: " + tProd.getCategoria() + ". DESCRIPCION: " + tProd.getDescripcion());
                        }
                    } catch (Exception e) {
                        System.out.println("Error\n" + e.getMessage());
                    }
                    break;
                }
                case 9: // 9.- Registro de Productos.
                {
                    System.out.print("MOSTRAR (R)ECIENTES O (A)NTIGUOS PRIMERO? ");
                    char mostrar9 = sc.next().charAt(0);
                    LinkedList tRegistros = null;
                    try {

                        tRegistros = Registro.listarRegistro(conn);

                    } catch (Exception e) {
                        System.out.println("Error\n" + e.getMessage());
                    }

                    ListIterator<Registro> t;

                    if (mostrar9 == 'r' | mostrar9 == 'R') {

                        t = tRegistros.listIterator();

                        while (t.hasNext()) {
                            System.out.println(t.next().datosIterador());
                        }

                    } else if (mostrar9 == 'a' | mostrar9 == 'A') {

                        t = tRegistros.listIterator(tRegistros.size());

                        while (t.hasPrevious()) {
                            System.out.println(t.previous().datosIterador());
                        }

                    } else {

                        System.out.println("OPCION SELECCIONADA INVALIDA.");
                    }

                    break;
                }
                case 10: // 10.- Registro Completo de todo en Arbol
                {
                    try {
                        // Recorrer aulas -> armarios -> productos -> referencias -> registros
                        Map<Integer, Aula> tAulas = Aula.listarAulas(conn);
                        
                        for (Aula tAu : tAulas.values()) {
                            System.out.println("ID_AULA: " + tAu.getId() + ". NOMBRE: " + tAu.getNombre()
                                    + ". DESCRIPCION: " + tAu.getDescripcion());

                            for (Armario tArm : tAu.gettArmarios()) {
                                System.out.println("\tID_ARMARIO: " + tArm.getId() + ". NOMBRE: " + tArm.getNombre()
                                        + ". DESCRIPCION: " + tArm.getDescripcion());
                                
                                tArm.settProductos(Producto.listarProductos(tArm.getId(), conn));
                                for (Producto tProd : tArm.gettProductos().values()) {
                                    System.out.println("\t\tID_PRODUCTO: " + tProd.getId() + ". NOMBRE: " + tProd.getNombre()
                                            + ". CATEGORIA: " + tProd.getCategoria() + ". DESCRIPCION: " + tProd.getDescripcion());
                                    
                                    
                                    for (Referencia tRef : tProd.gettReferencias()) {
                                        System.out.println("\t\t\tNUMREF: " + tRef.getNumRef() + ". BAJA: " + tRef.isBaja());
                                        
                                        tRef.settRegistro(Referencia.listarRegistro(tRef.getNumRef(), conn));
                                        for (Registro tReg : tRef.gettRegistro()) {
                                            System.out.println("\t\t\t\tREGISTRO_NOMBRE: " + tReg.getNombre() + ". FECHA_ALTA: " + tReg.getFecAlta()
                                                    + ". FECHA_BAJA: " + tReg.getFecBaja() + ". CAUSA_BAJA: " + tReg.getCausaBaja());
                                        }
                                    }
                                }
                            }
                            System.out.println("");
                        }

                    } catch (Exception e) {
                        System.out.println("Error\n" + e.getMessage());
                    }

                }
                case 0: // 0.- Salir.
                {
                    break;
                }
                default:
                    System.out.println("OPCION NO DISPONIBLE.");
            }
            System.out.println("");
        } while (op != 0);

        System.out.println("GUARDANDO DATOS...");

        //------------ GUARDAR DATOS --------------//
        try {

            conn.cerrarConexion();
            System.out.println("");
            System.out.println("DATOS GUARDADOS CORRECTAMENTE.");

        } catch (Exception e) {
            System.out.println("¡ERROR!\n" + e.getMessage());
        }
        //---------- FIN GUARDAR DATOS --------------//
    }

    private static void MenuOp() {
        System.out.println("Seleccionar una opcion:");
        System.out.println("-----------------------");
        System.out.println("");
        System.out.println("1.- Alta de Aula.");
        System.out.println("2.- Baja de Aula.");
        System.out.println("3.- Alta de Armario.");
        System.out.println("4.- Baja de Armario.");
        System.out.println("5.- Alta de Producto.");
        System.out.println("6.- Baja de Producto.");
        System.out.println("7.- Listado de Aulas y Armarios.");
        System.out.println("8.- Listado de Productos por Armario.");
        System.out.println("9.- Registro de Productos.");
        System.out.println("10.- Registro Completo de todo en Arbol");
        System.out.println("0.- Salir.");
        System.out.println("");
        System.out.print("Opcion? ");
    }

}
