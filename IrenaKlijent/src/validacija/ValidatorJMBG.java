/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

import domen.Klijent;
import java.util.List;
import kontrolor.Kontrolor;

/**
 *
 * @author Nadin kompjuter
 */
public class ValidatorJMBG implements IValidator{
    
    @Override
    public void izvrsiValidaciju(String text) throws Exception{
        if (text == null || text.isEmpty()) {
            throw new Exception("Ovo polje je obavezno");
        }
        for (int i = 0; i < text.length(); i++) {
           if(!Character.isDigit(text.charAt(i))){
                   throw new Exception("Na poziciji "+(i+1)+"nije cifra");
           }
        }
        if(text.length()!=13){
                  throw new Exception("JMBG mora da sadrzi 13 cifara");
        }
        List<Klijent> lista = Kontrolor.getInstance().vratiListuKlijenata();
        for (Klijent kl : lista) {
            if(text.equals(kl.getJmbg())){
                   throw new Exception("Uneti JMBG vec postoji!");
            }
        }
        
    }
}
