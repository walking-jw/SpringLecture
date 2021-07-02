package com.springlec.base0701.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0701.command.BCommand;
import com.springlec.base0701.command.BContentCommand;
import com.springlec.base0701.command.BDeleteCommand;
import com.springlec.base0701.command.BListCommand;
import com.springlec.base0701.command.BUpdateCommand;
import com.springlec.base0701.command.BWriteCommand;
import com.springlec.base0701.util.Constant;

@Controller
public class BController {

	BCommand command = null;
	
	private JdbcTemplate template;
	
	// DI에 있는 template 쓰려면Auto 해야함
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		//전체에 쓰게 해주기
		this.template = template;
		// DATA값 넣어주기
		Constant.template = this.template;
		
	}
	
	// 원래라면 Command 마다 이것을 써줘야하는데 번거로우니까 어차피 거치는 Controller에다가 작성해주면 전체 사용이 가능함..
	
	
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
