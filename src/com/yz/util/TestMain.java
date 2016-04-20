package com.yz.util;


public class TestMain {

	/**
	 * @param args
	 */
	private static String infoExtractionMsg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "1,3,4,";
		
		
		
		String sstr[] = str.split(",");
		
		for (int i = 0; i < sstr.length; i++) {
			System.out.println(sstr[i]+" sdf");
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
