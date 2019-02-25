package br.com.dbas.api.security.services;

import br.com.dbas.api.security.repositories.BaseRepositoryTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class UserServiceTest extends BaseRepositoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void save() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findById() {
    }

}