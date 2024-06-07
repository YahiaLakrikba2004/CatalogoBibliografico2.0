package entities;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Rivista extends ElementoL {
    @Enumerated(EnumType.STRING)
    Periodicita periodicita;

    public Rivista(int codiceIsbn, String titolo, int annoDiPubblicazione, int numeroDiPagine, Periodicita periodicita) {
        super(codiceIsbn, titolo, annoDiPubblicazione, numeroDiPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista {" +
                "titolo=" + getTitolo() +
                " periodicita=" + periodicita +
                '}';
    }



}
