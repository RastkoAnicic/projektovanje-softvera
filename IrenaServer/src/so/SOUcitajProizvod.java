/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import domen.Radnik;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

/**
 *
 * @author Irena
 */
public class SOUcitajProizvod extends OpstaSO{
private List<OpstiDomenskiObjekat> lp;
    Map<String,Object> mapaUslova;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
      mapaUslova =  kreirajKriterijumPretrage(odo);
    odo.setMapaUslov(mapaUslova);
        lp = db.DBBroker.getInstance().vratiSve(odo);

    }

    public List<OpstiDomenskiObjekat> getLp() {
        return lp;
    }
    
     private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
        mapaUslova = new HashMap<>();
        
        mapaUslova.put(Util.USLOV_UCITAJ_PROIZVOD, ((Proizvod)odo).getUslugaProzivodID());

        return mapaUslova;
    }
}
