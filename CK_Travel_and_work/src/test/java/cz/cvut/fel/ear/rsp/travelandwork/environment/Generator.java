package cz.cvut.fel.ear.rsp.travelandwork.environment;

import cz.cvut.fel.ear.rsp.travelandwork.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    private static LocalDate startDate = LocalDate.parse("2010-10-10");
    private static LocalDate endDate = LocalDate.parse("2040-10-10");


    public static int randomInt() {
        return RAND.nextInt();
    }


    public static boolean randomBoolean() {
        return RAND.nextBoolean();
    }


}