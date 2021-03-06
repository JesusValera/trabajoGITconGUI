package t12p05;

import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.Producto;
import modelo.Referencia;
import modelo.Registro;

/**
 *
 * @author jesus
 */
public class PanelReferenciaAlta extends javax.swing.JPanel {

    ConexionBD conn;
    Producto producto;
    IBusCallBack ibc;
    
    public PanelReferenciaAlta(ConexionBD conn) {
        initComponents();
        this.conn = conn;
    }
    
    public void mostrar(Producto producto, IBusCallBack ibc) {
        this.setVisible(true);
        this.ibc = ibc;
        this.producto = producto;
        txtIdProducto.setEditable(false);
        txtIdProducto.setBackground(new java.awt.Color(200,230,250));
        txtIdProducto.setText(String.valueOf(producto.getId()));
        txtNumReferencia.setText("");
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
        labNumReferencia = new javax.swing.JLabel();
        labIdProducto = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        txtNumReferencia = new javax.swing.JTextField();
        botAceptar = new javax.swing.JButton();
        botCancelar = new javax.swing.JButton();
        txtNumReferenciaBloqueado = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(620, 420));
        setMinimumSize(new java.awt.Dimension(620, 420));

        labCabecera.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labCabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labCabecera.setText("ALTA REFERENCIA");

        labNumReferencia.setText("Num. referencia:");

        labIdProducto.setText("ID producto:");

        txtIdProducto.setEditable(false);
        txtIdProducto.setBackground(new java.awt.Color(200, 230, 250));

        txtNumReferencia.setBackground(new java.awt.Color(250, 210, 220));
        txtNumReferencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumReferenciaFocusLost(evt);
            }
        });

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

        txtNumReferenciaBloqueado.setEditable(false);
        txtNumReferenciaBloqueado.setBackground(java.awt.Color.gray);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNumReferencia)
                            .addComponent(labIdProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNumReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumReferenciaBloqueado))
                            .addComponent(txtIdProducto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                    .addComponent(labIdProducto)
                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNumReferencia)
                    .addComponent(txtNumReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumReferenciaBloqueado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botAceptar)
                    .addComponent(botCancelar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAceptarActionPerformed
        if (!txtNumReferencia.getText().equals("")) {
            try {
                // ALTA REFERENCIA
                Referencia referencia = new Referencia();
                referencia.setIdProducto(producto.getId());
                referencia.setNumRef(txtNumReferencia.getText());
                referencia.altaReferencia(conn);
                
                // ALTA REGISTRO
                Registro registro = new Registro();
                registro.setId(Registro.generarId(conn));
                registro.setIdProducto(producto.getId());
                registro.setNumRef(txtNumReferencia.getText());
                registro.altaRegistro(conn);
                
                int op = JOptionPane.showConfirmDialog(this,
                    "Referencia creada correctamente.\n¿Añadir nueva referencia?", 
                    "ALTA", 
                    JOptionPane.YES_NO_OPTION);
                switch(op) {
                    case JOptionPane.YES_OPTION:
                        this.mostrar(producto, ibc);
                        break;
                    case JOptionPane.NO_OPTION:
                        this.setVisible(false);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Número de referencia repetido.\nDebe de ser único por producto.",
                    "ERROR", 
                    JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Debes introducir un número de referencia.", 
                    "ERROR", 
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botAceptarActionPerformed

    private void botCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCancelarActionPerformed
        // Comprobar si existe alguna referencia con ID Producto.
        // -- SI: setVisible(false).
        // -- NO: eliminar producto. (mostrar JOptionPane -> Es necesario crear al menos una referencia.
        try {
            List<String> referenciasExistentesConIdProd = Referencia.getReferenciasCreadas(producto.getId(), conn);
            if (referenciasExistentesConIdProd.isEmpty()) {
                Producto.bajaProducto(producto.getId(), conn);
                JOptionPane.showMessageDialog(this, 
                        "Es necesario añadir referencias a un producto.\nProducto no creado.", 
                        "CANCELAR", 
                        JOptionPane.WARNING_MESSAGE);
                this.setVisible(false);
            } else {
                int op = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que desea cancelar?", 
                    "CANCELAR", 
                    JOptionPane.YES_NO_OPTION);
                switch(op) {
                    case JOptionPane.NO_OPTION:
                        this.mostrar(producto, null);
                        break;
                    case JOptionPane.YES_OPTION:
                        // Eliminar referencias del producto creado
                        this.setVisible(false);
                        break;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            ;
        }
        if (ibc != null) { ibc.callBack(); }
    }//GEN-LAST:event_botCancelarActionPerformed

    private void txtNumReferenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumReferenciaFocusLost
        // PINTAR CUADRO TEXTO DERECHA.
        if (txtNumReferencia.getText().equals("")) {
            txtNumReferenciaBloqueado.setBackground(Color.GRAY);
            txtNumReferenciaBloqueado.setText("");
        } else {
            try {
                if (!Referencia.existeReferencia(txtNumReferencia.getText(), Long.parseLong(txtIdProducto.getText()), conn)) {
                    throw new Exception();
                }
                txtNumReferenciaBloqueado.setBackground(Color.red);
                txtNumReferenciaBloqueado.setText("REFERENCIA EN USO.");
            } catch (Exception e) {
                //e.printStackTrace();
                txtNumReferenciaBloqueado.setText("DISPONIBLE.");
                txtNumReferenciaBloqueado.setBackground(new java.awt.Color(130, 250, 130));
            }
        }
    }//GEN-LAST:event_txtNumReferenciaFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAceptar;
    private javax.swing.JButton botCancelar;
    private javax.swing.JLabel labCabecera;
    private javax.swing.JLabel labIdProducto;
    private javax.swing.JLabel labNumReferencia;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNumReferencia;
    private javax.swing.JTextField txtNumReferenciaBloqueado;
    // End of variables declaration//GEN-END:variables

}
