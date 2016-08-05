/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author Nadin kompjuter
 */
public abstract class OpstaSO {

    public void izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            proveriPreduslov(odo);
            izvrisiKonkretnuOperaciju(odo);
            potvrdi();
        } catch (Exception ex) {
            ponisti();
            ex.printStackTrace();
            throw new Exception("Greska kod izvrsenja so" + ex.getMessage());
        }
    }

    public void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {

    }

    protected abstract void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception;

    private void potvrdi() throws Exception {
        DBBroker.getInstance().potvrdiTaransakciju();
    }

    private void ponisti() throws Exception {
        DBBroker.getInstance().ponistiTaransakciju();
    }
}
