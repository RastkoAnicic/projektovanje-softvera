/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Racun;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

/**
 *
 * @author Nadin kompjuter
 */
public class SOPretraziRacune extends OpstaSO {

    private List<OpstiDomenskiObjekat> lr;
    Map<String,Object> mapaUslova;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
      mapaUslova =  kreirajKriterijumPretrage(odo);
      odo.setMapaUslov(mapaUslova);
        lr = db.DBBroker.getInstance().vratiSve(odo);

    }

    public List<OpstiDomenskiObjekat> getLr() {
        return lr;
    }

    private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
       Racun racun = (Racun)odo;
       mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_KLIJENT, racun.getKlijent());
      mapaUslova.put(Util.USLOV_RADNIK, racun.getRadnik());
        mapaUslova.put(Util.USLOV_GODINA, racun.getGodina());
        mapaUslova.put(Util.USLOV_MESEC,racun.getMesec());
       mapaUslova.put(Util.USLOV_DAN, racun.getDan());
        mapaUslova.put(Util.USLOV_UKUPNA_VREDNOST, racun.getRasponUkupneVrednosti());

        return mapaUslova;
    }

}
