package ua.sinitsyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.sinitsyn.dto.UserRegisterDto;
import ua.sinitsyn.exception.ThisEmailIsBusyException;
import ua.sinitsyn.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getPage(){
        return "register";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto user){

        try {
            userService.saveUser(user);
        } catch (ThisEmailIsBusyException e) {
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }




}
