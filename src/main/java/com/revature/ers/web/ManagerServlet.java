package com.revature.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ers.controllers.ManagerController;

public class ManagerServlet extends HttpServlet {
	
	private static Logger log=Logger.getLogger(ManagerServlet.class);
	private ManagerController mc = new ManagerController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setStatus(404);
		final String URI = req.getRequestURI().replace("/project-1/man/", "");
		if(req.getSession(false)==null) {
			log.warn("Request All Reimbursement Failed. User's Credential is not found");
			resp.setStatus(401);
			resp.getWriter().print("Unauthorized User");
		} else {
			switch (URI) {
			case "view-all":
				mc.viewAllReimbursement(req, resp);				
				break;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		doGet(req, resp);
	}
	
	
}
