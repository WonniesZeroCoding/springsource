package com.study.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.AttachDTO;
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
		log.info("전체 리스트 요청"+cri);
		List<BoardDTO> list = service.getList(cri);
		// 전체 게시물 개수
		int total = service.getTotalCnt(cri);
		
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
	public String registerPost(BoardDTO insertDto,Criteria cri,RedirectAttributes rttr) {
		log.info("게시글 등록 요청"+insertDto);
		
		service.insert(insertDto);
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list"; // 성공 시
	}
	
	// /board/read + bno
	// bno에 해당하는 게시물 읽어온 후, read.jsp 보여주기
	@GetMapping({"/read","/modify"})
	public void readGet(int bno,@ModelAttribute("cri") Criteria cri,Model model) {
		log.info("게시글 폼 보여주기"+bno);
		
			BoardDTO dto = service.getRow(bno);
			model.addAttribute("dto", dto);
	}
	
	// /board/read + post => 수정 성공 시 수정된 게시물 보여주기
	@PostMapping("/modify")
	public String modifyPost(BoardDTO modifyDto,Criteria cri ,RedirectAttributes rttr) {
		log.info("게시물 수정 요청"+modifyDto);
		log.info("게시물 수정 요청 - cri"+cri);
		
		service.modify(modifyDto);
		
		//주소줄에 딸려 보내는 방식
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addAttribute("bno", modifyDto.getBno());
		return "redirect:/board/read";
	}
	
	// /board/remove + bno
	// 성공 시, list 보여주기
	
	@GetMapping("/remove")
	public String removePost(int bno,@ModelAttribute("cri") Criteria cri,RedirectAttributes rttr) {
		log.info("게시물 삭제 요청"+bno);
		log.info("게시물 삭제 요청-cri "+cri);
		
		//서버 폴더에 저장한 첨부 파일 삭제
		//bno에 해당하는 첨부 리스트 가져오기
		
		List<AttachDTO> attachList = service.attachList(bno);
		deleteFiles(attachList);
		
		
		
		//DB작업 게시글 삭제 + 첨부파일 삭제 + 댓글 삭제
		
		service.remove(bno);
		
		//주소줄에 딸려 보내는 방식
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		//아래 flash는 session을 이용하는 방식
		rttr.addFlashAttribute("result", "success"); //딸려 보낼 때, FlashAttribute
		
		return "redirect:/board/list";
	}
	
	// 첨부 파일 가져오기
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachDTO>> getAttachList(int bno) {
		log.info("첨부파일"+bno);
		return new ResponseEntity<List<AttachDTO>>(service.attachList(bno),HttpStatus.OK);
	}
	
	private void deleteFiles(List<AttachDTO> attachList) {
		log.info("폴더 내 첨부파일 삭제");
		
		if(attachList == null||attachList.size() <=0) {
			return;
		}
		
		for(AttachDTO attach:attachList) {
			//파일이 존재하는 경로 생성
			Path path = Paths.get("d:\\upload\\", attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName()); // Path : java.nio
		
			try {
				//일반파일, 원본이미지 삭제
				Files.deleteIfExists(path);
				
				// Files.probeContentType(파일경로) : 확장자를 통해서 mime 타입을 판단하는 메소드
				
				
				if(Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("d:\\upload\\", attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					//썸네일 이미지 삭제
					Files.delete(thumb);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
