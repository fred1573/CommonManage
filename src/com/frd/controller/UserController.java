package com.frd.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.frd.model.User;
import com.frd.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
//	@RequestMapping(value="/register",method=RequestMethod.GET)
//	public ModelAndView register(@Valid UserForm userForm){
//		ModelAndView mav = new ModelAndView("/user/register");
//		mav.addObject(userForm);
//		return mav;
//	}
//							
//	@RequestMapping(value="/doRegister", method=RequestMethod.POST)
//	public ModelAndView doRegister(@Valid UserForm userForm){
//		ModelAndView mav = new ModelAndView("/error/error");
//		if(userService.findByName(userForm.getName()).size() == 0){
//			mav.setViewName("/user/register_success");
//			User user = new User();
//			user.setName(userForm.getName());
//			user.setPassword(userForm.getPassword());
//			user.setEmail(userForm.getEmail());
//			user.setAdmin(false);
//			user.setRegisterTime(new Date());
//			userService.register(user);
//		}
//		return mav;
//	}
//	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public ModelAndView login(@Valid UserForm userForm){
//		ModelAndView mav = new ModelAndView("/user/login");
//		mav.addObject(userForm);
//		return mav;
//	}
//	
//	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
//	public ModelAndView doLogin(@Valid UserForm userForm,
//			HttpServletRequest request){
//		ModelAndView mav = new ModelAndView("/error/error");
//		User user = new User();
//		user.setName(userForm.getName());
//		user.setPassword(userForm.getPassword());
//		List<User> list = userService.find(user);
//		if(list.size() == 1){
//			for(User u : list){
//				if(u.isAvailable()){
//					request.getSession().setAttribute("user", u);
//					mav.addObject("user", u);
//					mav.setViewName("/user/main");
//				}
//			}
//		}
//		return mav;
//	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView("/user/list");
		List<User> list = userService.find();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value={"/lock","/unlock"}, method=RequestMethod.GET)
	public ModelAndView lock(@RequestParam("id")int id){
		ModelAndView mav = new ModelAndView("redirect:list");
		User u = userService.get(id);
		if(u != null){
			if(!u.isAdmin() && u.isAvailable()){
				//lock
				u.setAvailable(false);
				userService.lock(u);
			}else if(!u.isAdmin() && !u.isAvailable()){
				//unlock
				u.setAvailable(true);
				userService.lock(u);
			}else{
				mav.setViewName("/error/error");
			}
		}
		List<User> list = userService.find();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="/personalData", method=RequestMethod.GET)
	public ModelAndView personalData(@Valid UserForm userForm){
		ModelAndView mav = new ModelAndView("/user/personal_setting");
		mav.addObject(userForm);
		return mav;
	}
	
	@RequestMapping(value="/personalSetting", method=RequestMethod.POST)
	public ModelAndView personalSetting(@Valid UserForm userForm,
			HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/login");
		User u = userService.get(userForm.getId());
		u.setPassword(userForm.getPassword());
		userService.update(u);
		try{
			if(request.getSession().getAttribute("user") != null){
				request.getSession().removeAttribute("user");	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/exit", method=RequestMethod.GET)
	public ModelAndView exit(@Valid UserForm userForm,
			HttpServletRequest req){
		ModelAndView mav = new ModelAndView("/login");
		try{
			if(req.getSession().getAttribute("user") != null){
				req.getSession().removeAttribute("user");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject(userForm);
		return mav;
	}
	
	@RequestMapping(value="/userNameCheck", method=RequestMethod.GET)
	public void userNameCheck(@RequestParam("name")String name,
			HttpServletResponse response) throws IOException{
		List<User> userList = userService.findByName(name);
		if(userList.size() > 0){
			response.getWriter().print("failure");
		}else{
			response.getWriter().print("success");
		}
	}
	
	@RequestMapping(value="/emailCheck", method=RequestMethod.GET)
	public void emailCheck(@RequestParam("email")String email,
			HttpServletResponse response) throws IOException{
		List<User> userList = userService.findByEmail(email);
		if(userList.size() > 0){
			response.getWriter().print("failure");
		}else{
			response.getWriter().print("success");
		}
	}
}
