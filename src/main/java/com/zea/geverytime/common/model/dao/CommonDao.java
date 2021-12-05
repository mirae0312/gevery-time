package com.zea.geverytime.common.model.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommonDao {
	
	private Properties prop = new Properties();
	
	public CommonDao() {
		File filepath = new File(CommonDao.class.getResource("/common-query.properties").getPath());
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
