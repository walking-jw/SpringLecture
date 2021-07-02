package com.springlec.s0705.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springlec.s0705.dao.Board_Dao;


public class Board_DeleteCommand implements Board_Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub


		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
	

		int bId = Integer.parseInt(request.getParameter("bId"));
		System.out.println("bId : " + bId);
		
		Board_Dao dao = new Board_Dao();
		dao.delete(bId);

		
	}

}
