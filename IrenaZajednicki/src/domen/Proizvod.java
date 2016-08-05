/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class Proizvod extends UP {

    private int raspolozivaKolicina;

    public Proizvod() {
        mapaUslov = new HashMap<>();
    }

    public Proizvod(String naziv, String sifra, int raspolozivaKolicina, double cena) {
        this.naziv = naziv;
        this.uslugaProzivodID = sifra;
        this.raspolozivaKolicina = raspolozivaKolicina;
        this.cena = cena;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getUslugaProzivodID() {
        return uslugaProzivodID;
    }

    public void setUslugaProzivodID(String uslugaProzivodID) {
        this.uslugaProzivodID = uslugaProzivodID;
    }

    public int getRaspolozivaKolicina() {
        return raspolozivaKolicina;
    }

    public void setRaspolozivaKolicina(int raspolozivaKolicina) {
        this.raspolozivaKolicina = raspolozivaKolicina;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "proizvod";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista_proizvoda = new LinkedList<>();
        try {
            while (rs.next()) {
                Proizvod p = new Proizvod();
                p.setCena(rs.getDouble("Cena"));
                p.setNaziv(rs.getString("Naziv"));
                p.setRaspolozivaKolicina(rs.getInt("RaspolozivaKolicina"));
                p.setUslugaProzivodID(rs.getString("ProizvodID"));
                lista_proizvoda.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista_proizvoda;
    }

    @Override
    public String vratiUslov() {
        if (mapaUslov.get(util.Util.USLOV_PRETRAGA_PROIZVODA) == null) {
            return "";
        } else {
            String rec = (String) mapaUslov.get(util.Util.USLOV_PRETRAGA_PROIZVODA);
            return " WHERE naziv LIKE '%" + rec + "%'";

        }

    }

}
