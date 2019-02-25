package com.app.service;

import com.app.exceptions.RegistrationException;
import com.app.model.dto.UserDto;
import com.app.model.dto.mappers.ConverterDto;
import com.app.model.user.Role;
import com.app.model.user.User;
import com.app.model.VerificationToken;
import com.app.repository.UserRepository;
import com.app.repository.VerificationTokenRepository;
import com.app.service.listeners.OnRegistrationCompleteEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserService {

    private ConverterDto converterDto;
    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ApplicationEventPublisher eventPublisher;

    public UserService(ConverterDto converterDto, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationEventPublisher eventPublisher) {

        this.converterDto = converterDto;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.eventPublisher = eventPublisher;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void registerNewUserAccount(UserDto userDto, HttpServletRequest request) {
        if (userRepository.findByUsername(userDto.getUsername()) != null || userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RegistrationException("UZYTKOWNIK O PODANYM EMAILU LUB NAZWIE UZYTKOWNIAK JUZ ISTNIEJE", LocalDateTime.now());
        }

        if (!userDto.getPasswordConfirmation().equals(userDto.getPassword())) {
            throw new RegistrationException("POLE HASLO I POTWIERDZ HASLO POWINNY MIEC TAKIE SAME WARTOSCI", LocalDateTime.now());
        }

        User user = converterDto.fromUserDtoToUser(userDto);

        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setActive(false);
        user.setRole(Role.USER);


        final String appURL = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
                user, appURL
        ));


        userRepository.save(user);
    }


    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createVerificationToken(User user, String token) {
        verificationTokenRepository.save(VerificationToken
                .builder()
                .token(token)
                .user(user)
                .expirationDateTime(LocalDateTime.now().plusMinutes(1))
                .build());
    }

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public Long getLoggedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Long userId = getUserId(name);
        return userId;
    }

    public Long getUserId(String userName) {
        User user = userRepository.findByUsername(userName);
        return user.getId();
    }

    public User getLoggedUser(String token)
    {
        return verificationTokenRepository.findByToken(token).getUser();
    }

    public User getLoggedUser()
    {
        return userRepository.findById(getLoggedUserId()).orElseThrow(NullPointerException::new);
    }


}
