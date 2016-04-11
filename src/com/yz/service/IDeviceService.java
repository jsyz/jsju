package com.yz.service;

import java.util.List;

import com.yz.model.Device;

public interface IDeviceService {

	// 添加对象
	public abstract void add(Device device) throws Exception;

	// 删除对象
	public abstract void delete(Device device);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Device device);

	// 获取所有对象
	public abstract List<Device> getDevices();

	// 加载一个id的对象
	public abstract Device loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, Device device);

	// 后台管理-获取符合条件的记录
	public abstract List<Device> queryList(int con, String convalue,
			Device device, int page, int size);

	public abstract Device getDeviceByDevicename(String devicename);

}