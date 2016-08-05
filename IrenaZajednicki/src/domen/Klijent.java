/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class Klijent extends OpstiDomenskiObjekat {

    private int klijentID;
    private String ime;
    private String prezime;
    private String jmbg;
    private String kontakt;
    private String mail;
    private String ulica;
    private String brojUlice;
    private Opstina opstina;
    private boolean aktivan;
   

    public Klijent() {
        mapaUslov = new HashMap<>();
    }

    public Klijent(int klijentID, String ime, String prezime, String jmbg, String kontakt, String mail, String ulica, String broj, Opstina opstina, boolean uplacenaClanarina) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.kontakt = kontakt;
        this.mail = mail;
        this.ulica = ulica;
        this.brojUlice = broj;
        this.opstina = opstina;
        this.aktivan = aktivan;
        mapaUslov = new HashMap<>();

    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBrojUlice() {
        return brojUlice;
    }

    public void setBrojUlice(String broj) {
        this.brojUlice = broj;
    }

    public Opstina getOpstina() {
        return opstina;
    }

    public void setOpstina(Opstina opstina) {
        this.opstina = opstina;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " - " + jmbg;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Klijent) {
            Klijent k = (Klijent) obj;
            if (k.getKlijentID() == klijentID) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        if (isAktivan()) {
            return "(null,'" + ime + "','" + prezime + "','" + jmbg + "','" + ulica + "','" + brojUlice + "','" + kontakt + "','" + 1 + "','" + opstina.getPostanskiBroj() + "','" + mail + "')";
        } else {
            return "(null,'" + ime + "','" + prezime + "','" + jmbg + "','" + ulica + "','" + brojUlice + "','" + kontakt + "','" + 0 + "','" + opstina.getPostanskiBroj() + "','" + mail + "')";

        }

    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lk = new ArrayList<>();
        try {

            while (rs.next()) {
                Klijent k = new Klijent();
                k.setBrojUlice(rs.getString("Broj"));
                k.setIme(rs.getString("Ime"));
                k.setPrezime(rs.getString("Prezime"));
                k.setJmbg(rs.getString("JMBG"));
                k.setKlijentID(rs.getInt("KlijentID"));
                k.setKontakt(rs.getString("Kontakt"));
                k.setMail(rs.getString("Mail"));

                Opstina o = new Opstina();
                o.setPostanskiBroj(rs.getInt("PostanskiBroj"));

                k.setOpstina(o);
                k.setUlica(rs.getString("Ulica"));
                k.setAktivan(rs.getBoolean("Status"));

                lk.add(k);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lk;
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        vezani_objekti = new LinkedList<>();
        vezani_objekti.add(new Opstina());
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
            if (vezObj instanceof Opstina) {
                while (rsVezObj.next()) {

                    Opstina o = new Opstina();
                    o.setNaziv(rsVezObj.getString("Naziv"));
                    o.setPostanskiBroj(rsVezObj.getInt("PostanskiBroj"));

                    listaDO.add(o);
                }
                for (OpstiDomenskiObjekat odo : lista) {
                    for (OpstiDomenskiObjekat opstina : listaDO) {
                        Klijent k = (Klijent) odo;
                        Opstina o = ((Opstina) opstina);
                        if (k.getOpstina().getPostanskiBroj() == o.getPostanskiBroj()) {
                            k.setOpstina(o);
                            break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    @Override
    public String vratiUslov() {
        if(opstina == null){
        return "";}
        if (isAktivan()) {
            return " Ime ='" + ime + "' , Prezime = '" + prezime + "', Ulica ='" + ulica + "' , Broj = '" + brojUlice + "'"
                    + " , JMBG = '" + jmbg + "', Mail = '" + mail + "', Kontakt = '" + kontakt + "' , PostanskiBroj ="
                    + opstina.getPostanskiBroj() + ", Status = 1 WHERE KlijentID = " + klijentID;
        } else {
            return " Ime ='" + ime + "' , Prezime = '" + prezime + "', Ulica ='" + ulica + "' , Broj = '" + brojUlice + "'"
                    + " , JMBG = '" + jmbg + "', Mail = '" + mail + "', Kontakt = '" + kontakt + "' , PostanskiBroj ="
                    + opstina.getPostanskiBroj() + ", Status = 0 WHERE KlijentID = " + klijentID;

        }
    }

   
}
