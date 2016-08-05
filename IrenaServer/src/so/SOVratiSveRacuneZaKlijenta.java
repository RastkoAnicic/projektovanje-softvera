/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Racun;
import java.util.List;
import java.util.Map;
import util.Util;

/**
 *
 * @author Nadin kompjuter
 */
public class SOVratiSveRacuneZaKlijenta extends OpstaSO{
    private List<OpstiDomenskiObjekat> lr;


    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
               lr = db.DBBroker.getInstance().vratiSve(odo);

    }
    public List<OpstiDomenskiObjekat> getLr() {
        return lr;
    }
    
}
