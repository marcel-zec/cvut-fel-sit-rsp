package cz.cvut.fel.rsp.travelandwork.seeder;

import cz.cvut.fel.rsp.travelandwork.dao.*;
import cz.cvut.fel.rsp.travelandwork.dto.TripSessionDto;
import cz.cvut.fel.rsp.travelandwork.model.*;
import cz.cvut.fel.rsp.travelandwork.service.TranslateService;
import cz.cvut.fel.rsp.travelandwork.service.TripService;
import cz.cvut.fel.rsp.travelandwork.service.UserService;
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
    private AchievementCertificateDao achievementCertificateDao;
    private AchievementCategorizedDao achievementCategorizedDao;
    private AchievementSpecialDao achievementSpecialDao;
    private CategoryDao categoryDao;
    private UserDao userDao;
    private AddressDao addressDao;
    private TripService tripService;
    private TranslateService translateService;

    @Autowired
    public DatabaseSeeder(TripDao tripDao, TripSessionDao tripSessionDao, AchievementCertificateDao achievementCertificateDao, AchievementCategorizedDao achievementCategorizedDao, AchievementSpecialDao achievementSpecialDao, CategoryDao categoryDao, UserDao userDao, AddressDao addressDao, TripService tripService, TranslateService translateService) {
        this.tripDao = tripDao;
        this.tripSessionDao = tripSessionDao;
        this.achievementCertificateDao = achievementCertificateDao;
        this.achievementCategorizedDao = achievementCategorizedDao;
        this.achievementSpecialDao = achievementSpecialDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.tripService = tripService;
        this.translateService = translateService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //TODO - vykona sa hned po spusteni
        System.out.println("Vypis po stupusteni aplikacie.");

        createCategories();
        createAchievement();
        createTrips();
        setAchievementsAndCategories();
        createUsers();
        signUsersToTrips();

    }

    @Transactional
    void createTrips(){
        String description;// = "Nullam gravida lectus tempus congue pretium. Nunc volutpat diam orci, a consectetur dui iaculis sollicitudin. Fusce varius nisi placerat turpis viverra pulvinar. Pellentesque vel commodo nibh, sed volutpat nunc. Duis congue enim malesuada sapien commodo egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum interdum, ante eu vehicula porttitor, libero purus consequat metus, quis aliquet lectus orci sit amet mi. Aenean libero sapien, tempus sit amet lorem in, cursus sodales erat. Vivamus suscipit felis et ex pulvinar, vitae rutrum diam tempus. ";
        Trip trip;/* = new Trip("Casablanca Me gusto",15,description,"casablanca_me_gusta",1000,"Casablan, Mexico",2);
        tripDao.persist(trip);
        */TripSession tripSession;

        //priklady tripov a user progressu medzi nimi
        description = "Tento zajezd bude mit cenu za dopravu a kurz, po absolvování se odemkne achievement ´kuchař ryb fugu´, pro absolvování je potřeba mít achievement ´Kuchtík´." ;
        trip = new Trip("Kurz vaření ryb Fugu",10,description,"fugukurz",1000,"Tokyo, Japan",1);
        //trip.addGainAchievement();
        //trip.addRequiredAchievement();
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-06"), LocalDate.parse("2020-06-12"), 3000);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-12"), LocalDate.parse("2020-06-18"), 3000);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-18"), LocalDate.parse("2020-06-24"), 3000);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripDao.update(trip);

        description = "Tento zajezd bude mit zalohu, pro absolvování je potřeba mít achievement ´Kuchař ryb fugu´." ;
        trip = new Trip("Vaření ryb Fugu, Praha",10,description,"fuguvar",3459,"Praha, Česká republika",1);
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-06"), LocalDate.parse("2020-07-12"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-12"), LocalDate.parse("2020-07-18"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-18"), LocalDate.parse("2020-07-24"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripDao.update(trip);

        description = "Tento zajezd bude mit zalohu, pro absolvování je potřeba mít achievement ´Kuchař´." ;
        trip = new Trip("Kuchař na Pražském hradě",8,description,"prahradvar",1000,"Praha, Česká republika",3);
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-06"), LocalDate.parse("2020-07-12"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-12"), LocalDate.parse("2020-07-18"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-07-18"), LocalDate.parse("2020-07-24"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripDao.update(trip);

        description = "Tento zajezd nevyzaduje zadne achievementy a po nem se nedaji ziskat specialni achievementy ale daji se ziskat achievementy jako jsou např. ´Kuchtík´, ´Kuchař´ apod. Odměna Xp je dost nízká aby se nedalo jednoduše dostat za tuhle práci na prestižnější místa jako pražský hrad, ale zároveň je možno si dopomoct s touto lehčí a dostupnější práci nahnat achievement kuchař, jestliže xp grind mám za sebou z jiných zájezdů." ;
        trip = new Trip("Kuchař menza Studentský dům, Praha",3,description,"studumkuch",50,"Praha, Česká republika",1);
        tripDao.persist(trip);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-06"), LocalDate.parse("2020-06-12"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-12"), LocalDate.parse("2020-06-18"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripSession = new TripSession(trip, LocalDate.parse("2020-06-18"), LocalDate.parse("2020-06-24"), 0);
        tripSessionDao.persist(tripSession);
        trip.addSession(tripSession);
        tripDao.update(trip);
    }

    @Transactional
    void createAchievement(){
        AchievementCertificate achievementCertificate;
        AchievementSpecial achievementSpecial;
        AchievementCategorized achievementCategorized;
        //ACHIEVEMENTY ZAJEZDOVE

        //Certifikáty
        achievementCertificate = new AchievementCertificate("Certifikát Angličtina B2", "Uživatel má certifikát B2 v anglickém jazyku.", "graduation-cap"); //0
        achievementCertificateDao.persist(achievementCertificate);
        achievementCertificate = new AchievementCertificate("Certifikát Španěličina C1", "Uživatel má certifikát C1 v španělském jazyku.", "graduation-cap"); //1
        achievementCertificateDao.persist(achievementCertificate);

        //Specifické achievementy
        achievementSpecial = new AchievementSpecial("Kuchař sushi", "Uživatel má zkušenosti s přípravou sushi.", "fish"); //0
        achievementSpecialDao.persist(achievementSpecial);

        achievementSpecial = new AchievementSpecial("Kuchař ryby fugu", "Uživatel má zkušenosti s přípravou jedovatých ryb fugu.", "fish"); //1
        achievementSpecialDao.persist(achievementSpecial);

        achievementSpecial = new AchievementSpecial("Horolezec", "Uživatel má zkušenosti s lezením po skalách.", "mountain"); //2
        achievementSpecialDao.persist(achievementSpecial);

        achievementSpecial = new AchievementSpecial("Restaurátor hradů", "Uživatel má zkušenosti s restaurací starých památek.", "chess-rook"); //3
        achievementSpecialDao.persist(achievementSpecial);

        //Achievementy za počet zájezdů v konkrétních kategoriích
        /*achievementCategorized = new AchievementCategorized("Kuchtík", "Uživatel byl jednou vařit.", "hamburger"); //0
        achievementCategorized.setCategory(categoryDao.findAll().get(0));
        achievementCategorized.setLimit(1);
        achievementCategorizedDao.persist(achievementCategorized);*/

        achievementCategorized = new AchievementCategorized("Kuchař", "Uživatel vařil už na 5-ti zájezdech.", "pizza-slice"); //1
        achievementCategorized.setCategory(categoryDao.findAll().get(0));
        achievementCategorized.setLimit(5);
        achievementCategorizedDao.persist(achievementCategorized);

        achievementCategorized = new AchievementCategorized("Pohl v Reichu", "Uživatel vařil už na 15-ti zájezdech.", "glass-cheers"); //2
        achievementCategorized.setCategory(categoryDao.findAll().get(0));
        achievementCategorized.setLimit(15);
        achievementCategorizedDao.persist(achievementCategorized);
    }

    void setAchievementsAndCategories(){
        List<AchievementCertificate> certificates = achievementCertificateDao.findAll();
        List<AchievementCategorized> categorized =achievementCategorizedDao.findAll();
        List<AchievementSpecial> special = achievementSpecialDao.findAll();
        List<Category> categories = categoryDao.findAll();
        List<Trip> trips = tripDao.findAll();

        //IT IS DONE THE HARD WAY BECAUSE AUTHOR (ME) IS LITERALLY BRAINDED RETARD
        //ak budete mat problem sa orientovat v tejto casti nemam vam to za zle je to humus...
        //O.S.

        Trip trip = trips.get(0);
        trip.addRequired_achievements_categorized(categorized.get(0));
        categorized.get(0).addTrips(trip);
        trip.addGain_achievements_special(special.get(1));
        special.get(1).addTrips(trip);
        trip.setCategory(categories.get(4));
        categories.get(4).add(trip);

        trip =  trips.get(1);
        trip.addRequired_achievements_special(special.get(1));
        special.get(1).addTrips(trip);
        trip.setCategory(categories.get(0));
        categories.get(0).add(trip);

        trip =  trips.get(2);
        trip.addRequired_achievements_categorized(categorized.get(1));
        categorized.get(1).addTrips(trip);
        trip.setCategory(categories.get(0));
        categories.get(0).add(trip);

        trip =  trips.get(3);
        trip.setCategory(categories.get(0));
        categories.get(0).add(trip);

        //jediny zlepsovak co tu je aj ked by to but ani nemusel...
        for(Trip t : trips) {
            tripDao.update(t);
        }
        for(AchievementCategorized ac : categorized) {
            achievementCategorizedDao.update(ac);
        }
        for(AchievementCertificate cert : certificates) {
            achievementCertificateDao.update(cert);
        }
        for(AchievementSpecial spec : special) {
            achievementSpecialDao.update(spec);
        }
        for(Category category : categories) {
            categoryDao.update(category);
        }

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

        //user Jan
        User user = new User(BCrypt.hashpw("hesloo",BCrypt.gensalt()),"Jan","Testovany","user@gmail.com");
        user.setRole(Role.USER);

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

        //user Milan
        user = new User(BCrypt.hashpw("hesloo",BCrypt.gensalt()),"Milan","Netestovany","milan@gmail.com");
        user.setRole(Role.USER);

        userDao.persist(user);
        address = new Address();
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

        //admin Peter
        user = new User(BCrypt.hashpw("hesloo",BCrypt.gensalt()),"Peter","Testovany","admin@gmail.com");
        user.setRole(Role.ADMIN);
        userDao.persist(user);
        address = new Address();
        address.setUser(user);
        address.setCountry("Slovakia");
        address.setCity("Licartovce");
        address.setStreet("Vranovska");
        address.setHouseNumber(20);
        address.setZipCode("05175");
        addressDao.persist(address);
        user.setAddress(address);
        userDao.update(user);
        System.out.println("Test admin persist.");
    }

    void signUsersToTrips() {
        User user = userDao.findAll().get(0);
        Trip trip = tripDao.findAll().get(0);
        TripSession tripSession = trip.getSessions().get(0);

/*
        //test
        System.out.println("SESSION: " + tripSession.toString());
        System.out.println("TRIP IN SESSION: " + tripSession.getTrip().toString());
        System.out.println("CATEGORIZED IN TRIP: " + trip.getRequired_achievements_categorized().toString());
        System.out.println("CAT IN TRIP DTO: " + translateService.translateAchievementCategorized(trip.getRequired_achievements_categorized().get(0)));
        System.out.println("TRIP DTO: " + translateService.translateTrip(trip).toString());
        System.out.println("TRIP DTO: " + translateService.translateTrip(tripSession.getTrip()).toString());
        System.out.println("SESSION DTO: " + translateService.translateSession(tripSession));
*/

        if(tripSession != null && tripSession.getTrip() != null) {
            signUserToTrip(user, tripSession);
        }

        user = userDao.findAll().get(0);
        trip = tripDao.findAll().get(1);
        tripSession = trip.getSessions().get(0);
        signUserToTrip(user, tripSession);

        user = userDao.findAll().get(1);
        trip = tripDao.findAll().get(0);
        tripSession = trip.getSessions().get(1);
        signUserToTrip(user, tripSession);

        user = userDao.findAll().get(1);
        trip = tripDao.findAll().get(1);
        tripSession = trip.getSessions().get(1);
        signUserToTrip(user, tripSession);
    }

    void signUserToTrip(User user, TripSession tripSession) {
        TripSessionDto tripSessionDto;

        tripSessionDto = translateService.translateSession(tripSession);
        tripService.signUpToTrip(tripSessionDto, user);
    }
}
