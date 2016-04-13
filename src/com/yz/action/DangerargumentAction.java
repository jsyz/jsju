package com.yz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Dangerargument;
import com.yz.service.IDangerargumentService;
import com.yz.vo.AjaxMsgVO;

@Component("dangerargumentAction")
@Scope("prototype")
public class DangerargumentAction extends ActionSupport implements
		RequestAware, SessionAware, ServletResponseAware, ServletRequestAware {

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
	private int dayid;
	private int danid;

	private IDangerargumentService dangerargumentService;

	// 单个表对象
	private Dangerargument dangerargument;

	public String goToAdd() {
		return "add";
	}

	public String add() throws Exception {

		dangerargumentService.add(dangerargument);
		return "success_child";
	}

	public String deleteDangerargument() throws Exception {

		dangerargument = dangerargumentService.loadById(danid);
		dangerargumentService.delete(dangerargument);
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("删除成功.");
		JSONObject jsonObj = JSONObject.fromObject(msgVO);
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(jsonObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String load() {
		dangerargument = dangerargumentService.loadById(danid);
		return "load";
	}

	public String update() {
		dangerargumentService.update(dangerargument);
		return "success_child";
	}

	// get、set-------------------------------------------

	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
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

	public IDangerargumentService getDangerargumentService() {
		return dangerargumentService;
	}

	@Resource
	public void setDangerargumentService(
			IDangerargumentService dangerargumentService) {
		this.dangerargumentService = dangerargumentService;
	}

	public Dangerargument getDangerargument() {
		return dangerargument;
	}

	public void setDangerargument(Dangerargument dangerargument) {
		this.dangerargument = dangerargument;
	}

	public int getDayid() {
		return dayid;
	}

	public void setDayid(int dayid) {
		this.dayid = dayid;
	}

	public int getDanid() {
		return danid;
	}

	public void setDanid(int danid) {
		this.danid = danid;
	}

}
