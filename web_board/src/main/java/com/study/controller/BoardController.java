package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.dto.PageDTO;
import com.study.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// /Board/list 컨트롤러 작성 get방식
	@GetMapping("/list")
	public void list(Model model,@ModelAttribute("cri") Criteria cri) {
		log.info("전체 리스트 요청");
		List<BoardDTO> list = service.getList(cri);
		// 전체 게시물 개수
		int total = service.getTotalCnt();
		
		model.addAttribute("pageDto", new PageDTO(cri, total));
		model.addAttribute("list", list);
	}
	
	// /board/register 컨트롤러 작성
	@GetMapping("/register")
	public void register() {
		log.info("register 폼 요청");
	}
	
	// register post
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto,RedirectAttributes rttr) {
		log.info("게시글 등록 요청"+insertDto);
		
		if(!service.insert(insertDto)) {
			//성공하면 넘어가면 되고, 실패하면
			return "redirect:/board/register"; // 실패 시
		}
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list"; // 성공 시
	}
	
	// /board/read + bno
	// bno에 해당하는 게시물 읽어온 후, read.jsp 보여주기
	@GetMapping({"/read","/modify"})
	public void readGet(int bno,Model model) {
		log.info("게시글 폼 보여주기"+bno);

			BoardDTO dto = service.getRow(bno);
			model.addAttribute("dto", dto);
	}
	
	// /board/read + post => 수정 성공 시 수정된 게시물 보여주기
	@PostMapping("/modify")
	public String modifyPost(BoardDTO modifyDto,RedirectAttributes rttr) {
		log.info("게시물 수정 요청"+modifyDto);
		
		service.modify(modifyDto);
		
		rttr.addAttribute("bno", modifyDto.getBno());
		return "redirect:/board/read";
	}
	
	// /board/remove + bno
	// 성공 시, list 보여주기
	
	@GetMapping("/remove")
	public String removePost(int bno, RedirectAttributes rttr) {
		log.info("게시물 삭제 요청"+bno);
		
		service.remove(bno);
		
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
}