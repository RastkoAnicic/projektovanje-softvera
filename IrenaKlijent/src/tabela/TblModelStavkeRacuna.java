/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela;

import domen.Proizvod;
import domen.Racun;
import domen.StavkaRacuna;
import domen.Usluga;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nadin kompjuter
 */
public class TblModelStavkeRacuna extends AbstractTableModel {

    Racun racun;

    public TblModelStavkeRacuna(Racun racun) {
        this.racun = racun;
    }

    @Override
    public int getRowCount() {
        return racun.getLista_stavki().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna sr = racun.getLista_stavki().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sr.getRedniBroj();
            case 1:
                return sr.getUslugaProizvod();
            case 2:
                return sr.getKolicina();
            case 3:
                return sr.getCena();
            case 4:
                return sr.getIznos();
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
                return "Naziv";
            case 2:
                return "Kolicina";
            case 3:
                return "Cena";
            case 4:
                return "Iznos";
            default:
                return "N/A";
        }

    }

    public void dodajStavkuProizvod(Proizvod p, int kolicina, String racunID) {
        StavkaRacuna sr = racun.vratiStavkuRacuna(p);
        if (sr != null) {
            sr.setKolicina(sr.getKolicina() + kolicina);
            sr.setIznos(sr.getKolicina() * sr.getCena());
            sr.setRacunID(racunID);
        } else {
            sr = new StavkaRacuna();
            sr.setRedniBroj(racun.getLista_stavki().size() + 1);
            sr.setKolicina(kolicina);
            sr.setRacunID(racunID);
            sr.setCena(p.getCena());
            sr.setUslugaProizvod(p);
            sr.setIznos(sr.getKolicina() * sr.getCena());

            racun.getLista_stavki().add(sr);
        }
        fireTableDataChanged();
    }

    public Racun vratiRacun() {
       return racun;
    }

    public void dodajStavkuUsluga(Usluga u, String racunID) throws Exception {
      StavkaRacuna sr =racun.vratiStavkuRacuna(u);
        if(sr!=null){
        throw new Exception("Ova usluga vec postoji na racunu!");
        }
        else{
            sr = new StavkaRacuna();
            sr.setRedniBroj(racun.getLista_stavki().size()+1);
             sr.setKolicina(1);
             sr.setRacunID(racunID);
            sr.setCena(u.getCena());
            sr.setUslugaProizvod(u);
            sr.setIznos(sr.getKolicina() * sr.getCena());

        racun.getLista_stavki().add(sr);
        fireTableDataChanged();
        }
    }

    public void obrisiStavku(int red) {
       racun.getLista_stavki().remove(red);
       fireTableDataChanged();
    }

}
