package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
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
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IUseroService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.InitParam;
import com.yz.vo.AjaxMsgVO;

/**
 * @author lq
 * 
 */
@Component("yxareaAction")
@Scope("prototype")
public class YxareaAction extends ActionSupport implements RequestAware,
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
	private int con;
	private String convalue;
	private int status;// 按状态
	private int pid;// 按用户id

	// 批量删除
	private String checkedIDs;

	// service层对象
	private IYxareaService yxareaService;

	// 单个对象
	private Usero usero;
	private Yxarea yxarea;

	// list对象
	private List<Yxarea> yxareas;

	/**
	 * 区域管理
	 */
	public String list() throws Exception {
		// 判断会话是否失效
		/*
		 * Usero usero = (Usero) session.get("usero"); if (yxarea == null) {
		 * return "opsessiongo"; }
		 * 
		 * if (convalue != null && !convalue.equals("")) { convalue =
		 * URLDecoder.decode(convalue, "utf-8"); } if (page < 1) { page = 1; } //
		 * 总记录数 totalCount = yxareaService.getTotalCount(con, convalue, usero); //
		 * 总页数 pageCount = yxareaService.getPageCount(totalCount, size); if
		 * (page > pageCount && pageCount != 0) { page = pageCount; } //
		 * 所有当前页记录对象 yxareas = yxareaService.queryList(con, convalue, usero,
		 * page, size);
		 */
		yxareas = yxareaService.getYxareas();
		return "list";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		// 判断回话是否失效
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo_child";
		}
		yxareaService.add(yxarea);

		arg[0] = "yxareaAction!list";
		arg[1] = "区域管理";
		return "success_child";
	}

	// 上传照片
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

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

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() {
		// 判断会话是否失效
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}

		yxarea = yxareaService.loadById(id);

		yxareaService.delete(yxarea);

		yxareaService.deleteById(id);
		arg[0] = "yxareaAction!list";
		arg[1] = "区域管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteUseros() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			yxarea = yxareaService.loadById(ids[i]);

			yxareaService.delete(yxarea);
		}
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("批量删除成功.");
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

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String load() {

		yxarea = yxareaService.loadById(id);
		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo_child";
		}

		yxareaService.update(yxarea);
		arg[0] = "yxareaAction!list";
		arg[1] = "区域管理";
		return "success_child";
	}

	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}
		yxarea = yxareaService.loadById(id);
		return "view";
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public IYxareaService getYxareaService() {
		return yxareaService;
	}

	@Resource
	public void setYxareaService(IYxareaService yxareaService) {
		this.yxareaService = yxareaService;
	}

	public Usero getUsero() {
		return usero;
	}

	public void setUsero(Usero usero) {
		this.usero = usero;
	}

	public Yxarea getYxarea() {
		return yxarea;
	}

	public void setYxarea(Yxarea yxarea) {
		this.yxarea = yxarea;
	}

	public List<Yxarea> getYxareas() {
		return yxareas;
	}

	public void setYxareas(List<Yxarea> yxareas) {
		this.yxareas = yxareas;
	}

}
