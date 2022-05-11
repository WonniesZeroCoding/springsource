package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx4 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("abc","def","apple","melon","text");
		
		//대문자로 변경한 값을 다른 리스트에 담은 후 출력
		
		//1) 일반적인 경우
		List<String> uL = new ArrayList<String>();
		for(String s:list) {
			uL.add(s.toUpperCase());
		}
		System.out.println(uL);
	
		//2) Stream 이용
//		Stream<String> stream = list.stream();
//		Stream<String> upr = stream.map(String::toUpperCase);
//		upr.forEach(s -> System.out.println(s));
	
		List<String> upperList2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println();
	}
}
