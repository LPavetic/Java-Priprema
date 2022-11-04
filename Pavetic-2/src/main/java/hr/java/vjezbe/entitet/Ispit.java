package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//u mainu napravi provjeru je li ispit online pa sukladno tome uredi return/konstruktor klase ispit


public final class Ispit implements Online{
    private Predmet predmet;
    private Student student;
    private Integer ocjena;
    private LocalDateTime datum_i_vrijeme;
    private String nazivSoftvera;
    private boolean isOnline;

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public record Dvorana(String naziv, String zgrada) {}
    Dvorana dvorana;

    @Override
    public void onlineProvjera(String nazivZgrade, String nazivDvorane) {
        if(nazivZgrade.equals("ONLINE")){
            isOnline = true;
            nazivSoftvera = nazivDvorane;
        }
        else{
            isOnline = false;
            dvorana = new Dvorana(nazivDvorane, nazivZgrade);
        }
    }


    public Ispit(Predmet predmet, Student student, Integer ocjena, LocalDateTime datum_i_vrijeme, String nazivDvorane, String nazivZgrade) {
        this.predmet = predmet;
        this.student = student;
        this.ocjena = ocjena;
        //DateTimeFormatter formaterDatum = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH:mm");
        //this.datum_i_vrijeme = LocalDateTime.parse(unos_datuma_i_vremena, formaterDatum);
        this.datum_i_vrijeme = datum_i_vrijeme;
        onlineProvjera(nazivZgrade, nazivDvorane);
    }

    public Ispit(Predmet predmet, Student student, Integer ocjena) {
        this.predmet = predmet;
        this.student = student;
        this.ocjena = ocjena;
        //DateTimeFormatter formaterDatum = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH:mm");
        //this.datum_i_vrijeme = LocalDateTime.parse(unos_datuma_i_vremena, formaterDatum);
        //this.datum_i_vrijeme = datum_i_vrijeme;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    public LocalDateTime getDatum_i_vrijeme() {
        return datum_i_vrijeme;
    }

    public void setDatum_i_vrijeme(LocalDateTime datum_i_vrijeme) {
        this.datum_i_vrijeme = datum_i_vrijeme;
    }
}
