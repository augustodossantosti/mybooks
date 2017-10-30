package br.com.mybooks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mybooks.configuration.AbstractIntegrationTest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybooksApplicationTests extends AbstractIntegrationTest {

	@Test
	public void contextLoads() {}

}
