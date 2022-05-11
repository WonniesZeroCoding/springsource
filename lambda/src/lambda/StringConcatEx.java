package lambda;

public class StringConcatEx {
	public static void main(String[] args) {
//		StringConcat sc = new StringConcatImple();
//		sc.makeString("Hello", "interface");
		
		//람다식으로 구현
		StringConcat sc = (s1,s2) -> System.out.println(s1+", "+s2);
		sc.makeString("hello", "world");
	}
}
