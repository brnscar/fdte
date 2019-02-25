package br.com.dbas.api.security.repositories;

import br.com.dbas.api.security.entities.Authorities;
import br.com.dbas.api.security.entities.Endpoint;
import br.com.dbas.api.security.entities.Page;
import br.com.dbas.api.security.entities.User;
import br.com.dbas.api.security.enums.HttpMethod;
import br.com.dbas.api.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private EndpointRepository endpointRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    public User getUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(PasswordUtils.gerarBCrypt("admin"));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAuthorities(getAuthorities());
        userRepository.save(user);
        return user;
    }

    public Collection<Authorities> getAuthorities() {
        return getAuthorities(getEndpoint());
    }

    public Collection<Authorities> getAuthorities(Endpoint endpoint){
        Authorities authorities = new Authorities();
        authorities.setAuthority("ADMIN");
        authorities.setDescription("Administrador do sistema");
        Set<Endpoint> endpointSet = new HashSet<Endpoint>();
        endpointSet.add(endpoint);
        authorities.setEndpoints(endpointSet);
        authoritiesRepository.saveAndFlush(authorities);
        ArrayList<Authorities> list = new ArrayList<Authorities>();
        list.add(authorities);
        return list;
    }

    public Endpoint getEndpoint() {
        return getEndpoint(getPage());
    }

    public Endpoint getEndpoint(Page page) {
        Endpoint endpoint = new Endpoint();
        endpoint.setDescription("Busca endpoint");
        endpoint.setHttpMethod(HttpMethod.GET);
        endpoint.setUrl("/endpoint");
        endpoint.setPage(page);
        endpointRepository.saveAndFlush(endpoint);
        return endpoint;
    }

    public Page getPage() {
        Page page = new Page();
        page.setDescription("Gerenciar usuarios e perfils");
        page.setName("USUARIO");
        return pageRepository.save(page);
    }

}
