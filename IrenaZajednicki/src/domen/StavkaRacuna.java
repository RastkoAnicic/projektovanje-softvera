/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static util.Util.USLOV_DOPUNI_STAVKE;

/**
 *
 * @author Nadin kompjuter
 */
public class StavkaRacuna extends OpstiDomenskiObjekat {

    private int redniBroj;
    private UP uslugaProizvod;
    private int kolicina;
    private double cena;
    private double iznos;
    private String racunID;

    public StavkaRacuna() {
    }

    public StavkaRacuna(int redniBroj, UP uslugaProizvod, int kolicina, double cena, double iznos, String racunID) {
        this.redniBroj = redniBroj;
        this.uslugaProizvod = uslugaProizvod;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.racunID = racunID;
    }

  
    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public UP getUslugaProizvod() {
        return uslugaProizvod;
    }

    public void setUslugaProizvod(UP uslugaProizvod) {
        this.uslugaProizvod = uslugaProizvod;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaracuna";
    }

    @Override
    public String vratiVrednostiZaInsert() {
                System.out.println("stavka racuna 1:"+uslugaProizvod + "RacunID"+racunID);

        
          if (uslugaProizvod instanceof Usluga){
        return "(" + redniBroj + ",'" + racunID + "','" + uslugaProizvod.uslugaProzivodID + "', null ," +kolicina + "," +cena + ","+iznos+")";
          }
           else{
        return "(" + redniBroj + ",'" + racunID + "',null,'" + uslugaProizvod.uslugaProzivodID + "'," +kolicina + "," +cena + ","+iznos+")";
          }
          
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        vezani_objekti = new LinkedList<>();
        vezani_objekti.add(new Usluga());
        vezani_objekti.add(new Proizvod());
        return vezani_objekti.size();
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return vezani_objekti.get(i);
    }


    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lo = new ArrayList<>();
        try {
            while (rs.next()) {
                StavkaRacuna sr = new StavkaRacuna();
                sr.setCena(rs.getDouble("Cena"));
                sr.setIznos(rs.getDouble("Iznos"));
                sr.setKolicina(rs.getInt("Kolicina"));
                sr.setRedniBroj(rs.getInt("RedniBroj"));

                UP up;

                if (rs.getString("UslugaID") == null) {
                    Proizvod p = new Proizvod();
                    p.setUslugaProzivodID(rs.getString("ProizvodID"));
                    up = p;
                } else {
                    Usluga u = new Usluga();
                    u.setUslugaProzivodID(rs.getString("UslugaID"));
                    up = u;
                }
                sr.setUslugaProizvod(up);
                lo.add(sr);
            }
        } catch (SQLException ex) {
        }
        return lo;

    }

    @Override
    public List<OpstiDomenskiObjekat> dopuniListu(List<OpstiDomenskiObjekat> lista, ResultSet rsVezObj, OpstiDomenskiObjekat vezObj) {
        List<OpstiDomenskiObjekat> listaDO = new ArrayList<>();
        try {
            if (vezObj instanceof Usluga) {

                while (rsVezObj.next()) {
                    Usluga u = new Usluga();
                    u.setCena(rsVezObj.getDouble("Cena"));
                    u.setNaziv(rsVezObj.getString("Naziv"));
                    u.setOpis(rsVezObj.getString("Opis"));
                    u.setUslugaProzivodID(rsVezObj.getString("UslugaID"));
                    System.out.println("usluga: "+u.getNaziv());
                    listaDO.add(u);
                }
                for (OpstiDomenskiObjekat stavka : lista) {
                    for (OpstiDomenskiObjekat usluga : listaDO) {
                        StavkaRacuna st = (StavkaRacuna) stavka;
                        Usluga usl = (Usluga) usluga;
                        if (st.getUslugaProizvod() instanceof Usluga) {
                            if (((Usluga) st.getUslugaProizvod()).getUslugaProzivodID().equals(usl.getUslugaProzivodID())) {
                                st.setUslugaProizvod(usl);
                                break;
                            }
                        }
                    }
                }
            }
            if (vezObj instanceof Proizvod) {

                while (rsVezObj.next()) {
                    Proizvod p = new Proizvod();
                    p.setCena(rsVezObj.getDouble("Cena"));
                    p.setNaziv(rsVezObj.getString("Naziv"));
                    p.setRaspolozivaKolicina(rsVezObj.getInt("RaspolozivaKolicina"));
                    p.setUslugaProzivodID(rsVezObj.getString("ProizvodID"));
                    
                    System.out.println("proizvod"+p.getNaziv());
                    listaDO.add(p);
                }
                for (OpstiDomenskiObjekat stavka : lista) {
                    for (OpstiDomenskiObjekat proizvod : listaDO) {
                        StavkaRacuna st = (StavkaRacuna) stavka;
                        Proizvod pro = (Proizvod) proizvod;
                        if (st.getUslugaProizvod() instanceof Proizvod) {

                            if (((Proizvod) st.getUslugaProizvod()).getUslugaProzivodID().equals(pro.getUslugaProzivodID())) {
                                st.setUslugaProizvod(pro);
                                break;
                            }
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;

    }

    public String getRacunID() {
        return racunID;
    }

    public void setRacunID(String racunID) {
        this.racunID = racunID;
    }

    @Override
    public String vratiUslov() {
       return " WHERE racunID ='" +  mapaUslov.get(USLOV_DOPUNI_STAVKE)+"'";        
    }

}
