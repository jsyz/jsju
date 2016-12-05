package com.yz.action;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.EnterpriseLibrary;
import com.yz.service.IDeviceService;
import com.yz.service.IEnterpriseLibraryService;

@Component("enterpriseLibraryAction")
@Scope("prototype")
public class EnterpriseLibraryAction extends ActionSupport implements RequestAware,
SessionAware, ServletResponseAware, ServletRequestAware{

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;
	
	// 分页显示
	private String[] arg = new String[2];
	private int page;
	private final int size = 10;
	private int pageCount;
	private int totalCount;
	
	// 条件
	private int id;
	private int con;
	private String convalue;
	private int status;// 按状态
	
	private String enterprisename;
	private EnterpriseLibrary enterpriseLibrary;
	
	// list对象
	private List<EnterpriseLibrary> enterpriseLibrarys;
	
	private IEnterpriseLibraryService enterpriseLibraryService;
	
	
	



	



	public EnterpriseLibrary getEnterpriseLibrary() {
		return enterpriseLibrary;
	}


	public String list() throws Exception {
		// 判断会话是否失效
		/*Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}*/
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}
		
		
		//System.out.println("the device list projectId is"+projectId);
		
		// 总记录数
		totalCount = enterpriseLibraryService.getTotalCount(con, convalue);
		// 总页数
		pageCount = enterpriseLibraryService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		
		
		con = 1;
		enterpriseLibrarys = enterpriseLibraryService.queryList(con, convalue,page, size);
		//devices = deviceService.getDevices();
		System.out.println("enterpriseLibrarys have "+enterpriseLibrarys.size());
		return "list";
	}
	
	
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
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

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}

	public List<EnterpriseLibrary> getEnterpriseLibrarys() {
		return enterpriseLibrarys;
	}

	public void setEnterpriseLibrarys(List<EnterpriseLibrary> enterpriseLibrarys) {
		this.enterpriseLibrarys = enterpriseLibrarys;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
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

	public void setEnterpriseLibrary(EnterpriseLibrary enterpriseLibrary) {
		this.enterpriseLibrary = enterpriseLibrary;
	}


	public IEnterpriseLibraryService getEnterpriseLibraryService() {
		return enterpriseLibraryService;
	}

	@Resource
	public void setEnterpriseLibraryService(
			IEnterpriseLibraryService enterpriseLibraryService) {
		this.enterpriseLibraryService = enterpriseLibraryService;
	}

}
