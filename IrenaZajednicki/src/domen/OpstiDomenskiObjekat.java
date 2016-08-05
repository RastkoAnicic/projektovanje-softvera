/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nadin kompjuter
 */
public abstract class OpstiDomenskiObjekat implements Serializable {

    List<OpstiDomenskiObjekat> vezani_objekti;
    Map<String, Object> mapaUslov;

    public OpstiDomenskiObjekat() {

    }
    

    public Map<String, Object> getMapaUslov() {
        return mapaUslov;
    }

    public void setMapaUslov(Map<String, Object> mapaUslov) {
        this.mapaUslov = mapaUslov;
    }
    

    public abstract String vratiNazivTabele();

    public abstract String vratiVrednostiZaInsert();

    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        return null;
    }

    public int vratiBrojVezanihObjekata() {
        vezani_objekti = new LinkedList<>();
        return 0;
    }

    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return null;
    }

    public List<OpstiDomenskiObjekat> dopuniListu(List<OpstiDomenskiObjekat> lista, ResultSet rsVezObj, OpstiDomenskiObjekat vezObj) {
        return null;
    }

    

    public void postaviListuSlabih(List<OpstiDomenskiObjekat> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int vratiBrojSlabihObjekata() {
        return 0;
    }

    public int vratiBrojSlogovaSlabog(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OpstiDomenskiObjekat vratiSlogVezanogObjekata(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String vratiUslov() {
        return "";
    }

}
