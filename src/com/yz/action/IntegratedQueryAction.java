package com.yz.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Project;
import com.yz.model.Usero;
import com.yz.service.IProjectService;
import com.yz.util.DateTimeKit;
import com.yz.util.IntegratedQueryExcel;
import com.yz.util.ProjectExcel;

@Component("integratedQueryAction")
@Scope("prototype")
public class IntegratedQueryAction extends ActionSupport implements
		RequestAware, SessionAware, ServletResponseAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	// 条件
	private int id;
	private int con;
	private String convalue;
	private int status;// 按状态

	private String input;
	private String input1;
	private int personType;

	private String showName;
	private int isConstructionUnit;
	
	
	private IProjectService projectService;

	// 单个对象
	private Project project;

	// list对象
	private List<Project> projects;

	// 表格条件
	private int integratedQuertyType; // 1:总合查询

	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
			
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.req = req;
	}

	/**
	 * 综合查询
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String count() throws UnsupportedEncodingException {

		 Usero userSession = (Usero) session.get("userSession");
		 if (userSession == null) {
		 return "opsessiongo";
		 }

//		if (con == 1 && input.length() > 0) {
//			if (input1.length() == 0) {
//				integratedQuertyType = 0;
//			} else {
//				if (personType == 1) {
//					integratedQuertyType = 2;
//				} else if (personType == 2) {
//					integratedQuertyType = 3;
//				}
//
//			}
//		} else if (con == 2 && input.length() > 0) {
//			if (input1.length() == 0) {
//				integratedQuertyType = 1;
//			} else {
//				if (personType == 1) {
//					integratedQuertyType = 4;
//				} else if (personType == 2) {
//					integratedQuertyType = 5;
//				}
//			}
//		}

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
				
		showName = convalue;
		if(con == 1){
			isConstructionUnit = 1;
		}else{
			isConstructionUnit = 0;
		}
		
		projects = projectService.queryList(con, convalue,integratedQuertyType);
		
		
//		switch (integratedQuertyType) {
//		case 0:
//			break;
//		case 1:
//			break;
//		case 2:
//			break;
//		case 3:
//			break;
//		default:
//			break;
//		}
		return "excel";

		//		
		// if (convalue != null && !convalue.equals("")) {
		// convalue = URLDecoder.decode(convalue, "utf-8");
		// }
		// if (starttime != null && !starttime.equals("")) {
		// starttime = URLDecoder.decode(starttime, "utf-8");
		// starttime = starttime.replace(" ", "");
		// }
		// if (endtime != null && !endtime.equals("")) {
		// endtime = URLDecoder.decode(endtime, "utf-8");
		// endtime = endtime.replace(" ", "");
		// }
		// if (page < 1) {
		// page = 1;
		// }
		//
		// // 总记录数
		// totalCount = projectService
		// .getTotalCount(status, con, convalue, areaIndex,
		// engineeringType, graphicProgress, starttime, endtime);
		// // 总页数
		// pageCount = projectService.getPageCount(totalCount, size);
		// if (page > pageCount && pageCount != 0) {
		// page = pageCount;
		// }
		// // 所有当前页记录对象
		// projects = projectService.queryList(status, con, convalue, areaIndex,
		// engineeringType, graphicProgress, page, size, starttime,
		// endtime);
		//
		// if (projects != null) {
		// projectNumberTotal = projects.size();
		//
		// for (int i = 0; i < projects.size(); i++) {
		//
		// buildingAreaTotal = buildingAreaTotal
		// + projects.get(i).getBuildingArea();
		// buildingCostTotal = buildingCostTotal
		// + projects.get(i).getBuildingCost();
		// }
		// }
		// return "excel";
	}

	/***************************************************************************
	 * 导出excel表格
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String outputExcel() throws UnsupportedEncodingException {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}

		// 所有当前页记录对象
		projects = projectService.queryList(con, convalue,integratedQuertyType);
		if (projects.size() > 0) {
			// 导出数据-------------------------------------
			String filename = "output\\" + DateTimeKit.getDateRandom()
					+ "_projects.xls";
			String savePath = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ filename;
			System.out.println("[--------------------savePath=" + savePath);
			int type = 0;
			if(con == 1){
				type = 0;
			}else{
				type = 1;
			}
			boolean isexport = IntegratedQueryExcel.exportExcel(savePath, projects,type,(short)projects.size(),convalue);
			if (isexport) {
				request.put("errorInfo", "导出数据成功,下载点<a href='" + filename
						+ "'>-这里-</a>");
				return "opexcel";
			} else {
				request.put("errorInfo", "导出数据失败！");
				return "opexcel";
			}
		} else {
			request.put("errorInfo", "查询失败，未导出数据！");
			return "opexcel";
		}
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public String getConvalue() {
		return convalue;
	}

	public void setConvalue(String convalue) {
		this.convalue = convalue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	@Resource
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public int getIntegratedQuertyType() {
		return integratedQuertyType;
	}

	public void setIntegratedQuertyType(int integratedQuertyType) {
		this.integratedQuertyType = integratedQuertyType;
	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
	}

	public void setReq(javax.servlet.http.HttpServletRequest req) {
		this.req = req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public int getPersonType() {
		return personType;
	}

	public void setPersonType(int personType) {
		this.personType = personType;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getIsConstructionUnit() {
		return isConstructionUnit;
	}

	public void setIsConstructionUnit(int isConstructionUnit) {
		this.isConstructionUnit = isConstructionUnit;
	}

}
