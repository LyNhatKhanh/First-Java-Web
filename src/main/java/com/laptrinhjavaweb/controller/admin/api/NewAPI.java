package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = -915988021506484384L;
	
	@Inject
	private INewService newService;
	
	// *** ADD ***
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// response to client
		ObjectMapper mapper = new ObjectMapper();
		// Servlet code
		request.setCharacterEncoding("UTF-8");			// chap nhan font VN nhan tu client
		response.setContentType("application/json");	// KDL khi tra KQ lai Client
		// binding JSON to NewModel : HttpUtil.of(request.getReader()) : simple Object of HttpUtils
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newModel.setCreateBy(((UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL")).getUserName());
		newModel = newService.save(newModel);
		mapper.writeValue(response.getOutputStream(), newModel);
	}
	
	// *** UPDATE ***
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response to client
		ObjectMapper mapper = new ObjectMapper();
		// Servlet code
		request.setCharacterEncoding("UTF-8"); // chap nhan font VN nhan tu client
		response.setContentType("application/json"); // KDL khi tra KQ lai Client
		// binding JSON to NewModel : HttpUtil.of(request.getReader()) : simple Object of HttpUtils
		NewModel updateNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL")).getUserName());
		updateNew = newService.update(updateNew); 
		mapper.writeValue(response.getOutputStream(), updateNew);
	}
	
	// *** DELETE ***
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response to client
		ObjectMapper mapper = new ObjectMapper();
		// Servlet code
		request.setCharacterEncoding("UTF-8"); // chap nhan font VN nhan tu client
		response.setContentType("application/json"); // KDL khi tra KQ lai Client
		// binding JSON to NewModel : HttpUtil.of(request.getReader()) : simple Object of HttpUtils
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
	/*// *** SELECT ***
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Servlet code
	}*/

	
}
