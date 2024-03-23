package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-new" }) // multiple != (value = "/trang-chu")
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;
	
	@Inject
	private INewService newService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get Parameters from View (with attribute name in <input> tag)
		/*// 1. Manual	
		NewModel model = new NewModel();
		String pageStr = request.getParameter("page");
		String maxPageItemStr = request.getParameter("maxPageItem");
		if (pageStr != null)
			model.setPage(Integer.parseInt(pageStr));
		else
			model.setPage(1);
		if (maxPageItemStr != null)
			model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
		else
			model.setMaxPageItem(0);*/ 
		
		// 2. From Util (BeanUtils)
		NewModel model = FormUtil.toModel(NewModel.class, request);
 		Integer offset = (model.getPage()-1) * model.getMaxPageItem();
		model.setListResult(newService.findAll(offset, model.getMaxPageItem()));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
		
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list.jsp"); 	// views muon tra ve
																							// (dispatcher: nguoi gui di - dieu phoi)
		rd.forward(request, response);; // additional commit
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}