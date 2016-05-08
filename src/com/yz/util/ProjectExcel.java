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


public class ProjectExcel {
	 //表头-up
    public static final String[] tableHeader = {"序号","乡镇分类","工程分类","建设单位","建设单位负责人","联系电话","项目名称","面积（㎡）","造价（万元）","结构层次","单位工程","开工日期",
    	"计划竣工日期","施工单位","监理单位","项目经理","工程总监","清欠负责人","联系电话","形象进度","是否本月新开工","施工许可证发证日期","备注"};
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
    public static void createTableRow(List<String> cells,short rowIndex)
    {
        //创建第rowIndex行
        HSSFRow row = demoSheet.createRow((short) rowIndex);
        for(short i = 0;i < cells.size();i++)
        {
            //创建第i个单元格
            HSSFCell cell = row.createCell((short) i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(cells.get(i));
        }
    }
    
    /** *//**
     * 创建整个Excel表-up
     * @throws SQLException 
     *
     */
    public static void createExcelSheeet(List<Project> projects)
    {
    	//生成表头
        createTableHeader();
        try {
        	for (int k = 0;k < projects.size(); k++) {
        		Project project = projects.get(k);
            	//用来装行中的每个单元格
    			List<String> list = new ArrayList<String>();
    			list.add(k+1+"");
    			list.add(project.getYxarea().getAreaname()==null?"":project.getYxarea().getAreaname());
    			switch (project.getEngineeringType()) {//engineeringType 工程分类(0:土建,1：装饰，2:市政,3:绿化，4：照明亮化)
				case 0:
					list.add("土建");
					break;
				case 1:
					list.add("装饰");
					break;
				case 2:
					list.add("市政");
					break;
				case 3:
					list.add("绿化");
					break;
				case 4:
					list.add("照明亮化");
					break;
				case 5:
					list.add("其他");
					break;
				default:
					list.add("");
					break;
				}
    			list.add(project.getBuildUnit()==null?"":project.getBuildUnit());
    			list.add(project.getBuildUnitPrincipal()==null?"":project.getBuildUnitPrincipal());
    			list.add(project.getBuildUnittelphone()==null?"":project.getBuildUnittelphone());
    			list.add(project.getName()==null?"":project.getName());
    			list.add(project.getBuildingArea()+"");
    			list.add(project.getBuildingCost()+"");
    			list.add(project.getStructureLevel()==null?"":project.getStructureLevel());//structureLevel
    			list.add(project.getBuildingNumber()==null?"":project.getBuildingNumber().toString());
    			list.add(project.getStartDate()==null?"":project.getStartDate());
    			list.add(project.getPlanendDate()==null?"":project.getPlanendDate());
    			list.add(project.getConstructionUnit()==null?"":project.getConstructionUnit());//constructionUnit
    			list.add(project.getSupervisionUnit()==null?"":project.getSupervisionUnit());
    			list.add(project.getProjectManager()==null?"":project.getProjectManager());//projectManager
    			list.add(project.getEngineeringDirector()==null?"":project.getEngineeringDirector());//engineeringDirector
    			list.add(project.getClearPrincipal()==null?"":project.getClearPrincipal());//clearPrincipal
    			list.add(project.getClearPrincipalTelphone()==null?"":project.getClearPrincipalTelphone());
    			switch (project.getGraphicProgress()) {//形象进度(0:未开工/0%,1:基础/30%,2：主体/50%，3:装饰/70%，4：完工待验/100%，5：竣工)
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
    			if(project.getIsNewProjectMonth()==0)
    			{
    				list.add("跨年度");
    			}else
    			{
    				list.add("本月新开工");
    			}
    			list.add(project.getConstructionPermitNumber()==null?"":project.getConstructionPermitNumber());
    			list.add(project.getPremarks()==null?"":project.getPremarks());
    			createTableRow(list,(short)(k+1));
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
    
	public static boolean exportExcel(String savePath,List<Project> projects){
		
		FileOutputStream fos = null;
           try {
           	   ProjectExcel pd = new ProjectExcel();
               pd.createExcelSheeet(projects);
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
