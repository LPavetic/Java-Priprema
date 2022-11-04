package hr.java.vjezbe.glavna;
import hr.java.vjezbe.entitet.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Glavna{

    private static final int MAX_UNOSA = 1;

    //deklaracija metoda za unos
    public static Predmet unosPredmeta(Scanner scanner, Profesor[] profesori){
        //String sifra,
        //String naziv,
        //Integer brojEctsBodova,
        //Profesor nositelj
        System.out.print("Unesite sifru predmeta: ");
        String sifra = scanner.nextLine();
        System.out.print("Unesite naziv predmeta: ");
        String naziv = scanner.nextLine();
        System.out.print("Unesite broj ECTS bodova za predmet " + "'" + naziv + "'");
        Integer brojEctsBodova = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Odaberite profesora:");
        for(int j=0; j < profesori.length; j++){
            System.out.println((j+1) + ". " + profesori[j].getIme() + " " + profesori[j].getPrezime());
        }
        System.out.print("Odabir >> ");
        short odabir = scanner.nextShort();
        scanner.nextLine();
        Profesor nositelj = profesori[odabir-1];
        System.out.print("Unesite broj studenata za predmet " + "'" + naziv + "'");
        boolean prolaz = false;
        do {
            try {
                System.out.print("Unesite ime profesora: ");
                Integer brStudenata=scanner.nextInt();
            }
            catch (InputMismatchException ex){
                System.out.println("Unesite ponovno!");
            }
        }while(!prolaz);
        scanner.nextLine();
        return new Predmet(sifra, naziv, brojEctsBodova, nositelj);
    }
    public static Profesor unosProfesora(Scanner scanner){
        System.out.print("Unesite sifru profesora: ");
        String sifra = scanner.nextLine();
        String ime = scanner.nextLine();
        System.out.print("Unesite prezime profesora: ");
        String prezime = scanner.nextLine();
        System.out.print("Unesite titulu profesora: ");
        String titula = scanner.nextLine();

        //System.out.println("Uneseno: " + ime + " " + prezime + " " + titula + " " + sifra);
        System.out.println();

        return new Profesor.Builder(ime, prezime).imaSifru(sifra).nosiTitulu(titula).build();
    }
    public static Student unosStudenta(Scanner scanner){
//        private String ime;
//        private String prezime;
//        private String jmbag;
        System.out.print("Unesite ime studenta: ");
        String ime = scanner.nextLine();
        System.out.print("Unesite prezime studenta: ");
        String prezime = scanner.nextLine();
        System.out.print("Unesite datum rođenja studenta " + ime + " " + prezime + " u formatu (dd.MM.yyyy.): ");
        String datumRodjenja = scanner.nextLine();
        DateTimeFormatter formaterDatum = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate datum = LocalDate.parse(datumRodjenja, formaterDatum);
        System.out.print("Unesite JMBAG studenta: " + ime + " " + prezime + ":");
        String jmbag = scanner.nextLine();
        return new Student(ime, prezime, jmbag, datum);
    }
    public static Ispit unosRoka(Scanner scanner, Predmet[] predmeti, Student[] studenti){
//        private Predmet predmet;
//        private Student student;
//        private Integer ocjena;
//        private LocalDateTime datum_i_vrijeme;
//        public record Dvorana(String naziv, String dvorana)
        System.out.println("Odaberite predmet:");
        for(int i=0; i<predmeti.length; i++){
            System.out.println((i+1) + ". " + predmeti[i].getNaziv());
        }
        System.out.print("Odabir >> ");
        short odabirPredmeta = scanner.nextShort();
        Predmet predmet = predmeti[odabirPredmeta-1];

        System.out.print("Unesite naziv zgrade, ako je ispit online upisati 'ONLINE': ");
        String nazivZgrade = scanner.nextLine();

        System.out.print("Unesite naziv dvorane: ");
        String nazivDvorane = scanner.nextLine();

        System.out.println("Odaberite studenta:");
        for(int i=0; i< studenti.length; i++){
            System.out.println((i+1) + ". " + studenti[i].getIme() + " " + studenti[i].getPrezime());
        }
        System.out.print("Odabir >> ");
        short odabirStudenta = scanner.nextShort();
        scanner.nextLine();
        Student student = studenti[odabirStudenta-1];

        //Objekte klase „Student“ koji su pristupili ispitima iz određenih
        //predmeta treba dodati u polje studenata za taj određeni predmet
        predmeti[odabirPredmeta-1].dodajStudenta(student);

        System.out.print("Unesite ocjenu na ispitu (1-5): ");
        Integer ocjena = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm): ");
        String datumIVrijemeUnos = scanner.nextLine();
        DateTimeFormatter formatDatumaIVremena = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH:mm");
        //LocalDateTime datumIVrijeme = DateTimeFormatterBuilder.appendPattern(datumIVrijemeUnos);
        LocalDateTime datumIVrijeme = LocalDateTime.parse(datumIVrijemeUnos, formatDatumaIVremena);

        return new Ispit(predmet, student, ocjena, datumIVrijeme, nazivDvorane, nazivZgrade);
//        String unos_datuma_i_vremena = scanner.nextLine();
//        LocalDateTime datum_i_vrijeme = ();
//        DateTimeFormatter formaterDatum = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
//        LocalDateTime datum_i_vrijeme = LocalDateTime.parse(unos_datuma_i_vremena, formaterDatum);
//        return new Ispit(predmet, student, ocjena, datum_i_vrijeme);
//          scanner.nextLine();

//        return new Ispit(predmet, student, ocjena);
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ObrazovnaUstanova[] ustanove = new ObrazovnaUstanova[0];
        int brUstanova = 0;
        short odabirUstanove = 0;

        System.out.print("Unesite broj obrazovnih ustanova: ");
        brUstanova = scanner.nextInt();
        scanner.nextLine();
        for(int n=0; n<brUstanova; n++){
            System.out.println("Unesite podatke za " + (n+1) + ". obrazovnu ustanovu:");
            Ispit[] ispiti = new Ispit[MAX_UNOSA];
            Predmet[] predmeti = new Predmet[MAX_UNOSA];
            Profesor[] profesori = new Profesor[MAX_UNOSA];
            Student[] studosi = new Student[MAX_UNOSA];

            for(int i=0; i<MAX_UNOSA; i++){
                System.out.println("Unesite " + (i+1) + ". profesora:");
                profesori[i] = unosProfesora(scanner);
                //System.out.println("Uneseno: " + profesori[i].getIme() + " " + profesori[i].getPrezime() + " " + profesori[i].getTitula() + " " + profesori[i].getSifra());
            }

            for(int i=0; i<MAX_UNOSA; i++){
                System.out.println("Unesite " + (i+1) + ". predmet:");
                predmeti[i] = unosPredmeta(scanner, profesori);
            }

            for(int i=0; i<MAX_UNOSA; i++){
                System.out.println("Unesite " + (i+1) + ". studenta:");
                studosi[i] = unosStudenta(scanner);
            }

            for(int i=0; i<MAX_UNOSA; i++){
                System.out.println("Unesite " + (i+1) + ". ispitni rok:");
                ispiti[i] = unosRoka(scanner, predmeti, studosi);
                //Objekte klase „Student“ koji su pristupili ispitima iz određenih
                //predmeta treba dodati u polje studenata za taj određeni predmet
            }

            System.out.println();

            for(int i=0; i<MAX_UNOSA; i++){
                if(ispiti[i].getOcjena() == 5){
                    System.out.println("Student " + ispiti[i].getStudent().getIme() + " " + ispiti[i].getStudent().getPrezime() + " je ostvario ocjenu 'izvrstan' na predmetu '" + ispiti[i].getPredmet().getNaziv() + "'.");
                }
            }

            System.out.print("Odaberite obrazovnu ustanovu za navedene podatke koju zelite unijeti (1 - Veleuciliste Jave, 2 - Fakultet Racunarstva): ");
            odabirUstanove = scanner.nextShort();
            ustanove = Arrays.copyOf(ustanove, (n+1));
            if(odabirUstanove == 1)
                ustanove[n] = new VeleucilisteJave(predmeti, profesori, studosi, ispiti);
            else if(odabirUstanove == 2)
                ustanove[n] = new FakultetRacunarstva(predmeti, profesori, studosi, ispiti);
            else{
                System.out.println("KRIVI UNOSSSS!!!");
                break;
            }

            System.out.print("Unesite naziv obrazovne ustanove: ");
            ustanove[n].setNaziv(scanner.nextLine());

            for(int x=0; x < studosi.length; x++){
                int ocjenaZavrsnog;
                int ocjenaObrane;
                System.out.print("Unesite ocjenu zavrsnog rada za studenta " + studosi[x].getIme() + " " + studosi[x].getPrezime() + ": ");
                ocjenaZavrsnog = scanner.nextInt();
                System.out.print("Unesite ocjenu obrane zavrsnog rada za studenta " + studosi[x].getIme() + " " + studosi[x].getPrezime() + ": ");
                ocjenaObrane = scanner.nextInt();
                System.out.println("Konacna ocjena za studenta " + studosi[x].getIme() + " " + studosi[x].getPrezime() + "je " + ustanove[n].izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, ocjenaZavrsnog, ocjenaObrane));
            }

            Student najuspjesniji = ustanove[n].odrediNajuspjesnijegStudentaGodine(studosi, ispiti);
            System.out.println("Najuspjesniji student godine je " + najuspjesniji.getIme() + " " + najuspjesniji.getPrezime() + ".");

            Student rektorovaNagrada = new Student("IME", "PREZIME", "JMBAG", LocalDate.EPOCH);
            if(ustanove[n].isDiplomski()){
                rektorovaNagrada = ((FakultetRacunarstva) ustanove[n]).odrediStudentaZaRektorovuNagradu();
            }

            System.out.println("Student za rektorovu nagradu je " + rektorovaNagrada.getIme() + " " + rektorovaNagrada.getPrezime() + ".");

        }
    }
}