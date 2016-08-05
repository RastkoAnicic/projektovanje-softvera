/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolor;

import db.DBBroker;
import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.Opstina;
import domen.Proizvod;
import domen.Racun;
import domen.Radnik;
import domen.StavkaRacuna;
import domen.TipUsluge;
import domen.Usluga;
import forme.FMGlavna;
import java.util.List;
import so.OpstaSO;
import so.SOIzmeniKlijenta;
import so.SONadjiRadnika;
import so.SOPretraziProizvode;
import so.SOPretraziRacune;
import so.SOUcitajRacun;
import so.SOVratiListuOpstina;
import so.SOVratiSveKlijente;
import so.SOVratiSveProizvode;
import so.SOVratiSveRacuneZaKlijenta;
import so.SOVratiSveRadnike;
import so.SOVratiSveUsluge;
import so.SOVratiTipoveUsluga;
import so.SOZapamtiListuKlijenata;
import so.SOZapamtiOpstinu;
import so.SOZapamtiRacun;
import so.SOZapamtiRadnika;

/**
 *
 * @author Nadin kompjuter
 */
public class KontrolorAL {

    private static KontrolorAL instanca;

    FMGlavna fmGlavna;

    private KontrolorAL() {
    }

    public static KontrolorAL getInstance() {
        if (instanca == null) {
            instanca = new KontrolorAL();
        }
        return instanca;
    }

    public List<OpstiDomenskiObjekat> vratiSveOpstine() throws Exception {
        OpstaSO so = new SOVratiListuOpstina();
        so.izvrsi(new Opstina());
        List<OpstiDomenskiObjekat> opstine = ((SOVratiListuOpstina) so).getLo();
        return opstine;
    }

    public List<OpstiDomenskiObjekat> vratiSveRadnike(FMGlavna fmGlavna) throws Exception {
        this.fmGlavna = fmGlavna;
        Radnik r = new Radnik();
        List<OpstiDomenskiObjekat> radnici = DBBroker.getInstance().vratiSve(r);
        return radnici;
    }

    public void promeniStatusRadniku(Radnik radnik) {
        fmGlavna.promeniStatusRadniku(radnik);

    }

    public void testirajKonekciju() throws Exception {
        try {
            DBBroker.getInstance().konektujSeNaBazu();
        } catch (Exception ex) {
            throw new Exception("Neuspesna konekcija na bazu!");
        }

    }

    public void dodajRadnika(Radnik odo) throws Exception {
        OpstaSO so = new SOZapamtiRadnika();
        so.izvrsi(odo);

    }

    public void postaviStatuseNaOffline() {
        fmGlavna.postaviStatuseNaOffline();
    }

    public List<OpstiDomenskiObjekat> vratiSveKlijente() throws Exception {
        OpstaSO so = new SOVratiSveKlijente();
        so.izvrsi(new Klijent());
        List<OpstiDomenskiObjekat> klijenti = ((SOVratiSveKlijente) so).getLk();
        return klijenti;

    }

    public OpstiDomenskiObjekat proveraLogovanja(OpstiDomenskiObjekat odo) throws Exception {
        OpstaSO so = new SONadjiRadnika();
        so.izvrsi(odo);
        OpstiDomenskiObjekat r = ((SONadjiRadnika) so).getRadnik();
        if (r == null) {
            return r;
        } else {
            if (fmGlavna.daLiJeRadnikVecPrijavljen(r)) {
                System.out.println("ovde sam vratio true");
                ((Radnik) r).setStatus("online");
                            return r;

            }
            ((Radnik) r).setStatus("ofline");
            return r;
        }
    }

    public void dodajOpstinu(Opstina odo) throws Exception {
        OpstaSO so = new SOZapamtiOpstinu();
        so.izvrsi(odo);

    }

    public void sacuvajListuKlijenata(List<OpstiDomenskiObjekat> listaKlijenata) throws Exception {

        OpstaSO so = new SOZapamtiListuKlijenata(listaKlijenata);
        so.izvrsi(null);
    }

    public List<OpstiDomenskiObjekat> vratiRacuneZaKlijenata(Racun odo) throws Exception {

        OpstaSO so = new SOVratiSveRacuneZaKlijenta();
        so.izvrsi(odo);
        List<OpstiDomenskiObjekat> racuni = ((SOVratiSveRacuneZaKlijenta) so).getLr();
        return racuni;
    }

    public OpstiDomenskiObjekat vratiStavkeZaRacun(Racun odo) throws Exception {

        OpstaSO so = new SOUcitajRacun(odo);
        so.izvrsi(new StavkaRacuna());
        OpstiDomenskiObjekat racun = ((SOUcitajRacun) so).getRacunSaStavkama();
        return racun;
    }

    public void izmeniKlijenta(Klijent odo) throws Exception {
        OpstaSO so = new SOIzmeniKlijenta();
        so.izvrsi(odo);
    }

    public List<OpstiDomenskiObjekat> vratiSveProizvode() throws Exception {
        OpstaSO so = new SOVratiSveProizvode();
        so.izvrsi(new Proizvod());
        List<OpstiDomenskiObjekat> proizvdi = ((SOVratiSveProizvode) so).getLp();
        return proizvdi;

    }

    public List<OpstiDomenskiObjekat> vratiSveUsluge() throws Exception {
        OpstaSO so = new SOVratiSveUsluge();
        so.izvrsi(new Usluga());
        List<OpstiDomenskiObjekat> usluge = ((SOVratiSveUsluge) so).getLu();
        return usluge;

    }

    public List<OpstiDomenskiObjekat> vratiTipoveUsluga() throws Exception {
        OpstaSO so = new SOVratiTipoveUsluga();
        so.izvrsi(new TipUsluge());
        List<OpstiDomenskiObjekat> tipUsluge = ((SOVratiTipoveUsluga) so).getLtu();
        return tipUsluge;
    }

    public void sacuvajRacun(Racun odo) throws Exception {
        OpstaSO so = new SOZapamtiRacun();
        so.izvrsi(odo);

    }

    public List<OpstiDomenskiObjekat> vratiListuRadnika() throws Exception {
        OpstaSO so = new SOVratiSveRadnike();
        so.izvrsi(new Radnik());
        List<OpstiDomenskiObjekat> radnici = ((SOVratiSveRadnike) so).getLr();
        return radnici;

    }

    public List<OpstiDomenskiObjekat> pretragaRacuna(Racun racun) throws Exception {
        OpstaSO so = new SOPretraziRacune();
        so.izvrsi(racun);
        List<OpstiDomenskiObjekat> racuni = ((SOPretraziRacune) so).getLr();
        return racuni;

    }

    public List<OpstiDomenskiObjekat> vratiProizvodePoKriterijumu(Proizvod odo) throws Exception {
        OpstaSO so = new SOPretraziProizvode();
        so.izvrsi(odo);
        List<OpstiDomenskiObjekat> proizvodi = ((SOPretraziProizvode) so).getLp();
        return proizvodi;
    }

}
