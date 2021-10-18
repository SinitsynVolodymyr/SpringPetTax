package ua.sinitsyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.sinitsyn.dto.ReportDto;
import ua.sinitsyn.dto.UserRegisterDto;
import ua.sinitsyn.dto.UserVerifyDto;
import ua.sinitsyn.model.Report;
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
    @Autowired
    ReportService reportService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email Not Found"));
    }
/*
    public void addNewReport(UserVerifyDto userDto, Report report){
        userRepository.s.findByEmail(userDto.getEmail());
    }
*/
    public User saveUser(UserRegisterDto userDto) {
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

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
