package br.com.dbas.api.security.services;

import br.com.dbas.api.security.dtos.UserDto;
import br.com.dbas.api.security.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface UserService {

    User save(User user);
    List<User> saveAll(List<UserDto> userDtoList);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    Page<User> findAll(Pageable pageable);
    void delete(User user);
    User saveOrUpdate(User user, UserDto userDto);

}
