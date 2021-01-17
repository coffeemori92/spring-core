package hello.core;

import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 좋은 객체지향 설계의 5가지 원칙
 *
 * SRP 단일 책임원칙 = 한 클래스는 하나의 책임만 가져야 한다.
 * 클라이언트 객체는 직접 구현 객체를 생성하고, 연결하고, 실행하는 다양한 책임을 가지고 있다.
 * SRP 단일 책임 원칙에 따라 관심사를 분리함.
 * 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당.
 * 클라이언트 객체는 실행하는 책임만 담당.
 *
 * DIP 의존관계 역전 원칙 = 프로그래머는 추상화에 의존해야지, 구현체에 의존하면 안된다.
 * 의존성 주입은 이 원칙을 다르는 방법 중 하나다.
 * AppConfig가 객체인스턴스를 클라이언트 코드 대신 생성해서 클라이언트 코드에 의존관계를 주입
 *
 * OCP 열림 폐쇄 원칙 = 소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀 있어야 한다.
 * 어플리케이션을 사용 영역과 구성 영역으로 나눔
 * 클라이언트 코드는 변경하지 않아도 됨.
 * 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀있다.
 *
 * IoC(Inversion of Control) 제어의 역전
 * 구현객체는 자신의 로직을 실행하는 역할만 담당한다.
 * 프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있다.
 * 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전이라 한다.
 *
 * 의존관계 주입 DI
 * 인터페이스에만 의존한다. 실제 어떤 구현객체가 사용될지는 모른다.
 * 의존관계는 정적인 클래스 의존관계와, 실행 시점에 결정되는
 * 동적인 객체(인스턴스)의존 관계들을 분리해서 생각해야 한다.
 * == 실행시점(RunTime)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서
 * 클라이언트와 서버의 실제의존관계가 연결되는 것을 의존관계 주입이라고 한다.
 * 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결된다.
 * 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는
 * 대상의 타입 인스턴스를 변경할 수 있다.
 * 의존관계 주입을 사용하면 정적은 클래스 의존관계를 변경하지 않고,
 * 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.
 *
 * 정적인 클래스 의존관계
 * 클래스가 사용하는 import코드만 보고 의존관계를 쉽게 판단할 수 있다.
 * 정적인 의존관계는 어플리케이션을 실행하지 않아도 분석할 수 있다.
 *
 * 동적인 객체 인스턴스 의존관계
 * 어플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존관계다.
 *
 * IoC컨테이너, DI컨테이너
 * AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을
 * IoC컨테이너 또는 DI컨테이너라고 한다.
 * 의존관계 주입에 초점을 맞추어 최근에는 주로 DI컨테이너라 한다.
 * 또는 어셈블러, 오브젝트 팩토리 등으로 불리기도 한다.
 */

// 애노테이션 기반의 자바 설정클래스로 만든 스프링 컨테이너
@Configuration
public class AppConfig {

	// @Bean memberService -> new MemoryMemberRepository()
	// @Bean orderService -> new MemoryMemberRepository()

	// 생성자 주입
	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), disCountPolicy());
	}

	@Bean
	public DisCountPolicy disCountPolicy() {
		return new RateDiscountPolicy();
	}

}
