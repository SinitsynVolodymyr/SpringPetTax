package ua.sinitsyn;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.sinitsyn.exception.ThisEmailIsBusyException;
import ua.sinitsyn.model.Role;
import ua.sinitsyn.model.TypeOrganisation;
import ua.sinitsyn.model.User;
import ua.sinitsyn.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InitDatabase implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    List<User> userList = Stream.of(
            new User(
                    "user1@email.com"
                    ,"user1"
                    , "user1"
                    , Role.USER
                    , TypeOrganisation.ENTREPRENEUR
            ),
            new User(
                    "user2@email.com"
                    ,"user2"
                    , "user2"
                    , Role.USER
                    , TypeOrganisation.ENTERPRISE
            ),
            new User(
                    "insp@email.com"
                    ,"inspector"
                    , "ins"
                    , Role.INSPECTOR
                    , TypeOrganisation.NONE
            )
    ).collect(Collectors.toList());


    public void run(String[] strings){

        if (!checkIsInitUserTable(userList)){
            userList.stream().forEach(user -> {
                    userService.saveUser(user);

            });
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
