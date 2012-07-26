package com.frd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.frd.model.AccountOut;
import com.frd.model.CheckInfo;
import com.frd.model.User;
import com.frd.service.AccountOutService;
import com.frd.service.CheckInfoService;
import com.frd.service.UserService;

@Controller
@RequestMapping("/accountOut")
public class AccountOutController {
	
	private static final String INOROUT = "out";
	
	@Resource
	private AccountOutService accountOutService;
	
	@Resource
	private CheckInfoService checkInfoService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/account_out/list");
		
		List<AccountOut> list = accountOutService.find();
		
		mav.addObject("list", list);
		mav.addObject("checkedUserIdMap", checkedUserIdMap());
		mav.addObject("allowRepeatCheck", allowRepeatCheck(request));
		return mav;
	}
	
	@RequestMapping(value="/produce", method=RequestMethod.GET)
	public ModelAndView produce(@Valid AccountOutForm accountOutForm){
		ModelAndView mav = new ModelAndView("/account_out/produce");
		mav.addObject(accountOutForm);
		return mav;
	}
	
	@RequestMapping(value="/doProduce", method=RequestMethod.POST)
	public ModelAndView doProduce(@Valid AccountOutForm accountOutForm,
			HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:list");
		AccountOut aout = new AccountOut();
		aout.setNumber(accountOutForm.getNumber());
		aout.setTime(new Date());
		User u = (User)request.getSession().getAttribute("user");
		aout.setUid(u.getId());
		aout.setDetails(accountOutForm.getDetails());
		aout.setVerify(false);
		accountOutService.produce(aout);
		List<AccountOut> list = accountOutService.find();
		mav.addObject("list", list);
		mav.addObject("checkedUserIdMap", checkedUserIdMap());
		mav.addObject("allowRepeatCheck", allowRepeatCheck(request));
		return mav;
	}
	
	@RequestMapping(value="/check", method=RequestMethod.GET)
	public ModelAndView check(@RequestParam("id")int id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/account_out/list");
		AccountOut aout = accountOutService.get(id);
		if(request.getSession().getAttribute("user") != null){
			User u = (User)request.getSession().getAttribute("user");
			CheckInfo cinfo = new CheckInfo();
			cinfo.setAccountId(id);
			cinfo.setCreateTime(new Date());
			cinfo.setUserId(u.getId());
			cinfo.setInOrOut(INOROUT);
			checkInfoService.create(cinfo);
		}
		if(aout != null){
			List<CheckInfo> checkInfoList = checkInfoService.findByAccountIdAndInOrOut(aout.getId(), INOROUT);
			List<User> userList = userService.find();
			if(checkInfoList.size() == userList.size()){
				aout.setVerify(true);
				accountOutService.check(aout);
			}
		}
		List<AccountOut> list = accountOutService.find();
		mav.addObject("list", list);
		mav.addObject("checkedUserIdMap", checkedUserIdMap());
		mav.addObject("allowRepeatCheck", allowRepeatCheck(request));
		return mav;
	}
	
	// 针对每条accountIn记录拼接其已通过的用户id
		private Map<Integer, String> checkedUserIdMap() {
			List<CheckInfo> checkInfoList = checkInfoService
					.findByInOrOut(INOROUT); 
			List<AccountOut> accountOutList = accountOutService.find();
			if(checkInfoList.size() != 0 && accountOutList.size() != 0){
				//转换成数组
				int k = 0;
				CheckInfo[] checkInfoArray = new CheckInfo[checkInfoList.size()];
				for(CheckInfo cinfo : checkInfoList){
					checkInfoArray[k] = cinfo;
					k++;
				}
				//转换成数组
				int n = 0;
				AccountOut[] accountOutArray = new AccountOut[accountOutList.size()];
				for(AccountOut aout : accountOutList){
					accountOutArray[n] = aout;
					n++;
				}
				
				Map<Integer, String> checkedUserIdMap = new HashMap<Integer, String>();
				for (int i = 0; i < accountOutArray.length; i++) {
					String userIds = null;
					for (int j = 0; j < checkInfoArray.length; j++) {
						if (accountOutArray[i].getId() == checkInfoArray[j]
								.getAccountId()) {
							if(userIds == null){
								userIds = "" + checkInfoArray[j].getUserId();
							}else{
								userIds += "#" + checkInfoArray[j].getUserId();
							}
						}
					}
					checkedUserIdMap.put(accountOutArray[i].getId(), userIds);
				}
				return checkedUserIdMap;
			}
			return null;
		}
		
		private Map<Integer, Boolean> allowRepeatCheck(HttpServletRequest request){
			if(request.getSession().getAttribute("user") != null){
				User u = (User) request.getSession().getAttribute("user");
				List<CheckInfo> checkInfoList = checkInfoService.findByUserId(u.getId());
				List<AccountOut> accountOutList = accountOutService.find();
				Map<Integer, Boolean> allowRepeatMap = new HashMap<Integer, Boolean>();
				for(AccountOut aout : accountOutList){
					allowRepeatMap.put(aout.getId(), Boolean.TRUE);
					for(CheckInfo cinfo : checkInfoList){
						if(aout.getId() == cinfo.getAccountId()){
							allowRepeatMap.remove(aout.getId());
							allowRepeatMap.put(aout.getId(), Boolean.FALSE);
						}
					}
				}
				return allowRepeatMap;
			}
			return null;
		}
}
