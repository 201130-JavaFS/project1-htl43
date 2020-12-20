package com.revature.ers.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsReimbursment;
import com.revature.ers.model.ErsUser;
import com.revature.ers.service.ManagerService;

public class ManagerController {

	private static Logger log = Logger.getLogger(ManagerController.class);

	private ObjectMapper om = new ObjectMapper();
	private ManagerService ms = new ManagerService();

	public void viewAllReimbursement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<ErsReimbursment> listR;
		HttpSession ses = req.getSession();	
		ErsUser ersUser = (ErsUser)ses.getAttribute("user");
		if(ersUser.getRole().getUserRoleId()!=2) {
			resp.setStatus(403);
			resp.getWriter().print("No Permission for the resource");
			log.warn("<Request All Reimbursment Failed!>");
			log.warn("No Permission for the resource");
		} else {
			try {
				listR = ms.getAllRibs();
				String json = om.writeValueAsString(listR);
				resp.getWriter().print(json);
				resp.setStatus(200);
				log.info("<Request All Reimbursments Succmssfully!>");
			} catch (BusinessException e) {
				if(e.getMessage().equals("No Reimbursement Record Found in System")) {
					resp.setStatus(204);
				} else {
					resp.setStatus(500);
				}		
				resp.getWriter().print(e.getMessage());
				log.warn("<Request All Reimbursment Failed!>");
				log.warn(e.getMessage());
			}
		}
		
	}

}
