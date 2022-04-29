package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.NumDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/calc/*")
public class AddController {
//	메소드 명은 중요한게 아니다
	// 사용자가 들어오는 주소, 즉 맵핑이 가장 중요하다
	@GetMapping("/add")
	public void addGet() {
		log.info("add.jsp 페이지요청..");
	}
//	@PostMapping("/add")
//	public void addPost(@RequestParam("num1") int op1,@RequestParam("num2") int op2) {
//		//넘겨주는 값 () 안에 타입, 이름 맞춰줄 것
//		//add.jsp에서 넘겨주는 값 num1, num2
//		log.info("덧셈 요청");
//		//log.info("num1 + num2" = +(num1+num2));
//		log.info("num1 + num2 = "+(op1+op2));
//	}
	
//	@PostMapping("/add")
//	public void addPost(@RequestParam(value="num1", required = false, defaultValue="0") int num1,@RequestParam(value="num2",required=false,defaultValue="0") int num2) {
//		//RequestParam을 빼든 안빼뜬 Data를 넣지 않으면 400에러 발생
//		log.info("덧셈 요청");
//		
//		log.info("num1 + num2 = "+(num1+num2));
//		
//		// 어느 jsp 갈것인가 ? calc/add
//		// controller의 마지막(get이건 post건)은 항상 calc/add를 가지고 움직임
//	}
	
	// @ModelAttribute("이름") : 바인딩 객체의 이름을 변경하는 용도
	//						  : Model 객체에 값을 담는 것과 같은 기능 제공
	
	@PostMapping("/add")
	public void addPost(@ModelAttribute("dto") NumDTO dto,Model model) {
		//RequestParam을 빼든 안빼뜬 Data를 넣지 않으면 400에러 발생
		log.info("덧셈 요청");
		log.info("num1 + num2 = "+(dto.getNum1()+dto.getNum2()));
		
		int result = dto.getNum1()+dto.getNum2();
		// result 값을 add.jsp 보여주고 싶다면 : Model 객체를 사용함(request.setAttribute()) 한 것과 같은 개념
		model.addAttribute("result", result);
		//개념은 같은데 set부분이 add로 다를 뿐 개념은 같다
	}
}
