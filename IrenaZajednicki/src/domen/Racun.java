/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class Racun extends OpstiDomenskiObjekat {

    private String racunID;
    private Double ukupnaVrednost;
    private Date datum;
    private Klijent klijent;
    private Radnik radnik;
    private List<StavkaRacuna> lista_stavki;
    private List<OpstiDomenskiObjekat> lista_slabih_obj;
    
    private String godina;
    private String mesec;
    private String dan;
    private String rasponUkupneVrednosti;

    public Racun() {
        lista_stavki = new LinkedList<>();
        mapaUslov = new HashMap<>();
    }

    public Racun(String racunID, Double ukupnaVrednost, Date datum, Klijent Klijent, Radnik Radnik, List<StavkaRacuna> lista_stavki) {
        this.racunID = racunID;
        this.ukupnaVrednost = ukupnaVrednost;
        this.datum = datum;
        this.klijent = Klijent;
        this.radnik = Radnik;
        this.lista_stavki = lista_stavki;
    }

    public List<StavkaRacuna> getLista_stavki() {
        return lista_stavki;
    }

    public void setLista_stavki(List<StavkaRacuna> lista_stavki) {
        this.lista_stavki = lista_stavki;
    }

    public String getRacunID() {
        return racunID;
    }

    public void setRacunID(String racunID) {
        this.racunID = racunID;
    }

    public Double getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(Double ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent Klijent) {
        this.klijent = Klijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik Radnik) {
        this.radnik = Radnik;
    }

    public StavkaRacuna vratiStavkuRacuna(UP p) {
        for (StavkaRacuna stavkaRacuna : lista_stavki) {
            if (stavkaRacuna.getUslugaProizvod() == p) {
                return stavkaRacuna;
            }
        }
        return null;
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(this.datum);

        return "('" + racunID + "','" + d + "'," + ukupnaVrednost + "," + klijent.getKlijentID() + ",'" + radnik.getRadnikID() + "')";

    }

    @Override
    public int vratiBrojVezanihObjekata() {
        vezani_objekti = new LinkedList<>();
        vezani_objekti.add(new Klijent());
        vezani_objekti.add(new Radnik());
        return vezani_objekti.size();
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return vezani_objekti.get(i);
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new LinkedList();
        try {
            while (rs.next()) {
                Racun r = new Racun();
                r.setDatum(new Date(rs.getDate("Datum").getTime()));
                r.setRacunID(rs.getString("RacunID"));
                r.setUkupnaVrednost(rs.getDouble("UkupnaVrednost"));

                Radnik radnik = new Radnik();
                radnik.setRadnikID(rs.getString("RadnikID"));
                r.setRadnik(radnik);

                Klijent klijent = new Klijent();
                klijent.setKlijentID(rs.getInt("KlijentID"));
                r.setKlijent(klijent);
                lista.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<OpstiDomenskiObjekat> dopuniListu(List<OpstiDomenskiObjekat> lista, ResultSet rsVezObj, OpstiDomenskiObjekat vezObj) {

        List<OpstiDomenskiObjekat> listaVezo = new ArrayList<>();
        try {
            if (vezObj instanceof Radnik) {
                while (rsVezObj.next()) {

                    Radnik radnik = new Radnik();
                    radnik.setDatumZaposlenja(new Date(rsVezObj.getDate("DatumZaposlenja").getTime()));
                    radnik.setIme(rsVezObj.getString("Ime"));
                    radnik.setPrezime(rsVezObj.getString("Prezime"));
                    radnik.setPassword(rsVezObj.getString("Lozinka"));
                    radnik.setUsername(rsVezObj.getString("Username"));
                    radnik.setRadnikID(rsVezObj.getString("RadnikID"));

                    listaVezo.add(radnik);
                }
                for (OpstiDomenskiObjekat odo : lista) {
                    for (OpstiDomenskiObjekat radnik : listaVezo) {
                        Racun r = (Racun) odo;
                        Radnik rad = ((Radnik) radnik);
                        if (r.getRadnik().getRadnikID().equals(rad.getRadnikID())) {
                            r.setRadnik(rad);
                        }
                    }
                }
            }
            if (vezObj instanceof Klijent) {

                while (rsVezObj.next()) {
                    //  StavkaRacuna sr = new StavkaRacuna();

                    Klijent k = new Klijent();
                    k.setBrojUlice(rsVezObj.getString("Broj"));
                    k.setIme(rsVezObj.getString("Ime"));
                    k.setPrezime(rsVezObj.getString("Prezime"));
                    k.setJmbg(rsVezObj.getString("JMBG"));
                    k.setKlijentID(rsVezObj.getInt("KlijentID"));
                    k.setKontakt(rsVezObj.getString("Kontakt"));
                    k.setMail(rsVezObj.getString("Mail"));

                    Opstina o = new Opstina();
                    o.setPostanskiBroj(rsVezObj.getInt("PostanskiBroj"));

                    k.setOpstina(o);
                    k.setUlica(rsVezObj.getString("Ulica"));
                    k.setAktivan(rsVezObj.getBoolean("Status"));

                    listaVezo.add(k);
                }

                for (OpstiDomenskiObjekat odo : lista) {
                    for (OpstiDomenskiObjekat klijent : listaVezo) {
                        Racun r = (Racun) odo;
                        Klijent kl = ((Klijent) klijent);
                        if (r.getKlijent().getKlijentID() == kl.getKlijentID()) {
                            r.setKlijent(kl);
                        }
                    }
                }
            }

        } catch (SQLException ex) {
        }
        return lista;
    }

    

    @Override
    public void postaviListuSlabih(List<OpstiDomenskiObjekat> lista) {
        for (OpstiDomenskiObjekat odo : lista) {
            lista_stavki.add((StavkaRacuna) odo);
        }
    }

    @Override
    public int vratiBrojSlabihObjekata() {
        lista_slabih_obj = new LinkedList<>();
        lista_slabih_obj.add(new StavkaRacuna());
        return lista_slabih_obj.size();

    }

    @Override
    public int vratiBrojSlogovaSlabog(int i) {
        if (lista_slabih_obj.get(i) instanceof StavkaRacuna) {
            return lista_stavki.size();
        }
        return -1;
    }

    @Override
    public OpstiDomenskiObjekat vratiSlogVezanogObjekata(int i, int j) {
        if (lista_slabih_obj.get(i) instanceof StavkaRacuna) {
            return lista_stavki.get(j);
        }
        return null;

    }

    @Override
    public String vratiUslov() {
        if (mapaUslov.get(util.Util.USLOV_RADNIK) == null) {

            String upit = " WHERE KlijentID = " + ((Klijent) mapaUslov.get(util.Util.USLOV_KLIJENT)).getKlijentID();
            return upit;

        }

        String klijentID = "";
        String radnikID = "";
        String godina = "";
        String mesec = "";
        String dan = "";
        String ukupnaVrednost = "";

        Radnik radnik = (Radnik) mapaUslov.get(util.Util.USLOV_RADNIK);
        Klijent klijent = (Klijent) mapaUslov.get(util.Util.USLOV_KLIJENT);

        if (!klijent.getIme()
                .equals("-")) {
            klijentID = "= " + klijent.getKlijentID();
        }

        if (!radnik.getIme()
                .equals("-")) {
            radnikID = " AND RadnikID = '" + radnik.getRadnikID() + "'";
        }

        if (!mapaUslov.get(util.Util.USLOV_DAN)
                .equals("-")) {
            dan = "= " + (String) mapaUslov.get(util.Util.USLOV_DAN);
        }

        if (!mapaUslov.get(util.Util.USLOV_MESEC)
                .equals("-")) {
            mesec = "= " + (String) mapaUslov.get(util.Util.USLOV_MESEC);
        }

        if (!mapaUslov.get(util.Util.USLOV_GODINA)
                .equals("-")) {
            godina = "= " + (String) mapaUslov.get(util.Util.USLOV_GODINA);
        }

        if (!mapaUslov.get(util.Util.USLOV_UKUPNA_VREDNOST)
                .equals("-")) {
            if (mapaUslov.get(util.Util.USLOV_UKUPNA_VREDNOST).equals("0-2000")) {
                ukupnaVrednost = "BETWEEN 0 AND 2000";
            }
            if (mapaUslov.get(util.Util.USLOV_UKUPNA_VREDNOST).equals("2000-4000")) {
                ukupnaVrednost = "BETWEEN 2000 AND 4000";
            }
            if (mapaUslov.get(util.Util.USLOV_UKUPNA_VREDNOST).equals("4000-6000")) {
                ukupnaVrednost = "BETWEEN 4000 AND 6000";
            }
            if (mapaUslov.get(util.Util.USLOV_UKUPNA_VREDNOST).equals("6000-8000")) {
                ukupnaVrednost = "BETWEEN 6000 AND 8000";
            }
            if (mapaUslov.get(util.Util.USLOV_UKUPNA_VREDNOST).equals("8000 i vise")) {
                ukupnaVrednost = ">8000";
            }

        }

        String upit = " WHERE KlijentID " + klijentID + " AND UkupnaVrednost " + ukupnaVrednost
                + " AND YEAR(Datum) " + godina + " AND MONTH(Datum) " + mesec + " AND DAY(Datum) " + dan + radnikID;
        return upit;

    }


    
    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public String getMesec() {
        return mesec;
    }

    public void setMesec(String mesec) {
        this.mesec = mesec;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getRasponUkupneVrednosti() {
        return rasponUkupneVrednosti;
    }

    public void setRasponUkupneVrednosti(String rasponUkupneVrednosti) {
        this.rasponUkupneVrednosti = rasponUkupneVrednosti;
    }

    
}
