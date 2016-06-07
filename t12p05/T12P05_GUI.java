package t12p05;

import javax.swing.JOptionPane;
import modelo.ConexionBD;

public class T12P05_GUI extends javax.swing.JFrame {

    ConexionBD conn;
    PanelArmarioAlta pArmA;
    PanelArmarioBuscador pArmB;
    PanelAulaAlta pAulA;
    PanelAulaBuscador pAulB;
    PanelProductoAlta pProA;
    PanelProductoBuscador pProB;
    PanelReferenciaAlta pRefA;
    PanelReferenciaBuscador pRefB;
    
    public T12P05_GUI() {
        initComponents();
        conn = new ConexionBD();
        myInit();
    }

    private void myInit() {
        this.setLocationRelativeTo(null);
        //------------ CARGAR DATOS -------------//
        try {
            conn.abrirConexion();
            System.out.println("Datos cargados correctamente.");
        } catch (Exception e) {
            System.out.println("¡ERROR!\n" + e.getMessage());
        }
        //---------- FIN CARGAR DATOS -------------//
        
        pRefA = new PanelReferenciaAlta(conn);
        add(pRefA);
        pRefB = new PanelReferenciaBuscador(conn, pRefA);
        add(pRefB);
        pArmA = new PanelArmarioAlta(conn);
        add(pArmA);
        pArmB = new PanelArmarioBuscador(conn, pArmA);
        add(pArmB);
        pAulA = new PanelAulaAlta(conn);
        add(pAulA);
        pAulB = new PanelAulaBuscador(conn, pAulA);
        add(pAulB);
        pProA = new PanelProductoAlta(conn, pRefA);
        add(pProA);
        pProB = new PanelProductoBuscador(conn, pProA);
        add(pProB);
        
        ocultarPaneles();
    }
    
    private void ocultarPaneles() {
        pRefA.setVisible(false);
        pRefB.setVisible(false);
        pAulA.setVisible(false);
        pAulB.setVisible(false);
        pArmA.setVisible(false);
        pArmB.setVisible(false);
        pProA.setVisible(false);
        pProB.setVisible(false);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(T12P05_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(T12P05_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(T12P05_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(T12P05_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new T12P05_GUI().setVisible(true);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JMenuBar();
        menuPrincipal = new javax.swing.JMenu();
        menuPrincipalSalir = new javax.swing.JMenuItem();
        menuAulas = new javax.swing.JMenu();
        menuAulasBuscador = new javax.swing.JMenuItem();
        menuAulasAlta = new javax.swing.JMenuItem();
        menuArmarios = new javax.swing.JMenu();
        menuArmariosBuscador = new javax.swing.JMenuItem();
        menuArmariosAlta = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenu();
        menuProductosBuscador = new javax.swing.JMenuItem();
        menuProductosAlta = new javax.swing.JMenuItem();
        menuListado = new javax.swing.JMenu();
        menuListadoReferencias = new javax.swing.JMenuItem();
        menuListadoRegistros = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        menuAyudaInstrucciones = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(680, 440));
        setMinimumSize(new java.awt.Dimension(680, 440));
        setPreferredSize(new java.awt.Dimension(680, 440));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());

        menuPrincipal.setText("Principal");

        menuPrincipalSalir.setText("Salir");
        menuPrincipalSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPrincipalSalirActionPerformed(evt);
            }
        });
        menuPrincipal.add(menuPrincipalSalir);

        menu.add(menuPrincipal);

        menuAulas.setText("Aulas");

