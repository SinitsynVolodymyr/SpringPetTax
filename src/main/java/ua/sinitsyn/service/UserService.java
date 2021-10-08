package ua.sinitsyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.sinitsyn.dto.UserRegisterDto;
import ua.sinitsyn.exception.ThisEmailIsBusyException;
import ua.sinitsyn.model.Role;
import ua.sinitsyn.model.TypeOrganisation;
import ua.sinitsyn.model.User;
import ua.sinitsyn.repo.UserRepository;

import java.util.Locale;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email Not Found"));
        return user;
    }

    public User saveUser(UserRegisterDto userDto) throws ThisEmailIsBusyException {
        if (userDto.getTypeOrganisation().equalsIgnoreCase(
                TypeOrganisation.NONE.name()))
            throw new IllegalArgumentException();

        User user = new User(
                userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword(),
                Role.USER,
                TypeOrganisation.valueOf(
                        userDto.getTypeOrganisation().toUpperCase(Locale.ROOT))
        );

        return this.saveUser(user);
    }

    public User saveUser(User user) throws ThisEmailIsBusyException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ThisEmailIsBusyException(user.getEmail());
        }
        return userRepository.save(user);
    }

}
