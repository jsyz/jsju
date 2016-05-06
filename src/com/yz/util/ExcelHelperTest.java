/*package com.yz.util;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.yz.model.Usero;


*//**
 * 
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 *//*
public class ExcelHelperTest {

	@Test
	public void test() {
		String path = "E://test//test.xlsx";
		System.out.println(new File(path).exists());
		List<Usero> list = null;
		try {
			list = ExcelHelper.exportListFromExcel(new File(path), 0);
			System.out.println(list.size());
			
			for(Usero usero:list)
			{
				System.out.println(usero.getLochus()+" "+usero.getShortnumber()+" "+usero.getName() );
			}
			
		} catch (IOException e) {
			fail();
		}

	}
}*/