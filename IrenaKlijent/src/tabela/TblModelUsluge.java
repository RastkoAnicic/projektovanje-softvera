/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela;

import domen.Usluga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nadin kompjuter
 */
public class TblModelUsluge extends AbstractTableModel{
    
 List<Usluga> lista_usluga;

    public TblModelUsluge(List<Usluga> lista_usluga) {
        this.lista_usluga = lista_usluga;
    }

  
       
    @Override
    public int getRowCount() {
       return lista_usluga.size();
    }

    @Override
    public int getColumnCount() {
       return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Usluga u =lista_usluga.get(rowIndex);
       switch(columnIndex){
           case 0: return u.getUslugaProzivodID();
           case 1: return u.getNaziv();
           case 2: return u.getCena();
           case 3: return u.getTipUsluge();
           default: return "N/A";
       }
              
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
           case 0: return "Sifra";
           case 1: return "Naziv";
           case 2: return "Cena";
           case 3: return "Tip usluge";
           default: return "N/A";
       }
        
    }

    public Usluga vratiUslugu(int selectedRow) {
        return lista_usluga.get(selectedRow);
        
    }
    
    
    
    
    
    
}