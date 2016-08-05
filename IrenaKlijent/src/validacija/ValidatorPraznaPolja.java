/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

/**
 *
 * @author Nadin kompjuter
 */
public class ValidatorPraznaPolja implements IValidator{
    
   

    @Override
    public void izvrsiValidaciju(String text) throws Exception {
       if (text == null || text.isEmpty()) {
            throw new Exception("Ovo polje je obavezno");
        }
    }
}
