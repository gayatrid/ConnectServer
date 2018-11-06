package com.connect.common.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	public static List<String> splitString(String inputString){
		 List<String> returnListOfString = new ArrayList<String>();
	      String[] lines = inputString.split("\\s*\\r?\\n\\s*");
	      for (String line : lines) {
	    	  if(line!=null && line.length()!=0)
	    	  returnListOfString.add(line);
	      }
	      return returnListOfString;
	}

}
