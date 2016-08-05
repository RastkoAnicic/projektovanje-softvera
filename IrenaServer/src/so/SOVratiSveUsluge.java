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
public class SOVratiSveUsluge extends OpstaSO{
     private List<OpstiDomenskiObjekat> lu;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        lu = db.DBBroker.getInstance().vratiSve(odo);
    }

    public List<OpstiDomenskiObjekat> getLu() {
        return lu;
    }
}
