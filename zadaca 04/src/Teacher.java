// Klasa Teacher koja nasljeđuje klasu Person
class Teacher extends Person {
    private String[] imenaPredmeta;
    private double iznosPlace;

    // Konstruktor
    public Teacher(String ime, String prezime, String[] imenaPredmeta, double iznosPlace) {
        super(ime, prezime);
        this.imenaPredmeta = imenaPredmeta;
        this.iznosPlace = iznosPlace;
    }

    // Nadjačavanje metode za ispis detalja o nastavniku
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Predmeti koje nastavnik drži:");
        for (String predmet : imenaPredmeta) {
            System.out.println("- " + predmet);
        }
        System.out.println("Iznos plaće: " + iznosPlace);
    }
}