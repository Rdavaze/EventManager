package fr.eventmanager.dao;

import fr.eventmanager.builder.EventBuilder;
import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Generic entity manager service to perform jpa operations & queries,
 * and handle back-end management of jpa resources.
 */
public class EntityManagerService {
    public static final String PERSISTENCE_UNIT = "EventManager";
    private static EntityManagerService instance = null;

    private final EntityManagerFactory factory;
    private final EntityManager manager;

    private EntityManagerService(String persistenceUnit) {
        this.factory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.manager = factory.createEntityManager();
    }

    public static EntityManagerService getInstance() {
        if (instance == null) {
            instance = new EntityManagerService(PERSISTENCE_UNIT);
            instance.populate();
        }
        return instance;
    }

    public synchronized <T> T performQuery(Function<EntityManager, T> query) {
        return query.apply(this.manager);
    }

    public synchronized void performOperation(Consumer<EntityManager> operation) {
        this.manager.getTransaction().begin();
        operation.accept(this.manager);
        this.manager.getTransaction().commit();
    }

    private synchronized void populate() {
        final User johnDoe = new UserBuilder("john.doe@gmail.com", "password", "John", "Doe")
                .setCompany("OpenJDK")
                .build();

        final User richardRoe = new UserBuilder("richard.roe@gmail.com", "password", "Richard", "Roe")
                .setCompany("Sun")
                .build();

        performOperation(em -> {
            em.persist(johnDoe);
            em.persist(richardRoe);
        });

        performOperation(em -> {
            em.persist(new EventBuilder("Premier événement", johnDoe).build());
            em.persist(new EventBuilder("Deuxième événement", johnDoe).build());
            em.persist(new EventBuilder("Troisième événement", richardRoe).build());
            em.persist(new EventBuilder("Quatre événement", johnDoe).build());
            em.persist(new EventBuilder("Cinq événement", johnDoe).build());
            em.persist(new EventBuilder("Sixieme événement", richardRoe).build());
            em.persist(new EventBuilder("Sept événement", johnDoe).build());
            em.persist(new EventBuilder("Huit événement", johnDoe).build());
            em.persist(new EventBuilder("Neuf événement", richardRoe).build());
            em.persist(new EventBuilder("Dix événement", johnDoe).build());
            em.persist(new EventBuilder("Onze événement", johnDoe).build());
            em.persist(new EventBuilder("Douze événement", richardRoe).build());
            em.persist(new EventBuilder("Treize événement", johnDoe).build());
            em.persist(new EventBuilder("Quatorze événement", johnDoe).build());
            em.persist(new EventBuilder("Quinze événement", richardRoe).build());
        });
    }
}
