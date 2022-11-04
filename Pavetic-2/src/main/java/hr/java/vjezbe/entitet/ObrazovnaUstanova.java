package hr.java.vjezbe.entitet;

/*  koja sadrži naziv ustanove (tipa „String“), polje predmeta,
    profesora, studenata i ispita. Konstruktor klase mora primati sva četiri polja i spremati
    ih u privatne varijable. Također je potrebno generirati „getter“ i „setter“ metode za
    naziv obrazovne ustanove i svako od polja.

    Klasa „ObrazovnaUstanova“ mora također sadržavati i jednu apstraktnu metodu
    „odrediNajuspjesnijegStudentaNaGodini“ koja prima cjelobrojni broj koji označava
    godinu te vraća objekt klase „Student“.
*/

import java.math.BigDecimal;

public abstract class ObrazovnaUstanova {
    private String naziv = "NAZIV_OBRAZOVNE_USTANOVE_OVDJE";
    private Predmet[] predmeti;
    private Profesor[] profesori;
    private Student[] studenti;
    private Ispit[] ispiti;

    abstract public boolean isDiplomski();

    public abstract BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaPismeniDio, int ocjenaObraneDiplomskog);

    public abstract Student odrediNajuspjesnijegStudentaGodine(Student[] studenti, Ispit[] ispiti);

    public ObrazovnaUstanova(Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        this.predmeti = predmeti;
        this.profesori = profesori;
        this.studenti = studenti;
        this.ispiti = ispiti;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Predmet[] getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(Predmet[] predmeti) {
        this.predmeti = predmeti;
    }

    public Profesor[] getProfesori() {
        return profesori;
    }

    public void setProfesori(Profesor[] profesori) {
        this.profesori = profesori;
    }

    public Student[] getStudenti() {
        return studenti;
    }

    public void setStudenti(Student[] studenti) {
        this.studenti = studenti;
    }

    public Ispit[] getIspiti() {
        return ispiti;
    }

    public void setIspiti(Ispit[] ispiti) {
        this.ispiti = ispiti;
    }
}
