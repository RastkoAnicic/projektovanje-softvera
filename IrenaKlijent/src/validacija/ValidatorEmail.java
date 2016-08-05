/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nadin kompjuter
 */
public class ValidatorEmail implements IValidator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void izvrsiValidaciju(String text) throws Exception {

        if (text == null || text.isEmpty()) {
            throw new Exception("Ovo polje je obavezno");
        }
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            throw new Exception("E-mail nije unet u odgovarajucem formatu");
        }
    }

}
