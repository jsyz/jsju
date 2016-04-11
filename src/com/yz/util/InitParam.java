package com.yz.util;

import com.yz.model.Usero;

/**
 * 项目初始化所用参数
 * 
 * @author Administrator
 * 
 */
public class InitParam {

	public static final String[] AREAS = { "宜城", "环科园", "开发区", "丁蜀", "和桥",
			"官林", "张渚", "周铁", "徐舍" };

	private static Usero usero;

	public static Usero getUsero() {
		usero = new Usero();
		usero.setRealname("管理员");
		usero.setUsername("admin");
		usero.setPassword("admin");
		usero.setUserLimit(0);
		return usero;
	}

	public static void setUsero(Usero usero) {
		InitParam.usero = usero;
	}

}
