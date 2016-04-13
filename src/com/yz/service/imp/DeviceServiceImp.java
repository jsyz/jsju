package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IDeviceDao;
import com.yz.model.Device;
import com.yz.service.IDeviceService;
@Component("deviceService")
public class DeviceServiceImp implements IDeviceService {
	private IDeviceDao deviceDao;
	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}
	@Resource
	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#add(com.yz.model.Device)
	 */
	public void add(Device device) throws Exception {
		deviceDao.save(device);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#delete(com.yz.model.Device)
	 */
	public void delete(Device device) {
		deviceDao.delete(device);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		deviceDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#update(com.yz.model.Device)
	 */
	public void update(Device device) {
		deviceDao.update(device);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#getDevices()
	 */
	public List<Device> getDevices() {
		return deviceDao.getDevices();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#loadById(int)
	 */
	public Device loadById(int id) {
		return deviceDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Device device) {
		String queryString = "select count(*) from Device mo where 1=1 and mo.id!="+device.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return deviceDao.getUniqueResult(queryString,p);
	}
	
	public int getTotalCount(int con, String convalue,
			int projectId) {
		String queryString = "select count(*) from Device mo where 1=1 and mo.project.id="+projectId;
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return deviceDao.getUniqueResult(queryString,p);
	} 
	
	public Device getDeviceByDevicename(String username) {
		String queryString="from Device mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return deviceDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#queryList(int, java.lang.String, int, int)
	 */
//	public List<Device> queryList(int con, String convalue, Device device, int page, int size) {
//		String queryString = "from Device mo where 1=1 and mo.id!="+device.getId();
//		Object[] p = null;
//		if(con!=0&&convalue!=null&&!convalue.equals("")){
//			if(con==1){
//				queryString += "and mo.unit.name like ? "; 
//			}
//			if(con==2){
//				queryString += "and mo.realname like ? "; 
//				
//			}
//			if(con==3){
//				queryString += "and mo.number like ? "; 
//			}
//			p = new Object[]{'%'+convalue+'%'};
//		}
//		return deviceDao.pageList(queryString,p,page,size);
//	}
	//用户登录
	public Device devicelogin(String username, String password) {
		String queryString="from Device mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return deviceDao.queryByNamedParam(queryString,paramNames,values);
	}
	public List<Device> queryList(int con, String convalue, int projectId,
			int page, int size) {
		String queryString = "from Device mo where 1=1 and mo.project.id="+projectId;
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return deviceDao.pageList(queryString,p,page,size);
	}
	
	
}
