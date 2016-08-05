/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import forme.FMGlavna;
import forme.FMLogIn;
import java.io.IOException;
import java.net.Socket;
import kontrolor.Kontrolor;

/**
 *
 * @author Nadin kompjuter
 */
public class Start {

    public static void main(String[] args) throws IOException {

        Socket ss = new Socket("127.0.0.1", 9000);
        Kontrolor.getInstance().getMapa().put(util.Util.MAP_KEY_SOKET, ss);
        FMLogIn glavna = new FMLogIn();
        glavna.setVisible(true);

    }
}
