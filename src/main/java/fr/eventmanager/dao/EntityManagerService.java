package fr.eventmanager.dao;

import fr.eventmanager.builder.EventBuilder;
import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
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
        final User romainDavaze = new UserBuilder("romain.davaze@gmail.com", "password", "Romain", "Davaze")
                .setCompany("Sigma")
                .build();

        final User guillaumeChanson = new UserBuilder("guillaume.chanson@gmail.com", "password", "Guillaume", "Chanson")
                .setCompany("Nantes Métropole")
                .build();

        final User florineCercle = new UserBuilder("florine.cercle@gmail.com", "password", "Florine", "Cerclé")
                .setCompany("Sopra Steria")
                .build();

        final User johnDoe = new UserBuilder("john.doe@gmail.com", "password", "John", "Doe")
                .setCompany("OpenJDK")
                .build();

        final User laurentGuerin = new UserBuilder("laurent.guerin@gmail.com", "password", "Laurent", "Guerin")
                .setCompany("Teacher")
                .build();


        performOperation(em -> {
            em.persist(romainDavaze);
            em.persist(guillaumeChanson);
            em.persist(florineCercle);
            em.persist(laurentGuerin);
            em.persist(johnDoe);
        });

        performOperation(em -> {
            em.persist(new EventBuilder("Fête de la musique", guillaumeChanson)
                    .setVisible(true)
                    .setDateBegin(new Date(116, 11, 21))
                    .setDateEnd(new Date(116, 11, 22))
                    .setLocation("Nantes")
                    .setDescription("Fête de la musique à Nantes")
                    .build());
            em.persist(new EventBuilder("Laser Game", romainDavaze)
                    .setDateBegin(new Date(116, 12, 23))
                    .setDateEnd(new Date(116, 12, 23))
                    .setLocation("Saint-Herblain")
                    .setDescription("On va bien s'amuser !")
                    .build());
            em.persist(new EventBuilder("Avant-première", florineCercle)
                    .addAttendee(romainDavaze)
                    .setVisible(true)
                    .setDateBegin(new Date(116, 11, 1))
                    .setDateEnd(new Date(116, 11, 1))
                    .setLocation("Gaumont")
                    .setDescription("Avant-première Harry Potter")
                    .build());
            em.persist(new EventBuilder("Vide-grenier", guillaumeChanson)
                    .setDateBegin(new Date(116, 11, 12))
                    .setDateEnd(new Date(116, 11, 12))
                    .setLocation("Bouffay")
                    .setDescription("Venez dénichez de objets rares !")
                    .build());
            em.persist(new EventBuilder("Fête des fleurs", florineCercle)
                    .setDateBegin(new Date(117, 06, 20))
                    .setDateEnd(new Date(117, 06, 21))
                    .setLocation("Dans un champ de fleurs")
                    .setDescription("Plus belle journée de l'année où il n'y a jamais rien eu d'autre que du soleil")
                    .build());
            em.persist(new EventBuilder("Shopping à l'Apple Store", romainDavaze)
                    .setDateBegin(new Date(116, 12, 25))
                    .setDateEnd(new Date(116, 12, 25))
                    .setLocation("Apple Store")
                    .setDescription("Romain doit s'acheter un macbook pro")
                    .build());
            em.persist(new EventBuilder("Concert Parov Stelar", guillaumeChanson)
                    .setDateBegin(new Date(116, 11, 230))
                    .setDateEnd(new Date(116, 11, 30))
                    .build());
            em.persist(new EventBuilder("Concours Patisserie", romainDavaze)
                    .setDateBegin(new Date(117, 01, 12))
                    .setDateEnd(new Date(117, 01, 12))
                    .setLocation("Chez Romain")
                    .setDescription("Concours de gateau !")
                    .build());
            em.persist(new EventBuilder("Salons des Vins", romainDavaze)
                    .setDateBegin(new Date(117, 04, 01))
                    .setDateEnd(new Date(117, 04, 01))
                    .setLocation("Beaujoire")
                    .setDescription("Dégustation de vin")
                    .build());
            em.persist(new EventBuilder("Pyjama party", florineCercle)
                    .setDateBegin(new Date(116, 12, 1))
                    .setDateEnd(new Date(116, 12, 2))
                    .setLocation("Chez Florine")
                    .setDescription("On va bien s'amuser !")
                    .build());
            em.persist(new EventBuilder("Anniversaire de Guillaume", guillaumeChanson)
                    .setDateBegin(new Date(116, 10, 31))
                    .setDateEnd(new Date(116, 11, 1))
                    .setLocation("Chez Guillaume")
                    .setDescription("Bon anniversaire Guillaume !")
                    .build());

        });
    }
}
