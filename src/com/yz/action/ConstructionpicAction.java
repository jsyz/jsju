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
import com.yz.model.Construction;
import com.yz.model.Constructionpic;
import com.yz.service.IConstructionService;
import com.yz.service.IConstructionpicService;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;

@Component("constructionpicAction")
@Scope("prototype")
public class ConstructionpicAction extends ActionSupport implements
		RequestAware, SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private String[] arg = new String[2];
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	// 条件
	private int id;
	private int conpicid;

	private IConstructionpicService constructionpicService;
	private IConstructionService constructionService;

	// 单个表对象
	private Constructionpic constructionpic;
	private Construction construction;

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
	private int rowid;// 行号
	private int conid;

	public String goToAdd() {

		return "add";
	}

	public String add() throws Exception {

		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/conpic" + rowid, imageName, picture1);
			constructionpic.setPicDir1("/conpic" + rowid + "/" + imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/conpic" + rowid, imageName, picture2);
			constructionpic.setPicDir2("conpic" + rowid + "/" + imageName);
		}
		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/conpic" + rowid, imageName, picture3);
			constructionpic.setPicDir3("conpic" + rowid + "/" + imageName);
		}
		constructionpicService.add(constructionpic);

		arg[0] = "projectAction!bench?id=" + ((Integer) session.get("pid"))
				+ "&areaIndex=" + ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "项目工作台";
		return SUCCESS;
	}

	public String deleteConstructionpic() throws Exception {

		constructionpic = constructionpicService.loadById(conpicid);

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

	public String checkToView() {
		// 查询

		construction = constructionService.loadByCid(conid);

		constructionpic = constructionpicService
				.loadByConidAndRow(conid, rowid);

		if (constructionpic != null) {
			return "load";
		} else {
			return "add";
		}
	}

	public String update() throws Exception {

		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/conpic" + rowid, imageName, picture1);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ constructionpic.getPicDir1());
			photofile.delete();
			constructionpic.setPicDir1("conpic" + rowid + "/" + imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/conpic" + rowid, imageName, picture2);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ constructionpic.getPicDir2());
			photofile.delete();
			constructionpic.setPicDir2("conpic" + rowid + "/" + imageName);
		}

		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/conpic" + rowid, imageName, picture3);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ constructionpic.getPicDir3());
			photofile.delete();
			constructionpic.setPicDir3("conpic" + rowid + "/" + imageName);
		}

		constructionpicService.update(constructionpic);

		arg[0] = "projectAction!bench?id=" + ((Integer) session.get("pid"))
				+ "&areaIndex=" + ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "项目工作台";
		return SUCCESS;
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

	// ------------------get、set方法
	public int getConid() {
		return conid;
	}

	public int getConpicid() {
		return conpicid;
	}

	public Constructionpic getConstructionpic() {
		return constructionpic;
	}

	public IConstructionpicService getConstructionpicService() {
		return constructionpicService;
	}

	public int getId() {
		return id;
	}

	public File getPicture1() {
		return picture1;
	}

	public String getPicture1ContentType() {
		return picture1ContentType;
	}

	public String getPicture1FileName() {
		return picture1FileName;
	}

	public File getPicture2() {
		return picture2;
	}

	public String getPicture2ContentType() {
		return picture2ContentType;
	}

	public String getPicture2FileName() {
		return picture2FileName;
	}

	public File getPicture3() {
		return picture3;
	}

	public String getPicture3ContentType() {
		return picture3ContentType;
	}

	public String getPicture3FileName() {
		return picture3FileName;
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

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setConid(int conid) {
		this.conid = conid;
	}

	public void setConpicid(int conpicid) {
		this.conpicid = conpicid;
	}

	public void setConstructionpic(Constructionpic constructionpic) {
		this.constructionpic = constructionpic;
	}

	@Resource
	public void setConstructionpicService(
			IConstructionpicService constructionpicService) {
		this.constructionpicService = constructionpicService;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setPicture2(File picture2) {
		this.picture2 = picture2;
	}

	public void setPicture2ContentType(String picture2ContentType) {
		this.picture2ContentType = picture2ContentType;
	}

	public void setPicture2FileName(String picture2FileName) {
		this.picture2FileName = picture2FileName;
	}

	public void setPicture3(File picture3) {
		this.picture3 = picture3;
	}

	public void setPicture3ContentType(String picture3ContentType) {
		this.picture3ContentType = picture3ContentType;
	}

	public void setPicture3FileName(String picture3FileName) {
		this.picture3FileName = picture3FileName;
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

	public IConstructionService getConstructionService() {
		return constructionService;
	}

	@Resource
	public void setConstructionService(IConstructionService constructionService) {
		this.constructionService = constructionService;
	}

	public Construction getConstruction() {
		return construction;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

}
