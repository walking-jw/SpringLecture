package com.springlec.base0501;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping("index")
	public String goIndex() {
		
		return "index";
	}
	
	// Get방식으로 받아서 model에 담아 보내기
	@RequestMapping(method = RequestMethod.GET, value = "student")
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("RequestMethod : GET");
		
		// HTTP 데이터 받기
		String id = httpServletRequest.getParameter("id");
		System.out.println("id : " + id);
		
		// Model에 받은 데이터 담기
		model.addAttribute("studentId", id);
		
		// 보내기(student 폴더에 있는 studentId.jsp로 보내기)
		return "student/studentId";
	}

	
	
	// Post방식으로 받아서 model에 담아 보내기
	@RequestMapping(method = RequestMethod.POST, value = "student")
	public ModelAndView goStudent(HttpServletRequest httpServletRequest) {
		System.out.println("RequestMethod : POST");
		
		// HTTP 데이터 받기
		String id = httpServletRequest.getParameter("id");
		System.out.println("id : " + id);
		
		// Model과 view 를 같이 넣어줘야한다! 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("student/studentId");
		mv.addObject("studentId", id);
		
		// 보내기
		return mv;
	}

		
	
	// Get방식으로 받아서 model에 담아 보내기
	@RequestMapping("studentGet")
	public String goStudentGet(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("RequestMethod : GET");
		
		// HTTP 데이터 받기
		String id = httpServletRequest.getParameter("id");
		System.out.println("id : " + id);
		
		// Model에 받은 데이터 담기
		model.addAttribute("studentId", id);
		
		// 보내기(student 폴더에 있는 studentId.jsp로 보내기)
		return "student/studentId";
	}
	

	// Post방식으로 받아서 model에 담아 보내기
	@RequestMapping("studentPost")
	public String goStudentPost(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("RequestMethod : Post");
		
		// HTTP 데이터 받기
		String id = httpServletRequest.getParameter("id");
		System.out.println("id : " + id);
		
		// Model에 받은 데이터 담기
		model.addAttribute("studentId", id);
		
		// 보내기(student 폴더에 있는 studentId.jsp로 보내기)
		return "student/studentId";
	}
	

	
	
	
} // HomeController
