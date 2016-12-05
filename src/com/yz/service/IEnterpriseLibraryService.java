package com.yz.service;

import java.util.List;

import com.yz.model.EnterpriseLibrary;


public interface IEnterpriseLibraryService {
	// 添加对象
	public abstract void add(EnterpriseLibrary enterpriseLibrary) throws Exception;

	// 删除对象
	public abstract void delete(EnterpriseLibrary enterpriseLibrary);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(EnterpriseLibrary enterpriseLibrary);

	// 获取所有对象
	public abstract List<EnterpriseLibrary> getEnterpriseLibrarys();

	// 加载一个id的对象
	public abstract EnterpriseLibrary loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数

	// 后台管理-获取符合条件的记录
	public abstract List<EnterpriseLibrary> queryList(int con, String convalue,
			int page, int size);

	public abstract EnterpriseLibrary getEnterpriseLibraryByEnterpriseLibraryname(String enterpriseLibraryname);

	public abstract int getTotalCount(int con, String convalue);
}
