package hr.java.vjezbe.entitet;
import java.math.BigDecimal;
import java.util.Arrays;

/*
Prva metoda bez implementacije mora se zvati
„izracunajKonacnuOcjenuStudijaZaStudenta“ koja prima polje objekata klase
„Ispit“ za studenta, cjelobrojnu vrijednost koja označava ocjenu pismenog dijela
završnog rada za studenta te još jednu cjelobrojnu vrijednost koja označava ocjenu
obrane završnog rada, a vraća objekt „BigDecimal“ koji sadrži vrijednost ocjene
studija za studenta.

Druga metoda „odrediProsjekOcjenaNaIspitima“ mora imati modifikator
„default“, primati polje objekata klase „Ispit“, a vraćati objekt klase
„BigDecimal“ koji će poprimati prosječnu ocjenu na ispitima koji su položeni (s
pozitivnom ocjenom).

Treća metoda „filtrirajPolozeneIspite“ mora imati modifikator
„private“, primati polje objekata klase „Ispit“, a također vraćati polje objekata klase
„Ispit“, ali samo onih koji su ocijenjeni pozitivnom ocjenom.

Četvrta metoda „filtrirajIspitePoStudentu“ mora biti označena modifikatorom „default“, primati
polje objekata klase „Ispit“ i jedan objekt klase „Student“, a vraćati polje objekata
klase „Ispit“ koje sadrži samo one objekte klase „Ispit“ kojima je pristupio zadani
student.
*/



public interface VisokoSkolska {
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaPismeniDio, int ocjenaObrane);
    //gornja funkcija nema implementaciju unutar definicije sucelja

    default public BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti){
        BigDecimal prosjek = BigDecimal.valueOf(0);
        Ispit[] polozeniIspiti = filtrirajPolozeneIspite(ispiti);//moguca greska, vidi redak 48, ovdje radis isto to
        for(int i=0; i<polozeniIspiti.length; i++)
            prosjek.add(BigDecimal.valueOf(polozeniIspiti[i].getOcjena()));
        prosjek.divide(BigDecimal.valueOf(polozeniIspiti.length));
        return prosjek;
    }

    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti){
        Ispit[] polozeniIspiti = new Ispit[0];
        int j=0;
        for(int i=0; i< ispiti.length; i++)
            if(ispiti[i].getOcjena()>2) {
                polozeniIspiti = Arrays.copyOf(polozeniIspiti, ++j);
                polozeniIspiti[(j-1)] = ispiti[i];//moguca greska zbog ovakvog dodijeljivanja vrijednosti
                //ako ne radi ovaj odma gore redak, probaj u konstruktor novog elementa ubacit gettere iz starog niza kao parametre
                //takoder provjeri varijablu j
            }
        return polozeniIspiti;
    }

    default public Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
        Ispit[] odgovarajuciIspiti = new Ispit[0];
        int j=0;
        //provjeri taj j jeli stima
        for (int i=0; i < ispiti.length; i++){
            if(ispiti[i].getStudent().getIme().equals(student.getIme()) &&
                    ispiti[i].getStudent().getPrezime().equals(student.getPrezime())){
                odgovarajuciIspiti = Arrays.copyOf(odgovarajuciIspiti, ++j);
                odgovarajuciIspiti[(j-1)] = ispiti[i];
                //u slucaju greske, vidi redak 48("moguca greska zbog ovakvog dodijeljivanja vrijednosti")
            }
        }
        return odgovarajuciIspiti;
    }
}
