package ua.sinitsyn.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getPage(Authentication auth){
        if (auth==null) return "notAuthHome";
        else return "authHome";
    }

}
