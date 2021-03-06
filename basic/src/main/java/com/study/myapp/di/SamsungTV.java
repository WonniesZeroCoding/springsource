package com.study.myapp.di;

public class SamsungTV implements TV {

	//private SonySpeaker speaker; // NullPointer발생 안하려면 "초기화 작업"  필요.
	// 초기화 작업 : 생성자를 이용 or setter 메소드
	//public SamsungTV() : default 생성자
	
	//private AppleSpeaker speaker;
	// 객체를 변경
	private Speaker speaker;
	
	public SamsungTV() {
	
	}
	
	public SamsungTV(Speaker speaker/*멤버변수의 초기화 담당*/) {
		super();
		this.speaker = speaker;
	} // 초기화를 시키면 default 생성자는 없어짐.
	
	//setter 메소드 만들어주기 -- 목적 : 초기화, 변경
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV 전원 On");

	}


	@Override
	public void powerOff() {
		System.out.println("SamsungTV 전원 Off");

	}

	@Override
	public void volumeUp() {
		//System.out.println("SamsungTV Volume Up"); TV볼륨이 아니라 Sony꺼 쓰고 싶다면
		speaker.volumeUp();

	}

	@Override
	public void volumeDown() {
		//System.out.println("SamsungTV Volume Down");
		speaker.volumeDown();
	}

}
