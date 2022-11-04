package hr.java.vjezbe.entitet;
/*
Metodu „izracunajKonacnuOcjenuStudijaZaStudenta“ implementirati na način da se koristi izraz
„konačna ocjena = (3 * prosjek ocjena studenta + ocjena diplomskog rada + ocjena obrane diplomskog rada) / 5“.
Kod te implementacije je umjesto „završni rad“
potrebno koristiti termin „diplomski rad“ što se tiče imenovanja ulaznih parametara
metode.

Metoda „odrediNajuspješnijegStudentaNaGodini“ mora vratiti studenta
koji ima najviše ispita ocijenjenih ocjenom „izvrstan“.
Ako takvih studenata ima više, onda prednost mora imati onaj student s manjim
indeksom unutar polja koje sadrži objekte klase „Student“.

Metodu „odrediStudentaZaRektorovuNagradu“ implementirati na način da se odredi student
s najvišim prosjekom.
Ako takvih studenata ima više, onda je potrebno koristiti odabrati najmlađeg studenta među
njima.
*/


import java.math.BigDecimal;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

    @Override
    public boolean isDiplomski() {
        return true;
    }

    public FakultetRacunarstva(Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(predmeti, profesori, studenti, ispiti);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaPismeniDio, int ocjenaObraneDiplomskog) {
        //„konačna ocjena = (3 * prosjek ocjena studenta + ocjena diplomskog rada + ocjena obrane diplomskog rada) / 5“
        BigDecimal ocjena = BigDecimal.valueOf(0);
        BigDecimal prosjekIspita = BigDecimal.valueOf(0);
        for(int i=0; i<ispiti.length; i++){
            prosjekIspita.add(BigDecimal.valueOf(ispiti[i].getOcjena()));
        }
        prosjekIspita.divide(BigDecimal.valueOf(ispiti.length));
        prosjekIspita.multiply(BigDecimal.valueOf(3));

        ocjena.add(prosjekIspita.add(BigDecimal.valueOf(ocjenaObraneDiplomskog+ocjenaPismeniDio)));
        ocjena.divide(BigDecimal.valueOf(5));

        return ocjena;
    }

    @Override
    public Student odrediNajuspjesnijegStudentaGodine(Student[] studenti, Ispit[] ispiti) {
        Student najuspjesniji = studenti[0]; //inicijalno je najuspjesniji onaj koji je prvi u nizu
        int najviseIzvrsnih = 0;
        //postavljanje varijable najviseIzvrsnih na vrijednost broja izvrsnih ocjena prvog studenta ovdje
        for(int i=1; i<studenti.length-1; i++){
            Ispit[] ispitistudenta = filtrirajIspitePoStudentu(ispiti, studenti[i]);
            int brIzvrsnih = 0;

            for(int j=0; i<ispitistudenta.length; j++)
                if(ispitistudenta[j].getOcjena() == 5)
                    brIzvrsnih++;

            if(brIzvrsnih>najviseIzvrsnih){
                najviseIzvrsnih = brIzvrsnih;
                najuspjesniji = studenti[i];//moguca greska zbog nacina dodijeljivanja vrijednosti
            }
        }
        return najuspjesniji;
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {
        Student[] studenti = super.getStudenti();
        Ispit[] ispiti = super.getIspiti();
        //moguca greska, mozda ces trebat inicijalizirat svakog od njih preko konstruktora pomocu for petlje
        Student pobjednik = studenti[0];//moguca greska, teska klasika
        BigDecimal najveciProsjek = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(ispiti, studenti[0]));
        for(int i=0; i<studenti.length; i++){
            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(ispiti, studenti[i]);
            //moguca greska, mozda ce biti potrebno koristiti .Arrays.copyOf()
            if(odrediProsjekOcjenaNaIspitima(ispitiStudenta).compareTo(najveciProsjek) != (-1) &&
            studenti[i].getDatumRodjenja().compareTo(pobjednik.getDatumRodjenja()) < 0){
                pobjednik=studenti[i];
                najveciProsjek = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
            }
        }

        return pobjednik;
    }
}
