package com.yz.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.yz.model.Project;
import com.yz.vo.ProjectClassify;


public class ProjectClassifyExcel {
	 //表头-up
    public static final String[] tableHeader = {"序号","     ","    ","项目数","面积（㎡）","造价（万元）"};
    //创建工作本
    public static HSSFWorkbook demoWorkBook = new HSSFWorkbook();
    //创建表-up
    public static HSSFSheet demoSheet = demoWorkBook.createSheet("deviceportExcel");
    //表头的单元格个数目
    public static final short cellNumber = (short)tableHeader.length;
    //数据库表的列数-up
    public static final int columNumber = 21;
    /** *//**
     * 创建表头
     * @return
     */
    public static void createTableHeader()
    {
        HSSFHeader header = demoSheet.getHeader();
        header.setCenter("项目导出数据");
        HSSFRow headerRow = demoSheet.createRow((short) 0);
        for(int i = 0;i < cellNumber;i++)
        {
            HSSFCell headerCell = headerRow.createCell((short) i);
            headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            headerCell.setCellValue(tableHeader[i]);
        }
    }
    /** *//**
     * 创建行
     * @param cells
     * @param rowIndex
     */
    public static void createTableRow(List<String> cells,short rowIndex,short color)
    {
        //创建第rowIndex行
        HSSFRow row = demoSheet.createRow((short) rowIndex);
        for(short i = 0;i < cells.size();i++)
        {
            //创建第i个单元格
        	
        	HSSFCellStyle style = demoWorkBook.createCellStyle();
        	
        	
        	style.setFillForegroundColor(color);
    		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        	
            HSSFCell cell = row.createCell((short) i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(cells.get(i));
            cell.setCellStyle(style);
        }
    }
    
    /** *//**
     * 创建整个Excel表-up
     * @throws SQLException 
     *
     */
    public static void createExcelSheeet(List<ProjectClassify> projectClassifys)
    {
    	//生成表头
        createTableHeader();
        try {
        	for (int k = 0;k < projectClassifys.size(); k++) {
        		ProjectClassify projectClassify = projectClassifys.get(k);
            	//用来装行中的每个单元格
    			List<String> list = new ArrayList<String>();
    			list.add(k+1+"");
    			list.add(projectClassify.getTotalClassifyName()==null?"":projectClassify.getTotalClassifyName());
    			list.add(projectClassify.getClassifyName()==null?"":projectClassify.getClassifyName());
    			
    			list.add(projectClassify.getProjectNumberTotal()+"");
    			list.add(projectClassify.getBuildingAreaTotal()+"");
    			list.add(projectClassify.getBuildingCostTotal()+"");
    			
    			short color = 1;
    			switch (k) {
    		      case 0:
    		      case 1:
    		      case 2:
    		      case 3:
    		      case 4:
    		    	  color = HSSFColor.AQUA.index;
    		        break;
    		      case 5:
    		      case 6:
    		      case 7:
    		      case 8:
    		      case 9:
    		    	  color = HSSFColor.GREEN.index;
    		        break;
    		      case 10:
    		      case 11:
    		      case 12:
    		    	  color = HSSFColor.TURQUOISE.index;
    		        break;
    		      case 13:
    		      case 14:
    		      case 15:
    		      case 16:
    		      case 17:
    		      case 18:
    		    	  color = HSSFColor.CORNFLOWER_BLUE.index;
    		        break;
    		      default:
    		        break;
    		      }
    			
    			createTableRow(list,(short)(k+1),color);
    			
    			
    			
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    /** *//**
     * 导出表格
     * @param sheet
     * @param os
     * @throws IOException
     */
    public void exportExcel(HSSFSheet sheet,OutputStream os) throws IOException
    {
        sheet.setGridsPrinted(true);
        HSSFFooter footer = sheet.getFooter();
        footer.setRight("Page " + HSSFFooter.page() + " of " +
        HSSFFooter.numPages());
        demoWorkBook.write(os);
    }
    
  /*  public static void main(String[] args) {
        String fileName = "E:/lowrent.xls";
         FileOutputStream fos = null;
            try {
                createExcelSheeet();
                fos = new FileOutputStream(fileName);
                exportExcel(demoSheet,fos);
                JOptionPane.showMessageDialog(null, "表格已成功导出到 : "+fileName);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "表格导出出错，错误信息 ："+e+"\n错误原因可能是表格已经打开。");
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }*/
    
	public static boolean exportExcel(String savePath,List<ProjectClassify> projectClassifys){
		
		FileOutputStream fos = null;
           try {
           	   ProjectClassifyExcel pd = new ProjectClassifyExcel();
               pd.createExcelSheeet(projectClassifys);
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
