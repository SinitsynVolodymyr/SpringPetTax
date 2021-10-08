package ua.sinitsyn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cabinet/user")
public class UserCabinetController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getPage(){
        return "UserCabinet";
    }

}
