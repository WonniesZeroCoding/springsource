package lambda;

public class LambdaEx1 {
	public static void main(String[] args) {
//		Lambda2 lbda = e -> System.out.println(e);
//		lbda.method(10);
//		
//		lbda = e -> System.out.println(e*e);
//		lbda.method(100);

		//익명구현 클래스
//		Lambda2 lbda2 = new Lambda2() {
//
//			@Override
//			public int mx(int n1, int n2) {
//				// TODO Auto-generated method stub
//				return n1 > n2 ? n1 : n2;
//			}
//			
//		};
//		
//		lbda2.mx(10, 5);
		
		//아래 오류 문구는 : 함수형 인터페이스에서는 단 하나의 추상 메서드만 정의할 수 있다는 의미
		//The target type of this expression must be a functional interface
		//함수형 
		Lambda2 lbda = (n1,n2) -> (n1 > n2)? n1 : n2;
		System.out.println(lbda.mx(20, 30));
		
		
	
	}
}
