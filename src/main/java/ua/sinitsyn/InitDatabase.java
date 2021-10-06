package ua.sinitsyn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.sinitsyn.model.Role;
import ua.sinitsyn.model.User;
import ua.sinitsyn.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InitDatabase implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public void run(String[] strings){
        List<User> userList = Stream.of(
                new User(
                        "user@email.com"
                        , "$2a$12$7lSB0x8eD42B8jFlbhFh5e81ydMU5bTPtL0Ah4O.wwbmhJJetTTJq"
                        , Role.USER),
                new User(
                        "insp@email.com"
                        , "$2a$12$q0s6EWp1UcXtcAPdtmXvfuGOzlYPhdvvWlzYTHGQ4nPOekQRrEJce"
                        , Role.INSPECTOR)
        ).collect(Collectors.toList());

        if (!checkIsInitUserTable(userList)){
            userList.stream().forEach(user -> userService.saveUser(user));
        }
    }

    private boolean checkIsInitUserTable(List<User> userList){
        String email = userList.stream().findFirst().get().getEmail();
        try {
            userService.loadUserByUsername(email);
        }catch (UsernameNotFoundException e){
            return false;
        }
        return true;
    }

}
