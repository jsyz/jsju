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
import com.yz.vo.AjaxMsgVO;

/**
 * @author Administrator
 * 
 */
@Component("useroAction")
@Scope("prototype")
public class UseroAction extends ActionSupport implements RequestAware,
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

	// 登陆
	private String username;
	private String password;

	// 批量删除
	private String checkedIDs;

	// service层对象
	private IUseroService useroService;
	private IYxareaService yxareaService;

	// 单个对象
	private Usero usero;

	// list对象
	private List<Usero> useros;
	private List<Yxarea> areas;

	// 个人资料新旧密码
	private String password1;
	private String password2;

	/**
	 * 用户登陆
	 * 
	 * @throws Exception
	 */
	public String login() throws Exception {

		if (checkDatebase())// 检查数据库
		{
			Usero adminUsero = new Usero();
			adminUsero.setRealname("管理员");
			adminUsero.setUsername("admin");
			adminUsero.setPassword("admin");
			adminUsero.setUserLimit(0);
			useroService.add(adminUsero);
			session.put("usero", adminUsero);
			return "loginSucc";
		}
		if (username == null || username.equals("") || password == null
				|| password.equals("")) {
			String loginfail = "用户名或密码不能为空";
			request.put("loginFail", loginfail);
			return "adminLogin";
		}
		Usero useroLogin = useroService.useroLogin(username, password);
		if (useroLogin == null) {
			String loginfail = "用户名或密码输入有误";
			request.put("loginFail", loginfail);
			return "adminLogin";
		} else {
			// 设置登陆时间
			if (session.get("usero") == null) {
				//setLoginTime(useroLogin);
				session.put("usero", useroLogin);
			}
			// checkIP();//检查IP地址
			return "loginSucc";
		}
	}

	public String welcome() {
		// 登陆验证
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}
		Usero useroWelcome = useroService.loadById(usero.getId());
		// 欢迎界面
		return "welcome";
	}

	// 设置登陆时间
	/**
	 * 
	 * private void setLoginTime(Usero useroLogin) { // TODO Auto-generated
	 * method stub if (useroLogin.getBeforeLoginTime() == "" ||
	 * useroLogin.getBeforeLoginTime() == null) {
	 * useroLogin.setBeforeLoginTime(DateTimeKit.getLocalTime()); } else {
	 * useroLogin.setBeforeLoginTime(useroLogin .getCurrentLoginTime()); }
	 * useroLogin.setCurrentLoginTime(DateTimeKit.getLocalTime());
	 * useroService.update(useroLogin); }
	 */
	private boolean checkDatebase() {
		// TODO Auto-generated method stub
		areas = yxareaService.getYxareas();
		if(areas==null||areas.size()!=9)
		{
			yxareaService.deleteAllAreas(areas);
		}
		
		useros = useroService.getUseros();
		if (useros==null||useros.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void checkIP() {
		// TODO Auto-generated method stub
		// String ip = getIpAddr(req);
	}

	/*
	 * 获取IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 用户注销
	 */
	public String logout() {
		session.clear();
		return "adminLogin";
	}

	/**
	 * 用户管理
	 */
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
		// 总记录数
		totalCount = useroService.getTotalCount(con, convalue, usero);
		// 总页数
		pageCount = useroService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		useros = useroService.queryList(con, convalue, usero, page, size);
		return "list";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		areas = yxareaService.getYxareas();
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
		useroService.add(usero);

		arg[0] = "useroAction!list";
		arg[1] = "用户管理";
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

		usero = useroService.loadById(id);

		useroService.delete(usero);

		useroService.deleteById(id);
		arg[0] = "useroAction!list";
		arg[1] = "用户管理";
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
			usero = useroService.loadById(ids[i]);

			useroService.delete(usero);
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

		usero = useroService.loadById(id);
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

		useroService.update(usero);
		arg[0] = "useroAction!list";
		arg[1] = "用户管理";
		return "success_child";
	}

	/**
	 * 跳转到修改秒页面
	 * 
	 * @return
	 */
	public String loadPassword() throws Exception {
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}
		password = usero.getPassword();
		return "password";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePassword() throws Exception {
		// 判断会话是否失效
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}
		usero.setPassword(password);
		useroService.update(usero);
		arg[0] = "useroAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
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
		usero = useroService.loadById(id);
		return "view";
	}

	/**
	 * 个人资料
	 */
	public String currentUsero() {
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}
		usero = useroService.loadById(usero.getId());
		;
		return "currentUsero";
	}

	public String updateCurrentUsero() throws Exception {
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}
		if (password1 != null && !password1.replace(" ", "").equals("")
				&& password2 != null && !password2.replace(" ", "").equals("")) {
			usero.setPassword(password1);
		}
		useroService.update(usero);
		arg[0] = "useroAction!currentUsero";
		arg[1] = "个人资料";
		return SUCCESS;
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

	public IUseroService getUseroService() {
		return useroService;
	}

	@Resource
	public void setUseroService(IUseroService useroService) {
		this.useroService = useroService;
	}

	public Usero getUsero() {
		return usero;
	}

	public void setUsero(Usero usero) {
		this.usero = usero;
	}

	public List<Usero> getUseros() {
		return useros;
	}

	public void setUseros(List<Usero> useros) {
		this.useros = useros;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public IYxareaService getYxareaService() {
		return yxareaService;
	}

	@Resource
	public void setYxareaService(IYxareaService yxareaService) {
		this.yxareaService = yxareaService;
	}

	public List<Yxarea> getAreas() {
		return areas;
	}

	public void setAreas(List<Yxarea> areas) {
		this.areas = areas;
	}
	
	
	
}
