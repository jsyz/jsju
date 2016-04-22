package com.yz.util;

import java.util.Arrays;


public class TestMain {

	/**
	 * @param args
	 */
	private static String infoExtractionMsg;
	public static void main(String[] args) {
		
		int[] sheetTypes = {7};
		// TODO Auto-generated method stub
		Arrays.sort(sheetTypes); //排序数组

		if(Arrays.binarySearch(sheetTypes,7) >= 0){ //若找不到，则当前企业不在任何填报期内
			System.out.println("have");
		}
		
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
