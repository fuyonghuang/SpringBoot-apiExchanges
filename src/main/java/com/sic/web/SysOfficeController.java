package com.sic.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sic.entity.SysOffice;
import com.sic.service.SysOfficeService;

@RestController
public class SysOfficeController {
	
	@Autowired
	private SysOfficeService sysOfficeService;
	
	@GetMapping(value = "/test")
	public Map select(@RequestParam("areaId") String areaId){
		
		Map<String,Object> map=new HashMap<String,Object>();
		SysOffice sysOffice=new SysOffice();
		sysOffice.setAreaId(areaId);
		
		List<SysOffice> list=sysOfficeService.selectByEntity(sysOffice);
		map.put("office", list);
		
		return map;
		
	}

}
