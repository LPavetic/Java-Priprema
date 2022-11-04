package hr.java.vjezbe.entitet;

//moze ga implementirati samo klasa Ispit
//metoda koja prima naziv softvera(kojim se koristi na online ispitu)
//u mainu napravi provjeru je li ispit online pa sukladno tome uredi return/konstruktor klase ispit

public sealed interface Online permits Ispit{
    public void onlineProvjera(String nazivZgrade, String nazivDvorane);
}
