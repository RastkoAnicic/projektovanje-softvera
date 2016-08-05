/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadin kompjuter
 */
public class Radnik extends OpstiDomenskiObjekat {

    private String radnikID;
    private String ime;
    private String prezime;
    private Date datumZaposlenja;
    private String username;
    private String password;
    private String status;

    public Radnik() {
       
    }

    public Radnik(String radnikID, String ime, String prezime, Date datumZaposlenja, String username, String password, String status) {
        this.radnikID = radnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumZaposlenja = datumZaposlenja;
        this.username = username;
        this.password = password;
        this.status = status;
               

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(String radnikID) {
        this.radnikID = radnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Radnik) {
            Radnik r = (Radnik) obj;
            if (getRadnikID().equals(r.getRadnikID())) {
                return true;
            }
            return false;
        }
        return false;

    }

    @Override
    public String vratiNazivTabele() {
        return "radnik";
    }

    @Override
    public String vratiVrednostiZaInsert() {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(this.datumZaposlenja);
        
                return "('" + radnikID + "','" + ime + "','" + prezime + "','" + d + "','" + username + "','" + password + "')";

        
    }

    @Override
    public String vratiUslov() {
        if(mapaUslov==null){
        return "";
        }
        if((Integer)mapaUslov.get(util.Util.USLOV_PRETRAGA)==1){
        return " WHERE Username= '" + username + "' && Lozinka='" + password + "'";
        }
return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista_radnika = new LinkedList<>();
        try {
            while (rs.next()) {
                Radnik r = new Radnik();
                r.setIme(rs.getString("Ime"));
                r.setPrezime(rs.getString("Prezime"));
                r.setPassword(rs.getString("Lozinka"));
                r.setRadnikID(rs.getString("RadnikID"));
                r.setUsername(rs.getString("Username"));
                r.setDatumZaposlenja(new java.util.Date(rs.getDate("datumZaposlenja").getTime()));
                r.setStatus("offline");
                lista_radnika.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista_radnika;

    }

}
