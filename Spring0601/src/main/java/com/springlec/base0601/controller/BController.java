package com.springlec.base0601.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0601.command.BCommand;
import com.springlec.base0601.command.BContentCommand;
import com.springlec.base0601.command.BDeleteCommand;
import com.springlec.base0601.command.BListCommand;
import com.springlec.base0601.command.BUpdateCommand;
import com.springlec.base0601.command.BWriteCommand;

@Controller
public class BController {

	BCommand command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println(" ## METHOD : list() ## ");
		
		command = new BListCommand();
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
	@RequestMapping("write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println(" ## METHOD : write() ## ");
		
		// "request"  =  map requset
		model.addAttribute("request", request);
		
		command = new BWriteCommand();
		command.execute(model);
		
		// 리스트로 돌아가야 하니까 이 Controller에서만 움직이면 된다! -> jsp 로 쏘는게 아니니까 redirexct
		return "redirect:list";
		
	}
	
	// 자세히보기
	@RequestMapping("/content_view")
	public String content(HttpServletRequest request, Model model) {
		System.out.println(" ## METHOD : Content() ## ");
		
		model.addAttribute("request", request);
		
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	// 삭제하기
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println(" ## METHOD : Delete() ## ");
		
		model.addAttribute("request", request);
		
		command = new BDeleteCommand();
		command.execute(model);
		
		
		return "redirect:list";
	}
	
	// 수정하기
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BUpdateCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	
}
