package dao;

import entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;

public class PrestitoDAO {
    private EntityManager entityManager;

    public PrestitoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Prestito prestito) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(prestito);
            transaction.commit();
            System.out.println("Prestito di " + prestito.getUtente().getNome() + " " + prestito.getUtente().getCognome() + " creato con successo!");
        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio del prestito: " + e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void updateDataRestituzione(Prestito prestito, LocalDate dataRestituzioneEffettiva) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            prestito.setDataRestituzioneEffettiva(dataRestituzioneEffettiva);
            entityManager.merge(prestito);
            transaction.commit();
            System.out.println("Il prestito di " + prestito.getUtente().getNome() + " " + prestito.getUtente().getCognome() + " è stato restituito!");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiornamento della data di restituzione del prestito: " + e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}