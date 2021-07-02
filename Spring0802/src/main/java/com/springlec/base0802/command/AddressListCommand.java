package com.springlec.base0802.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.springlec.base0802.dao.Address_Dao;

public class AddressListCommand implements BCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub

		Address_Dao dao = sqlSession.getMapper(Address_Dao.class);
		
		// DATA 건네주기
		model.addAttribute("ADDRESS_LIST", dao.addressListDao());
		
		
	}

}
