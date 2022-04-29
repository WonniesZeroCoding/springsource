package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.SampleMapper1;
import com.study.mapper.SampleMapper2;

@Service("service")
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleMapper1 mapper1;
	
	@Autowired
	private SampleMapper2 mapper2;
	
	@Override
	public void addData(String data) {
		

	}

}
