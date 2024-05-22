// Klasa Student koja nasljeđuje klasu Person
class Student extends Person {
    private int studentID;
    private String[] upisaniPredmeti;
    private String[] imenaNastavnika;

    // Konstruktor
    public Student(String ime, String prezime, int studentID, String[] upisaniPredmeti, String[] imenaNastavnika) {
        super(ime, prezime);
        this.studentID = studentID;
        this.upisaniPredmeti = upisaniPredmeti;
        this.imenaNastavnika = imenaNastavnika;
    }

    // Nadjačavanje metode za ispis detalja o studentu
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("ID studenta: " + studentID);
        System.out.println("Upisani predmeti:");
        for (String predmet : upisaniPredmeti) {
            System.out.println("- " + predmet);
        }
        System.out.println("Imena nastavnika:");
        for (String nastavnik : imenaNastavnika) {
            System.out.println("- " + nastavnik);
        }
    }
}