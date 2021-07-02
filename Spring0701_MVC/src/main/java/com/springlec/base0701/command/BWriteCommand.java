package com.springlec.base0701.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springlec.base0701.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		//Model 내용을 뺴와야한다! (앞 키값, 뒤 밸류)
		Map<String, Object> map = model.asMap();
		
		// 글작성 누르면 Request 로 날아온다 -> 이거 Request를 없애줘야함
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		
		// 보안을 강화하기 위해 Model 으로 감싼것이다. 이 감싼 것을 푸는게 Map
		// 이 해제하는 것이 jsp 와 다른 점이다!
	
		
		// 가져온 데이터
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao dao = new BDao();
		dao.write(bName, bTitle, bContent);
	}

}
