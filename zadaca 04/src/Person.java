class Person {
    private String ime;
    private String prezime;

    // Konstruktor
    public Person(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    // Getteri i setteri
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    // Metoda za ispis detalja o osobi
    public void displayDetails() {
        System.out.println("Ime: " + ime);
        System.out.println("Prezime: " + prezime);
    }
}