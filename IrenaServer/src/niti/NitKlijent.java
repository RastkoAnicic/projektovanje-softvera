/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.Opstina;
import domen.Proizvod;
import domen.Racun;
import domen.Radnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


import transferObjekat.KlijentTransferObjekat;
import transferObjekat.ServerTransferObjekat;

/**
 *
 * @author Nadin kompjuter
 */
public class NitKlijent extends Thread {

    Socket ks;
    Radnik radnik;
    ObjectInputStream in;
    ObjectOutputStream out;
public boolean kraj = false;
    public NitKlijent(Socket soket) {
        radnik = new Radnik();
        ks = soket;
       // start();
    }

    @Override
    public void run() {
        try {
            //     db.DBKonekcija dbKonekcija = new DBKonekcija();

            while (!kraj) {
                ObjectInputStream in = new ObjectInputStream(ks.getInputStream());
                KlijentTransferObjekat kto = (KlijentTransferObjekat) in.readObject();
                int operacija = kto.getOperacija();
                switch (operacija) {
                    case util.Util.OPERACIJA_VRATI_SVE_OPSTINE: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                       List<OpstiDomenskiObjekat> opstine = kontrolor.KontrolorAL.getInstance().vratiSveOpstine();
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(opstine);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_SVE_KLIJENTE: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> klijenti = kontrolor.KontrolorAL.getInstance().vratiSveKlijente();
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(klijenti);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }

                    case util.Util.OPERACIJA_PROVERA_LOGOVANJA: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        OpstiDomenskiObjekat r = kontrolor.KontrolorAL.getInstance().proveraLogovanja((OpstiDomenskiObjekat) kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        radnik = (Radnik) r;
                        sto.setRezultat(r);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                       break;                         
                    }
                    case util.Util.OPERACIJA_DODAJ_OPSTINU: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        kontrolor.KontrolorAL.getInstance().dodajOpstinu((Opstina) kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_SACUVAJ_LISTU_KLIJENATA: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        kontrolor.KontrolorAL.getInstance().sacuvajListuKlijenata((List<OpstiDomenskiObjekat>) kto.getParametar());

                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_RACUNE_ZA_KLIJENTA: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> racuni = kontrolor.KontrolorAL.getInstance().vratiRacuneZaKlijenata((Racun) kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(racuni);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);

                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_STAVKE_RACUNE_ZA_RACUNE: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        OpstiDomenskiObjekat racun = kontrolor.KontrolorAL.getInstance().vratiStavkeZaRacun((Racun) kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(racun);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_IZMENI_KLIJENTA: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        kontrolor.KontrolorAL.getInstance().izmeniKlijenta((Klijent) kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_SVE_PROIZVODE: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> proizvdi = kontrolor.KontrolorAL.getInstance().vratiSveProizvode();
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(proizvdi);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_SVE_USLUGE: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> usluge =kontrolor.KontrolorAL.getInstance().vratiSveUsluge();
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(usluge);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_TIPOVE_USLUGA: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> tipUsluge = kontrolor.KontrolorAL.getInstance().vratiTipoveUsluga();
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(tipUsluge);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_SACUVAJ_RACUN: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        kontrolor.KontrolorAL.getInstance().sacuvajRacun((Racun) kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_LISTU_RADNIKA: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> radnici = kontrolor.KontrolorAL.getInstance().vratiListuRadnika();
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(radnici);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                    case util.Util.OPERACIJA_VRATI_RACUNE_PO_ZADATIM_KRITERIJUMIMA: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> racuni = kontrolor.KontrolorAL.getInstance().pretragaRacuna((Racun)kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(racuni);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                      case util.Util.OPERACIJA_PROMENI_STATUS_RADNIKU: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        Radnik radnik = (Radnik) kto.getParametar();
                        kontrolor.KontrolorAL.getInstance().promeniStatusRadniku(radnik);
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }
                      case util.Util.OPERACIJA_VRATI_PROIZVODE_PO_KRITERIJUMU: {
                        ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());
                        List<OpstiDomenskiObjekat> proizvodi = kontrolor.KontrolorAL.getInstance().vratiProizvodePoKriterijumu((Proizvod)kto.getParametar());
                        ServerTransferObjekat sto = new ServerTransferObjekat();
                        sto.setRezultat(proizvodi);
                        sto.setStatus(util.Util.SERVER_STATUS_OPERACIJA_OK);
                        out.writeObject(sto);
                        break;
                    }

                }

            }

        } catch (Exception e) {
          // e.printStackTrace();
            if(radnik!=null){
           radnik.setStatus("ofline");
            kontrolor.KontrolorAL.getInstance().promeniStatusRadniku(radnik);
            }
           
            
        }

    }

    void zaustaviNit() throws IOException {
      kraj = true;
        in.close();
      out.close();
      ks.close();
                              ObjectOutputStream out = new ObjectOutputStream(ks.getOutputStream());

      ServerTransferObjekat sto = new ServerTransferObjekat();
      sto.setRezultat("ugasi_server");
      out.writeObject(sto);
      
      
             
        
    }

}

