package com.spring.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	VisitorObjDao objDao;
	@Autowired
	TimeObjDao timeDao;
	
	List<VisitorObj> list;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		list = objDao.searchAll();
		logger.info("db connected");
		
		model.addAttribute("lists",list);
		
		return "home";
	}
	
	
	@RequestMapping(value="change", method=RequestMethod.POST)
	public String changeData(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		logger.info("db action");
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String btn = req.getParameter("submitBtn");
		
		if(btn.equalsIgnoreCase("write")){
			writeData(model,req,resp);
		}
		else if(btn.equalsIgnoreCase("modify")){
			modifyData(model,req,resp);
		}
		else if(btn.equalsIgnoreCase("delete")){
			deleteData(model,req,resp);
		}
		
		//list = objDao.searchAll();
		model.addAttribute("lists",list);
		
		return "home";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeData(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String formattedDate = dateFormat.format(date);
		
		logger.info("db write action");
		
		PrintWriter out = resp.getWriter();
		
		String email = req.getParameter("email");
		String password = req.getParameter("passwd");
		String content = req.getParameter("content");
		
		if(email!="" && password!=""){
			Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher m = p.matcher(email);
			if(!m.matches()){
				out.println("<script>alert('올바른 이메일 주소를 입력하세요');</script>");
			}
			else{
				if(objDao.getObj(email)!=null){
					out.println("<script>alert('이메일이 중복됩니다');</script>");
					out.flush();
					return "home";
				}
				
				VisitorObj add = new VisitorObj(email,password,content);
				objDao.insert(add);
				logger.info("db objDao insert work");
				TimeObj addT = new TimeObj(email,formattedDate,"");
				timeDao.insert(addT);
				logger.info("db timeDao insert work");
			}
		}
		else{
			out.println("<script>alert('이메일과 비밀번호를 입력하세요');</script>");
		}
		
		list = objDao.searchAll();
		model.addAttribute("lists",list);
		
		out.flush();
		return "home";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyData(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String formattedDate = dateFormat.format(date);
		
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		
		PrintWriter out = resp.getWriter();
		
		if(email!="" && passwd!=""){
			Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher m = p.matcher(email);
			if(!m.matches()){
				out.println("<script>alert('올바른 이메일 주소를 입력하세요');</script>");
			}
			else{
				VisitorObj obj = objDao.getObj(email);
				if(obj == null) out.println("<script> alert('사용자가 존재하지 않습니다.'); </script>");
				else{
					//password 유효성 검사
					if(!obj.getPassword().equalsIgnoreCase(passwd))
						out.println("<script>alert('비밀번호가 올바르지 않습니다.');</script>");
					else{
						objDao.modify(email, req.getParameter("content"));
						timeDao.modify(email, formattedDate);
					}
				}
			}
		}
		else{
			out.println("<script>alert('이메일과 비밀번호를 입력하세요');</script>");
		}
		
		logger.info("db modify action");
		
		list = objDao.searchAll();
		model.addAttribute("lists",list);
		
		out.flush();
		return "home";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteData(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		
		PrintWriter out = resp.getWriter();
		
		if(email!="" && passwd!=""){
			Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher m = p.matcher(email);
			if(!m.matches()){
				out.println("<script>alert('올바른 이메일 주소를 입력하세요');</script>");
			}
			else{
				VisitorObj obj = objDao.getObj(email);
				if(obj == null) out.print("<script> alert('사용자가 존재하지 않습니다.'); </script>");
				else{
					//password 유효성 검사
					if(!obj.getPassword().equalsIgnoreCase(passwd))
						out.print("<script>alert('비밀번호가 올바르지 않습니다.');</script>");
					else{
						objDao.delete(email);
						timeDao.delete(email);
					}
				}
			}
		}
		else{
			out.println("<script>alert('이메일과 비밀번호를 입력하세요');</script>");
		}
		
		logger.info("db delete action");
		
		list = objDao.searchAll();
		model.addAttribute("lists",list);
		
		out.flush();
		return "home";
	}
	
}
