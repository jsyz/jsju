package com.yz.util;

import java.util.ArrayList;
import java.util.List;


public class TestMain {

	/**
	 * @param args
	 */
	private static String infoExtractionMsg;
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("123");
		list.add("123");
		System.out.println(listToString(list));
		
	}
	
	
	public static String listToString(List<String> stringList) {
		if (stringList == null||stringList.size()<1) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append("/"+string);
		}
		return result.toString();
	}
	
	/*private static void handleInfoExtractionMsg(String infoExtraction) {
		// TODO Auto-generated method stub
		if(infoExtraction!=null&&infoExtraction.length()>0&& infoExtraction.contains(",") )
		{
			String[] infoExtractions = infoExtraction.split(",");
			infoExtractionMsg = "{'";
			for(int i=0;i<infoExtractions.length;i++)
			{
				infoExtractionMsg = infoExtractionMsg+infoExtractions[i]+"','";
			}
			infoExtractionMsg = (infoExtractionMsg.substring(0, infoExtractionMsg.length()-2)+"}").trim();
		}else
		{
			infoExtractionMsg =  "{}";
		}
		
	}
*/
}
