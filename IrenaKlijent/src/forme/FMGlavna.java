/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Radnik;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kontrolor.Kontrolor;

/**
 *
 * @author Nadin kompjuter
 */
public class FMGlavna extends javax.swing.JFrame {

    /**
     * Creates new form FMGlavna
     */
    public FMGlavna() {
        initComponents();
        rasiriFormu();
        postaviUlogovanogRadnika();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jmKlijent = new javax.swing.JMenu();
        jmiRadSaKlijentima = new javax.swing.JMenuItem();
        jmRacun = new javax.swing.JMenu();
        jmiKreirajRacun = new javax.swing.JMenuItem();
        jmiPretraziRacune = new javax.swing.JMenuItem();
        jmProizvodi = new javax.swing.JMenu();
        jmiPretragaProizvoda = new javax.swing.JMenuItem();
        jmRadnik = new javax.swing.JMenu();
        jmiLogOut = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dobrodosli");
        setAlwaysOnTop(true);

        jmKlijent.setText("Klijent");

        jmiRadSaKlijentima.setText("Rad sa klijentima");
        jmiRadSaKlijentima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRadSaKlijentimaActionPerformed(evt);
            }
        });
        jmKlijent.add(jmiRadSaKlijentima);

        jMenuBar1.add(jmKlijent);

        jmRacun.setText("Racun");

        jmiKreirajRacun.setText("KreirajRacun");
        jmiKreirajRacun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiKreirajRacunActionPerformed(evt);
            }
        });
        jmRacun.add(jmiKreirajRacun);

        jmiPretraziRacune.setText("Pretrazi racune");
        jmiPretraziRacune.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPretraziRacuneActionPerformed(evt);
            }
        });
        jmRacun.add(jmiPretraziRacune);

        jMenuBar1.add(jmRacun);

        jmProizvodi.setText("Proizvodi");

        jmiPretragaProizvoda.setText("Pretraga proizvoda na zalihama");
        jmiPretragaProizvoda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPretragaProizvodaActionPerformed(evt);
            }
        });
        jmProizvodi.add(jmiPretragaProizvoda);

        jMenuBar1.add(jmProizvodi);

        jmiLogOut.setText(" Log out");
        jmiLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLogOutActionPerformed(evt);
            }
        });
        jmRadnik.add(jmiLogOut);

        jMenuBar1.add(jmRadnik);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiRadSaKlijentimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRadSaKlijentimaActionPerformed

        FMRadSaKlijentima fmKlijenti = new FMRadSaKlijentima(this, true);
        fmKlijenti.setVisible(true);

    }//GEN-LAST:event_jmiRadSaKlijentimaActionPerformed

    private void jmiKreirajRacunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiKreirajRacunActionPerformed
        FMRacun fmr = new FMRacun(this, true);
        fmr.setVisible(true);
    }//GEN-LAST:event_jmiKreirajRacunActionPerformed

    private void jmiLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLogOutActionPerformed

        java.awt.Window win[] = java.awt.Window.getWindows();
        for (int i = 0; i < win.length; i++) {
            win[i].dispose();
        }
        FMLogIn fml = new FMLogIn();
        fml.setVisible(true);
    }//GEN-LAST:event_jmiLogOutActionPerformed

    private void jmiPretraziRacuneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPretraziRacuneActionPerformed
        FMPretragaRacuna fmpr = new FMPretragaRacuna(this, true);
        fmpr.setVisible(true);
    }//GEN-LAST:event_jmiPretraziRacuneActionPerformed

    private void jmiPretragaProizvodaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPretragaProizvodaActionPerformed
        FMPretragaProizvodaNaZalihama fmp = new FMPretragaProizvodaNaZalihama(this, true);
        fmp.setVisible(true);

    }//GEN-LAST:event_jmiPretragaProizvodaActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jmKlijent;
    private javax.swing.JMenu jmProizvodi;
    private javax.swing.JMenu jmRacun;
    private javax.swing.JMenu jmRadnik;
    private javax.swing.JMenuItem jmiKreirajRacun;
    private javax.swing.JMenuItem jmiLogOut;
    private javax.swing.JMenuItem jmiPretragaProizvoda;
    private javax.swing.JMenuItem jmiPretraziRacune;
    private javax.swing.JMenuItem jmiRadSaKlijentima;
    // End of variables declaration//GEN-END:variables

    private void rasiriFormu() {
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void postaviUlogovanogRadnika() {
        Radnik radnik = (Radnik) Kontrolor.getInstance().getMapa().get("Ulogovan_radnik");
        this.setTitle(this.getTitle() + " Radnik: " + radnik);
        jmRadnik.setText(radnik + "");
    }
}
