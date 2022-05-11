package lambda;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx7 {
	public static void main(String[] args) {
		List<String> lst = Arrays.asList("사과","딸기","배","바나나","수박","참외","바나나");
		
		lst.stream().distinct().forEach(s -> System.out.print(s));
		
		System.out.println();
		
		Stream<File> strm = Stream.of(new File("file1.txt"), new File("file2.txt"), new File("Ex1"), 
									new File("Ex2.bak"), new File("test.java"));
		
		//파일명 모아서(map사용) 확장자가 없는 파일명은 제외(filter)하고, 확장자만 모으고 확장자 중복 제거 후, 확장자 출력
		strm.map(File::getName) // 이름만 싹 모아줬을 거임 "file1.txt" , "file2.txt" , ....
			.filter(f -> f.indexOf(".") > -1 ) // .을 기준으로 확장자 확인 .이 없는 경우는 제외됨
			.map(f -> f.substring(f.lastIndexOf(".")+1)) // "txt", "bak", "java" 등만 잘라냄
			.distinct() // "txt","bak","java" 등 찾은 후 중복 제거
			.forEach(f -> System.out.println(f));
	}
}
