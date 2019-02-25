package br.com.dbas.api.security.repositories;

import br.com.dbas.api.security.entities.Page;
import br.com.dbas.api.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;

public interface PageRepository extends JpaRepository<Page, Long> {

    @Transactional(readOnly = true)
    Page findByName(String name);

}
