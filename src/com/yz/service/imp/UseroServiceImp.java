package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yz.dao.IUseroDao;
import com.yz.model.UserRole;
import com.yz.model.Usero;
import com.yz.service.IUseroService;
@Component("userRoleService")
public class UseroServiceImp implements IUseroService {
	private IUseroDao useroDao;
	public IUseroDao getUseroDao() {
		return useroDao;
	}
	@Resource
	public void setUseroDao(IUseroDao useroDao) {
		this.useroDao = useroDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#add(com.yz.model.Usero)
	 */
	public void add(Usero usero) throws Exception {
		useroDao.save(usero);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#delete(com.yz.model.Usero)
	 */
	public void delete(Usero usero) {
		useroDao.delete(usero);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		useroDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#update(com.yz.model.Usero)
	 */
	public void update(Usero usero) {
		useroDao.update(usero);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#getUseros()
	 */
	public List<Usero> getUseros() {
		return useroDao.getUseros();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#loadById(int)
	 */
	public Usero loadById(int id) {
		return useroDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Usero usero) {
		String queryString = "select count(*) from Usero mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.username like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return useroDao.getUniqueResult(queryString,p);
	}
	public Usero getUseroByUseroname(String username) {
		String queryString="from Usero mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return useroDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IUseroServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Usero> queryList(int con, String convalue, Usero usero, int page, int size) {
		String queryString = "from Usero mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.username like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return useroDao.pageList(queryString,p,page,size);
	}
	//用户登录
	public Usero userologin(String username, String password) {
		String queryString="from Usero mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return useroDao.queryByNamedParam(queryString,paramNames,values);
	} 
	
	//webservice部分
	/**
	 * 用户登录
	 */
/*	@GET
	@Path("/userologin/{useroname}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Useromo> useroLogin(@PathParam("useroname") String useroname,@PathParam("password") String password){
		List<Useromo> useromos = new ArrayList<Useromo>();
		
		String queryString="from Usero mo where mo.useroname=:useroname and mo.password=:password";
		String[] paramNames=new String[]{"useroname","password"};
		Object[] values=new Object[]{useroname,password};
		Usero loginer=useroDao.queryByNamedParam(queryString, paramNames, values);
		if(loginer!=null){
			Useromo useromo = new Useromo();
			useromo.setUseroname(loginer.getUseroname());
			useromos.add(useromo);
//			islogin=true;
		}
		return useromos;
		
	}*/
	/**
	 * 用户修改密码
	 */
	@GET
	@Path("/updatepwd/{useroname}/{oldpwd}/{newpwd}")
	@Produces(MediaType.APPLICATION_JSON)
	public int updatePwd(@PathParam("useroname") String useroname,@PathParam("oldpwd") String oldpwd,@PathParam("newpwd") String newpwd){
		int logined=0;
		String queryString="update Usero mo set mo.password=? where mo.useroname=? and mo.password=?";
		Object[] p=new Object[]{newpwd,useroname,oldpwd};
		logined=useroDao.updateUseroByhql(queryString,p);
		return logined;
		
	}

	public Usero useroLogin(String username, String password) {
		String queryString="from UserRole mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return useroDao.queryByNamedParam(queryString,paramNames,values);
	} 
}
