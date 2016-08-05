/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela;

import domen.Proizvod;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nadin kompjuter
 */
public class TblModelPretragaProizvoda extends AbstractTableModel {

    List<Proizvod> lista_proizvoda;

    public TblModelPretragaProizvoda(List<Proizvod> lista_proizvoda) {
        this.lista_proizvoda = lista_proizvoda;
    }

    @Override
    public int getRowCount() {
        return lista_proizvoda.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proizvod p = lista_proizvoda.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getUslugaProzivodID();
            case 1:
                return p.getNaziv();
            case 2:
                return p.getRaspolozivaKolicina();
            default:
                return "N/A";
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Sifra";
            case 1:
                return "Naziv";
            case 2:
                return "Raspoloziva kolicina";
            default:
                return "N/A";
        }

    }

    public Proizvod vratiProizvod(int red) {

        return lista_proizvoda.get(red);
    }

}
