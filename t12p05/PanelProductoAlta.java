package t12p05;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.Armario;
import modelo.ConexionBD;
import modelo.Producto;

/**
 *
 * @author jesus
 */
public class PanelProductoAlta extends javax.swing.JPanel {

    ConexionBD conn;
    IBusCallBack ibc;
    DefaultComboBoxModel modelocombo;
    boolean edicion;
    
    public PanelProductoAlta(ConexionBD conn) {
        initComponents();
        this.conn = conn;
        modelocombo = new DefaultComboBoxModel(new String[] { "", 
            Producto.CATEGORIA.Hardware.toString(), 
            Producto.CATEGORIA.Software.toString(), 
            Producto.CATEGORIA.Otra.toString() });
        cboCategoria.setModel(modelocombo);
    }

    void mostrar(IBusCallBack ibc) {
        this.setVisible(true);
        edicion = false;
        this.ibc = ibc;
        // Limpiar/inicializar campos
        txtIdArmario.setText("");
        txtIdArmarioBloqueado.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        cboCategoria.setSelectedIndex(0);
        try {
            txtId.setText(String.valueOf(Producto.generarId(conn)));
        } catch(Exception e) {
            e.printStackTrace();
            //JOptionPane.
        }
    }
    
    void mostrarEdicion(IBusCallBack ibc, Producto producto) {
        this.setVisible(true);
        edicion = true;
        this.ibc = ibc;
        txtIdArmarioBloqueado.setBackground(new java.awt.Color(130, 250, 130));
        botReferencia.setText("Actualizar");
        // --
        try {
            txtId.setText(String.valueOf(producto.getId()));
            txtIdArmario.setText((producto.getIdArmario()==null)?"":String.valueOf(producto.getIdArmario()));
            txtNombre.setText(producto.getNombre());
            txtDescripcion.setText(producto.getDescripcion());
            cboCategoria.setSelectedItem((producto.getCategoria()==null)?0:producto.getCategoria().toString());
            if (txtIdArmario.getText().equals("")) {
                txtIdArmarioBloqueado.setText("");
                txtIdArmarioBloqueado.setBackground(Color.gray);
            } else {
                try {
                    Armario armario = new Armario();
                    armario.setId(Integer.parseInt(txtIdArmario.getText()));
                    armario.recuperarArmario(conn);
                    txtIdArmarioBloqueado.setText(armario.getNombre());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        labIdArmario = new javax.swing.JLabel();
        labNombre = new javax.swing.JLabel();
        labDescripcion = new javax.swing.JLabel();
        labCategoria = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtIdArmario = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        cboCategoria = new javax.swing.JComboBox<>();
        txtIdArmarioBloqueado = new javax.swing.JTextField();
        botReferencia = new javax.swing.JButton();
        botCancelar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(620, 420));
        setMinimumSize(new java.awt.Dimension(620, 420));

        labCabecera.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labCabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labCabecera.setText("ALTA DE PRODUCTO");

        labId.setText("ID:");

        labIdArmario.setText("Id Armario:");

        labNombre.setText("Nombre:");

        labDescripcion.setText("Descripcion:");

        labCategoria.setText("Categoria:");

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(200, 230, 250));

        txtIdArmario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdArmarioFocusLost(evt);
            }
        });

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtIdArmarioBloqueado.setEditable(false);
        txtIdArmarioBloqueado.setBackground(java.awt.Color.gray);

        botReferencia.setText("Crear referencias");
        botReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botReferenciaActionPerformed(evt);
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
                    .addComponent(labCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNombre)
                            .addComponent(labIdArmario)
                            .addComponent(labId))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labDescripcion)
                            .addComponent(labCategoria))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 112, Short.MAX_VALUE))
                                    .addComponent(txtIdArmario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdArmarioBloqueado, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botReferencia)))
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
                    .addComponent(labIdArmario)
                    .addComponent(txtIdArmario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdArmarioBloqueado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCategoria)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botReferencia)
                    .addComponent(botCancelar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botCancelarActionPerformed

    private void txtIdArmarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdArmarioFocusLost
        // PINTAR CUADRO TEXTO DERECHA.
        if (txtIdArmario.getText().equals("")) {
            txtIdArmarioBloqueado.setBackground(Color.gray);
            txtIdArmarioBloqueado.setText("");
        } else {
            try {
                Armario armario = new Armario();
                armario.setId(Integer.parseInt(txtIdArmario.getText()));
                armario.recuperarArmario(conn);
                txtIdArmarioBloqueado.setText(armario.getNombre());
                txtIdArmarioBloqueado.setBackground(new java.awt.Color(130, 250, 130));
            } catch (Exception e) {
                //e.printStackTrace();
                txtIdArmarioBloqueado.setBackground(Color.red);
                txtIdArmarioBloqueado.setText("NOT FOUND.");
            }
        }
    }//GEN-LAST:event_txtIdArmarioFocusLost

    private void botReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botReferenciaActionPerformed
        // ACEPTAR
        Producto producto = new Producto();
        producto.setId(Integer.parseInt(txtId.getText()));
        if (!txtIdArmario.getText().equals("")) {
            producto.setIdArmario(Integer.parseInt(txtIdArmario.getText()));
        }
        producto.setNombre(txtNombre.getText());
        producto.setDescripcion(txtDescripcion.getText());
        producto.setCategoria(((cboCategoria.getSelectedItem().toString().equals("")?null:Producto.CATEGORIA.valueOf(cboCategoria.getSelectedItem().toString()))));
        
        if (!edicion) {
            try {
                producto.altaProducto(conn);
                JOptionPane.showMessageDialog(this, 
                        "Alta correcta.",
                        "ALTA", 
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                        "Error: " +e.getMessage(), 
                        "ERROR AL DAR DE ALTA", 
                        JOptionPane.ERROR_MESSAGE);
                //
                //
                // REFERENCIAS Y REGISTROS AQUI.
                //
                //
            }
        } else {
            try {
                producto.updateProducto(conn);
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
    }//GEN-LAST:event_botReferenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botCancelar;
    private javax.swing.JButton botReferencia;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JLabel labCabecera;
    private javax.swing.JLabel labCategoria;
    private javax.swing.JLabel labDescripcion;
    private javax.swing.JLabel labId;
    private javax.swing.JLabel labIdArmario;
    private javax.swing.JLabel labNombre;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdArmario;
    private javax.swing.JTextField txtIdArmarioBloqueado;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
