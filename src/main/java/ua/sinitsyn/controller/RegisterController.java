package ua.sinitsyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.sinitsyn.dto.UserRegisterDto;
import ua.sinitsyn.exception.ThisEmailIsBusyException;
import ua.sinitsyn.service.UserService;
import org.postgresql.util.*;

import java.sql.SQLException;
import java.util.Locale;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("/register")
public class RegisterController {
    static Logger log = Logger.getLogger(RegisterController.class.getName());

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getPage(){
        return "register";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto user){
        log.info("Request to register user: "+user.getEmail());
        try {
            userService.saveUser(user);
        } catch (DataIntegrityViolationException e) {
             log.info("Email conflict to register user: "+user.getEmail());
             return new ResponseEntity<String>(HttpStatus.CONFLICT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
