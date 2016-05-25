package com.yz.service;

import java.util.List;

import com.yz.model.Constructionpic;

public interface IConstructionpicService {

	// 添加对象
	public abstract void add(Constructionpic constructionpic) throws Exception;

	// 删除对象
	public abstract void delete(Constructionpic constructionpic);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Constructionpic constructionpic);

	// 获取所有对象
	public abstract List<Constructionpic> getConstructionpics();

	// 加载一个id的对象
	public abstract Constructionpic loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Constructionpic constructionpic);

	// 后台管理-获取符合条件的记录
	public abstract List<Constructionpic> queryList(int con, String convalue,
			Constructionpic constructionpic, int page, int size);

	public abstract Constructionpic getConstructionpicByConstructionpicname(String constructionpicname);

}