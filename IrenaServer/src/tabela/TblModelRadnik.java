/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela;

import domen.OpstiDomenskiObjekat;
import domen.Radnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nadin kompjuter
 */
public class TblModelRadnik extends AbstractTableModel {
    
    List<OpstiDomenskiObjekat> lista_radnika;
    
    public TblModelRadnik(List<OpstiDomenskiObjekat> lr) {
        this.lista_radnika = lr;
    }
    
    @Override
    public int getRowCount() {
        return lista_radnika.size();
    }
    
    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Radnik radnik = (Radnik) lista_radnika.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return radnik;
            case 1:
                return radnik.getStatus();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Ime i prezime";
            case 1:
                return "Status";
            default:
                return "N/A";
        }
        
    }
    
    public void promeniStatusRadniku(Radnik radnik) {
        for (OpstiDomenskiObjekat odo : lista_radnika) {
            Radnik r = (Radnik) odo;
            if (r.getRadnikID().equals(radnik.getRadnikID())) {
                r.setStatus(radnik.getStatus());
                
            }
        }
        fireTableDataChanged();
    }
    
}
