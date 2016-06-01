package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Constructionpic;
import com.yz.service.IConstructionpicService;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;

@Component("constructionpicAction")
@Scope("prototype")
public class ConstructionpicAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

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
	private int edid;

	private IConstructionpicService constructionpicService;

	// 单个表对象
	private Constructionpic constructionpic;

	// 图片
	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;
	
	private File picture2;
	private String picture2ContentType;
	private String picture2FileName;
	
	private File picture3;
	private String picture3ContentType;
	private String picture3FileName;
	private int row;
	private int conid;
	
	public String add() throws Exception { 

		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName
							.indexOf("."));
			this.upload("/constructionpic", imageName, picture1);
			constructionpic.setPicDir("constructionpic" + "/"
					+ imageName);
		}
		constructionpicService.add(constructionpic);
		
		
		return "success_child";
	}

	public String deleteConstructionpic() throws Exception {

		constructionpic = constructionpicService.loadById(edid);

		if (constructionpic.getPicDir() != null
				&& !constructionpic.getPicDir().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ constructionpic.getPicDir());
			photofile.delete();
		}

		constructionpicService.delete(constructionpic);
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

	public String[] getArg() {
		return arg;
	}

	public int getConid() {
		return conid;
	}

	public Constructionpic getConstructionpic() {
		return constructionpic;
	}

	public IConstructionpicService getConstructionpicService() {
		return constructionpicService;
	}

	public int getDayid() {
		return dayid;
	}

	public int getEdid() {
		return edid;
	}

	public int getId() {
		return id;
	}

	public int getPage() {
		return page;
	}

	public int getPageCount() {
		return pageCount;
	}
	
	public File getPicture1() {
		return picture1;
	}

	// get、set-------------------------------------------

	public String getPicture1ContentType() {
		return picture1ContentType;
	}

	public String getPicture1FileName() {
		return picture1FileName;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public int getRow() {
		return row;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public int getSize() {
		return size;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public String goToAdd() {
		
		System.out.println("the row is "+row+"and conid is "+conid);
		
		return "add";
	}

	public String load() {
		constructionpic = constructionpicService.loadById(edid);
		return "load";
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public void setConid(int conid) {
		this.conid = conid;
	}

	public void setConstructionpic(Constructionpic constructionpic) {
		this.constructionpic = constructionpic;
	}

	@Resource
	public void setConstructionpicService(IConstructionpicService constructionpicService) {
		this.constructionpicService = constructionpicService;
	}

	public void setDayid(int dayid) {
		this.dayid = dayid;
	}

	public void setEdid(int edid) {
		this.edid = edid;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPicture1(File picture1) {
		this.picture1 = picture1;
	}

	public void setPicture1ContentType(String picture1ContentType) {
		this.picture1ContentType = picture1ContentType;
	}

	public void setPicture1FileName(String picture1FileName) {
		this.picture1FileName = picture1FileName;
	}

	public void setReq(javax.servlet.http.HttpServletRequest req) {
		this.req = req;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String update() {
		constructionpicService.update(constructionpic);
		return "success_child";
	}

	// 文件上传
	public void upload(String fileName, String imageName, File picture)
			throws Exception {
		File saved = new File(ServletActionContext.getServletContext()
				.getRealPath(fileName), imageName);
		InputStream ins = null;
		OutputStream ous = null;
		try {
			saved.getParentFile().mkdirs();
			ins = new FileInputStream(picture);
			ous = new FileOutputStream(saved);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = ins.read(b)) != -1) {
				ous.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ous != null)
				ous.close();
			if (ins != null)
				ins.close();
		}
	}

	public String view() {
		constructionpic = constructionpicService.loadById(id);
		return "view";
	}

	public File getPicture2() {
		return picture2;
	}

	public void setPicture2(File picture2) {
		this.picture2 = picture2;
	}

	public String getPicture2ContentType() {
		return picture2ContentType;
	}

	public void setPicture2ContentType(String picture2ContentType) {
		this.picture2ContentType = picture2ContentType;
	}

	public String getPicture2FileName() {
		return picture2FileName;
	}

	public void setPicture2FileName(String picture2FileName) {
		this.picture2FileName = picture2FileName;
	}

	public File getPicture3() {
		return picture3;
	}

	public void setPicture3(File picture3) {
		this.picture3 = picture3;
	}

	public String getPicture3ContentType() {
		return picture3ContentType;
	}

	public void setPicture3ContentType(String picture3ContentType) {
		this.picture3ContentType = picture3ContentType;
	}

	public String getPicture3FileName() {
		return picture3FileName;
	}

	public void setPicture3FileName(String picture3FileName) {
		this.picture3FileName = picture3FileName;
	}

}
