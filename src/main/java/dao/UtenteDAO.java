package dao;

import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDAO {

    private final EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addUtente(Utente utente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(utente);
            transaction.commit();
            System.out.println("Nuovo utente - " + utente.getNome() + " " + utente.getCognome() + " - registrato con successo!");
        } catch (Exception e) {
            System.out.println("Errore durante la creazione dell'utente: " + e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
