package farmacia;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;


@Suite
@SelectClasses({FarmaciaTest.class})
@SpringBootTest
public class AllTests {

	@Test
	void contextLoads() {
	}
}
