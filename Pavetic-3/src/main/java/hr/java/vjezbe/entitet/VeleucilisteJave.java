package hr.java.vjezbe.entitet;

/*
Konstruktor klase „VeleucilisteJave“ mora primiti sve ulazne parametre koje prima i
konstruktor klase „ObrazovnaUstanova“ te ih proslijediti tom konstruktoru.

Potrebno je implementirati metodu „izracunajKonacnuOcjenuStudijaZaStudenta“
koja izračunava konačnu ocjenu prema izrazu:
„konačna ocjena = (2 * prosjek ocjena studenta + ocjena završnog rada + ocjena obrane završnog rada) / 4“.

Na kraju je još potrebno implementirati metodu „odrediNajuspjesnijegStudentaNaGodini“ korištenjem
„default“ metoda iz sučelja „Visokoskolska“: „filtrirajIspitePoStudentu“ i „odrediProsjekOcjenaNaIspitima“ na način
da se student odabere student s najboljim prosjekom.
Ako više studenata ima isti najveći prosjek, onda je potrebno vratiti objekt klase „Student“ koji
je posljednji po redu unutar polja koje sadrži objekte klase „Student“.
*/

import java.math.BigDecimal;

public class VeleucilisteJave extends ObrazovnaUstanova implements VisokoSkolska{

    @Override
    public boolean isDiplomski() {
        return false;
    }

    public VeleucilisteJave(Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(predmeti, profesori, studenti, ispiti);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaPismeniDio, int ocjenaObrane) {
        //„konačna ocjena = (2 * prosjek ocjena studenta + ocjena završnog rada + ocjena obrane završnog rada) / 4“.
        BigDecimal prosjekIspita = BigDecimal.valueOf(0);
        BigDecimal ocjena = BigDecimal.valueOf(0);
        for(int i=0; i<ispiti.length; i++){
            prosjekIspita.add(BigDecimal.valueOf(ispiti[i].getOcjena()));
        }
        prosjekIspita.divide(BigDecimal.valueOf(ispiti.length));
        prosjekIspita.multiply(BigDecimal.valueOf(2));

        ocjena.add(prosjekIspita.add(BigDecimal.valueOf(ocjenaObrane+ocjenaPismeniDio)));
        ocjena.divide(BigDecimal.valueOf(4));

        return ocjena;
    }

    public Student odrediNajuspjesnijegStudentaGodine(Student[] studenti, Ispit[] ispiti) {
        //iz sučelja „Visokoskolska“: „filtrirajIspitePoStudentu“ i „odrediProsjekOcjenaNaIspitima“
        Student najuspjesniji = studenti[0]; //inicijalno je najuspjesniji onaj koji je prvi u nizu
        BigDecimal najboljiProsjek = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(ispiti, najuspjesniji));
        for(int i=1; i<studenti.length-1; i++){
            if(odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(ispiti, studenti[i])).compareTo(najboljiProsjek) != (-1)){
                //gornji uvjet vraca 1 ako je trenutni student ostvario veci prosjek ocjena
                najuspjesniji = studenti[i];//moguca greska zbog ovakvog dodijeljivanje vrijednosti
                //moguce je da ces morat radit new Student(...) sa "..." kao parametrima konstruktora
                najboljiProsjek = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(ispiti, najuspjesniji));
            }
        }

        return najuspjesniji;
    }
}
