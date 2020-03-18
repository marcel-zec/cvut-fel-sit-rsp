package cz.cvut.fel.ear.rsp.travelandwork.seeder;

import cz.cvut.fel.ear.rsp.travelandwork.dao.*;
import cz.cvut.fel.ear.rsp.travelandwork.exception.AlreadyExistsException;
import cz.cvut.fel.ear.rsp.travelandwork.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.logging.Logger;

@Component
public class DatabaseSeeder implements
        ApplicationListener<ContextRefreshedEvent> {

    private Logger LOGGER = Logger.getLogger(DatabaseSeeder.class.getName());


    @Autowired
    public DatabaseSeeder() {
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //TODO - vykoná sa po spustení aplikácie
    }
}