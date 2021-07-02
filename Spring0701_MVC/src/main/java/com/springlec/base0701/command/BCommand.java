package com.springlec.base0701.command;

import org.springframework.ui.Model;

public interface BCommand {

	// 불러온 DATA 를 Model에 담아야 하기 때문에 공통적으로 쓰는 METHOD 미리 지정
	void execute(Model model);
	
}
