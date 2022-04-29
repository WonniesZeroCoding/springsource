package com.study.myapp.di.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVMain {
	public static void main(String[] args) {
	
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		
		
//		TV tv = (TV) ctx.getBean("lg");
		TV tv = (TV) ctx.getBean("samsung");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
//		[NoUnique]BeanDefinitionException: No qualifying bean of type 'com.study.myapp.di.annotation.Speaker' 
//		available: expected single matching bean but found 2: appleSpeaker,sonySpeaker
		// speaker(객체로 Apple~, Sony~ 2개 생성해놨기 때문)가 가능한게 2개가 나왔음. 대입 여러개 가능하니, 하나를 지정해줘야 한다. 이말임
		
	}
}
