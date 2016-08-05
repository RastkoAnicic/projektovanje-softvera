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
public class SOVratiTipoveUsluga extends OpstaSO{
    
    private List<OpstiDomenskiObjekat> ltu;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ltu = db.DBBroker.getInstance().vratiSve(odo);

    }

    public List<OpstiDomenskiObjekat> getLtu() {
        return ltu;
    }
}
