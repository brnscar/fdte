package br.com.dbas.api.security.controllers;

import br.com.dbas.api.response.Response;
import br.com.dbas.api.response.ResponseList;
import br.com.dbas.api.response.ResponsePage;
import br.com.dbas.api.security.dtos.UserDto;
import br.com.dbas.api.security.entities.User;
import br.com.dbas.api.security.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<ResponsePage<User>> getUser(Pageable pageRequest) throws AuthenticationException {
        ResponsePage<User> response = new ResponsePage<>();
        log.info("getUsuario");
        response.setData(service.findAll(pageRequest));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<User>> addUser(@Valid @RequestBody User user, BindingResult result) throws Exception {
        log.info("add usuario: {}",user.toString());
        Response<User> response = new Response<>();
        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(service.save(user));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<User>> put(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto, BindingResult result) {
        log.info("Atualizando usuario: {}", userDto.toString());
        Response<User> response = new Response<>();
        User user = service.findById(id).orElseGet(User::new);
        if (result.hasErrors()) {
            log.error("Erro validando funcionário: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(service.saveOrUpdate(user,userDto));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ResponseList<User>> put(@Valid @RequestBody List<UserDto> userDtoList, BindingResult result) {
        log.info("Atualizando usuarios");
        ResponseList<User> response = new ResponseList<>();

        if (result.hasErrors()) {
            log.error("Erro validando funcionário: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        List<User> users = service.saveAll(userDtoList);
        response.setData(users);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
        log.info("Removendo usuario: {}", id);
        Response<String> response = new Response<>();
        Optional<User> user = this.service.findById(id);

        if (!user.isPresent()) {
            log.info("Erro ao remover ID: {} inválido.", id);
            response.getErrors().add("Erro, registro não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }
        this.service.delete(user.get());
        return ResponseEntity.ok(response);
    }

}
