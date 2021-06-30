package com.springlec.base0602.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0602.command.BCommand;
import com.springlec.base0602.command.BContentCommand;
import com.springlec.base0602.command.BDeleteCommand;
import com.springlec.base0602.command.BListCommand;
import com.springlec.base0602.command.BModifyCommand;
import com.springlec.base0602.command.BWriteCommand;

@Controller
public class BController {

	private BCommand listCommand = null;
	private BCommand writeCommand = null;
	private BCommand contentCommand = null;
	private BCommand modifyCommand = null;
	private BCommand deleteCommand = null;
	
	@Autowired
	public void auto(BCommand list, BCommand write, BCommand content, BCommand modify, BCommand delete) {
		this.listCommand = list;
		this.writeCommand = write;
		this.contentCommand = content;
		this.modifyCommand = modify;
		this.deleteCommand = delete;
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println(" ## METHOD : list() ## ");
		
		listCommand.execute(model);
		
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
		
		model.addAttribute("request", request);
		writeCommand.execute(model);
		
		return "redirect:list";
	}
	
	// 자세히보기
	@RequestMapping("/content_view")
	public String content(HttpServletRequest request, Model model) {
		System.out.println(" ## METHOD : Content() ## ");
		
		model.addAttribute("request", request);
		contentCommand.execute(model);
		
		return "content_view";
	}
	
	// 수정하기
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		modifyCommand.execute(model);
		
		return "redirect:list";
	}
	
	
	// 삭제하기
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println(" ## METHOD : Delete() ## ");
		
		model.addAttribute("request", request);
		deleteCommand.execute(model);
		
		
		return "redirect:list";
	}
	
}
