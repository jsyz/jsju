package com.yz.service;

import java.util.List;

import com.yz.model.Subunit;

public interface ISubunitService {

	// 添加对象
	public abstract void add(Subunit subunit) throws Exception;

	// 删除对象
	public abstract void delete(Subunit subunit);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Subunit subunit);

	// 获取所有对象
	public abstract List<Subunit> getSubunits();

	// 加载一个id的对象
	public abstract Subunit loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Subunit subunit);

	// 后台管理-获取符合条件的记录
	public abstract List<Subunit> queryList(int con, String convalue,
			Subunit subunit, int page, int size);

	public abstract Subunit getSubunitBySubunitname(String subunitname);

}