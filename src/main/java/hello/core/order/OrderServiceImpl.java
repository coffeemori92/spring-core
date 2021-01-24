package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
//	private final DisCountPolicy disCountPolicy = new FixDiscountPolicy();
//	private final DisCountPolicy disCountPolicy = new RateDiscountPolicy();
	private final DisCountPolicy disCountPolicy;

	@Autowired // 생성자 주입, 생성자 호출 시점에 1번만 호출되는 것이 보장된다. 불변, 필수의 의존관계에 사용된다.
	public OrderServiceImpl(MemberRepository memberRepository,
													//@Qualifier("mainDiscountPolicy")
														@MainDiscountPolicy DisCountPolicy disCountPolicy) {
		this.memberRepository = memberRepository;
		this.disCountPolicy = disCountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = disCountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
}
