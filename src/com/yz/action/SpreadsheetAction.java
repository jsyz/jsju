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
import java.util.Arrays;
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
import com.yz.model.Project;
import com.yz.model.Spreadsheet;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IProjectService;
import com.yz.service.ISpreadsheetService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;
import com.yz.vo.SheetNumber;
import com.yz.vo.SheetVO;

/**
 * @author lq 表格
 * 
 */
@Component("spreadsheetAction")
@Scope("prototype")
public class SpreadsheetAction extends ActionSupport implements RequestAware,
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
	private int pid;// 按项目id
	private int areaIndex;// 区域标示
	private String sheetTypeStr;// 表格类型可能为多个
	private Integer[] sheetTypes;
	private String pageName;// 跳转到页面名称
	private Integer sheetType;
	private String sheetName;// 表格名称

	// 批量删除
	private String checkedIDs;

	// service层对象
	private ISpreadsheetService spreadsheetService;
	private IYxareaService yxareaService;
	private IProjectService projectService;

	// 单个对象
	private Spreadsheet spreadsheet;
	private Project project;
	private AreaVO areaVO;
	private SheetVO sheetVO;
	private SheetNumber sheetNumber;

	// list对象
	private List<Spreadsheet> spreadsheets;
	private List<Project> projects;
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;
	private List<SheetVO> sheetVOs;

	// 试图展示控制
	private int viewChangedConent;

	// 相同表格试图控制 0：表示从日常管理界面进入 1:表示由档案管理页面进入表单
	private int pageType;

	/**
	 * 表格管理
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

		handleSheetTypes(sheetTypeStr);

		Arrays.sort(sheetTypes); // 排序数组

		if (Arrays.binarySearch(sheetTypes, 7) >= 0) { // 若找不到，则当前企业不在任何填报期内
			viewChangedConent = 1;
		}

		if (sheetTypes.length > 0) {
			handleSheetVOs(sheetTypes);// 处理同一页面按钮显示
		}

		switch (pageType) {
		case 0:
			pageName = "档案管理";
			break;
		case 1:
			pageName = "日常监管";
			break;
		case 2:
			pageName = "项目评价";
			break;
		default:
			break;
		}

		// 获得当前项目
		project = projectService.loadById(pid);
		// 总记录数
		totalCount = spreadsheetService.getTotalCount(con, convalue, pid,
				sheetTypes);
		// 总页数
		pageCount = spreadsheetService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		spreadsheets = spreadsheetService.queryList(con, convalue, pid,
				sheetTypes, page, size);

		return "list";
	}

	private void handleSheetTypes(String sheetTypeString) {
		// TODO Auto-generated method stub
		System.out.println(sheetTypeString);
		List<Integer> types = new ArrayList<Integer>();
		if (sheetTypeString != null) {
			String strs[] = sheetTypeString.split(",");
			for (int i = 0; i < strs.length; i++) {
				types.add(Integer.parseInt(strs[i]));
			}
		}
		sheetTypes = (Integer[]) types.toArray(new Integer[types.size()]);
	}

	// 动态显示按钮
	private void handleSheetVOs(Integer[] types) {
		// TODO Auto-generated method stub
		sheetVOs = new ArrayList<SheetVO>();
		for (int i = 0; i < types.length; i++) {
			SheetVO sheet = new SheetVO();
			switch (types[i]) {
			// 1,2同一个页面
			case 1:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量行为资料监督抽查记录");
				break;
			case 2:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("施工单位安全生产行为监督检查表");
				break;
			// 3,4同一页面
			case 3:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督抽查（巡查）记录");
				break;
			case 4:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全生产监督抽查记录表");
				break;
			case 5:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("监理单位安全生产行为监督检查表");
				break;
			case 6:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设单位安全生产行为监督检查表");
				break;
			case 7:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程施工停工整改通知书");
				break;
			case 8:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("现浇混凝土强度监督抽测记录");
				break;
			case 9:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督抽测记录（钢筋保护层厚度）");
				break;
			case 10:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督抽测记录（现浇楼板结构厚度）");
				break;
			case 11:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督抽测记录(导线)");
				break;
			case 12:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("市政工程质量监督抽测记录");
				break;
			case 13:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量不良行为记录表");
				break;
			case 14:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("江苏省建筑施工项目经理质量安全责任记分通知单");
				break;
			case 15:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("行政处罚记录表");
				break;
			case 16:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("奖励情况汇总表");
				break;
			case 17:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("民工工资投诉处理记录表");
				break;
			case 18:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全事故快报表");
				break;
			case 19:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督工作计划");
				break;
			case 20:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量行为资料监督抽查记录");
				break;
			case 21:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督抽查（巡查）记录");
				break;
			case 22:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督抽检通知书");
				break;
			case 23:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量整改完成报告");
				break;
			case 24:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程局部停工（暂停）通知书");
				break;
			case 25:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程复工申请报告");
				break;
			case 26:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程复工通知书");
				break;
			case 27:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量事故处理监督记录");
				break;
			case 28:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程质量申请行政处罚报告");
				break;
			case 29:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("单位（子单位）工程质量竣工验收监督记录");
				break;
			case 30:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程质量监督报告");
				break;
			case 31:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督人员情况一览表");
				break;
			case 33:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程中止施工安全监督申请书");
				break;
			case 34:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程恢复施工安全监督申请书");
				break;
			case 35:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全监督通知书");
				break;
			case 36:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全生产监督告知书");
				break;
			case 37:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全监督交底记录");
				break;
			case 38:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全生产约谈记录");
				break;
			case 39:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程施工安全隐患整改通知书");
				break;
			case 40:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程施工安全隐患整改完成报告书");
				break;
			case 41:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程施工复工申请书");
				break;
			case 42:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程施工复工通知书");
				break;
			case 43:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程施工阶段安全自检评定表");
				break;
			case 44:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全事故快报表");
				break;
			case 45:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全监督报告");
				break;
			case 46:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("其它资料");
				break;
			default:
				break;
			}
			sheetVOs.add(sheet);

		}
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {

		// 获得当前项目
		project = projectService.loadById(pid);
		switch (sheetType) {
		case 0:
			break;
		case 1:
			// pageName = "日常监管-行为监督抽查-工程质量行为资料监督抽查记录";
			sheetName = "工程质量行为资料监督抽查记录";
			break;
		case 2:
			// pageName = "日常监管-行为监督抽查-施工单位安全生产行为监督检查表";
			sheetName = "施工单位安全生产行为监督检查表";
			break;
		case 3:
			// pageName = "日常监管-日常巡查-工程质量监督抽查（巡查）记录";
			sheetName = "工程质量监督抽查（巡查）记录";
			break;
		case 4:
			// pageName = "日常监管-日常巡查-施工单位安全生产行为监督检查表";
			sheetName = "建设工程安全生产监督抽查记录表";
			break;
		case 5:
			// pageName = "日常监管-行为监督抽查-监理单位安全生产行为监督检查表";
			sheetName = "监理单位安全生产行为监督检查表";
			break;
		case 6:
			// pageName = "日常监管-行为监督抽查-建设单位安全生产行为监督检查表";
			sheetName = "建设单位安全生产行为监督检查表";
			break;
		case 7:
			// pageName = "日常监管-检查及整改情况-建设工程施工停工整改通知书";
			sheetName = "建设工程施工停工整改通知书";
			break;
		case 8:
			/*
			 * if (pageType == 1) { pageName = "现浇混凝土强度监督抽测记录"; } else {
			 * pageName = "日常监管-抽测抽查-现浇混凝土强度监督抽测记录"; }
			 */
			sheetName = "现浇混凝土强度监督抽测记录";
			break;
		case 9:
			/*
			 * if (pageType == 1) { pageName = "工程质量监督抽测记录（钢筋保护层厚度）"; } else {
			 * pageName = "日常监管-抽测抽查-工程质量监督抽测记录（钢筋保护层厚度）"; }
			 */
			sheetName = "工程质量监督抽测记录（钢筋保护层厚度）";
			break;
		case 10:
			/*
			 * if (pageType == 1) { pageName = "工程质量监督抽测记录（现浇楼板结构厚度）"; } else {
			 * pageName = "日常监管-抽测抽查-工程质量监督抽测记录（现浇楼板结构厚度）"; }
			 */
			sheetName = "工程质量监督抽测记录（现浇楼板结构厚度）";
			break;
		case 11:
			/*
			 * if (pageType == 1) { pageName = "工程质量监督抽测记录(导线)"; } else {
			 * pageName = "日常监管-抽测抽查-工程质量监督抽测记录(导线)"; }
			 */
			sheetName = "工程质量监督抽测记录(导线)";
			break;
		case 12:
			/*
			 * if (pageType == 1) { pageName = "市政工程质量监督抽测记录"; } else { pageName =
			 * "日常监管-抽测抽查-市政工程质量监督抽测记录"; }
			 */
			sheetName = "市政工程质量监督抽测记录";
			break;
		case 13:
			// pageName = "项目评价-工程质量不良行为记录表";
			sheetName = "工程质量不良行为记录表";
			break;
		case 14:
			// pageName = "项目评价-江苏省建筑施工项目经理质量安全责任记分通知单";
			sheetName = "江苏省建筑施工项目经理质量安全责任记分通知单";
			break;
		case 15:
			// pageName = "项目评价-行政处罚记录表";
			sheetName = "行政处罚记录表";
			break;
		case 16:
			// pageName = "项目评价-奖励情况汇总表";
			sheetName = "奖励情况汇总表";
			break;
		case 17:
			// pageName = "项目评价-民工工资投诉处理记录表";
			sheetName = "民工工资投诉处理记录表";
			break;
		case 18:
			// pageName = "项目评价-建设工程安全事故快报表";
			sheetName = "建设工程安全事故快报表";
			break;
		case 19:
			// pageName = "档案管理-工程质量监督工作计划";
			sheetName = "工程质量监督工作计划";
			break;
		case 20:
			// pageName = "档案管理-工程质量行为资料监督抽查记录";
			sheetName = "工程质量行为资料监督抽查记录";
			break;
		case 21:
			// pageName = "档案管理-工程质量监督抽查（巡查）记录";
			sheetName = "工程质量监督抽查（巡查）记录";
			break;
		case 22:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "工程质量监督抽检通知书";
			break;
		case 23:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "工程质量整改完成报告";
			break;
		case 24:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "工程局部停工（暂停）通知书";
			break;
		case 25:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "工程复工申请报告";
			break;
		case 26:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "工程复工通知书";
			break;
		case 27:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "工程质量事故处理监督记录";
			break;
		case 28:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程质量申请行政处罚报告";
			break;
		case 29:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "单位（子单位）工程质量竣工验收监督记录";
			break;
		case 30:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程质量监督报告";
			break;
		case 31:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "工程质量监督人员情况一览表";
			break;
		case 32:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "其它资料";
			break;
		case 33:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程中止施工安全监督申请书";
			break;
		case 34:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程恢复施工安全监督申请书";
			break;
		case 35:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程安全监督通知书";
			break;
		case 36:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程安全生产监督告知书";
			break;
		case 37:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程安全监督交底记录";
			break;
		case 38:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程安全生产约谈记录";
			break;
		case 39:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程施工安全隐患整改通知书";
		case 40:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程施工安全隐患整改完成报告书";
			break;
		case 41:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程施工复工申请书";
			break;
		case 42:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程施工复工通知书";
			break;
		case 43:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程施工阶段安全自检评定表";
			break;
		case 44:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程安全事故快报表";
			break;
		case 45:
			// pageName = "档案管理-建设工程安全事故快报表";
			sheetName = "建设工程安全监督报告";
		case 46:
			sheetName = "其他资料";
		default:
			break;
		}
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
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/sheet" + spreadsheet.getSheetType(), imageName,
					picture1);
			spreadsheet.setSheetImg("/sheet" + spreadsheet.getSheetType() + "/"
					+ imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/sheet" + spreadsheet.getSheetType(), imageName, picture2);
			spreadsheet.setSheetImg1("sheet"+ spreadsheet.getSheetType() + "/" + imageName);
		}
		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/sheet" + spreadsheet.getSheetType(), imageName, picture3);
			spreadsheet.setSheetImg2("sheet"+ spreadsheet.getSheetType() + "/" + imageName);
		}
		spreadsheetService.add(spreadsheet);
		arg[0] = "spreadsheetAction!list?pid=" + pid + "&sheetTypeStr="
				+ sheetTypeStr + "&pageType=" + pageType;
		arg[1] = "表格管理";
		return SUCCESS;
	}

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
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		spreadsheet = spreadsheetService.loadById(id);
		if (spreadsheet.getSheetImg() != null
				&& !spreadsheet.getSheetImg().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ spreadsheet.getSheetImg());
			photofile.delete();
		}
		if (spreadsheet.getSheetImg1() != null
				&& !spreadsheet.getSheetImg1().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ spreadsheet.getSheetImg1());
			photofile.delete();
		}
		if (spreadsheet.getSheetImg2() != null
				&& !spreadsheet.getSheetImg2().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ spreadsheet.getSheetImg2());
			photofile.delete();
		}
		spreadsheetService.delete(spreadsheet);
		arg[0] = "spreadsheetAction!list?pid=" + pid + "&sheetTypeStr="
				+ sheetTypeStr + "&pageType=" + pageType;
		arg[1] = "表格管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteSpreadsheets() {
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			spreadsheet = spreadsheetService.loadById(ids[i]);
			if (spreadsheet.getSheetImg() != null
					&& !spreadsheet.getSheetImg().replace(" ", "").equals("")) {
				File photofile = new File(ServletActionContext
						.getServletContext().getRealPath("/")
						+ spreadsheet.getSheetImg());
				photofile.delete();
			}
			if (spreadsheet.getSheetImg1() != null
					&& !spreadsheet.getSheetImg1().replace(" ", "").equals("")) {
				File photofile = new File(ServletActionContext
						.getServletContext().getRealPath("/")
						+ spreadsheet.getSheetImg1());
				photofile.delete();
			}
			if (spreadsheet.getSheetImg2() != null
					&& !spreadsheet.getSheetImg2().replace(" ", "").equals("")) {
				File photofile = new File(ServletActionContext
						.getServletContext().getRealPath("/")
						+ spreadsheet.getSheetImg2());
				photofile.delete();
			}
			spreadsheetService.delete(spreadsheet);
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
	 * 跳转到修改页面 仅仅当sheetType==7时,才会进入load,其他代码保留
	 * 
	 * @return
	 */
	public String load() {
		spreadsheet = spreadsheetService.loadById(id);
		switch (spreadsheet.getSheetType()) {
		case 0:
			break;
		case 1:
			pageName = "日常监管-行为监督抽查-工程质量行为资料监督抽查记录";
			sheetName = "工程质量行为资料监督抽查记录";
		case 2:
			pageName = "日常监管-行为监督抽查-施工单位安全生产行为监督检查表";
			sheetName = "施工单位安全生产行为监督检查表";
			break;
		case 3:
			pageName = "日常监管-日常巡查-工程质量监督抽查（巡查）记录";
			sheetName = "工程质量监督抽查（巡查）记录";
			break;
		case 4:
			pageName = "日常监管-日常巡查-施工单位安全生产行为监督检查表";
			sheetName = "建设工程安全生产监督抽查记录表";
			break;
		case 5:
			pageName = "日常监管-行为监督抽查-监理单位安全生产行为监督检查表";
			sheetName = "监理单位安全生产行为监督检查表";
			break;
		case 6:
			pageName = "日常监管-行为监督抽查-建设单位安全生产行为监督检查表";
			sheetName = "建设单位安全生产行为监督检查表";
			break;
		case 7:
			pageName = "日常监管-检查及整改情况-建设工程施工停工整改通知书";
			sheetName = "建设工程施工停工整改通知书";
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;
		case 11:
			break;
		case 12:
			break;
		case 13:
			break;
		case 14:
			break;
		default:
			break;
		}
		return "load";
	}

	/**
	 * 跳转到展示页面
	 * 
	 * @return
	 */
	public String view() {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		spreadsheet = spreadsheetService.loadById(id);
		return "view";
	}

	public String update() throws Exception {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/sheet" + spreadsheet.getSheetType(), imageName, picture1);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ spreadsheet.getSheetImg());
			photofile.delete();
			spreadsheet.setSheetImg("sheet"+ spreadsheet.getSheetType() + "/" + imageName);
		}
		if (picture2 != null && picture2FileName != null
				&& !picture2FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));
			this.upload("/sheet" + spreadsheet.getSheetType(), imageName, picture2);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ spreadsheet.getSheetImg1());
			photofile.delete();
			spreadsheet.setSheetImg1("sheet"+ spreadsheet.getSheetType() + "/" + imageName);
		}

		if (picture3 != null && picture3FileName != null
				&& !picture3FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));
			this.upload("/sheet" + spreadsheet.getSheetType(), imageName, picture3);
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ spreadsheet.getSheetImg2());
			photofile.delete();
			spreadsheet.setSheetImg2("sheet"+ spreadsheet.getSheetType() + "/" + imageName);
		}
		spreadsheetService.update(spreadsheet);
		arg[0] = "spreadsheetAction!list?pid=" + pid + "&sheetTypeStr="
				+ sheetTypeStr;
		arg[1] = "表格管理";
		return SUCCESS;
	}

	public String evaluate() {
		project = projectService.loadById(pid);
		pageName = "项目评价";
		handleSheetTypes(sheetTypeStr);// 处理表格取值范围
		spreadsheets = spreadsheetService
				.queryListByArrayRange(pid, sheetTypes);
		handleSheetNumber(spreadsheets);// 生成数量类
		return "evaluate";
	}

	public String archives() {
		project = projectService.loadById(pid);
		pageName = "档案管理";
		spreadsheets = spreadsheetService.queryListByPid(pid);// 默认所有表格
		handleSheetNumber(spreadsheets);// 生成数量类
		return "archives";
	}

	// 处理生成projectEvaluate
	private void handleSheetNumber(List<Spreadsheet> sheets) {
		sheetNumber = new SheetNumber();
		int _1 = 0;
		int _2 = 0;
		int _3 = 0;
		int _4 = 0;
		int _5 = 0;
		int _6 = 0;
		int _13 = 0;
		int _14 = 0;
		int _15 = 0;
		int _16 = 0;
		int _17 = 0;
		int _18 = 0;
		int entityQualityNumber = 0;
		int _19 = 0;
		int _20 = 0;
		int _21 = 0;
		int _22 = 0;
		int _23 = 0;
		int _24 = 0;
		int _25 = 0;
		int _26 = 0;
		int _27 = 0;
		int _28 = 0;
		int _29 = 0;
		int _30 = 0;
		int _31 = 0;
		int _32 = 0;
		int _33 = 0;
		int _34 = 0;
		int _35 = 0;
		int _36 = 0;
		int _37 = 0;
		int _38 = 0;
		int _39 = 0;
		int _40 = 0;
		int _41 = 0;
		int _42 = 0;
		int _43 = 0;
		int _44 = 0;
		int _45 = 0;
		int _46 = 0;
		for (int i = 0; i < sheets.size(); i++) {
			Spreadsheet sheet = sheets.get(i);
			switch (sheet.getSheetType()) {
			case 1:
				_1 = _1 + 1;
				break;
			case 2:
				_2 = _2 + 1;
				break;
			case 3:
				_3 = _3 + 1;
				break;
			case 4:
				_4 = _4 + 1;
				break;
			case 5:
				_5 = _5 + 1;
				break;
			case 6:
				_6 = _6 + 1;
				break;
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
				entityQualityNumber = entityQualityNumber + 1;
			case 13:
				_13 = _13 + 1;
				break;
			case 14:
				_14 = _14 + 1;
				break;
			case 15:
				_15 = _15 + 1;
				break;
			case 16:
				_16 = _16 + 1;
				break;
			case 17:
				_17 = _17 + 1;
				break;
			case 18:
				_18 = _18 + 1;
				break;
			case 19:
				_19 = _19 + 1;
				break;
			case 20:
				_20 = _20 + 1;
				break;
			case 21:
				_21 = _21 + 1;
				break;
			case 22:
				_22 = _22 + 1;
				break;
			case 23:
				_23 = _23 + 1;
				break;
			case 24:
				_24 = _24 + 1;
				break;
			case 25:
				_25 = _25 + 1;
				break;
			case 26:
				_26 = _26 + 1;
				break;
			case 27:
				_27 = _27 + 1;
				break;
			case 28:
				_28 = _28 + 1;
				break;
			case 29:
				_29 = _29 + 1;
				break;
			case 30:
				_30 = _30 + 1;
				break;
			case 31:
				_31 = _31 + 1;
				break;
			case 32:
				_32 = _32 + 1;
				break;
			case 33:
				_33 = _33 + 1;
				break;
			case 34:
				_34 = _34 + 1;
				break;
			case 35:
				_35 = _35 + 1;
				break;
			case 36:
				_36 = _36 + 1;
				break;
			case 37:
				_37 = _37 + 1;
				break;
			case 38:
				_38 = _38 + 1;
				break;
			case 39:
				_39 = _39 + 1;
				break;
			case 41:
				_41 = _41 + 1;
				break;
			case 42:
				_42 = _42 + 1;
				break;
			case 43:
				_43 = _43 + 1;
				break;
			case 44:
				_44 = _44 + 1;
				break;
			case 45:
				_45 = _45 + 1;
				break;
			case 46:
				_46 = _46 + 1;
				break;
			default:
				break;
			}

		}
		sheetNumber.setSheet1(_1);
		sheetNumber.setSheet2(_2);
		sheetNumber.setSheet3(_3);
		sheetNumber.setSheet4(_4);
		sheetNumber.setSheet5(_5);
		sheetNumber.setSheet6(_6);
		sheetNumber.setSheet13(_13);
		sheetNumber.setSheet14(_14);
		sheetNumber.setSheet15(_15);
		sheetNumber.setSheet16(_16);
		sheetNumber.setSheet17(_17);
		sheetNumber.setSheet18(_18);
		sheetNumber.setEntityQualityNumber(entityQualityNumber);
		sheetNumber.setSheet19(_19);
		sheetNumber.setSheet20(_20);
		sheetNumber.setSheet21(_21);
		sheetNumber.setSheet22(_22);
		sheetNumber.setSheet23(_23);
		sheetNumber.setSheet24(_24);
		sheetNumber.setSheet25(_25);
		sheetNumber.setSheet26(_26);
		sheetNumber.setSheet27(_27);
		sheetNumber.setSheet28(_28);
		sheetNumber.setSheet29(_29);
		sheetNumber.setSheet30(_30);
		sheetNumber.setSheet31(_31);
		sheetNumber.setSheet32(_32);
		sheetNumber.setSheet33(_33);
		sheetNumber.setSheet34(_34);
		sheetNumber.setSheet35(_35);
		sheetNumber.setSheet36(_36);
		sheetNumber.setSheet37(_37);
		sheetNumber.setSheet38(_38);
		sheetNumber.setSheet39(_39);
		sheetNumber.setSheet40(_40);
		sheetNumber.setSheet41(_41);
		sheetNumber.setSheet42(_42);
		sheetNumber.setSheet43(_43);
		sheetNumber.setSheet44(_44);
		sheetNumber.setSheet45(_45);
		sheetNumber.setSheet46(_46);
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

	public ISpreadsheetService getSpreadsheetService() {
		return spreadsheetService;
	}

	@Resource
	public void setSpreadsheetService(ISpreadsheetService spreadsheetService) {
		this.spreadsheetService = spreadsheetService;
	}

	public Spreadsheet getSpreadsheet() {
		return spreadsheet;
	}

	public void setSpreadsheet(Spreadsheet spreadsheet) {
		this.spreadsheet = spreadsheet;
	}

	public List<Spreadsheet> getSpreadsheets() {
		return spreadsheets;
	}

	public void setSpreadsheets(List<Spreadsheet> spreadsheets) {
		this.spreadsheets = spreadsheets;
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

	public int getAreaIndex() {
		return areaIndex;
	}

	public void setAreaIndex(int areaIndex) {
		this.areaIndex = areaIndex;
	}

	public AreaVO getAreaVO() {
		return areaVO;
	}

	public void setAreaVO(AreaVO areaVO) {
		this.areaVO = areaVO;
	}

	public List<AreaVO> getAreaVOs() {
		return areaVOs;
	}

	public void setAreaVOs(List<AreaVO> areaVOs) {
		this.areaVOs = areaVOs;
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

	public IProjectService getProjectService() {
		return projectService;
	}

	@Resource
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public String getSheetTypeStr() {
		return sheetTypeStr;
	}

	public void setSheetTypeStr(String sheetTypeStr) {
		this.sheetTypeStr = sheetTypeStr;
	}

	public Integer[] getSheetTypes() {
		return sheetTypes;
	}

	public void setSheetTypes(Integer[] sheetTypes) {
		this.sheetTypes = sheetTypes;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public SheetVO getSheetVO() {
		return sheetVO;
	}

	public void setSheetVO(SheetVO sheetVO) {
		this.sheetVO = sheetVO;
	}

	public List<SheetVO> getSheetVOs() {
		return sheetVOs;
	}

	public void setSheetVOs(List<SheetVO> sheetVOs) {
		this.sheetVOs = sheetVOs;
	}

	public Integer getSheetType() {
		return sheetType;
	}

	public void setSheetType(Integer sheetType) {
		this.sheetType = sheetType;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public File getPicture1() {
		return picture1;
	}

	public void setPicture1(File picture1) {
		this.picture1 = picture1;
	}

	public String getPicture1ContentType() {
		return picture1ContentType;
	}

	public void setPicture1ContentType(String picture1ContentType) {
		this.picture1ContentType = picture1ContentType;
	}

	public String getPicture1FileName() {
		return picture1FileName;
	}

	public void setPicture1FileName(String picture1FileName) {
		this.picture1FileName = picture1FileName;
	}

	public int getViewChangedConent() {
		return viewChangedConent;
	}

	public void setViewChangedConent(int viewChangedConent) {
		this.viewChangedConent = viewChangedConent;
	}

	public SheetNumber getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(SheetNumber sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public int getPageType() {
		return pageType;
	}

	public void setPageType(int pageType) {
		this.pageType = pageType;
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
