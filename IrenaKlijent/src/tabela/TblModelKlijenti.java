/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela;

import domen.Klijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import kontrolor.Kontrolor;

/**
 *
 * @author Nadin kompjuter
 */
public class TblModelKlijenti extends AbstractTableModel {

    List<Klijent> lista_klijenata;

    public TblModelKlijenti(List<Klijent> lkl) {
        this.lista_klijenata = lkl;
    }

    public List<Klijent> getLista_klijenata() {
        return lista_klijenata;
    }

    @Override
    public int getRowCount() {
        return lista_klijenata.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Klijent klijent = lista_klijenata.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return klijent.getIme();
            case 2:
                return klijent.getPrezime();
            case 3:
                return klijent.getKontakt();
            case 4:
                return klijent.getMail();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "Redni broj";
            case 1:
                return "Ime";
            case 2:
                return "Prezime";
            case 3:
                return "Kontakt";
            case 4:
                return "E-mail";
            default:
                return "n/a";

        }

    }

    public Klijent vratiKlijenta(int red) {
        return lista_klijenata.get(red);
    }

    public void dodaj(Klijent klijent) {
        lista_klijenata.add(klijent);
        fireTableDataChanged();
    }

    public void obrisiSve() {
        lista_klijenata.removeAll(lista_klijenata);
        fireTableDataChanged();
    }

    public void ukloniKlijenta(int red) {
        lista_klijenata.remove(red);
        fireTableDataChanged();
    }

    public void azurirajTabelu(List<Klijent> lk) {
        lista_klijenata.addAll(lk);
        fireTableDataChanged();

    }

    public void izmeni(Klijent k) {
        for (int i = 0; i < lista_klijenata.size(); i++) {
            if (lista_klijenata.get(i).getKlijentID() == k.getKlijentID()) {
                lista_klijenata.remove(lista_klijenata.get(i));
                lista_klijenata.add(i, k);

            }
        }
        fireTableDataChanged();
    }

    
    
}