        menuAulasBuscador.setText("Buscador");
        menuAulasBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAulasActionPerformed(evt);
            }
        });
        menuAulas.add(menuAulasBuscador);

        menuAulasAlta.setText("Alta");
        menuAulasAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAulasActionPerformed(evt);
            }
        });
        menuAulas.add(menuAulasAlta);

        menu.add(menuAulas);

        menuArmarios.setText("Armarios");

        menuArmariosBuscador.setText("Buscador");
        menuArmariosBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArmariosActionPerformed(evt);
            }
        });
        menuArmarios.add(menuArmariosBuscador);

        menuArmariosAlta.setText("Alta");
        menuArmariosAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArmariosActionPerformed(evt);
            }
        });
        menuArmarios.add(menuArmariosAlta);

        menu.add(menuArmarios);

        menuProductos.setText("Productos");

        menuProductosBuscador.setText("Buscador");
        menuProductosBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductosActionPerformed(evt);
            }
        });
        menuProductos.add(menuProductosBuscador);

        menuProductosAlta.setText("Alta");
        menuProductosAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductosActionPerformed(evt);
            }
        });
        menuProductos.add(menuProductosAlta);

        menu.add(menuProductos);

        menuListado.setText("Listado");

        menuListadoReferencias.setText("Referencias");
        menuListadoReferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListadoActionPerformed(evt);
            }
        });
        menuListado.add(menuListadoReferencias);

        menuListadoRegistros.setText("Registros");
        menuListadoRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListadoActionPerformed(evt);
            }
        });
        menuListado.add(menuListadoRegistros);

        menu.add(menuListado);

        menuAyuda.setText("Ayuda");

        menuAyudaInstrucciones.setText("Instrucciones");
        menuAyuda.add(menuAyudaInstrucciones);

        menu.add(menuAyuda);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuPrincipalSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPrincipalSalirActionPerformed
        int op = 0;
        // COMENTO ESTO PARA QUE NO ME DE EL FOLLON; DEBERIA ESTAR DESCOMENTADO EN LA VERSION FINAL. :)
//        int op = JOptionPane.showConfirmDialog(this, 
//                "¿Seguro que quiere salir?", 
//                "SALIR", 
//                JOptionPane.WARNING_MESSAGE);
        switch (op) {
            case JOptionPane.YES_OPTION:
                //------------ GUARDAR DATOS --------------//
                try {
                    conn.cerrarConexion();
                    System.out.println("Datos guardados correctamente.");
                    this.setVisible(false);
                    this.dispose();
                } catch (Exception e) {
                    System.out.println("¡ERROR!\n" + e.getMessage());
                }
                //---------- FIN GUARDAR DATOS --------------//
        }
        
    }//GEN-LAST:event_menuPrincipalSalirActionPerformed

    private void menuAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAulasActionPerformed
        ocultarPaneles();
        if (evt.getActionCommand().equals("Alta")) {
            pAulA.mostrar(null);
        } else if (evt.getActionCommand().equals("Buscador")) {
            pAulB.mostrar();
        }
    }//GEN-LAST:event_menuAulasActionPerformed

    private void menuArmariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArmariosActionPerformed
        ocultarPaneles();
        if (evt.getActionCommand().equals("Alta")) {
            pArmA.mostrar(null);
        } else if (evt.getActionCommand().equals("Buscador")) {
            pArmB.mostrar();
        }
    }//GEN-LAST:event_menuArmariosActionPerformed

    private void menuProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductosActionPerformed
        ocultarPaneles();
        if (evt.getActionCommand().equals("Alta")) {
            pProA.mostrar(null);
        } else if (evt.getActionCommand().equals("Buscador")) {
            pProB.mostrar();
        }
    }//GEN-LAST:event_menuProductosActionPerformed

    private void menuListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListadoActionPerformed
        ocultarPaneles();
        if (evt.getActionCommand().equals("Referencias")) {
            pRefB.mostrar();
        } else if (evt.getActionCommand().equals("Registros")) {
            
            
            //
            
            //
        }
    }//GEN-LAST:event_menuListadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuArmarios;
    private javax.swing.JMenuItem menuArmariosAlta;
    private javax.swing.JMenuItem menuArmariosBuscador;
    private javax.swing.JMenu menuAulas;
    private javax.swing.JMenuItem menuAulasAlta;
    private javax.swing.JMenuItem menuAulasBuscador;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuAyudaInstrucciones;
    private javax.swing.JMenu menuListado;
    private javax.swing.JMenuItem menuListadoReferencias;
    private javax.swing.JMenuItem menuListadoRegistros;
    private javax.swing.JMenu menuPrincipal;
    private javax.swing.JMenuItem menuPrincipalSalir;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenuItem menuProductosAlta;
    private javax.swing.JMenuItem menuProductosBuscador;
    // End of variables declaration//GEN-END:variables
}
