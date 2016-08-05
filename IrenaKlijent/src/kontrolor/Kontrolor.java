/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolor;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.Opstina;
import domen.Proizvod;
import domen.Racun;
import domen.Radnik;
import domen.TipUsluge;
import domen.Usluga;
import java.io.IOException;
import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import transferObjekat.KlijentTransferObjekat;
import transferObjekat.ServerTransferObjekat;

/**
 *
 * @author Nadin kompjuter
 */
public class Kontrolor {

    private KolekcijaKlijenata kk;
    private static Kontrolor instanca;
    private int aktivnaForma;
    private Map<String, Object> mapa;

    private Kontrolor() {
        kk = new KolekcijaKlijenata();
        mapa = new HashMap<>();
    }

    public static Kontrolor getInstance() {
        if (instanca == null) {
            instanca = new Kontrolor();
        }
        return instanca;
    }

    public Map<String, Object> getMapa() {
        return mapa;
    }

    public int getAktivnaForma() {
        return aktivnaForma;
    }

    public void setAktivnaForma(int aktivnaForma) {
        this.aktivnaForma = aktivnaForma;
    }

    public List<Klijent> vratiListuKlijenata() throws Exception {

        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_SVE_KLIJENTE);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Klijent>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }

    }

    public List<Opstina> vratiListuOpstina() throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_SVE_OPSTINE);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Opstina>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public List<Proizvod> vratiListuProizvoda() throws Exception {

        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_SVE_PROIZVODE);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Proizvod>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public List<Usluga> vratiListuUsluga() throws Exception {

        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_SVE_USLUGE);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Usluga>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public void sacuvajRacun(Racun racun) throws Exception {

        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);

        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_SACUVAJ_RACUN);
        kto.setParametar(racun);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public int vratiNajveciIDKlijenta() throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_NAJVECI_ID_KLIJENTA);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (int) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public void izmeniKlijenta(Klijent klijent) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);

        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_IZMENI_KLIJENTA);
        kto.setParametar(klijent);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public void postaviStatusNaNeaktivan(Klijent klijent) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);

        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_POSTAVI_STATUS_KLIJENTA_NA_NEAKTIVAN);
        kto.setParametar(klijent);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public void sacuvajKlijente(List<Klijent> listaKlijenata) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_SACUVAJ_LISTU_KLIJENATA);
        kto.setParametar(listaKlijenata);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public List<TipUsluge> vratiTipoveUsluga() throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_TIPOVE_USLUGA);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<TipUsluge>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }

    }

    public Radnik proveraLogovanja(OpstiDomenskiObjekat odo) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_PROVERA_LOGOVANJA);
        kto.setParametar(odo);
        out.writeObject(kto);
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            System.out.println("sad ce da kastujee");
            return (Radnik) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }

    }

    public void dodajOpstinu(Opstina o) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);

        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_DODAJ_OPSTINU);
        kto.setParametar(o);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
        } else {
            throw new Exception(sto.getGreska());
        }

    }

    public List<Racun> vratiRacuneZaKlijenta(Racun racunSaKlijentom) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_RACUNE_ZA_KLIJENTA);
        kto.setParametar(racunSaKlijentom);
        out.writeObject(kto);
    
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Racun>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }

    }

    public Racun vratiStavkeRacunaZaRacun(Racun racun) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_STAVKE_RACUNE_ZA_RACUNE);
        kto.setParametar(racun);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (Racun) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }

    }

    public List<Radnik> vratiListuRadnika() throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_LISTU_RADNIKA);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Radnik>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }
    }

    public List<Racun> vratiRacunePoZadatimKriterijumima(Racun racun) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_RACUNE_PO_ZADATIM_KRITERIJUMIMA);
        kto.setParametar(racun);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Racun>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }

    }

    public void promeniStatusUlogovanogRadnika(Radnik radnik) throws Exception {
        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_PROMENI_STATUS_RADNIKU);
        kto.setParametar(radnik);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
        } else {
        }

    }

    public List<Proizvod> vratiProizvodePoZadatomKriterijumu(Proizvod p) throws IOException, Exception {

        Socket s = (Socket) getMapa().get(util.Util.MAP_KEY_SOKET);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

        KlijentTransferObjekat kto = new KlijentTransferObjekat();
        kto.setOperacija(util.Util.OPERACIJA_VRATI_PROIZVODE_PO_KRITERIJUMU);
        kto.setParametar(p);
        out.writeObject(kto);

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ServerTransferObjekat sto = (ServerTransferObjekat) in.readObject();
        if (sto.getStatus() == util.Util.SERVER_STATUS_OPERACIJA_OK) {
            return (List<Proizvod>) sto.getRezultat();
        } else {
            throw new Exception(sto.getGreska());
        }

    }

}
