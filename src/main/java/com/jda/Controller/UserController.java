package com.jda.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jda.Model.Model;
import com.jda.Services.IUserService;
import com.jda.Services.UserService;
import com.jda.util.ApplicationMailer;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView getLoginPage() {
		ModelAndView model = new ModelAndView("Login");
		return model;
	}

	@RequestMapping("/reg")
	public ModelAndView getRegPage() {
		return new ModelAndView("Registeration");
	}

	@RequestMapping(value = "/takedata")
	public ModelAndView takeData(@ModelAttribute("User") Model userModel) {
		if (userService.registerUser(userModel))
			return new ModelAndView("StartUpPage");
		return null;
	}
	
	@RequestMapping(value="forgotpasswordform")
	public ModelAndView getForgotPasswordPage() {
		return new ModelAndView("ForgotPassword");
	}
	
	@RequestMapping(value="Forgot")
	public ModelAndView getMail(@ModelAttribute("User")Model userModel,HttpServletRequest request)
	{
		String requestURL = request.getRequestURL().toString();
		boolean flag = userService.forgotPassword(userModel, requestURL);
		if(flag)
		return new ModelAndView("success");
		return null;
	}
	@RequestMapping(value="ResetPassword")
	public ModelAndView resetPasswordl()
	{
		return new ModelAndView("ResetPassword");
	}
	
	@RequestMapping(value="ResetPassForm")
	public ModelAndView getResetPass(@ModelAttribute("User") Model model,HttpServletRequest request)
	{
String requestURL = request.getHeader("Referer");
		if(userService.updatePass(model,requestURL))
		return new ModelAndView("success");
		
		return null;
			
	}
	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("User") Model model) {
		Model userModel = userService.loginUser(model);
		if (userModel != null) {
			//javaMailSenderimpl.send(ApplicationMailer.sendMail());
			return new ModelAndView("WelcomePage");
		}
		return new ModelAndView("Login");
	}
}
