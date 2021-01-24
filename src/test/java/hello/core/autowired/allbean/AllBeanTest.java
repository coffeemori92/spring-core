package hello.core.autowired.allbean;

import hello.core.AutoAppConfig;
import hello.core.discount.DisCountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

	@Test
	void findAllBean() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
		DiscountService discountService = ac.getBean(DiscountService.class);
		Member member = new Member(1L, "userA", Grade.VIP);
		int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

		assertThat(discountService).isInstanceOf(DiscountService.class);
		assertThat(discountPrice).isEqualTo(1000);
	}

	static class DiscountService {

		private final Map<String, DisCountPolicy> policyMap;
		private final List<DisCountPolicy> policyList;

		@Autowired
		public DiscountService(Map<String, DisCountPolicy> policyMap, List<DisCountPolicy> policyList) {
			this.policyMap = policyMap;
			this.policyList = policyList;
			System.out.println("policyMap = " + policyMap);
			System.out.println("policyList = " + policyList);
		}

		public int discount(Member member, int price, String discountCode) {
			DisCountPolicy disCountPolicy = policyMap.get(discountCode);
			return disCountPolicy.discount(member, price);
		}
	}

}
