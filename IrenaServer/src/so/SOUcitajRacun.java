/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Klijent;
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
public class SOUcitajRacun extends OpstaSO {

    private OpstiDomenskiObjekat jakObjekat;
    private OpstiDomenskiObjekat racunSaStavkama;
        Map<String,Object> mapaUslova;


    public SOUcitajRacun(OpstiDomenskiObjekat jakObjekat) {
        this.jakObjekat = jakObjekat;
    }

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat slabObj) throws Exception {
                     mapaUslova =  kreirajKriterijumPretrage(jakObjekat);
    slabObj.setMapaUslov(mapaUslova);

        racunSaStavkama = db.DBBroker.getInstance().dopuniJakObjekatStavkama(jakObjekat, slabObj);
        
    }

    public OpstiDomenskiObjekat getRacunSaStavkama() {
        return racunSaStavkama;
    }
     private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
          Racun r = (Racun)odo;
         mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_DOPUNI_STAVKE, r.getRacunID());

        return mapaUslova;
    }

}
