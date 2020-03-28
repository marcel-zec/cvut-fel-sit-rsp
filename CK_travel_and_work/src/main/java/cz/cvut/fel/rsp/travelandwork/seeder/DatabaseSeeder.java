package cz.cvut.fel.rsp.travelandwork.seeder;

import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.logging.Logger;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DatabaseSeeder implements
        ApplicationListener<ContextRefreshedEvent> {

    private Logger LOGGER = Logger.getLogger(DatabaseSeeder.class.getName());
    private TripDao tripDao;
    private TripSessionDao tripSessionDao;


    @Autowired
    public DatabaseSeeder(TripDao tripDao, TripSessionDao tripSessionDao) {
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
    }
    
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //TODO - vykona sa hned po spusteni
        System.out.println("Vypis po stupusteni aplikacie.");
        createTrips();
    }

    @Transactional
    void createTrips(){
        String description = "Nullam gravida lectus tempus congue pretium. Nunc volutpat diam orci, a consectetur dui iaculis sollicitudin. Fusce varius nisi placerat turpis viverra pulvinar. Pellentesque vel commodo nibh, sed volutpat nunc. Duis congue enim malesuada sapien commodo egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum interdum, ante eu vehicula porttitor, libero purus consequat metus, quis aliquet lectus orci sit amet mi. Aenean libero sapien, tempus sit amet lorem in, cursus sodales erat. Vivamus suscipit felis et ex pulvinar, vitae rutrum diam tempus. ";
        Trip trip = new Trip("Casablanca Me gusto",15,description,"777 555 111","casablanca_me_gusta");
        tripDao.persist(trip);
        TripSession tripSession = new TripSession(LocalDateTime.now(),LocalDateTime.now(),2000);
        tripSession.setTrip(trip);
        tripSessionDao.persist(tripSession);

        description = "Sed nec scelerisque metus. Etiam volutpat ipsum id ullamcorper viverra. Nunc ultrices odio odio, quis rutrum lacus pharetra sit amet. Phasellus in eleifend dolor. Sed et nisi massa. Vestibulum suscipit vel leo condimentum varius. Ut in augue quis eros aliquet egestas sed nec augue. Pellentesque vel neque eget nisi sodales porta eget quis orci. Maecenas nisi quam, vulputate et imperdiet id, finibus eget lacus. Cras et metus odio. Donec posuere ac nisi eget semper. Nunc sit amet lectus vitae est ornare semper id rutrum odio. Sed eu lectus odio. Fusce mattis nulla massa, in pellentesque arcu tincidunt sed. Nulla facilisi. ";
        trip = new Trip("Chef in Barca",20,description,"213180900","barcechef");
        tripDao.persist(trip);

        description = "Etiam a sem odio. Cras congue a odio vitae convallis. Nullam volutpat in eros eget aliquet. Maecenas congue finibus purus, non bibendum dolor finibus vel. Sed a molestie odio, vitae pharetra nisl. Integer quis velit sit amet augue sagittis tincidunt eu quis est. Sed ultricies fringilla ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vestibulum tincidunt pretium. Donec a lobortis enim, quis malesuada ipsum. Vestibulum egestas erat pulvinar orci posuere, id pharetra arcu scelerisque. Vestibulum sed ultrices magna, dictum tristique lorem. ";
        trip = new Trip("Chef in London",20,description,"213180900","londonchef");
        tripDao.persist(trip);

        description = "Nullam gravida lectus tempus congue pretium. Nunc volutpat diam orci, a consectetur dui iaculis sollicitudin. Fusce varius nisi placerat turpis viverra pulvinar. Pellentesque vel commodo nibh, sed volutpat nunc. Duis congue enim malesuada sapien commodo egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum interdum, ante eu vehicula porttitor, libero purus consequat metus, quis aliquet lectus orci sit amet mi. Aenean libero sapien, tempus sit amet lorem in, cursus sodales erat. Vivamus suscipit felis et ex pulvinar, vitae rutrum diam tempus. ";
        trip = new Trip("Archeology of Zabgre",10,description,"200333695","zagreb_archeology");
        tripDao.persist(trip);

        description = "Sed nec scelerisque metus. Etiam volutpat ipsum id ullamcorper viverra. Nunc ultrices odio odio, quis rutrum lacus pharetra sit amet. Phasellus in eleifend dolor. Sed et nisi massa. Vestibulum suscipit vel leo condimentum varius. Ut in augue quis eros aliquet egestas sed nec augue. Pellentesque vel neque eget nisi sodales porta eget quis orci. Maecenas nisi quam, vulputate et imperdiet id, finibus eget lacus. Cras et metus odio. Donec posuere ac nisi eget semper. Nunc sit amet lectus vitae est ornare semper id rutrum odio. Sed eu lectus odio. Fusce mattis nulla massa, in pellentesque arcu tincidunt sed. Nulla facilisi. ";
        trip = new Trip("LoLoLo",5,description,"412856232","lololo");
        tripDao.persist(trip);

        description = "Etiam a sem odio. Cras congue a odio vitae convallis. Nullam volutpat in eros eget aliquet. Maecenas congue finibus purus, non bibendum dolor finibus vel. Sed a molestie odio, vitae pharetra nisl. Integer quis velit sit amet augue sagittis tincidunt eu quis est. Sed ultricies fringilla ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vestibulum tincidunt pretium. Donec a lobortis enim, quis malesuada ipsum. Vestibulum egestas erat pulvinar orci posuere, id pharetra arcu scelerisque. Vestibulum sed ultrices magna, dictum tristique lorem. ";
        trip = new Trip("Laster",8,description,"123654789","laster");
        tripDao.persist(trip);
    }
}