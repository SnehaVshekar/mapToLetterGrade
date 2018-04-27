/*
 * Lab5Servlet.java
 *
 * Copyright:  2008 Kevin A. Gary All Rights Reserved
 *
 */
package edu.asupoly.ser422.lab5;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author Kevin Gary
 *
 */
@SuppressWarnings("serial")
public class LetterGradeMapperServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Map<String, String> result = new HashMap<>();
		String gradeString = req.getParameter("grade");

		if (gradeString == null) {
			res.sendError(400, "Grade value not provided");
			return;
		}
		Double grade;
		try {
			grade = Double.parseDouble(gradeString);
			String letterGrade = LetterGradeMapper.mapToLetterGrade(grade);
			result.put("letterGrade", letterGrade);
			res.setContentType("application/json");
			PrintWriter out = res.getWriter();
			out.println(new ObjectMapper().writeValueAsString(result));

		}
		catch (NumberFormatException e){
			res.sendError(400, "Grade value must be a real number");
			return;
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
