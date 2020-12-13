package com.revature.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsUser;
import com.revature.ers.model.LoginDTO;
import com.revature.ers.service.UserService;


public class UserController {
	
	private static Logger log=Logger.getLogger(UserController.class);
	private ObjectMapper om = new ObjectMapper();
	private UserService us = new UserService();

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(req.getMethod().equals("POST")) {
			
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while (line!=null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			String body = new String(sb);
			LoginDTO lDTO = om.readValue(body, LoginDTO.class);
			try {
				ErsUser ersUser = us.login(lDTO.username, lDTO.password);
				ersUser.setPassword("");
				System.out.println(ersUser.toString());
				String json = om.writeValueAsString(ersUser);
				resp.getWriter().print(json);
				resp.setStatus(200);		
				HttpSession ses = req.getSession();	
				ses.setAttribute("user", lDTO);
				ses.setAttribute("loggedin", true);
			} catch (BusinessException e) {
				log.warn(e.getMessage());
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				resp.setStatus(401);
				resp.getWriter().print(e.getMessage());
			}	
		}
		
	}

}
