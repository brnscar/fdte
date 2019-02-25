package br.com.dbas.api.security.repositories;

import br.com.dbas.api.security.entities.Authorities;
import br.com.dbas.api.security.entities.Endpoint;
import br.com.dbas.api.security.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    @Transactional(readOnly = true)
    List<Authorities> findByAuthority(String authority);
    @Transactional(readOnly = true)
    List<Authorities> findByEndpoints(Endpoint endpoint);

}
