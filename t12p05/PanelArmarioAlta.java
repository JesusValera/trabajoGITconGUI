package t12p05;

import java.awt.Color;
import javax.swing.JOptionPane;
import modelo.Armario;
import modelo.Aula;
import modelo.ConexionBD;

/**
 *
 * @author jesus
 */
public class PanelArmarioAlta extends javax.swing.JPanel {

    ConexionBD conn;
    IBusCallBack ibc;
    boolean edicion;
    
    public PanelArmarioAlta(ConexionBD conn) {
        initComponents();
        this.conn = conn;
    }
    
    void mostrar(IBusCallBack ibc) {
        this.setVisible(true);
        edicion = false;
        this.ibc = ibc;
        txtIdAula.setEditable(true);
        // Limpiar/inicializar campos
        txtIdAula.setText("");
        txtIdAulaBloqueada.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        try {
            txtId.setText(String.valueOf(Armario.generarId(conn)));
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener ID.");
        }
    }
    
    void mostrarEdicion(IBusCallBack ibc, Armario armario) {
        this.setVisible(true);
        edicion = true;
        this.ibc = ibc;
        txtIdAula.setEditable(false);
        txtIdAula.setBackground(new java.awt.Color(200, 230, 250));
        txtIdAulaBloqueada.setBackground(new java.awt.Color(130, 250, 130));
        try {
            txtId.setText(String.valueOf(armario.getId()));
            txtIdAula.setText(String.valueOf(armario.getIdAula()));
            txtNombre.setText(armario.getNombre());
            txtDescripcion.setText(armario.getDescripcion());
            
            // MOSTRAR NOMBRE AULA AL QUE HACE REFERENCIA IDAula EN CAMPO BLOQUEADO.
            Aula aula = new Aula();
            aula.setId(Integer.parseInt(txtIdAula.getText()));
            aula.recuperarAula(conn);
            txtIdAulaBloqueada.setText(aula.getNombre());
        } catch (Exception e) {
            ;
        }
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
        labDescripcion = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtIdAula = new javax.swing.JTextField();
        txtIdAulaBloqueada = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        botAceptar = new javax.swing.JButton();
        botCancelar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(620, 420));
        setMinimumSize(new java.awt.Dimension(620, 420));

        labCabecera.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labCabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labCabecera.setText("ALTA DE ARMARIO");

        labId.setText("ID:");

        labIdAula.setText("ID Aula:");

        labNombre.setText("Nombre:");

        labDescripcion.setText("Descripcion:");

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(200, 230, 250));

        txtIdAula.setBackground(new java.awt.Color(250, 210, 220));
        txtIdAula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdAulaFocusLost(evt);
            }
        });

        txtIdAulaBloqueada.setEditable(false);
        txtIdAulaBloqueada.setBackground(java.awt.Color.gray);

        botAceptar.setText("Aceptar");
        botAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAceptarActionPerformed(evt);
            }
        });

        botCancelar.setText("Cancelar");
        botCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botAceptar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labId)
                            .addComponent(labIdAula)
                            .addComponent(labNombre)
                            .addComponent(labDescripcion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtIdAula, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdAulaBloqueada))
                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombre)
                            .addComponent(txtDescripcion))))
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
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIdAula)
                    .addComponent(txtIdAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdAulaBloqueada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botAceptar)
                    .addComponent(botCancelar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botCancelarActionPerformed

    private void txtIdAulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdAulaFocusLost
        // PINTAR CUADRO BLOQUEADO SEGUN IDAula DE DERECHA.
        if (txtIdAula.getText().equals("")) {
            txtIdAulaBloqueada.setBackground(Color.gray);
            txtIdAulaBloqueada.setText("");
        } else {
            try {
                Aula aula = new Aula();
                aula.setId(Integer.parseInt(txtIdAula.getText()));
                aula.recuperarAula(conn);
                txtIdAulaBloqueada.setText(aula.getNombre());
                txtIdAulaBloqueada.setBackground(new java.awt.Color(130, 250, 130));
            } catch (Exception e) {
                //e.printStackTrace();
                txtIdAulaBloqueada.setBackground(Color.red);
                txtIdAulaBloqueada.setText("NOT FOUND.");
            }
        }
    }//GEN-LAST:event_txtIdAulaFocusLost

    private void botAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAceptarActionPerformed
        // CREAR Y DAR DE ALTA A ARMARIO.
        if (!txtIdAula.getText().equals("")) {
            Armario armario = new Armario();
            armario.setId(Integer.parseInt(txtId.getText()));
            armario.setIdAula(Integer.parseInt(txtIdAula.getText()));
            armario.setNombre(txtNombre.getText());
            armario.setDescripcion(txtDescripcion.getText());
            if (!edicion) {
                try {
                    armario.altaArmario(conn);
                    JOptionPane.showMessageDialog(this, 
                            "Alta correcta.",
                            "ALTA", 
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, 
                            "Error: " +e.getMessage(), 
                            "ERROR AL DAR DE ALTA", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    armario.updateArmario(conn);
                    JOptionPane.showMessageDialog(this, 
                            "Actualizacion correcta.",
                            "ACTUALIZAR", 
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, 
                            "Error: " +e.getMessage(), 
                            "ERROR AL ACTUALIZAR", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            if (ibc != null) { ibc.callBack(); }
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, 
                            "ID Aula no puede estar vacío.", 
                            "ERROR", 
                            JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_botAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAceptar;
    private javax.swing.JButton botCancelar;
    private javax.swing.JLabel labCabecera;
    private javax.swing.JLabel labDescripcion;
    private javax.swing.JLabel labId;
    private javax.swing.JLabel labIdAula;
    private javax.swing.JLabel labNombre;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdAula;
    private javax.swing.JTextField txtIdAulaBloqueada;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
