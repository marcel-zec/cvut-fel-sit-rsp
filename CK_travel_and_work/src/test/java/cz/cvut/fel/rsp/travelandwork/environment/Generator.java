package cz.cvut.fel.rsp.travelandwork.environment;

import cz.cvut.fel.rsp.travelandwork.model.Address;
import cz.cvut.fel.rsp.travelandwork.model.Role;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.User;
import cz.cvut.fel.rsp.travelandwork.service.UserService;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return RAND.nextInt();
    }

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFHIJKLMNOPQRSTUVWXYZ0123456789";

    public static boolean randomBoolean() {
        return RAND.nextBoolean();
    }

    private static String generatePassword(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(ALPHABET.charAt(RAND.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    private static Address generateAddress(User user){
        return new Address("City " + randomInt(), "Street " + randomInt(), randomInt(), "Z71" + randomInt(), "Country " + randomInt(), user);
    }


    /**
     * Generates user
     * @return user
     */
    public static User generateUser() {
        String username =  "UserrNo"+ randomInt();
        String email = username + "imejl.cz";
        final User user = new User(username, generatePassword(), "Tester", "Testovany", email, Role.USER);
        user.setAddress(generateAddress(user));
        return user;
    }


    /**
     * Generates trip
     * @return generated trip
     */
    public static Trip generateTrip() {
       return new Trip("trip no." + randomInt(), randomInt(), "a really good trip", "12345678" + randomInt(), "trip" + randomInt());
    }


    /**
     * Generates date
     * @return date
     */
    public static Date generateDate(){
        long ms;
        ms = 1609459200 + (Math.abs(RAND.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }


    /**
     * Generates earliest date than date in parameter
     * @param latestDate
     * @return date
     */
    public static Date generateDate(Date latestDate){
        return new Date(ThreadLocalRandom.current().nextLong(1609459200, latestDate.getTime()));
    }

    /**
     * Generates random date between earliest and latest dates
     * @param earliestDate
     * @param latestDate
     * @return date
     */
    public static Date generateDate(Date earliestDate, Date latestDate){
        return new Date(ThreadLocalRandom.current().nextLong(earliestDate.getTime(), latestDate.getTime()));
    }

    /**
     * Generates time
     * @return time
     */
    public static Time generateTime(){
        final int millisInDay = 24*60*60*1000;
        return new Time((long)RAND.nextInt(millisInDay));
    }

}