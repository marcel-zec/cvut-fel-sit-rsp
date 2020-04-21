package cz.cvut.fel.rsp.travelandwork.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.rsp.travelandwork.config.AppConfig;
import cz.cvut.fel.rsp.travelandwork.environment.config.PropertyMockingApplicationContextInitializer;
import cz.cvut.fel.rsp.travelandwork.environment.config.TestSecurityConfig;
import cz.cvut.fel.rsp.travelandwork.environment.util.Environment;
import cz.cvut.fel.rsp.travelandwork.environment.util.Generator;
import cz.cvut.fel.rsp.travelandwork.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TestSecurityConfig.class,
        AppConfig.class}, initializers = PropertyMockingApplicationContextInitializer.class)
public class AuthenticationSuccessTest{
    /*

    private User user = Generator.generateUser();

    @Autowired
    private AuthenticationSuccess success;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Environment environment;

    @Test
    public void authenticationSuccessReturnsResponseContainingUsername() throws Exception {
        final MockHttpServletResponse response = response();
        success.onAuthenticationSuccess(request(), response, generateAuthenticationToken());
        verifyLoginStatus(response);
    }

    private void verifyLoginStatus(MockHttpServletResponse response) throws java.io.IOException {
        final LoginStatus status = mapper.readValue(response.getContentAsString(), LoginStatus.class);
        assertTrue(status.isSuccess());
        assertTrue(status.isLoggedIn());
        assertEquals(person.getUsername(), status.getUsername());
        assertNull(status.getErrorMessage());
    }

    static MockHttpServletRequest request() {
        return new MockHttpServletRequest();
    }

    static MockHttpServletResponse response() {
        return new MockHttpServletResponse();
    }

    private Authentication generateAuthenticationToken() {
        final UserDetails userDetails = new UserDetails(person);
        return new AuthenticationToken(userDetails.getAuthorities(), userDetails);
    }

    @Test
    public void logoutSuccessReturnsResponseContainingLoginStatus() throws Exception {
        final MockHttpServletResponse response = response();
        success.onLogoutSuccess(request(), response, generateAuthenticationToken());
        final LoginStatus status = mapper.readValue(response.getContentAsString(), LoginStatus.class);
        assertTrue(status.isSuccess());
        assertFalse(status.isLoggedIn());
        assertNull(status.getUsername());
        assertNull(status.getErrorMessage());
    }

     */

}
