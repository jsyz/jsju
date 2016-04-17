package com.yz.service;

import java.util.List;

import com.yz.model.Spreadsheet;

public interface ISpreadsheetService {

	// 添加对象
	public abstract void add(Spreadsheet spreadsheet) throws Exception;

	// 删除对象
	public abstract void delete(Spreadsheet spreadsheet);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Spreadsheet spreadsheet);

	// 获取所有对象
	public abstract List<Spreadsheet> getSpreadsheets();

	// 加载一个id的对象
	public abstract Spreadsheet loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Spreadsheet spreadsheet);

	// 后台管理-获取符合条件的记录
	public abstract List<Spreadsheet> queryList(int con, String convalue,
			Spreadsheet spreadsheet, int page, int size);

	public abstract Spreadsheet getSpreadsheetBySpreadsheetname(String spreadsheetname);

	public abstract int getTotalCount(int con, String convalue, int pid,
			Integer[] sheetTypes);

	public abstract List<Spreadsheet> queryList(int con, String convalue,
			int pid, Integer[] sheetTypes, int page, int size);

}