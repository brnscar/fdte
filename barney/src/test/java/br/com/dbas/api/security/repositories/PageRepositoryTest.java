package br.com.dbas.api.security.repositories;

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
public class PageRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private PageRepository pageRepository;

    @Before
    public void setUp() throws Exception {
        getPage();
    }

    @Test
    public void findByName() {
        pageRepository.findByName("USUARIO");
    }

}