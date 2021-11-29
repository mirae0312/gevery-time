package com.zea.geverytime.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MvcInfoFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originalFile) {
		
		File renamedFile = null;

		do {
			// 새 파일명 생성
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat decimalFormat = new DecimalFormat("000");
			
			// 확장자명
			String originalName = originalFile.getName();
			String ext = "";
			int dot = originalName.lastIndexOf(".");
			if(dot > -1)
				ext = originalName.substring(dot);
			String newName = simpleDateFormat.format(new Date()) + decimalFormat.format(Math.random() * 999) + ext;
			
			// 생성을 시도학 새 File객체
			renamedFile = new File(originalFile.getParent(), newName);
		} while(!createNewFile(renamedFile));
		
		return renamedFile;
	}
	
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch(IOException ignored) {
			return false;
		}
	}

}
