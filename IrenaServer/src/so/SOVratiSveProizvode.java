/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class SOVratiSveProizvode extends OpstaSO {
    private List<OpstiDomenskiObjekat> lp;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        lp = db.DBBroker.getInstance().vratiSve(odo);
    }

    public List<OpstiDomenskiObjekat> getLp() {
        return lp;
    }
    
    

}
