package com.study.myapp;

public class TVMain {
	public static void main(String[] args) {
		//TV tv = new SamsungTV(new SonySpeaker());
		
		SamsungTV tv = new SamsungTV();
		
		//tv.setSpeaker(new SonySpeaker());
		tv.setSpeaker(new AppleSpeaker());
		
		tv.powerOn(); 
		tv.volumeUp();
		tv.powerOff();
		
		//Exception in thread "main" java.lang.NullPointerException
		// String str = null;
		// syso(str); --> NullPointerException 안나옴
		
//		String str = null; // 초기화
//		System.out.println(str);
//		System.out.println(str.toString());
		//str로 불러낼 때, NullPointer가 나는 것이 아니라 . 찍고 객체에 접근할 때 발생
		//
	}
}
