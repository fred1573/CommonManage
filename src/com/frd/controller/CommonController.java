package com.frd.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.frd.model.User;
import com.frd.service.UserService;

@Controller
public class CommonController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(@Valid UserForm userForm){
		ModelAndView mav = new ModelAndView("/register");
		mav.addObject(userForm);
		return mav;
	}
							
	@RequestMapping(value="/doRegister", method=RequestMethod.POST)
	public ModelAndView doRegister(@Valid UserForm userForm){
		ModelAndView mav = new ModelAndView("/error/error");
		if(userService.findByName(userForm.getName()).size() == 0){
			mav.setViewName("/register_success");
			User user = new User();
			user.setName(userForm.getName());
			user.setPassword(userForm.getPassword());
			user.setEmail(userForm.getEmail());
			user.setAdmin(false);
			user.setRegisterTime(new Date());
			userService.register(user);
		}
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(@Valid UserForm userForm){
		ModelAndView mav = new ModelAndView("/login");
		mav.addObject(userForm);
		return mav;
	}
	
	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public ModelAndView doLogin(@Valid UserForm userForm,
			HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/error/error");
		User user = new User();
		user.setName(userForm.getName());
		user.setPassword(userForm.getPassword());
		List<User> list = userService.find(user);
		if(list.size() == 1){
			for(User u : list){
				if(u.isAvailable()){
					request.getSession().setAttribute("user", u);
					mav.addObject("user", u);
					mav.setViewName("/user/main");
				}
			}
		}
		return mav;
	}
}
