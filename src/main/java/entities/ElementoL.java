package entities;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_elemento_l")
@NamedQuery(name = "trovaPerIsbn", query = "SELECT e FROM ElementoL e WHERE e.codiceIsbn = :codiceIsbn")
@NamedQuery(name = "trovaPerAnnoPubblicazione", query = "SELECT e FROM ElementoL e WHERE e.annoDiPubblicazione = :annoDiPubblicazione")
@NamedQuery(name = "trovaPerAutore", query = "SELECT l FROM Libro l WHERE l.autore = :autore")
@NamedQuery(name = "trovaPerTitoloOParte", query = "SELECT e FROM ElementoL e WHERE e.titolo LIKE CONCAT('%', :titolo, '%')")

public class ElementoL {
    @Id
    private int codiceIsbn;
    private String titolo;
    @Column(name = "anno_pubblicazione")
    private int annoDiPubblicazione;
    @Column(name = "numero_di_pagine")
    private int numeroDiPagine;




    public ElementoL(int codiceIsbn, String titolo, int annoDiPubblicazione, int numeroDiPagine) {
        this.codiceIsbn = codiceIsbn;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;

        this.numeroDiPagine = numeroDiPagine;
    }

    public int getCodiceIsbn() {
        return codiceIsbn;
    }

    public void setCodiceIsbn(int codiceIsbn) {
        this.codiceIsbn = codiceIsbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroDiPagine() {
        return numeroDiPagine;
    }

    public void setNumeroDiPagine(int numeroDiPagine) {
        this.numeroDiPagine = numeroDiPagine;
    }


}
