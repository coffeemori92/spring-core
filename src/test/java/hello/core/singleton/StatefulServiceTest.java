package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		int userA_Price = statefulService1.order("userA", 10000);
		int userB_Price = statefulService2.order("userB", 20000);

//		int price = statefulService1.getPrice();
		System.out.println("price = " + userA_Price);

//		assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}

	}

}