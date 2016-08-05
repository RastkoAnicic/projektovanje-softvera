/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import domen.Radnik;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

/**
 *
 * @author Nadin kompjuter
 */
public class SONadjiRadnika extends OpstaSO {

    private Radnik radnik;
    Map<String, Object> mapaUslova;

    @Override
    protected void izvrisiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        mapaUslova = kreirajKriterijumPretrage(odo);
        odo.setMapaUslov(mapaUslova);
        List<OpstiDomenskiObjekat> lista = db.DBBroker.getInstance().vratiSve(odo);
       if(lista.size()==0){
       radnik = null;
       }
       else{
        radnik = (Radnik) lista.get(0);}
    }

    public Radnik getRadnik() {
        return radnik;
    }

    private Map<String, Object> kreirajKriterijumPretrage(OpstiDomenskiObjekat odo) {
        mapaUslova = new HashMap<>();
        mapaUslova.put(Util.USLOV_PRETRAGA, 1);

        return mapaUslova;
    }

}
