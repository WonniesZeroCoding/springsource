package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class CustomerEx {
	public static void main(String[] args) {
		//고객 명단 출력
		//총 여행 경비 계산
		//고객 중, 20세 이상인 사람의 이름을 정렬하여 출력
		List<Customer> cusLst = new ArrayList<Customer>();
		cusLst.add(new Customer("이순신", 30, 100));
		cusLst.add(new Customer("성춘향", 18, 100));
		cusLst.add(new Customer("홍길동", 21, 100));
		cusLst.add(new Customer("김유신", 17, 100));
	
		// 고객 명단 출력 - map, forEach 이용
		cusLst.stream().map(c -> c.getN()).forEach(n -> System.out.println(n));
		// 총 여행 경비 계산 - map, sum 이용
		long sm = cusLst.stream().mapToInt(c -> c.getPri()).sum();
		System.out.println("총 여행 경비"+sm);
		
		// 고객 중 20세 이상인 사람의 이름을 정렬해서 출력
		System.out.println("--- 20세 이상 고객 명단---");
		cusLst.stream().filter(c -> c.getAge() >= 20).map(c -> c.getN()).sorted().forEach(c -> System.out.println(c));
	}
}
