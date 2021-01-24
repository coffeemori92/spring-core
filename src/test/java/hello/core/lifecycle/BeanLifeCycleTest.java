package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.
 * 인터페이스(InitializingBean, DisposableBean)
 * 설정 정보에 초기화 메서드, 종료 메서드 지정
 * @PostConstruct, @PreDestroy 애노테이션 지원
 */

public class BeanLifeCycleTest {

	@Test
	void lifeCycleTest() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close();
	}

	@Configuration
	static class LifeCycleConfig {

	//	@Bean(initMethod = "init", destroyMethod = "close")
		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://hello-spring.dev");
			return networkClient;
		}

	}

}
