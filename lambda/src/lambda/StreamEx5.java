package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamEx5 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("바둑","바나나","포도","딸기","바질","강아지");
		
		//'바'로 시작하는 단어만 출력
		List<String> fst = new ArrayList<String>();
		for(String f:list) {
			if(f.startsWith("바")) {
				System.out.println(f);
			}
		}
		System.out.println();
		// Stream 이용
		list.stream().filter(f-> f.startsWith("바")).forEach(f -> System.out.println(f));

		System.out.println();
		
		List<Student> stlt = new ArrayList<Student>();
		stlt.add(new Student("누구냐", 90));
		stlt.add(new Student("나보다", 80));
		stlt.add(new Student("잘하는", 75));
		stlt.add(new Student("사람은", 90));
		
		
		//특정 시작 글자 이름 출력
		for(Student stu : stlt) {
			if(stu.getSn().startsWith("누")) { 
				System.out.println(stu.getSn());
			}
		
			System.out.println();
			
			//stream 처리
//			stlt.stream().filter(n -> n.getSn().startsWith("나")).forEach(s-> System.out.println(s));
//			stlt.stream().map(n -> n.getSn())
//						.filter(s -> s.startsWith("누")).forEach(s -> System.out.println(s));
			
			// 점수 모아서 특정 점수 이상 점수만 출력
			stlt.stream().map(n -> n.getSc()).filter(s -> s >= 80 ).forEach(s -> System.out.println(s));

		}
	}
}
