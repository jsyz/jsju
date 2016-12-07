package com.yz.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yz.model.Project;

public class IntegratedQueryExcel {
	public static final String[] tableHeader = { "序号", "乡镇", "项目名称", "面积（㎡）",
			"造价（万元）", "开工日期", "计划竣工日期", "监理单位", "项目经理", "工程总监", "形象进度" };
	public static final String[] tableHeader1 = { "序号", "乡镇", "项目名称", "面积（㎡）",
			"造价（万元）", "开工日期", "计划竣工日期", "施工单位", "项目经理", "工程总监", "形象进度" };
	public static final String[] tableHeader2 = { "序号", "项目名称", "奖惩内容", "时间",
			"备注" };

	// 创建工作本
	public static HSSFWorkbook demoWorkBook = new HSSFWorkbook();
	// 创建表-up
	public static HSSFSheet demoSheet = demoWorkBook
			.createSheet("IntegratedQueryExcel");
	// 表头的单元格个数目
	// public static final short cellNumber = (short)tableHeader.length;
	public short cellNumber;
	// 数据库表的列数-up
	// public static final int columNumber = 21;
	public int columNumber;

	public static void createTableHeader(int type,String head) {

		
		HSSFHeader header = demoSheet.getHeader();
		header.setCenter(head);
		HSSFRow headerRow = demoSheet.createRow((short) 0);
		for (int i = 0; i < 11; i++) {
			HSSFCell headerCell = headerRow.createCell((short) i);
			headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(type == 0){ // 施工单位
				headerCell.setCellValue(tableHeader[i]);
			}else if(type == 1){ // 监理单位
				headerCell.setCellValue(tableHeader1[i]);
			}
		}
	}
	/** */
	/**
	 * 创建行
	 * 
	 * @param cells
	 * @param rowIndex
	 */
	  public static void createTableRow(List<String> cells,short rowIndex)
	    {
	        // 创建第rowIndex行
	        HSSFRow row = demoSheet.createRow((short) rowIndex);
	        for(short i = 0;i < cells.size();i++)
	        {
	            // 创建第i个单元格
	            HSSFCell cell = row.createCell((short) i);
	            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	            cell.setCellValue(cells.get(i));
	        }
	    }
	  
	  /** *//**
			 * 创建整个Excel表-up
			 * 
			 * @throws SQLException
			 * 
			 */
	    public static void createExcelSheeet(List<Project> projects,int type, short cellNumber,String head)
	    {
	    	// 生成表头
	        createTableHeader(type,head);
	        try {
	        	for (int k = 0;k < projects.size(); k++) {
	        		Project project = projects.get(k);
	            	// 用来装行中的每个单元格
	    			List<String> list = new ArrayList<String>();
	    			list.add(k+1+"");
	    			list.add(project.getYxarea().getAreaname()==null?"":project.getYxarea().getAreaname());
	    			list.add(project.getName()==null?"":project.getName());
	    			list.add(project.getBuildingArea()+"");
	    			list.add(project.getBuildingCost()+"");
	    			list.add(project.getStartDate()==null?"":project.getStartDate());
	    			list.add(project.getPlanendDate()==null?"":project.getPlanendDate());
	    			if(type == 1)
	    			list.add(project.getConstructionUnit()==null?"":project.getConstructionUnit());// constructionUnit
	    			else if(type == 0)
	    			list.add(project.getSupervisionUnit()==null?"":project.getSupervisionUnit());
	    			list.add(project.getProjectManager()==null?"":project.getProjectManager());// projectManager
	    			list.add(project.getEngineeringDirector()==null?"":project.getEngineeringDirector());// engineeringDirector
	    			switch (project.getGraphicProgress()) {// 形象进度(0:未开工/0%,1:基础/30%,2：主体/50%，3:装饰/70%，4：完工待验/100%，5：竣工)
	    			case 0:
						if(project.getEngineeringType()==0)
						{
							list.add("未开工");
						}else
						{
							list.add("0%");
						}
						break;
	    			case 1:
						if(project.getEngineeringType()==0)
						{
							list.add("基础");
						}else
						{
							list.add("30%");
						}
						break;
					case 2:
						if(project.getEngineeringType()==0)
						{
							list.add("主体");
						}else
						{
							list.add("50%");
						}
						break;
					case 3:
						if(project.getEngineeringType()==0)
						{
							list.add("装饰");
						}else
						{
							list.add("70%");
						}
						break;
					case 4:
						if(project.getEngineeringType()==0)
						{
							list.add("完工待验");
						}else
						{
							list.add("100%");
						}
						break;
					case 5:
							list.add("竣工");
						break;
					default:
						list.add("");
						break;
					}
	    			createTableRow(list,(short)(k+1));
	        	}
	        }catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    public void exportExcel(HSSFSheet sheet,OutputStream os) throws IOException
	    {
	        sheet.setGridsPrinted(true);
	        HSSFFooter footer = sheet.getFooter();
	        footer.setRight("Page " + HSSFFooter.page() + " of " +
	        HSSFFooter.numPages());
	        demoWorkBook.write(os);
	    }
	    
	  /*
		 * public static void main(String[] args) { String fileName =
		 * "E:/lowrent.xls"; FileOutputStream fos = null; try {
		 * createExcelSheeet(); fos = new FileOutputStream(fileName);
		 * exportExcel(demoSheet,fos); JOptionPane.showMessageDialog(null,
		 * "表格已成功导出到 : "+fileName); } catch (Exception e) {
		 * JOptionPane.showMessageDialog(null, "表格导出出错，错误信息
		 * ："+e+"\n错误原因可能是表格已经打开。"); e.printStackTrace(); } finally { try {
		 * fos.close(); } catch (Exception e) { e.printStackTrace(); } } }
		 */
	    
		public static boolean exportExcel(String savePath,List<Project> projects,int type,short cellNumber,String head){
			
			FileOutputStream fos = null;
	           try {
	           	   IntegratedQueryExcel pd = new IntegratedQueryExcel();
	               pd.createExcelSheeet(projects,type,cellNumber,head);
	               fos = new FileOutputStream(savePath);
	               pd.exportExcel(demoSheet,fos);
	               
	           } catch (Exception e) {
	               e.printStackTrace();
	               return false;
	           } finally {
	               try {
	                   fos.close();
	               } catch (Exception e) {
	                   e.printStackTrace();
	                   return false;
	               }
	           }
	           return true;
		}
}
