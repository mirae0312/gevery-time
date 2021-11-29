package com.zea.geverytime.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

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
}
