/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela;

import domen.Racun;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nadin kompjuter
 */
public class TblModelRacun extends AbstractTableModel {

    private List<Racun> lista_racuna;

    public TblModelRacun(List<Racun> lista_racuna) {
        this.lista_racuna = lista_racuna;
    }

    public List<Racun> getLista_racuna() {
        return lista_racuna;
    }

    public void setLista_racuna(List<Racun> lista_racuna) {
        this.lista_racuna = lista_racuna;
    }

    @Override
    public int getRowCount() {
        return lista_racuna.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun r = lista_racuna.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                return sdf.format(r.getDatum());
            case 2:
                return r.getUkupnaVrednost();
            case 3:
                return r.getRadnik();
            default:
                return "N/A";
        }
    }
     @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "Redni broj";
            case 1:
                return "Datum";
            case 2:
                return "Ukupna vrednost";
            case 3:
                return "Izdat od strane";
            default:
                return "n/a";
        }
    }

    public Racun vratiRacun(int red) {
       return lista_racuna.get(red);
    }

}
