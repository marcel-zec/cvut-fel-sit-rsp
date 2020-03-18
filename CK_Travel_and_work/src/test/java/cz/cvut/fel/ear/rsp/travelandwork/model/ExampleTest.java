package cz.cvut.fel.ear.rsp.travelandwork.model;

import cz.cvut.fel.ear.rsp.travelandwork.exception.AlreadyExistsException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before


    @Test
    public void example() throws AlreadyExistsException {
        thrown.expect(AlreadyExistsException.class);
    }
}
