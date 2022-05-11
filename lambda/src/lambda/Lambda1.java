package lambda;
/*
 * 함수형 프로그래밍 -- 단 하나의 추상 메소드만 정의되어 있어야 함
 * 
 * 람다식(자바 8버전 부터 추가 됨)
 * 메소드를 하나의 식으로 표현한 개념
 * 메소드를 람다식으로 표현하면 메소드의 이름과 값이 없어지므로, 람다식을 익명 함수라고도 한다.
 * */


public class Lambda1 {
	
//	int max(int a, int b) {
//		return a > b ? a : b;
//	}
	
	// 위의 일반적인 형태를 람다식 표현으로 바꾼 것
//	(int a, int b) -> {return a > b ? a : b;}
//	(int a, int b) -> a > b ? a : b;
//	(a, b) -> a > b ? a : b;
	
	
	// 일반 메소드를 람다식으로 바꿈
	void pt(String n, int x) {
		System.out.println(n + "+" + x);
	}
	
//	(String n, int x) -> {System.out.println(n + "+" + x);}
//	(String n, int x) -> System.out.println(n + "+" + x);
//	(n, x) -> System.out.println(n + "+" + x);
	
	//인자를 안받는 경우
	int roll( ) {
		return (int)(Math.random()*6);
	}
	
//	() -> {return (int)(Math.random()*6);}
//	() -> (int)(Math.random()*6);
	
	//인자를 하나만 받는 경우
	int square(int x) {
		return x * x;
	}
	
//	(int x) -> { return x * x; }
//	(x) -> x * x;
//	x -> x*x;
}
