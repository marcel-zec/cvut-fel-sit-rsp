package cz.cvut.fel.ear.rsp.travelandwork.rest;

import cz.cvut.fel.ear.rsp.travelandwork.exception.AlreadyLoginException;
import cz.cvut.fel.ear.rsp.travelandwork.service.security.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class LoginController {

    private LoginService service;

    @Autowired
    public LoginController(LoginService service) {
         this.service = service;
    }

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public void loginStudent(@RequestBody HashMap<String,String> request) throws AlreadyLoginException {

        service.loginStudent(request.get("username"),request.get("password"));
    }

    @PostMapping(value = "/login_manager",produces = MediaType.APPLICATION_JSON_VALUE)
    public void loginManager(@RequestBody HashMap<String,String> request) throws AlreadyLoginException {

        service.loginManager(request.get("username"),request.get("password"));
    }
}
