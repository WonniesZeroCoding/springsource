package com.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.SampleDTO;

import lombok.extern.slf4j.Slf4j;

/*
   REST 방식

   요청 + GET ==> DATA ( txt , xml, json ) 전송


   ResponseEntity 객체 : Controller 에서 데이터 형식으로 반환되며, 네트워크에 데이터와 상태코드를 함께 전송한다

   Controller : 사용자의 요청을 처리해주는 Class
      @Controller : retrun ( void, String :: JSP )
         @ResponseBody : 일반 Controller 에서 return 값이 데이터로써 활용할 수 있도록 지시
      @RestController : return ( DATA 그 자체 )
         JAVA 객체 ===== Converter =====>> JSON 변경
                     ( GSON::구글 , JSON::jackson-databind )
                        Jackson Databind : JSON Converter
                        Jackson Dataformat XML : XML Converter
                        
                        
                        
                        
                        
   @PathVariable : REST 방식은 URL 이 갖고 있는 값을 정보(DATA)로 사용하는 경우가 많음
               URL Parameter 에 들어오는 값을 변수에 담을 수 있게 해주는 Annotation
*/

@Slf4j
@RestController
public class BasicController {

   
   @GetMapping("/hello")
   public String sayHello() {
      return "Hello World"; // RestController 이기 때문에, 문자열을 그대로 출력
   }
   
   @GetMapping(path = "/send.xml", produces = MediaType.APPLICATION_XML_VALUE)
   public SampleDTO sendDTOXml() {
      SampleDTO dto = new SampleDTO();
      dto.setBno("1");
      dto.setName("hnog");
      dto.setAddr("seoul");
      return dto;
   }

//   @GetMapping(path = "/send.json", produces = MediaType.APPLICATION_JSON_VALUE)
//   public SampleDTO sendDTOJson() {
//      SampleDTO dto = new SampleDTO();
//      dto.setBno("1");
//      dto.setName("hnog");
//      dto.setAddr("seoul");
//      return dto;
//   }

   
   
//   @GetMapping(path = "/send_list.json", produces = MediaType.APPLICATION_JSON_VALUE)
//   public List<SampleDTO> sendList() {
//      List<SampleDTO> list = new ArrayList<SampleDTO>();
//      
//      for(int i = 0; i < 10; i++) {
//         SampleDTO dto = new SampleDTO();
//         dto.setBno("" + i);
//         dto.setName("hnog");
//         dto.setAddr("seoul");
//         list.add(dto);
//      }
//      return list;
//   }
   
   
   
   @GetMapping(path = "/send_map.json", produces = MediaType.APPLICATION_JSON_VALUE)
   public Map<String, SampleDTO> sendList() {
      
      SampleDTO dto = new SampleDTO();
      dto.setBno("1");
      dto.setName("hnog");
      dto.setAddr("seoul");
      
      Map<String, SampleDTO> map = new HashMap<String, SampleDTO>();
      map.put("First", dto);
      return map;
   }
   
   
   // ResponseEntity 타입 :: 상태코드(HTTP) + DATA 를 한번에 전송가능한 TYPE
      // 상태코드 ( 404, 500, 406, 200 ... )
   @GetMapping(path = "/send_response.json", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SampleDTO> check(Double height, Double weight){
      SampleDTO dto = new SampleDTO("123",height+ "" ,weight + "");
      
      ResponseEntity<SampleDTO> result = null;
      
      if(height < 150) {   
//         result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
         result = new ResponseEntity<>(dto, HttpStatus.BAD_GATEWAY);
      }else {
         result = ResponseEntity.status(HttpStatus.OK).body(dto);
      }
      return result;
   }
   
   @GetMapping(path = "/product/{cat}/{pid}", produces = MediaType.APPLICATION_XML_VALUE)
   public String[] getPath(@PathVariable("cat") String cat,@PathVariable("pid") String pid) {
      return new String[] {"category : " + cat, "productId : " + pid};
   }
   
   // @RequestBody : JSON 데이터를 서버로 가져올 때, 원하는 타입의 객체로 변환
   @PostMapping("/test1")
   public void test1(@RequestBody SampleDTO dto) {
	   log.info("json 데이터 가져오기 "+dto);
   }
   
   
}









