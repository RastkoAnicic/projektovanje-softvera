/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Nadin kompjuter
 */
public class SOZapamtiOpstinu extends OpstaSO{

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
              db.DBBroker.getInstance().sacuvaj(odo);
        
    }
    
}
