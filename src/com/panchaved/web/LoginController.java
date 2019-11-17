package com.panchaved.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panchaved.util.AppSession;
import com.panchaved.util.ForgotPasswordQuery;
import com.panchaved.util.LoginQuery;



@Controller
@RequestMapping("/")
public class LoginController {

	@RequestMapping(method=RequestMethod.GET)
	public String login(HttpServletRequest req) {
		//if(!AppSession.isLoggedIn(req)) {
		System.out.println("login called!");
		return "login.jsp";
		//}
		//return "success.jsp";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String login(HttpServletRequest req,@RequestParam("userID") long userID,@RequestParam("password")String password,@RequestParam("radiob")String radio , Model model) {
		
		if(LoginQuery.selectQueryLogin(userID,password)) {
			
			HttpSession session = req.getSession();
			session.setAttribute("user", userID);
			session.setAttribute("pass", password);
			model.addAttribute("userID", userID);
			System.out.println("ok!");
			return "redirect:/"+radio;
			
			
		}else {
			model.addAttribute("fail_msg", "INVALID CREDENTIALS");
			return "login.jsp";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		//HttpSession s = request.getSession(false);
		//if(AppSession.isLoggedIn(request)) {
			session.invalidate();
			return "redirect:/";
		//}
		//return "success.jsp";
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword() {
		return "forgotPassword.jsp";
	}
	
	@RequestMapping(value = "/forgotPassword" ,method = RequestMethod.POST)
	public @ResponseBody String setPassword(@RequestParam("userID") long userID, @RequestParam("secAnswer") String secAns) {
		
		return ForgotPasswordQuery.forgotpasswordQuery(userID, secAns);
}
	
}
