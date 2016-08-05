/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Radnik;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadin kompjuter
 */
public class StartServer extends Thread {

   static Socket s;
    private static boolean kraj;

    
    public StartServer() {
        kraj = false;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(util.Util.SERVER_PORT);
            System.out.println("Server se pokrenuo");
            while (!kraj) {
                System.out.println("Cekam klijenta");
                s = ss.accept();
                System.out.println("Klijent se povezao");

                NitKlijent nit = new NitKlijent(s);
                nit.start();

            }
        } catch (IOException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
           
        }

    }
    
    public static void zaustaviNit() throws IOException{
    kraj = true;
    s.close();
    
    
    }

}
