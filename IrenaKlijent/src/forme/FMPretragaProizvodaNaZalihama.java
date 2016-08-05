/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Proizvod;
import domen.Racun;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import kontrolor.Kontrolor;
import tabela.TblModelPretragaProizvoda;
import tabela.TblModelRacun;

/**
 *
 * @author Nadin kompjuter
 */
public class FMPretragaProizvodaNaZalihama extends javax.swing.JDialog {

    /**
     * Creates new form FMPretragaProizvodaNaZalihama
     */
    public FMPretragaProizvodaNaZalihama(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        srediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnPretrazi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtxtRec = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pretraga proizvoda");

        jbtnPretrazi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnPretrazi.setText("Pronadji proizvode");
        jbtnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPretraziActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Kljucna rec pretrage:");

        jtxtRec.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Detalji");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtRec, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnPretrazi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPretrazi))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton1)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPretraziActionPerformed

        jButton1.setEnabled(false);
        String rec = jtxtRec.getText().trim();
        Proizvod p = new Proizvod();
p.setNaziv(rec);
         List<Proizvod> lp = new LinkedList<>();
        try {
            lp = Kontrolor.getInstance().vratiProizvodePoZadatomKriterijumu(p);

        } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Sistem ne može da nađe proizvode po zadatoj vrednosti", "Pretraga proizvoda", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        TblModelPretragaProizvoda tmr = new TblModelPretragaProizvoda(lp);
        jTable1.setModel(tmr);
        
        
    }//GEN-LAST:event_jbtnPretraziActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    int red = jTable1.getSelectedRow();
        TblModelPretragaProizvoda tmp =   (TblModelPretragaProizvoda) jTable1.getModel();
      Proizvod p = tmp.vratiProizvod(red);
      FMProizvod fmp = new FMProizvod(this, true,p);
      fmp.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
jButton1.setEnabled(true);
    }//GEN-LAST:event_jTable1MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnPretrazi;
    private javax.swing.JTextField jtxtRec;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        setLocationRelativeTo(null);
        jButton1.setEnabled(false);
        List<Proizvod> proizvodi = new LinkedList<>();
        TblModelPretragaProizvoda tmp = new TblModelPretragaProizvoda(proizvodi);
        jTable1.setModel(tmp);
        
    }
}