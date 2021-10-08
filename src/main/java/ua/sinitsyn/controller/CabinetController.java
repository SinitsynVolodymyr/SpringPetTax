package ua.sinitsyn.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.sinitsyn.model.Role;
import ua.sinitsyn.model.User;

@Controller
@RequestMapping("cabinet")
public class CabinetController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getPage(Authentication auth){
        User user = (User) auth.getPrincipal();

        if (user.getRole().equals(Role.USER)){
            return "redirect:/cabinet/user";
        }else if (user.getRole().equals(Role.INSPECTOR)){
            return "redirect:/cabinet/inspector";
        }
        return "redirect:/";
    }

}
