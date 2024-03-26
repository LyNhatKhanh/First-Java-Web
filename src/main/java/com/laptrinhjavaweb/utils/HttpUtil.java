package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	/*
	 * Method toPost: INSERT INTO
	 * From JSON to String & From String to objectModel
	*/
	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}
	
	// String to Model
	public <T> T toModel(Class<T> tClass) {	// can truyen class vao => model.class
		try {
			// String => Model
			return new ObjectMapper().readValue(value, tClass); // value: lay gia tri tu method of(reader)
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// JSON to String
	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return new HttpUtil(sb.toString());
	}
	
	
}
