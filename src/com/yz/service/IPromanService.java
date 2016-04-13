package com.yz.service;

import java.util.List;

import com.yz.model.Proman;

public interface IPromanService {

	// 添加对象
	public abstract void add(Proman proman) throws Exception;

	// 删除对象
	public abstract void delete(Proman proman);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Proman proman);

	// 获取所有对象
	public abstract List<Proman> getPromans();

	// 加载一个id的对象
	public abstract Proman loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, int projectId);

	// 后台管理-获取符合条件的记录
	public abstract List<Proman> queryList(int con, String convalue,
			int projectId, int page, int size);

	public abstract Proman getPromanByPromanname(String promanname);

}