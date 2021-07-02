package com.springlec.s0705.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.s0705.command.Board_Command;
import com.springlec.s0705.command.Board_DeleteCommand;
import com.springlec.s0705.command.Board_ListCommand;
import com.springlec.s0705.command.Board_WriteCommand;
import com.springlec.s0705.util.Constant;

@Controller
public class Board_Controller {

	Board_Command command = null;
	
	private JdbcTemplate template;
	
	// DI에 있는 template 쓰려면Auto 해야함
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		//전체에 쓰게 해주기
		this.template = template;
		// DATA값 넣어주기
		Constant.template = this.template;
		
	}


	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println(" ## METHOD : list() ## ");
		
		command = new Board_ListCommand();
		command.execute(model);
		
		return "list";
	}
	

	// 글 작성하는 페이지
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println(" ## METHOD : write_view() ## ");
		
		return "write_view";
	}
	
	// 글 작성
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println(" ## METHOD : write() ## ");
		
		// "request"  =  map requset
		model.addAttribute("request", request);
		
		command = new Board_WriteCommand();
		command.execute(model);
		
		// 리스트로 돌아가야 하니까 이 Controller에서만 움직이면 된다! -> jsp 로 쏘는게 아니니까 redirexct
		return "redirect:list";
		
	}
	
	// 삭제하기
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println(" ## METHOD : Delete() ## ");
		
		model.addAttribute("request", request);
		
		command = new Board_DeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
}
