package com.test.netCatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieData {
	public static String DataSelect(String string) {
		String data = null;
		String data2=null;
		Pattern p=Pattern.compile("<li .*class=.*</li>");
		Matcher m=p.matcher(string);
		while(m.find()) {
			 data=m.group();
			 Pattern p2=Pattern.compile(">.*</.+>");
			 Matcher m2=p2.matcher(data);
			 while(m2.find()) {
				 data2=m2.group();
			 }
		}
		return data2;
	}

}
