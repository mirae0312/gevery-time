package com.zea.geverytime.market.purchase.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

/**
 * Servlet implementation class PurchasePaymentsServlet
 */
@WebServlet("/purchase/payments")
public class PurchasePaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 결제내역 보내기
		String impUid = request.getParameter("imp_uid");
		String merchantUid = request.getParameter("merchant_uid");
		
		// url 설정
		URL url = new URL("https://api.iamport.kr/users/getToken");
		
		// 연결 열기
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		// 요청 방법 설정
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		// 응답 데이터 형식 지정
		con.setRequestProperty("Accept", "application/json");
		
		// doOUtput 확인
		con.setDoOutput(true);
		
		
		// 요청 본문 생성
		String jsonInputString = "{\"imp_key\": \"3102689863625731\", \"imp_secret\": \"e4568ee8b5be247f55457ea7ae9d453569fc24ed358481bbcfc8ed4fcfefe6ec2a3f114e0988fe40\"}";
		
		try(OutputStream os = con.getOutputStream()){
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		String requestString = "";
		
		StringBuilder responseData = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			System.out.println("con.getin@@: " + con.getInputStream());
			String responseLine = null;
			while((responseLine = br.readLine()) != null) {
				responseData.append(responseLine.trim());
				System.out.println("resLIne@" + responseLine);
			}
			requestString = responseData.toString();
		}
		
		// connection 종료
		con.disconnect();
		
		JSONParser jsonParser = new JSONParser();
		String token = "";
		try {
			JSONObject jsonObj = (JSONObject) jsonParser.parse(requestString);
			JSONObject getToken = (JSONObject) jsonObj.get("response");
			System.out.println("getToken@: "+getToken.get("access_token") );
			token = (String) getToken.get("access_token");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 2. 검증하기
		
		url = new URL("https://api.iamport.kr/payments/"+impUid);
		
		// 연결 열기
		con = (HttpURLConnection)url.openConnection();
		// 요청 방법 설정
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", token);
		
		con.setDoOutput(true);
		
		StringBuilder responseData2 = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			System.out.println("con.getin@@: " + con.getInputStream());
			String responseLine = null;
			while((responseLine = br.readLine()) != null) {
				responseData2.append(responseLine.trim());
				System.out.println("resLIne@" + responseLine);
			}
			requestString = responseData2.toString();
		}
		System.out.println("paymentData2 : "+ requestString);
		
		// connection 종료
		con.disconnect();
		
		int amountToBePaid = Integer.parseInt(request.getParameter("amountToBePaid"));
		System.out.println("amount : "+amountToBePaid);
		
		// 값 불러오기
		try {
			JSONObject jsonObj = (JSONObject) jsonParser.parse(requestString);
			JSONObject getRes = (JSONObject) jsonObj.get("response");
			long amount = (long)getRes.get("amount");
			String status = (String) getRes.get("status");
			System.out.println("amount : "+amount + " / status : " + status);
			
			// 검증 후 response
			Map<String, Object> map = new HashMap<>();
			if(amount == (long)amountToBePaid && status.equals("paid")) {
				response.setContentType("application/json; charset=utf-8");
				map.put("msg", "결제 성공");
				new Gson().toJson(map, response.getWriter());
			} else {
				response.setContentType("application/json; charset=utf-8");
				map.put("msg", "결제 실패(위변조 데이터)");
				new Gson().toJson(map, response.getWriter());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	}

}
