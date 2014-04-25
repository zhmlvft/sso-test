package com.zhm.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value="/home/main")
	public String main()
	{
		return "home";
	}
	@RequestMapping(value="/")
	public String root()
	{
		return "redirect:/home/main.html";
	}
}
