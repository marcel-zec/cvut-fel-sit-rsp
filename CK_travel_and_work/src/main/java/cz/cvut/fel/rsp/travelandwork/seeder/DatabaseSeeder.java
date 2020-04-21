package cz.cvut.fel.rsp.travelandwork.seeder;

import cz.cvut.fel.rsp.travelandwork.dao.*;
import cz.cvut.fel.rsp.travelandwork.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DatabaseSeeder implements
        ApplicationListener<ContextRefreshedEvent> {

    private Logger LOGGER = Logger.getLogger(DatabaseSeeder.class.getName());
    private TripDao tripDao;
    private TripSessionDao tripSessionDao;
    private AchievementDao achievementDao;
    private CategoryDao categoryDao;
    private UserDao userDao;
    private AddressDao addressDao;

    @Autowired
    public DatabaseSeeder(TripDao tripDao, TripSessionDao tripSessionDao, AchievementDao achievementDao, CategoryDao categoryDao, UserDao userDao, AddressDao addressDao) {
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
        this.achievementDao = achievementDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
        this.addressDao = addressDao;
    }
    
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //TODO - vykona sa hned po spusteni
        System.out.println("Vypis po stupusteni aplikacie.");
        createAchievement();
        createCategories();
        //setAchievementsAndCategories();
        createTrips();
        createUsers();
    }

    @Transactional
    void createTrips(){
        String description;// = "Nullam gravida lectus tempus congue pretium. Nunc volutpat diam orci, a consectetur dui iaculis sollicitudin. Fusce varius nisi placerat turpis viverra pulvinar. Pellentesque vel commodo nibh, sed volutpat nunc. Duis congue enim malesuada sapien commodo egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum interdum, ante eu vehicula porttitor, libero purus consequat metus, quis aliquet lectus orci sit amet mi. Aenean libero sapien, tempus sit amet lorem in, cursus sodales erat. Vivamus suscipit felis et ex pulvinar, vitae rutrum diam tempus. ";
        Trip trip;/* = new Trip("Casablanca Me gusto",15,description,"casablanca_me_gusta",1000,"Casablan, Mexico",2);
        tripDao.persist(trip);
        */TripSession tripSession;/*= new TripSession(trip,LocalDate.parse("2020-09-12"), LocalDate.parse("2020-10-12"),2000);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip,LocalDate.parse("2020-10-12"), LocalDate.parse("2020-11-12"),2500);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip,LocalDate.parse("2020-12-12"), LocalDate.parse("2020-12-20"),1800);
        tripSessionDao.persist(tripSession);

        description = "Sed nec scelerisque metus. Etiam volutpat ipsum id ullamcorper viverra. Nunc ultrices odio odio, quis rutrum lacus pharetra sit amet. Phasellus in eleifend dolor. Sed et nisi massa. Vestibulum suscipit vel leo condimentum varius. Ut in augue quis eros aliquet egestas sed nec augue. Pellentesque vel neque eget nisi sodales porta eget quis orci. Maecenas nisi quam, vulputate et imperdiet id, finibus eget lacus. Cras et metus odio. Donec posuere ac nisi eget semper. Nunc sit amet lectus vitae est ornare semper id rutrum odio. Sed eu lectus odio. Fusce mattis nulla massa, in pellentesque arcu tincidunt sed. Nulla facilisi. ";
        trip = new Trip("Chef in Barca",20,description,"barcechef",1000,"Barcelona, Spain",10);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2022-02-14"), LocalDate.parse("2022-02-21"),2000);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip,LocalDate.parse("2020-12-12"), LocalDate.parse("2020-12-20"),1000);
        tripSessionDao.persist(tripSession);

        description = "Etiam a sem odio. Cras congue a odio vitae convallis. Nullam volutpat in eros eget aliquet. Maecenas congue finibus purus, non bibendum dolor finibus vel. Sed a molestie odio, vitae pharetra nisl. Integer quis velit sit amet augue sagittis tincidunt eu quis est. Sed ultricies fringilla ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vestibulum tincidunt pretium. Donec a lobortis enim, quis malesuada ipsum. Vestibulum egestas erat pulvinar orci posuere, id pharetra arcu scelerisque. Vestibulum sed ultrices magna, dictum tristique lorem. ";
        trip = new Trip("Chef in London",20,description,"londonchef",1000,"London, England",9);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2021-02-14"), LocalDate.parse("2021-03-21"),2000);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip,LocalDate.parse("2021-04-14"), LocalDate.parse("2021-05-21"),2000);
        tripSessionDao.persist(tripSession);

        description = "Nullam gravida lectus tempus congue pretium. Nunc volutpat diam orci, a consectetur dui iaculis sollicitudin. Fusce varius nisi placerat turpis viverra pulvinar. Pellentesque vel commodo nibh, sed volutpat nunc. Duis congue enim malesuada sapien commodo egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum interdum, ante eu vehicula porttitor, libero purus consequat metus, quis aliquet lectus orci sit amet mi. Aenean libero sapien, tempus sit amet lorem in, cursus sodales erat. Vivamus suscipit felis et ex pulvinar, vitae rutrum diam tempus. ";
        trip = new Trip("Archeology of Zabgre",10,description,"zagreb_archeology",1000,"Zagreb, Croatia",4);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2021-07-14"), LocalDate.parse("2021-08-21"),2000);
        tripSessionDao.persist(tripSession);

        description = "Sed nec scelerisque metus. Etiam volutpat ipsum id ullamcorper viverra. Nunc ultrices odio odio, quis rutrum lacus pharetra sit amet. Phasellus in eleifend dolor. Sed et nisi massa. Vestibulum suscipit vel leo condimentum varius. Ut in augue quis eros aliquet egestas sed nec augue. Pellentesque vel neque eget nisi sodales porta eget quis orci. Maecenas nisi quam, vulputate et imperdiet id, finibus eget lacus. Cras et metus odio. Donec posuere ac nisi eget semper. Nunc sit amet lectus vitae est ornare semper id rutrum odio. Sed eu lectus odio. Fusce mattis nulla massa, in pellentesque arcu tincidunt sed. Nulla facilisi. ";
        trip = new Trip("LoLoLo",5,description,"lololo",800,"Lololo, Lololo island",2);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2021-07-14"), LocalDate.parse("2021-08-21"),2000);
        tripSessionDao.persist(tripSession);

        description = "Etiam a sem odio. Cras congue a odio vitae convallis. Nullam volutpat in eros eget aliquet. Maecenas congue finibus purus, non bibendum dolor finibus vel. Sed a molestie odio, vitae pharetra nisl. Integer quis velit sit amet augue sagittis tincidunt eu quis est. Sed ultricies fringilla ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vestibulum tincidunt pretium. Donec a lobortis enim, quis malesuada ipsum. Vestibulum egestas erat pulvinar orci posuere, id pharetra arcu scelerisque. Vestibulum sed ultrices magna, dictum tristique lorem. ";
        trip = new Trip("Laster",8,description,"laster",500,"Laster, Lasterland",2);
        tripDao.persist(trip);
        tripSession = new TripSession(trip,LocalDate.parse("2021-01-14"), LocalDate.parse("2021-01-21"),2000);
        tripSessionDao.persist(tripSession);*/

        //priklady tripov a user progressu medzi nimi
        description = "Tento zajezd bude mit cenu za dopravu a kurz, po absolvování se odemkne achievement ´kuchař ryb fugu´, pro absolvování je potřeba mít achievement ´Kuchtík´." ;
        trip = new Trip("Kurz vaření ryb Fugu",10,description,"fugukurz",1000,"Tokyo, Japan",1);
        //trip.addGainAchievement();
        //trip.addRequiredAchievement();
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-06"), LocalDate.parse("2020-06-12"), 3000);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-12"), LocalDate.parse("2020-06-18"), 3000);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-18"), LocalDate.parse("2020-06-24"), 3000);
        tripSessionDao.persist(tripSession);

        description = "Tento zajezd bude mit zalohu, pro absolvování je potřeba mít achievement ´Kuchař ryb fugu´." ;
        trip = new Trip("Vaření ryb Fugu, Praha",10,description,"fuguvar",3459,"Praha, Česká republika",1);
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-06"), LocalDate.parse("2020-07-12"), 0);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-12"), LocalDate.parse("2020-07-18"), 0);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-18"), LocalDate.parse("2020-07-24"), 0);
        tripSessionDao.persist(tripSession);

        description = "Tento zajezd bude mit zalohu, pro absolvování je potřeba mít achievement ´Kuchař´." ;
        trip = new Trip("Kuchař na Pražském hradě",8,description,"prahradvar",1000,"Praha, Česká republika",3);
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-06"), LocalDate.parse("2020-07-12"), 0);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-12"), LocalDate.parse("2020-07-18"), 0);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-18"), LocalDate.parse("2020-07-24"), 0);
        tripSessionDao.persist(tripSession);

        description = "Tento zajezd nevyzaduje zadne achievementy a po nem se nedaji ziskat specialni achievementy ale daji se ziskat achievementy jako jsou např. ´Kuchtík´, ´Kuchař´ apod. Odměna Xp je dost nízká aby se nedalo jednoduše dostat za tuhle práci na prestižnější místa jako pražský hrad, ale zároveň je možno si dopomoct s touto lehčí a dostupnější práci nahnat achievement kuchař, jestliže xp grind mám za sebou z jiných zájezdů." ;
        trip = new Trip("Kuchař menza Studentský dům, Praha",3,description,"studumkuch",50,"Praha, Česká republika",1);
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-06"), LocalDate.parse("2020-06-12"), 0);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-12"), LocalDate.parse("2020-06-18"), 0);
        tripSessionDao.persist(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-18"), LocalDate.parse("2020-06-24"), 0);
        tripSessionDao.persist(tripSession);
    }

    @Transactional
    void createAchievement(){
        AchievementCertificate achievement; /* = new Achievement("Habamalo","Habamalo is balabo con mambo.","location-arrow");
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
*/
        //ACHIEVEMENTY ZAJEZDOVE

        //Certifikáty
        achievement = new AchievementCertificate("Certifikát Angličtina B2", "Uživatel má certifikát B2 v anglickém jazyku.", "graduation-cap");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Certifikát Španěličina C1", "Uživatel má certifikát C1 v španělském jazyku.", "graduation-cap");
        achievementDao.persist(achievement);

        //Specifické achievementy
        achievement = new AchievementCertificate("Kuchař sushi", "Uživatel má zkušenosti s přípravou sushi.", "fish");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Kuchař ryby fugu", "Uživatel má zkušenosti s přípravou jedovatých ryb fugu.", "fish");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Horolezec", "Uživatel má zkušenosti s lezením po skalách.", "mountain");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Restaurátor hradů", "Uživatel má zkušenosti s restaurací starých památek.", "chess-rook");
        achievementDao.persist(achievement);

        //Achievementy za počet zájezdů do určité krajiny
        achievement = new AchievementCertificate("Skoro Ital!", "Uživatel byl už 5-krát v Itálii.", "flag");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Poprve do Ruska.", "Uživatel byl jednou v Rusku.", "snowflake");
        achievementDao.persist(achievement);

        //Achievementy za počet zájezdů v konkrétních kategoriích
        achievement = new AchievementCertificate("Kuchtík", "Uživatel byl jednou vařit.", "hamburger");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Kuchař", "Uživatel vařil už na 5-ti zájezdech.", "pizza-slice");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Pohl v Reichu", "Uživatel vařil už na 15-ti zájezdech.", "glass-cheers");
        achievementDao.persist(achievement);

        //ACHIEVEMENTY PROFILOVE

        //Achievementy za počty achievementů
        achievement = new AchievementCertificate("Do třetice všechno dobré!", "Uživatel získal svůj třetí achievement.", "trophy");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Kopa achievementů", "Uživatel získal svůj 60-tý achievement.", "crown");
        achievementDao.persist(achievement);

        //Achievementy za různorodost tripů achievementů
        achievement = new AchievementCertificate("Švýcarák", "Uživatel má za sebou více než 5 tripů z různých kategorii.", "pastafarianism");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Světoběžník", "Uživatel navštívil už 10 různých zemí.", "compass");
        achievementDao.persist(achievement);

        //Achievementy za délku registrace
        achievement = new AchievementCertificate("Už tři roky s námi", "Uživatel byl zaregistrován po dobu 3 let.", "glass-cheers");
        achievementDao.persist(achievement);

        //Achievementy za počet dní strávených na cestách
        achievement = new AchievementCertificate("Turista", "Uživatel procestoval 10 dní.", "running");
        achievementDao.persist(achievement);
        achievement = new AchievementCertificate("Poutník", "Uživatel procestoval 60 dní.", "campground");
        achievementDao.persist(achievement);
    }

    void setAchievementsAndCategories(){
        List<AchievementCertificate> achievements = achievementDao.findAll();
        List<Category> categories = categoryDao.findAll();

        Trip trip = tripDao.find("fugukurz");
        trip.addRequiredAchievement(achievements.get(8));
        trip.addGainAchievement(achievements.get(3));
        trip.setCategory(categories.get(4));
        tripDao.update(trip);

        trip = tripDao.find("fuguvar");
        achievements = achievementDao.findAll();
        trip.addRequiredAchievement(achievements.get(3));
        trip.setCategory(categories.get(0));
        tripDao.update(trip);

        trip = tripDao.find("prahradvar");
        achievements = achievementDao.findAll();
        trip.addRequiredAchievement(achievements.get(9));
        trip.setCategory(categories.get(0));
        tripDao.update(trip);

        trip = tripDao.find("studumkuch");
        achievements = achievementDao.findAll();
        trip.setCategory(categories.get(0));
        tripDao.update(trip);
    }

    void createCategories(){
        Category category = new Category("Vaření");
        categoryDao.persist(category);

        category = new Category("Archeologie");
        categoryDao.persist(category);

        category = new Category("Restaurování");
        categoryDao.persist(category);

        category = new Category("Práce instruktora");
        categoryDao.persist(category);

        category = new Category("Kurz");
        categoryDao.persist(category);

        category = new Category("Práce animátora");
        categoryDao.persist(category);
    }

    void createUsers(){
        User user = new User(BCrypt.hashpw("heslo",BCrypt.gensalt()),"Jan","Testovany","test@gmail.com",Role.USER);
        userDao.persist(user);
        Address address = new Address();
        address.setUser(user);
        address.setCountry("Slovakia");
        address.setCity("Kapusany");
        address.setStreet("Presovska");
        address.setHouseNumber(20);
        address.setZipCode("08001");
        addressDao.persist(address);
        user.setAddress(address);
        userDao.update(user);
        System.out.println("Test user persist.");
    }
}