package me.jlblog.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class MenuController {
	
	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String callDashboard() {
		return "/menu/dashboard";
	}

	@RequestMapping(value = "common-code", method = RequestMethod.GET)
	public String callCommonCode() {
		return "/menu/commonCode";
	}
	
	@RequestMapping(value="/getData", method=RequestMethod.GET)
	@ResponseBody
	public String findId(HttpServletRequest request, String userName, String email) throws UnirestException {
		String str = Unirest.get("http://dev.tera.techhub.co.kr:8080/inflow/summary/index")
			.asJson().getBody().toString();
		return str;
	}
}
