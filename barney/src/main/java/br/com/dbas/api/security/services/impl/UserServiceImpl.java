package br.com.dbas.api.security.services.impl;

import br.com.dbas.api.security.dtos.UserDto;
import br.com.dbas.api.security.entities.User;
import br.com.dbas.api.security.repositories.UserRepository;
import br.com.dbas.api.security.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.dbas.api.utils.PasswordUtils.gerarBCrypt;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        log.debug("Save user" + user.toString());
        user.setPassword(gerarBCrypt(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> saveAll(List<UserDto> userDtoList) {
        log.debug("saveAll");
        List<User> users = new ArrayList<>();
        userDtoList.forEach( userDto -> {
            User user = dtoUser(new User(), userDto);
            String crypt = user.getPassword();
            user.setPassword(gerarBCrypt(crypt));
            users.add(user);
        });
        return userRepository.saveAll(users);
    }

    public Optional<User> findByUsername(String username) {
        log.debug("findByUserName =["+username);
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public Optional<User> findById(Long id) {
        log.debug("findById =["+id);
        return userRepository.findById(id);
    }

    public Page<User> findAll(Pageable pageable) {
        log.debug("findAll user");
        return userRepository.findAll(pageable);
    }

    public void delete(User user) {
        log.debug("delete =["+user.toString());
        userRepository.delete(user);
    }

    public User saveOrUpdate(User user, UserDto userDto) {
        return save(dtoUser(user,userDto));
    }

    private User dtoUser(User user, UserDto userDto) {
        user.setAuthorities(userDto.getAuthorities());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setAccountNonExpired(userDto.isAccountNonExpired());
        user.setAccountNonLocked(userDto.isAccountNonLocked());
        user.setEnabled(userDto.isEnabled());
        user.setCredentialsNonExpired(userDto.isCredentialsNonExpired());
        return user;
    }

}
