package com.springlec.base0701.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springlec.base0701.dao.BDao;
import com.springlec.base0701.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
	

		int bId = Integer.parseInt(request.getParameter("bId"));
		System.out.println("bId : " + bId);
		
		BDao dao = new BDao();
		BDto dtos = dao.content(bId);
		System.out.println("dtos : " + dtos);
		System.out.println("content : " + dtos.getbContent());
		
		model.addAttribute("content", dtos);
		

	}

}
