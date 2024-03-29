package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;
import com.mysql.cj.Session;


@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/thoat" })
public class HomeController extends HttpServlet {
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private IUserService userService;

	private static final long serialVersionUID = 2686801510274002166L;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String message = request.getParameter("message");		// username_password_invalid => Username or Password is invalid (in message.properties)
			String alert = request.getParameter("alert");			// danger
			if (message != null & alert != null) {
				// trả dữ liệu ra views với tên biến 'model'
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request,"USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
			// from Controller "/thoat" => Controller "/trang-chu", then getRequestDispatcher("/views/web/home.jsp") go to view
		} else {
//			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp"); // go to view
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			// logic
			if (model != null) {
				// put data & retain its while login
				SessionUtil.getInstance().putValue(request,"USERMODEL", model);
				if (model.getRole().getCode().equals("USER"))
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				else if (model.getRole().getCode().equals("ADMIN")) 
					response.sendRedirect(request.getContextPath()+"/admin-home");
			} else 
				// request.getContextPath(): http://localhost:8080/new-jdbc
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
		} 
		
		
	}

}
