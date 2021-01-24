package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

/**
 * 컴포넌트 스캔 기본대상
 * @Component: 컴포넌트 스캔에 사용
 * @Controller: 스프링 MVC 컨트롤러에서 사용
 * @Service: 스프링 비즈니스 로직에서 사용
 * @Repository: 스프링 데이터 접근 계층에서 사용
 * @Configuration: 스프링 설정 정보에서 사용
 */

@Configuration
@ComponentScan(
				basePackages = "hello.core", // 탐색할 패키지의 시작위치
				excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
	
	// 오버라이딩 됨, 스프링 부트에서는 오류 발생
//	@Bean(name = "memoryMemberRepository")
//	MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//	}

}
