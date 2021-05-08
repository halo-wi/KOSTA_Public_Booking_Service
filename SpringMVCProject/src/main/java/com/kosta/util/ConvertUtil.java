package com.kosta.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ConvertUtil {
	public static  int covertInt(String param) {
		return Integer.parseInt(param);
	}
	public static double covertDouble(String param) {
		return Double.parseDouble(param);
	}
	public static java.sql.Date covertDate(String param) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date d=null;
			try {
				d = new java.sql.Date(sdf.parse(param).getTime());
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return d;
	}
	public static java.sql.Date covertDate2(String param) {
		return java.sql.Date.valueOf(param);

	}
	
}

