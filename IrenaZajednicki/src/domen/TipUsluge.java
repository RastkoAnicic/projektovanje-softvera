/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class TipUsluge extends OpstiDomenskiObjekat {

    private int tipUslugeID;
    private String nazivTipaUsluge;

    public TipUsluge() {
    }

    public TipUsluge(int tipUslugeID, String nazivTipaUsluge) {
        this.tipUslugeID = tipUslugeID;
        this.nazivTipaUsluge = nazivTipaUsluge;
    }

    public String getNazivTipaUsluge() {
        return nazivTipaUsluge;
    }

    public void setNazivTipaUsluge(String nazivTipaUsluge) {
        this.nazivTipaUsluge = nazivTipaUsluge;
    }

    public int getTipUslugeID() {
        return tipUslugeID;
    }

    public void setTipUslugeID(int tipUslugeID) {
        this.tipUslugeID = tipUslugeID;
    }

    @Override
    public String toString() {
        return nazivTipaUsluge;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof TipUsluge) {
            TipUsluge tu = (TipUsluge) obj;
            if (tu.getTipUslugeID() == tipUslugeID) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "tipusluge";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> tipovi = new LinkedList<>();
        try {
            while (rs.next()) {
                TipUsluge tu = new TipUsluge();
                tu.setTipUslugeID(rs.getInt("TipUslugeID"));
                tu.setNazivTipaUsluge(rs.getString("NazivTipaUsluge"));
                tipovi.add(tu);
            }
        } catch (SQLException ex) {
            System.out.println("evo i ovde greska");
            ex.printStackTrace();
        }
        return tipovi;
    }
}
