package com.frd.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.frd.model.AccountIn;
import com.frd.model.AccountOut;
import com.frd.service.AccountInService;
import com.frd.service.AccountOutService;

@Controller
@RequestMapping("/balance")
public class BalanceController {

	@Resource
	private AccountInService accountInService;
	
	@Resource
	private AccountOutService accountOutService;
	
	@RequestMapping(value="/sum", method=RequestMethod.GET)
	public ModelAndView sum(){
		ModelAndView mav = new ModelAndView("/balance/display");
		List<AccountIn> ainList = accountInService.find(true);
		List<AccountOut> aoutList = accountOutService.find(true);
		double inSum = 0, outSum = 0;
		for(AccountIn ain : ainList){
			inSum += ain.getNumber().doubleValue();
		}
		for(AccountOut aout : aoutList){
			outSum += aout.getNumber().doubleValue();
		}
		double sum = inSum - outSum;
		mav.addObject("sum", sum);
		return mav;
	}
}
