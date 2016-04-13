package com.yz.service;

import java.util.List;

import com.yz.model.Dangerargument;

public interface IDangerargumentService {

	// 添加对象
	public abstract void add(Dangerargument dangerargument) throws Exception;

	// 删除对象
	public abstract void delete(Dangerargument dangerargument);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Dangerargument dangerargument);

	// 获取所有对象
	public abstract List<Dangerargument> getDangerarguments();

	// 加载一个id的对象
	public abstract Dangerargument loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Dangerargument dangerargument);

	// 后台管理-获取符合条件的记录
	public abstract List<Dangerargument> queryList(int con, String convalue,
			Dangerargument dangerargument, int page, int size);

	public abstract Dangerargument getDangerargumentByDangerargumentname(String dangerargumentname);

}