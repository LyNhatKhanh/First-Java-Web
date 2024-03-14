package com.laptrinhjavaweb.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.service.ICategoryService;

//import com.laptrinhjavaweb.model.UserModel;

@WebServlet(urlPatterns = { "/trang-chu" }) // multiple != (value = "/trang-chu")
public class HomeController extends HttpServlet {
	
	@Inject
	private ICategoryService categoryService;

	private static final long serialVersionUID = 2686801510274002166L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*// set model to response to Controller
		UserModel userModel = new UserModel();
		userModel.setFullName("Ly Nhat Khanh xin chao");
		request.setAttribute("model", userModel);*/
		
		request.setAttribute("categories", categoryService.findAll());
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp"); // views muon tra ve 
																					// (dispatcher: nguoi gui di - dieu phoi)
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
