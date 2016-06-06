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
        
        pArmA = new PanelArmarioAlta(conn);
        add(pArmA);
        pArmB = new PanelArmarioBuscador(conn, pArmA);
        add(pArmB);
        pAulA = new PanelAulaAlta(conn);
        add(pAulA);
        pAulB = new PanelAulaBuscador(conn, pAulA);
        add(pAulB);
        pProA = new PanelProductoAlta(conn);
        add(pProA);
        pProB = new PanelProductoBuscador(conn, pProA);
        add(pProB);
        
        ocultarPaneles();
    }
    
    private void ocultarPaneles() {
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

        jMenuItem6 = new javax.swing.JMenuItem();
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
        jMenu5 = new javax.swing.JMenu();

        jMenuItem6.setText("jMenuItem6");

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

        jMenu5.setText("jMenu5");
        menu.add(jMenu5);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuArmarios;
    private javax.swing.JMenuItem menuArmariosAlta;
    private javax.swing.JMenuItem menuArmariosBuscador;
    private javax.swing.JMenu menuAulas;
    private javax.swing.JMenuItem menuAulasAlta;
    private javax.swing.JMenuItem menuAulasBuscador;
    private javax.swing.JMenu menuPrincipal;
    private javax.swing.JMenuItem menuPrincipalSalir;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenuItem menuProductosAlta;
    private javax.swing.JMenuItem menuProductosBuscador;
    // End of variables declaration//GEN-END:variables
}
