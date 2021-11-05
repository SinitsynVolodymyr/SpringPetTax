package ua.sinitsyn.controller;


import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.sinitsyn.model.Role;
import ua.sinitsyn.model.User;

@Controller
@RequestMapping("cabinet")
public class CabinetController {
    static Logger log = Logger.getLogger(CabinetController.class.getName());

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
