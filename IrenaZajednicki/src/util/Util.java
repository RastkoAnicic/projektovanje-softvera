/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domen.Radnik;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Nadin kompjuter
 */
public class Util {


    private Properties properties;
    private static Util instance;

    public static final int SERVER_PORT = 9000;

    public static final int UNOS_KLIJENTA = 1;
    public static final int IZMENA_KLIJENTA = 2;
    public static final int DETALJI_KLIJENTA = 3;

    public static final int POPUNI_ZA_RACUN = 4;
    public static final int POPUNI_ZA_KLIJENTA = 5;

    public static final Radnik ULOGOVAN_RADNIK = null;

    public static final int OPERACIJA_VRATI_SVE_KLIJENTE = 200;
    public static final int OPERACIJA_IZMENI_KLIJENTA = 201;
    public static final int OPERACIJA_VRATI_NAJVECI_ID_KLIJENTA = 202;
    public static final int OPERACIJA_POSTAVI_STATUS_KLIJENTA_NA_NEAKTIVAN = 203;
    public static final int OPERACIJA_SACUVAJ_LISTU_KLIJENATA = 204;
    public static final int OPERACIJA_PROVERA_LOGOVANJA = 205;
    public static final int OPERACIJA_VRATI_SVE_OPSTINE = 100;
    public static final int OPERACIJA_DODAJ_OPSTINU = 101;
    public static final int OPERACIJA_VRATI_SVE_PROIZVODE = 300;
    public static final int OPERACIJA_VRATI_PROIZVODE_PO_KRITERIJUMU = 301;
    public static final int OPERACIJA_VRATI_SVE_USLUGE = 400;
    public static final int OPERACIJA_VRATI_TIPOVE_USLUGA = 401;
    public static final int OPERACIJA_SACUVAJ_RACUN = 1;
    public static final int OPERACIJA_VRATI_STAVKE_RACUNE_ZA_RACUNE = 2;
    public static final int OPERACIJA_VRATI_RACUNE_ZA_KLIJENTA = 3;
    public static final int OPERACIJA_VRATI_RACUNE_PO_ZADATIM_KRITERIJUMIMA = 4;
    public static final int OPERACIJA_VRATI_LISTU_RADNIKA = 500;
    public static final int OPERACIJA_PROMENI_STATUS_RADNIKU = 600;

    public static final int SERVER_STATUS_OPERACIJA_OK = 1000;
    public static final int SERVER_STATUS_OPERACIJA_NOT_OK = 1001;

    public static final String USLOV_KLIJENT = "k1";
    public static final String USLOV_RADNIK = "k2";
    public static final String USLOV_GODINA = "k3";
    public static final String USLOV_MESEC = "k4";
    public static final String USLOV_DAN = "k5";
    public static final String USLOV_UKUPNA_VREDNOST = "k6";
    public static final String USLOV_PRETRAGA_PROIZVODA = "p1";
    public static final String USLOV_UCITAJ_KLIJENTA = "r1";
    public static final String USLOV_UCITAJ_PROIZVOD = "p";
    public static final String USLOV_IZMENI_KLIJENTA = "k7";
    public static final String USLOV_DOPUNI_STAVKE = "ds";
        public static final String USLOV_PRETRAGA = "pretraga";
    

//    public static final int OPERACIJA_VRATI_RACUNE_ZA_KLIJENTA = 3;
//    public static final int OPERACIJA_VRATI_RACUNE_PO_ZADATIM_KRITERIJUMIMA = 4;
//    public static final int OPERACIJA_VRATI_LISTU_RADNIKA = 500;
//    public static final int OPERACIJA_PROMENI_STATUS_RADNIKU = 600;
    public static final String MAP_KEY_SOKET = "soket";

    private Util() throws FileNotFoundException, IOException {
        properties = new Properties();
        properties.load(new FileInputStream("db.properties"));
    }

    public static Util getInstance() throws IOException {
        if (instance == null) {
            instance = new Util();
        }
        return instance;

    }

    public String getDriver() {
        return properties.getProperty(properties.getProperty("current_db") + "_driver");
    }

    public String getUrl() {
        return properties.getProperty(properties.getProperty("current_db") + "_url");
    }

    public String getUser() {
        return properties.getProperty(properties.getProperty("current_db") + "_user");
    }

    public String getPassword() {
        return properties.getProperty(properties.getProperty("current_db") + "_password");
    }

    public void setCurrent_db(String value) {
        properties.setProperty("current_db", value);
    }
}
