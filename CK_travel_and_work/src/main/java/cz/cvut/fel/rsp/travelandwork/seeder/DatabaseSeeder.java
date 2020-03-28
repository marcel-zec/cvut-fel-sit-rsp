package cz.cvut.fel.rsp.travelandwork.seeder;

import cz.cvut.fel.rsp.travelandwork.dao.AchievementDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripDao;
import cz.cvut.fel.rsp.travelandwork.dao.TripSessionDao;
import cz.cvut.fel.rsp.travelandwork.model.Achievement;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DatabaseSeeder implements
        ApplicationListener<ContextRefreshedEvent> {

    private Logger LOGGER = Logger.getLogger(DatabaseSeeder.class.getName());
    private TripDao tripDao;
    private TripSessionDao tripSessionDao;
    private AchievementDao achievementDao;


    @Autowired
    public DatabaseSeeder(TripDao tripDao, TripSessionDao tripSessionDao, AchievementDao achievementDao) {
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
        this.achievementDao = achievementDao;
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
        Trip trip = new Trip("Casablanca Me gusto",15,description,"casablanca_me_gusta",1000,"Casablan, Mexico",2);
        tripDao.persist(trip);
        TripSession tripSession = new TripSession(trip,LocalDate.parse("2020-09-12"), LocalDate.parse("2020-10-12"),2000);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip,LocalDate.parse("2020-10-12"), LocalDate.parse("2020-11-12"),2500);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip,LocalDate.parse("2020-12-12"), LocalDate.parse("2020-12-20"),1800);
        tripSessionDao.persist(tripSession);

        description = "Sed nec scelerisque metus. Etiam volutpat ipsum id ullamcorper viverra. Nunc ultrices odio odio, quis rutrum lacus pharetra sit amet. Phasellus in eleifend dolor. Sed et nisi massa. Vestibulum suscipit vel leo condimentum varius. Ut in augue quis eros aliquet egestas sed nec augue. Pellentesque vel neque eget nisi sodales porta eget quis orci. Maecenas nisi quam, vulputate et imperdiet id, finibus eget lacus. Cras et metus odio. Donec posuere ac nisi eget semper. Nunc sit amet lectus vitae est ornare semper id rutrum odio. Sed eu lectus odio. Fusce mattis nulla massa, in pellentesque arcu tincidunt sed. Nulla facilisi. ";
        trip = new Trip("Chef in Barca",20,description,"barcechef",1000,"Barcelona, Spain",10);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2020-02-14"), LocalDate.parse("2020-02-21"),2000);
        tripSessionDao.persist(tripSession);

        description = "Etiam a sem odio. Cras congue a odio vitae convallis. Nullam volutpat in eros eget aliquet. Maecenas congue finibus purus, non bibendum dolor finibus vel. Sed a molestie odio, vitae pharetra nisl. Integer quis velit sit amet augue sagittis tincidunt eu quis est. Sed ultricies fringilla ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vestibulum tincidunt pretium. Donec a lobortis enim, quis malesuada ipsum. Vestibulum egestas erat pulvinar orci posuere, id pharetra arcu scelerisque. Vestibulum sed ultrices magna, dictum tristique lorem. ";
        trip = new Trip("Chef in London",20,description,"londonchef",1000,"London, England",9);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2018-02-14"), LocalDate.parse("2018-03-21"),2000);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip,LocalDate.parse("2018-04-14"), LocalDate.parse("2018-05-21"),2000);
        tripSessionDao.persist(tripSession);

        description = "Nullam gravida lectus tempus congue pretium. Nunc volutpat diam orci, a consectetur dui iaculis sollicitudin. Fusce varius nisi placerat turpis viverra pulvinar. Pellentesque vel commodo nibh, sed volutpat nunc. Duis congue enim malesuada sapien commodo egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum interdum, ante eu vehicula porttitor, libero purus consequat metus, quis aliquet lectus orci sit amet mi. Aenean libero sapien, tempus sit amet lorem in, cursus sodales erat. Vivamus suscipit felis et ex pulvinar, vitae rutrum diam tempus. ";
        trip = new Trip("Archeology of Zabgre",10,description,"zagreb_archeology",1000,"Zagreb, Croatia",4);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2018-07-14"), LocalDate.parse("2018-08-21"),2000);
        tripSessionDao.persist(tripSession);

        description = "Sed nec scelerisque metus. Etiam volutpat ipsum id ullamcorper viverra. Nunc ultrices odio odio, quis rutrum lacus pharetra sit amet. Phasellus in eleifend dolor. Sed et nisi massa. Vestibulum suscipit vel leo condimentum varius. Ut in augue quis eros aliquet egestas sed nec augue. Pellentesque vel neque eget nisi sodales porta eget quis orci. Maecenas nisi quam, vulputate et imperdiet id, finibus eget lacus. Cras et metus odio. Donec posuere ac nisi eget semper. Nunc sit amet lectus vitae est ornare semper id rutrum odio. Sed eu lectus odio. Fusce mattis nulla massa, in pellentesque arcu tincidunt sed. Nulla facilisi. ";
        trip = new Trip("LoLoLo",5,description,"lololo",800,"Lololo, Lololo island",2);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2016-07-14"), LocalDate.parse("2016-08-21"),2000);
        tripSessionDao.persist(tripSession);

        description = "Etiam a sem odio. Cras congue a odio vitae convallis. Nullam volutpat in eros eget aliquet. Maecenas congue finibus purus, non bibendum dolor finibus vel. Sed a molestie odio, vitae pharetra nisl. Integer quis velit sit amet augue sagittis tincidunt eu quis est. Sed ultricies fringilla ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vestibulum tincidunt pretium. Donec a lobortis enim, quis malesuada ipsum. Vestibulum egestas erat pulvinar orci posuere, id pharetra arcu scelerisque. Vestibulum sed ultrices magna, dictum tristique lorem. ";
        trip = new Trip("Laster",8,description,"laster",500,"Laster, Lasterland",2);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2019-01-14"), LocalDate.parse("2019-01-21"),2000);
        tripSessionDao.persist(tripSession);
    }

    @Transactional
    void createAchievement(){
        Achievement achievement = new Achievement("Habamalo","Habamalo is balabo con mambo.","location-arrow");
        achievementDao.persist(achievement);

        achievement = new Achievement("Kalomone","Kalomone is bumbalo fon lucato.","swimmer");
        achievementDao.persist(achievement);

        achievement = new Achievement("Swamino","Momone con valuta meta so Swamino.","swimming-pool");
        achievementDao.persist(achievement);

        achievement = new Achievement("Wanoko","Wanoko der muliko fon gelono","water");
        achievementDao.persist(achievement);

        achievement = new Achievement("Supero bumbo","Supero bumbo con muplito fongo.","award");
        achievementDao.persist(achievement);

        achievement = new Achievement("Kingonelo","Richardo con mucho la despacito Kingo.","crown");
        achievementDao.persist(achievement);

        achievement = new Achievement("Schampiono luscato","Le schapmiono luscato con rumba de la noche.","trophy");
        achievementDao.persist(achievement);

        achievement = new Achievement("Securito pronto","Los securitos prostos de la salsa.","shield-alt");
        achievementDao.persist(achievement);

        achievement = new Achievement("Medalo","Picholino de la noche con vuelta macho medalo.","medal");
        achievementDao.persist(achievement);
    }
}