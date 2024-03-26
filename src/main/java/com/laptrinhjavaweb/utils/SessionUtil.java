package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    // HttpServletRequest initialized session-object => request.getSession()

    private static SessionUtil sessionUtil =null;

    // check SessionUtil have been already initialized, haven't SessionUtil?
    public static SessionUtil getInstance() {
        if (sessionUtil == null)
            sessionUtil = new SessionUtil();
        return sessionUtil;
    }

    // push data & retain user-data when log in
    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key,value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    // when log out
    public void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }


}
