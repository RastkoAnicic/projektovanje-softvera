/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.OpstiDomenskiObjekat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;

/**
 *
 * @author Nadin kompjuter
 */
public class DBBroker {

    Connection connection;

    private static DBBroker instance;

    private DBBroker() throws Exception {
        konektujSeNaBazu();
    }

    public void konektujSeNaBazu() throws Exception {
        Class.forName(Util.getInstance().getDriver());
        String url = Util.getInstance().getUrl();
        String user = Util.getInstance().getUser();
        String password = Util.getInstance().getPassword();
        connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        System.out.println("Uspesna konekcija");
    }

    public static DBBroker getInstance() throws Exception {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void potvrdiTaransakciju() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ponistiTaransakciju() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sacuvaj(OpstiDomenskiObjekat odo) throws SQLException {

        String upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES " + odo.vratiVrednostiZaInsert();
        System.out.println(upit);
        Statement st = connection.createStatement();
        st.executeUpdate(upit);

        for (int i = 0; i < odo.vratiBrojSlabihObjekata(); i++) {
            OpstiDomenskiObjekat slab;
            for (int j = 0; j < odo.vratiBrojSlogovaSlabog(i); j++) {
                slab = odo.vratiSlogVezanogObjekata(i, j);
                upit = "INSERT INTO " + slab.vratiNazivTabele() + " VALUES " + slab.vratiVrednostiZaInsert();
                System.out.println(upit);
                st.executeUpdate(upit);
            }
        }
    }

    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws Exception {

        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + odo.vratiUslov();
        System.out.println("Upit vrati sve: "+upit);
        Statement st = connection.createStatement();
        ResultSet rsOdo = st.executeQuery(upit);
        ResultSet rsVezObj = null;
        List<OpstiDomenskiObjekat> lista = odo.vratiListu(rsOdo);

        if (odo.vratiBrojVezanihObjekata() == 0) {
            return lista;
        }
        for (int i = 0; i < odo.vratiBrojVezanihObjekata(); i++) {
            OpstiDomenskiObjekat vezObj = odo.vratiVezaniObjekat(i);
            upit = "SELECT * FROM " + vezObj.vratiNazivTabele();
            System.out.println("upit iz petlje"+vezObj.vratiNazivTabele());
            rsVezObj = st.executeQuery(upit);
            lista = odo.dopuniListu(lista, rsVezObj, vezObj);

        }
        return lista;

    }

    public OpstiDomenskiObjekat dopuniJakObjekatStavkama(OpstiDomenskiObjekat odoJak, OpstiDomenskiObjekat odoSlab) throws Exception {

        try {
            String upit = "SELECT * FROM " + odoSlab.vratiNazivTabele() + odoSlab.vratiUslov();
            System.out.println(upit);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(upit);
            ResultSet rsVezObj = null;
            List<OpstiDomenskiObjekat> lista = odoSlab.vratiListu(rs);

            if (odoSlab.vratiBrojVezanihObjekata() == 0) {
                //return lista;
            }
            for (int i = 0; i < odoSlab.vratiBrojVezanihObjekata(); i++) {
                OpstiDomenskiObjekat vezObj = odoSlab.vratiVezaniObjekat(i);
                upit = "SELECT * FROM " + vezObj.vratiNazivTabele();
                rsVezObj = st.executeQuery(upit);
                lista = odoSlab.dopuniListu(lista, rsVezObj, vezObj);

            }
            odoJak.postaviListuSlabih(lista);
            return odoJak;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

    }


    public void sacuvajListu(List<OpstiDomenskiObjekat> listaODO) throws SQLException {
        for (OpstiDomenskiObjekat odo : listaODO) {

            String upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES " + odo.vratiVrednostiZaInsert();
            System.out.println(upit);
            Statement st = connection.createStatement();
            st.executeUpdate(upit);
        }

    }

    public void izmeni(OpstiDomenskiObjekat odo) throws SQLException {

        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiUslov();
        System.out.println(upit);
        Statement st = connection.createStatement();
        st.executeUpdate(upit);

    }

  
}
