package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.yz.model.Device;
import com.yz.model.Project;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IDeviceService;
import com.yz.service.IProjectService;
import com.yz.service.IUseroService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;

/**
 * @author Administrator
 * 
 */
@Component("deviceAction")
@Scope("prototype")
public class DeviceAction extends ActionSupport implements RequestAware,
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
	private int pid;// 按设备id
	private int areaIndex;
	// // 登陆
	// private String username;
	// private String password;

	private String name; // 设备名称
	private String propertyCardNumber;// 产权证号
	private String installTime;// 安装告知时间
	private String checkTime;// 检测时间
	private Integer isDealUsecard;// 是否办理使用登记证
	private String usecardExpireTime;// 登记证到期时间
	private String removeTime;// 拆卸告知日期
	// 批量删除
	private String checkedIDs;

	// service层对象
	private IDeviceService deviceService;
	private IProjectService projectService;
	private IYxareaService yxareaService;
	// 单个对象
	private Device device;
	private Project project;
	private AreaVO areaVO;
	// list对象
	private List<Device> devices;

	// list对象
	private List<Project> projects;
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;

	// 设备类型
	private Integer devType;


	/**
	 * 设备管理
	 */
	public String list() throws Exception {
		// 判断会话是否失效
		/*
		 * Usero usero = (Usero) session.get("usero"); if (usero == null) {
		 * return "opsessiongo"; }
		 */
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}

		initAreas();

		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		project = projectService.loadById(pid);
		// System.out.println("the device list projectId is"+projectId);

		// 总记录数
		totalCount = deviceService.getTotalCount(con, convalue, pid);
		// 总页数
		pageCount = deviceService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象

		devices = deviceService.queryList(con, convalue, pid, page, size);
		// devices = deviceService.getDevices();
		return "list";
	}

	private void initAreas() {
		areaVOs = new ArrayList<AreaVO>();
		yxareas = yxareaService.getYxareas();
		for (Yxarea yxarea : yxareas) {
			AreaVO areaVO = new AreaVO();
			areaVO.setId(yxarea.getId());
			areaVO.setIndex(yxarea.getAreaIndex());
			areaVO.setAreaName(yxarea.getAreaname());
			int numberTotal = 0;
			float areaTotal = 0f;
			float costTotal = 0f;

			if (yxarea.getProjects() != null && yxarea.getProjects().size() > 0) {
				// 处理已上传区域项目数量
				projects = new ArrayList<Project>();
				List<Project> pros = yxarea.getProjects();
				if (pros != null && pros.size() > 0) {
					for (Project project : pros) {
						if (project.getIsUpload() != null
								&& project.getIsUpload() == 1) {
							projects.add(project);
						}
					}
				}
				numberTotal = projects.size();
				for (int i = 0; i < projects.size(); i++) {
					areaTotal += projects.get(i).getBuildingArea();
					costTotal += projects.get(i).getBuildingCost();
				}
			}
			areaVO.setProjectNumberTotal(numberTotal);
			areaVO.setBuildingAreaTotal(areaTotal);
			areaVO.setBuildingCostTotal(costTotal);
			areaVOs.add(areaVO);
		}
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		initAreas();
		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		project = projectService.loadById(pid);
		return "add";
	}

	/**
	 * 添加设备
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		deviceService.add(device);

		arg[0] = "deviceAction!list?pid=" + pid + "&areaIndex="
				+ ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "设备管理";
		return "success";
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
		// Device device = (Device) session.get("device");
		// if (device == null) {
		// return "opsessiongo";
		// }
		device = deviceService.loadById(id);

		deviceService.delete(device);

		// deviceService.deleteById(id);
		arg[0] = "deviceAction!list?pid=" + pid + "&areaIndex="
				+ ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "设备管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteDevices() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			device = deviceService.loadById(ids[i]);

			deviceService.delete(device);
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
		initAreas();
		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		project = projectService.loadById(pid);

		device = deviceService.loadById(id);
		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		// Device device = (Device) session.get("device");
		// if (device == null) {
		// return "opsessiongo_child";
		// }

		deviceService.update(device);
		arg[0] = "deviceAction!list?pid=" + pid + "&areaIndex="
				+ ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "设备管理";
		return "success_child";
	}

	/**
	 * 跳转到修改秒页面
	 * 
	 * @return
	 */
	// public String loadPassword() throws Exception {
	// Device device = (Device) session.get("device");
	// if (device == null) {
	// return "opsessiongo";
	// }
	// password = device.getPassword();
	// return "password";
	// }
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	// public String updatePassword() throws Exception {
	// // 判断会话是否失效
	// Device device = (Device) session.get("device");
	// if (device == null) {
	// return "opsessiongo";
	// }
	// device.setPassword(password);
	// deviceService.update(device);
	// arg[0] = "deviceAction!list";
	// arg[1] = "用户管理";
	// return SUCCESS;
	// }
	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		// Device device = (Device) session.get("device");
		// if (device == null) {
		// return "opsessiongo";
		// }

		device = deviceService.loadById(id);
		return "view";
	}

	/**
	 * 设备资料
	 */
	public String currentDevice() {
		Device device = (Device) session.get("device");
		if (device == null) {
			return "opsessiongo";
		}
		device = deviceService.loadById(device.getId());

		return "currentDevice";
	}

	public String updateCurrentDevice() throws Exception {
		Device device = (Device) session.get("device");
		if (device == null) {
			return "opsessiongo";
		}
		deviceService.update(device);
		arg[0] = "deviceAction!currentDevice";
		arg[1] = "设备资料";
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

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertyCardNumber() {
		return propertyCardNumber;
	}

	public void setPropertyCardNumber(String propertyCardNumber) {
		this.propertyCardNumber = propertyCardNumber;
	}

	public String getInstallTime() {
		return installTime;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getIsDealUsecard() {
		return isDealUsecard;
	}

	public void setIsDealUsecard(Integer isDealUsecard) {
		this.isDealUsecard = isDealUsecard;
	}

	public String getUsecardExpireTime() {
		return usecardExpireTime;
	}

	public void setUsecardExpireTime(String usecardExpireTime) {
		this.usecardExpireTime = usecardExpireTime;
	}

	public String getRemoveTime() {
		return removeTime;
	}

	public void setRemoveTime(String removeTime) {
		this.removeTime = removeTime;
	}

	public int getAreaIndex() {
		return areaIndex;
	}

	public void setAreaIndex(int areaIndex) {
		this.areaIndex = areaIndex;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	@Resource
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public IYxareaService getYxareaService() {
		return yxareaService;
	}

	@Resource
	public void setYxareaService(IYxareaService yxareaService) {
		this.yxareaService = yxareaService;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public AreaVO getAreaVO() {
		return areaVO;
	}

	public void setAreaVO(AreaVO areaVO) {
		this.areaVO = areaVO;
	}

	public Integer getDevType() {
		return devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}

	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Yxarea> getYxareas() {
		return yxareas;
	}

	public void setYxareas(List<Yxarea> yxareas) {
		this.yxareas = yxareas;
	}

	public List<AreaVO> getAreaVOs() {
		return areaVOs;
	}

	public void setAreaVOs(List<AreaVO> areaVOs) {
		this.areaVOs = areaVOs;
	}
}
