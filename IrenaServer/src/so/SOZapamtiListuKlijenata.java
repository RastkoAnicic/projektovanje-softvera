/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class SOZapamtiListuKlijenata extends OpstaSO {

    List<OpstiDomenskiObjekat> listaKlijenata;

    public SOZapamtiListuKlijenata(List<OpstiDomenskiObjekat> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
    
            db.DBBroker.getInstance().sacuvajListu(listaKlijenata);
    }

}
