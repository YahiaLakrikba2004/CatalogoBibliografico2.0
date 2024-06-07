package entities;

import dao.ElementoLDAO;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import entities.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4D15");
        EntityManager em = emf.createEntityManager();

        ElementoLDAO elementoLetterarioDAO = new ElementoLDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        // Creazione utenti
        Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1980, 3, 15), 1001);
        Utente utente2 = new Utente("Luca", "Bianchi", LocalDate.of(1992, 7, 21), 1002);
        Utente utente3 = new Utente("Giulia", "Verdi", LocalDate.of(1985, 11, 30), 1003);

        // Salvataggio utenti
        utenteDAO.addUtente(utente1);
        utenteDAO.addUtente(utente2);
        utenteDAO.addUtente(utente3);

        // Creazione libri
        Libro libro1 = new Libro(1010, "Viaggio nel Tempo", 2010, 350, "Marco Polo", "storia");
        Libro libro2 = new Libro(1020, "Programmazione Avanzata", 2015, 600, "John Doe", "tecnologia");
        Libro libro3 = new Libro(1030, "Cucina Italiana", 2000, 150, "Anna Rossi", "cucina");
        Libro libro4 = new Libro(1040, "Fisica Quantistica", 2018, 700, "Albert Einstein", "scienza");
        Libro libro5 = new Libro(1050, "Romanzo Giallo", 2021, 500, "Luigi Pirandello", "giallo");

        // Creazione riviste
        Rivista rivista1 = new Rivista(2010, "Scientific American", 2022, 40, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista(2020, "National Geographic", 2020, 30, Periodicita.SEMESTRALE);
        Rivista rivista3 = new Rivista(2030, "Fitness Monthly", 2021, 50, Periodicita.MENSILE);

        // Salvataggio elementi letterari
        elementoLetterarioDAO.save(libro1);
        elementoLetterarioDAO.save(libro2);
        elementoLetterarioDAO.save(libro3);
        elementoLetterarioDAO.save(libro4);
        elementoLetterarioDAO.save(libro5);
        elementoLetterarioDAO.save(rivista1);
        elementoLetterarioDAO.save(rivista2);
        elementoLetterarioDAO.save(rivista3);

        // Creazione prestiti
        Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 2, 10), null);
        Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.of(2023, 2, 20), LocalDate.of(2023, 3, 20), null);
        Prestito prestito3 = new Prestito(utente3, rivista1, LocalDate.of(2023, 3, 30), LocalDate.of(2023, 4, 30), null);
        Prestito prestito4 = new Prestito(utente1, libro3, LocalDate.of(2023, 4, 15), LocalDate.of(2023, 5, 15), null);
        Prestito prestito5 = new Prestito(utente2, libro4, LocalDate.of(2023, 5, 25), LocalDate.of(2023, 6, 25), null);
        Prestito prestito6 = new Prestito(utente3, rivista2, LocalDate.of(2023, 6, 5), LocalDate.of(2023, 7, 5), null);
        Prestito prestito7 = new Prestito(utente1, libro5, LocalDate.of(2023, 7, 10), LocalDate.of(2023, 8, 10), null);

        // Salvataggio prestiti
        prestitoDAO.save(prestito1);
        prestitoDAO.save(prestito2);
        prestitoDAO.save(prestito3);
        prestitoDAO.save(prestito4);
        prestitoDAO.save(prestito5);
        prestitoDAO.save(prestito6);
        prestitoDAO.save(prestito7);

        // Rimozione di un elemento letterario
        elementoLetterarioDAO.findByIsbnAndDelete(1020);

        // Ricerca di un elemento per ISBN
        ElementoL elementoTrovato = elementoLetterarioDAO.findElementoPerIsbn(1040);
        System.out.println("Elemento trovato: " + elementoTrovato.getTitolo());

        // Ricerca di elementi per anno di pubblicazione
        System.out.println("Elementi pubblicati nel 2021: " + elementoLetterarioDAO.ricercaPerAnnoDiPubblicazione(2021));

        // Ricerca di libri per autore
        System.out.println("Libri scritti da Anna Rossi: " + elementoLetterarioDAO.ricercaPerAutore("Anna Rossi"));

        // Ricerca di elementi per titolo o parte di esso
        System.out.println("Risultati per titolo contenente 'Quantum': " + elementoLetterarioDAO.ricercaPerTitoloOparteDiEsso("Quantum"));

        // Restituzione di un prestito
        prestitoDAO.updateDataRestituzione(prestito1, LocalDate.of(2023, 1, 25));

        em.close();
        emf.close();
    }
}
