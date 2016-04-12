package com.yz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
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
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IUseroService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.util.InitParam;
import com.yz.vo.AjaxMsgVO;

/**
 * @author lq
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
	private int command;

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
	private List<Yxarea> yxareas;

	// 个人资料新旧密码
	private String password1;
	private String password2;
	private String password3;

	/**
	 * 用户登陆
	 * 
	 * @throws Exception
	 */
	public String login() throws Exception {
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
			if (session.get("userSession") == null) {
				// setLoginTime(useroLogin);
				session.put("userSession", useroLogin);
			}
			// checkIP();//检查IP地址
			return "loginSucc";
		}
	}

	public String index2() {
		// 登陆验证
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		return "index2";
	}
	
	public String index() {
		// 登陆验证
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		return "loginSucc";
	}

	// 设置登陆时间

	/**
	 * 初始化默认登录及区域
	 * 
	 * @throws Exception
	 */
	public String inits() throws Exception {
		int backNumber = -1;
		if (command == 1123) {
			for (int i = 0; i < InitParam.AREAS.length; i++) {
				Yxarea yxarea = new Yxarea();
				yxarea.setAreaname(InitParam.AREAS[i]);
				yxarea.setAreaIndex(i + 1);
				yxareaService.add(yxarea);
			}

			Usero usero = new Usero();
			usero.setUsername("admin");
			usero.setPassword("admin");
			usero.setUserLimit(0);
			usero.setRealname("默认管理员");
			usero.setJobTitle("无");
			useroService.add(usero);

			backNumber = 1;
		}
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(backNumber);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 检查用户名是否存在
	 */
	public String checkUsername()
	{
		usero = useroService.getUseroByUseroname(username);
		if(usero!=null)
		{
			AjaxMsgVO msgVO = new AjaxMsgVO();
			msgVO.setMessage("该用户名已经存在,请重新输入.");
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
		}
		return  null;
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
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}
		// 总记录数
		totalCount = useroService.getTotalCount(con, convalue, userSession);
		// 总页数
		pageCount = useroService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		useros = useroService.queryList(con, convalue, userSession, page, size);
		return "list";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		yxareas = yxareaService.getYxareas();
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUser() throws Exception {
		// 判断回话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opfailure_child";
		}
		useroService.add(usero);
		return "success_child";
	}

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opfailure_child";
		}

		usero = useroService.loadById(id);
		useroService.delete(usero);
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
		yxareas = yxareaService.getYxareas();
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
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opfailure_child";
		}
		useroService.update(usero);
		return "success_child";
	}

	/**
	 * 跳转到修改秒页面
	 * 
	 * @return
	 */
	public String loadPassword() throws Exception {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opfailure_child";
		}
		return "password";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePassword() throws Exception {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opfailure_child";
		}
		if (!password1.equals(userSession.getPassword())) {
			String loginfail = "原密码不正确";
			request.put("loginFail", loginfail);
			return "opfailure_child";
		}
		if (!password2.equals(password3)) {
			String loginfail = "两次密码输入不正确";
			request.put("loginFail", loginfail);
			return "opfailure_child";
		}
		userSession.setPassword(password2);
		session.put("userSession", userSession);// 修改session
		useroService.update(userSession);// 修改数据库
		return "success_child";
	}

	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		usero = useroService.loadById(id);
		return "view";
	}

	/**
	 * 个人资料
	 */
	public String currentUsero() {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		usero = useroService.loadById(usero.getId());
		;
		return "currentUsero";
	}

	public String updateCurrentUsero() throws Exception {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
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

	public List<Yxarea> getYxareas() {
		return yxareas;
	}

	public void setYxareas(List<Yxarea> yxareas) {
		this.yxareas = yxareas;
	}

	public String getPassword3() {
		return password3;
	}

	public void setPassword3(String password3) {
		this.password3 = password3;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

}
