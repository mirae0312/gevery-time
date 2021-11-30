package com.zea.geverytime.common;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import com.oreilly.servlet.MultipartRequest;
import com.zea.geverytime.common.model.vo.Attachment;

public class MvcUtils {
	public static String getEncryptedPassword(String password) {
		
		// 1.암호화처리
		byte[] encrypted = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] plain = password.getBytes("utf-8");
			md.update(plain); // md객체에 원본문자열 설정
			encrypted = md.digest(); // 암호화
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(encrypted);
		
		// 2.인코딩처리 
		// 영문자, 숫자, +, / 64개의 문자를 사용(= 패딩문자도 사용)
		Encoder encoder = Base64.getEncoder();
		String encryptedPassword = encoder.encodeToString(encrypted);
		
		System.out.println(encryptedPassword);
		
		return  encryptedPassword;
	}
	public static String getPagebar(int cPage, int numPerPage, int pageBarSize, int totalContentCount,
			String url) {
		StringBuilder str = new StringBuilder();
		
		int totalPage = (int)Math.ceil((double)totalContentCount/numPerPage);
		int endPage = (int)Math.ceil((double)cPage/pageBarSize)*pageBarSize;
		int startPage = endPage-pageBarSize+1;
		System.out.println(totalContentCount);
		System.out.println(startPage);
		System.out.println(endPage);
		System.out.println(totalPage);
		int pageNum = startPage;
		
		// 1. startPage가 1페이지가 아닌 경우 prev만들기
		if(startPage!=1) {
			str.append("<a data-page='"+(startPage-1)+"'>prev</a>");
		}
		// 2. 숫자만들기 (+ next버튼)
		// 토탈페이지가 엔드페이지보다 큰 경우 -> 숫자 끝까지 + next
		if(totalPage>endPage) {
			while(pageNum<=endPage) {
				if(pageNum!= cPage) {
					str.append("<a data-page='"+pageNum+"'>"+pageNum+"</a>");
				}
				else {
					str.append("<span class='cPage'>"+cPage+"</span>");
				}
				pageNum++;
			}
			// next버튼 추가
			str.append("<a data-page='"+(endPage+1)+"'>next</a>");
		}
		// 토탈페이지가 엔드페이지보다 작거나 같은 경우 -> 토탈페이지까지 (next버튼 x)
		else {
			while(pageNum<=totalPage) {
				if(pageNum!= cPage) {
					str.append("<a data-page='"+pageNum+"'>"+pageNum+"</a>");
				}
				else {
					str.append("<span class='cPage'>"+cPage+"</span>");
				}
				pageNum++;
			}
		}
		return str.toString();
	}

	public static Attachment makeAttachment(MultipartRequest multipartRequest, String fileName) {
		Attachment attachment = new Attachment();
		String orName = multipartRequest.getOriginalFileName(fileName);
		String reName = multipartRequest.getFilesystemName(fileName);
		attachment.setOriginalFilename(orName);
		attachment.setRenamedFilename(reName);
		return attachment;
	}
	
}
