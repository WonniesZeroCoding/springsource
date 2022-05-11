package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * 스트림 최종 연산
 * forEach()
 * collect() : 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 변환하는데 사용
 * sum()
 * average()
 * max()
 * min()
 * findFirst()
 * */



public class StreamEx9 {
	public static void main(String[] args) {
		List<Student> stlt = new ArrayList<Student>();
		stlt.add(new Student("누구냐", 90));
		stlt.add(new Student("나보다", 80));
		stlt.add(new Student("잘하는", 75));
		stlt.add(new Student("사람은", 90));
		stlt.add(new Student("그런건", 94));
		
		List<Integer> stuNum = new ArrayList<Integer>();
		for(Student stu:stlt) {
			stuNum.add(stu.getSc());
		}
		
		//학생들의 점수만 모아서 새로운 리스트로 생성
		
		//Stream<Student> strm = stlt.stream();
		List<Integer> jumsu = stlt.stream().map(n-> n.getSc()).collect(Collectors.toList());

		//		strm.map(n -> n.getSc()).collect(Collectors.toList());
		
	}
}
