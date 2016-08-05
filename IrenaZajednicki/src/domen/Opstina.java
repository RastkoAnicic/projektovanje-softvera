/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class Opstina extends OpstiDomenskiObjekat  {

    private String naziv;
    private int postanskiBroj;

    public Opstina() {
    }

    public Opstina(String naziv, int postanskiBroj) {
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv + " - " + postanskiBroj;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Opstina) {
            Opstina o = (Opstina) obj;
            if (o.getPostanskiBroj() == postanskiBroj) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "opstina";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "("+postanskiBroj+",'"+naziv+"')";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lo = new ArrayList<>();
        try {
            while (rs.next()) {
                Opstina o = new Opstina();
                o.setNaziv(rs.getString("Naziv"));
                o.setPostanskiBroj(rs.getInt("PostanskiBroj"));
                lo.add(o);
            }
        } catch (SQLException ex) {
        }
        return lo;
    }

}
