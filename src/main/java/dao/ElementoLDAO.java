package dao;

import entities.ElementoL;
import entities.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ElementoLDAO {

    private EntityManager entityManager;

    public ElementoLDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(ElementoL elementoL) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(elementoL);
            transaction.commit();
            System.out.println("Elemento Letterario - " + elementoL.getTitolo() + " - creato!");
        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public ElementoL findByIsbn(int codiceIsbn) {
        return entityManager.find(ElementoL.class, codiceIsbn);
    }

    public void findByIsbnAndDelete(int codiceIsbn) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            ElementoL found = entityManager.find(ElementoL.class, codiceIsbn);
            if (found != null) {
                transaction.begin();
                entityManager.remove(found);
                transaction.commit();
                System.out.println("Elemento letterario: " + found.getTitolo() + " eliminato");
            } else {
                System.out.println("Elemento letterario non trovato");
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'eliminazione: " + e.getMessage());
        }
    }

    public ElementoL findElementoPerIsbn(int codiceIsbn){
        TypedQuery<ElementoL> query = entityManager.createNamedQuery("trovaPerIsbn", ElementoL.class);
        query.setParameter("codiceIsbn", codiceIsbn);
        return query.getSingleResult();
    }

    public List<ElementoL> ricercaPerAnnoDiPubblicazione(int annoDiPubblicazione) {
        TypedQuery<ElementoL> query = entityManager.createNamedQuery("trovaPerAnnoPubblicazione", ElementoL.class);
        query.setParameter("annoDiPubblicazione", annoDiPubblicazione);
        return query.getResultList();
    }

    public List<Libro> ricercaPerAutore(String autore) {
        TypedQuery<Libro> query = entityManager.createNamedQuery("trovaPerAutore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<ElementoL> ricercaPerTitoloOparteDiEsso(String titolo) {
        TypedQuery<ElementoL> query = entityManager.createNamedQuery("trovaPerTitoloOParte", ElementoL.class);
        query.setParameter("titolo", titolo);
        return query.getResultList();
    }
}
