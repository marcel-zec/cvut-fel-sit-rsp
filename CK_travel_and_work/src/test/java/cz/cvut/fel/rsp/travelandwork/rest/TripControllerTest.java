package cz.cvut.fel.rsp.travelandwork.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.cvut.fel.rsp.travelandwork.dto.TripDto;
import cz.cvut.fel.rsp.travelandwork.dto.TripSessionDto;
import cz.cvut.fel.rsp.travelandwork.environment.util.Generator;
import cz.cvut.fel.rsp.travelandwork.model.Trip;
import cz.cvut.fel.rsp.travelandwork.model.TripSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class TripControllerTest extends AbstractTest {

    private List<TripDto> tripDto;

    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void getAllTrips() throws Exception {
        String uri = "/trip";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void filterTest() throws Exception {
        System.out.println("TripControllerTest - filterTest");
        String uri = "/trip/filter?location=Tokyo, Japan&max_price=4000&from_date=2020-06-07&to_date=2020-06-18";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void getTest() throws Exception {
        String uri = "/trip/fuguvar";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser(username = "admin@gmail.com",roles={"USER","ADMIN"})
    public void createTripTest()throws Exception{
        Trip trip = new Trip("test1",10,"Description","shortName",2000,"Hawaii",3);
        trip.setSessions(Collections.singletonList(new TripSession(trip, LocalDate.now(), LocalDate.now().plusDays(7), 2000)));
        String uri = "/trip";
        String inputJson = super.mapToJson(trip);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser(username = "admin@gmail.com",roles={"USER","ADMIN","SUPERADMIN"})
    public void updateTest() throws Exception {
        String uri = "/trip/fuguvar";
        Trip trip = new Trip("test1",10,"Description","shortName",2000,"Hawaii",3);
        trip.setSessions(Collections.singletonList(new TripSession(trip, LocalDate.now(), LocalDate.now().plusDays(7), 2000)));
        trip.setDeposit(1945);
        String inputJson = super.mapToJson(trip);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }

    @Test
    @WithMockUser(username = "admin@gmail.com",roles={"USER","ADMIN","SUPERADMIN"})
    public void deleteTest() throws Exception {
        String uri = "/trip/fuguvar";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }

    @Test
    @WithMockUser(username = "admin@gmail.com",roles={"USER","ADMIN","SUPERADMIN"})
    public void signUpToTripTest() throws Exception {
        String uri = "/trip/fuguvar";

        TripSessionDto tripSessionDto = new TripSessionDto();

        String inputJson = super.mapToJson(tripSessionDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Trip is created successfully");
    }

//    @Test
//    @WithMockUser(username = "admin@gmail.com",roles={"USER","ADMIN","SUPERADMIN"})
//    public void showAllTripsCantUserAffordTest() throws Exception {
//        String uri = "/trip/cannotAfford";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
//
//    @Test
//    @WithMockUser(username = "user@gmail.com")
//    public void showAllTripsCanUserAffordTest() throws Exception {
//        String uri = "/trip/canAfford";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }
}
