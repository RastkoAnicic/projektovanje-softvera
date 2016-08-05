/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import domen.Racun;
import java.util.List;
import java.util.Map;
import util.Util;

/**
 *
 * @author Nadin kompjuter
 */
public class SOPretraziProizvode extends OpstaSO {

    private List<OpstiDomenskiObjekat> lp;
    Map<String,Object> mapaUslova;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
      mapaUslova =  kreirajKriterijumPretrage(odo);

        lp = db.DBBroker.getInstance().vratiSve(odo);

    }

    public List<OpstiDomenskiObjekat> getLp() {
        return lp;
    }
    
     private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
         Proizvod p = (Proizvod)odo;
        p.getMapaUslov().put(Util.USLOV_PRETRAGA_PROIZVODA, p.getNaziv());

        return p.getMapaUslov();
    }
}
