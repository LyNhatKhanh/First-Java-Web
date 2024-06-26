//package com.laptrinhjavaweb.controller.admin;
//
//import com.laptrinhjavaweb.constant.SystemConstant;
//import com.laptrinhjavaweb.model.NewModel;
//import com.laptrinhjavaweb.paging.PageRequest;
//import com.laptrinhjavaweb.paging.Pageble;
//import com.laptrinhjavaweb.service.INewService;
//import com.laptrinhjavaweb.sort.Sorter;
//import com.laptrinhjavaweb.utils.FormUtil;
//
//import javax.inject.Inject;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(urlPatterns = { "/admin-new" }) // multiple != (value = "/trang-chu")
//public class NewControllerCopy extends HttpServlet {
//
//	private static final long serialVersionUID = 2686801510274002166L;
//
//	@Inject
//	private INewService newService;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// Get Parameters from View (with attribute name in <input> tag)
//		/*// 1. Manual
//		NewModel model = new NewModel();
//		String pageStr = request.getParameter("page");
//		String maxPageItemStr = request.getParameter("maxPageItem");
//		if (pageStr != null)
//			model.setPage(Integer.parseInt(pageStr));
//		else
//			model.setPage(1);
//		if (maxPageItemStr != null)
//			model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
//		else
//			model.setMaxPageItem(0);*/
//
//		// 2. From Util (BeanUtils)
//			// nhận dữ liệu từ client (views)
//		NewModel model = FormUtil.toModel(NewModel.class, request);
//			// set data
//		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
//											new Sorter(model.getSortName(), model.getSortBy()));
//		model.setListResult(newService.findAll(pageble));
//		model.setTotalItem(newService.getTotalItem());
//		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
//			// trả dữ liệu ra views với tên biến 'model'
//		request.setAttribute(SystemConstant.MODEL, model);
//		/* getRequestDispatcher(""): return views muon tra ve
//		   (dispatcher: nguoi gui di - dieu phoi) */
//		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list.jsp");
//		rd.forward(request, response);; // additional commit
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//	}
//
//}
