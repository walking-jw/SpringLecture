package com.springlec.s0705.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.springlec.s0705.dao.Board_Dao;
import com.springlec.s0705.dto.Board_Dto;


public class Board_ListCommand implements Board_Command {

	@Override
	public void execute(Model model) {
		
		Board_Dao dao = new Board_Dao();
		ArrayList<Board_Dto> dtos = dao.list();
		model.addAttribute("list", dtos);
		

	}

}
