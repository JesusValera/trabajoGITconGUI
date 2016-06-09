package t12p05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Armario;
import modelo.ConexionBD;

/**
 *
 * @author jesus
 */
public class PanelArmarioBuscador extends javax.swing.JPanel implements IBusCallBack{

    ConexionBD conn;
    PanelArmarioAlta pArmA;
    DefaultTableModel modelo;
    
    public PanelArmarioBuscador(ConexionBD conn, PanelArmarioAlta pArmA) {
        initComponents();
        this.conn = conn;
        this.pArmA = pArmA;
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] {"ID", "ID Aula", "Nombre", "Descripcion"});
        tabArmarios.setModel(modelo);
    }
    
    void mostrar() {
        this.setVisible(true);
        modelo.setRowCount(0);
        txtId.setText("");
        txtIdAula.setText("");
        txtNombre.setText("");
    }
    
    @Override
    public void callBack() {
        botBuscar.doClick();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labCabecera = new javax.swing.JLabel();
        labId = new javax.swing.JLabel();
        labIdAula = new javax.swing.JLabel();
        labNombre = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtIdAula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        botBuscar = new javax.swing.JButton();
        botLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabArmarios = new javax.swing.JTable();
        botAceptar = new javax.swing.JButton();
        botCancelar = new javax.swing.JButton();
        botAlta = new javax.swing.JButton();
        botEditar = new javax.swing.JButton();
        botBaja = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(620, 420));
        setMinimumSize(new java.awt.Dimension(620, 420));

        labCabecera.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labCabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labCabecera.setText("BUSCADOR DE ARMARIO");

        labId.setText("ID:");

        labIdAula.setText("ID Aula:");

        labNombre.setText("Nombre:");

        botBuscar.setText("Buscar");
        botBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBuscarActionPerformed(evt);
            }
        });

        botLimpiar.setText("Limpiar");
        botLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botLimpiarActionPerformed(evt);
            }
        });

        tabArmarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabArmarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabArmarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabArmarios.getTableHeader().setResizingAllowed(false);
        tabArmarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabArmarios);

        botAceptar.setText("Aceptar");
        botAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAcepCancActionPerformed(evt);
            }
        });

        botCancelar.setText("Cancelar");
        botCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAcepCancActionPerformed(evt);
            }
        });

        botAlta.setText("Alta");
        botAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAltaActionPerformed(evt);
            }
        });

        botEditar.setText("Editar");
        botEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botEditarActionPerformed(evt);
            }
        });

        botBaja.setText("Baja");
        botBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNombre)
                            .addComponent(labIdAula)
                            .addComponent(labId))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdAula)
                                    .addComponent(txtId))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botLimpiar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(botBuscar, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(txtNombre)))
                    .addComponent(labCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botAlta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botBaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botAceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labCabecera)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIdAula)
                    .addComponent(txtIdAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botAceptar)
                    .addComponent(botCancelar)
                    .addComponent(botAlta)
                    .addComponent(botEditar)
                    .addComponent(botBaja))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botAcepCancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAcepCancActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botAcepCancActionPerformed

    private void botLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botLimpiarActionPerformed
        mostrar();
    }//GEN-LAST:event_botLimpiarActionPerformed

    private void botBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBuscarActionPerformed
        List<Armario> tArmarios = new ArrayList<>();
        try {
            Armario.buscarArmarios(conn, tArmarios, txtId.getText(), txtIdAula.getText(), txtNombre.getText());
            modelo.setRowCount(0);
            Collections.sort(tArmarios);
            for (Armario t : tArmarios) {
                modelo.addRow(new String[] {
                    String.valueOf(t.getId()), 
                    String.valueOf(t.getIdAula()), 
                    t.getNombre(), 
                    t.getDescripcion()
                });
            }
            
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showConfirmDialog(this, 
                    "Error cargar datos." +e.getMessage(), 
                    "ERROR", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botBuscarActionPerformed

    private void botAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAltaActionPerformed
        pArmA.mostrar(this);
    }//GEN-LAST:event_botAltaActionPerformed

    private void botEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botEditarActionPerformed
        // Obtener armario a partir de sus PK y editar desde PanelArmarioAlta.
        if (tabArmarios.getSelectedRow() >= 0) {
        String id = (String) modelo.getValueAt(tabArmarios.getSelectedRow(), 0);
            Armario armario = new Armario();
            armario.setId(Integer.parseInt(id));
            try {
                armario.recuperarArmario(conn);
                pArmA.mostrarEdicion(this, armario);
            } catch (Exception e) {
                //e.printStackTrace();
                ;
            }
        }
    }//GEN-LAST:event_botEditarActionPerformed

    private void botBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBajaActionPerformed
        // Obtener armario a partir de sus PK y eliminar.
        if (tabArmarios.getSelectedRow() >= 0) {
            String id = (String) modelo.getValueAt(tabArmarios.getSelectedRow(), 0);
            int op = JOptionPane.showConfirmDialog(this, 
                    "Seguro que desea eliminar el armario con ID " +id, 
                    "ELIMINAR", 
                    JOptionPane.WARNING_MESSAGE);
            switch (op) {
                case JOptionPane.YES_OPTION:
                try {
                    Armario.bajaArmario(Integer.parseInt(id), conn);
                    JOptionPane.showConfirmDialog(this, 
                            "Aula eliminada.", 
                            "ELIMINAR", 
                            JOptionPane.OK_OPTION);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        botBuscar.doClick();
    }//GEN-LAST:event_botBajaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAceptar;
    private javax.swing.JButton botAlta;
    private javax.swing.JButton botBaja;
    private javax.swing.JButton botBuscar;
    private javax.swing.JButton botCancelar;
    private javax.swing.JButton botEditar;
    private javax.swing.JButton botLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labCabecera;
    private javax.swing.JLabel labId;
    private javax.swing.JLabel labIdAula;
    private javax.swing.JLabel labNombre;
    private javax.swing.JTable tabArmarios;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdAula;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
