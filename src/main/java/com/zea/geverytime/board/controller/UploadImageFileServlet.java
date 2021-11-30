package com.zea.geverytime.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.zea.geverytime.common.MvcFileRenamePolicy;

/**
 * Servlet implementation class UploadImageFileServlet
 */
@WebServlet("/board/uploadImageFile")
public class UploadImageFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		JsonObject jsonObject = new JsonObject();
//
//		MultipartFile multipartFile = request.getFile("file");
//		// 내부경로로 저장
//		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
//		String fileRoot = contextRoot+"resources/fileupload/";
//		
//		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
//		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
//		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
//		
//		File targetFile = new File(fileRoot + savedFileName);	
//		try {
//			InputStream fileStream = multipartFile.getInputStream();
//			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
//			jsonObject.addProperty("url", "/summernote/resources/fileupload/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
//			jsonObject.addProperty("responseCode", "success");
//				
//		} catch (IOException e) {
//			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
//			jsonObject.addProperty("responseCode", "error");
//			e.printStackTrace();
//		}
//		String a = jsonObject.toString();
//		return a;
		
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		// 첨부파일 최대 용량
		int maxPostSize = 1024*1024*10; // 10MB
		// 인코딩방식
		String encoding = "utf-8";
		
		// 파일명 재지정 정책객체 : 
		// DefaultFileRenamePolicy 동일한 이름의 파일은 numbering을 통해 overwrite을 방지(기본형)
		// FileRenamePolicy policy = new DefaultFileRenamePolicy();
		FileRenamePolicy policy = new MvcFileRenamePolicy(); // 내가 만든 rename메소드가 담긴 클래스 (FileRenamePolicy을 구현하는 클래스임)
		
		// 객체생성하는 순간 서버에 사용자 파일이 저장되며 정책에 따라 renamed된 이름도 발급됨
		// multipartRequest객체에 policy객체 넘겨주면 알아서 policy내의 메소드 사용해서 renamed파일이름 생성하여 객체 내에 저장
		MultipartRequest multipartRequest = new MultipartRequest(request,saveDirectory,maxPostSize,encoding,policy);
		
		String renamedFileName = multipartRequest.getFilesystemName("file");

	}
}
