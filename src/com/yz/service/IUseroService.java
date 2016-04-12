package com.yz.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yz.model.Usero;

public interface IUseroService {

	//添加对象
	public abstract void add(Usero usero) throws Exception;

	//删除对象
	public abstract void delete(Usero usero);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Usero usero);

	//获取所有对象
	public abstract List<Usero> getUseros();

	//加载一个id的对象
	public abstract Usero loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Usero usero);

	//后台管理-获取符合条件的记录
	public abstract List<Usero> queryList(int con, String convalue,Usero usero, int page,
			int size);

	//用户登录
	public abstract  Usero useroLogin(String useroname, String password) ;
	
	/**
	 * 用户登录
	 */
/*	@GET
	@Path("/userologin/{useroname}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Useromo> useroLogin(@PathParam("useroname") String useroname,@PathParam("password") String password);
	*/
	/**
	 * 用户修改密码
	 */
	@GET
	@Path("/updatepwd/{useroname}/{oldpwd}/{newpwd}")
	@Produces(MediaType.APPLICATION_JSON)
	public int updatePwd(@PathParam("useroname") String useroname,@PathParam("oldpwd") String oldpwd,@PathParam("newpwd") String newpwd);

	public abstract Usero getUseroByUseroname(String useroname);

	public abstract int saveAndReturn(Usero usero);

}