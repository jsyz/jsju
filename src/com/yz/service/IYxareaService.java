package com.yz.service;

import java.util.List;

import com.yz.model.Usero;
import com.yz.model.Yxarea;

public interface IYxareaService {

	//添加对象
		public abstract void add(Yxarea yxarea) throws Exception;

	//删除对象
	public abstract void delete(Yxarea yxarea);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Yxarea yxarea);

	//获取所有对象
	public abstract List<Yxarea> getYxareas();

	//加载一个id的对象
	public abstract Yxarea loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Usero usero);

	//后台管理-获取符合条件的记录
	public abstract List<Yxarea> queryList(int con, String convalue,Usero usero, int page,
			int size);

	public abstract Yxarea getYxareaByYxareaname(String yxareaname);

}