package hr.java.vjezbe.entitet;

public class Profesor extends Osoba{
    private String sifra;
    private String titula;

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getIme(){
        return super.getIme();
    }
    public void setIme(String ime){
        super.setIme(ime);
    }

    public String getPrezime(){
        return super.getPrezime();
    }
    public void setPrezime(String prezime){
        super.setPrezime(prezime);
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public static class Builder{
        private String ime;
        private String prezime;
        private String sifra;
        private String titula;

        public Builder (String ime, String prezime){
            this.ime = ime;
            this.prezime = prezime;
        }

        public Builder imaSifru(String sifra){
            this.sifra=sifra;
            return this;
        }

        public Builder nosiTitulu(String titula){
            this.titula=titula;
            return this;
        }

        public Profesor build(){
            Profesor profesor = new Profesor(ime, prezime);

            profesor.sifra = this.sifra;
            profesor.titula = this.titula;

            return profesor;
        }
    }

    private Profesor(String ime, String prezime){
        super(ime, prezime);
    }

}
