package com.springlec.base0802.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0802.command.AddressListCommand;
import com.springlec.base0802.command.BCommand;
import com.springlec.base0802.dao.Address_Dao;

@Controller
public class Address_Controller {

	@Autowired
	private SqlSession sqlSession;

	BCommand command;
	
	////////////////////////////////////
	//  Address List 불러오기           //
	////////////////////////////////////
	
	@RequestMapping("/list")
	public String addressList(Model model) {
		System.out.println("* * * Controller : addresslist * * *");
		
		// DATA 받기
//		Address_Dao dao = sqlSession.getMapper(Address_Dao.class);
		// DATA 건네주기
//		model.addAttribute("ADDRESS_LIST", dao.addressListDao());
		
		command = new AddressListCommand();
		command.execute(sqlSession, model); // Session도 같이넘겨줘야하는 이유가. Session이 정의 된 곳이 여기이기때문이다.
		
		// 이동하기
		return "address_list";
	}
		
	////////////////////////////////////
	//  새로운 주소록 작성하               //
	////////////////////////////////////
	@RequestMapping("/newaddress")
	public String newAddress(Model model) {
		System.out.println("* * * Controller : newaddress * * *");
		return "new_address";
	}
	
	@RequestMapping("/newaddress_insert")
	public String newAddressInsert(HttpServletRequest request, Model model) {
		System.out.println("* * * Controller : newAddressInsert * * *");
		
		// DATA 받기
		Address_Dao dao = sqlSession.getMapper(Address_Dao.class);
		
		// 입력은 값 받고 보내기
		dao.newAddressDao(request.getParameter("name"), request.getParameter("telno"), request.getParameter("address"), request.getParameter("email"), request.getParameter("relation"));
		
		// 이동하기
		return "redirect:list";
	}
	
	////////////////////////////////////
	//  자세히보기로 들어가기               //
	////////////////////////////////////
	@RequestMapping("content_view")
	public String contentView(HttpServletRequest request, Model model) {
		System.out.println("* * * Controller : contentView * * *");
		
		// DATA	받기
		Address_Dao dao = sqlSession.getMapper(Address_Dao.class);
		
		model.addAttribute("content", dao.contentViewDao(Integer.parseInt(request.getParameter("seqno"))));
		
		return "content_view";
	}
	
	////////////////////////////////////
	//  삭제하기                        //
	////////////////////////////////////
	@RequestMapping("/delete_address")
	public String deleteAddress(HttpServletRequest request, Model model) {
		System.out.println("* * * Controller : deleteAddress * * *");
		
		// DATA	받기
		Address_Dao dao = sqlSession.getMapper(Address_Dao.class);
		
		dao.deleteAddressDao(Integer.parseInt(request.getParameter("seqno")));
		
		return "redirect:list";
	}
	
	////////////////////////////////////
	//  수정하기                        //
	////////////////////////////////////
	@RequestMapping("/modify_address")
	public String modifyAddress(HttpServletRequest request, Model model) {
		System.out.println("* * * Controller : modifyAddress * * *");
		
		// DATA	받기
		Address_Dao dao = sqlSession.getMapper(Address_Dao.class);
				
		dao.modifyAddressDao(request.getParameter("name"), request.getParameter("telno"), request.getParameter("address"), request.getParameter("email"), request.getParameter("relation"), Integer.parseInt(request.getParameter("seqno")));
		
		model.addAttribute("content", dao.contentViewDao(Integer.parseInt(request.getParameter("seqno"))));
		
		return "content_view";
	}
	
	
	@RequestMapping("/listQuery")
	public String listQuery(HttpServletRequest request, Model model) {
		// DATA	받기
		Address_Dao dao = sqlSession.getMapper(Address_Dao.class);
		
		model.addAttribute("ADDRESS_LIST", dao.listQueryDao(request.getParameter("query"), request.getParameter("content")));
		
		return "address_list";
	}
	
	
}
