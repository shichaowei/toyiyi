package com.wsc.qa.web.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsc.qa.meta.User;
import com.wsc.qa.utils.ExcelDataProvider;

@Controller
public class UserController {
	ExcelDataProvider temp;
	ArrayList<User> students ;
	public UserController() {
		temp = new ExcelDataProvider("student", "student");
		students= new ArrayList<User>();
		while(temp.hasNext()){
			Map<String, String> var = temp.next();
			User user = new User();
			user.setUuid(var.get("学号"));
			user.setUserName(var.get("姓名"));
			students.add(user);
		}
	}
	
	@RequestMapping(value="getStudent",produces = "text/plain; charset=utf-8" )
	@ResponseBody
	public String getStudent(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		int max = students.size();
		System.out.println("max is "+max);
		int min = 0;
		Random random = new Random();

//        int s = random.nextInt(max)%(max-min+1) + min;
        int s = random.nextInt(max);
        System.out.println(s);
        String result = students.get(s).getUserName();
        System.out.println(result);
		return result; 
	}
	
	@RequestMapping({ "/" })
	public String getindex(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		return "index";
	}
	
	
	
	

}
