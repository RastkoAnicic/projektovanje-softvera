/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Nadin kompjuter
 */
public class SOVratiSveKlijente extends OpstaSO {

    private List<OpstiDomenskiObjekat> lk;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        lk = db.DBBroker.getInstance().vratiSve(odo);
    }

    public List<OpstiDomenskiObjekat> getLk() {
        return lk;
    }

}
