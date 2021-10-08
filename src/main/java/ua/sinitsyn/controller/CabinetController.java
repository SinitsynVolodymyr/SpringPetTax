package ua.sinitsyn.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.sinitsyn.model.Role;
import ua.sinitsyn.model.User;

@Slf4j
@Controller
@RequestMapping("cabinet")
public class CabinetController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getPage(Authentication auth){
        User user = (User) auth.getPrincipal();

        if (user.getRole().equals(Role.USER)){
            log.info("Cabinet redirect to /cabinet/user");
            return "redirect:/cabinet/user";
        }else if (user.getRole().equals(Role.INSPECTOR)){
            log.info("Cabinet redirect to /cabinet/inspector");
            return "redirect:/cabinet/inspector";
        }
        log.error("Not equals role in user");
        return "redirect:/";
    }

}
