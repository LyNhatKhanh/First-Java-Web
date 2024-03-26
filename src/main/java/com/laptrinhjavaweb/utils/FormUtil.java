package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	/*
	 * Method toGet: SELECT
	 * from request.getParameterMap() [String JSON] to objectModel
	*/
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request) { // can truyen class vao => model.class
		T object = null;
		try {
			object = tClass.newInstance();  // = new classModel();
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}

		return object;
	}
}
