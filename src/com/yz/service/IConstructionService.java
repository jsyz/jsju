package com.yz.service;

import java.util.List;

import com.yz.model.Construction;

public interface IConstructionService {

	// 添加对象
	public abstract void add(Construction construction) throws Exception;

	// 删除对象
	public abstract void delete(Construction construction);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Construction construction);

	// 获取所有对象
	public abstract List<Construction> getConstructions();

	// 加载一个id的对象
	public abstract Construction loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue,
			Construction construction);

	// 后台管理-获取符合条件的记录
	public abstract List<Construction> queryList(int con, String convalue,
			Construction construction, int page, int size);

	public abstract Construction getConstructionByConstructionname(String constructionname);

	public abstract Construction loadByCid(int cid);

}