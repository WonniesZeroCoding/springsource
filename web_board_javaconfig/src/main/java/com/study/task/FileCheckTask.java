package com.study.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.study.dto.AttachDTO;
import com.study.mapper.AttachMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckTask {
	
	@Autowired
	private AttachMapper attachMapper;
	
	// Task를 하기 위해서
	
	// ① 전일자 폴더 구하기
	private String getFolderYesterDay() {
		// ②어제 날짜 추출
		LocalDate yd = LocalDate.now().minusDays(1);
		// ③추출된 날짜의 포맷 변경 "2022-05-09"(문자열)로 담김
		String str = yd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return str.replace("-", File.separator); // File.separator 파일을 구분하는 구분자 "2022-05-09" -> "2022\05\09"
		
	}
	
	@Scheduled(cron="0 0 2 * * *") // " 0 0 2 * * * " : 새벽 2시에 하겠다
	public void checkFiles() {
		log.info("file check task run...");
		//  ④ 어제날짜의 첨부파일 리스트 가져오기(db)
		List<AttachDTO> oL = attachMapper.getOldFiles();
		List<Path> fLP = oL.stream().map(dto -> Paths.get("d:\\upload", dto.getUploadPath(),dto.getUuid()+"_"+dto.getFileName()))
											.collect(Collectors.toList());
	
		oL.stream().filter(dto -> dto.isFileType() == true).map(dto -> Paths.get("d:\\upload", dto.getUploadPath(),dto.getUuid()+"_"+dto.getFileName()))
															.forEach(f -> fLP.add(f));
		// ⑤ 전일자 폴더의 파일 목록 가져오기
		File targetDir = Paths.get("d:\\upload", getFolderYesterDay()).toFile();
		File[] removeFiles = targetDir.listFiles(f -> fLP.contains(f.toPath()) == false);
		
		// ⑥ 비교 후 폴더 안의 파일 삭제 
		for(File remove:removeFiles) {
			log.info("제거 파일 " + remove.getAbsolutePath());
			remove.delete();
		}
	}
	
	
}
