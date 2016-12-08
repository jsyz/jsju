package com.yz.service;

import java.util.List;

import com.yz.model.Concreterecord;

public interface IConcreterecordService {

	// 添加对象
	public abstract void add(Concreterecord concreterecord) throws Exception;

	// 删除对象
	public abstract void delete(Concreterecord concreterecord);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Concreterecord concreterecord);

	// 获取所有对象
	public abstract List<Concreterecord> getConstructionpics();

	// 加载一个id的对象
	public abstract Concreterecord loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Concreterecord concreterecord);

	// 后台管理-获取符合条件的记录
	public abstract List<Concreterecord> queryList(int con, String convalue,
			Concreterecord concreterecord, int page, int size);

	public abstract List<Concreterecord> queryList(int concreterecordType,int con, String convalue,
			int page, int size);
	
	public abstract Concreterecord getConstructionpicByConstructionpicname(String constructionpicname);

	public abstract Concreterecord loadByConidAndRow(int conid, int rowid);
}
