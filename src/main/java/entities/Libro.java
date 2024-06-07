package entities;


import jakarta.persistence.Entity;

@Entity
public class Libro extends ElementoLetterario {
    private String autore;
    private String genere;


    public Libro(int codiceIsbn, String titolo, int annoDiPubblicazione, int numeroDiPagine, String autore, String genere) {
        super(codiceIsbn, titolo, annoDiPubblicazione, numeroDiPagine);
        this.autore = autore;
        this.genere = genere;
    }


    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
       return " {" +
               "autore =" + autore +
               ", titolo=" + getTitolo() +
               ", genere" + getGenere() +
               '}';
    }
}
