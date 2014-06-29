package com.board.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	TestObjDao objDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		
		List<TestObj> list = objDao.searchAll();
		logger.info("db connected");
		model.addAttribute("lists",list);
		model.addAttribute("number", list.size());
		
		TestObj obj = objDao.getObj("asdf@qwer.com");
		model.addAttribute("searchPw", obj.getPassword());
		
		//TestObj obj = new TestObj("test@test.com","1111","kkk","20140505");
		//objDao.insert(obj);
		
		return "home";
	}
	
	
			
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String writeAction(Locale locale, @RequestBody TestObj obj){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		logger.info("db write action");
		
		return "home";
	}
	
}
