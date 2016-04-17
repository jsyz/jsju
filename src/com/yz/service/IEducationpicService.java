package com.yz.service;

import java.util.List;

import com.yz.model.Educationpic;

public interface IEducationpicService {

	// 添加对象
	public abstract void add(Educationpic educationpic) throws Exception;

	// 删除对象
	public abstract void delete(Educationpic educationpic);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Educationpic educationpic);

	// 获取所有对象
	public abstract List<Educationpic> getEducationpics();

	// 加载一个id的对象
	public abstract Educationpic loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Educationpic educationpic);

	// 后台管理-获取符合条件的记录
	public abstract List<Educationpic> queryList(int con, String convalue,
			Educationpic educationpic, int page, int size);

	public abstract Educationpic getEducationpicByEducationpicname(String educationpicname);

}