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

import com.frd.model.AccountIn;
import com.frd.model.CheckInfo;
import com.frd.model.User;
import com.frd.service.AccountInService;
import com.frd.service.CheckInfoService;
import com.frd.service.UserService;

@Controller
@RequestMapping("/accountIn")
public class AccountInController {

	private static String INOROUT = "in";

	@Resource
	private AccountInService accountInService;

	@Resource
	private CheckInfoService checkInfoService;

	@Resource
	private UserService userService;

	@RequestMapping(value = "/produce", method = RequestMethod.GET)
	public ModelAndView produce(@Valid AccountInForm accountInForm) {
		ModelAndView mav = new ModelAndView("/account_in/produce");
		mav.addObject(accountInForm);
		return mav;
	}

	@RequestMapping(value = "/doProduce", method = RequestMethod.POST)
	public ModelAndView doProduce(@Valid AccountInForm accountInForm,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:list");
		AccountIn ain = new AccountIn();
		ain.setNumber(accountInForm.getNumber());
		ain.setTime(new Date());
		User u = (User) request.getSession().getAttribute("user");
		ain.setUid(u.getId());
		ain.setVerify(false);
		accountInService.produce(ain);
		List<AccountIn> list = accountInService.find();
		mav.addObject("list", list);
		mav.addObject("checkedUserIdMap", checkedUserIdMap());

		mav.addObject("allowRepeatCheck", allowRepeatCheck(request));
		return mav;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/account_in/list");
		List<AccountIn> list = accountInService.find();
		mav.addObject("list", list);

		mav.addObject("checkedUserIdMap", checkedUserIdMap());

		mav.addObject("allowRepeatCheck", allowRepeatCheck(request));
		return mav;
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView check(@RequestParam("id") int id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:list");
		AccountIn ain = accountInService.get(id);
		if(request.getSession().getAttribute("user") != null){
			User u = (User)request.getSession().getAttribute("user");
			CheckInfo cinfo = new CheckInfo();
			cinfo.setAccountId(id);
			cinfo.setCreateTime(new Date());
			cinfo.setUserId(u.getId());
			cinfo.setInOrOut(INOROUT);
			checkInfoService.create(cinfo);
		}
		if(ain != null){
			List<CheckInfo> checkInfoList = checkInfoService.findByAccountIdAndInOrOut(ain.getId(), INOROUT);
			List<User> userList = userService.find();
			if(checkInfoList.size() == userList.size()){
				ain.setVerify(true);
				accountInService.check(ain);
			}
		}
		
		List<AccountIn> list = accountInService.find();
		mav.addObject("list", list);
		mav.addObject("checkedUserIdMap", checkedUserIdMap());
		mav.addObject("allowRepeatCheck", allowRepeatCheck(request));
		return mav;
	}

	// 针对每条accountIn记录拼接其已通过的用户id
	private Map<Integer, String> checkedUserIdMap() {
		List<CheckInfo> checkInfoList = checkInfoService
				.findByInOrOut(INOROUT); 
		List<AccountIn> accountInList = accountInService.find();
		if(checkInfoList.size() != 0 && accountInList.size() != 0){
			int k = 0;
			CheckInfo[] checkInfoArray = new CheckInfo[checkInfoList.size()];
			for(CheckInfo cinfo : checkInfoList){
				checkInfoArray[k] = cinfo;
				k++;
			}
			int n = 0;
			AccountIn[] accountInArray = new AccountIn[accountInList.size()];
			for(AccountIn ain : accountInList){
				accountInArray[n] = ain;
				n++;
			}
			Map<Integer, String> checkedUserIdMap = new HashMap<Integer, String>();
			for (int i = 0; i < accountInArray.length; i++) {
				String userIds = null;
				for (int j = 0; j < checkInfoArray.length; j++) {
					if (accountInArray[i].getId() == checkInfoArray[j]
							.getAccountId()) {
						if(userIds == null){
							userIds = "" + checkInfoArray[j].getUserId();
						}else{
							userIds += "#" + checkInfoArray[j].getUserId();
						}
					}
				}
				checkedUserIdMap.put(accountInArray[i].getId(), userIds);
			}
			return checkedUserIdMap;
		}
		return null;
	}
	
	private Map<Integer, Boolean> allowRepeatCheck(HttpServletRequest request){
		if(request.getSession().getAttribute("user") != null){
			User u = (User) request.getSession().getAttribute("user");
			List<CheckInfo> checkInfoList = checkInfoService.findByUserId(u.getId());
			List<AccountIn> accountInList = accountInService.find();
			Map<Integer, Boolean> allowRepeatMap = new HashMap<Integer, Boolean>();
			for(AccountIn ain : accountInList){
				allowRepeatMap.put(ain.getId(), Boolean.TRUE);
				for(CheckInfo cinfo : checkInfoList){
					if(ain.getId() == cinfo.getAccountId()){
						allowRepeatMap.remove(ain.getId());
						allowRepeatMap.put(ain.getId(), Boolean.FALSE);
					}
				}
			}
			return allowRepeatMap;
		}
		return null;
	}
	
//	private Map<Integer, Boolean> isRepeatCheck(AccountIn ain, HttpServletRequest request){
//		if(request.getSession().getAttribute("user") != null){
//			User u = (User) request.getSession().getAttribute("user");
//			List<CheckInfo> checkInfoList = checkInfoService.findByUserId(u.getId());
//			List<AccountIn> accountInList = accountInService.find();
//			Map<Integer, Boolean> allowRepeatMap = new HashMap<Integer, Boolean>();
//			for(AccountIn ain : accountInList){
//				allowRepeatMap.put(ain.getId(), Boolean.TRUE);
//				for(CheckInfo cinfo : checkInfoList){
//					if(ain.getId() == cinfo.getAccountId()){
//						allowRepeatMap.remove(ain.getId());
//						allowRepeatMap.put(ain.getId(), Boolean.FALSE);
//					}
//				}
//			}
//			return allowRepeatMap;
//		}
//		return null;
//	}
}
