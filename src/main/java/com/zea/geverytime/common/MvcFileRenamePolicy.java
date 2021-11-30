package com.zea.geverytime.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MvcFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originalFile) {
		File renamedFile = null;
		
		do {
			// 새파일명 생성
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_"); // 날짜포맷
			DecimalFormat df = new DecimalFormat("000"); // 숫자 세 자리 (소수점 x)
			// 확장자명
			String originalName = originalFile.getName(); // file클래스의 getName()메소드 사용
			String ext = "";
			int dot = originalName.lastIndexOf("."); // 점(.)이 존재하는 인덱스
			if(dot>-1) // (dot의 index가 존재한다면 = 확장자 표기가 있다면)
				ext = originalName.substring(dot); // 점(.)포함 이후의 문자열
			String newName = sdf.format(new Date())+df.format(Math.random()*999) + ext;
			renamedFile = new File(originalFile.getParent(),newName); // originalFile의 조상 디렉토리에 대한 파일 객체 생성
			
		}while(!createNewFile(renamedFile)); // 새로운 파일이 생성될 때까지
											 
		
		System.out.println("[MvcFileRenamePolicy] renamedFile = "+renamedFile);
		return renamedFile;
	}
	
	private boolean createNewFile(File f) { 
	    try {
	      return f.createNewFile(); // 새로운 이름의 파일이 생성되면 true리턴
	    }
	    catch (IOException ignored) {
	      return false; // 기존에 같은 이름의 파일이 존재하면 false리턴
	    }
	}
}
