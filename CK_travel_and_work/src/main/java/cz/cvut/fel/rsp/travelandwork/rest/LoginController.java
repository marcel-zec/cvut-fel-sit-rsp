package cz.cvut.fel.rsp.travelandwork.rest;

import cz.cvut.fel.rsp.travelandwork.exception.AlreadyLoginException;
import cz.cvut.fel.rsp.travelandwork.service.security.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    private LoginService service;

    @Autowired
    public LoginController(LoginService service) {
         this.service = service;
    }

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody HashMap<String,String> request) throws AlreadyLoginException {

        service.login(request.get("username"),request.get("password"));
    }
}
