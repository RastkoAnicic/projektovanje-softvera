/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Racun;

/**
 *
 * @author Nadin kompjuter
 */
public class SOZapamtiRacun extends OpstaSO {

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        db.DBBroker.getInstance().sacuvaj(odo);

    }

    @Override
    public void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
       Racun racun = (Racun) odo;
       if(!racun.getKlijent().isAktivan()){
       throw new Exception("Nije moguce kreirati racun za deaktiviranog klijenta");
       }
        
    }
    

}
