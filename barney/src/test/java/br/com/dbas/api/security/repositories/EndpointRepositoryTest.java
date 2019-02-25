package br.com.dbas.api.security.repositories;

import br.com.dbas.api.security.entities.Page;
import br.com.dbas.api.security.enums.HttpMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class EndpointRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private EndpointRepository endpointRepository;

    @Before
    public void setUp() throws Exception {
        Page page = getPage();
        getEndpoint(page);
    }

    @Test
    public void findByUrl() {
        endpointRepository.findByUrl("/endpoint");
    }

    @Test
    public void findByPage() {
        Page page = pageRepository.findByName("USUARIO");
        endpointRepository.findByPage(page);
    }

    @Test
    public void findByHttpMethod() {
        endpointRepository.findByHttpMethod(HttpMethod.GET);
    }

}