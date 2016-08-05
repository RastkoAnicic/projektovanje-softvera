/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class Usluga extends UP {

    private String opis;
    private TipUsluge tipUsluge;

    public Usluga() {
    }

    public Usluga(String uslugaID, String naziv, String opis, Double cena, TipUsluge tipUsluge) {
        this.uslugaProzivodID = uslugaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.tipUsluge = tipUsluge;
    }

    public TipUsluge getTipUsluge() {
        return tipUsluge;
    }

    public void setTipUsluge(TipUsluge tipUsluge) {
        this.tipUsluge = tipUsluge;
    }

    public String getUslugaProzivodID() {
        return uslugaProzivodID;
    }

    public void setUslugaProzivodID(String uslugaProzivodID) {
        this.uslugaProzivodID = uslugaProzivodID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista_usluga = new LinkedList<>();
        try {
            while (rs.next()) {
                Usluga u = new Usluga();
                u.setCena(rs.getDouble("Cena"));
                u.setNaziv(rs.getString("Naziv"));
                u.setOpis(rs.getString("Opis"));
                u.setUslugaProzivodID(rs.getString("UslugaID"));

                TipUsluge tu = new TipUsluge();
                tu.setTipUslugeID(rs.getInt("TipUslugeID"));

                u.setTipUsluge(tu);
                lista_usluga.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista_usluga;
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        vezani_objekti = new LinkedList<>();
        vezani_objekti.add(new TipUsluge());
        return vezani_objekti.size();
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return vezani_objekti.get(i);
    }

    @Override
    public List<OpstiDomenskiObjekat> dopuniListu(List<OpstiDomenskiObjekat> lista, ResultSet rsVezObj, OpstiDomenskiObjekat vezObj) {
        List<OpstiDomenskiObjekat> listaDO = new ArrayList<>();

        try {
            if (vezObj instanceof TipUsluge) {
                while (rsVezObj.next()) {
                    TipUsluge tu = new TipUsluge();
                    tu.setNazivTipaUsluge(rsVezObj.getString("NazivTipaUsluge"));
                    tu.setTipUslugeID(rsVezObj.getInt("TipUslugeID"));
                    listaDO.add(tu);
                }
                for (OpstiDomenskiObjekat odo : lista) {
                    for (OpstiDomenskiObjekat tip : listaDO) {
                        Usluga u = (Usluga) odo;
                        TipUsluge tu = (TipUsluge) tip;
                        if (u.getTipUsluge().getTipUslugeID() == tu.getTipUslugeID()) {
                            u.setTipUsluge(tu);
                            break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

}
