package com.revature.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.controllers.EmployeeController;

public class EmployeeServlet extends HttpServlet {

	private static Logger log=Logger.getLogger(UserServlet.class);
	private EmployeeController ec = new EmployeeController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setStatus(404);
		final String URI = req.getRequestURI().replace("/project-1/emp/", "");
		
		switch (URI) {
			case "submit":
				if(req.getSession(false)!=null) {
					ec.submitReimbursement(req, resp);
				} else {
					resp.setStatus(403);
				}				
				break;
			}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

		
}
