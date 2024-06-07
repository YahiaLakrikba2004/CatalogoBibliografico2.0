package dao;


import entities.ElementoL;
import entities.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ElementoLetterarioDAO {

    private EntityManager em;

    public ElementoLetterarioDAO(EntityManager em) {
        this.em = em;
    }


    public void save(ElementoL elementoL) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(elementoL);
            t.commit();
            System.out.println("Elemento Letterario - " + elementoL.getTitolo() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ElementoL findByIsbn(int codiceIsbn) {
        return em.find(ElementoL.class, codiceIsbn);
    }


    public void findByIsbnAndDelete(int codiceIsbn) {
        try {
            EntityTransaction t = em.getTransaction();
            ElementoL found = em.find(ElementoL.class, codiceIsbn);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Elemento letterario: " + found.getTitolo() + " eliminato");
            } else System.out.println("Elemento letterario non trovato");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // QUERIES
    public ElementoL findElementoPerIsbn(int codiceIsbn){
        TypedQuery<ElementoL> query = em.createNamedQuery("ricercaPerIsbn", ElementoL.class);
        query.setParameter("codiceIsbn", codiceIsbn);
        return query.getSingleResult();
    }

    public List<ElementoL> ricercaPerAnnoDiPubblicazione(int annoDiPubblicazione) {
        TypedQuery<ElementoL> query = em.createNamedQuery("ricercaPerAnnoDiPubblicazione", ElementoL.class);
        query.setParameter("annoDiPubblicazione", annoDiPubblicazione);
        return query.getResultList();
    }

    public List<Libro> ricercaPerAutore(String autore) {
        TypedQuery<Libro> query = em.createNamedQuery("ricercaPerAutore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<ElementoL> ricercaPerTitoloOparteDiEsso(String titolo) {
        TypedQuery<ElementoL> query = em.createNamedQuery("ricercaPerTitoloOparteDiEsso", ElementoL.class);
        query.setParameter("titolo", titolo);
        return query.getResultList();
    }

}