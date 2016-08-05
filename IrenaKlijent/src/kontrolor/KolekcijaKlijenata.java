/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolor;

import domen.Klijent;
import domen.Opstina;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class KolekcijaKlijenata {
    
  private  List<Klijent> listaKlijenata;

    public KolekcijaKlijenata() {
    }
    

    public List<Klijent> vratiListuKlijenata(){
        return listaKlijenata;
    }
    
    public void dodajKlijenta(Klijent klijent){
        listaKlijenata.add(klijent);
    }
    /* public void izbaciKlijenta(String klijentID){
    for (int i = 0; i < listaKlijenata.size(); i++) {
    if(listaKlijenata.get(i).getKlijentID()==klijentID){
    listaKlijenata.remove(1);
    }
    }
    }*/
    
}
