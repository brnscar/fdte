package br.com.dbas.api.security.repositories;


import br.com.dbas.api.security.entities.Endpoint;
import br.com.dbas.api.security.entities.Page;
import br.com.dbas.api.security.enums.HttpMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {

    @Transactional(readOnly = true)
    List<Endpoint> findByUrl(String url);
    @Transactional(readOnly = true)
    Endpoint findByPage(Page page);
    @Transactional(readOnly = true)
    List<Endpoint> findByHttpMethod(HttpMethod httpMethod);

}
